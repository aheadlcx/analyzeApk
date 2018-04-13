package qsbk.app.fragments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.ToastAndDialog;

class gz implements SimpleCallBack {
    final /* synthetic */ MyQiuYouDynamicFragment a;

    gz(MyQiuYouDynamicFragment myQiuYouDynamicFragment) {
        this.a = myQiuYouDynamicFragment;
    }

    public void onSuccess(JSONObject jSONObject) {
        if (this.a.getActivity() != null && !this.a.getActivity().isFinishing()) {
            this.a.c();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("data");
                boolean optBoolean = jSONObject.optBoolean("has_more");
                if (this.a.b == 1) {
                    this.a.c.clear();
                    this.a.d.refreshDone();
                } else {
                    this.a.d.loadMoreDone(true);
                }
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    Object parseJson = CircleArticle.parseJson(jSONArray.optJSONObject(i));
                    if (!(parseJson == null || this.a.c.contains(parseJson))) {
                        this.a.c.add(parseJson);
                    }
                }
                if (optBoolean) {
                    this.a.d.setLoadMoreEnable(true);
                    this.a.p.setVisibility(8);
                } else {
                    this.a.d.setLoadMoreEnable(false);
                    this.a.p.setVisibility(0);
                }
                this.a.b = this.a.b + 1;
                this.a.f.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "数据解析出错", Integer.valueOf(0)).show();
            } catch (Exception e2) {
                e2.printStackTrace();
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, HttpClient.getLocalErrorStr(), Integer.valueOf(0)).show();
            }
            this.a.f();
        }
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
