package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.GroupBriefInfo;

class nt implements OnClickListener {
    final /* synthetic */ GroupBriefInfo a;
    final /* synthetic */ a b;

    nt(a aVar, GroupBriefInfo groupBriefInfo) {
        this.b = aVar;
        this.a = groupBriefInfo;
    }

    public void onClick(View view) {
        GroupInfoActivity.launch(view.getContext(), this.a);
    }
}
