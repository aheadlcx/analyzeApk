package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.GroupInfo;
import qsbk.app.widget.GroupLevelHelpDialog;

class lq implements OnClickListener {
    final /* synthetic */ GroupInfo a;
    final /* synthetic */ GroupInfoActivity b;

    lq(GroupInfoActivity groupInfoActivity, GroupInfo groupInfo) {
        this.b = groupInfoActivity;
        this.a = groupInfo;
    }

    public void onClick(View view) {
        this.b.A = new GroupLevelHelpDialog(this.b, this.a.level, this.a.leftOMember);
        this.b.A.show();
    }
}
