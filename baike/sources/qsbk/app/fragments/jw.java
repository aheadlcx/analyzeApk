package qsbk.app.fragments;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.activity.publish.CirclePublishActivity;

class jw implements OnClickListener {
    final /* synthetic */ QiuyouCircleFragment a;

    jw(QiuyouCircleFragment qiuyouCircleFragment) {
        this.a = qiuyouCircleFragment;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser != null) {
            this.a.startActivity(new Intent(this.a.getActivity(), CirclePublishActivity.class));
            return;
        }
        this.a.startActivity(new Intent(this.a.getActivity(), ActionBarLoginActivity.class));
    }
}
