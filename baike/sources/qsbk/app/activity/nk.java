package qsbk.app.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.model.GroupNotice;
import qsbk.app.utils.ListUtil;

class nk implements OnItemClickListener {
    final /* synthetic */ GroupNoticeActivity a;

    nk(GroupNoticeActivity groupNoticeActivity) {
        this.a = groupNoticeActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerCount = ListUtil.getHeaderCount(this.a.f);
        if (i >= headerCount) {
            int i2 = i - headerCount;
            GroupNotice groupNotice = (GroupNotice) this.a.d.getItem(i2);
            if (groupNotice.isActionNotice()) {
                GroupNoticeDetailActivity.launch(this.a, i2, groupNotice);
            }
        }
    }
}
