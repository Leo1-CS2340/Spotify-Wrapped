package com.example.spotifywrapped;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifywrapped.data_classes.User;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {
    private List<User> following;
    Context context;

    public FeedAdapter(List<User> following, Context context) {
        this.following = following;
        this.context = context;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.post, parent, false);
        return new FeedAdapter.FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        User user = following.get(position);
        holder.username.setText(user.getUserId());
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    static class FeedViewHolder extends RecyclerView.ViewHolder {
        ImageView pfp;
        TextView username;
        RecyclerView post;
        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            pfp = itemView.findViewById(R.id.profile_picture);
            username = itemView.findViewById(R.id.username);
            post = itemView.findViewById(R.id.post_fragment);
        }
    }
}
