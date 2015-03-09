package ru.livetex.service.example

import com.twitter.finagle.Service
import com.twitter.finagle.Filter
import com.twitter.util.Future
import org.jboss.netty.buffer.ChannelBuffers
import org.jboss.netty.handler.codec.http._


class HttpToByteArray extends Filter[HttpRequest, HttpResponse, Array[Byte], Array[Byte]] {
  /**
   * Трансформация тела Http запроса в бинарное сообщение.
   * @param request - Http запрос.
   * @param service - сервис работающий с бинарными сообщениями.
   * @return - Http ответ.
   */
  def apply(request: HttpRequest, service: Service[Array[Byte], Array[Byte]]): Future[HttpResponse] = {
    val response = service(request.getContent.array())
    response flatMap { res =>
      val httpResponse = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK)
      httpResponse.setContent(ChannelBuffers.copiedBuffer(res))
      httpResponse.setHeader(HttpHeaders.Names.CONTENT_LENGTH, res.toString)
      Future.value(httpResponse)
    }
  }
}
