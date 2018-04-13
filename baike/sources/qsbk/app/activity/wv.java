package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class wv implements OnClickListener {
    final /* synthetic */ MyInfoActivity a;

    wv(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
