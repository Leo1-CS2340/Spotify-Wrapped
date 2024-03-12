package com.example.spotifywrapped.authentication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.spotifywrapped.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegisterFragment extends Fragment {
    private TextInputLayout email;
    private TextInputLayout password;
    private TextInputLayout confirm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseAuth.getInstance().useEmulator("10.0.2.2", 9099);
        email = view.findViewById(R.id.register_email);
        password = view.findViewById(R.id.register_password);
        confirm = view.findViewById(R.id.register_confirm);
        Button register_button = view.findViewById(R.id.register_button);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndRegister();
            }
        });
    }

    private void validateAndRegister() {
        String textEmail = email.getEditText().getText().toString();
        String textPassword = password.getEditText().getText().toString();
        String textConfirm = confirm.getEditText().getText().toString();
        email.setError(null);
        password.setError(null);
        confirm.setError(null);
        if (TextUtils.isEmpty(textEmail)) {
            Toast.makeText(getActivity(), "Please enter your email", Toast.LENGTH_LONG).show();
            email.setError("Email is required");
            email.requestFocus();
        } else if (TextUtils.isEmpty(textPassword)) {
            Toast.makeText(getActivity(), "Please enter your password", Toast.LENGTH_LONG).show();
            password.setError("Password is required");
            password.requestFocus();
        } else if (TextUtils.isEmpty(textConfirm)) {
            Toast.makeText(getActivity(), "Please confirm your password", Toast.LENGTH_LONG).show();
            confirm.setError("Confirmation of password is required");
            confirm.requestFocus();
        } else if (!textPassword.equals(textConfirm)) {
            Toast.makeText(getActivity(), "Your passwords don't match", Toast.LENGTH_LONG).show();
            confirm.setError("Your passwords must match");
            confirm.requestFocus();
            password.getEditText().getText().clear();
            confirm.getEditText().getText().clear();
        } else if (textPassword.length() < 6) {
            Toast.makeText(getActivity(), "Your password must be longer than 6 characters", Toast.LENGTH_LONG).show();
            confirm.setError("Password is too short");
            confirm.requestFocus();
        } else {
            Log.d("Register","Everything is good");
            registerUser(textEmail, textPassword);
        }
    }

    private void registerUser(String email, String password) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "User registered successfully", Toast.LENGTH_LONG).show();
                            FirebaseUser user = auth.getCurrentUser();
                            Log.d("Test", user.getEmail());
                            // @Todo Change the activity here
                        } else {
                            Toast.makeText(getActivity(), "Registration failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}