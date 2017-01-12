package br.com.rockbox.adapter;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.util.Log;

import br.com.rockbox.fragments.NowPlayingFragment;
import br.com.rockbox.fragments.SongListFragment;
import br.com.rockbox.model.Song;

/**
 * Classe responsável por servir como adapter das tabs do player de música, novas tabs devem ser adicionadas aqui
 * e o metodo getItemCount alterado
 *
 */
public class PlayerTabsAdapter extends FragmentStatePagerAdapter {

    private NowPlayingFragment nowPlayingFragment;
    private SongListFragment songListFragment;

    private int currentSongPosition = -1;

    public static final String currentSongPositionName = "CurrentSongPositionName";

    public PlayerTabsAdapter(FragmentManager fm, int currentSong) {
        super(fm);
        nowPlayingFragment = null;
        songListFragment = null;
        this.currentSongPosition = currentSong;
        Log.i("Player Tabs Adapter: ",  String.valueOf(currentSongPosition));
    }

    //Fragments do Player de música
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                songListFragment = new SongListFragment();
                return songListFragment;
            case 1:
                nowPlayingFragment = new NowPlayingFragment();
                if(currentSongPosition != -1){
                    Bundle b = new Bundle();
                    //Colocando a currentSongPosition novamente no bundle para passar para o NowPlayingFragment
                    b.putInt(currentSongPositionName,currentSongPosition);
                }
                return nowPlayingFragment;

        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
