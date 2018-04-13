package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.GroupInfo;

class mr implements OnClickListener {
    final /* synthetic */ GroupInfo a;
    final /* synthetic */ GroupInfoActivity b;

    mr(GroupInfoActivity groupInfoActivity, GroupInfo groupInfo) {
        this.b = groupInfoActivity;
        this.a = groupInfo;
    }

    public void onClick(View view) {
        InviteQiuYouActivity.launch(this.b, this.a);
    }
}
