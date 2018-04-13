package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.GroupInfo;

class ls implements OnClickListener {
    final /* synthetic */ GroupInfo a;
    final /* synthetic */ GroupInfoActivity b;

    ls(GroupInfoActivity groupInfoActivity, GroupInfo groupInfo) {
        this.b = groupInfoActivity;
        this.a = groupInfo;
    }

    public void onClick(View view) {
        GroupLeaderListActivity.launch(this.b, this.a);
    }
}
