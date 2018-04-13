package cn.v6.sixrooms.socket.IM;

import cn.v6.sdk.sixrooms.base.Base64;
import cn.v6.sixrooms.socket.common.SocketUtil;
import cn.v6.sixrooms.socket.common.ZipUtil;
import cn.v6.sixrooms.utils.AppInfoUtils;
import com.ali.auth.third.login.LoginConstants;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tencent.open.wpa.WPA;
import com.tencent.stat.DeviceInfo;
import com.umeng.analytics.pro.x;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class IMSocketUtil {
    public static final String COMMAND_RECEIVE_MESSAGE = "receivemessage";
    public static final String INNER_TYPE_ID_DISCONNECT = "503";
    public static final String INNER_TYPE_ID_LOGIC_ERROR = "402";
    public static final String INNER_TYPE_ID_LOGIN_BAD = "502";
    public static final String INNER_TYPE_ID_OK = "001";
    public static final String INNER_TYPE_ID_SYS_ERROR = "401";
    public static final String INNER_TYPE_ID_USER_NO_LOGIN = "501";
    public static final String KEY_COMMAND = "command";
    public static final String KEY_CONTENT = "content";
    public static final String KEY_ENC = "enc";
    public static final int NOTIFY_ID_IM = 0;
    public static final String RESPONSE_SEND_SUCCESS = "send.success";
    public static final String SERVICE_TAG_IM_APPLY = "im_apply";
    public static final String SERVICE_TAG_IM_ONLINE_MSG = "im_online_msg";
    public static final String SERVICE_TAG_IM_OUTLINE_MSG = "im_outline_msg";
    public static final String SOCKET_TYPE_CHAT = "CHAT_SOCKET";
    public static final String SOCKET_TYPE_IM = "IM_SOCKET";
    public static final int TYPE_ID_FANS_DELETE = 1131;
    public static final int TYPE_ID_FANS_LOGIN = 110;
    public static final int TYPE_ID_FANS_LOGOUT = 113;
    public static final int TYPE_ID_FRIEND_LOGIN = 104;
    public static final int TYPE_ID_FRIEND_LOGOUT = 105;
    public static final int TYPE_ID_FRIEND_LOGOUT_DEL = 1051;
    public static final int TYPE_ID_IGNORE_CHAT_RECORD = 215;
    public static final int TYPE_ID_IPAD_RECHARGE_FAILED = 1001;
    public static final int TYPE_ID_IPAD_RECHARGE_SUCCEED = 1000;
    public static final int TYPE_ID_JOIN_GROUP = 211;
    public static final int TYPE_ID_LEGION_GATHERED = 2122;
    public static final int TYPE_ID_LOGIN_FAILED = 503;
    public static final int TYPE_ID_LUCKY_SQUARE = 108;
    public static final int TYPE_ID_MESSAGE_CHAT = 101;
    public static final int TYPE_ID_MESSAGE_FRIEND_REQUEST = 107;
    public static final int TYPE_ID_MESSAGE_FRIEND_REQUEST_FRIEND = 1071;
    public static final int TYPE_ID_MESSAGE_FRIEND_REQUEST_GROUP = 1072;
    public static final int TYPE_ID_MESSAGE_FRIEND_REQUEST_UNTREATED = 203;
    public static final int TYPE_ID_MESSAGE_SYSTEM = 106;
    public static final int TYPE_ID_MESSAGE_UNREAD = 102;
    public static final int TYPE_ID_MODE_MESSAGE_SHIELD = 2121;
    public static final int TYPE_ID_MSSAGE_SUBSCRIBED_PROGRAM = 207;
    public static final int TYPE_ID_NEED_LOGIN = 502;
    public static final int TYPE_ID_OPTION_MODIFIED = 204;
    public static final int TYPE_ID_OUTLINE_FRIEND_ADD = 114;
    public static final int TYPE_ID_OUTLINE_FRIEND_DELETE = 115;
    public static final int TYPE_ID_QUIT_GROUP = 212;
    public static final int TYPE_ID_RESPONSE_OF_COMMAND = 701;
    public static final int TYPE_ID_SOCKET_START_CONNECT = -1;
    public static final int TYPE_ID_USER_HAS_CHECKED_MESSAGE = 205;
    public static final int TYPE_ID_USER_HIDDEN_MODIFIED = 206;
    public static final String T_ADD_FRIEND = "im_requestfriend";
    public static final String T_ADD_TO_BLACK_LIST = "im_baduser";
    public static final String T_AGREE_FRIEND_REQUEST = "im_agreefriend";
    public static final String T_AGREE_SOMEBODY_JOIN_GROUP = "im_agreegroup";
    public static final String T_FIND_FRIEND = "im_findfriend";
    public static final String T_GROUP_LIST = "login_getgrouplist";
    public static final String T_GROUP_ONLINE_USER_LIST = "login_getgroupuserlist";
    public static final String T_GROUP_ONLINE_USER_NUM = "login_getgroupusernum";
    public static final String T_IGNORE_ALL_MESSAGE = "msg_unsetmsg";
    public static final String T_LAST_USERS = "login_lastuser";
    public static final String T_LOGIN = "login_login";
    public static final String T_MSG_READ_HISTORY_MSG = "msg_readhistorymsg";
    public static final String T_MSG_READ_MSG_ONCE = "msg_readmsgonce";
    public static final String T_ONLINE_LIST = "login_getlist";
    public static final String T_OPTION = "im_setoption";
    public static final String T_OUTLINE_LIST = "login_outlinelist";
    public static final String T_REFUSE_FRIEND_REQUEST = "im_refusefriend";
    public static final String T_REFUSE_SOMEBODY_JOIN_GROUP = "im_refgroup";
    public static final String T_REMOVE_FRIEND = "im_deluser";
    public static final String T_REMOVE_FROM_BLACK_LIST = "im_delbad";
    public static final String T_REMOVE_GROUP_ADMIN = "im_delgroupadmin";
    public static final String T_REMOVE_USER_FROM_GROUP = "im_delgroupuser";
    public static final String T_SEND_GROUP_MSG = "msg_sendgroupmsg";
    public static final String T_SEND_MSG = "msg_sendmsg";
    public static final String T_SET_GROUP_ADMIN = "im_setgroupadmin";
    public static final String T_SET_HIDE = "im_sethide";

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

    public static String sendMessageCommand(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("command=sendmessage\r\n");
        stringBuffer.append("content=" + encryptContent(str) + SocketUtil.CRLF);
        stringBuffer.insert(0, addZeroForMessageLength(stringBuffer.toString()) + SocketUtil.CRLF);
        return stringBuffer.toString();
    }

    public static StringBuffer processString(StringBuffer stringBuffer) {
        stringBuffer.insert(0, addZeroForMessageLength(stringBuffer.toString()) + SocketUtil.CRLF);
        return stringBuffer;
    }

    public static String bundleTheCommand(Map<String, String> map) {
        map.put("ic", AppInfoUtils.getAppInfo());
        return sendMessageCommand(new JSONObject(map).toString());
    }

    private static String a(Map<String, Object> map) {
        map.put("ic", AppInfoUtils.getAppInfo());
        return sendMessageCommand(new JSONObject(map).toString());
    }

    public static String getCommandLogin(String str) {
        Map hashMap = new HashMap();
        hashMap.put("t", "login_login");
        hashMap.put("encpass", str);
        return bundleTheCommand(hashMap);
    }

    public static String getCommandOnLineList() {
        Map hashMap = new HashMap();
        hashMap.put("t", T_ONLINE_LIST);
        return bundleTheCommand(hashMap);
    }

    public static String getCommandOutLineList(int i, int i2) {
        JsonObject jsonObject = new JsonObject();
        JsonElement jsonObject2 = new JsonObject();
        jsonObject2.addProperty("page", Integer.valueOf(i));
        jsonObject2.addProperty("size", Integer.valueOf(i2));
        jsonObject.addProperty("ic", AppInfoUtils.getAppInfo());
        jsonObject.addProperty("t", T_OUTLINE_LIST);
        jsonObject.add("content", jsonObject2);
        return sendMessageCommand(jsonObject.toString());
    }

    public static String getCommandGroupList() {
        Map hashMap = new HashMap();
        hashMap.put("t", T_GROUP_LIST);
        return bundleTheCommand(hashMap);
    }

    public static String getCommandLastUsers() {
        Map hashMap = new HashMap();
        hashMap.put("t", T_LAST_USERS);
        return bundleTheCommand(hashMap);
    }

    public static String getCommandGroupOnLineUserNum(String str) {
        Map hashMap = new HashMap();
        hashMap.put("t", T_GROUP_ONLINE_USER_NUM);
        Map hashMap2 = new HashMap();
        hashMap2.put("gid", str);
        hashMap.put("content", new JSONObject(hashMap2));
        return a(hashMap);
    }

    public static String getCommandGroupOnLineUserList(String str) {
        Map hashMap = new HashMap();
        hashMap.put("t", T_GROUP_ONLINE_USER_LIST);
        Map hashMap2 = new HashMap();
        hashMap2.put("gid", str);
        hashMap.put("content", new JSONObject(hashMap2));
        return a(hashMap);
    }

    public static String getCommandOption(boolean z, boolean z2, boolean z3) {
        Map hashMap = new HashMap();
        hashMap.put("t", T_OPTION);
        Map hashMap2 = new HashMap();
        hashMap2.put("friend", z ? "1" : "2");
        hashMap2.put(WPA.CHAT_TYPE_GROUP, z2 ? "1" : "2");
        hashMap2.put("sound", z3 ? "1" : "2");
        hashMap.put("content", new JSONObject(hashMap2));
        return a(hashMap);
    }

    public static String getCommandSetHide(boolean z) {
        Map hashMap = new HashMap();
        hashMap.put("t", T_SET_HIDE);
        Map hashMap2 = new HashMap();
        hashMap2.put("hid", z ? "2" : "1");
        hashMap.put("content", new JSONObject(hashMap2));
        return a(hashMap);
    }

    public static String getCommandAddFriend(String str) {
        Map hashMap = new HashMap();
        hashMap.put("t", T_ADD_FRIEND);
        Map hashMap2 = new HashMap();
        hashMap2.put("tuid", str);
        hashMap.put("content", new JSONObject(hashMap2));
        return a(hashMap);
    }

    public static String getCommandFindFriend(String str) {
        Map hashMap = new HashMap();
        hashMap.put("t", T_FIND_FRIEND);
        Map hashMap2 = new HashMap();
        hashMap2.put("trid", str);
        hashMap.put("content", new JSONObject(hashMap2));
        return a(hashMap);
    }

    public static String getCommandAgreeFriendRequest(String str) {
        Map hashMap = new HashMap();
        hashMap.put("t", T_AGREE_FRIEND_REQUEST);
        Map hashMap2 = new HashMap();
        hashMap2.put(x.at, str);
        hashMap.put("content", new JSONObject(hashMap2));
        return a(hashMap);
    }

    public static String getCommandRefuseFriendRequest(String str) {
        Map hashMap = new HashMap();
        hashMap.put("t", T_REFUSE_FRIEND_REQUEST);
        Map hashMap2 = new HashMap();
        hashMap2.put(x.at, str);
        hashMap.put("content", new JSONObject(hashMap2));
        return a(hashMap);
    }

    public static String getCommandAddToBlackList(String str) {
        Map hashMap = new HashMap();
        hashMap.put("t", T_ADD_TO_BLACK_LIST);
        Map hashMap2 = new HashMap();
        hashMap2.put(x.at, str);
        hashMap.put("content", new JSONObject(hashMap2));
        return a(hashMap);
    }

    public static String getCommandRemoveFromBlackList(String str) {
        Map hashMap = new HashMap();
        hashMap.put("t", T_REMOVE_FROM_BLACK_LIST);
        Map hashMap2 = new HashMap();
        hashMap2.put(x.at, str);
        hashMap.put("content", new JSONObject(hashMap2));
        return a(hashMap);
    }

    public static String getCommandRemoveFriend(String str) {
        Map hashMap = new HashMap();
        hashMap.put("t", T_REMOVE_FRIEND);
        Map hashMap2 = new HashMap();
        hashMap2.put(x.at, str);
        hashMap.put("content", new JSONObject(hashMap2));
        return a(hashMap);
    }

    public static String getCommandSendMsg(String str, String str2, String str3) {
        Map hashMap = new HashMap();
        hashMap.put("t", T_SEND_MSG);
        Map hashMap2 = new HashMap();
        hashMap2.put("tuid", str);
        hashMap2.put("msg", str2);
        hashMap2.put("cmid", str3);
        hashMap.put("content", new JSONObject(hashMap2));
        return a(hashMap);
    }

    public static String getCommandSendGroupMsg(String str, String str2, String str3) {
        Map hashMap = new HashMap();
        hashMap.put("t", T_SEND_GROUP_MSG);
        Map hashMap2 = new HashMap();
        hashMap2.put("tuid", str);
        hashMap2.put("msg", str2);
        hashMap2.put("cmid", str3);
        hashMap.put("content", new JSONObject(hashMap2));
        return a(hashMap);
    }

    public static String getCommandReadSingleUserMessage(String str, long j, long j2) {
        Map hashMap = new HashMap();
        hashMap.put("t", T_MSG_READ_MSG_ONCE);
        Map hashMap2 = new HashMap();
        hashMap2.put(x.at, str);
        hashMap2.put(IXAdRequestInfo.MAX_TITLE_LENGTH, String.valueOf(j));
        hashMap2.put(DeviceInfo.TAG_MID, String.valueOf(j2));
        hashMap.put("content", new JSONObject(hashMap2));
        return a(hashMap);
    }

    public static String getCommandReadSingleUserRecord(String str, long j, long j2, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("t", T_MSG_READ_HISTORY_MSG);
        Map hashMap2 = new HashMap();
        hashMap2.put(x.at, str);
        hashMap2.put(IXAdRequestInfo.MAX_TITLE_LENGTH, String.valueOf(j));
        hashMap2.put(DeviceInfo.TAG_MID, String.valueOf(j2));
        hashMap2.put("size", str2);
        hashMap.put("content", new JSONObject(hashMap2));
        return a(hashMap);
    }

    public static String getCommandIgnoreAllMessage() {
        Map hashMap = new HashMap();
        hashMap.put("t", T_IGNORE_ALL_MESSAGE);
        hashMap.put("content", new JSONObject(new HashMap()));
        return a(hashMap);
    }

    public static String getCommandAgreeSomebodyJoinGroup(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("t", T_AGREE_SOMEBODY_JOIN_GROUP);
        Map hashMap2 = new HashMap();
        hashMap2.put(x.at, str);
        hashMap2.put("gid", str2);
        hashMap.put("content", new JSONObject(hashMap2));
        return a(hashMap);
    }

    public static String getCommandRefuseSomebodyJoinGroup(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("t", T_REFUSE_SOMEBODY_JOIN_GROUP);
        Map hashMap2 = new HashMap();
        hashMap2.put(x.at, str);
        hashMap2.put("gid", str2);
        hashMap.put("content", new JSONObject(hashMap2));
        return a(hashMap);
    }

    public static String getCommandSetGroupAdmin(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("t", T_SET_GROUP_ADMIN);
        Map hashMap2 = new HashMap();
        hashMap2.put("tuid", str);
        hashMap2.put("gid", str2);
        hashMap.put("content", new JSONObject(hashMap2));
        return a(hashMap);
    }

    public static String getCommandRemoveGroupAdmin(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("t", T_REMOVE_GROUP_ADMIN);
        Map hashMap2 = new HashMap();
        hashMap2.put("tuid", str);
        hashMap2.put("gid", str2);
        hashMap.put("content", new JSONObject(hashMap2));
        return a(hashMap);
    }

    public static String getCommandRemoveUserFromGroup(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("t", T_REMOVE_USER_FROM_GROUP);
        Map hashMap2 = new HashMap();
        hashMap2.put("tuid", str);
        hashMap2.put("gid", str2);
        hashMap.put("content", new JSONObject(hashMap2));
        return a(hashMap);
    }

    public static String getCommandIgnoreMessage(String str) {
        Map hashMap = new HashMap();
        hashMap.put("t", "msg_unsetmsgonce");
        Map hashMap2 = new HashMap();
        hashMap2.put("tuid", str);
        hashMap.put("content", new JSONObject(hashMap2));
        return a(hashMap);
    }
}
