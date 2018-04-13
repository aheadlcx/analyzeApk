package qsbk.app.activity;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

class hi implements OnClickListener {
    final /* synthetic */ CircleTopicActivity a;

    hi(CircleTopicActivity circleTopicActivity) {
        this.a = circleTopicActivity;
    }

    public void onClick(View view) {
        String str = this.a.g.extraLink;
        if (!TextUtils.isEmpty(str)) {
            SimpleWebActivity.launch(view.getContext(), str, false);
        }
    }
}
