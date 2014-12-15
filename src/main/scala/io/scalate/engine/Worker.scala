package io.scalate.engine

import akka.actor._

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.commons.ValidBSONType.ObjectId

class Worker extends Actor {
  //val uri = MongoClientURI("mongodb://scalate-worker:scalate-worker@ds063630.mongolab.com:63630/m-rouget-scalate")
  //val mongo = MongoClient(uri)
  val mongo = MongoClient()
  val db = mongo("scalate")
  val coll = db("test")

  def receive = {
    case LogInsertion(origin, service, activity) =>
      val id = new ObjectId()
      val obj = MongoDBObject("origin" -> origin, "service" -> service, "activity" -> activity, "_id" -> id)
      println("["+id+"]" + " -> " + obj)
      val ret = coll.insert(obj)
      sender ! LogInserted(id.toString())

    case _ =>
      sender ! LogInsertionError(400, "Unknown request")
  }
}
