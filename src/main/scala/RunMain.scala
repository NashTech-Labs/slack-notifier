import service.SlackServiceImpl

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
  * run RunMain to send the message "welcome user on slack!! :)" on slack channel named notify-me as user
  */
object RunMain extends App {

  val slackServiceImpl = SlackServiceImpl
  val result = Await.result(slackServiceImpl.sendSlackMsg("notify-me", "welcome user on slack!! :)", Some("user")), Duration(20, "seconds"))

}
