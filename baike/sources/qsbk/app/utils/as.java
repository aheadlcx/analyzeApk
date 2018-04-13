package qsbk.app.utils;

import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.Remark;

class as implements SimpleCallBack {
    final /* synthetic */ RemarkManager a;

    as(RemarkManager remarkManager) {
        this.a = remarkManager;
    }

    public void onSuccess(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                RemarkManager.REMARKS = Remark.parseJsonFromServer(jSONObject.getJSONObject("nicks"));
                if (RemarkManager.REMARKS.size() > 0) {
                    RemarkManager.a(RemarkManager.remarksToJson(RemarkManager.REMARKS));
                }
            } catch (JSONException e) {
                this.a.loadFromLocal();
                e.printStackTrace();
            }
        }
    }

    public void onFailure(int i, String str) {
        this.a.loadFromLocal();
    }
}
