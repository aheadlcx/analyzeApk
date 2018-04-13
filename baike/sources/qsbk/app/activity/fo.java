package qsbk.app.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.CircleComment;
import qsbk.app.utils.CircleUpgradeDialog;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.StringUtils;
import qsbk.app.utils.ToastAndDialog;

class fo implements SimpleCallBack {
    final /* synthetic */ CircleComment a;
    final /* synthetic */ CircleArticleActivity b;

    fo(CircleArticleActivity circleArticleActivity, CircleComment circleComment) {
        this.b = circleArticleActivity;
        this.a = circleComment;
    }

    public void onSuccess(JSONObject jSONObject) {
        int optInt = jSONObject.optInt("score");
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "评论成功" + (optInt > 0 ? "，积分+" + optInt : ""), Integer.valueOf(0)).show();
        optInt = jSONObject.optInt("rank", 0);
        if (optInt > 0 && !this.b.isFinishing()) {
            CircleUpgradeDialog.show(this.b, optInt);
        }
        SharePreferenceUtils.remove(this.b.j());
        SharePreferenceUtils.remove(this.b.k());
        if (!this.b.isFinishing()) {
            this.b.s();
            this.b.m();
            String optString = jSONObject.optString("comment_id");
            if (!StringUtils.isEmpty(optString)) {
                this.a.id = optString;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("comment");
            if (optJSONObject != null) {
                this.a.update(CircleComment.newInstance(optJSONObject));
            }
            CircleArticle circleArticle = this.b.l;
            circleArticle.commentCount++;
            this.b.c.setAllCount(this.b.l.commentCount);
            this.b.z.commentCountView.setText(String.valueOf(this.b.l.commentCount));
            this.b.c.notifyDataSetChanged();
        }
    }

    public void onFailure(int i, String str) {
        if (this.b.e.size() > 0) {
            this.b.e.remove(0);
        }
        this.b.c.notifyDataSetChanged();
        AlertDialog create;
        if (i == 30000) {
            if (!this.b.isFinishing()) {
                create = new Builder(this.b).setTitle("补充完个人资料，才能完成此操作哦。").setPositiveButton("补充个人资料", new fp(this)).setNegativeButton("取消", null).create();
                create.setCanceledOnTouchOutside(true);
                create.show();
            } else {
                return;
            }
        } else if (i != 40006) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str).show();
        } else if (!this.b.isFinishing()) {
            create = new Builder(this.b).setTitle("根据网信办实名验证相关规定，请先绑定手机号，再进行评论").setPositiveButton("绑定手机", new fq(this)).setNegativeButton("取消", null).create();
            create.setCanceledOnTouchOutside(true);
            create.show();
        } else {
            return;
        }
        this.b.i.setClickable(true);
    }
}
