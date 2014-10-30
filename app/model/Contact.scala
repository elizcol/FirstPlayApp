package model

import anorm._
import play.api.db._
import play.api.Play.current
import play.api.data._
import play.api.data.Forms._

case class Contact(id: Long, name: String, email: String)

object Contact {
	
	def all = {
		DB.withConnection { implicit connection => 
			SQL("SELECT * FROM contacts")().map { row =>
				Contact(
						id = row[Long]("id"),
						name = row[String]("name"),
						email = row[String]("email")
				)
			}.toList
		}
	}
	
	def create(contact: Contact) {
		DB.withConnection { implicit connectino =>
			SQL("INSERT INTO contacts(name, email) VALUES({name}, {email})").on(
				"name" -> contact.name,
				"email" -> contact.email
				).execute()
		}
	}
	
	def get(id: Long) = {
	    DB.withConnection { implicit connection =>
	      SQL("SELECT * FROM contacts WHERE id={id}").on("id" -> id)().headOption.map { row =>
	        Contact(
	          id = row[Long]("id"),
	          name = row[String]("name"),
	          email = row[String]("email")
	        )
	      }
	    }
	 }
	
	def update(id: Long, contact: Contact) {
		DB.withConnection { implicit connection => 
			SQL("UPDATE contacts SET name={name}, email={email} WHERE id={id}").on(
			"id" -> id,
			"name" -> contact.name,
			"email" -> contact.email
			).execute()
		}
	}
	
	def delete(id: Long) {
		DB.withConnection { implicit connection =>
			SQL("DELETE FROM contacts WHERE id={id}").on("id" -> id).execute()
		}
	}
	
	val form = Form(
		mapping(
			"id" -> ignored(0L),
			"name" -> nonEmptyText,
			"email" -> email
		) (Contact.apply)(Contact.unapply)
	)
	
}