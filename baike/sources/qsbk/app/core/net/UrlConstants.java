package qsbk.app.core.net;

import android.text.TextUtils;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.PreferenceUtils;

public class UrlConstants {
    public static final String ACCOUNT_SWITCH = (API_DOMAIN + "/v1/user/alteracc");
    public static final String ADMIN_BLOCK = (LIVE_DOMAIN + "/admin/user/block");
    public static final String ADMIN_CLOSE = (LIVE_DOMAIN + "/admin/user/close");
    public static final String ADMIN_MUTE = (LIVE_DOMAIN + "/admin/user/mute");
    public static final String ADMIN_WARN = (LIVE_DOMAIN + "/admin/user/systemmsg");
    public static final String ADMIN_WEIGHT = (LIVE_DOMAIN + "/admin/user/weight");
    public static final String ALI_PAY = "https://pay.qiushibaike.com/ali_charge/%1$s/%2$s/%3$s";
    public static final String ALI_PAY_QIUBAI = "https://live.app-remix.com/ali_charge";
    public static final String ALI_SET_PROCESSING = "https://pay.qiushibaike.com/set_processing/%1$s/%2$s";
    public static final String API_DOMAIN = (NET_PROTOCOL + "api" + DOMAIN);
    public static final String APP_LAUCH_REPORT = (API_DOMAIN + "/v1/launch");
    public static final String AUTH_CODE = (API_DOMAIN + "/v1/thirdparty/smscode");
    public static final String AUTH_CODE_CORRECT = (API_DOMAIN + "/v1/thirdparty/checkcode");
    public static final String AUTH_IS_VERIFIED = (API_DOMAIN + "/v1/realnameverify/isverified");
    public static final String AUTH_MANUAL_PHOTO = (API_DOMAIN + "/v1/realnameverify/updatephoto");
    public static final String AUTH_NOTICE = (STATIC_DOMAIN + "/html/authen.html");
    public static final String AUTH_VERIFY = (API_DOMAIN + "/v1/realnameverify/doverify");
    public static final String BIND_ACCOUNT = (API_DOMAIN + "/v1/user/bind");
    public static final String BIND_PUSH_TOEKN = (API_DOMAIN + "/v1/push/bind");
    public static final String BIND_PUSH_USER_BIND = (API_DOMAIN + "/v1/push/user/bind");
    public static final String BLACKLIST = (API_DOMAIN + "/v1/user/blocklist");
    public static final String BLACKLIST_ADD = (API_DOMAIN + "/v1/user/%s/block");
    public static final String BLACKLIST_DELETE = (API_DOMAIN + "/v1/user/%s/unblock");
    public static final String CHANNEL = (API_DOMAIN + "/v1/tag/get");
    public static final String CHANNEL_CREATE = (API_DOMAIN + "/v1/tag/add");
    public static final String CHANNEL_DETAIL = (API_DOMAIN + "/v1/tag/detail");
    public static final String CHANNEL_MODIFY = (API_DOMAIN + "/v1/tag/modify");
    public static final String CHANNEL_SEARCH = (API_DOMAIN + "/v1/tag/search");
    public static final String CHECK_NEW = (API_DOMAIN + "/v1/user/index/checknew");
    public static final String CHECK_NEW_FOLLOW = (API_DOMAIN + "/v1/user/follow/checknew");
    public static final String CHECK_NEW_FOLLOW_LIVE_VIDEO = (API_DOMAIN + "/v1/user/follow/checkfeed");
    public static final String CITY_LIST = (API_DOMAIN + "/v1/init/citylist");
    public static final String COMMENT = (API_DOMAIN + "/v1/pic/%1$s/comment/add");
    public static final String COMMENTS = (API_DOMAIN + "/v1/pic/%1$s/comment/list");
    public static final String DELETE_LIVE = (API_DOMAIN + "/v1/live/stream/delete");
    public static final String DELETE_MY_COMMENT = (API_DOMAIN + "/v1/pic/%1$s/comment/%2$s/delete");
    public static final String DELETE_VIDOE = (API_DOMAIN + "/v1/pic/%1$s/delete");
    public static final String DOLL_BALANCE = "https://catchlive.app-remix.com/doll/user/balance";
    public static final String DOLL_CHECK_INVITE_CODE = "https://catchapi.app-remix.com/v1/doll/checkInviteCode";
    public static final String DOLL_CONFIG = "https://catchlive.app-remix.com/doll/config";
    public static final String DOLL_DOMAIN = "https://catchapi.app-remix.com";
    public static final String DOLL_FEEDBACK = "https://catchapi.app-remix.com/v1/doll/web/feedback?round_id=0";
    public static final String DOLL_GET_INVITE_CODE = "https://catchapi.app-remix.com/v1/doll/getMyInviteCode";
    public static final String DOLL_GET_SHARE_TEXT = "https://catchapi.app-remix.com/v1/doll/sharetext";
    public static final String DOLL_HELP = "https://static.app-remix.com/html/dollHelp.html";
    public static final String DOLL_HISTORY = "https://catchlive.app-remix.com/game/doll/catchrecord";
    public static final String DOLL_LIVE_DETAIL = "https://catchapi.app-remix.com/v1/live/stream/detail";
    public static final String DOLL_LIVE_DOMAIN = "https://catchlive.app-remix.com";
    public static final String DOLL_LIVE_ROOM_SELECT = "https://catchlive.app-remix.com/select";
    public static final String DOLL_LIVE_USER_AVATAR = "https://catchlive.app-remix.com/user/avatar";
    public static final String DOLL_LOGOUT = "https://catchapi.app-remix.com/v1/doll/user/logout";
    public static final String DOLL_MESSAGE_LIST = "https://catchapi.app-remix.com/v1/doll/notice/get_list_v1";
    public static final String DOLL_MESSAGE_UNREAD = "https://catchapi.app-remix.com/v1/doll/notice/unread";
    public static final String DOLL_PAY_ALI = "https://catchlive.app-remix.com/doll/ali/charge";
    public static final String DOLL_RECORD_DETAIL = "https://catchapi.app-remix.com/v1/doll/web/recorddetail?roundid=%s&catch=1";
    public static final String DOLL_SIGNIN = "https://catchapi.app-remix.com/v1/doll/signin";
    public static final String DOLL_UPDATE = "https://catchapi.app-remix.com/v1/doll/version";
    public static final String DOLL_USER_PAGE_INFO = "https://catchapi.app-remix.com/v1/doll/user/pandect";
    public static final String DOLL_USER_POLICY = "https://static.app-remix.com/html/dollPolicy.html";
    public static final String DOMAIN = ".app-remix.com";
    public static final String FAMILY_ANCHOR_SUPPORT = (LIVE_DOMAIN + "/family/support/list");
    public static final String FAMILY_APPLY = (LIVE_DOMAIN + "/v1/family/enter/request");
    public static final String FAMILY_BUGLE = (LIVE_DOMAIN + "/family/bugle");
    public static final String FAMILY_CARD = (LIVE_DOMAIN + "/family/member/checkin");
    public static final String FAMILY_CREATE = (LIVE_DOMAIN + "/v1/family/create");
    public static final String FAMILY_CREATE_APPLY = (LIVE_DOMAIN + "/family/create/check");
    public static final String FAMILY_CREATE_CREST_TOKEN = (LIVE_DOMAIN + "/v1/family/crest/create");
    public static final String FAMILY_CREATE_DIAMOND = (LIVE_DOMAIN + "/family/create/check");
    public static final String FAMILY_DETAIL = (LIVE_DOMAIN + "/v1/family/detail");
    public static final String FAMILY_EXIT = (LIVE_DOMAIN + "/v1/family/exit");
    public static final String FAMILY_EXPEL = (LIVE_DOMAIN + "/v1/family/expel");
    public static final String FAMILY_GATHER_RECORD = (LIVE_DOMAIN + "/family/gather/record");
    public static final String FAMILY_MEMBER_WEEK_RANK = (LIVE_DOMAIN + "/family/member/week/rank");
    public static final String FAMILY_MY_DATA = (LIVE_DOMAIN + "/family/myfamily/rank");
    public static final String FAMILY_PROCESS = (LIVE_DOMAIN + "/v1/family/enter/process");
    public static final String FAMILY_UPDATE = (LIVE_DOMAIN + "/v1/family/modify");
    public static final String FAMILY_UPDATE_CREST_TOKEN = (LIVE_DOMAIN + "/v1/family/crest/update");
    public static final String FEEDBACK = (API_DOMAIN + "/v1/feedback/add");
    public static final String FOLLOW = (API_DOMAIN + "/v1/feed/follow");
    public static final String FOLLOWVIDEO = (API_DOMAIN + "/v1/user/follow/pic/list");
    public static final String FOLLOW_CHANNEL = (API_DOMAIN + "/v1/tag/follow");
    public static final String FOLLOW_LIVE_VIDEO = (API_DOMAIN + "/v1/user/follow/feed/list");
    public static final String FQA = (STATIC_DOMAIN + "/html/feedback.html");
    public static final String GAME_FANFANLE_HISTORY = (LIVE_DOMAIN + "/game/userbethistory");
    public static final String GAME_ROLLTABLE_HISTORY = (LIVE_DOMAIN + "/game/turntablerecord");
    public static final String GET_BALANCE = (LIVE_DOMAIN + "/user/balance");
    public static final String GET_CODE = (API_DOMAIN + "/v1/user/phone/getcode");
    public static final String GET_DOLL_LIST = "https://catchapi.app-remix.com/v1/doll/live/stream/list";
    public static final String GET_DOLL_RECORD = "https://catchapi.app-remix.com/v1/doll/web/recordList";
    public static final String GET_DOLL_SUCCESS_RECORD = "https://catchapi.app-remix.com/v1/doll/user/successList";
    public static final String GET_LIVE = (API_DOMAIN + "/v1/live/stream/detail");
    public static final String GET_LIVE_LIST = (API_DOMAIN + "/v1/live/stream/list");
    public static final String GET_SHARE = (LIVE_DOMAIN + "/user/share");
    public static final String HIDE_CHANNEL_VIDOE = (API_DOMAIN + "/v1/tag/hide");
    public static final String HOT = (API_DOMAIN + "/v1/feed/index");
    public static final String HOT_ALPHA = (API_DOMAIN + "/v1/feed/index_alpha");
    public static final String HOT_BETA = (API_DOMAIN + "/v1/feed/index_beta");
    public static final String HOT_CHARLIE = (API_DOMAIN + "/v1/feed/index_charlie");
    public static final String HTTPDNS = (API_DOMAIN + "/v1/config/httpdns");
    public static final String IMAGE_DOMAIN = (NET_PROTOCOL + "pic" + DOMAIN);
    public static final String INSPECT_LIST = (API_DOMAIN + "/v1/inspect/list");
    public static final String LIKE = (API_DOMAIN + "/v1/pic/%1$s/like");
    public static final String LIVE_ADMIN_ADD = (LIVE_DOMAIN + "/user/manager/add");
    public static final String LIVE_ADMIN_CANCEL = (LIVE_DOMAIN + "/user/manager/cancel");
    public static final String LIVE_ADMIN_LIST = (LIVE_DOMAIN + "/user/manager/list");
    public static final String LIVE_BAG = (LIVE_DOMAIN + "/user/backpack");
    public static final String LIVE_BAG_EQUIP = (LIVE_DOMAIN + "/user/backpack/enable/effect");
    public static final String LIVE_COVER_DATA = (API_DOMAIN + "/v1/anchorcover/cover/data");
    public static final String LIVE_COVER_UPLOAD = (API_DOMAIN + "/v1/anchorcover/cover/upload");
    public static final String LIVE_DOLL_HISTORY = (LIVE_DOMAIN + "/game/doll/catchrecord");
    public static final String LIVE_DOMAIN = (NET_PROTOCOL + "live" + DOMAIN);
    public static final String LIVE_DOMAIN_HTTPS = "https://live.app-remix.com";
    public static final String LIVE_DOWNLOAD_WEB = (STATIC_DOMAIN + "/html/tixian-start.html");
    public static final String LIVE_GAME_GUESS_SHARE = (LIVE_DOMAIN + "/v1/happyguess/share");
    public static final String LIVE_GAME_HISTORY_HLNB = (LIVE_DOMAIN + "/game/happycow/lotteryrecord");
    public static final String LIVE_GAME_HISTORY_YPDX = (LIVE_DOMAIN + "/game/lotteryrecord");
    public static final String LIVE_GAME_MVP = (LIVE_DOMAIN + "/game/gameranktop");
    public static final String LIVE_GAME_PERMISSION = (LIVE_DOMAIN + "/game/anchorgameids");
    public static final String LIVE_GIFT_RANK = (LIVE_DOMAIN + "/user/coupon/rank");
    public static final String LIVE_GIFT_RANK_WEEK = (LIVE_DOMAIN + "/user/coupon/rank/week");
    public static final String LIVE_LEVEL_GIFT_CHECK = (LIVE_DOMAIN + "/v1/treasurebox/check");
    public static final String LIVE_LEVEL_GIFT_PAY = (LIVE_DOMAIN + "/v1/treasurebox/pay");
    public static final String LIVE_LEVEL_GIFT_PAY_NOTIFY = (LIVE_DOMAIN + "/v1/treasurebox/status/notify/payed");
    public static final String LIVE_MARKET = (LIVE_DOMAIN + "/user/backpack/mall");
    public static final String LIVE_MARKET_BUY = (LIVE_DOMAIN + "/v2/user/backpack/pay/effect");
    public static final String LIVE_MARKET_PRICE = (LIVE_DOMAIN + "/v2/user/backpack/effect/price");
    public static final String LIVE_MYRANK_RECEIVE_WEEK = (LIVE_DOMAIN + "/coupon/receive/myrank/week");
    public static final String LIVE_MYRANK_SEND_WEEK = (LIVE_DOMAIN + "/coupon/send/myrank/week");
    public static final String LIVE_MYRANK_TOTAL = (LIVE_DOMAIN + "/user/coupon/myrank");
    public static final String LIVE_MYRANK_WEEK = (LIVE_DOMAIN + "/user/coupon/myrank/week");
    public static final String LIVE_RANK_FAMILY_WEEK = (LIVE_DOMAIN + "/family/week/rank");
    public static final String LIVE_RANK_RECEIVE_TOTAL = (LIVE_DOMAIN + "/coupon/receive/rank");
    public static final String LIVE_RANK_RECEIVE_WEEK = (LIVE_DOMAIN + "/coupon/receive/rank/week");
    public static final String LIVE_RANK_SEND_TOTAL = (LIVE_DOMAIN + "/coupon/send/rank");
    public static final String LIVE_RANK_SEND_WEEK = (LIVE_DOMAIN + "/coupon/send/rank/week");
    public static final String LIVE_RED_ENVELOPES_RECORD = (LIVE_DOMAIN + "/redpackage/recv/list");
    public static final String LIVE_REPORT = (LIVE_DOMAIN + "/user/report");
    public static final String LIVE_ROOM_ACCESS = "ws://%s/access";
    public static final String LIVE_ROOM_ACCESS_WSS = "wss://%s/access";
    public static final String LIVE_ROOM_CREATE = (LIVE_DOMAIN + "/create");
    public static final String LIVE_ROOM_SOURCE = (LIVE_DOMAIN + "/select");
    public static final String LIVE_RULE = (STATIC_DOMAIN + "/html/rule.html");
    public static final String LIVE_START_UP_WINDOW = (LIVE_DOMAIN + "/v1/startup/popupWindow");
    public static final String LIVE_TEST_DOMAIN = (NET_PROTOCOL + "livetest1" + DOMAIN);
    public static final String LIVE_TEST_DOMAIN_HTTPS = (NET_PROTOCOL + "livetest1" + DOMAIN);
    public static final String LIVE_USER_AVATAR = (LIVE_DOMAIN + "/user/avatar");
    public static final String LIVE_USER_INFO = (LIVE_DOMAIN + "/user/info");
    public static final String LIVE_USER_SILENT = (API_DOMAIN + "/v1/live/user/%1$s/silent");
    public static final String LOGIN_BY_USERID = (API_DOMAIN + "/v1/user/%1$s/token");
    public static final String LOGIN_PAGE_VIDEO = (API_DOMAIN + "/v1/config/loginpagevideo");
    public static final String LOGOUT = (API_DOMAIN + "/v1/user/logout");
    public static final String MESSAGE = (API_DOMAIN + "/v1/notice/get_list_v1");
    public static final String MESSAGES = (API_DOMAIN + "/v1/notice/get_list");
    public static final String MESSAGE_DRAWER_FOLLOWS = (API_DOMAIN + "/v1/notice/follows");
    public static final String MESSAGE_DRAWER_REWARDS = (API_DOMAIN + "/v1/notice/article_rewards");
    public static final String MESSAGE_DRAWER_VOTES = (API_DOMAIN + "/v1/notice/votes");
    public static final String MESSAGE_FAMILY = (LIVE_DOMAIN + "/v1/family/notice");
    public static final String MESSAGE_UNREAD = (API_DOMAIN + "/v1/notice/unread");
    public static final String MOBILE_FORGET_PWD = (API_DOMAIN + "/v1/user/phone/retrieve");
    public static final String MOBILE_INFO_COMPLETE = (API_DOMAIN + "/v1/user/phone/update");
    public static final String MOBILE_PHONE_BIND = (API_DOMAIN + "/v1/userphonebind/userphonebindData");
    public static final String MOBILE_PHONE_BIND_CODE = (API_DOMAIN + "/v1/userphonebind/getcode");
    public static final String MOBILE_PHONE_BIND_STATUS = (API_DOMAIN + "/v1/userphonebind/isbound");
    public static final String MOBILE_SIGNIN = (API_DOMAIN + "/v1/user/phone/signin");
    public static final String MOBILE_SIGNUP = (API_DOMAIN + "/v1/user/phone/signup");
    public static final String MUSIC_BANNER = (API_DOMAIN + "/v1/mlib/banner");
    public static final String MUSIC_CAN_USE = (API_DOMAIN + "/v1/song/canuse");
    public static final String MUSIC_DETAIL_HOT_VIDEO = (API_DOMAIN + "/v1/song/pic/hot");
    public static final String MUSIC_DETAIL_NEW_VIDEO = (API_DOMAIN + "/v1/song/pic/new");
    public static final String MUSIC_LIB = (API_DOMAIN + "/v1/mlib/get");
    public static final String NEARBY = (API_DOMAIN + "/v1/feed/nearby");
    public static String NET_PROTOCOL = "https://";
    public static final String NEWEST = (API_DOMAIN + "/v1/feed/hot");
    public static final String NEW_LIVE_PUSH = (API_DOMAIN + "/v1/live/stream/new");
    public static final String PALYTIMES_VALIDATION = (API_DOMAIN + "/v1/pic/mystatistics");
    public static final String PAY_DOMAIN = "https://pay.qiushibaike.com";
    public static final String PAY_TEST_DOMAIN = "https://pay.qiushibaike.com/paytest";
    public static final String PROTOCOL_HTTP = "http://";
    public static final String PROTOCOL_HTTPS = "https://";
    public static final String QSBK_CONVERT_UID = (LIVE_DOMAIN + "/user/idmap");
    public static final String QSBK_DOMAIN = ".qiushibaike.com";
    public static final String RECOMMEND_BATCH_FOLLOW = (LIVE_DOMAIN + "/user/batchfollow");
    public static final String RECOMMEND_FOLLOW_LIST = (API_DOMAIN + "/v1/user/newrecommend");
    public static final String REMIX_CONFIG = (LIVE_DOMAIN + "/remix_config_v2");
    public static final String REPORT_COMMENT = (API_DOMAIN + "/v1/pic/%1$s/comment/%2$s/report");
    public static final String REPORT_VIDOE = (API_DOMAIN + "/v1/pic/%1$s/report");
    public static final String SEARCH_MUSIC = (LIVE_DOMAIN + "/music/search");
    public static final String SEARCH_USER = (LIVE_DOMAIN + "/user/search");
    public static final String SHARE_REPORT = (API_DOMAIN + "/v1/pic/%1$s/forward");
    public static final String SIGNIN = (API_DOMAIN + "/v1/user/signin");
    public static final String SIGNUP = (API_DOMAIN + "/v1/user/signup");
    public static final String SONG_DETAIL = (API_DOMAIN + "/v1/song/get");
    public static final String SONG_LIST = (API_DOMAIN + "/v1/mlib/songlist");
    public static final String SONG_RECOMENT_LIST = (API_DOMAIN + "/v1/song/recommend");
    public static final String SPEED_TEST = (API_DOMAIN + "/v1/config/speed/test");
    public static final String STATIC_DOMAIN = (NET_PROTOCOL + "static" + DOMAIN);
    public static final String STREAM_CLOSE = (LIVE_DOMAIN + "/stream/close");
    public static final String STREAM_RECOMMEND = (API_DOMAIN + "/v1/live/stream/recommend");
    public static final String TEST_DOMAIN = (NET_PROTOCOL + "test1" + DOMAIN);
    public static final String TODAY_INCOME = (LIVE_DOMAIN + "/user/withdrawinfo/single");
    public static final String UNBIND_PUSH_TOEKN = (API_DOMAIN + "/v1/push/unbind");
    public static final String UNFOLLOW_CHANNEL = (API_DOMAIN + "/v1/tag/unfollow");
    public static final String UNLIKE = (API_DOMAIN + "/v1/pic/%1$s/unlike");
    public static final String UPDATE = (API_DOMAIN + "/v1/version");
    public static final String UPLOAD_CHANNEL_COVER_TOKEN = (API_DOMAIN + "/v1/upload/tag");
    public static final String UPLOAD_HEAD_TOKEN = (API_DOMAIN + "/v1/upload/head");
    public static final String UPLOAD_VIDEO_TOKEN = (API_DOMAIN + "/v1/upload/video");
    public static final String USER_CHANNEL = (API_DOMAIN + "/v1/tag/userlist");
    public static final String USER_EDIT = (API_DOMAIN + "/v1/user/modify");
    public static final String USER_EXP = (LIVE_DOMAIN + "/user/expr");
    public static final String USER_FOLLOWED_LIST = (API_DOMAIN + "/v1/user/%1$s/followedlist");
    public static final String USER_FOLLOW_LIST = (API_DOMAIN + "/v1/user/%1$s/followlist");
    public static final String USER_FOLLOW_NEW = (LIVE_DOMAIN + "/user/follow");
    public static final String USER_INFO = (API_DOMAIN + "/v1/user/%1$s");
    public static final String USER_POLICY = (STATIC_DOMAIN + "/html/policy.html");
    public static final String USER_QUERY = (LIVE_DOMAIN + "/user/query");
    public static final String USER_SEARCH = (API_DOMAIN + "/v1/user/search");
    public static final String USER_UNFOLLOW_NEW = (LIVE_DOMAIN + "/user/unfollow");
    public static final String USER_VIDEO = (API_DOMAIN + "/v1/user/%1$s/pic");
    public static final String VIDEO_DETAIL = (API_DOMAIN + "/v1/pic/%1$s");
    public static final String VIDEO_DOMAIN = (NET_PROTOCOL + "video" + DOMAIN);
    public static final String VIDEO_PLAY_COUNT_STATISTICS = (API_DOMAIN + "/v1/pic/mystatistics");
    public static final String VIDEO_RECOMMEND = (API_DOMAIN + "/v1/feed/recommend");
    public static final String VIDEO_REWARD = (API_DOMAIN + "/v1/pic/%s/pay");
    public static final String WEBSOCKET_WSS = "wss://";
    public static final String WECHAT_PAY = "https://pay.qiushibaike.com/weixin_charge/%1$s/%2$s/%3$s";
    public static final String WECHAT_PAY_QIUBAI = "https://live.app-remix.com/weixin_charge";
    public static final String WITHDRAW = "https://live.app-remix.com/user/withdraw";
    public static final String WITHDRAW_DIAMOND = "https://live.app-remix.com/user/exchange";
    public static final String WITHDRAW_DIAMOND_RECORD = "https://live.app-remix.com/user/exchange/record";
    public static final String WITHDRAW_INFO = (LIVE_DOMAIN + "/user/withdrawinfo/total");
    public static final String WITHDRAW_RECORD = "https://live.app-remix.com/user/withdraw/record";
    public static final String WWW_DOMAIN = (NET_PROTOCOL + "www" + DOMAIN);
    private static String a = API_DOMAIN;
    private static String b = LIVE_DOMAIN;
    private static String c = LIVE_DOMAIN_HTTPS;
    private static String d = PAY_DOMAIN;
    private static String e = DOLL_DOMAIN;
    private static String f = DOLL_LIVE_DOMAIN;

