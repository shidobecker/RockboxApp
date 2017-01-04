package br.com.rockbox.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class User(
 @PrimaryKey
 open var username: String = "",
 open var name: String? = "",
 open var bands: RealmList<Band>? = RealmList()

) : RealmObject()