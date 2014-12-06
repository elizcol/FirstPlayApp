package services

import com.mongodb.casbah.MongoCollection
import com.mongodb.casbah.MongoClient

object MongoFactory {

  private val SERVER = "localhost"
  private val PORT = 27017
  private val DATABASE = "DuplicateATF"
  private val COLLECTION = "Features"

  def getConnection: MongoClient = return MongoClient(SERVER, PORT)
  def getAllCollections(conn: MongoClient): Set[String] = return conn(DATABASE).collectionNames.toSet
  def getCollection(conn: MongoClient, collection: String = COLLECTION): MongoCollection = return conn(DATABASE)(collection)
  def closeConnection(conn: MongoClient) { conn.close }

}