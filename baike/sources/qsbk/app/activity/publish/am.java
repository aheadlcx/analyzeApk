package qsbk.app.activity.publish;

import android.graphics.Rect;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.ImageViewer;
import qsbk.app.utils.UIHelper;

class am implements OnClickListener {
    final /* synthetic */ CirclePublishActivity a;

    am(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void onClick(View view) {
        int i = 0;
        int size = this.a.y.size();
        if (size == 0 || (size < this.a.f.length && view == this.a.f[size])) {
            this.a.z();
            return;
        }
        int i2 = 0;
        while (i2 < size) {
            if (view == this.a.f[i2]) {
                break;
            }
            i2++;
        }
        i2 = 0;
        Rect[] rectArr = new Rect[size];
        while (i < this.a.y.size()) {
            rectArr[i] = UIHelper.getRectOnScreen(this.a.f[i]);
            i++;
        }
        ImageViewer.launch(this.a, i2, this.a.y, rectArr);
    }
}
