package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class xj implements OnClickListener {
    final /* synthetic */ NewFansActivity a;

    xj(NewFansActivity newFansActivity) {
        this.a = newFansActivity;
    }

    public void onClick(View view) {
        NewFansActivity.a(this.a);
        this.a.finish();
    }
}
