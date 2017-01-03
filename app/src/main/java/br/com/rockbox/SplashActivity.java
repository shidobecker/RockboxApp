package br.com.rockbox;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.view.Window;

import br.com.rockbox.utils.GlobalConstants;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent;
        //Verificar se usuário já está logado/baixar algumas poucas coisas, etc
        if(checkUserFirstTime()){
            intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.animator.fade_in, R.animator.fade_out);
        }else {
            intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.animator.fade_in, R.animator.fade_out);
        }

         finish();


    }



    private boolean checkUserFirstTime(){
        boolean isUserFirstTime = true;
        SharedPreferences sharedPreferences = getSharedPreferences(GlobalConstants.PREFERENCES_TAG, Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean(GlobalConstants.FIRST_TIME, true)){
            //Primeira Vez
            Log.i(GlobalConstants.SPLASH_ACTIVITY_TAG, "FIRST TIME");
        }else{
            isUserFirstTime = false;
        }
        return isUserFirstTime;
    }




}
