package qsbk.app.live.ui;

import android.support.v4.app.NotificationCompat;
import org.json.JSONObject;
import qsbk.app.core.net.upload.IUploadListener;
import qsbk.app.core.utils.ToastUtil;

class ew implements IUploadListener {
    final /* synthetic */ LivePushActivity a;

    ew(LivePushActivity livePushActivity) {
        this.a = livePushActivity;
    }

    public void uploadStat(String str, boolean z, String str2, JSONObject jSONObject) {
        this.a.hideSavingDialog();
        if (jSONObject == null) {
            return;
        }
        if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR) != 0) {
            ToastUtil.Short(jSONObject.optString("err_msg"));
        } else if (z) {
            this.a.showSnackbar("上传成功");
        } else {
            ToastUtil.Short(str2);
        }
    }

    public void uploadProgress(String str, double d) {
    }
}
