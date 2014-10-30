import play.api.{GlobalSettings, Application}
import play.api.Play.current
import play.api.db.DB
import anorm._
import play.api.mvc.{Results, SimpleResult, RequestHeader}
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits._

object Global extends GlobalSettings {

	override def onStart(app: Application) = {
		DB.withConnection { implicit connection => 
			SQL("INSERT INTO contacts(name, email) VALUES('Fred', 'fred@stone.com')").execute()
			SQL("INSERT INTO contacts(name, email) VALUES('Wilma', 'wilma@stone.com')").execute()
			SQL("INSERT INTO contacts(name, email) VALUES('Pebbles', 'pebbles@stone.com')").execute()
		}
	}
	
	override def onHandlerNotFound(request: RequestHeader): Future[SimpleResult] = {
		Future(Results.NotFound(views.html.notFound()))
	}
}