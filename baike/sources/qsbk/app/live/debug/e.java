package qsbk.app.live.debug;

import android.view.View;
import android.view.View.OnClickListener;

class e implements OnClickListener {
    final /* synthetic */ LiveDebugListFragment a;

    e(LiveDebugListFragment liveDebugListFragment) {
        this.a = liveDebugListFragment;
    }

    public void onClick(View view) {
        this.a.n = (this.a.n + 1) % 7;
        this.a.j();
    }
}
