package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleTopicActivity;

class z implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ n b;

    z(n nVar, String str) {
        this.b = nVar;
        this.a = str;
    }

    public void onClick(View view) {
        CircleTopicActivity.launch(this.b.a.h, this.a);
    }
}
