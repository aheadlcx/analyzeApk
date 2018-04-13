package com.ak.android.bridge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import com.ak.android.base.landingpage.ActivityBridge;
import com.ak.android.base.landingpage.CallBack;
import java.util.HashMap;

public class AKActivity extends Activity implements CallBack {
    private ActivityBridge activityBridge;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        IBridge a = c.a((Context) this);
        if (a != null) {
            this.activityBridge = a.getActivityBridge();
        }
        if (this.activityBridge != null) {
            this.activityBridge.onInit(this, this);
        }
        if (this.activityBridge != null) {
            this.activityBridge.onCreate(bundle);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.activityBridge != null) {
            this.activityBridge.onDestroy();
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (this.activityBridge != null) {
            this.activityBridge.onNewIntent(intent);
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.activityBridge != null) {
            this.activityBridge.onResume();
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.activityBridge != null) {
            this.activityBridge.onPause();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.activityBridge != null) {
            this.activityBridge.onConfigurationChanged(configuration);
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        if (this.activityBridge != null) {
            this.activityBridge.onLowMemory();
        }
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        if (this.activityBridge != null) {
            this.activityBridge.onTrimMemory(i);
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.activityBridge != null) {
            return this.activityBridge.dispatchKeyEvent(keyEvent);
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    protected void onStart() {
        super.onStart();
        if (this.activityBridge != null) {
            this.activityBridge.onStart();
        }
    }

    protected void onRestart() {
        super.onRestart();
        if (this.activityBridge != null) {
            this.activityBridge.onRestart();
        }
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (this.activityBridge != null) {
            this.activityBridge.onRestoreInstanceState(bundle);
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.activityBridge != null) {
            this.activityBridge.onSaveInstanceState(bundle);
        }
    }

    protected void onStop() {
        super.onStop();
        if (this.activityBridge != null) {
            this.activityBridge.onStop();
        }
    }

    public void onCatchException(Exception exception) {
        if (this.activityBridge != null) {
            this.activityBridge.onCatchException(exception);
        }
    }

    public void onOptionClicked(int i, View view) {
        if (this.activityBridge != null) {
            this.activityBridge.onOptionClicked(i, view);
        }
    }

    public void onExtendMethod(HashMap<String, Object> hashMap) {
        if (this.activityBridge != null) {
            this.activityBridge.onExtendMethod(hashMap);
        }
    }
}
