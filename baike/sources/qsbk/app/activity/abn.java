package qsbk.app.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.model.GroupBriefInfo;
import qsbk.app.utils.ListUtil;

class abn implements OnItemClickListener {
    final /* synthetic */ SearchGroupActivity a;

    abn(SearchGroupActivity searchGroupActivity) {
        this.a = searchGroupActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerCount = ListUtil.getHeaderCount(this.a.j);
        if (i >= headerCount) {
            GroupInfoActivity.launch(this.a, (GroupBriefInfo) this.a.e.getItem(i - headerCount));
        }
    }
}
