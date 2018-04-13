package qsbk.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import qsbk.app.model.Laisee;

class rc extends BroadcastReceiver {
    final /* synthetic */ MainActivity a;

    rc(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void onReceive(Context context, Intent intent) {
        Laisee laisee = (Laisee) intent.getSerializableExtra("laisee");
        if (laisee != null) {
            this.a.f = laisee;
            if (MainActivity.b(this.a)) {
                MainActivity.c(this.a);
            }
        }
    }
}
