package io.scalate.engine


sealed trait EngineMessage

case class LogInsertion(origin: String, service: String, activity: String) extends EngineMessage

case class LogResponse(status: Int) extends EngineMessage

