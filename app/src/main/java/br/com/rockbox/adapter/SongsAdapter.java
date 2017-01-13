package br.com.rockbox.adapter;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.TimeUnit;

import br.com.rockbox.R;
import br.com.rockbox.model.Song;
import br.com.rockbox.utils.GlobalConstants;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Shido on 09/05/2016.
 */
public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongsViewHolder> {

    private static SongsRecyclerViewClickListener itemListener;

    private Context context;
    private List<Song> songs;

    public SongsAdapter(Context context, List<Song> songs, SongsRecyclerViewClickListener itemListener) {
        this.context = context;
        this.songs = songs;
        this.itemListener = itemListener;


    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }


    @Override
    public void onBindViewHolder(SongsAdapter.SongsViewHolder holder, int position) {
        holder.songNameRow.setText(songs.get(position).getTitle());
        holder.artistRow.setText(songs.get(position).getArtist());
        //Aqui deve ser feita uma formatação pelo tamanho da música

        //albumArtwork
        Uri uri = ContentUris.withAppendedId(GlobalConstants.sArtworkUri,
                songs.get(position).getAlbumID());
        //Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").placeholder(R.drawable.ic_play).into(holder.imageRow);
        Picasso.with(context).load(uri).placeholder(R.drawable.generic_album_cover).into(holder.imageRow);


    }


    @Override
    public SongsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.user_song_row, parent, false);
        return new SongsViewHolder(v);
    }


    public String convertMusicDuration(double finalTime){
        String duration = String.format("%d:%d ",
                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                toMinutes((long) finalTime)));
        return duration;
    }


    static class SongsViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        @BindView(R.id.artistRow)
        TextView artistRow;

        @BindView(R.id.songNameRow)
        TextView songNameRow;

        @BindView(R.id.songImageRow)
        ImageView imageRow;

        public SongsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getLayoutPosition());
            Toast.makeText(v.getContext(), "Position Clicked: "+ this.getLayoutPosition(), Toast.LENGTH_SHORT).show();
        }
    }



}
