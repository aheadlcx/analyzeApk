package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class mp implements OnClickListener {
    final /* synthetic */ GroupInfoActivity a;

    mp(GroupInfoActivity groupInfoActivity) {
        this.a = groupInfoActivity;
    }

    public void onClick(View view) {
        if (this.a.b.autoIn) {
            ApplyForGroupActivity.launchForResult(this.a, this.a.b, -1);
        } else {
            ApplyForGroupActivity.launchForResult(this.a, this.a.b, -1);
        }
    }
}
