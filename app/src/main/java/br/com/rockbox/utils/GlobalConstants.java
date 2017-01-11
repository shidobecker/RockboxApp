package br.com.rockbox.utils;

import br.com.rockbox.LoginActivity;
import br.com.rockbox.MainActivity;
import br.com.rockbox.SplashActivity;

public class GlobalConstants {

    public static final String PREFERENCES_TAG = "ROCKBOX_SHARED_PREFERENCES";
    public static final String FIRST_TIME = "FIRST_TIME";
    public static final String USERNAME = "USERNAME";

    //Activities Names
    public static final String MAIN_ACTIVITY_TAG = MainActivity.class.getSimpleName();
    public static final String LOGIN_ACTIVITY_TAG = LoginActivity.class.getSimpleName();
    public static final String SPLASH_ACTIVITY_TAG = SplashActivity.class.getSimpleName();


    //Fragments Constants
    public static final int CalendarFragment = 0;
    public static final int MainFragment = 1;
    public static final int PlayerMainFragment = 2;
    public static final int UserSongsFragment = 3;
    public static final int BandListFragment = 4;



}
