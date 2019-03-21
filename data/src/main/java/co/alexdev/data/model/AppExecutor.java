package co.alexdev.data.model;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;

public final class AppExecutor {

    private static final Object LOCK = new Object();
    private static AppExecutor sAppExecutor;
    private final Executor movieIO;
    private final Executor networkIO;
    private final Executor mainThreadIO;


    public AppExecutor(Executor movieIO, Executor networkIO, Executor mainThreadIO) {
        this.movieIO = movieIO;
        this.networkIO = networkIO;
        this.mainThreadIO = mainThreadIO;
    }

    /*Executors.newSingleThreadExecutor() - make sure that operations are done in order*/
    public static AppExecutor getInstance() {
        if (sAppExecutor == null) {
            synchronized (LOCK) {
                sAppExecutor = new AppExecutor(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3),
                        new MainThreadExecutor());
            }
        }
        return sAppExecutor;
    }

    public Executor getNetworkIO() {
        return networkIO;
    }

    public Executor getMainThreadIO() {
        return mainThreadIO;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable runnable) {
            mainThreadHandler.post(runnable);
        }
    }

    public Executor getDiskIO() {
        return movieIO;
    }
}