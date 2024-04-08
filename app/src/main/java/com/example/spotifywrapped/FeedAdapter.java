package com.example.spotifywrapped;

import static java.security.AccessController.getContext;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifywrapped.data_classes.Post;
import com.example.spotifywrapped.data_classes.Stat;
import com.example.spotifywrapped.data_classes.User;
import com.example.spotifywrapped.data_classes.Wrapped;
import com.example.spotifywrapped.ui.wrapped.CommentFragment;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    private List<Post> feedPosts;
    private List<Stat> stats;

    private User masterUser;
    Context context;

    public FeedAdapter(List<Post> feedPosts, User masterUser,Context context) {
        this.feedPosts = feedPosts;
        this.masterUser = masterUser;
        this.context = context;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post, parent, false);
        return new FeedAdapter.FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        Post post = feedPosts.get(position);
        User user = feedPosts.get(position).getUser();
        holder.username.setText(user.getUserId());
        Wrapped wrap = user.getWrap();
        holder.likeButton.setText(String.valueOf(post.getLikeCount()));

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
        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (masterUser.getLikedPosts().contains(post)) {
                    post.removeLike(masterUser);
                } else {
                    post.addLike(masterUser, new Date());
                }
                notifyItemChanged(holder.getAdapterPosition());
            }
        });

        holder.openCommentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                CommentFragment commentFragment = new CommentFragment(post.getComments());
                View commentView = LayoutInflater.from(context).inflate(R.layout.comment_section, null);
                dialog.setContentView(commentView);
                dialog.show();

            }
        });

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
        return feedPosts.size();
    }


    static class FeedViewHolder extends RecyclerView.ViewHolder {
        ImageView pfp;
        TextView username;
        RecyclerView topArtists;
        RecyclerView topGenre;
        RecyclerView topSongs;
        RecyclerView minutes;

        Button likeButton;
        Button openCommentsButton;
        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            pfp = itemView.findViewById(R.id.profile_picture);
            username = itemView.findViewById(R.id.username);
            topArtists = itemView.findViewById(R.id.rvTopArtists);
            topGenre = itemView.findViewById(R.id.rvTopGenre);
            topSongs = itemView.findViewById(R.id.rvTopSongs);
            minutes = itemView.findViewById(R.id.rvMinutesListened);
            likeButton = itemView.findViewById(R.id.like_button);
            openCommentsButton = itemView.findViewById(R.id.comment_button);
        }
    }
}
