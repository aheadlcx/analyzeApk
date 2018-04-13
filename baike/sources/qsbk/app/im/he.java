package qsbk.app.im;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;

class he implements OnItemLongClickListener {
    final /* synthetic */ IMMessageListFragment a;

    he(IMMessageListFragment iMMessageListFragment) {
        this.a = iMMessageListFragment;
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (j > ((long) (this.a.i.size() - 1))) {
            return false;
        }
        this.a.b((ContactListItem) this.a.i.get((int) j));
        return true;
    }
}
