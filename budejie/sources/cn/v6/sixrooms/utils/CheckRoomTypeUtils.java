package cn.v6.sixrooms.utils;

import android.text.TextUtils;
import cn.v6.sixrooms.room.IM.IMMessageLastManager;

public class CheckRoomTypeUtils {
    public static boolean isFamilyRoomType(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong < IMMessageLastManager.SYSTEM_INFOMATION_ID || parseLong > 920000000) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }
}
