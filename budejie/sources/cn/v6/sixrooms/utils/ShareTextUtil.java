package cn.v6.sixrooms.utils;

import android.text.TextUtils;
import cn.v6.sixrooms.bean.LiveinfoBean;
import cn.v6.sixrooms.bean.RoominfoBean;
import cn.v6.sixrooms.bean.WrapRoomInfo;

public class ShareTextUtil {
    public static String getPicUrl(WrapRoomInfo wrapRoomInfo) {
        RoominfoBean roominfoBean = wrapRoomInfo.getRoominfoBean();
        LiveinfoBean liveinfoBean = wrapRoomInfo.getLiveinfoBean();
        if (TextUtils.isEmpty(liveinfoBean.getSpredPic())) {
            return roominfoBean.getUoption().getPicuser();
        }
        return liveinfoBean.getSpredPic();
    }
}
