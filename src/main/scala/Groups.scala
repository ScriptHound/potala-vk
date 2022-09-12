package org.potala.main

import sttp.client3._
import org.json4s._
import org.json4s.native.JsonMethods._

import org.potala.const._
import sttp.model.Uri

class Groups()

case class Response(val response: ResponseMap)
case class ResponseMap(
    val key: String,
    val server: String,
    val ts: String)

object Groups {
    // TODO must return key, server and ts, all are String t
    // type, also create global constant for basic vk url

    def getLongPollServer(token: String, groupId: Int) = {
        val method = "groups.getLongPollServer"
        val args = Map("group_id" -> groupId)
        val requestURL = makeRequestURL(token, method, args)
        val responseJSON = HTTPget(requestURL)
        val parsedJSON = parse(responseJSON.body.merge)
        implicit val formats: Formats = DefaultFormats
        val data = parsedJSON.extract[Response]
        (data.response.key, data.response.server, data.response.ts)
    }

}
