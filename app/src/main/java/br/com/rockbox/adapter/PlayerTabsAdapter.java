package br.com.rockbox.adapter;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import br.com.rockbox.fragments.NowPlayingFragment;
import br.com.rockbox.fragments.SongListFragment;

/**
 * Classe responsável por servir como adapter das tabs do player de música, novas tabs devem ser adicionadas aqui
 * e o metodo getItemCount alterado
 *
 */
public class PlayerTabsAdapter extends FragmentStatePagerAdapter {

    private NowPlayingFragment nowPlayingFragment;
    private SongListFragment songListFragment;


    public PlayerTabsAdapter(FragmentManager fm) {
        super(fm);
        nowPlayingFragment = null;
        songListFragment = null;
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
                return nowPlayingFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
