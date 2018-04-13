package qsbk.app.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;

final class av implements SimpleCallBack {
    final /* synthetic */ boolean a;
    final /* synthetic */ JSONArray b;

    av(boolean z, JSONArray jSONArray) {
        this.a = z;
        this.b = jSONArray;
    }

    public void onSuccess(JSONObject jSONObject) {
        ReportArticle.b = false;
        ReportArticle.a = 0;
        SharePreferenceUtils.remove("_report_article_");
        ReportArticle.mReportArtcicle.clear();
        if (this.a) {
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "举报成功，感谢您的反馈", Integer.valueOf(0)).show();
        }
    }

    public void onFailure(int i, String str) {
        ReportArticle.b = false;
        ReportArticle.report(this.b, this.a);
    }
}
