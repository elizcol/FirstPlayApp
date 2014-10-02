package controllers

import play.api._
import play.api.mvc._

object Contacts extends Controller {

	def index = Action {
		val names = List("Fred", "Harry", "Charles", "Anne") 
		Ok(views.html.view("This is my list <small>this is small</small>", names))
	}
	
	def another = Action { implicit request =>
		val initialValue = session.get("counter").map(_.toInt).getOrElse(0)
		
		Ok(views.html.another()).withSession(
				"counter" -> (initialValue + 1).toString
		)
	}
	
	def create = TODO
	
	def edit(id: Long) = TODO
	
	def update(id: Long) = TODO
	
	def delete(id: Long) = TODO
}