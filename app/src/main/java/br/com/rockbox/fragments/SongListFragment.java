package br.com.rockbox.fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.rockbox.MainActivity;
import br.com.rockbox.R;
import br.com.rockbox.adapter.PlayerTabsAdapter;
import br.com.rockbox.adapter.SongsAdapter;
import br.com.rockbox.adapter.SongsRecyclerViewClickListener;
import br.com.rockbox.model.Song;
import br.com.rockbox.utils.GlobalConstants;
import br.com.rockbox.utils.OverlapDecoration;
import br.com.rockbox.utils.StringFormat;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongListFragment extends Fragment implements SongsRecyclerViewClickListener {

    @BindView(R.id.rvSongList)
    RecyclerView rvListSongs;

    private List<Song> songs;

    private SongsAdapter adapter;

    private RecyclerView.LayoutManager layoutManager;

    private Toolbar playerToolbar;

    public SongListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_song_list, container, false);
        ButterKnife.bind(this, v);
        layoutManager = new LinearLayoutManager(container.getContext());
        rvListSongs.setLayoutManager(layoutManager);
        adapter = new SongsAdapter(container.getContext(),songs, this);
        rvListSongs.setAdapter(adapter);
        playerToolbar = ((MainActivity) getActivity()).getPlayerToolbar();
        rvListSongs.addItemDecoration(new OverlapDecoration());

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        songs = new ArrayList<>();
        songs = GlobalConstants.globalSongs;
        super.onCreate(savedInstanceState);
    }


    //Transformar a Toolbar num singleton para ser acessada de todos os Fragments e Services.
    @Override
    public void recyclerViewListClicked(View v, final int position) {
        adapter =  new SongsAdapter(getActivity(), songs, this);
        //Pegando a musica que foi clicada a partir da posição do this.getLayoutPosition() do SongsAdapter
        Song selectedSong  = songs.get(position);
        GlobalConstants.currentSongPosition = position;
        GlobalConstants.currentSong = songs.get(position);
        ((MainActivity)getActivity()).configurePlayerToolbar(View.VISIBLE);
        //Manda a musica para o FragmentNowPlaying
        /*PlayerMainFragment playerMainFragment = new PlayerMainFragment();
        Bundle b = new Bundle();
        b.putInt(PlayerTabsAdapter.currentSongPositionName, position);

        //Colocando a position no bundle e enviando para o fragment
        playerMainFragment.setArguments(b);

        NowPlayingFragment nowPlayingFragment = new NowPlayingFragment();
        nowPlayingFragment.setArguments(b);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.mainFrameLayout, nowPlayingFragment).commit();
       // TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tabLayout);
        //tabLayout.getTabAt(2).select(); */

    }



    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
