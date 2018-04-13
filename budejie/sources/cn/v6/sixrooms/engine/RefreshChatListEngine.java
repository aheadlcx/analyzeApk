package cn.v6.sixrooms.engine;

import android.annotation.SuppressLint;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.bean.WrapUserInfo;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.JsonParseUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"HandlerLeak"})
public class RefreshChatListEngine {
    protected static final String TAG = "RefreshChatListEngine";
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void resultInfo(WrapUserInfo wrapUserInfo);
    }

    public RefreshChatListEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void getRoomList(String str, String str2) {
        List arrayList = new ArrayList();
        UrlStrs.URL_ROOM_LIST = UrlStrs.URL_GETROOMLIST_SERVER + "?id=" + str + "&tm=" + str2;
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new ar(this), UrlStrs.URL_ROOM_LIST, arrayList);
    }

    static /* synthetic */ void a(RefreshChatListEngine refreshChatListEngine, JSONObject jSONObject) throws JSONException {
        int i;
        WrapUserInfo wrapUserInfo = new WrapUserInfo();
        JSONObject jSONObject2 = jSONObject.getJSONObject("content");
        JSONObject jSONObject3 = jSONObject2.getJSONObject("content");
        String string = jSONObject2.getString("num");
        String string2 = jSONObject2.getString("typeID");
        JSONArray jSONArray = jSONObject3.getJSONArray("all");
        JSONArray jSONArray2 = jSONObject3.getJSONArray("liv");
        JSONArray jSONArray3 = jSONObject3.getJSONArray("adm");
        JSONArray jSONArray4 = jSONObject3.getJSONArray("safe");
        wrapUserInfo.setNum(string);
        wrapUserInfo.setTypeID(string2);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        for (i = 0; i < jSONArray.length(); i++) {
            UserInfoBean userInfoBean = (UserInfoBean) JsonParseUtils.json2Obj(jSONArray.getJSONObject(i).toString(), UserInfoBean.class);
            userInfoBean.analyze();
            int userIdentity = userInfoBean.getUserIdentity();
            if (userIdentity == 7 || userIdentity == 8 || userIdentity == 9) {
                arrayList5.add(userInfoBean);
            }
            if (userIdentity == 5 || userIdentity == 3) {
                arrayList2.add(userInfoBean);
            }
            arrayList.add(userInfoBean);
        }
        for (i = 0; i < jSONArray2.length(); i++) {
            userInfoBean = (UserInfoBean) JsonParseUtils.json2Obj(jSONArray2.getJSONObject(i).toString(), UserInfoBean.class);
            userInfoBean.analyze();
            arrayList2.add(userInfoBean);
        }
        for (i = 0; i < jSONArray3.length(); i++) {
            userInfoBean = (UserInfoBean) JsonParseUtils.json2Obj(jSONArray3.getJSONObject(i).toString(), UserInfoBean.class);
            userInfoBean.analyze();
            arrayList5.add(userInfoBean);
            arrayList3.add(userInfoBean);
        }
        for (i = 0; i < jSONArray4.length(); i++) {
            userInfoBean = (UserInfoBean) JsonParseUtils.json2Obj(jSONArray4.getJSONObject(i).toString(), UserInfoBean.class);
            userInfoBean.analyze();
            arrayList4.add(userInfoBean);
        }
        wrapUserInfo.setAllList(arrayList);
        wrapUserInfo.setLivList(arrayList);
        wrapUserInfo.setAdmList(arrayList3);
        wrapUserInfo.setSafeList(arrayList4);
        wrapUserInfo.setAllAdmList(arrayList5);
        refreshChatListEngine.a.resultInfo(wrapUserInfo);
    }
}
