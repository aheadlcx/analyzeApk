package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.GroupInfo;

class lu implements OnClickListener {
    final /* synthetic */ GroupInfo a;
    final /* synthetic */ GroupInfoActivity b;

    lu(GroupInfoActivity groupInfoActivity, GroupInfo groupInfo) {
        this.b = groupInfoActivity;
        this.a = groupInfo;
    }

    public void onClick(View view) {
        InviteQiuYouActivity.launch(this.b, this.a);
    }
}
