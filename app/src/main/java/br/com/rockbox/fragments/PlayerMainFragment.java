package br.com.rockbox.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.rockbox.R;
import br.com.rockbox.adapter.PlayerTabsAdapter;
import br.com.rockbox.utils.GlobalConstants;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Esse Fragment servirá como se fosse uma activity apenas para segurar o tablayout para o SongListFragment e o NowPlaying Fragment
 */
public class PlayerMainFragment extends Fragment {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;


    private int currentSongPosition;

    public PlayerMainFragment() {
        //Pegando a musica e colocando no position
        if(getArguments()!=null) {
            currentSongPosition = getArguments().getInt(PlayerTabsAdapter.currentSongPositionName, -1);
            Log.i("PlayerMainFragment: ", String.valueOf(currentSongPosition));
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_main, container, false);
        ButterKnife.bind(this, view);
        setupTabLayout();
        return view;
    }


    private void setupTabLayout(){
        tabLayout.addTab(tabLayout.newTab().setText(R.string.player_tab_songlist));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.player_tab_nowplaying));

        //Enviando a currentSongPosition para o PlayerTabsAdapter
        PlayerTabsAdapter playerTabsAdapter = new PlayerTabsAdapter(getFragmentManager(), currentSongPosition);
        viewPager.setAdapter(playerTabsAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        if(currentSongPosition > -1){
            tabLayout.getTabAt(1).select();
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }



}
