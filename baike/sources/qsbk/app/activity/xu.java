package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.NewFan;

class xu implements OnClickListener {
    final /* synthetic */ NewFansActivity a;

    xu(NewFansActivity newFansActivity) {
        this.a = newFansActivity;
    }

    public void onClick(View view) {
        NewFansActivity.q(this.a).setClickable(false);
        NewFansActivity.a(this.a, ((NewFan) NewFansActivity.c(this.a).get(NewFansActivity.m(this.a).getSelectedItemPosition())).userId);
        NewFansActivity.p(this.a);
    }
}
