package qsbk.app.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.model.QiushiTopic;
import qsbk.app.utils.Util;

class dr implements OnClickListener {
    final /* synthetic */ QiushiTopic a;
    final /* synthetic */ QiushiTopicCell b;

    dr(QiushiTopicCell qiushiTopicCell, QiushiTopic qiushiTopic) {
        this.b = qiushiTopicCell;
        this.a = qiushiTopic;
    }

    public void onClick(View view) {
        boolean z = this.a.isSubscribed;
        this.b.f.setChecked(z);
        if (QsbkApp.currentUser == null) {
            Context activityOrContext = Util.getActivityOrContext(this.b.f);
            Intent intent = new Intent(activityOrContext, ActionBarLoginActivity.class);
            if (!(activityOrContext instanceof Activity)) {
                intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
            }
            activityOrContext.startActivity(intent);
            return;
        }
        this.b.f.setClickable(false);
        if (this.b.i == null) {
            return;
        }
        if (z) {
            this.b.i.unSubcribe(this.a, this.b);
        } else {
            this.b.i.subcribe(this.a, this.b);
        }
    }
}
