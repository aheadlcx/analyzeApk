package qsbk.app.activity.publish;

import android.view.View;
import android.view.View.OnClickListener;

class bb implements OnClickListener {
    final /* synthetic */ PublishActivity a;

    bb(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void onClick(View view) {
        if (this.a.K()) {
            this.a.m();
            this.a.X = false;
        } else {
            this.a.l();
            this.a.X = true;
        }
        this.a.W = false;
    }
}
