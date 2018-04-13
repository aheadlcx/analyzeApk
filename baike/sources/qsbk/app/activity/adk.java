package qsbk.app.activity;

import android.util.Pair;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class adk implements HttpCallBack {
    final /* synthetic */ TemporaryNoteActivity a;

    adk(TemporaryNoteActivity temporaryNoteActivity) {
        this.a = temporaryNoteActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.a.hideLoading();
        this.a.o.refreshDone();
        this.a.o.loadMoreDone(true);
        this.a.o.setLoadMoreEnable(false);
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("sws_dict");
            this.a.q.clear();
            Iterator keys = jSONObject2.keys();
            boolean z = true;
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                String string = jSONObject2.getString(str2);
                if (Integer.parseInt(string) == 0) {
                    z = false;
                }
                this.a.e.put(Integer.parseInt(str2), Integer.parseInt(string));
                this.a.r = new Pair(Integer.valueOf(Integer.parseInt(str2)), Integer.valueOf(Integer.parseInt(string)));
                this.a.q.add(this.a.r);
            }
            this.a.n.notifyDataSetChanged();
            if (z) {
                this.a.f.setChecked(true);
            } else {
                this.a.f.setChecked(false);
            }
            this.a.o.refreshDone();
            this.a.o.loadMoreDone(true);
            this.a.o.setLoadMoreEnable(false);
        } catch (JSONException e) {
            e.printStackTrace();
            onFailure("", "数据加载失败");
        }
        this.a.d = null;
    }

    public void onFailure(String str, String str2) {
        this.a.hideLoading();
        this.a.o.refreshDone();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
        this.a.d = null;
    }
}
