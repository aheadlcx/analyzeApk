package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLBlock;
import cn.v6.sixrooms.base.VLScheduler;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.utils.JsonParseUtils;
import cn.v6.sixrooms.utils.LoginUtils;
import org.json.JSONArray;
import org.json.JSONObject;

final class ax extends VLBlock {
    final /* synthetic */ JSONObject a;
    final /* synthetic */ RoomInfoEngine b;

    ax(RoomInfoEngine roomInfoEngine, JSONObject jSONObject) {
        this.b = roomInfoEngine;
        this.a = jSONObject;
    }

    protected final void process(boolean z) {
        WrapRoomInfo wrapRoomInfo = new WrapRoomInfo();
        try {
            JSONArray jSONArray = new JSONObject(this.a.getJSONObject("content").getString(StatisticCodeTable.ROOMLIST)).getJSONObject("content").getJSONObject("content").getJSONArray("safe");
            for (int i = 0; i < jSONArray.length(); i++) {
                UserInfoBean userInfoBean = (UserInfoBean) JsonParseUtils.json2Obj(jSONArray.getJSONObject(i).toString(), UserInfoBean.class);
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
            }
            VLScheduler.instance.schedule(0, 0, new ay(this, wrapRoomInfo));
        } catch (Exception e) {
            e.printStackTrace();
            VLScheduler.instance.schedule(0, 0, new az(this));
        }
    }
}
