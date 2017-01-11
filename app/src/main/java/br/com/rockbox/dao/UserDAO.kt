package br.com.rockbox.dao

import android.content.Context
import android.util.Log
import br.com.rockbox.model.User
import com.google.gson.Gson
import io.realm.Realm


class UserDAO (var user:User, val context: Context)  {

init{
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
       // userCollection.insertOne(Document.parse(json))

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