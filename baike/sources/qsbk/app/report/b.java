package qsbk.app.report;

import android.os.Message;
import android.support.v4.app.NotificationCompat;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.utils.HttpClient;

class b extends Thread {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ ArticleReporter c;

    b(ArticleReporter articleReporter, String str, int i, String str2) {
        this.c = articleReporter;
        this.a = i;
        this.b = str2;
        super(str);
    }

    public void run() {
        Message obtainMessage;
        try {
            Map hashMap = new HashMap();
            hashMap.put("reason", Integer.valueOf(this.a));
            JSONObject jSONObject = new JSONObject(HttpClient.getIntentce().post(String.format(Constants.REPORT_ARTICLE, new Object[]{this.b}), hashMap));
            int i = jSONObject.getInt(NotificationCompat.CATEGORY_ERROR);
            if (i == 0) {
                obtainMessage = this.c.a.obtainMessage(0, "举报成功");
            } else {
                obtainMessage = this.c.a.obtainMessage(i, jSONObject.getString("err_msg"));
            }
        } catch (Exception e) {
            Exception exception = e;
            obtainMessage = this.c.a.obtainMessage(999, "举报失败，请稍后重试");
            exception.printStackTrace();
        }
        this.c.a.sendMessage(obtainMessage);
    }
}
