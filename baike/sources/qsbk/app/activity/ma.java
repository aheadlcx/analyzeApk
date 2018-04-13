package qsbk.app.activity;

import android.util.Pair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class ma implements HttpCallBack {
    final /* synthetic */ GroupInfoActivity a;

    ma(GroupInfoActivity groupInfoActivity) {
        this.a = groupInfoActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.a.j();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("pic_url_list");
            if (jSONArray.length() > 0) {
                Pair url = GroupInfoActivity.getUrl(jSONArray.getString(0));
                if (url == null) {
                    onFailure(str, "返回文件路径格式不正确！");
                    return;
                }
                ToastAndDialog.makePositiveToast(QsbkApp.mContext, "图片上传成功", Integer.valueOf(0)).show();
                this.a.c((String) url.second);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onFailure(String str, String str2) {
        this.a.j();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
    }
}
