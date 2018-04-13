package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.bean.PropBean;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.JsonParseUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class PropListEngine {
    private CallBack a;
    private PropListEngine$UserCallBack b;
    private final String[] c;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void result(List<PropBean> list);
    }

    public PropListEngine(CallBack callBack) {
        this.c = new String[]{"常规", "座驾", "守护", "爱心管理", "徽章", "抢星达人"};
        this.a = callBack;
    }

    public PropListEngine() {
        this.c = new String[]{"常规", "座驾", "守护", "爱心管理", "徽章", "抢星达人"};
    }

    public void setUserCallBack(PropListEngine$UserCallBack propListEngine$UserCallBack) {
        this.b = propListEngine$UserCallBack;
    }

    public void getUserPermission(String str, String str2, String str3) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("logiuid", str);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("encpass", str2);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new an(this, str3), UrlStrs.URL_INDEX_INFO + "?padapi=coop-mobile-listProp.php", arrayList);
    }

    public void getPropList(String str, String str2) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("logiuid", str);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("encpass", str2);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new ao(this), UrlStrs.URL_INDEX_INFO + "?padapi=coop-mobile-listProp.php", arrayList);
    }

    static /* synthetic */ PropBean a(PropListEngine propListEngine, PropBean propBean, JSONObject jSONObject, String str, int i) {
        propBean.setGuard_alias(JsonParseUtils.getString(jSONObject, "alias"));
        propBean.setGuard_btm(JsonParseUtils.getString(jSONObject, "btm"));
        propBean.setGuard_etm(JsonParseUtils.getString(jSONObject, "etm"));
        propBean.setGuard_rid(JsonParseUtils.getString(jSONObject, "rid"));
        propBean.setGuard_state(JsonParseUtils.getString(jSONObject, "switch"));
        propBean.setGuard_uid(JsonParseUtils.getString(jSONObject, HistoryOpenHelper.COLUMN_UID));
        propBean.setFlag(JsonParseUtils.getInt(jSONObject, "flag"));
        propBean.setState(str);
        propBean.setTag(propListEngine.c[i - 1]);
        propBean.setTypeTag(i);
        return propBean;
    }
}
