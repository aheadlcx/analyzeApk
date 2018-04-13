package qsbk.app.live.ui;

import android.view.SurfaceView;
import qsbk.app.core.utils.LogUtils;

class aa implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ int c;
    final /* synthetic */ LiveBaseActivity d;

    aa(LiveBaseActivity liveBaseActivity, int i, String str, int i2) {
        this.d = liveBaseActivity;
        this.a = i;
        this.b = str;
        this.c = i2;
    }

    public void run() {
        if (!this.d.isFinishing()) {
            if (LiveBaseActivity.h(this.d).containsKey(Integer.valueOf(this.a))) {
                LogUtils.d("MicConnect", "already added to UI, ignore items. uid:" + this.a + " surfaceView:" + LiveBaseActivity.h(this.d).get(Integer.valueOf(this.a)));
                return;
            }
            LogUtils.d("MicConnect", "onJoinChannelSuccess channel:" + this.b + " uid:" + this.a + " elapsed:" + this.c);
            LiveBaseActivity.s(this.d).getEngineConfig().mUid = this.a;
            SurfaceView surfaceView = (SurfaceView) LiveBaseActivity.h(this.d).remove(Integer.valueOf(0));
            if (surfaceView != null) {
                LiveBaseActivity.h(this.d).put(Integer.valueOf(this.a), surfaceView);
            }
        }
    }
}
