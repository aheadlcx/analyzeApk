package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CheckInActivity;

class jx implements OnClickListener {
    final /* synthetic */ QiuyouCircleFragment a;

    jx(QiuyouCircleFragment qiuyouCircleFragment) {
        this.a = qiuyouCircleFragment;
    }

    public void onClick(View view) {
        CheckInActivity.launch(this.a.getActivity());
    }
}
