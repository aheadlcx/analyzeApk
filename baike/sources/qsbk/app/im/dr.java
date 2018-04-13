package qsbk.app.im;

import qsbk.app.im.ScrollTopListView.OnScrollToTopListener;
import qsbk.app.im.datastore.Util;

class dr implements OnScrollToTopListener {
    final /* synthetic */ GroupConversationActivity a;

    dr(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void onPull() {
        Util.imStorageQueue.postRunnable(new ds(this));
    }
}
