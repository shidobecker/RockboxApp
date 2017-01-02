package br.com.rockbox.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class User(
 @PrimaryKey
 open var id: Int = 0,
 open var name: String = "",
 open var bands: RealmList<Band>? = RealmList()

) : RealmObject()