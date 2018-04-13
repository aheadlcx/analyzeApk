package qsbk.app.activity.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class n extends BroadcastReceiver {
    final /* synthetic */ BaseArticleListViewFragment a;

    n(BaseArticleListViewFragment baseArticleListViewFragment) {
        this.a = baseArticleListViewFragment;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.a.j != null && this.a.i != null) {
            this.a.i.notifyDataSetChanged();
        }
    }
}
