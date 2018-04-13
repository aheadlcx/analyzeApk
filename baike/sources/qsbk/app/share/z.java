package qsbk.app.share;

import android.os.Message;
import android.support.v4.app.NotificationCompat;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpClient;

final class z extends Thread {
    final /* synthetic */ String a;
    final /* synthetic */ Map b;
    final /* synthetic */ ShareUtils c;
    final /* synthetic */ int d;

    z(String str, String str2, Map map, ShareUtils shareUtils, int i) {
        this.a = str2;
        this.b = map;
        this.c = shareUtils;
        this.d = i;
        super(str);
    }

    public void run() {
        Message obtainMessage;
        try {
            int i = new JSONObject(HttpClient.getIntentce().post(String.format(Constants.SHARE_URL, new Object[]{this.a}), this.b)).getInt(NotificationCompat.CATEGORY_ERROR);
            if (i == 0) {
                obtainMessage = this.c.a.obtainMessage(i, this.c.getTarget(this.d));
            } else {
                obtainMessage = this.c.a.obtainMessage(999);
            }
        } catch (QiushibaikeException e) {
            QiushibaikeException qiushibaikeException = e;
            obtainMessage = this.c.a.obtainMessage(999);
            qiushibaikeException.printStackTrace();
        } catch (Exception e2) {
            Exception exception = e2;
            obtainMessage = this.c.a.obtainMessage(999);
            exception.printStackTrace();
        }
        obtainMessage.sendToTarget();
    }
}
