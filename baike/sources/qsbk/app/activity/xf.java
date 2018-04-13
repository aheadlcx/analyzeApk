package qsbk.app.activity;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import java.util.Timer;
import qsbk.app.model.relationship.Relationship;
import qsbk.app.utils.LoginPermissionClickDelegate;

class xf implements OnTouchListener {
    final /* synthetic */ MyInfoActivity a;

    xf(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (Relationship.NO_REL_CHATED != this.a.bD && Relationship.NO_REL != this.a.bD) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                if (!this.a.bV) {
                    if (this.a.bG == 0 && this.a.bJ) {
                        this.a.bM = SystemClock.elapsedRealtime();
                        this.a.bJ = false;
                    } else {
                        this.a.bJ = false;
                    }
                    this.a.bI.removeCallbacks(this.a.bK);
                    if (this.a.bH != null) {
                        this.a.bH.cancel();
                    }
                    this.a.bH = new Timer();
                    if (this.a.bG >= 0 && this.a.bG <= 100) {
                        this.a.bH.schedule(new xg(this), 20, 20);
                        break;
                    }
                }
                LoginPermissionClickDelegate.startLoginActivity(this.a, BasePersonalCenterActivity.LOGIN_REQUEST_CODE);
                return true;
                break;
            case 1:
                if (!this.a.bV) {
                    this.a.bI.removeCallbacks(this.a.cb);
                    if (this.a.bH != null) {
                        this.a.bH.cancel();
                    }
                    this.a.bH = new Timer();
                    if (this.a.bG > 0 && this.a.bG <= 100) {
                        this.a.bH.schedule(new xh(this), 20, 20);
                        break;
                    }
                }
                return true;
        }
        return true;
    }
}
