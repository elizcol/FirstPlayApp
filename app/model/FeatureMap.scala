package model

import com.mongodb.casbah.Imports._
import com.mongodb.DBObject
import com.mongodb.casbah.commons.{ MongoDBObject, MongoDBList }
import org.bson.types.ObjectId

object FeatureMap {

	def fromBson(o: DBObject): Feature = {
		Feature(
			_id = o.as[String]("_id"),
			keyword = o.as[String]("keyword"),
			name = o.as[String]("name"),
			project_id = o.as[String]("project_id"),
			group_id = o.getAs[String]("group_id"),
			issue_tag = o.getAs[String]("issue_tag"),
			uri = o.as[String]("uri"),
			tags = o.as[MongoDBList]("tags").toArray.map(doc => TagMap.fromBson(doc.asInstanceOf[DBObject])),
			description = o.as[String]("description"),
			elements = o.as[MongoDBList]("elements").toArray.map(doc => ElementMap.fromBson(doc.asInstanceOf[DBObject])))
	}

	def toBson(feature: Feature): DBObject = {
		MongoDBObject(
			"_id" -> feature._id,
			"keyword" -> feature.keyword,
			"name" -> feature.name,
			"project_id" -> feature.project_id,
			"group_id" -> feature.group_id,
			"issue_tag" -> feature.issue_tag,
			"uri" -> feature.uri,
			"tags" -> feature.tags.map(TagMap.toBson),
			"description" -> feature.description,
			"elements" -> feature.elements.map(ElementMap.toBson))
	}

}

object TagMap {

	def fromBson(o: DBObject): Tag = {
		Tag(
			name = o.as[String]("name"))
	}

	def toBson(tag: Tag): MongoDBList = {
		MongoDBList(
			"name" -> tag.name)
	}
}

object ElementMap {
	def fromBson(o: DBObject): Element = {
		Element(
			description = o.as[String]("description"),
			_type = o.as[String]("type"),
			keyword = o.as[String]("keyword"),
			name = o.as[String]("name"),
			// tags = Some(o.getAs[MongoDBList]("tags").toArray.map(doc => TagMap.fromBson(doc.asInstanceOf[DBObject]))),
			tags = o.getAs[MongoDBList]("tags") match {
				case Some(someStr) => Some(someStr.toArray.map(doc => TagMap.fromBson(doc.asInstanceOf[DBObject])))
				case None => None
			},
			// steps = o.as[MongoDBList]("steps").toArray.map(doc => StepMap.fromBson(doc.asInstanceOf[DBObject])),
			steps = o.getAs[MongoDBList]("steps") match {
				case Some(someStr) => Some(someStr.toArray.map(doc => StepMap.fromBson(doc.asInstanceOf[DBObject])))
				case None => None
			},
			// examples = Some(o.getAs[MongoDBList]("examples").toArray.map(doc => ExampleMap.fromBson(doc.asInstanceOf[DBObject])))
			examples = o.getAs[MongoDBList]("examples") match {
				case Some(someStr) => Some(someStr.toArray.map(doc => ExampleMap.fromBson(doc.asInstanceOf[DBObject])))
				case None => None
			})
	}

	def toBson(element: Element): MongoDBList = {
		MongoDBList(
			"description" -> element.description,
			"_type" -> element._type,
			"keyword" -> element.keyword,
			"name" -> element.name,
			"tags" -> element.tags.get.map(TagMap.toBson),
			"steps" -> element.steps.get.map(StepMap.toBson),
			"examples" -> element.examples.get.map(ExampleMap.toBson)
			)
	}
}

object StepMap {

	def fromBson(o: DBObject): Step = {
		Step(
			keyword = o.as[String]("keyword"),
			name = o.as[String]("name"),
			//		rows = Some(o.getAs[MongoDBList]("rows").toArray.map(doc => RowMap.fromBson(doc.asInstanceOf[DBObject])))
			rows = o.getAs[MongoDBList]("rows") match {
				case Some(someStr) => Some(someStr.toArray.map(doc => RowMap.fromBson(doc.asInstanceOf[DBObject])))
				case None => None
			})
	}

	def toBson(step: Step): MongoDBList = {
		MongoDBList(
			"keyword" -> step.keyword,
			"name" -> step.name,
			"rows" -> step.rows.get.map(RowMap.toBson))
	}
}

object ExampleMap {

	def fromBson(o: DBObject): Example = {
		Example(
			description = o.as[String]("description"),
			_type = o.as[String]("type"),
			keyword = o.as[String]("keyword"),
			name = o.as[String]("name"),
			rows = o.as[MongoDBList]("rows").toArray.map(doc => RowMap.fromBson(doc.asInstanceOf[DBObject])))
	}

	def toBson(example: Example): MongoDBList = {
		MongoDBList(
			"description" -> example.description,
			"type" -> example._type,
			"keyword" -> example.keyword,
			"name" -> example.name,
			"rows" -> example.rows.map(RowMap.toBson))

	}
}

object RowMap {

	def fromBson(o: DBObject): Row = {
		Row(
			cells = o.as[MongoDBList]("cells").toArray.map(doc => doc.asInstanceOf[String]))
	}

	def toBson(row: Row): MongoDBList = {
		MongoDBList(
			"cells" -> row.cells //toarray
			)
	}
}