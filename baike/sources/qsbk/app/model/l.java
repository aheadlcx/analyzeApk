package qsbk.app.model;

import android.support.v4.app.NotificationCompat;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.request.ImageRequest;
import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;

final class l implements HttpCallBack {
    l() {
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR, -1) == 0) {
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            if (optJSONObject != null) {
                Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(""), null).subscribe(new m(this, optJSONObject.optJSONObject("window")), UiThreadImmediateExecutorService.getInstance());
                return;
            }
            return;
        }
        EventWindow.saveEventWindow("");
    }

    public void onFailure(String str, String str2) {
        EventWindow.saveEventWindow("");
    }
}
