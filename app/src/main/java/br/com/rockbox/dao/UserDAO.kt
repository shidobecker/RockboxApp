package br.com.rockbox.dao

import android.content.Context
import android.util.Log
import br.com.rockbox.model.User
import io.realm.Realm


class UserDAO (val user:User, val context: Context)  {
    val realm: Realm

init{
    Realm.init(context);
    realm = Realm.getDefaultInstance()
}
    //Function extended
    fun Realm.insertUser(){
        executeTransaction {
            realm.copyToRealm(user)
        }

    }

    fun Realm.returnUser(): User{
        val singleUser = where(User::class.java).equalTo("name", user.name).findFirst()
        return singleUser
    }




}