package io.scalate.engine

import akka.actor._

/*import akka.routing.RoundRobinRouter
import akka.util.Duration
import akka.util.duration._*/

class Worker extends Actor {
  def receive = {
    case LogInsertion(origin, service, activity) =>
      println("[" + sender.hashCode() + "]" + origin + "/" + service + "/" + activity)
      sender ! LogResponse(201)
    case _ =>
      sender ! LogResponse(400)
  }
}