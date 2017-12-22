/*
 * Copyright (c) 2016 - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential by BNBJobs
 */

package com.rxjavaandroiddemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class ActivityUtils {

  public static void launchActivity(Activity context, Class<? extends Activity> activity,
      boolean closeCurrentActivity, Bundle bundle) {
    Intent intent = new Intent(context, activity);

    if (bundle != null) {
      intent.putExtras(bundle);
    }

    context.startActivity(intent);
    if (closeCurrentActivity) {
      context.finish();
    }
  }

  public static void launchActivity(Activity context, Class<? extends Activity> activity, boolean closeCurrentActivity) {
    ActivityUtils.launchActivity(context, activity, closeCurrentActivity, null);
  }

  public static void launchActivityWithClearBackStack(Context context, Class<? extends Activity> activity) {
    Intent intent = new Intent(context, activity);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
        Intent.FLAG_ACTIVITY_CLEAR_TASK);
    context.startActivity(intent);
  }

  /**
   * Force screen to turn on if the phone is asleep.
   *
   * @param context The current Context or Activity that this method is called from
   */
  public static void turnScreenOn(Activity context) {
    try {
      Window window = context.getWindow();
      window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
      window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
      window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
    } catch (Exception ex) {
      Log.e("Caffeine", "Unable to turn on screen for activity " + context);
    }
  }
}