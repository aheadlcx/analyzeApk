package cn.v6.sixrooms.utils;

import android.content.Context;
import android.view.View;
import cn.v6.sixrooms.bean.UserBean;

public class GlobleValue {
    public static final String KEYNAME_RTMPURL = "RTMP";
    public static final int RECONNECTCHAT = 1;
    public static String RESULT_BACK_FROM_PERSONAL = null;
    public static final String RTMPURL_PARAMS = " live=1 swfUrl=http://v.6.cn pageUrl=http://v.6.cn";
    private static UserBean a;
    public static int aesKeyNum = 32;
    public static float density;
    public static String forgetUserName = null;
    public static boolean keep_ImSocket_Alive = false;
    public static boolean networkRoomChange = false;
    public static boolean networkRoomHint = false;
    public static String rid = null;
    public static String ruid = null;
    public static boolean status = false;
    public static int totalSize;
    public static View view;
    public static String weixinCode = "";

    public static UserBean getUserBean() {
        if (a == null) {
            a = (UserBean) FileUtil.getBeanFromFile(UserBean.class);
        }
        return a;
    }

    public static void setUserBean(UserBean userBean) {
        a = userBean;
        FileUtil.saveBeanToFile(userBean);
    }

    public static void clearUserBean(Context context) {
        a = null;
        FileUtil.cleanBeanFromFile(context, UserBean.class);
    }

    public static View getView() {
        return view;
    }

    public static void setView(View view) {
        view = view;
    }
}
