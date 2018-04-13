package qsbk.app.im;

import qsbk.app.im.ScrollTopListView.OnScrollToTopListener;
import qsbk.app.im.datastore.Util;

class cc implements OnScrollToTopListener {
    final /* synthetic */ ConversationActivity a;

    cc(ConversationActivity conversationActivity) {
        this.a = conversationActivity;
    }

    public void onPull() {
        Util.imStorageQueue.postRunnable(new cd(this));
    }
}
