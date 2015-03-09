package ru.livetex.service.example

import com.twitter.finagle.Thrift
import com.twitter.util.{Await, Future}

object ExampleThriftClient {
  def main(args: Array[String]) {
    val client = Thrift.newIface[Example.FutureIface]("127.0.0.1:8080")

    val response: Future[Boolean] = client.notify(true, "123123") flatMap {
      f: Unit => client.getState("123123")
    }

    response onSuccess { rep =>
      println("Current state: " + rep)
    }
    Await.ready(response)
  }
}
