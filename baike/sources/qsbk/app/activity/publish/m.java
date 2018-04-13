package qsbk.app.activity.publish;

import android.text.Selection;

class m implements Runnable {
    final /* synthetic */ CirclePublishActivity a;

    m(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void run() {
        Selection.setSelection(this.a.b.getText(), 0);
    }
}
