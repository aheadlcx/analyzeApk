package qsbk.app.live.ui;

import android.support.v4.app.NotificationCompat;
import android.widget.TextView;
import com.baidu.mobstat.Config;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.utils.FontUtils;

class ff extends Callback {
    final /* synthetic */ LiveUserLevelActivity a;

    ff(LiveUserLevelActivity liveUserLevelActivity) {
        this.a = liveUserLevelActivity;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("user_id", AppUtils.getInstance().getUserInfoProvider().getUserId() + "");
        return hashMap;
    }

    public void onSuccess(BaseResponse baseResponse) {
        int i = 300;
        if (baseResponse.getSimpleDataInt(NotificationCompat.CATEGORY_ERROR) == 0) {
            int simpleDataInt = baseResponse.getSimpleDataInt("a");
            int simpleDataInt2 = baseResponse.getSimpleDataInt(Config.SESSTION_END_TIME);
            int simpleDataInt3 = baseResponse.getSimpleDataInt("l");
            this.a.d.setMax(simpleDataInt);
            this.a.d.setProgress(simpleDataInt2);
            this.a.s.setText(simpleDataInt2 + MqttTopic.TOPIC_LEVEL_SEPARATOR + simpleDataInt);
            this.a.b.setText("LV." + simpleDataInt3);
            TextView e = this.a.c;
            StringBuilder append = new StringBuilder().append("LV.");
            if (simpleDataInt3 + 1 <= 300) {
                i = simpleDataInt3 + 1;
            }
            e.setText(append.append(i).toString());
            this.a.a.setTypeface(FontUtils.getBloggerSansFontLight());
            e = this.a.a;
            append = new StringBuilder().append("LV.");
            if (simpleDataInt3 == 0) {
                i = 1;
            } else {
                i = simpleDataInt3;
            }
            e.setText(append.append(i).toString());
        }
    }
}
