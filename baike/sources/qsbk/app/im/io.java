package qsbk.app.im;

import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class io implements HttpCallBack {
    final /* synthetic */ OfficialSubscribeListActivity a;

    io(OfficialSubscribeListActivity officialSubscribeListActivity) {
        this.a = officialSubscribeListActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (Constants.OFFICIAL_SUBSCRIBE_LIST.equalsIgnoreCase(str)) {
            List linkedList = new LinkedList();
            JSONArray optJSONArray = jSONObject.optJSONArray("subscription");
            for (int i = 0; i < optJSONArray.length(); i++) {
                linkedList.add(new OfficialSubscribeListItem(optJSONArray.optJSONObject(i)));
            }
            this.a.d.replaceItem(linkedList);
        }
        this.a.c.setVisibility(8);
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
        this.a.c.setVisibility(8);
    }
}
