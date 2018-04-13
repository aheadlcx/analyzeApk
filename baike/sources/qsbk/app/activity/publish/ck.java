package qsbk.app.activity.publish;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import java.util.TimerTask;
import qsbk.app.QsbkApp;

class ck extends TimerTask {
    final /* synthetic */ QiushiPublishTask a;

    ck(QiushiPublishTask qiushiPublishTask) {
        this.a = qiushiPublishTask;
    }

    public void run() {
        Intent intent = new Intent();
        intent.putExtra("sendData", this.a.a);
        intent.setAction("_KEY_PUBLISH_ARTICLE_SUCC_");
        LocalBroadcastManager.getInstance(QsbkApp.getInstance()).sendBroadcast(intent);
    }
}
