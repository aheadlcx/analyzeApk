package qsbk.app;

import android.os.Build.VERSION;
import android.os.SystemClock;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import qsbk.app.AppStat.OnFpsResultListener;

final class a implements FrameCallback {
    final /* synthetic */ long a;
    final /* synthetic */ int b;
    final /* synthetic */ int[] c;
    final /* synthetic */ OnFpsResultListener d;

    a(long j, int i, int[] iArr, OnFpsResultListener onFpsResultListener) {
        this.a = j;
        this.b = i;
        this.c = iArr;
        this.d = onFpsResultListener;
    }

    public void doFrame(long j) {
        int uptimeMillis = (int) ((SystemClock.uptimeMillis() - this.a) / 1000);
        if (uptimeMillis < this.b) {
            int[] iArr = this.c;
            iArr[uptimeMillis] = iArr[uptimeMillis] + 1;
            if (VERSION.SDK_INT >= 16) {
                Choreographer.getInstance().postFrameCallback(AppStat.a);
            }
        } else if (this.d != null) {
            this.d.onFpsResult(this.c);
        }
    }
}
