package qsbk.app.im;

import android.support.v4.app.ActivityCompat;

class gq implements Runnable {
    final /* synthetic */ gp a;

    gq(gp gpVar) {
        this.a = gpVar;
    }

    public void run() {
        this.a.a.g();
        ActivityCompat.invalidateOptionsMenu(this.a.a.getActivity());
        MessageCountManager.getMessageCountManager(this.a.a.j).updateUnreadCount(0, 0);
        this.a.a.q.setAllUnreadToZero();
        this.a.a.q.notifyDataSetChanged();
    }
}
