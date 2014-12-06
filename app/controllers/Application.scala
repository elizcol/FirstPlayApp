package controllers

import play.api._
import play.api.mvc._
import services.{MongoFactory,FeatureDal}

object Application extends Controller {

	val conn = MongoFactory.getConnection
	val featureDal = new FeatureDal

	def index() = Action {
		Ok("Cheers")
	}
	
	def coll = Action {
		val collections = MongoFactory.getAllCollections(conn)
		//Ok(views.html.view(contacts, Contact.form))
		Ok(views.html.coll(collections))
	}
	
	def getColl(name: String) = Action {
		val names = featureDal.getFeatures
		//Ok(views.html.view(contacts, Contact.form))
		Ok(views.html.features(names))
	}

	def token(id: Long) = Action {
		Ok(s"This is a token based route: $id")
	}

	def regex(id: Long) = Action {
		Ok(s"This is a regex based route: $id")
	}

	def clob(path: String) = Action {
		Ok(s"This is a clob based route: $path")
	}
}