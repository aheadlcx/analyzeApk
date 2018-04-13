package qsbk.app.im;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleTopicActivity;

class as implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ am b;

    as(am amVar, String str) {
        this.b = amVar;
        this.a = str;
    }

    public void onClick(View view) {
        if (TextUtils.isEmpty(this.a)) {
            CircleTopicActivity.launch(this.b.a.h, "0");
        } else {
            CircleTopicActivity.launch(this.b.a.h, this.a);
        }
    }
}
