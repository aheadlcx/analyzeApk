package qsbk.app.share;

import android.content.Context;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.PicUrl;
import qsbk.app.nearby.api.LocationCache.Location;
import qsbk.app.utils.CircleArticleBus;
import qsbk.app.utils.CircleUpgradeDialog;
import qsbk.app.utils.ToastAndDialog;

final class p implements SimpleCallBack {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ String e;
    final /* synthetic */ Location f;
    final /* synthetic */ Context g;

    p(String str, String str2, String str3, String str4, String str5, Location location, Context context) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = location;
        this.g = context;
    }

    public void onSuccess(JSONObject jSONObject) {
        int optInt;
        JSONException e;
        try {
            optInt = jSONObject.optInt("score");
            try {
                String string = jSONObject.getString("article_id");
                CircleArticle circleArticle = new CircleArticle();
                int i = 4;
                if ("link".equals(this.a)) {
                    i = 9;
                } else if ("live".equals(this.a)) {
                    i = 11;
                } else if ("video".equals(this.a)) {
                    i = 8;
                } else if ("web".equals(this.a)) {
                    i = 12;
                } else if ("gif".equals(this.a)) {
                    i = 13;
                }
                circleArticle.type = i;
                circleArticle.id = string;
                circleArticle.content = this.b;
                circleArticle.distance = "0m";
                circleArticle.createAt = (int) (System.currentTimeMillis() / 1000);
                ArrayList arrayList = new ArrayList();
                circleArticle.picUrls = arrayList;
                if (this.c != null) {
                    PicUrl picUrl = new PicUrl(circleArticle.createAt);
                    picUrl.url = this.c;
                    picUrl.status = 2;
                    arrayList.add(picUrl);
                }
                circleArticle.shareLink = this.d;
                circleArticle.shareContent = this.e;
                circleArticle.user = QsbkApp.currentUser;
                circleArticle.likeCount = 0;
                circleArticle.commentCount = 0;
                if (this.f == null) {
                    circleArticle.latitude = String.valueOf(0);
                    circleArticle.longitude = String.valueOf(0);
                } else {
                    circleArticle.latitude = String.valueOf(this.f.latitude);
                    circleArticle.longitude = String.valueOf(this.f.longitude);
                }
                CircleArticleBus.newArticle(circleArticle);
            } catch (JSONException e2) {
                e = e2;
                e.printStackTrace();
                ToastAndDialog.makePositiveToast(QsbkApp.mContext, "发布成功" + (optInt > 0 ? "，积分+" + optInt : ""), Integer.valueOf(0)).show();
                optInt = jSONObject.optInt("rank", 0);
                if (optInt > 0) {
                }
                return;
            }
        } catch (JSONException e3) {
            e = e3;
            optInt = 0;
            e.printStackTrace();
            if (optInt > 0) {
            }
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "发布成功" + (optInt > 0 ? "，积分+" + optInt : ""), Integer.valueOf(0)).show();
            optInt = jSONObject.optInt("rank", 0);
            if (optInt > 0) {
                return;
            }
        }
        if (optInt > 0) {
        }
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "发布成功" + (optInt > 0 ? "，积分+" + optInt : ""), Integer.valueOf(0)).show();
        optInt = jSONObject.optInt("rank", 0);
        if (optInt > 0 && this.g != null) {
            try {
                CircleUpgradeDialog.show(this.g, optInt);
            } catch (Exception e4) {
            }
        }
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
