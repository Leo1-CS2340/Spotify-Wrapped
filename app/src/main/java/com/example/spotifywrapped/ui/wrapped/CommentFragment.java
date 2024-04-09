package com.example.spotifywrapped.ui.wrapped;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifywrapped.FeedAdapter;
import com.example.spotifywrapped.R;
import com.example.spotifywrapped.data_classes.Comment;
import com.example.spotifywrapped.data_classes.User;
import com.example.spotifywrapped.ui.CommentAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentFragment extends DialogFragment {



    public CommentFragment(List<Comment> comments, Context context) {
        this.comments = comments;
        this.context = context;
    }

    Context context;
    RecyclerView commentView;
    CommentAdapter adapter;
    List<Comment> comments;

    private void addComments(String str) {
        Log.d("comment", "addComments() called");
        Comment newComment = new Comment("username", str, new Date());
        comments.add(newComment);
        adapter.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.comment_section, null);
        commentView = view.findViewById(R.id.commentRV);
        commentView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new CommentAdapter(comments);
        commentView.setAdapter(adapter);

        Button commentButton = view.findViewById(R.id.submitbutton);
        commentButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Log.d("Button prese", "butt");
                        EditText text = view.findViewById(R.id.editTextComment);
                        if (text.getText().toString() != null && !text.getText().toString().isEmpty()) {
                            String str = text.getText().toString();
                            addComments(str);
                            text.setText("");
                        }
                    }
                }
        );

        builder.setView(view);
        return builder.create();
    }
}

