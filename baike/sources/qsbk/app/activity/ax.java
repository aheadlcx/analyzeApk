package qsbk.app.activity;

import android.support.v4.app.NotificationCompat;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.http.HttpCallBack;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.utils.ToastAndDialog;

class ax implements HttpCallBack {
    final /* synthetic */ AddQiuYouActivity a;

    ax(AddQiuYouActivity addQiuYouActivity) {
        this.a = addQiuYouActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (Constants.URL_SEARCH_QIUYOU.equalsIgnoreCase(str)) {
            if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR) == 0) {
                JSONObject optJSONObject = jSONObject.optJSONObject("user");
                MyInfoActivity.launch(this.a, String.valueOf(optJSONObject.optInt("id")), MyInfoActivity.FANS_ORIGINS[4], new IMChatMsgSource(5, String.valueOf(optJSONObject.optInt("id")), "来自昵称搜索"));
            } else {
                ToastAndDialog.makeNegativeToast(this.a, "搜索不到糗友：" + this.a.c.getText(), Integer.valueOf(0)).show();
            }
        }
        this.a.a.setVisibility(8);
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeNegativeToast(this.a, str2, Integer.valueOf(0)).show();
        this.a.a.setVisibility(8);
    }
}
