package com.ixintui.push;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.ixintui.plugin.IPushActivity;
import com.ixintui.smartlink.a.a;

public class PushActivity extends Activity {
    private IPushActivity a;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.a == null) {
            this.a = (IPushActivity) a.a((Context) this, "com.ixintui.push.PushActivityImpl");
        }
        if (this.a != null) {
            this.a.onCreate(this, bundle);
        }
    }

    protected void onRestart() {
        super.onRestart();
        if (this.a != null) {
            this.a.onRestart();
        }
    }

    protected void onStart() {
        super.onStart();
        if (this.a != null) {
            this.a.onStart();
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (this.a != null) {
            this.a.onNewIntent(intent);
        }
    }

    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        if (this.a != null) {
            this.a.onPostCreate(bundle);
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.a != null) {
            this.a.onPause();
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.a != null) {
            this.a.onResume();
        }
    }

    protected void onPostResume() {
        super.onPostResume();
        if (this.a != null) {
            this.a.onPostResume();
        }
    }

    protected void onStop() {
        super.onStop();
        if (this.a != null) {
            this.a.onStop();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.a != null) {
            this.a.onActivityResult(i, i2, intent);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (this.a != null) {
            this.a.onBackPressed();
        }
    }
}
