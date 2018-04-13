package qsbk.app.nearby.ui;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

class i implements Runnable {
    final /* synthetic */ h a;

    i(h hVar) {
        this.a = hVar;
    }

    public void run() {
        if (this.a.b.n.getPickedBitmap() != null) {
            this.a.b.i.setImageBitmap(this.a.b.n.getPickedBitmap());
            Intent intent = new Intent();
            intent.setAction(InfoCompleteActivity.ACTION_CHANGE_USERINFO);
            LocalBroadcastManager.getInstance(this.a.b).sendBroadcast(intent);
        }
    }
}
