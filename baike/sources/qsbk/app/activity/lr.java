package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.GroupInfo;

class lr implements OnClickListener {
    final /* synthetic */ GroupInfo a;
    final /* synthetic */ GroupInfoActivity b;

    lr(GroupInfoActivity groupInfoActivity, GroupInfo groupInfo) {
        this.b = groupInfoActivity;
        this.a = groupInfo;
    }

    public void onClick(View view) {
        MemberManagerActivity.launch(this.b, this.a);
    }
}
