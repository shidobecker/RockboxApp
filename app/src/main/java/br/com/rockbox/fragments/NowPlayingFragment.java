package br.com.rockbox.fragments;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.ContentUris;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import br.com.rockbox.R;
import br.com.rockbox.adapter.PlayerTabsAdapter;
import br.com.rockbox.model.Song;
import br.com.rockbox.service.MusicPlayerService;
import br.com.rockbox.utils.GlobalConstants;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class NowPlayingFragment extends Fragment {


    private Song currentSong = new Song();
    private int currentSongPosition;
    private List<Song> globalSongs;


    @BindView(R.id.songTitleNowPlaying)
    TextView songTitle;
    @BindView(R.id.songArtistNowPlaying)
    TextView songArtist;
    @BindView(R.id.albumCoverNowPlaying)
    CircleImageView albumCover;
    @BindView(R.id.btnPreviousNowPlaying)
    ImageButton btnPrevious;
    @BindView(R.id.btnPlayNowPlaying)
    ImageButton btnPlay;
    @BindView(R.id.btnNextNowPlaying)
    ImageButton btnNext;
    @BindView(R.id.seekBarNowPlaying)
    SeekBar seekBar;


    public NowPlayingFragment() {

    }


    @Override
    public void onStart() {
        super.onStart();
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle b = getArguments();
        int position = b.getInt(GlobalConstants.SONGPOSITION);
        currentSong = GlobalConstants.globalSongs.get(position);
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);

        ButterKnife.bind(this, view);
        Uri uri = ContentUris.withAppendedId(GlobalConstants.sArtworkUri,
                currentSong.getAlbumID());
        Picasso.with(container.getContext()).load(uri).placeholder(R.drawable.generic_album_cover)
                .into(albumCover);
        songTitle.setText(currentSong.getTitle());
        songArtist.setText(currentSong.getArtist());
        Animation a = AnimationUtils.loadAnimation(container.getContext(), R.anim.rotate_anim);
        a.setRepeatCount(Animation.INFINITE);
        albumCover.startAnimation(a);
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
