package qsbk.app.activity.publish;

import android.view.View;
import android.view.View.OnClickListener;
import org.eclipse.paho.client.mqttv3.MqttTopic;

class h implements OnClickListener {
    final /* synthetic */ CirclePublishActivity a;

    h(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void onClick(View view) {
        this.a.b.getText().insert(this.a.b.getSelectionEnd(), MqttTopic.MULTI_LEVEL_WILDCARD);
    }
}
