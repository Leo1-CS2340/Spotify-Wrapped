package com.example.spotifywrapped.authentication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.spotifywrapped.AuthenticationActivity;
import com.example.spotifywrapped.MainActivity;
import com.example.spotifywrapped.R;
import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class SpotifyAccountFragment extends Fragment {
    private static final String CLIENT_ID = "1b5ff154cb554dbc9e189a18aff5d701";
    private static final String REDIRECT_URI = "com.example.spotifywrapped://auth";
    private static final int REQUEST_CODE = 69;
    private String access_token, access_code;
    private final OkHttpClient mOkHttpClient = new OkHttpClient();
    private Call mCall;


    public SpotifyAccountFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_spotify_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button token_btn = (Button) view.findViewById(R.id.connect_button);
        token_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthorizationRequest.Builder builder =
                        new AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI);
                builder.setScopes(new String[]{"user-read-email", "streaming", "user-top-read"});
                AuthorizationRequest request = builder.build();

                Intent intent = AuthorizationClient.createLoginActivityIntent(getActivity(), request);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthorizationResponse response = AuthorizationClient.getResponse(resultCode, intent);
            switch (response.getType()) {
                // Response was successful and contains auth token
                case TOKEN:
                    // Handle successful response
                    String token = response.getAccessToken();
                    Log.d("TokenRecieved", token);
                    final Request request = new Request.Builder()
                            .url("https://api.spotify.com/v1/me")
                            .addHeader("Authorization", "Bearer " + token)
                            .build();
                    mCall = mOkHttpClient.newCall(request);

                    mCall.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.d("HTTP", "Failed to fetch data: " + e);
                            Toast.makeText(requireActivity(), "Failed to fetch data, watch Logcat for more details",
                                    Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            try {
                                final String jsonObject = new JSONObject(response.body().string()).toString();
                                Log.d("FINAL", jsonObject);
                            } catch (JSONException e) {
                                Log.d("JSON", "Failed to parse data: " + e);
                                Toast.makeText(requireActivity(), "Failed to parse data, watch Logcat for more details",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    break;

                // Auth flow returned an error
                case ERROR:
                    // Handle error response
                    break;

                // Most likely auth flow was cancelled
                default:
                    // Handle other cases
            }
        }
    }

}