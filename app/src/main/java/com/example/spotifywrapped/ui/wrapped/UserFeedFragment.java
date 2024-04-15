package com.example.spotifywrapped.ui.wrapped;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifywrapped.FeedAdapter;
import com.example.spotifywrapped.R;
import com.example.spotifywrapped.data_classes.MasterUser;
import com.example.spotifywrapped.data_classes.Post;
import com.example.spotifywrapped.data_classes.Stat;
import com.example.spotifywrapped.data_classes.User;
import com.example.spotifywrapped.data_classes.Wrapped;
import com.example.spotifywrapped.ui.home.HomeFragment;
import com.example.spotifywrapped.ui.wrapped.UserStatsFragment;
import com.example.spotifywrapped.viewmodel.viewmodel;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class UserFeedFragment extends Fragment {

    private final viewmodel vm = viewmodel.getInstance();
    List<User> following;
    List<Post> feedPosts = new ArrayList<>();
    RecyclerView feed;
    FeedAdapter adapter;
    ArrayList<String> topArtists;
    ArrayList<String>topSongs;
    ArrayList<String> topGenre;
    ArrayList<String> minutes;
    Wrapped wrap;
    User user1;
    User user2;
    User user3;
    User masterUser;

    private void setDummyData() {
        topArtists = new ArrayList<>();
        topSongs = new ArrayList<>();
        topGenre = new ArrayList<>();
        following = new ArrayList<>();
        minutes = new ArrayList<>();
        topArtists.add("artist");
        topArtists.add("artist2");
        topSongs.add("song");
        topSongs.add("song2");
        topGenre.add("rock");
        topGenre.add("rap");
        minutes.add("365");
        wrap = new Wrapped(topArtists, topSongs, minutes, topGenre);
        User user1 = new User("1", "adam", wrap);
        User user2 = new User("2", "bob", wrap);
        User user3 = new User("3", "caitlyn", wrap);

        masterUser = new MasterUser("masteruser", "alex", wrap);

        user1.addPost();
        user2.addPost();
        user3.addPost();

        following.add(user1);
        following.add(user2);
        following.add(user3);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (vm.getPostLiveData().getValue().size() == 0) {
            setDummyData();
            Log.d("dummydata","dummydata initialized");
            for (User u : following) {
                vm.addAll(u.getPosts());
                Log.d("vm update","posts added to view model");
            }
        }
        //vm.printValues(); use to print values in the view model
        View view = inflater.inflate(R.layout.feed_fragment, container, false);
        feed = view.findViewById(R.id.feedRV);
        feed.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        Log.d("vm size ", String.valueOf(vm.getPostLiveData().getValue().size()));
//        Log.d("postdata in feed fragment", "debug start");
//        for (Post p : vm.getPostLiveData().getValue()) {
//            System.out.println(p.getPostId());
//        }
        Context context = getContext();
        adapter = new FeedAdapter(feedPosts, masterUser, context, this, vm);
        feed.setAdapter(adapter);
        Log.d("userFeedfragment", "created");

        Switch likedPosts = view.findViewById(R.id.likeSwitch);
        Switch commentedPosts = view.findViewById(R.id.commentSwitch);

        likedPosts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    commentedPosts.setChecked(false);
                }
                adapter.changeLikeFilter();
                adapter.notifyDataSetChanged();
            }
        });

        commentedPosts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    likedPosts.setChecked(false);
                }
                adapter.changeCommentFilter();
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }
}
