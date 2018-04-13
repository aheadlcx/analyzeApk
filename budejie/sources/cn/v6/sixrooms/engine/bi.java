package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.JsonParseUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class bi extends VLAsyncHandler<String> {
    final /* synthetic */ int a;
    final /* synthetic */ UserInfoMessageEngine b;

    bi(UserInfoMessageEngine userInfoMessageEngine, int i) {
        this.b = userInfoMessageEngine;
        this.a = i;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        int i = 0;
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                if ("001".equals(string)) {
                    JSONArray jSONArray;
                    jSONObject = jSONObject.getJSONObject("content");
                    UserInfoBean userInfoBean = new UserInfoBean();
                    userInfoBean.setUid(jSONObject.optString(HistoryOpenHelper.COLUMN_UID));
                    userInfoBean.setAnchorLevel(jSONObject.optInt("wrank"));
                    userInfoBean.setWealthLevel(jSONObject.optInt("crank"));
                    userInfoBean.setUname(jSONObject.optString("alias"));
                    userInfoBean.setIsfollow(jSONObject.optString("isFav"));
                    userInfoBean.setSpeakState(jSONObject.optInt("msgflag"));
                    userInfoBean.setUserpic(jSONObject.optString("userpic"));
                    userInfoBean.setIsGodPic(jSONObject.optInt("istop1"));
                    userInfoBean.setUrid(jSONObject.optString("rid"));
                    userInfoBean.setFriend(jSONObject.optInt("isFriend") != 0);
                    userInfoBean.setWealtlate(jSONObject.optLong("wealtlate"));
                    userInfoBean.setCoin6late(jSONObject.optLong("coin6late"));
                    userInfoBean.setWealthstep(jSONObject.optLong("wealthstep"));
                    userInfoBean.setCoinstep(jSONObject.optLong("coinstep"));
                    userInfoBean.setUserIdentity(jSONObject.optInt("userType"));
                    if (jSONObject.has("vip")) {
                        string = jSONObject.optString("vip");
                        if (JsonParseUtils.isJsonArray(string)) {
                            jSONArray = new JSONArray(string);
                            if (!jSONArray.isNull(0)) {
                                userInfoBean.setVipLevel(jSONArray.getString(0));
                            }
                        }
                    }
                    if (jSONObject.has("redProp")) {
                        string = jSONObject.optString("redProp");
                        if (JsonParseUtils.isJsonArray(string)) {
                            jSONArray = new JSONArray(string);
                            if (!jSONArray.isNull(0)) {
                                userInfoBean.setCardLevel(jSONArray.getString(0));
                            }
                        }
                    }
                    if (jSONObject.has("prop")) {
                        string = jSONObject.optString("prop");
                        if (JsonParseUtils.isJsonArray(string)) {
                            JSONArray jSONArray2 = new JSONArray(string);
                            StringBuilder stringBuilder = new StringBuilder();
                            while (i < jSONArray2.length()) {
                                stringBuilder.append(jSONArray2.getString(i));
                                stringBuilder.append(i != jSONArray2.length() + -1 ? "," : "");
                                i++;
                            }
                            userInfoBean.setProp(stringBuilder.toString());
                        }
                    }
                    this.b.a.handleInfo(userInfoBean, this.a);
                    return;
                }
                this.b.a.handleErrorInfo(string, jSONObject.getString("content"));
            } catch (JSONException e) {
                this.b.a.error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.b.a != null) {
            this.b.a.error(1006);
        }
    }
}
