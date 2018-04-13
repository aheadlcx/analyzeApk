package qsbk.app.live.debug;

import android.view.View;
import android.view.View.OnClickListener;

class d implements OnClickListener {
    final /* synthetic */ LiveDebugListFragment a;

    d(LiveDebugListFragment liveDebugListFragment) {
        this.a = liveDebugListFragment;
    }

    public void onClick(View view) {
        this.a.n = ((this.a.n - 1) + 7) % 7;
        this.a.j();
    }
}
