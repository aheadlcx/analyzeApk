package cn.v6.sixrooms.socket.common;

import android.text.TextUtils;
import cn.v6.sdk.sixrooms.base.Base64;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.room.statistic.StatisticValue;
import cn.v6.sixrooms.utils.AppInfoUtils;
import cn.v6.sixrooms.utils.LogUtils;
import com.ali.auth.third.login.LoginConstants;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.tencent.open.SocialConstants;
import com.tencent.stat.DeviceInfo;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SocketUtil {
    public static final String COMMAND_RECEIVE_MESSAGE = "receivemessage";
    public static final String CRLF = "\r\n";
    public static final String FLAG_OK = "001";
    public static final String FLAG_ON_FAST = "201";
    public static final String FLAG_ON_FULL = "102";
    public static final String FLAG_ON_KICK_OUT = "101";
    public static final String FLAG_ON_MISTAKE_OUT = "113";
    public static final String FLAG_ON_NOT_BOUND_MOBILE = "406";
    public static final String FLAG_ON_PARS_ERR = "402";
    public static final String FLAG_ON_RECONNECT = "205";
    public static final String FLAG_ON_SHORTAGE = "105";
    public static final String FLAG_ROB_PACKAGE = "403";
    public static final String KEY_COMMAND = "command";
    public static final String KEY_CONTENT = "content";
    public static final String KEY_ENC = "enc";
    public static final String RESPONSE_SEND_SUCCESS = "send.success";
    public static final String SERVICE_TAG_ALERT_PLAY = "alert_play";
    public static final String SOCKET_TYPE_CHAT = "CHAT_SOCKET";
    public static final String SOCKET_TYPE_IM = "IM_SOCKET";
    public static final String SONG_CALLEDOPE_RATE = "song_calledoperate";
    public static final int TYPEID_1 = 1;
    public static final int TYPEID_101 = 101;
    public static final int TYPEID_102 = 102;
    public static final int TYPEID_105 = 105;
    public static final int TYPEID_107 = 107;
    public static final int TYPEID_108 = 108;
    public static final int TYPEID_109 = 109;
    public static final int TYPEID_112 = 112;
    public static final int TYPEID_113 = 113;
    public static final int TYPEID_114 = 114;
    public static final int TYPEID_117 = 117;
    public static final int TYPEID_119 = 119;
    public static final int TYPEID_1201 = 1201;
    public static final int TYPEID_123 = 123;
    public static final int TYPEID_124 = 124;
    public static final int TYPEID_129 = 129;
    public static final int TYPEID_1304 = 1304;
    public static final int TYPEID_1305 = 1305;
    public static final int TYPEID_1306 = 1306;
    public static final int TYPEID_1309 = 1309;
    public static final int TYPEID_134 = 134;
    public static final int TYPEID_135 = 135;
    public static final int TYPEID_1350 = 1350;
    public static final int TYPEID_138 = 138;
    public static final int TYPEID_139 = 139;
    public static final int TYPEID_1413 = 1413;
    public static final int TYPEID_1501 = 1501;
    public static final int TYPEID_1502 = 1502;
    public static final int TYPEID_1503 = 1503;
    public static final int TYPEID_151 = 151;
    public static final int TYPEID_1514 = 1514;
    public static final int TYPEID_1519 = 1519;
    public static final int TYPEID_1520 = 1520;
    public static final int TYPEID_1521 = 1521;
    public static final int TYPEID_1523 = 1523;
    public static final int TYPEID_1524 = 1524;
    public static final int TYPEID_1525 = 1525;
    public static final int TYPEID_1605 = 1605;
    public static final int TYPEID_1606 = 1606;
    public static final int TYPEID_1607 = 1607;
    public static final int TYPEID_1608 = 1608;
    public static final int TYPEID_161 = 161;
    public static final int TYPEID_163 = 163;
    public static final int TYPEID_1705 = 1705;
    public static final int TYPEID_201 = 201;
    public static final int TYPEID_201001 = 201001;
    public static final int TYPEID_201002 = 201002;
    public static final int TYPEID_3 = 3;
    public static final int TYPEID_301 = 301;
    public static final int TYPEID_305 = 305;
    public static final int TYPEID_306 = 306;
    public static final int TYPEID_403 = 403;
    public static final int TYPEID_404 = 404;
    public static final int TYPEID_407 = 407;
    public static final int TYPEID_408 = 408;
    public static final int TYPEID_409 = 409;
    public static final int TYPEID_413 = 413;
    public static final int TYPEID_414 = 414;
    public static final int TYPEID_415 = 415;
    public static final int TYPEID_416 = 416;
    public static final int TYPEID_430 = 430;
    public static final int TYPEID_431 = 431;
    public static final int TYPEID_503 = 503;
    public static final int TYPEID_504 = 504;
    public static final int TYPEID_509 = 509;
    public static final int TYPEID_510 = 510;
    public static final int TYPEID_511 = 511;
    public static final int TYPEID_512 = 512;
    public static final int TYPEID_513 = 513;
    public static final int TYPEID_518 = 518;
    public static final int TYPEID_533 = 533;
    public static final int TYPEID_534 = 534;
    public static final int TYPEID_535 = 535;
    public static final int TYPEID_536 = 536;
    public static final int TYPEID_537 = 537;
    public static final int TYPEID_538 = 538;
    public static final int TYPEID_701 = 701;
    public static final int TYPEID_810 = 810;
    public static final int TYPEID_811 = 811;
    public static final String T_901_CLIENT = "901";
    public static final String T_ADD_LIVE_SONG = "song_addlivesong";
    public static final String T_DELETE_LIVE_SONG = "song_deletelivesong";
    public static final String T_KICK_SOFA = "prop_seat";
    public static final String T_LOGIN_LOGIN = "login_login";
    public static final String T_MICROCONNECT_REQUEST = "sound_request";
    public static final String T_MICROCONNECT_USER_CLOSED = "sound_userrefuse";
    public static final String T_MICROLIST_REQUEST = "sound_getlist";
    public static final String T_MSG_CHANGZHANFINAL_VOTE = "prop_czfinalvote";
    public static final String T_MSG_CHANGZHAN_VOTE = "msg_changzhanvote";
    public static final String T_MSG_GIFT_LIST_GET = "prop_getprop";
    public static final String T_MSG_PIGPKYELLOWDUCK_GET = "priv_getdefend";
    public static final String T_MSG_PRIVATE = "msg_private";
    public static final String T_MSG_ROOM = "msg_room";
    public static final String T_MSG_SHAREMSG = "msg_sharemsg";
    public static final String T_PRIV_ADD_ADMIN = "priv_setadmin";
    public static final String T_PRIV_ADD_MANAGER = "priv_setroommanager";
    public static final String T_PRIV_ALL_LIST = "priv_alllist";
    public static final String T_PRIV_KILL = "priv_kill";
    public static final String T_PRIV_RECOVER = "priv_recover";
    public static final String T_PRIV_REVOKE_ADMIN = "priv_deladmin";
    public static final String T_PRIV_REVOKE_MANAGER = "priv_delroommanager";
    public static final String T_PRIV_STOPMSG = "priv_stopmsg";
    public static final String T_PROP_FIREWORK = "prop_grabfireworkwealth";
    public static final String T_PROP_FLY_MSG = "prop_flymsg";
    public static final String T_PROP_FREEVOTE = "vote_vote";
    public static final String T_PROP_PROP = "prop_prop";
    public static final String T_PROP_RED_ENVELOPE = "prop_redenvelope";
    public static final String T_PROP_SHORT_FLY_MSG = "prop_shortflymsg";
    public static final String T_ROOM_MINIGAME_CLOSE = "room_minigameclose";
    public static final String T_ROOM_MINIGAME_OPEN = "room_minigameopen";
    public static final String T_SONG_ADD_CALLED = "song_addcalledsong";
    public static final String T_SONG_SHOW_CALLED_LIST = "song_showcalledlist";
    public static final String T_SONG_SHOW_LIVE_LIST = "song_showlivelist";
    public static final String T_UPDATE_LIVE_SONG = "song_updatelivesong";

    public static StringBuffer addZeroForMessageLength(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str.length());
        for (int length = stringBuffer.length(); length < 8; length = stringBuffer.length()) {
            stringBuffer.insert(0, "0");
        }
        return stringBuffer;
    }

    public static StringBuffer packMessage(String str) {
        StringBuffer addZeroForMessageLength = addZeroForMessageLength(str);
        if (addZeroForMessageLength != null) {
            addZeroForMessageLength.append(str);
        }
        return addZeroForMessageLength;
    }

    public static String loginCommand(String str, String str2, String str3) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("command=login\r\n");
        stringBuffer.append("uid=" + str + CRLF);
        stringBuffer.append("encpass=" + str2 + CRLF);
        stringBuffer.append("roomid=" + str3 + CRLF);
        stringBuffer.insert(0, addZeroForMessageLength(stringBuffer.toString()) + CRLF);
        return stringBuffer.toString();
    }

    public static boolean isDigit(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    public static String revertString(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        if (str.endsWith(CRLF)) {
            return str.substring(0, str.length() - 2);
        }
        return str;
    }

    public static boolean isLoginSuccess(String str) {
        if ("47\r\nenc=no\r\ncommand=result\r\ncontent=login.success\r\n".equals(str)) {
            return true;
        }
        return false;
    }

    public static String encryptContent(String str) {
        String replaceAll;
        Exception exception;
        try {
            String str2 = new String(Base64.encodeBase64(ZipUtil.compress(str.getBytes("UTF-8")), false));
            try {
                replaceAll = str2.replaceAll("\\+", "(").replaceAll("/", ")").replaceAll(LoginConstants.EQUAL, "@");
            } catch (Exception e) {
                Exception exception2 = e;
                replaceAll = str2;
                exception = exception2;
                exception.printStackTrace();
                return replaceAll;
            }
        } catch (Exception e2) {
            exception = e2;
            replaceAll = str;
            exception.printStackTrace();
            return replaceAll;
        }
        return replaceAll;
    }

    public static String decryptContent(String str, boolean z) {
        try {
            byte[] decode = android.util.Base64.decode(str.replaceAll("@", LoginConstants.EQUAL).replaceAll("\\)", "/").replaceAll("\\(", "+"), 0);
            if (z) {
                return new String(ZipUtil.inflate(decode));
            }
            return new String(decode);
        } catch (Exception e) {
            Exception exception = e;
            String str2 = str;
            exception.printStackTrace();
            return str2;
        }
    }

    public static String decryptAndDecodeContent(String str, boolean z) {
        return decodeUnicode(decryptContent(str, z));
    }

    public static String sendMessageCommand(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("command=sendmessage\r\n");
        encryptContent(str);
        stringBuffer.append("content=" + encryptContent(str) + CRLF);
        stringBuffer.insert(0, addZeroForMessageLength(stringBuffer.toString()) + CRLF);
        return stringBuffer.toString();
    }

    public static boolean isSendSuccess(String str) {
        if ("46\r\nenc=no\r\ncommand=result\r\ncontent=send.success\r\n".equals(str)) {
            return true;
        }
        return false;
    }

    public static String authKeyCommand(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("encpass", str);
            jSONObject2.put("mobile", 3);
            jSONObject.put("t", "priv_info");
            jSONObject.put("ic", AppInfoUtils.getAppInfo());
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String sendPublicChat(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("r", str2);
            jSONObject2.put("ak", str3);
            jSONObject2.put("m", str);
            jSONObject2.put("ttt", "");
            jSONObject2.put("from_module", StatisticValue.getInstance().getFromFoomPageModule());
            jSONObject2.put("watchid", StatisticValue.getInstance().getWatchid(StatisticCodeTable.ROOM));
            jSONObject.put("t", T_MSG_ROOM);
            jSONObject.put("ic", AppInfoUtils.getAppInfo());
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String sendPublicChatToPerson(String str, String str2, String str3, String str4, String str5, String str6) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("r", str2);
            jSONObject2.put("ak", str3);
            jSONObject2.put("t", str4);
            jSONObject2.put("m", str);
            jSONObject2.put("to", str5);
            jSONObject2.put("torid", str6);
            jSONObject2.put("toid", str4);
            jSONObject2.put("from_module", StatisticValue.getInstance().getFromFoomPageModule());
            jSONObject2.put("watchid", StatisticValue.getInstance().getWatchid(StatisticCodeTable.ROOM));
            jSONObject.put("t", T_MSG_ROOM);
            jSONObject.put("ic", AppInfoUtils.getAppInfo());
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String sendPrivateChat(String str, String str2, String str3, String str4, String str5, String str6) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("r", str2);
            jSONObject2.put("ak", str3);
            jSONObject2.put("t", str4);
            jSONObject2.put("m", str);
            jSONObject2.put("to", str5);
            jSONObject2.put("torid", str6);
            jSONObject2.put("toid", str4);
            jSONObject.put("t", T_MSG_PRIVATE);
            jSONObject.put("ic", AppInfoUtils.getAppInfo());
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String sendRedEnvelope(String str, int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("tuid", str);
            jSONObject2.put("num", i);
            jSONObject.put("t", T_PROP_RED_ENVELOPE);
            jSONObject.put("ic", AppInfoUtils.getAppInfo());
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String sendFreeVote(String str, String str2, int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("cpid", str);
            jSONObject2.put("tuid", str2);
            jSONObject2.put("vnum", i);
            jSONObject.put("t", T_PROP_FREEVOTE);
            jSONObject.put("ic", AppInfoUtils.getAppInfo());
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String sendPrivAllList() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(IXAdRequestInfo.GPS, "");
            jSONObject.put("t", T_PRIV_ALL_LIST);
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String sendFlyText(String str, String str2, String str3, String str4) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("t", str);
            jSONObject2.put("ak", str2);
            jSONObject2.put("r", str3);
            jSONObject2.put("m", str4);
            jSONObject2.put("from_module", StatisticValue.getInstance().getFromFoomPageModule());
            jSONObject.put("t", T_PROP_FLY_MSG);
            jSONObject.put("ic", AppInfoUtils.getAppInfo());
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String sendSmallFlyText(String str, String str2, String str3, String str4) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("t", str);
            jSONObject2.put("ak", str2);
            jSONObject2.put("r", str3);
            jSONObject2.put("m", str4);
            jSONObject2.put("from_module", StatisticValue.getInstance().getFromFoomPageModule());
            jSONObject.put("t", T_PROP_SHORT_FLY_MSG);
            jSONObject.put("ic", AppInfoUtils.getAppInfo());
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String getRobRedPackageCommand(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("redid", str);
            jSONObject.put("t", T_PROP_FIREWORK);
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String sendGift(String str, String str2, String str3, String str4, int i, int i2, String str5) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("t", str);
            jSONObject2.put("ak", str2);
            jSONObject2.put("r", str3);
            jSONObject2.put("i", str4);
            jSONObject2.put(IXAdRequestInfo.AD_COUNT, i);
            jSONObject2.put("f", i2);
            jSONObject2.put("y", 0);
            String str6 = "m";
            if (TextUtils.isEmpty(str5)) {
                str5 = "";
            }
            jSONObject2.put(str6, str5);
            jSONObject2.put("from_module", StatisticValue.getInstance().getFromFoomPageModule());
            jSONObject2.put("watchid", StatisticValue.getInstance().getWatchid(StatisticCodeTable.ROOM));
            jSONObject.put("t", T_PROP_PROP);
            jSONObject.put("ic", AppInfoUtils.getAppInfo());
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String sendAnonymGift(String str, String str2, String str3, String str4, int i, int i2, String str5) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("t", str);
            jSONObject2.put("ak", str2);
            jSONObject2.put("r", str3);
            jSONObject2.put("i", str4);
            jSONObject2.put(IXAdRequestInfo.AD_COUNT, i);
            jSONObject2.put("f", i2);
            jSONObject2.put("y", 0);
            String str6 = "m";
            if (TextUtils.isEmpty(str5)) {
                str5 = "";
            }
            jSONObject2.put(str6, str5);
            jSONObject2.put("anonym", "1");
            jSONObject2.put("from_module", StatisticValue.getInstance().getFromFoomPageModule());
            jSONObject2.put("watchid", StatisticValue.getInstance().getWatchid(StatisticCodeTable.ROOM));
            jSONObject.put("t", T_PROP_PROP);
            jSONObject.put("ic", AppInfoUtils.getAppInfo());
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String loginRedEnvelope(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("encpass", str);
            jSONObject.put("t", "login_login");
            jSONObject.put("ic", AppInfoUtils.getAppInfo());
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String stopMessage(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("u", str);
            jSONObject2.put("r", str2);
            jSONObject.put("t", T_PRIV_STOPMSG);
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String recoverMessage(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("u", str);
            jSONObject2.put("r", str2);
            jSONObject.put("t", T_PRIV_RECOVER);
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String kill(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("u", str);
            jSONObject2.put("r", str2);
            jSONObject.put("t", T_PRIV_KILL);
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String songCalledList(int i, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("p", i);
            jSONObject2.put("tuid", str);
            jSONObject.put("t", T_SONG_SHOW_CALLED_LIST);
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String songLiveList(int i, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("p", i);
            jSONObject2.put("tuid", str);
            jSONObject.put("t", T_SONG_SHOW_LIVE_LIST);
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String addSong(String str, String str2, String str3, String str4) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("mscname", str);
            jSONObject2.put("mscfirst", str2);
            jSONObject2.put("toname", str3);
            jSONObject2.put("tuid", str4);
            jSONObject2.put("from_module", StatisticValue.getInstance().getFromFoomPageModule());
            jSONObject.put("t", T_SONG_ADD_CALLED);
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String sendMicroRequest(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("ak", str);
            jSONObject.put("t", str2);
            jSONObject.put("ic", AppInfoUtils.getAppInfo());
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String sendChangzhanVoteRequest() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("t", T_MSG_CHANGZHAN_VOTE);
            jSONObject2.put("content", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString().subSequence(0, jSONObject.toString().length() - 1) + ", " + jSONObject2.toString().subSequence(1, jSONObject2.toString().length()));
    }

    public static String sendChangzhanFinalVoteRequest(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("t", T_MSG_CHANGZHANFINAL_VOTE);
            jSONObject.put("ic", AppInfoUtils.getAppInfo());
            jSONObject2.put("tuid", str);
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String sendShareRequest(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("t", T_MSG_SHAREMSG);
            jSONObject.put("ic", AppInfoUtils.getAppInfo());
            jSONObject2.put("ak", str);
            jSONObject2.put("stype", str2);
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String sendFreeVoteRequest(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("t", T_PROP_FREEVOTE);
            jSONObject.put("ic", AppInfoUtils.getAppInfo());
            jSONObject2.put("ak", str);
            jSONObject2.put("tuid", str2);
            jSONObject2.put("cpid", str3);
            jSONObject2.put("num", "1");
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String sendPigPkYellowDuckRequest() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("t", T_MSG_PIGPKYELLOWDUCK_GET);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String sendAddLiveSong(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject.put("t", T_ADD_LIVE_SONG);
            jSONObject.put("ic", AppInfoUtils.getAppInfo());
            jSONObject3.put("mscname", str);
            jSONObject3.put("mscfirst", str2);
            jSONArray.put(jSONObject3);
            jSONObject2.put("songs", jSONArray);
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String sendDeleteLiveSong(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("t", T_DELETE_LIVE_SONG);
            jSONObject.put("ic", AppInfoUtils.getAppInfo());
            jSONObject2.put(DeviceInfo.TAG_MID, str);
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String sendUpdateLiveSong(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("t", T_UPDATE_LIVE_SONG);
            jSONObject.put("ic", AppInfoUtils.getAppInfo());
            jSONObject2.put(DeviceInfo.TAG_MID, str);
            jSONObject2.put("mscname", str2);
            jSONObject2.put("mscfirst", str3);
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String sendOpenMiniGame(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("t", T_ROOM_MINIGAME_OPEN);
            jSONObject.put("ic", AppInfoUtils.getAppInfo());
            jSONObject2.put("gameid", str);
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String sendCloseMiniGame(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("t", T_ROOM_MINIGAME_CLOSE);
            jSONObject.put("ic", AppInfoUtils.getAppInfo());
            jSONObject2.put("gameid", str);
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String sendGiftListRequest(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("t", T_MSG_GIFT_LIST_GET);
            jSONObject.put("ic", AppInfoUtils.getAppInfo());
            jSONObject2.put("r", str);
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String disconnectCommand() {
        return "00000020\r\ncommand=disconnect\r\n";
    }

    public static String keepCommand() {
        return sendMessageCommand("noop");
    }

    public static String decodeUnicode(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        Matcher matcher = Pattern.compile("\\\\u([\\S]{4})([^\\\\]*)").matcher(str);
        while (matcher.find()) {
            stringBuffer.append(new Character((char) Integer.parseInt(matcher.group(1), 16)).toString());
            stringBuffer.append(matcher.group(2));
        }
        return stringBuffer.toString();
    }

    public static void main(String[] strArr) {
        decodeUnicode("“就是自信啊啊啊” 被房间管理员 “ツ→☆小样☆←” 禁言！");
    }

    public static String kickSofa(String str, int i, int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("site", i);
            jSONObject2.put("tuid", str);
            jSONObject2.put("num", i2);
            jSONObject2.put("from_module", StatisticValue.getInstance().getFromFoomPageModule());
            jSONObject.put("t", T_KICK_SOFA);
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.d("SocketUtil", "kickSofa: " + jSONObject.toString());
        return sendMessageCommand(jSONObject.toString());
    }

    public static String addManager(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("u", str);
            jSONObject2.put("f", str2);
            jSONObject2.put(SocialConstants.PARAM_ACT, "p");
            jSONObject.put("t", T_PRIV_ADD_MANAGER);
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String revokeManager(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("u", str);
            jSONObject2.put("f", str2);
            jSONObject2.put(SocialConstants.PARAM_ACT, "d");
            jSONObject.put("t", T_PRIV_REVOKE_MANAGER);
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String revokeAdmin(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("u", str);
            jSONObject2.put("f", str2);
            jSONObject2.put(SocialConstants.PARAM_ACT, "d");
            jSONObject.put("t", T_PRIV_REVOKE_ADMIN);
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String addAdmin(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("u", str);
            jSONObject2.put("f", str2);
            jSONObject2.put(SocialConstants.PARAM_ACT, "p");
            jSONObject.put("t", T_PRIV_ADD_ADMIN);
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }

    public static String songCalleoperate(String str, int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("id", str);
            jSONObject2.put("operate", i);
            jSONObject.put("t", SONG_CALLEDOPE_RATE);
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendMessageCommand(jSONObject.toString());
    }
}
