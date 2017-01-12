package br.com.rockbox.fragments;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import br.com.rockbox.R;
import br.com.rockbox.adapter.PlayerTabsAdapter;
import br.com.rockbox.model.Song;
import br.com.rockbox.service.MusicPlayerService;
import br.com.rockbox.utils.GlobalConstants;


public class NowPlayingFragment extends Fragment {


    private Song currentSong = new Song();
    private int currentSongPosition;
    private List<Song> globalSongs;

    public NowPlayingFragment() {
        //Pegando a currentSongPosition pelo bundle enviada pelo PageTabsAdapter
        if(getArguments()!=null) {
            currentSongPosition = getArguments().getInt(PlayerTabsAdapter.currentSongPositionName, -1);
            Log.i("Now Playing Fragment: ", String.valueOf(currentSongPosition));
        }
        //Pegando a musica a partir da position
        if(currentSongPosition != -1) {
            currentSong = GlobalConstants.globalSongs.get(currentSongPosition);
            Log.i("Now Playing Fragment: ", String.valueOf(currentSongPosition));

        }
    }


    @Override
    public void onStart() {
        if(currentSong!=null){
            //Toast.makeText(getActivity(), "Song: " + currentSong.getTitle(), Toast.LENGTH_SHORT).show();
        }
        super.onStart();
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
