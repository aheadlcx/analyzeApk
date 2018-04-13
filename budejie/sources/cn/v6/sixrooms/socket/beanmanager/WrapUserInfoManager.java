package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.bean.WrapUserInfo;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.JsonParseUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WrapUserInfoManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        int i2;
        MessageBean wrapUserInfo = new WrapUserInfo();
        wrapUserInfo.setTypeId(i);
        wrapUserInfo.setNum(jSONObject.getInt("num"));
        ArrayList arrayList = new ArrayList();
        wrapUserInfo.setAllList(arrayList);
        Collection arrayList2 = new ArrayList();
        wrapUserInfo.setAdmList(arrayList2);
        ArrayList arrayList3 = new ArrayList();
        wrapUserInfo.setSafeList(arrayList3);
        ArrayList arrayList4 = new ArrayList();
        wrapUserInfo.setAllAdmList(arrayList4);
        ArrayList arrayList5 = new ArrayList();
        wrapUserInfo.setLivList(arrayList5);
        JSONObject jSONObject2 = jSONObject.getJSONObject("content");
        JSONArray jSONArray = jSONObject2.getJSONArray("all");
        JSONArray jSONArray2 = jSONObject2.getJSONArray("adm");
        JSONArray jSONArray3 = jSONObject2.getJSONArray("safe");
        JSONArray jSONArray4 = jSONObject2.getJSONArray("liv");
        if (jSONObject2.has("ntvsn")) {
            wrapUserInfo.setNtvsn(jSONObject2.getString("ntvsn"));
        }
        if (jSONObject2.has("ntvs")) {
            JSONArray jSONArray5 = jSONObject2.getJSONArray("ntvs");
            ArrayList arrayList6 = new ArrayList();
            for (int i3 = 0; i3 < jSONArray5.length(); i3++) {
                UserInfoBean userInfoBean = (UserInfoBean) JsonParseUtils.json2Obj(jSONArray5.getJSONObject(i3).toString(), UserInfoBean.class);
                userInfoBean.analyze();
                arrayList6.add(userInfoBean);
            }
            wrapUserInfo.setFansCardList(arrayList6);
        }
        List arrayList7 = new ArrayList();
        List arrayList8 = new ArrayList();
        if (jSONArray != null) {
            for (i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i2);
                UserInfoBean userInfoBean2 = new UserInfoBean();
                String valueOf = String.valueOf(jSONObject3.getInt(HistoryOpenHelper.COLUMN_UID));
                userInfoBean2.setUid(valueOf);
                userInfoBean2.setUrid(jSONObject3.getInt("urid"));
                userInfoBean2.setUname(jSONObject3.getString("uname"));
                userInfoBean2.setPriv(jSONObject3.getString(CommonStrs.ROOMINFOENGINE_PRIV));
                if (jSONObject3.has("userpic")) {
                    userInfoBean2.setUserpic(jSONObject3.getString("userpic"));
                }
                userInfoBean2.analyze();
                int userIdentity = userInfoBean2.getUserIdentity();
                if (userIdentity == 7 || userIdentity == 8 || userIdentity == 9 || userIdentity == 10) {
                    arrayList4.add(userInfoBean2);
                    arrayList7.add(valueOf);
                }
                if (userIdentity == 5 || userIdentity == 3) {
                    arrayList5.add(userInfoBean2);
                    arrayList8.add(valueOf);
                }
                arrayList.add(userInfoBean2);
            }
        }
        for (i2 = 0; i2 < jSONArray2.length(); i2++) {
            JSONObject jSONObject4 = jSONArray2.getJSONObject(i2);
            UserInfoBean userInfoBean3 = new UserInfoBean();
            String valueOf2 = String.valueOf(jSONObject4.getInt(HistoryOpenHelper.COLUMN_UID));
            if (!arrayList7.contains(valueOf2)) {
                userInfoBean3.setUid(valueOf2);
                userInfoBean3.setUrid(jSONObject4.getInt("urid"));
                userInfoBean3.setUname(jSONObject4.getString("uname"));
                userInfoBean3.setPriv(jSONObject4.getString(CommonStrs.ROOMINFOENGINE_PRIV));
                if (jSONObject4.has("userpic")) {
                    userInfoBean3.setUserpic(jSONObject4.getString("userpic"));
                }
                userInfoBean3.analyze();
                arrayList2.add(userInfoBean3);
            }
        }
        arrayList4.addAll(arrayList2);
        for (i2 = 0; i2 < jSONArray4.length(); i2++) {
            JSONObject jSONObject5 = jSONArray4.getJSONObject(i2);
            UserInfoBean userInfoBean4 = new UserInfoBean();
            String valueOf3 = String.valueOf(jSONObject5.getInt(HistoryOpenHelper.COLUMN_UID));
            if (!arrayList8.contains(valueOf3)) {
                userInfoBean4.setUid(valueOf3);
                userInfoBean4.setUrid(jSONObject5.getInt("urid"));
                userInfoBean4.setUname(jSONObject5.getString("uname"));
                userInfoBean4.setPriv(jSONObject5.getString(CommonStrs.ROOMINFOENGINE_PRIV));
                if (jSONObject5.has("userpic")) {
                    userInfoBean4.setUserpic(jSONObject5.getString("userpic"));
                }
                userInfoBean4.analyze();
                arrayList5.add(userInfoBean4);
            }
        }
        if (jSONArray3 != null) {
            for (i2 = 0; i2 < jSONArray3.length(); i2++) {
                jSONObject5 = jSONArray3.getJSONObject(i2);
                userInfoBean4 = new UserInfoBean();
                userInfoBean4.setUid(String.valueOf(jSONObject5.getInt(HistoryOpenHelper.COLUMN_UID)));
                userInfoBean4.setUrid(jSONObject5.getInt("urid"));
                userInfoBean4.setUname(jSONObject5.getString("uname"));
                userInfoBean4.setPriv(jSONObject5.getString(CommonStrs.ROOMINFOENGINE_PRIV));
                userInfoBean4.setFlag(jSONObject5.getString("flag"));
                if (jSONObject5.has("userpic")) {
                    userInfoBean4.setUserpic(jSONObject5.getString("userpic"));
                }
                userInfoBean4.analyze();
                arrayList3.add(userInfoBean4);
            }
        }
        return wrapUserInfo;
    }
}
