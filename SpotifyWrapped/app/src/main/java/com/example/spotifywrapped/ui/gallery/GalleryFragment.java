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
import com.example.spotifywrapped.authentication.LoginFragment;
import com.example.spotifywrapped.data_classes.Artist;
import com.example.spotifywrapped.data_classes.Song;
import com.example.spotifywrapped.databinding.FragmentGalleryBinding;
import com.squareup.picasso.Picasso;

import org.checkerframework.checker.units.qual.K;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private MediaPlayer m;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //GalleryViewModel galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        m = new MediaPlayer();


        Log.d("userInfo", LoginFragment.currentUser.getName());

        ArrayList<Song> u = (LoginFragment.currentUser.getTopFiveSongs());
        ArrayList<Artist> j = (LoginFragment.currentUser.getTopFiveArtists());

        HashMap<String, Object> firstartist = (HashMap<String, Object>) j.toArray()[0];
        ArrayList<Object> artlist = new ArrayList<Object>(firstartist.values());
        String artistname1 = (String) artlist.get(2);
        ((TextView) root.findViewById(R.id.textViewArtist1)).setText(artistname1);

        String url = (String) artlist.get(3);
        ImageView imageView = root.findViewById(R.id.imageView);
        Picasso.get().load(url)
                .placeholder(R.drawable.imgload)
                .into(imageView);

        //DO NOT DELETE
//        HashMap<String, Object> secondartist = (HashMap<String, Object>) j.toArray()[1];
//        ArrayList<Object> artlist2 = new ArrayList<Object>(secondartist.values());
//        String artistname2 = (String) artlist2.get(2);
//        ((TextView) root.findViewById(R.id.textViewArtist2)).setText(artistname2);
//
//        HashMap<String, Object> thirdartist = (HashMap<String, Object>) j.toArray()[2];
//        ArrayList<Object> artlist3 = new ArrayList<Object>(thirdartist.values());
//        String artistname3 = (String) artlist3.get(2);
//        ((TextView) root.findViewById(R.id.textViewArtist3)).setText(artistname3);
//
//        HashMap<String, Object> fourthartist = (HashMap<String, Object>) j.toArray()[3];
//        ArrayList<Object> artlist4 = new ArrayList<Object>(fourthartist.values());
//        String artistname4 = (String) artlist4.get(2);
//        ((TextView) root.findViewById(R.id.textViewArtist4)).setText(artistname4);
//
//        HashMap<String, Object> fifthartist = (HashMap<String, Object>) j.toArray()[4];
//        ArrayList<Object> artlist5 = new ArrayList<Object>(fifthartist.values());
//        String artistname5 = (String) artlist5.get(2);
//        ((TextView) root.findViewById(R.id.textViewArtist5)).setText(artistname5);

        //ARTIST 0: genre list, 1: popularity, 2: artist name, 3: artist pic url
        //SONG 0: preview url, 1: artist name, 2: song name, 3: popularity
        HashMap<String, Object> firstsong = (HashMap<String, Object>) u.toArray()[0];
        ArrayList<Object> list = new ArrayList<Object>(firstsong.values());
        String song1 = (String) list.get(2) + " by " + (String) list.get(1);
        ((TextView) root.findViewById(R.id.textViewSong1)).setText(song1);

        String songLink = (String) list.get(0);
        startAudioStream(songLink, m);

        HashMap<String, Object> s = (HashMap<String, Object>) u.toArray()[1];
        ArrayList<Object> secondlist = new ArrayList<Object>(s.values());
        String song2 = (String) secondlist.get(2) + " by " + (String) secondlist.get(1);
        Log.d("Second song", song2);
        ((TextView) root.findViewById(R.id.textViewSong2)).setText(song2);

        HashMap<String, Object> thirdsong = (HashMap<String, Object>) u.toArray()[2];
        ArrayList<Object> list3 = new ArrayList<Object>(thirdsong.values());
        String song3 = (String) list3.get(2) + " by " + (String) list3.get(1);
        ((TextView) root.findViewById(R.id.textViewSong3)).setText(song3);

        HashMap<String, Object> fourthsong = (HashMap<String, Object>) u.toArray()[3];
        ArrayList<Object> list4 = new ArrayList<Object>(fourthsong.values());
        String song4 = (String) list4.get(2) + " by " + (String) list4.get(1);
        ((TextView) root.findViewById(R.id.textViewSong4)).setText(song4);

        HashMap<String, Object> fifthsong = (HashMap<String, Object>) u.toArray()[4];
        ArrayList<Object> list5 = new ArrayList<Object>(fifthsong.values());
        String song5 = (String) list5.get(2) + " by " + (String) list5.get(1);
        ((TextView) root.findViewById(R.id.textViewSong5)).setText(song5);

        final TextView textView = binding.textViewArtist1;
        //galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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
        m.stop();
    }
}