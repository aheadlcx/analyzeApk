package qsbk.app.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import qsbk.app.model.GroupNotice;
import qsbk.app.utils.ListUtil;

class nl implements OnItemLongClickListener {
    final /* synthetic */ GroupNoticeActivity a;

    nl(GroupNoticeActivity groupNoticeActivity) {
        this.a = groupNoticeActivity;
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerCount = ListUtil.getHeaderCount(this.a.f);
        if (i >= headerCount) {
            i -= headerCount;
        }
        if (i >= this.a.g.size()) {
            return false;
        }
        this.a.a((GroupNotice) this.a.g.get(i));
        return true;
    }
}
