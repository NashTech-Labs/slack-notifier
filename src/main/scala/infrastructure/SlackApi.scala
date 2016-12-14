package infrastructure

import slack.api.SlackApiClient

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._

trait SlackApi {
  val token: String = "generated-token" // replace your Slack API token here
  val apiClient: SlackApiClient

  /**
    *
    * this method sends a msgBody on the channel named channelName as user
    */
  def send(channelName: String, msgBody: String, user: Option[String]): Future[Boolean] = {
    val channelId: Future[Option[String]] = getChannelId(channelName)
    channelId.map {
      _ match {
        case Some(channelId) =>
          apiClient.postChatMessage(channelId, msgBody, user)
          true
        case None =>
          false

      }
    }
  }

  /**
    *
    * this method returns the channel id of the channel named channelName
    */
  def getChannelId(channelName: String): Future[Option[String]] = {
    val channelsFuture: Future[Seq[Option[String]]] = apiClient.listChannels().map(channels =>
      channels.map(channel =>
        if (channel.name == channelName) {
          Some(channel.id)
        }
        else {
          None
        }
      ))
    channelsFuture.map(channels => channels.find(channel => channel.isDefined).flatten)
  }
}

object SlackApiImpl extends SlackApi {
  val apiClient: SlackApiClient = SlackApiClient(token)
}
