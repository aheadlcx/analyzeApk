package qsbk.app.activity;

import android.util.Pair;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class nf implements HttpCallBack {
    final /* synthetic */ GroupMsgActivity a;

    nf(GroupMsgActivity groupMsgActivity) {
        this.a = groupMsgActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.a.hideLoading();
        this.a.h.refreshDone();
        this.a.h.loadMoreDone(true);
        this.a.h.setLoadMoreEnable(false);
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("sws_dict");
            this.a.j.clear();
            Iterator keys = jSONObject2.keys();
            boolean z = true;
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                String string = jSONObject2.getString(str2);
                if (Integer.parseInt(string) == 0) {
                    z = false;
                }
                this.a.d.put(Integer.parseInt(str2), Integer.parseInt(string));
                this.a.k = new Pair(Integer.valueOf(Integer.parseInt(str2)), Integer.valueOf(Integer.parseInt(string)));
                this.a.j.add(this.a.k);
            }
            this.a.g.notifyDataSetChanged();
            if (z) {
                this.a.e.setChecked(true);
            } else {
                this.a.e.setChecked(false);
            }
            this.a.h.refreshDone();
            this.a.h.loadMoreDone(true);
            this.a.h.setLoadMoreEnable(false);
        } catch (JSONException e) {
            e.printStackTrace();
            onFailure("", "数据加载失败");
        }
        this.a.c = null;
    }

    public void onFailure(String str, String str2) {
        this.a.hideLoading();
        this.a.h.refreshDone();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
        this.a.c = null;
    }
}
