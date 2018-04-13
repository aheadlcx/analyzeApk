package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.SimpleWebActivity;

class df implements OnClickListener {
    final /* synthetic */ LaiseeEventGetFragment a;

    df(LaiseeEventGetFragment laiseeEventGetFragment) {
        this.a = laiseeEventGetFragment;
    }

    public void onClick(View view) {
        SimpleWebActivity.launch(this.a.getActivity(), this.a.j.webUrl);
    }
}
