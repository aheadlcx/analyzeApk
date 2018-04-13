package qsbk.app.activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.CircleComment;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

class fk implements SimpleCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ CircleArticleActivity b;

    fk(CircleArticleActivity circleArticleActivity, int i) {
        this.b = circleArticleActivity;
        this.a = i;
    }

    public void onSuccess(JSONObject jSONObject) {
        int i = 0;
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            boolean z = jSONObject.optBoolean("has_more") || jSONObject.optInt("has_more", 0) != 0;
            int length = jSONArray.length();
            this.b.p = this.a + 1;
            if (this.a == 1) {
                this.b.c.setOwnerCount(jSONObject.optInt("total"));
                this.b.f.clear();
            }
            if (length != 0 || this.a != 1) {
                while (i < length) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        this.b.f.add(CircleComment.newInstance(optJSONObject));
                    }
                    i++;
                }
                this.b.c.notifyDataSetChanged();
                this.b.g.setLoadMoreFinished();
                this.b.S = z;
                if (!this.b.c.isNormalPage()) {
                    this.b.g.setLoadMoreEnable(z);
                }
            } else if (!this.b.c.isNormalPage()) {
                this.b.m.setImgAndTextViewGone();
                this.b.Z.set(UIHelper.getCommentEmptyImg(), "暂无楼主评论~");
                this.b.Z.show();
                this.b.g.addFooterView(this.b.m);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onFailure(int i, String str) {
        if (i == 30002 || i == 30001) {
            this.b.g.setVisibility(8);
            this.b.X.set(UIHelper.getEmptyImg(), "动态不存在");
            this.b.X.show();
            this.b.h.setVisibility(8);
            this.b.s();
            if (this.b.w != null) {
                this.b.w.cancel();
                return;
            }
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str).show();
        this.b.g.setLoadMoreFinished();
    }
}
