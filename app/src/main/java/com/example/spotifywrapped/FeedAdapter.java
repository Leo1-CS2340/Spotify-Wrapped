package com.example.spotifywrapped;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifywrapped.data_classes.Stat;
import com.example.spotifywrapped.data_classes.User;
import com.example.spotifywrapped.data_classes.Wrapped;

import java.util.ArrayList;
import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {
    private List<User> following;
    private List<Stat> stats;
    Context context;

    public FeedAdapter(List<User> following) {
        this.following = following;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post, parent, false);
        return new FeedAdapter.FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        User user = following.get(position);
        holder.username.setText(user.getUserId());
        Wrapped wrap = following.get(position).getWrap();

        List<Stat> artists = statify(wrap.getTopArtists());
        List<Stat> genre = statify(wrap.getTopGenre());
        List<Stat> songs = statify(wrap.getTopSongs());
        List<Stat> minutes = statify(wrap.getMinutesListened());

        UserStatAdapter artistAdapter = new UserStatAdapter(artists);
        UserStatAdapter genreAdapter = new UserStatAdapter(genre);
        UserStatAdapter songAdapter = new UserStatAdapter(songs);
        UserStatAdapter minutesAdapter = new UserStatAdapter(minutes);

        holder.topArtists.setAdapter(artistAdapter);
        holder.topArtists.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.topGenre.setAdapter(genreAdapter);
        holder.topGenre.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.topSongs.setAdapter(songAdapter);
        holder.topSongs.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.minutes.setAdapter(minutesAdapter);
        holder.minutes.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
    }

    private List<Stat> statify(List<String> list) {
        List<Stat> stat = new ArrayList<>();
        //int i = 0;
        for (String s : list) {
            stat.add(new Stat(s,s));
        }
        return stat;
    }

    @Override
    public int getItemCount() {
        return following.size();
    }


    static class FeedViewHolder extends RecyclerView.ViewHolder {
        ImageView pfp;
        TextView username;
        RecyclerView topArtists;
        RecyclerView topGenre;
        RecyclerView topSongs;
        RecyclerView minutes;
        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            pfp = itemView.findViewById(R.id.profile_picture);
            username = itemView.findViewById(R.id.username);
            topArtists = itemView.findViewById(R.id.rvTopArtists);
            topGenre = itemView.findViewById(R.id.rvTopGenre);
            topSongs = itemView.findViewById(R.id.rvTopSongs);
            minutes = itemView.findViewById(R.id.rvMinutesListened);
        }
    }
}
