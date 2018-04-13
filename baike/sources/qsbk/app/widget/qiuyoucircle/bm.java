package qsbk.app.widget.qiuyoucircle;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Environment;
import java.io.File;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.Constants;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UpdateHelper;
import qsbk.app.utils.UpdateHelper.SimpleDownLoadListener;

final class bm implements OnClickListener {
    final /* synthetic */ Context a;

    bm(Context context) {
        this.a = context;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        ToastAndDialog.makeNeutralToast(this.a, "开始下载新版本", Integer.valueOf(1)).show();
        String str = Constants.UPDATE_URL;
        File file = new File(Environment.getExternalStorageDirectory(), str.substring(str.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR), str.length()));
        UpdateHelper.getInstance().download(str, file, new SimpleDownLoadListener(this.a, file));
    }
}
