package cn.v6.sixrooms.socket.beanmanager;

import cn.v6.sixrooms.bean.MessageBean;
import cn.v6.sixrooms.bean.SysNotificationBean;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class SysNotificationBeanManager extends MessageBeanManager {
    public MessageBean progressMessageBean(JSONObject jSONObject, String str, int i) throws JSONException {
        MessageBean sysNotificationBean = new SysNotificationBean();
        sysNotificationBean.setTm(jSONObject.getLong(IXAdRequestInfo.MAX_TITLE_LENGTH));
        sysNotificationBean.setFrom(jSONObject.getString(UserTrackerConstants.FROM));
        sysNotificationBean.setTo(jSONObject.getString("to"));
        sysNotificationBean.setContent(jSONObject.getString("content"));
        sysNotificationBean.setTypeId(i);
        if (jSONObject.has("fmt") && jSONObject.getInt("fmt") == 1) {
            sysNotificationBean.setRankFlag(true);
            sysNotificationBean.setRank(a(sysNotificationBean.getContent()));
        }
        return sysNotificationBean;
    }

    private static int a(String str) {
        try {
            if (str.contains("rich")) {
                return Integer.parseInt(str.substring(str.indexOf("rich") + 4, str.indexOf("'>")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
