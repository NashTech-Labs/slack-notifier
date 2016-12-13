import service.SlackServiceImpl

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object RunMain extends App {

  val testSlackServiceImpl = SlackServiceImpl
  val result = Await.result(testSlackServiceImpl.sendSlackMsg("notify-me", "welcome user on slack!! :)", Some("user")), Duration(20, "seconds"))
}
