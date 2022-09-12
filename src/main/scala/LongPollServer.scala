package org.potala.main

import sttp.client3._
import org.json4s._
import org.json4s.native.JsonMethods._

import org.potala.const._
import sttp.model.Uri


class LongPollServer

case class LongPollResponse(val ts: String, val updates: List[Any])

object LongPollServer {
    def pollOnce(server: String, key: String, ts: String) = {
        val url = uri"$server?act=a_check&key=$key&ts=$ts&await=25&mode=2"
        val responseJSON = HTTPget(url)
        val parsedJSON = parse(responseJSON.body.merge)
        implicit val formats: Formats = DefaultFormats
        val serialized = parsedJSON.extract[Map[String, Any]]
        (serialized("updates"), serialized("ts").toString)
    }
}


