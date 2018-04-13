package qsbk.app.activity;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

class lv implements OnCheckedChangeListener {
    final /* synthetic */ GroupInfoActivity a;

    lv(GroupInfoActivity groupInfoActivity) {
        this.a = groupInfoActivity;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.a.c(z);
    }
}
