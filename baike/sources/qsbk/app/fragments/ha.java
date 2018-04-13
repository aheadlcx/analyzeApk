package qsbk.app.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.activity.GroupInfoActivity;
import qsbk.app.model.GroupBriefInfo;
import qsbk.app.utils.ListUtil;

class ha implements OnItemClickListener {
    final /* synthetic */ NearByGroupFragment a;

    ha(NearByGroupFragment nearByGroupFragment) {
        this.a = nearByGroupFragment;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerCount = ListUtil.getHeaderCount(this.a.b);
        if (i >= headerCount) {
            GroupInfoActivity.launch(this.a.getActivity(), (GroupBriefInfo) this.a.d.getItem(i - headerCount));
        }
    }
}
