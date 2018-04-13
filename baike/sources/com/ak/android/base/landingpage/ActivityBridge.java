package com.ak.android.base.landingpage;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import java.util.HashMap;

public interface ActivityBridge {
    boolean dispatchKeyEvent(KeyEvent keyEvent);

    void onCatchException(Exception exception);

    void onConfigurationChanged(Configuration configuration);

    void onCreate(Bundle bundle);

    void onDestroy();

    void onExtendMethod(HashMap<String, Object> hashMap);

    void onInit(Activity activity, CallBack callBack);

    void onLowMemory();

    void onNewIntent(Intent intent);

    void onOptionClicked(int i, View view);

    void onPause();

    void onRestart();

    void onRestoreInstanceState(Bundle bundle);

    void onResume();

    void onSaveInstanceState(Bundle bundle);

    void onStart();

    void onStop();

    void onTrimMemory(int i);
}
