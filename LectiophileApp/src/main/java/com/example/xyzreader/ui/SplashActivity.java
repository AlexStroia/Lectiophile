package com.example.xyzreader.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.xyzreader.R;
import com.example.xyzreader.data.UpdaterService;
import com.example.xyzreader.model.Book;
import com.example.xyzreader.networking.Resource;
import com.example.xyzreader.utils.Constants;
import com.example.xyzreader.viewmodel.ArticleListActivityViewModel;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private ArticleListActivityViewModel vm;
    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        vm = ViewModelProviders.of(this).get(ArticleListActivityViewModel.class);
        vm.getBooks().observe(this, new Observer<Resource<List<Book>>>() {
            @Override
            public void onChanged(Resource<List<Book>> listResource) {
                Log.d(TAG, "onChanged: " + listResource.data);
            }
        });

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, ArticleListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            refresh();
            startActivity(intent);
        }, Constants.CIRCULAR_PROGRESS_TIME);
    }


    private void refresh() {
        Intent intent = new Intent(this, UpdaterService.class);
        startService(intent);
    }
}
