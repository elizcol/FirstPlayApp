package controllers

import play.api._
import play.api.mvc._
import model.Contact

object Contacts extends Controller {

	def index = Action {
		val contacts = Contact.all
		Ok(views.html.view(contacts, Contact.form))
	}
	
	def another = Action { implicit request =>
		val initialValue = session.get("counter").map(_.toInt).getOrElse(0)
		
		Ok(views.html.another()).withSession(
				"counter" -> (initialValue + 1).toString
		)
	}
	
	def create = Action { implicit request =>
		Contact.form.bindFromRequest.fold(
			errors => BadRequest(views.html.view(Contact.all, errors)),
			contact => {
				Contact.create(contact)
				Redirect(routes.Contacts.index())
			}
		)
	}
	
	def edit(id: Long) = Action {
	    Contact.get(id).map { contact =>
	      Ok(views.html.edit(id, Contact.form.fill(contact)))
	    } getOrElse {
	      Redirect(routes.Contacts.index())
	    }
	}
	
	def update(id: Long) = Action { implicit request =>
		Contact.form.bindFromRequest.fold(
			errors => BadRequest(views.html.edit(id, errors)),
			contact => {
				Contact.update(id, contact)
				Redirect(routes.Contacts.index())
			}
		)
	}
	
	def delete(id: Long) = Action {
		Contact.delete(id)
		Redirect(routes.Contacts.index())
	}
}