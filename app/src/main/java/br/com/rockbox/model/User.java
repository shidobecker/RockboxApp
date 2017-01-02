package br.com.rockbox.model;

import io.realm.RealmObject;

/**
 * Created by Teste2 on 02/01/2017.
 */

public class User extends RealmObject {


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