    public static void initAllDomains() {
        a = a("server_domain", API_DOMAIN);
        b = a("live_server_domain", LIVE_DOMAIN);
        c = a("live_server_domain_https", LIVE_DOMAIN_HTTPS);
        d = a("pay_domain", PAY_DOMAIN);
        e = a("doll_server_domain", DOLL_DOMAIN);
        f = a("doll_live_server_domain", DOLL_LIVE_DOMAIN);
    }

    public static String getApiDomain() {
        return a;
    }

    public static void setApiDomain(String str) {
        if (a(str)) {
            a = str;
            b("server_domain", str);
        }
    }

    public static String getLiveDomain() {
        return b;
    }

    public static void setLiveDomain(String str) {
        if (a(str)) {
            b = str;
            b("live_server_domain", str);
        }
    }

    public static String getLiveHttpsDomain() {
        return c;
    }

    public static void setLiveHttpsDomain(String str) {
        if (a(str)) {
            c = str;
            b("live_server_domain_https", str);
        }
    }

    public static String getPayDomain() {
        return d;
    }

    public static void setPayDomain(String str) {
        if (a(str)) {
            d = str;
            b("pay_domain", str);
        }
    }

    private static boolean a(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("https://");
    }

    private static String a(String str, String str2) {
        if (AppUtils.getInstance().isTestOnlyChannel()) {
            return PreferenceUtils.instance().getString(str, str2);
        }
        if (!PreferenceUtils.instance().contains(str)) {
            return str2;
        }
        PreferenceUtils.instance().removeKey(str);
        return str2;
    }

    private static void b(String str, String str2) {
        if (AppUtils.getInstance().isTestOnlyChannel()) {
            PreferenceUtils.instance().putString(str, str2);
        }
    }

    public static boolean isInTestEnvironment() {
        return AppUtils.getInstance().isTestOnlyChannel() && getPayDomain().equals(PAY_TEST_DOMAIN);
    }

    public static String getDollApiDomain() {
        return e;
    }

    public static void setDollApiDomain(String str) {
        if (a(str)) {
            e = str;
            b("doll_server_domain", str);
        }
    }

    public static String getDollLiveDomain() {
        return f;
    }

    public static void setDollLiveDomain(String str) {
        if (a(str)) {
            f = str;
            b("doll_live_server_domain", str);
        }
    }
}
