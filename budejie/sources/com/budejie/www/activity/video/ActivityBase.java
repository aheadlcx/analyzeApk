package com.budejie.www.activity.video;

import android.app.Activity;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import com.budejie.www.R;

public abstract class ActivityBase extends Activity {
    private static boolean b = false;
    protected Camera a;
    private boolean c;
    private Dialog d;

    protected abstract void a();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setVolumeControlStream(3);
    }

    public void onWindowFocusChanged(boolean z) {
        if (b) {
            Log.v("ActivityBase", "onWindowFocusChanged.hasFocus=" + z + ".mOnResumePending=" + this.c);
        }
        if (z && this.c) {
            a();
            this.c = false;
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.a == null && !hasWindowFocus() && b()) {
            if (b) {
                Log.v("ActivityBase", "onRsume. mOnResumePending=true");
            }
            this.c = true;
            return;
        }
        if (b) {
            Log.v("ActivityBase", "onRsume. mOnResumePending=false");
        }
        a();
        this.c = false;
    }

    protected void onPause() {
        if (b) {
            Log.v("ActivityBase", "onPause");
        }
        super.onPause();
        this.c = false;
    }

    public boolean onSearchRequested() {
        return false;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if ((i == 84 || i == 82) && keyEvent.isLongPress()) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    protected void onDestroy() {
        l.a(this);
        super.onDestroy();
    }

    private boolean b() {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService("keyguard");
        return keyguardManager != null && keyguardManager.inKeyguardRestrictedInputMode();
    }

    protected Dialog onCreateDialog(int i) {
        switch (i) {
            case 1:
                this.d = new Dialog(this, R.style.dialogTheme);
                this.d.setContentView(R.layout.camrea_loaddialog);
                this.d.setCancelable(false);
                return this.d;
            default:
                return super.onCreateDialog(i);
        }
    }
}
