package com.ixintui.plugin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public interface IRichpushActivity {
    void onActivityResult(int i, int i2, Intent intent);

    void onBackPressed();

    void onCreate(Activity activity, Bundle bundle);

    void onNewIntent(Intent intent);

    void onPause();

    void onPostCreate(Bundle bundle);

    void onPostResume();

    void onRestart();

    void onResume();

    void onStart();

    void onStop();
}
