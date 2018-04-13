package qsbk.app.slide;

import android.os.Handler;
import android.os.Message;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;

class ad extends Handler {
    final /* synthetic */ SingleArticleFragment a;

    ad(SingleArticleFragment singleArticleFragment) {
        this.a = singleArticleFragment;
    }

    public void handleMessage(Message message) {
        JSONObject jSONObject = (JSONObject) message.obj;
        try {
            String string = jSONObject.getString("articleId");
            QsbkApp.allCollection.remove(string);
            if (jSONObject.getBoolean("collection")) {
                QsbkApp.allCollection.add(string);
            }
            SharePreferenceUtils.setCollections(QsbkApp.allCollection);
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, jSONObject.getString("msg"), Integer.valueOf(0)).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
