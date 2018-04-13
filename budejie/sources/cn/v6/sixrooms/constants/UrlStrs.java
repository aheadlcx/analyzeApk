package cn.v6.sixrooms.constants;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.utils.LogUtils;
import java.util.Properties;

public class UrlStrs {
    public static String APP_UPDATE_URL;
    public static String CALLBACK_URL;
    public static String DICTIONARY_URL;
    public static String DOMAIN;
    public static String DOMAIN_lOGIN;
    public static String EVENT_FIGHT_SING_INFO;
    public static String EVENT_SHOW_SING_INFO;
    public static String FAMILY_URL;
    public static String FRAME_STATISTICS_URL = "http://shrek.6.cn/live.6.cn/xs.php";
    public static String GET_NOTIFICATION_URL;
    public static String GET_STREAM_IP_URL = "http://rio.6rooms.com/live/";
    public static String GIFT_URL = "http://vr0.6.cn/mobile/android/all_gift_expression_vip_fourth.jpg";
    public static String GOD_USER_NEXT_IMG = "http://v.6.cn/api/godUserNextImg.php";
    public static String GOD_USER_RANK_IMG = "http://v.6.cn/api/godUserRankImg.php";
    public static String LOGIN_CLIENT;
    public static String ROOM_ALL_FANS = "http://v.6.cn/room/getDataActive.php";
    public static String ROOM_FANS;
    public static String ROOM_NOTICE_URL;
    public static String UNKNOW_PORTRAIT = "http://vi0.6rooms.com/imges/new_6/unknow.jpg";
    public static String UPLOAD_FOR_GENERAL = "http://pic.v.6.cn/api/uploadForGeneral.php";
    public static String UPLOAD_FOR_VOICE = "http://uploadmp3.v.6.cn/mp3/uploadVoice.php";
    public static String URL_COOP_ENCYPT = "http://v.6.cn/coop/android/checkDomainName.php";
    public static String URL_GETROOMLIST_SERVER;
    public static String URL_IDENTIFY_CODE;
    public static String URL_INDEX_INFO;
    public static String URL_LOGIN;
    public static String URL_MODIFY_PASSWORD = (URL_SERVICE + "/sso/editProfile.php");
    public static String URL_PREPARE_LOGIN;
    public static String URL_REGISTER;
    public static String URL_RESET_PASSWORD = (URL_SERVICE + "/api/resetPwd.php");
    public static String URL_ROOM_LIST;
    public static String URL_RTMP_ADDRESS;
    public static String URL_SERVICE;
    public static String WEALTH_LEVEL_PIC;
    public static String WEALTH_LEVEL_PIC_NEXT = "http://v.6.cn/api/godUserNextImg.php";
    public static String type;

    static {
        URL_SERVICE = "http://passport.6.cn";
        URL_IDENTIFY_CODE = URL_SERVICE + "/api/getVCK.php";
        URL_PREPARE_LOGIN = URL_SERVICE + "/sso/prelogin.php";
        URL_LOGIN = URL_SERVICE + "/sso/login.php";
        LOGIN_CLIENT = "http://v.6.cn/login_client.php";
        URL_REGISTER = URL_SERVICE + "/sso/register.php";
        URL_INDEX_INFO = "http://v.6.cn/coop/mobile/index.php";
        URL_RTMP_ADDRESS = "http://rio.6rooms.com/live/";
        DOMAIN = "&domain=v.6.cn&callback=callback";
        URL_ROOM_LIST = "";
        URL_GETROOMLIST_SERVER = "http://v.6.cn/room/getRoomList.php";
        ROOM_FANS = "http://v.6.cn/room/getRoomFans.php";
        FAMILY_URL = "http://vi0.6.cn/live/family/";
        ROOM_NOTICE_URL = "http://v.6.cn/room/getRoomNotice.php";
        APP_UPDATE_URL = "http://passport.6.cn/api/mobileDevice/a.php";
        DICTIONARY_URL = "http://v.6.cn/coop/coopLogin.php";
        GET_NOTIFICATION_URL = "http://rio.6rooms.com/aph/";
        DOMAIN_lOGIN = "v.6.cn";
        CALLBACK_URL = "http://v.6.cn/login_test.php";
        WEALTH_LEVEL_PIC = "http://v.6.cn/api/godUserRankImg.php";
        EVENT_SHOW_SING_INFO = "http://v.6.cn/event/shownew/getUserInfo.php";
        EVENT_FIGHT_SING_INFO = "http://v.6.cn/event/getUserInfo.php";
        type = "online";
        Properties properties = new Properties();
        try {
            properties.load(V6Coop.getInstance().getContext().getAssets().open("config.properties"));
            type = properties.getProperty("type");
        } catch (Exception e) {
            LogUtils.e("config", e.getMessage());
        }
        if ("dev".equals(type)) {
            WEALTH_LEVEL_PIC = "http://dev.v.6.cn/api/godUserRankImg.php";
            DOMAIN_lOGIN = "dev.v.6.cn";
            CALLBACK_URL = "http://dev.v.6.cn/login_test.php";
            URL_SERVICE = "http://passport.6.cn";
            URL_IDENTIFY_CODE = URL_SERVICE + "/api/getVCK.php";
            URL_PREPARE_LOGIN = URL_SERVICE + "/sso/prelogin.php";
            URL_LOGIN = URL_SERVICE + "/sso/login.php";
            LOGIN_CLIENT = "http://dev.v.6.cn/login_client.php";
            URL_REGISTER = URL_SERVICE + "/sso/register.php";
            URL_INDEX_INFO = "http://dev.v.6.cn/coop/mobile/index.php";
            URL_RTMP_ADDRESS = "http://rio.6rooms.com/live/";
            DOMAIN = "&domain=dev.v.6.cn&callback=callback";
            URL_ROOM_LIST = "";
            URL_GETROOMLIST_SERVER = "http://dev.v.6.cn/room/getRoomList.php";
            ROOM_FANS = "http://dev.v.6.cn/room/getRoomFans.php";
            FAMILY_URL = "http://vi0.6.cn/live/family/";
            ROOM_NOTICE_URL = "http://dev.v.6.cn/room/getRoomNotice.php";
            APP_UPDATE_URL = "http://passport.6.cn/api/mobileDevice/a.php";
            DICTIONARY_URL = "http://dev.v.6.cn/coop/coopLogin.php";
            GET_NOTIFICATION_URL = "http://rio.6rooms.com/aph/";
            EVENT_SHOW_SING_INFO = "http://dev.v.6.cn/event/shownew/getUserInfo.php";
            EVENT_FIGHT_SING_INFO = "http://dev.v.6.cn/event/getUserInfo.php";
        }
    }
}
