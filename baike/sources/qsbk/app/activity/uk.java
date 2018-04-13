package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class uk implements OnClickListener {
    final /* synthetic */ MyHighlightQiushiActivity a;

    uk(MyHighlightQiushiActivity myHighlightQiushiActivity) {
        this.a = myHighlightQiushiActivity;
    }

    public void onClick(View view) {
        this.a.g.cancel();
    }
}
