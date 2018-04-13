package qsbk.app.fragments;

import android.os.Message;
import android.support.v4.app.NotificationCompat;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.utils.HttpClient;

class fr extends Thread {
    final /* synthetic */ ManangeMyContentsFragment a;

    fr(ManangeMyContentsFragment manangeMyContentsFragment, String str) {
        this.a = manangeMyContentsFragment;
        super(str);
    }

    public void run() {
        String format = String.format(Constants.DELETE_CREATE, new Object[]{this.a.Y.id});
        Message obtainMessage = this.a.Q.obtainMessage();
        try {
            JSONObject jSONObject = new JSONObject(HttpClient.getIntentce().post(format));
            int i = jSONObject.getInt(NotificationCompat.CATEGORY_ERROR);
            obtainMessage.what = i;
            if (i != 0) {
                obtainMessage.obj = "删除失败";
                if (!jSONObject.isNull("err_msg")) {
                    obtainMessage.obj = jSONObject.getString("err_msg");
                }
            }
        } catch (Exception e) {
            obtainMessage.what = 999;
            e.printStackTrace();
        }
        obtainMessage.sendToTarget();
    }
}
