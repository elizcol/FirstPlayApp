package model

import com.mongodb.casbah.commons.MongoDBObject

case class Feature(
	_id: String,
	keyword: String,
	name: String,
	project_id: String = "",
	group_id: Option[String],
	issue_tag: Option[String],
	uri: String = "test.feature",
	tags: Array[Tag],
	description: String,
	elements: Array[Element])

case class Tag(name: String)

case class Element(
	description: String = "",
	_type: String,
	keyword: String,
	name: String = "",
	tags: Option[Array[Tag]],
	steps: Option[Array[Step]],
	examples: Option[Array[Example]])

case class Step(
	keyword: String,
	name: String,
	rows: Option[Array[Row]])

case class Example(
	description: String = "",
	_type: String,
	keyword: String,
	name: String = "",
	rows: Array[Row])

case class Row(cells: Array[String])
