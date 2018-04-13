package qsbk.app.abtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import qsbk.app.QsbkApp;
import qsbk.app.utils.LogUtil;

class a extends BroadcastReceiver {
    final /* synthetic */ SubscribListAbTest a;

    a(SubscribListAbTest subscribListAbTest) {
        this.a = subscribListAbTest;
    }

    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("action");
        String stringExtra2 = intent.getStringExtra(ArticleActionStater.ART_TYPE);
        LogUtil.d("recv stat broadcast:" + stringExtra + "_" + stringExtra2);
        this.a.stat(QsbkApp.mContext, stringExtra + "_" + stringExtra2);
    }
}
