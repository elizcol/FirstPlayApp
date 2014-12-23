package model

import com.mongodb.casbah.commons.MongoDBObject
import com.wordnik.swagger.annotations.ApiModel
import scala.annotation.meta.field
import com.wordnik.swagger.annotations.ApiModelProperty

@ApiModel("Feature")
case class Feature(
	_id: String,
	keyword: String,
	name: String,
	project_id: String = "",
	group_id: Option[String],
	@(ApiModelProperty @field)(value = "The Jira story issue tag") issue_tag: Option[String],
	uri: String = "test.feature",
	tags: Array[Tag],
	description: String,
	elements: Array[Element])

@ApiModel("Tag")
case class Tag(name: String)

@ApiModel("Element")
case class Element(
	description: String = "",
	_type: String,
	keyword: String,
	name: String = "",
	tags: Option[Array[Tag]],
	steps: Option[Array[Step]],
	examples: Option[Array[Example]])

@ApiModel("Step")
case class Step(
	keyword: String,
	name: String,
	rows: Option[Array[Row]])

@ApiModel("Example")
case class Example(
	description: String = "",
	_type: String,
	keyword: String,
	name: String = "",
	rows: Array[Row])

@ApiModel("Row")
case class Row(cells: Array[String])
