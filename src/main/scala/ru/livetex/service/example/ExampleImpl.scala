package ru.livetex.service.example

import java.util.concurrent.ConcurrentHashMap
import com.twitter.util.Future
import scala.collection.JavaConversions._


class ExampleImpl extends Example.FutureIface {
  val states = new ConcurrentHashMap[String, Boolean]()

  /**
   * Получить состояние.
   **/
  def getState(id: String): Future[Boolean] =
    Future(states.getOrElse(id, false))

  /**
   * Оповестить о состоянии.
   **/
  def notify(state: Boolean, id: String): Future[Unit] = {
    states(id) = state
    Future.Unit
  }

  /**
   * Получить состояние небезопасно.
   **/
  def getUnsafeState(id: String): Future[Boolean] = {
    if (states.contains(id)) {
      Future(states.getOrElse(id, false))
    } else {
      Future.exception(Error(id + "not found"))
    }
  }
}
