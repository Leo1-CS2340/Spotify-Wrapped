package com.example.spotifywrapped.ui.gallery;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.databinding.FragmentGalleryBinding;
import com.squareup.picasso.Picasso;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        String url = "https://www.billboard.com/wp-content/uploads/media/bruno-mars-thats-what-i-like-video-billboard-1548.jpg";
        ImageView imageView = root.findViewById(R.id.imageView);
        Picasso.get().load(url)
                .placeholder(R.drawable.imgload)
                .into(imageView);

        MediaPlayer m = new MediaPlayer();

        String songLink = "https://p.scdn.co/mp3-preview/93046e987d8c5bfdbeea2768ac1a8ecea17bd7e0?cid=1b5ff154cb554dbc9e189a18aff5d701";
        startAudioStream(songLink, m);
//        Log.d("userInfo", currentuser)
        final TextView textView = binding.textViewArtist1;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public void startAudioStream(String url, MediaPlayer m) {
        try {
            Log.d("mylog", "Playing: " + url);
            m.setAudioAttributes(
                    new AudioAttributes
                            .Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build());
            m.setDataSource(url);
            m.prepare();
            m.setVolume(1f, 1f);
            m.setLooping(true);
            m.start();
        } catch (Exception e) {
            Log.d("mylog", "Error playing in SoundHandler: " + e.toString());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}