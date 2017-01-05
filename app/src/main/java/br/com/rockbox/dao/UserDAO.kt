package br.com.rockbox.dao

import android.content.Context
import android.util.Log
import br.com.rockbox.connections.MongoConnection
import br.com.rockbox.model.User
import com.google.gson.Gson
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import io.realm.Realm
import org.bson.Document


class UserDAO (var user:User, val context: Context)  {
            var mongoDatabase: MongoDatabase
            var userCollection: MongoCollection<Document>

init{
    mongoDatabase = MongoConnection.getMongoConnection()
    userCollection = mongoDatabase.getCollection("users")
}

    //Extension Functions
   fun insertUser(realm:Realm){
        realm.executeTransaction {
            realm.copyToRealm(user)
        }

    }

    fun returnUser(realm: Realm): User{
        val singleUser = realm.where(User::class.java).equalTo("username", user.username).findFirst()
        return singleUser
    }

    fun insertUserMongoDB(){
        var gson: Gson = Gson()
        val json: String = gson.toJson(user)
        userCollection.insertOne(Document.parse(json))

    }

    fun returnUserSettingsFromMongo():User{
        var gson: Gson = Gson()
        val json: String = gson.toJson(user)
        val document: Document  = userCollection.find(Document.parse(json)).first()
        var u: User = gson.fromJson(document.toJson(), User::class.java)

        return u

    }




   /* fun generateUserID(realm:Realm): Int{
        var lastId:Int = 0
        try{
            lastId = realm.where(User::class.java).findAllSorted("id").last().id!!
        }catch(e:IndexOutOfBoundsException){
        }
        return lastId++
    }*/


}