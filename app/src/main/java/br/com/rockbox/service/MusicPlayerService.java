package br.com.rockbox.service;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.rockbox.model.Song;
import br.com.rockbox.utils.GlobalConstants;

public class MusicPlayerService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener,
        MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnInfoListener, MediaPlayer.OnSeekCompleteListener {


    private MediaPlayer mediaPlayer;
    private final IBinder musicBinder = new MusicBinder();

    //Lista de Musicas do usuário
    private List<Song> songs;
    //Posicao atual
    private int songPosition;

    private Song currentSong;


    public MusicPlayerService() {
    }

    public class MusicBinder extends Binder {
        public MusicPlayerService getServerInstance() {
            //Retorna o proprio service.
            return MusicPlayerService.this;
        }
    }

    /**********************************Service Methods***************************************************************************/

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(GlobalConstants.MUSIC_SERVICE_TAG, "Service On Bind");
        return musicBinder;

    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(GlobalConstants.MUSIC_SERVICE_TAG, "Service Unbound");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    /********************************** Music Player Methods***************************************************************************/

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {

    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onSeekComplete(MediaPlayer mediaPlayer) {

    }

    /********************************** Songs Methods***************************************************************************/

    public void loadSongs(Context context){
        songs = new ArrayList<>();
        if(context == null){
            Log.i("Context is null", "Context is null");
        }
        ContentResolver cr =   context.getContentResolver();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        //Isso limita a pasta onde o MediaStore irá buscar as musicas utilizando atraves da Query do cursor.
        //Buscando em todo o Device
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        String sortOrder = MediaStore.Audio.Media.ARTIST + " ASC";
        Cursor cur = cr.query(uri, null, selection, null, sortOrder);
        int count = 0;

        String[] projection = new String[] {MediaStore.Audio.Albums._ID,
                MediaStore.Audio.Albums.ALBUM_ART,
                MediaStore.Audio.Albums.ALBUM,
                MediaStore.Audio.Albums.ARTIST};
        if(cur != null) {
            count = cur.getCount();
            Log.i("COUNT CURSOR", String.valueOf(count));
            if(count > 0 && cur !=null) {
                cur.moveToFirst();
                while(cur.moveToNext()) {
                    Song m = new Song();
                    String data = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.DATA));
                    m.setTitle(cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                    m.setArtist(cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                    m.setFinalTime(cur.getDouble(cur.getColumnIndex(MediaStore.Audio.Media.DURATION)));
                    m.setUri(data);
                    m.setAlbumID(cur.getLong(cur.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)));
                    songs.add(m);
                }

            }
        }

        GlobalConstants.globalSongs = songs;
        cur.close();
    }




}
