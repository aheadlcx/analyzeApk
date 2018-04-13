package qsbk.app.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import qsbk.app.QsbkApp;
import qsbk.app.im.UserChatManager;

public class NetworkStateBroadcastReceiver extends BroadcastReceiver {
    private static long a;
    private final NetworkStateListener b;

    public interface NetworkStateListener {
        void onNetworkUnavaliable();
    }

    public NetworkStateBroadcastReceiver(Context context, NetworkStateListener networkStateListener) {
        this.b = networkStateListener;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getBooleanExtra("noConnectivity", false) && SystemClock.elapsedRealtime() - a >= 10000) {
            a = SystemClock.elapsedRealtime();
            if (this.b != null) {
                this.b.onNetworkUnavaliable();
            }
            if (QsbkApp.currentUser != null) {
                UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).destroy(false);
            }
        }
    }

    public void register(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(this, intentFilter);
    }

    public void unregister(Context context) {
        context.unregisterReceiver(this);
    }
}
