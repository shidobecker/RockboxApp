package br.com.rockbox.connections;


import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;

public class MongoConnection {
    private static MongoDatabase database;


    public synchronized static MongoDatabase getMongoConnection(){
        if(database !=null){
            return database;
        }

        MongoCredential credential = MongoCredential.createCredential(
                "rockbox", "rockbox",
                "rockbox".toCharArray());
        MongoClient c = new MongoClient(new ServerAddress("ds035673.mlab.com:35673"), Arrays.asList(credential));
        database = c.getDatabase("rockbox");
        return database;


    }



}
