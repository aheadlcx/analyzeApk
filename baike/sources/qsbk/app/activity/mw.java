package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class mw implements OnClickListener {
    final /* synthetic */ GroupIntroduceActivity a;

    mw(GroupIntroduceActivity groupIntroduceActivity) {
        this.a = groupIntroduceActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
