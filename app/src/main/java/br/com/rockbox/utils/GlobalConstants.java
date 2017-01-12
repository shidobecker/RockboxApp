package br.com.rockbox.utils;

import android.net.Uri;

import java.util.List;

import br.com.rockbox.LoginActivity;
import br.com.rockbox.MainActivity;
import br.com.rockbox.SplashActivity;
import br.com.rockbox.fragments.BandListFragment;
import br.com.rockbox.fragments.CalendarFragment;
import br.com.rockbox.fragments.NowPlayingFragment;
import br.com.rockbox.fragments.SongListFragment;
import br.com.rockbox.model.Song;
import br.com.rockbox.service.MusicPlayerService;

public class GlobalConstants {

    public static final String PREFERENCES_TAG = "ROCKBOX_SHARED_PREFERENCES";
    public static final String FIRST_TIME = "FIRST_TIME";
    public static final String USERNAME = "USERNAME";

    //Activities Names
    public static final String MAIN_ACTIVITY_TAG = MainActivity.class.getSimpleName();
    public static final String LOGIN_ACTIVITY_TAG = LoginActivity.class.getSimpleName();
    public static final String SPLASH_ACTIVITY_TAG = SplashActivity.class.getSimpleName();
    //Fragment Names
    public static final String SONG_LIST_FRAGMENT_TAG = SongListFragment.class.getSimpleName();
    public static final String NOW_PLAYING_FRAGMENT_TAG = NowPlayingFragment.class.getSimpleName();
    public static final String CALENDAR_FRAGMENT_TAG = br.com.rockbox.fragments.CalendarFragment.class.getSimpleName();
    public static final String BAND_LIST_FRAGMENT_TAG = br.com.rockbox.fragments.BandListFragment.class.getSimpleName();

    //Services names
    public static final String MUSIC_SERVICE_TAG = MusicPlayerService.class.getSimpleName();


    //Fragments Constants
    public static final int CalendarFragment = 0;
    public static final int MainFragment = 1;
    public static final int PlayerMainFragment = 2;
    public static final int UserSongsFragment = 3;
    public static final int BandListFragment = 4;



    //Lista de Musica para não haja necessidade de sempre ser carregada.
    public static List<Song> globalSongs;

    //Artwork do Album
    final public static Uri sArtworkUri = Uri
            .parse("content://media/external/audio/albumart");




}
