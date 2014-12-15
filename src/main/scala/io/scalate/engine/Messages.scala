package io.scalate.engine


sealed trait EngineMessage

case class LogInsertion(origin: String, service: String, activity: String) extends EngineMessage

case class LogInserted(id: String) extends EngineMessage
case class LogInsertionError(status: Int, reason: String) extends EngineMessage


