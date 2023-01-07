package com.example.do_an;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfileActivity extends AppCompatActivity {

    private TextView textViewWelcome, textViewFullname, textViewEmail, textViewSdt;
    private ProgressBar progressBar;
    private ImageView imageView;
    private FirebaseAuth authProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        getSupportActionBar().setTitle("User");
        authProfile = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfile.getCurrentUser();

        textViewFullname = findViewById(R.id.textView_show_full_name);
        textViewWelcome = findViewById(R.id.textView_show_welcome);
        textViewEmail = findViewById(R.id.textView_show_email);
        textViewSdt = findViewById(R.id.textView_show_sdt);
        progressBar = findViewById(R.id.progressBar);


        if (firebaseUser != null)
        {
            progressBar.setVisibility(View.VISIBLE);
            showUserProfile(firebaseUser);
        } else {
            Toast.makeText(UserProfileActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showUserProfile(FirebaseUser firebaseUser) {
        String phone = firebaseUser.getPhoneNumber();
        String name = firebaseUser.getDisplayName();
        String email = firebaseUser.getEmail();
        textViewFullname.setText(name);
        textViewEmail.setText(email);
        textViewSdt.setText(phone);

    }

}
