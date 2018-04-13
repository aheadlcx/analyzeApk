package android.support.v4.app;

import android.view.FrameMetrics;
import android.view.Window;
import android.view.Window.OnFrameMetricsAvailableListener;

class ai implements OnFrameMetricsAvailableListener {
    final /* synthetic */ a a;

    ai(a aVar) {
        this.a = aVar;
    }

    public void onFrameMetricsAvailable(Window window, FrameMetrics frameMetrics, int i) {
        if ((this.a.b & 1) != 0) {
            this.a.a(this.a.c[0], frameMetrics.getMetric(8));
        }
        if ((this.a.b & 2) != 0) {
            this.a.a(this.a.c[1], frameMetrics.getMetric(1));
        }
        if ((this.a.b & 4) != 0) {
            this.a.a(this.a.c[2], frameMetrics.getMetric(3));
        }
        if ((this.a.b & 8) != 0) {
            this.a.a(this.a.c[3], frameMetrics.getMetric(4));
        }
        if ((this.a.b & 16) != 0) {
            this.a.a(this.a.c[4], frameMetrics.getMetric(5));
        }
        if ((this.a.b & 64) != 0) {
            this.a.a(this.a.c[6], frameMetrics.getMetric(7));
        }
        if ((this.a.b & 32) != 0) {
            this.a.a(this.a.c[5], frameMetrics.getMetric(6));
        }
        if ((this.a.b & 128) != 0) {
            this.a.a(this.a.c[7], frameMetrics.getMetric(0));
        }
        if ((this.a.b & 256) != 0) {
            this.a.a(this.a.c[8], frameMetrics.getMetric(2));
        }
    }
}
