// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
import sttp.client3._
import sttp.model.StatusCode
import org.json4s._
import org.json4s.native.JsonMethods._

import org.potala.main.Groups.getLongPollServer
import org.potala.main.LongPollServer.pollOnce

import java.lang.System.getenv
import org.potala.main.LongPollServer

// Check the most basic functional works okay
class MySuite extends munit.FunSuite {
  test("example test that succeeds") {
    val obtained = 42
    val expected = 42
    assertEquals(obtained, expected)
  }

  test("Access to vk test") {
    val token = getenv("VK_TOKEN")
    val vkUrl = "https://api.vk.com/method/"
    val method = "messages.getConversations"
    val request = basicRequest.get(uri"$vkUrl$method?access_token=$token&v=5.131")
    val backend = HttpClientSyncBackend()
    val response = request.send(backend)
    assertEquals(response.code, StatusCode.Ok)

    val parsedJson = parse(response.body.merge)
    val doc = render(parsedJson)
    
    implicit val formats: Formats = DefaultFormats
    val jsonMap = parsedJson.extract[Map[String, Any]]
  }

  test("Extract server from longpoll server response") {
    val token = getenv("VK_TOKEN")
    val groupId = getenv("GROUP_ID")
    val data = getLongPollServer(token, groupId.toInt)
  }

  test("Get longpoll server") {
    val token = getenv("VK_TOKEN")
    val groupId = getenv("GROUP_ID")
    val (key, server, ts) = getLongPollServer(token, groupId.toInt)
    assert(key.isInstanceOf[String])
    assert(server.isInstanceOf[String])
    assert(ts.isInstanceOf[String])
  }

  test("pollOnce has no errors".ignore) {
    val token = getenv("VK_TOKEN")
    val groupId = getenv("GROUP_ID")
    val (key, server, ts) = getLongPollServer(token, groupId.toInt)
    val (updates, newTs) = LongPollServer.pollOnce(server, key, ts)
    var currentTs = newTs
    while (true) {
      val (newUpdates, tempTs) = LongPollServer.pollOnce(server, key, currentTs)
      currentTs = tempTs
      println(newUpdates)
    }
  }
}
