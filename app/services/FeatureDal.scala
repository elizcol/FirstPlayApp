package services

import com.mongodb.casbah.commons.MongoDBObject
import model.FeatureMap

class FeatureDal {

	val conn = MongoFactory.getConnection
	val collection = MongoFactory.getCollection(conn,"Features")
	
	def getFeatures = {
		collection.find().toSet.map(FeatureMap.fromBson)
	}
		
	def getFeature(id: String) = {
		var q = MongoDBObject("_id" -> id)
		val result = collection.findOne(q)
		
		val feature = result.get
		
		feature
	}
}