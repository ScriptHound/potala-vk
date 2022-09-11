package org.potala.main

import sttp.client3._
import sttp.model.StatusCode

import org.json4s._
import org.json4s.native.JsonMethods._

import java.lang.System.getenv

@main def hello: Unit =
  val token = getenv("VK_TOKEN")

def msg = "I was compiled by Scala 3. :)"
