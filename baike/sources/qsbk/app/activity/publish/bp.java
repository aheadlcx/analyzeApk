package qsbk.app.activity.publish;

import android.support.v4.app.NotificationCompat;
import android.widget.ImageView;
import org.json.JSONObject;
import qsbk.app.R;
import qsbk.app.http.HttpCallBack;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.EventWindow;

class bp implements HttpCallBack {
    final /* synthetic */ PublishActivity a;

    bp(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (!this.a.isFinishing()) {
            if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR, -1) == 0) {
                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                if (optJSONObject != null) {
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("window");
                    this.a.P = EventWindow.parseJson(optJSONObject2);
                    ImageView j = this.a.Q;
                    int i = (this.a.P == null || !EventWindow.JUMP_QB_POST.equals(this.a.P.actionType)) ? 8 : 0;
                    j.setVisibility(i);
                    FrescoImageloader.displayImage(this.a.Q, this.a.P.iconUrl, 0, R.drawable.ic_get_laisee);
                    EventWindow.saveEventWindow(optJSONObject2.toString());
                    return;
                }
                return;
            }
            EventWindow.saveEventWindow("");
        }
    }

    public void onFailure(String str, String str2) {
        EventWindow.saveEventWindow("");
    }
}
