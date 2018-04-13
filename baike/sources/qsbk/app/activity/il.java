package qsbk.app.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.CircleArticle;
import qsbk.app.nearby.api.LocationHelper;
import qsbk.app.utils.CircleArticleBus;
import qsbk.app.utils.CircleTopicManager;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.ToastAndDialog;

class il implements SimpleCallBack {
    final /* synthetic */ CircleTopicActivity a;

    il(CircleTopicActivity circleTopicActivity) {
        this.a = circleTopicActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "打卡成功！", Integer.valueOf(0)).show();
            this.a.S.setCompoundDrawablesWithIntrinsicBounds(R.drawable.clocked_bottom, 0, 0, 0);
            this.a.S.setText("打卡");
            this.a.R.setClickable(true);
            CircleTopicManager.getInstance().insertTopicToLRU(this.a.g);
            String string = jSONObject.getString("article_id");
            String optString = jSONObject.optString("content");
            CircleArticle circleArticle = new CircleArticle();
            circleArticle.type = 0;
            circleArticle.id = string;
            circleArticle.content = optString;
            if (this.a.g == null || !this.a.g.isAnonymous) {
                circleArticle.distance = "0m";
            } else {
                circleArticle.distance = "";
            }
            if (this.a.g != null) {
                circleArticle.topic = this.a.g;
            }
            if (this.a.g == null || !this.a.g.isAnonymous) {
                circleArticle.user = QsbkApp.currentUser;
            } else {
                circleArticle.user = BaseUserInfo.createAnonymous();
                circleArticle.user.isMe = true;
            }
            circleArticle.createAt = (int) (System.currentTimeMillis() / 1000);
            circleArticle.likeCount = 0;
            circleArticle.commentCount = 0;
            double latitude = LocationHelper.getLatitude();
            double longitude = LocationHelper.getLongitude();
            if (!(latitude == 0.0d || longitude == 0.0d)) {
                circleArticle.latitude = String.valueOf(latitude);
                circleArticle.longitude = String.valueOf(longitude);
            }
            circleArticle.picUrls = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("punches");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                circleArticle.clockedInfo = new long[optJSONArray.length()];
                for (int i = 0; i < optJSONArray.length(); i++) {
                    circleArticle.clockedInfo[i] = optJSONArray.getLong(i);
                }
            }
            CircleArticleBus.newArticle(circleArticle);
        } catch (JSONException e) {
            e.printStackTrace();
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "数据解析出错", Integer.valueOf(0)).show();
            this.a.S.setCompoundDrawablesWithIntrinsicBounds(R.drawable.clocked_bottom, 0, 0, 0);
            this.a.S.setText("打卡");
            this.a.R.setClickable(true);
        } catch (Exception e2) {
            e2.printStackTrace();
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, HttpClient.getLocalErrorStr(), Integer.valueOf(0)).show();
            this.a.S.setCompoundDrawablesWithIntrinsicBounds(R.drawable.clocked_bottom, 0, 0, 0);
            this.a.S.setText("打卡");
            this.a.R.setClickable(true);
        }
    }

    public void onFailure(int i, String str) {
        if (i == 30000) {
            AlertDialog create = new Builder(this.a).setTitle("补充完个人资料，才能完成此操作哦。").setPositiveButton("补充个人资料", new im(this)).setNegativeButton("取消", null).create();
            create.setCanceledOnTouchOutside(true);
            create.show();
        } else {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        }
        this.a.S.setCompoundDrawablesWithIntrinsicBounds(R.drawable.clocked_bottom, 0, 0, 0);
        this.a.S.setText("打卡");
        this.a.R.setClickable(true);
    }
}
