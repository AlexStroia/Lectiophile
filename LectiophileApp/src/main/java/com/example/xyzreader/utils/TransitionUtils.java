package com.example.xyzreader.utils;

import android.app.Activity;
import android.transition.Fade;
import android.view.View;
import android.view.Window;

import com.example.xyzreader.R;

public class TransitionUtils {

    /*Helper method which helps in performing smoother animations*/
    public static void setTransition(Activity activity) {
        Window window = activity.getWindow();
        View decor = window.getDecorView();
        Fade fade = new Fade();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container),true);
        fade.excludeTarget(android.R.id.statusBarBackground,true);
        fade.excludeTarget(android.R.id.navigationBarBackground,true);

        window.setEnterTransition(fade);
        window.setExitTransition(fade);
    }
}
