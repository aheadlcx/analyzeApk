package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.Constants;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UpdateHelper;
import qsbk.app.utils.UpdateHelper.SimpleDownLoadListener;

class so implements OnClickListener {
    final /* synthetic */ MainActivity a;

    so(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (TextUtils.isEmpty(DeviceUtils.getSDPath())) {
            ToastAndDialog.makeNegativeToast(this.a, "请插入SD卡", Integer.valueOf(1)).show();
            return;
        }
        String str = Constants.UPDATE_URL;
        File file = new File(Environment.getExternalStorageDirectory(), str.substring(str.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR), str.length()));
        UpdateHelper.getInstance().download(str, file, new SimpleDownLoadListener(this.a, file));
    }
}
