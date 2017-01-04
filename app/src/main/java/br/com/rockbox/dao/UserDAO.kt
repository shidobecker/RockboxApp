package br.com.rockbox.dao

import android.content.Context
import android.util.Log
import br.com.rockbox.model.User
import io.realm.Realm


class UserDAO (var user:User, val context: Context)  {



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


   /* fun generateUserID(realm:Realm): Int{
        var lastId:Int = 0
        try{
            lastId = realm.where(User::class.java).findAllSorted("id").last().id!!
        }catch(e:IndexOutOfBoundsException){
        }
        return lastId++
    }*/


}