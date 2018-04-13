package qsbk.app.activity;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

class lo implements OnCheckedChangeListener {
    final /* synthetic */ GroupInfoActivity a;

    lo(GroupInfoActivity groupInfoActivity) {
        this.a = groupInfoActivity;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.a.a(z);
    }
}
