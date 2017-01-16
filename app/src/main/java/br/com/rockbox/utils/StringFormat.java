package br.com.rockbox.utils;

/**
 * Created by Teste2 on 16/01/2017.
 */

public class StringFormat {

    private  static final int SUBSTRING_END = 17;

    public static String formatString(String value){
        if(value.length() >= 17) {
            return value.substring(0, SUBSTRING_END);
        }else{
            return value;
        }
    }

}
