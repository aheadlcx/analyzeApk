package qsbk.app.im;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import qsbk.app.utils.AppContext;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LogUtil;

public class ChatClientManager$NetworkReceiver extends BroadcastReceiver {
    final /* synthetic */ ChatClientManager a;

    public ChatClientManager$NetworkReceiver(ChatClientManager chatClientManager) {
        this.a = chatClientManager;
    }

    public void onReceive(Context context, Intent intent) {
        LogUtil.d("recv network change status");
        if (HttpUtils.isNetworkConnected(AppContext.getContext())) {
            this.a.connectLater();
            AppContext.getContext().unregisterReceiver(ChatClientManager.h(this.a));
        }
    }
}
