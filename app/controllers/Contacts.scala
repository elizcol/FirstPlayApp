package controllers

import play.api._
import play.api.mvc._

object Contacts extends Controller {

	def index = Action {
		Ok("static")
	}
	
	def create = TODO
	
	def edit(id: Long) = TODO
	
	def update(id: Long) = TODO
	
	def delete(id: Long) = TODO
}