package qsbk.app.live.ui;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;

class fc implements Runnable {
    final /* synthetic */ LivePushActivity a;

    fc(LivePushActivity livePushActivity) {
        this.a = livePushActivity;
    }

    public void run() {
        this.a.k();
        if (PermissionChecker.checkSelfPermission(this.a.getActivity(), "android.permission.CAMERA") != 0) {
            ActivityCompat.requestPermissions(this.a.getActivity(), new String[]{"android.permission.CAMERA"}, 1001);
        } else if (!(this.a.aC() || !this.a.isOnResume || this.a.isFinishing())) {
            this.a.aD();
        }
        this.a.ac();
    }
}
