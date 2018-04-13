package qsbk.app.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.activity.QiushiTopicActivity;
import qsbk.app.model.QiushiTopic;
import qsbk.app.utils.Util;

class du implements OnClickListener {
    final /* synthetic */ QiushiTopic a;
    final /* synthetic */ QiushiTopicRecommendCell b;

    du(QiushiTopicRecommendCell qiushiTopicRecommendCell, QiushiTopic qiushiTopic) {
        this.b = qiushiTopicRecommendCell;
        this.a = qiushiTopic;
    }

    public void onClick(View view) {
        Context activityOrContext = Util.getActivityOrContext(view);
        Intent intent = new Intent(activityOrContext, QiushiTopicActivity.class);
        intent.putExtra("topic", this.a);
        if (!(activityOrContext instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        activityOrContext.startActivity(intent);
    }
}
