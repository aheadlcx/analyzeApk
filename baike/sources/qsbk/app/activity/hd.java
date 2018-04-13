package qsbk.app.activity;

import android.support.v4.view.ViewCompat;
import qsbk.app.widget.QiushiTopicNavLayout.OnScrollHeaderListener;

class hd implements OnScrollHeaderListener {
    final /* synthetic */ CircleTopicActivity a;

    hd(CircleTopicActivity circleTopicActivity) {
        this.a = circleTopicActivity;
    }

    public void scrollHeader() {
        if (this.a.Z != null) {
            this.a.Z.setBackgroundColor(-17899);
            if (this.a.g != null) {
                this.a.W.setText(this.a.g.content);
            }
        }
    }

    public void scrollHeaderChange() {
        if (this.a.Z != null) {
            this.a.Z.setBackgroundColor(ViewCompat.MEASURED_SIZE_MASK);
            this.a.W.setText(null);
        }
    }
}
