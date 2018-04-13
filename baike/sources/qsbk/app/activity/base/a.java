package qsbk.app.activity.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import qsbk.app.model.UserLoginGuideCard;

class a extends BroadcastReceiver {
    final /* synthetic */ BaseArticleListViewFragment a;

    a(BaseArticleListViewFragment baseArticleListViewFragment) {
        this.a = baseArticleListViewFragment;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.a.j != null && this.a.j.contains(UserLoginGuideCard.instance)) {
            this.a.j.remove(UserLoginGuideCard.instance);
            if (this.a.i != null) {
                this.a.i.notifyDataSetChanged();
            }
        }
    }
}
