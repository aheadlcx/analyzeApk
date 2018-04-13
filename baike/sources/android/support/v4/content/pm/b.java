package android.support.v4.content.pm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;

final class b extends BroadcastReceiver {
    final /* synthetic */ IntentSender a;

    b(IntentSender intentSender) {
        this.a = intentSender;
    }

    public void onReceive(Context context, Intent intent) {
        try {
            this.a.sendIntent(context, 0, null, null, null);
        } catch (SendIntentException e) {
        }
    }
}
