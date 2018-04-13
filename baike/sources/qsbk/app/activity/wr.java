package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class wr implements OnClickListener {
    final /* synthetic */ MyInfoActivity a;

    wr(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void onClick(View view) {
        MedalListActivity.launch(this.a, this.a.b, this.a.bC);
    }
}
