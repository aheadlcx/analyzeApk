package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.live.ui.LiveUserLevelActivity;

class vz implements OnClickListener {
    final /* synthetic */ MyInfoActivity a;

    vz(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void onClick(View view) {
        if (this.a.bC) {
            this.a.startActivity(new Intent(this.a, LiveUserLevelActivity.class));
        }
    }
}
