package controllers

import play.api._
import play.api.mvc._
import model.Contact
import com.wordnik.swagger.annotations.Api
import com.wordnik.swagger.annotations.ApiOperation
import com.wordnik.swagger.annotations.ApiResponses
import com.wordnik.swagger.annotations.ApiResponse
import com.wordnik.swagger.annotations.ApiImplicitParams
import com.wordnik.swagger.annotations.ApiImplicitParam
import com.wordnik.swagger.annotations.ApiParam
import javax.ws.rs.PathParam

@Api(value = "/contact", description = "Operations about contacts")
object Contacts extends Controller {

	@ApiOperation(
		nickname = "getContacts",
		value = "Get the list of Contacts",
		notes = "Returns the contacts",
		response = classOf[model.Contact],
		responseContainer = "List",
		httpMethod = "GET") //	@ApiResponses(Array(
		//		new ApiResponse(code = 400, message = "Invalid ID supplied"),
		//		new ApiResponse(code = 404, message = "Pet not found")))
	def index = Action {
		val contacts = Contact.all
		Ok(views.html.view(contacts, Contact.form))
	}

	def another = Action { implicit request =>
		val initialValue = session.get("counter").map(_.toInt).getOrElse(0)

		Ok(views.html.another()).withSession(
			"counter" -> (initialValue + 1).toString)
	}

	@ApiOperation(
		nickname = "newContact",
		value = "Create a Contact",
		notes = "Creates a new contact",
		response = classOf[Void],
		httpMethod = "POST")
	@ApiImplicitParams(Array(
		new ApiImplicitParam(value = "Contact to add to db", required = true, dataType = "Contact", paramType = "form")))
	def create = Action { implicit request =>
		Contact.form.bindFromRequest.fold(
			errors => BadRequest(views.html.view(Contact.all, errors)),
			contact => {
				Contact.create(contact)
				Redirect(routes.Contacts.index())
			})
	}

	@ApiOperation(
		nickname = "getContactById",
		value = "Find contact by ID",
		notes = "Returns a Contact",
		response = classOf[model.Contact],
		httpMethod = "GET")
	def edit(@ApiParam(value = "ID of the contact to fetch")@PathParam("id") id: Long) = Action {
		Contact.get(id).map { contact =>
			Ok(views.html.edit(id, Contact.form.fill(contact)))
		} getOrElse {
			Redirect(routes.Contacts.index())
		}
	}

	@ApiOperation(nickname = "updateContact",
		value = "Update an existing Contact", response = classOf[Void], httpMethod = "POST")
	@ApiImplicitParams(Array(
		new ApiImplicitParam(value = "Contact to update in the db", required = true, dataType = "Contact", paramType = "form")))
	def update(@ApiParam(value = "ID of the contact to fetch")@PathParam("id")id: Long) = Action { implicit request =>
		Contact.form.bindFromRequest.fold(
			errors => BadRequest(views.html.edit(id, errors)),
			contact => {
				Contact.update(id, contact)
				Redirect(routes.Contacts.index())
			})
	}
	@ApiOperation(nickname = "deleteContact",
		value = "Delete an existing Contact", response = classOf[Void], httpMethod = "POST")
	@ApiResponses(Array(
		new ApiResponse(code = 303, message = "Contact deleted")))	
	@ApiImplicitParams(Array(
		new ApiImplicitParam(value = "Contact to delete in the db", required = true, dataType = "Contact", paramType = "form")))
	def delete(@ApiParam(value = "ID of the contact to fetch")@PathParam("id")id: Long) = Action {
		Contact.delete(id)
		Redirect(routes.Contacts.index())
	}
}