package qsbk.app.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.model.GroupBriefInfo;
import qsbk.app.utils.ListUtil;

class oc implements OnItemClickListener {
    final /* synthetic */ GroupRankFragment a;

    oc(GroupRankFragment groupRankFragment) {
        this.a = groupRankFragment;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerCount = ListUtil.getHeaderCount(GroupRankFragment.a(this.a));
        if (i >= headerCount) {
            headerCount = i - headerCount;
            if (headerCount < GroupRankFragment.b(this.a).getCount()) {
                GroupInfoActivity.launch(this.a.getActivity(), (GroupBriefInfo) GroupRankFragment.b(this.a).getItem(headerCount));
            }
        }
    }
}
