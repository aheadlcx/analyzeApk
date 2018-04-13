package qsbk.app.activity;

import java.util.Timer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.CircleComment;
import qsbk.app.utils.CircleArticleBus;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

class fl implements SimpleCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ CircleArticleActivity b;

    fl(CircleArticleActivity circleArticleActivity, int i) {
        this.b = circleArticleActivity;
        this.a = i;
    }

    public void onSuccess(JSONObject jSONObject) {
        if (jSONObject.optJSONObject("article") != null) {
            this.b.l = (CircleArticle) CircleArticle.parseJson(jSONObject.optJSONObject("article"));
            this.b.c.setArticle(this.b.l);
            this.b.w();
            this.b.v = SharePreferenceUtils.getSharePreferencesBoolValue("_qiushi_need_show_buddle");
            if (!(this.b.v || this.b.l == null || this.b.l.user == null || this.b.l.user.isAnonymous())) {
                this.b.showBuddleDialog();
                SharePreferenceUtils.setSharePreferencesValue("_qiushi_need_show_buddle", true);
                new Timer().schedule(new fm(this), 2000);
            }
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("comments");
            this.b.o = this.a + 1;
            if (this.a == 1) {
                this.b.c.setAllCount(jSONObject.optInt("total"));
            }
            boolean z = jSONObject.optBoolean("has_more") || jSONObject.optInt("has_more", 0) != 0;
            int length = jSONArray.length();
            if (length != 0 || this.a != 1) {
                int i;
                JSONArray optJSONArray = jSONObject.optJSONArray("hot_comments");
                if (optJSONArray != null) {
                    int length2 = optJSONArray.length();
                    for (i = 0; i < length2; i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            this.b.d.add(CircleComment.newInstance(optJSONObject));
                        }
                    }
                }
                for (i = 0; i < length; i++) {
                    JSONObject optJSONObject2 = jSONArray.optJSONObject(i);
                    if (optJSONObject2 != null) {
                        this.b.e.add(CircleComment.newInstance(optJSONObject2));
                    }
                }
                this.b.c.notifyDataSetChanged();
                this.b.g.setLoadMoreFinished();
                this.b.R = z;
                if (this.b.c.isNormalPage()) {
                    this.b.g.setLoadMoreEnable(z);
                }
                if (this.b.u && this.b.g != null && this.a == 1) {
                    this.b.g.post(new fn(this));
                    this.b.u = false;
                }
                CircleArticleBus.updateArticle(this.b.l, this.b);
            } else if (this.b.c.isNormalPage()) {
                this.b.m.setImgAndTextViewGone();
                this.b.Z.set(UIHelper.getCommentEmptyImg(), "暂无评论，快来抢地主吧~");
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
            if (this.b.w != null) {
                this.b.w.cancel();
            }
            this.b.U.setVisibility(8);
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str).show();
        this.b.g.setLoadMoreFinished();
    }
}
