package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Environment;
import java.io.File;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.Constants;
import qsbk.app.utils.UpdateHelper;
import qsbk.app.utils.UpdateHelper.SimpleDownLoadListener;

class i implements OnClickListener {
    final /* synthetic */ AboutSettingActivity a;

    i(AboutSettingActivity aboutSettingActivity) {
        this.a = aboutSettingActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        String str = Constants.UPDATE_URL;
        File file = new File(Environment.getExternalStorageDirectory(), str.substring(str.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR), str.length()));
        UpdateHelper.getInstance().download(str, file, new SimpleDownLoadListener(this.a, file));
    }
}
