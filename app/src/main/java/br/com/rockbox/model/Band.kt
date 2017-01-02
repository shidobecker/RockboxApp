package br.com.rockbox.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Teste2 on 02/01/2017.
 */
open class Band(@PrimaryKey
                open var id:Long = 0,
                open var name:String = ""
) : RealmObject ()

