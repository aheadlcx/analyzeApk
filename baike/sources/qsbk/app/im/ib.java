package qsbk.app.im;

import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.JoinedGroupGetter;

class ib implements SimpleCallBack {
    final /* synthetic */ String a;
    final /* synthetic */ IMPreConnector b;

    ib(IMPreConnector iMPreConnector, String str) {
        this.b = iMPreConnector;
        this.a = str;
    }

    public void onSuccess(JSONObject jSONObject) {
        IMPreConnector.a = null;
        JoinedGroupGetter.getJoinedGroupsFromServer(new ic(this));
    }

    public void onFailure(int i, String str) {
        onSuccess(null);
    }
}
