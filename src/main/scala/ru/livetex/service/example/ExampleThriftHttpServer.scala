package ru.livetex.service.example

import java.net.InetSocketAddress
import com.twitter.finagle.http.Http
import com.twitter.finagle.builder.ServerBuilder
import org.apache.thrift.protocol.TBinaryProtocol.Factory
import ru.livetex.service.example.Example.FinagledService


object ExampleThriftHttpServer {
  def main(args: Array[String]) {
    val processor = new ExampleImpl()
    val service = new FinagledService(processor, new Factory())
    val httpFilter = new HttpToByteArray()
    val httpService = httpFilter andThen service

    ServerBuilder()
      .name("ExampleThriftHttpServer")
      .bindTo(new InetSocketAddress(8080))
      .codec(Http())
      .build(httpService)
  }
}