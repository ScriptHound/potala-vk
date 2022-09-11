package org.potala

import sttp.client3._
import sttp.model.Uri
import org.json4s._
import org.json4s.native.JsonMethods._

package object const {
    val VK_URL = "https://api.vk.com/method/"

    def HTTPget(uri: Uri) = {
        val request = basicRequest.get(uri)
        val backend = HttpClientSyncBackend()
        request.send(backend)
    }
}
