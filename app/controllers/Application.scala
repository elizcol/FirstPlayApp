package controllers

import play.api._
import play.api.mvc._
import services.{MongoFactory,FeatureDal}
import com.wordnik.swagger.annotations._
import javax.ws.rs.PathParam

@Api(value = "/coll", description = "Operations about mongo")
object Application extends Controller {

	val conn = MongoFactory.getConnection
	val featureDal = new FeatureDal

	def index() = Action {
		Ok("Cheers")
	}
	
	@ApiOperation(
		nickname = "getCollections",
		value = "Get the list of Collections from MongoDB",
		notes = "Returns the collections",
		response = classOf[model.Feature],
		responseContainer = "List",
		httpMethod = "GET")
	def coll = Action {
		val collections = MongoFactory.getAllCollections(conn)
		//Ok(views.html.view(contacts, Contact.form))
		Ok(views.html.coll(collections))
	}
	
	@ApiOperation(
		nickname = "getFeatures",
		value = "Find all features",
		notes = "Returns features in collection 'Features' from db",
		response = classOf[model.Feature],
		responseContainer = "List",
		httpMethod = "GET")
	def getFeatures = Action {
		val names = featureDal.getFeatures
		//Ok(views.html.view(contacts, Contact.form))
		Ok(views.html.features(names))
	}
	
	@ApiOperation(
		nickname = "getFeatureByName",
		value = "Get all feature by name",
		notes = "Return feature in the collection by name",
		response = classOf[model.Feature],
		httpMethod = "GET")
	def getFeature(@ApiParam(value = "Name of the feature to fetch")@PathParam("name") name: String) = Action {
		val feature = featureDal.getFeature(name)
		Ok(views.html.feature(feature))
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