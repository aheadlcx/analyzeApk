package qsbk.app.slide;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import java.util.Timer;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.Article;
import qsbk.app.utils.QiushiArticleBus;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;

class g implements HttpCallBack {
    final /* synthetic */ SingleArticleFragment a;

    g(SingleArticleFragment singleArticleFragment) {
        this.a = singleArticleFragment;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.a.aM = false;
        try {
            if (this.a.getActivity() != null && !this.a.isDetached()) {
                this.a.l = new Article(jSONObject.getJSONObject("article"));
                UIHelper.setSupportAndCommentTextHighlight(this.a.C, this.a.D, this.a.l.getDisplayLaugth(), this.a.l.comment_count, this.a.l.shareCount, false);
                this.a.b(this.a.l);
                this.a.Y = SharePreferenceUtils.getSharePreferencesBoolValue("_qiushi_need_show_buddle");
                if (!(this.a.R || this.a.l == null || TextUtils.isEmpty(this.a.l.login) || this.a.Y)) {
                    this.a.showBuddleDialog();
                    SharePreferenceUtils.setSharePreferencesValue("_qiushi_need_show_buddle", true);
                    new Timer().schedule(new h(this), 2000);
                }
                QiushiArticleBus.updateArticle(this.a.l, null);
                this.a.j();
                this.a.o.load();
                this.a.n.load();
                this.a.p.load();
            }
        } catch (QiushibaikeException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void onFailure(String str, String str2) {
        this.a.aM = false;
        try {
            if (new JSONObject(str2).optInt(NotificationCompat.CATEGORY_ERROR) == 30001) {
                this.a.G();
                return;
            }
            this.a.o.load();
            this.a.n.load();
            this.a.p.load();
        } catch (JSONException e) {
            this.a.o.load();
            this.a.n.load();
            this.a.p.load();
            e.printStackTrace();
        }
    }
}
