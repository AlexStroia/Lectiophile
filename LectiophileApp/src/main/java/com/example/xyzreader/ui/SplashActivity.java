package com.example.xyzreader.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import com.example.xyzreader.R;
import com.example.xyzreader.utils.Constants;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle();
            Intent intent = new Intent(SplashActivity.this, ArticleListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent, bundle);
        }, Constants.CIRCULAR_PROGRESS_TIME);
    }
}
