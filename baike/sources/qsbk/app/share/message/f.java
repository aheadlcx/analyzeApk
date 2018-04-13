package qsbk.app.share.message;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.im.ContactListItem;
import qsbk.app.utils.DebugUtil;

class f implements OnItemClickListener {
    final /* synthetic */ RecentContactsFragment a;

    f(RecentContactsFragment recentContactsFragment) {
        this.a = recentContactsFragment;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        DebugUtil.debug(RecentContactsFragment.a, "onItemClick,position=" + i + ",id=" + j);
        this.a.a((ContactListItem) this.a.f.get(i));
    }
}
