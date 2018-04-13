package cn.v6.sixrooms.engine;

import android.text.TextUtils;
import cn.v6.sixrooms.base.VLBlock;
import cn.v6.sixrooms.base.VLScheduler;
import cn.v6.sixrooms.bean.RoomEventFloatBean;
import cn.v6.sixrooms.bean.RoomEventFloatTwoBean;
import cn.v6.sixrooms.bean.RoomNotice;
import cn.v6.sixrooms.bean.RoomPropConfBean;
import cn.v6.sixrooms.bean.RoominfoBean;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.bean.SuperGMemBean;
import cn.v6.sixrooms.bean.TalentFloatBean;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.bean.WrapUserInfo;
import cn.v6.sixrooms.room.game.MiniGameBean;
import cn.v6.sixrooms.room.statistic.StatiscEvent;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.utils.Html2Text;
import cn.v6.sixrooms.utils.JsonParseUtils;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class au extends VLBlock {
    final /* synthetic */ JSONObject a;
    final /* synthetic */ RoomInfoEngine b;

    au(RoomInfoEngine roomInfoEngine, JSONObject jSONObject) {
        this.b = roomInfoEngine;
        this.a = jSONObject;
    }

    protected final void process(boolean z) {
        try {
            String string;
            int i;
            ArrayList arrayList;
            UserInfoBean userInfoBean;
            WrapRoomInfo wrapRoomInfo = new WrapRoomInfo();
            JSONObject jSONObject = this.a.getJSONObject("content");
            JSONObject jSONObject2 = jSONObject.getJSONObject("roominfo");
            if (jSONObject.has("eventFloatNew")) {
                string = jSONObject.getString("eventFloatNew");
                if (new JsonParser().parse(string).isJsonObject()) {
                    wrapRoomInfo.setEventFloat((RoomEventFloatBean) JsonParseUtils.json2Obj(string, RoomEventFloatBean.class));
                }
            }
            if (jSONObject.has("eventFloatTwo")) {
                string = jSONObject.getString("eventFloatTwo");
                if (new JsonParser().parse(string).isJsonObject()) {
                    wrapRoomInfo.setEventFloatTwo((RoomEventFloatTwoBean) JsonParseUtils.json2Obj(string, RoomEventFloatTwoBean.class));
                }
            }
            if (jSONObject.has("miniGameList")) {
                Object string2 = jSONObject.getString("miniGameList");
                if (!TextUtils.isEmpty(string2)) {
                    JSONArray jSONArray = new JSONArray(string2);
                    List arrayList2 = new ArrayList();
                    for (i = 0; i < jSONArray.length(); i++) {
                        arrayList2.add((MiniGameBean) JsonParseUtils.json2Obj(jSONArray.get(i).toString(), MiniGameBean.class));
                    }
                    wrapRoomInfo.setMiniGameList(arrayList2);
                }
            }
            if (jSONObject.has("miniGameIntro")) {
                wrapRoomInfo.setMiniGameIntro(jSONObject.getString("miniGameIntro"));
            }
            string = null;
            if (jSONObject.has("tplType")) {
                string = jSONObject.getString("tplType");
            }
            wrapRoomInfo.setTplType(string);
            if (jSONObject.has("giftUserConf")) {
                wrapRoomInfo.setGiftUserConf(a(jSONObject));
            }
            this.b.b = ((RoominfoBean) JsonParseUtils.json2Obj(jSONObject2.toString(), RoominfoBean.class));
            wrapRoomInfo.setRoominfoBean(this.b.b);
            RoomInfoEngine.a(this.b, jSONObject.getJSONObject("roomParamInfo"), wrapRoomInfo);
            JSONObject jSONObject3 = new JSONObject(jSONObject.getString(StatisticCodeTable.ROOMLIST));
            JSONObject jSONObject4 = jSONObject3.getJSONObject("content");
            String string3 = jSONObject4.getString("num");
            String string4 = jSONObject4.getString("typeID");
            jSONObject4 = jSONObject3.getJSONObject("content").getJSONObject("content");
            JSONArray jSONArray2 = jSONObject4.getJSONArray("all");
            JSONArray jSONArray3 = jSONObject4.getJSONArray("liv");
            JSONArray jSONArray4 = jSONObject4.getJSONArray("adm");
            JSONArray jSONArray5 = jSONObject4.getJSONArray("safe");
            WrapUserInfo wrapUserInfo = new WrapUserInfo();
            if (jSONObject4.has("ntvsn")) {
                wrapUserInfo.setNtvsn(jSONObject4.getString("ntvsn"));
            }
            if (jSONObject4.has("ntvs")) {
                JSONArray jSONArray6 = jSONObject4.getJSONArray("ntvs");
                arrayList = new ArrayList();
                for (i = 0; i < jSONArray6.length(); i++) {
                    userInfoBean = (UserInfoBean) JsonParseUtils.json2Obj(jSONArray6.getJSONObject(i).toString(), UserInfoBean.class);
                    userInfoBean.analyze();
                    arrayList.add(userInfoBean);
                }
                wrapUserInfo.setFansCardList(arrayList);
            }
            wrapUserInfo.setNum(string3);
            wrapUserInfo.setTypeID(string4);
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            ArrayList arrayList5 = new ArrayList();
            arrayList = new ArrayList();
            ArrayList arrayList6 = new ArrayList();
            for (i = 0; i < jSONArray2.length(); i++) {
                userInfoBean = (UserInfoBean) JsonParseUtils.json2Obj(jSONArray2.getJSONObject(i).toString(), UserInfoBean.class);
                userInfoBean.analyze();
                int userIdentity = userInfoBean.getUserIdentity();
                if (userIdentity == 7 || userIdentity == 8 || userIdentity == 9 || userIdentity == 10) {
                    arrayList6.add(userInfoBean);
                }
                if (userIdentity == 5 || userIdentity == 3) {
                    arrayList4.add(userInfoBean);
                }
                arrayList3.add(userInfoBean);
            }
            for (i = 0; i < jSONArray3.length(); i++) {
                userInfoBean = (UserInfoBean) JsonParseUtils.json2Obj(jSONArray3.getJSONObject(i).toString(), UserInfoBean.class);
                userInfoBean.analyze();
                arrayList4.add(userInfoBean);
            }
            for (i = 0; i < jSONArray4.length(); i++) {
                userInfoBean = (UserInfoBean) JsonParseUtils.json2Obj(jSONArray4.getJSONObject(i).toString(), UserInfoBean.class);
                userInfoBean.analyze();
                arrayList6.add(userInfoBean);
                arrayList5.add(userInfoBean);
            }
            for (i = 0; i < jSONArray5.length(); i++) {
                userInfoBean = (UserInfoBean) JsonParseUtils.json2Obj(jSONArray5.getJSONObject(i).toString(), UserInfoBean.class);
                userInfoBean.analyze();
                if (LoginUtils.getLoginUserBean() != null) {
                    if (userInfoBean.getUid().equals(LoginUtils.getLoginUserBean().getId())) {
                        String badge = userInfoBean.getBadge();
                        if (badge != null && badge.length() > 0) {
                            if (badge.contains("7569")) {
                                wrapRoomInfo.setIsUserSafe("1");
                            } else if (badge.contains("7570")) {
                                wrapRoomInfo.setIsUserSafe("0");
                            } else {
                                wrapRoomInfo.setIsUserSafe("2");
                            }
                        }
                    } else {
                        continue;
                    }
                }
                arrayList.add(userInfoBean);
            }
            wrapUserInfo.setAllList(arrayList3);
            wrapUserInfo.setLivList(arrayList4);
            wrapUserInfo.setAdmList(arrayList5);
            wrapUserInfo.setSafeList(arrayList);
            wrapUserInfo.setAllAdmList(arrayList6);
            wrapRoomInfo.setWrapUserInfo(wrapUserInfo);
            jSONArray2 = new JSONArray(jSONObject.getString(StatiscEvent.ROOMMSG));
            ArrayList arrayList7 = new ArrayList();
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                boolean z2;
                JSONObject jSONObject5 = jSONArray2.getJSONObject(i2);
                if (jSONObject5.has("fmt") && jSONObject5.getInt("fmt") == 1) {
                    z2 = true;
                    i = RoomInfoEngine.a(jSONObject5.getString("content"));
                } else {
                    z2 = false;
                    i = 0;
                }
                RoommsgBean roommsgBean = (RoommsgBean) JsonParseUtils.json2Obj(Html2Text.Html2Text(jSONObject5.toString()), RoommsgBean.class);
                if (roommsgBean != null) {
                    roommsgBean.setRank(i);
                    roommsgBean.setRankFlag(z2);
                }
                arrayList7.add(roommsgBean);
            }
            wrapRoomInfo.setPublicRoommsgBeans(arrayList7);
            wrapRoomInfo.setIsFav(jSONObject.getString("isFav"));
            if (jSONObject.has("isBirth")) {
                wrapRoomInfo.setIsBirth(jSONObject.getString("isBirth"));
            }
            if (jSONObject.has("isTalent")) {
                wrapRoomInfo.setIsTalent(jSONObject.getString("isTalent"));
            }
            if (jSONObject.has("giftType")) {
                wrapRoomInfo.setGiftType(jSONObject.getString("giftType"));
            }
            if (jSONObject.has("talentFloat")) {
                wrapRoomInfo.setTalentFloat((TalentFloatBean) JsonParseUtils.json2Obj(jSONObject.getString("talentFloat"), TalentFloatBean.class));
            }
            if (jSONObject.has("talentFinal")) {
                wrapRoomInfo.setTalentFinal(jSONObject.getString("talentFinal"));
            }
            if (jSONObject.has("isAnchor")) {
                wrapRoomInfo.setIsAnchor(jSONObject.getString("isAnchor"));
            }
            JSONArray jSONArray7 = jSONObject.getJSONArray("notice");
            arrayList4 = new ArrayList();
            for (i = 0; i < jSONArray7.length(); i++) {
                JSONObject jSONObject6 = jSONArray7.getJSONObject(i);
                RoomNotice roomNotice = (RoomNotice) JsonParseUtils.json2Obj(jSONObject6.getJSONObject("content").toString(), RoomNotice.class);
                roomNotice.setId(jSONObject6.getString("id"));
                roomNotice.setPubtime(jSONObject6.getString("pubtime"));
                arrayList4.add(roomNotice);
            }
            wrapRoomInfo.setRoomNotices(arrayList4);
            if (TextUtils.isEmpty(wrapRoomInfo.getIsUserSafe())) {
                wrapRoomInfo.setIsUserSafe("2");
            }
            wrapRoomInfo.setLiveinfoBean(RoomInfoEngine.a(this.b, jSONObject.getJSONObject("liveinfo"), this.b.b.getId()));
            if (jSONObject.has("superGMem")) {
                wrapRoomInfo.setSuperGMem((SuperGMemBean) JsonParseUtils.json2Obj(jSONObject.getString("superGMem"), SuperGMemBean.class));
            }
            if (jSONObject.has("roomPropConf")) {
                wrapRoomInfo.setRoomPropConf((RoomPropConfBean) JsonParseUtils.json2Obj(jSONObject.getString("roomPropConf"), RoomPropConfBean.class));
            }
            VLScheduler.instance.schedule(0, 0, new av(this, wrapRoomInfo));
        } catch (Exception e) {
            e.printStackTrace();
            VLScheduler.instance.schedule(0, 0, new aw(this));
        }
    }

    private static List<UserInfoBean> a(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("giftUserConf");
        List<UserInfoBean> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setUid(jSONArray.getJSONObject(i).getString(HistoryOpenHelper.COLUMN_UID));
            userInfoBean.setUname(jSONArray.getJSONObject(i).getString("alias"));
            arrayList.add(userInfoBean);
        }
        return arrayList;
    }
}
