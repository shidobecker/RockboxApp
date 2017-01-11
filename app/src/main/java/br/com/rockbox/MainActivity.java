package br.com.rockbox;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import br.com.rockbox.dao.UserDAO;
import br.com.rockbox.fragments.BandListFragment;
import br.com.rockbox.fragments.CalendarFragment;
import br.com.rockbox.fragments.MainFragment;
import br.com.rockbox.fragments.NowPlayingFragment;
import br.com.rockbox.fragments.PlayerMainFragment;
import br.com.rockbox.model.User;
import br.com.rockbox.utils.GlobalConstants;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    TextView nav_header_username;

    @BindView(R.id.appBarApplicationName)
    TextView appBarApplicationName;

    private Realm realm;
    private Context context;

    private User loggedUser;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
       // setUpEnteringAnimation();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        returnUserSettings();
        setUpToolbar();
        setUpNavigationDrawer();

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFrameLayout, new MainFragment());
        fragmentTransaction.commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Action 1", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        appBarApplicationName.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainFrameLayout, new MainFragment());
                fragmentTransaction.commit();
            }
        });


    }





    private void setUpEnteringAnimation(){
        Fade fadeAnimation = new Fade();
        fadeAnimation.setDuration(2000);
        fadeAnimation.setMode(Visibility.MODE_IN);
        getWindow().setEnterTransition(fadeAnimation);
        getWindow().setReenterTransition(fadeAnimation);
        getWindow().setExitTransition(fadeAnimation);
        getWindow().setAllowEnterTransitionOverlap(false);

    }




    private void setUpToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
    }

    private void setUpNavigationDrawer(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View hView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        nav_header_username = (TextView) hView.findViewById(R.id.nav_header_username);
        nav_header_username.setText(loggedUser.getUsername());
    }


    private void  returnUserSettings(){
        SharedPreferences sharedPreferences = getSharedPreferences(GlobalConstants.PREFERENCES_TAG, android.content.Context.MODE_PRIVATE);
        String usernameShared = sharedPreferences.getString(GlobalConstants.USERNAME,null);
        Realm.init(MainActivity.this);
        realm = Realm.getDefaultInstance();
        UserDAO dao = new UserDAO(new User(usernameShared, null, null), MainActivity.this);
        loggedUser = dao.returnUser(realm);


    }

    private void  returnUserSettingsFromMongo(){
        SharedPreferences sharedPreferences = getSharedPreferences(GlobalConstants.PREFERENCES_TAG, android.content.Context.MODE_PRIVATE);
        String usernameShared = sharedPreferences.getString(GlobalConstants.USERNAME,null);
        UserDAO dao = new UserDAO(new User(usernameShared, null, null), MainActivity.this);
        //loggedUser = dao.returnUserSettingsFromMongo();


    }


    private void replaceMainFragment(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFrameLayout, new MainFragment());
        fragmentTransaction.commit();
    }


    @Override
    public boolean onSupportNavigateUp() {
        finishAfterTransition();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Teste", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_calendar) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mainFrameLayout, new CalendarFragment());
            fragmentTransaction.commit();

        }else if (id ==R.id.nav_bands){
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.animator.fade_in, R.animator.fade_out, R.animator.fade_out, R.animator.fade_in );
            fragmentTransaction.replace(R.id.mainFrameLayout, new BandListFragment());
            fragmentTransaction.commit();

        } else if (id == R.id.nav_player) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.animator.fade_in, R.animator.fade_out, R.animator.fade_out, R.animator.fade_in );
            fragmentTransaction.replace(R.id.mainFrameLayout, new PlayerMainFragment());
            fragmentTransaction.commit();

        } else if (id == R.id.nav_lyrics) {

        } else if (id == R.id.nav_youtube) {

        } else if (id == R.id.nav_spotify) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
