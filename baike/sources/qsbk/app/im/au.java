package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleTopicActivity;

class au implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ an b;

    au(an anVar, String str) {
        this.b = anVar;
        this.a = str;
    }

    public void onClick(View view) {
        CircleTopicActivity.launch(this.b.a.h, this.a);
    }
}
