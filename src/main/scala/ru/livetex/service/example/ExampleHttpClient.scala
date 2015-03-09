package ru.livetex.service.example

import com.twitter.finagle.{Http, Service}
import com.twitter.util.{Await, Future}
import org.jboss.netty.handler.codec.http._

object ExampleHttpClient extends App {
  val client: Service[HttpRequest, HttpResponse] =
    Http.newService("localhost:8080")

  val request =  new DefaultHttpRequest(
    HttpVersion.HTTP_1_1, HttpMethod.GET, "/")

  val response: Future[HttpResponse] = client(request)
  response onSuccess { resp: HttpResponse =>
    println("GET success: " + resp)
  }
  Await.ready(response)
}
