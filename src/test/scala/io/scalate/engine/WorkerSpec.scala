import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import io.scalate._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class WorkerSpec(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("WorkerSpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "An engine.Worker actor" must {
    val worker = system.actorOf(Props[engine.Worker])

    "accept well-formed log messages and return 201" in {
      worker ! engine.LogInsertion("ALPHA", "BETA", "GAMMA")
      expectMsg(engine.LogResponse(201))
    }

    "reject ill-formed log messages and return 400" in {
      worker ! 201
      expectMsg(engine.LogResponse(400))
    }
  }
}