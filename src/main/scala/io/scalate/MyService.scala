package io.scalate

import akka.actor.Actor
import org.json4s._
import spray.http.MediaTypes._
import spray.http._
import spray.httpx.Json4sSupport
import spray.routing._

object Json4sProtocol extends Json4sSupport {
  implicit def json4sFormats: Formats = DefaultFormats
}

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class MyServiceActor extends Actor with MyService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
}


// this trait defines our service behavior independently from the service actor
trait MyService extends HttpService {

  val myRoute =
    path("") {
      get {
        respondWithMediaType(`text/html`) {
          // XML is marshalled to `text/xml` by default, so we simply override here
          complete {
            <html>
              <body>
                <h1>Say hello to
                  <i>sca-late</i>
                  on
                  <i>spray-can</i>
                  !</h1>
              </body>
            </html>
          }
        }
      }
    } ~
      path("doc") {
        get {
          respondWithMediaType(`text/html`) {
            // XML is marshalled to `text/xml` by default, so we simply override here
            complete {
              <html>
                <body>
                  <h1>sca-LATE documentation</h1>
                  <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque metus nulla, commodo et ipsum sit amet, sagittis volutpat massa. Maecenas libero velit, interdum et nisi vel, rhoncus efficitur erat. Cras molestie sit amet metus eget rhoncus. Proin accumsan nulla purus, ut dapibus odio suscipit et. Nunc gravida malesuada felis id egestas. Donec auctor tristique cursus. In magna erat, cursus ac justo vel, hendrerit fringilla sapien. Donec ut erat maximus, cursus erat ornare, fringilla magna. Donec ac augue nec mauris commodo varius ac eu nunc. Aenean malesuada sapien diam, lobortis feugiat libero consequat a.</p>

                  <p>Pellentesque malesuada blandit arcu, at porttitor arcu viverra et. Maecenas aliquet enim tempus risus convallis, vel cursus dolor egestas. Phasellus elementum ligula sed sem aliquet rutrum. Donec id metus ac mi aliquam congue. Pellentesque a suscipit metus. Phasellus lacus neque, scelerisque vel lacus sit amet, mollis luctus orci. Proin congue, augue sit amet venenatis laoreet, massa purus malesuada risus, id sagittis urna tellus non magna.</p>

                  <p>Curabitur gravida ligula lobortis, congue tellus non, rutrum eros. Vivamus interdum elit purus, vitae aliquam purus malesuada vel. Vestibulum efficitur ligula a dui posuere sollicitudin. Aliquam erat volutpat. Nullam urna velit, cursus quis lacus ut, ultrices aliquet odio. Curabitur vitae sem orci. Pellentesque ac nisi eu neque ultricies gravida ut id lacus. Integer risus quam, pharetra eget massa eget, pulvinar dictum nisl. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Sed semper erat in erat aliquam posuere. Maecenas leo leo, tempor id dolor nec, rhoncus pharetra mauris. Mauris elementum elit risus, a ullamcorper tellus rhoncus eu. Donec at risus interdum, facilisis arcu eget, pulvinar arcu. Duis egestas ultricies erat, quis dignissim justo euismod at. Donec ornare, leo ac tincidunt malesuada, sapien lacus dapibus ante, id suscipit lectus urna in velit. Mauris maximus ornare velit, vel dictum neque.</p>

                  <p>Morbi nec feugiat mauris. Cras feugiat ante eu cursus dictum. Aliquam erat volutpat. Maecenas lobortis laoreet turpis, ac blandit nunc efficitur id. Curabitur facilisis eget ligula accumsan ultrices. Maecenas nec dolor ultricies, egestas arcu sed, posuere neque. Pellentesque sit amet libero sed elit dignissim rutrum. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nam vulputate tellus congue risus iaculis, eget malesuada felis suscipit. Aliquam congue rutrum sodales. Fusce nec augue ac quam dictum laoreet. Fusce interdum in orci non feugiat. In at ullamcorper ante. Quisque metus metus, egestas id sem non, porttitor porta sem. Donec eleifend laoreet ex quis pulvinar.</p>

                  <p>Aliquam ultricies, justo non maximus sagittis, dolor arcu fermentum justo, ac rhoncus est sem vel turpis. Maecenas ullamcorper dapibus nunc, et malesuada nunc gravida ac. Integer vitae orci ornare, efficitur felis ut, tempus est. Quisque ultrices eleifend tortor vel feugiat. Maecenas sed ex sit amet elit placerat feugiat sed ut ipsum. Donec ipsum ante, pharetra vel risus faucibus, finibus sollicitudin tortor. Phasellus tortor purus, pulvinar eu aliquet vel, ullamcorper eu sem. Donec at neque sagittis, viverra arcu ut, pulvinar augue.</p>
                </body>
              </html>
            }
          }
        }
      }
  /*~
   path("logs") {
     post {
       respondWithStatus(Created) {
         case class Foo(s: String) {

         }

         entity(as[Foo]) { someObject =>
           complete(s"OK")
         }
       }
     }
   }*/
}