package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.CircleComment;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

class fu implements SimpleCallBack {
    final /* synthetic */ CircleComment a;
    final /* synthetic */ CircleArticleActivity b;

    fu(CircleArticleActivity circleArticleActivity, CircleComment circleComment) {
        this.b = circleArticleActivity;
        this.a = circleComment;
    }

    public void onSuccess(JSONObject jSONObject) {
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已举报评论!").show();
        this.b.d.remove(this.a);
        this.b.e.remove(this.a);
        if (this.b.d.size() + this.b.e.size() == 0) {
            this.b.m.setImgAndTextViewGone();
            this.b.Z.set(UIHelper.getCommentEmptyImg(), "暂无评论，快来抢地主吧~");
            this.b.Z.show();
            this.b.g.addFooterView(this.b.m);
        }
        this.b.c.notifyDataSetChanged();
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str).show();
    }
}
