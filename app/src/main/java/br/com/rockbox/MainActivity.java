package br.com.rockbox;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.transition.Fade;
import android.transition.Visibility;
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
import br.com.rockbox.fragments.SongListFragment;
import br.com.rockbox.model.User;
import br.com.rockbox.service.MusicPlayerService;
import br.com.rockbox.utils.GlobalConstants;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

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


    private MusicPlayerService musicPlayerService;
    private ServiceConnection serviceConnection;
    private boolean musicBound = false;
    private MediaPlayer player ;
    //Handler para atualizar o tempo de musica.
    private Handler myHandler = new Handler();;


    @BindView(R.id.playerToolbar)
    Toolbar playerToolbar;



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
        replaceMainFragment(GlobalConstants.MainFragment);

        appBarApplicationName.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                replaceMainFragment(GlobalConstants.MainFragment);
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

    private void createServiceConnection(){
        musicPlayerService = new MusicPlayerService();
        //Conexão com o service para poder acessar os metodos do mesmo
         serviceConnection = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder service) {
                Toast.makeText(MainActivity.this, "Service is Connected", Toast.LENGTH_SHORT).show();
                musicBound = true;
                MusicPlayerService.MusicBinder mLocalBinder = (MusicPlayerService.MusicBinder)service;
                musicPlayerService = mLocalBinder.getServerInstance();
            }

            public void onServiceDisconnected(ComponentName name) {
                musicBound = false;
                musicPlayerService = null;
            }

        };

    }


    public Toolbar getPlayerToolbar() {
        return playerToolbar;
    }

    public void setPlayerToolbar(Toolbar playerToolbar) {
        this.playerToolbar = playerToolbar;
    }

    private void setUpToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        playerToolbar.setVisibility(View.GONE);
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


    private void replaceMainFragment(int FragmentType){
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (FragmentType){
            case GlobalConstants.CalendarFragment:
                fragmentTransaction.setCustomAnimations(R.animator.fade_in, R.animator.fade_out, R.animator.fade_out, R.animator.fade_in );
                fragmentTransaction.replace(R.id.mainFrameLayout, new CalendarFragment());
                break;

            case GlobalConstants.MainFragment:
                fragmentTransaction.setCustomAnimations(R.animator.fade_in, R.animator.fade_out, R.animator.fade_out, R.animator.fade_in );
                fragmentTransaction.replace(R.id.mainFrameLayout, new MainFragment());
                break;

            case GlobalConstants.PlayerMainFragment:
                fragmentTransaction.setCustomAnimations(R.animator.fade_in, R.animator.fade_out, R.animator.fade_out, R.animator.fade_in );
                fragmentTransaction.replace(R.id.mainFrameLayout, new SongListFragment());
                createServiceConnection();
                Intent mIntent = new Intent(this, MusicPlayerService.class);
                bindService(mIntent, serviceConnection, BIND_AUTO_CREATE);
                //Carrega as musicas.
                if(GlobalConstants.globalSongs == null) {
                    musicPlayerService.loadSongs(this);
                }
                break;

            case GlobalConstants.BandListFragment:
                fragmentTransaction.setCustomAnimations(R.animator.fade_in, R.animator.fade_out, R.animator.fade_out, R.animator.fade_in );
                fragmentTransaction.replace(R.id.mainFrameLayout, new BandListFragment());
                break;
        }
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


    public void setMusicPlayerService(MusicPlayerService musicPlayerService) {
        this.musicPlayerService = musicPlayerService;
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
            replaceMainFragment(GlobalConstants.CalendarFragment);

        }else if (id ==R.id.nav_bands){
           replaceMainFragment(GlobalConstants.BandListFragment);

        } else if (id == R.id.nav_player) {
            replaceMainFragment(GlobalConstants.PlayerMainFragment);
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



    public Handler getMyHandler() {
        return myHandler;
    }

    public void setMyHandler(Handler myHandler) {
        this.myHandler = myHandler;
    }

    public MediaPlayer getPlayer() {
        return player;
    }

    public void setPlayer(MediaPlayer player) {
        this.player = player;
    }

    public boolean isMusicBound() {
        return musicBound;
    }

    public void setMusicBound(boolean musicBound) {
        this.musicBound = musicBound;
    }

    public ServiceConnection getServiceConnection() {
        return serviceConnection;
    }

    public void setServiceConnection(ServiceConnection serviceConnection) {
        this.serviceConnection = serviceConnection;
    }

    public MusicPlayerService getMusicPlayerService() {
        return musicPlayerService;
    }
    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }
}
