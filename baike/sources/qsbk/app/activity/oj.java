package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class oj implements OnClickListener {
    final /* synthetic */ HotCommentPagerActivity a;

    oj(HotCommentPagerActivity hotCommentPagerActivity) {
        this.a = hotCommentPagerActivity;
    }

    public void onClick(View view) {
        this.a.h.cancel();
    }
}
