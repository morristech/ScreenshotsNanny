package com.basgeekball.screenshotsnanny.core;

import android.app.Activity;
import android.os.Handler;

import com.basgeekball.screenshotsnanny.activityassistant.ActivityHelper;
import com.basgeekball.screenshotsnanny.helper.Callback;

public class ScreenshotsTask {
    public static void perform(final Callback callback, long activityDelay, final long screenshotDelay) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.execute();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Activity activity = ActivityHelper.getCurrentActivity();
                        ScreenshotsCapturer.execute(activity);
                        activity.finish();
                    }
                }, screenshotDelay);
            }
        }, activityDelay);
    }

    public static void perform(final Class<?> T, final Callback callback, final int mapFragmentId, long activityDelay, final long screenshotDelay) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.execute();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Activity activity = ActivityHelper.getCurrentActivity();
                        ScreenshotsCapturer.executeWithMap(activity, mapFragmentId, true);
                    }
                }, 1000);
            }
        }, activityDelay);
    }
}
