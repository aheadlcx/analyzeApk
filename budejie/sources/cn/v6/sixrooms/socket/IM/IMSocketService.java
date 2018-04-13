package cn.v6.sixrooms.socket.IM;

import android.text.TextUtils;
import cn.v6.sixrooms.bean.AddressBean;
import cn.v6.sixrooms.engine.ServerAddressEngine;
import cn.v6.sixrooms.room.IM.IMBlackListManager;
import cn.v6.sixrooms.room.IM.IMMessageLastManager;
import cn.v6.sixrooms.room.IM.IMSettingsManager;
import cn.v6.sixrooms.socket.ReceiveEvent;
import cn.v6.sixrooms.socket.ReceiveListener;
import cn.v6.sixrooms.socket.TcpFactory;
import cn.v6.sixrooms.socket.common.SocketAddress;
import cn.v6.sixrooms.socket.common.SocketUtil;
import cn.v6.sixrooms.socket.common.TcpCommand;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class IMSocketService implements ReceiveListener {
    private static final String a = IMSocketService.class.getSimpleName();
    private TcpFactory b;
    private IMListenerManager c = new IMListenerManager();
    private String d;
    private String e;
    private String f;
    private String g;
    private long h;
    private String i;
    private ServerAddressEngine j = null;
    private int k = 0;

    public IMSocketService(String str, String str2, String str3, String str4) {
        this.d = str;
        this.e = str2;
        this.f = str3;
        this.i = str4;
    }

    public void start() {
        SocketAddress.getInstance();
        if (this.j == null) {
            this.j = new ServerAddressEngine(new b(this));
        }
        if ("IM_SOCKET".equals(this.i)) {
            this.j.getIMServerAddress(this.d);
        } else {
            this.j.getChatServerAddress(this.f);
        }
    }

    public void run(List<String> list, String str) {
        SocketAddress instance = SocketAddress.getInstance();
        instance.setImAddressList(list);
        AddressBean currentImAddress = instance.getCurrentImAddress();
        this.b = new TcpFactory();
        this.b.setHost(currentImAddress.getAddress());
        this.b.setPort(currentImAddress.getPort());
        this.b.setLoginStr(SocketUtil.loginCommand(this.d, this.e, this.f));
        this.b.setEncpass(this.e);
        this.b.setSocketType(this.i);
        this.b.setTimeout(18000);
        this.b.addReceiveListener(this);
        this.b.start();
    }

    private void a(String str) {
        if (this.b == null) {
            start();
            return;
        }
        try {
            this.b.sendCmd(str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void imLoginImServer() {
        a(IMSocketUtil.getCommandLogin(this.e));
    }

    public void imGetOnLineList() {
        a(IMSocketUtil.getCommandOnLineList());
    }

    public void imGetOutLineList(int i, int i2) {
        a(IMSocketUtil.getCommandOutLineList(i, i2));
    }

    public void imGetGroupList() {
        a(IMSocketUtil.getCommandGroupList());
    }

    public void imGetLastUsers() {
        a(IMSocketUtil.getCommandLastUsers());
    }

    public void imGetGroupOnLineUserNum(String str) {
        a(IMSocketUtil.getCommandGroupOnLineUserNum(str));
    }

    public void imGetGroupOnLineUserList(String str) {
        a(IMSocketUtil.getCommandGroupOnLineUserList(str));
    }

    public void imSetOption(boolean z, boolean z2, boolean z3) {
        a(IMSocketUtil.getCommandOption(z, z2, z3));
    }

    public void imSetHidden(boolean z) {
        a(IMSocketUtil.getCommandSetHide(z));
    }

    public void imSendFriendRequest(String str) {
        a(IMSocketUtil.getCommandAddFriend(str));
    }

    public void imSearchFriend(String str) {
        a(IMSocketUtil.getCommandFindFriend(str));
    }

    public void imFriendRequestAgree(String str) {
        a(IMSocketUtil.getCommandAgreeFriendRequest(str));
    }

    public void imFriendRequestRefuse(String str) {
        a(IMSocketUtil.getCommandRefuseFriendRequest(str));
    }

    public void imBlackListAdd(String str) {
        a(IMSocketUtil.getCommandAddToBlackList(str));
    }

    public void imBlackListRemove(String str) {
        a(IMSocketUtil.getCommandRemoveFromBlackList(str));
    }

    public void imRemoveFriend(String str) {
        a(IMSocketUtil.getCommandRemoveFriend(str));
    }

    public void imSendChatMessage(String str, String str2, String str3) {
        a(IMSocketUtil.getCommandSendMsg(str, str2, str3));
    }

    public void imSendGroupChatMessage(String str, String str2, String str3) {
        a(IMSocketUtil.getCommandSendGroupMsg(str, str2, str3));
    }

    public void imGetMessage(String str, long j, long j2) {
        a(IMSocketUtil.getCommandReadSingleUserMessage(str, j, j2));
    }

    public void imGetMessageHistory(String str, long j, long j2, String str2) {
        a(IMSocketUtil.getCommandReadSingleUserRecord(str, j, j2, str2));
    }

    public void imIgnoreAllMessage() {
        a(IMSocketUtil.getCommandIgnoreAllMessage());
    }

    public void imJoinGroupAgree(String str, String str2) {
        a(IMSocketUtil.getCommandAgreeSomebodyJoinGroup(str, str2));
    }

    public void imJoinGroupRefuse(String str, String str2) {
        a(IMSocketUtil.getCommandRefuseSomebodyJoinGroup(str, str2));
    }

    public void imGroupAdminAdd(String str, String str2) {
        a(IMSocketUtil.getCommandSetGroupAdmin(str, str2));
    }

    public void imGroupAdminRemove(String str, String str2) {
        a(IMSocketUtil.getCommandRemoveGroupAdmin(str, str2));
    }

    public void imRemoveFromGroup(String str, String str2) {
        a(IMSocketUtil.getCommandRemoveUserFromGroup(str, str2));
    }

    public void stop() {
        if (this.b != null) {
            try {
                this.b.sendCmd(SocketUtil.disconnectCommand());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.b.stop();
            }
        }
    }

    public boolean addImListener(IMListener iMListener) {
        if (this.c == null) {
            this.c = new IMListenerManager();
        }
        return this.c.add(iMListener);
    }

    public boolean removeListener(IMListener iMListener) {
        if (this.c != null) {
            return this.c.remove(iMListener);
        }
        return false;
    }

    public void removeAllListener() {
        if (this.c != null) {
            this.c.removeAll();
            this.c = null;
        }
    }

    public void onReceive(ReceiveEvent receiveEvent) {
        if (this.c != null) {
            TcpCommand recCmd = receiveEvent.getRecCmd();
            try {
                JSONObject jSONObject = new JSONObject(SocketUtil.decryptContent(recCmd.getContentValue(), recCmd.getEncBoolValue()));
                int i = jSONObject.getInt("typeID");
                long j = -1;
                if (!jSONObject.isNull(IXAdRequestInfo.MAX_TITLE_LENGTH)) {
                    j = jSONObject.getLong(IXAdRequestInfo.MAX_TITLE_LENGTH);
                }
                a(i, j, jSONObject.getString("content"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String a(int i, long j, String str) {
        String str2 = "";
        JSONObject jSONObject;
        if (i == 701) {
            try {
                jSONObject = new JSONObject(str);
                if (!jSONObject.isNull("t")) {
                    str2 = jSONObject.getString("t");
                }
                JSONObject jSONObject2 = jSONObject.getJSONObject("content");
                try {
                    String string = jSONObject2.getString("content");
                    if ("001".equals(jSONObject2.getString("typeID"))) {
                        if ("login_login".equals(str2)) {
                            IMMessageLastManager.getInstance().setMessageOnLogin(string);
                            IMSettingsManager.getInstance().setIMSettingsFromLoginInfo(string);
                        } else if (!(IMSocketUtil.T_ONLINE_LIST.equals(str2) || IMSocketUtil.T_OUTLINE_LIST.equals(str2) || IMSocketUtil.T_GROUP_LIST.equals(str2) || IMSocketUtil.T_GROUP_ONLINE_USER_NUM.equals(str2) || IMSocketUtil.T_GROUP_ONLINE_USER_LIST.equals(str2))) {
                            if (IMSocketUtil.T_LAST_USERS.equals(str2)) {
                                IMMessageLastManager.getInstance().onLastUsers(string);
                            } else if (IMSocketUtil.T_SEND_MSG.equals(str2)) {
                                IMMessageLastManager.getInstance().onSendMsg(string);
                            } else if (IMSocketUtil.T_SEND_GROUP_MSG.equals(str2)) {
                                IMMessageLastManager.getInstance().onSendGroupMsg(string);
                            } else if (IMSocketUtil.T_MSG_READ_MSG_ONCE.equals(str2)) {
                                IMMessageLastManager.getInstance().onReadOnce(string);
                            } else if (IMSocketUtil.T_MSG_READ_HISTORY_MSG.equals(str2)) {
                                IMMessageLastManager.getInstance().onReadHistory(string);
                            } else if (!IMSocketUtil.T_AGREE_SOMEBODY_JOIN_GROUP.equals(str2)) {
                                if (IMSocketUtil.T_ADD_TO_BLACK_LIST.equals(str2)) {
                                    IMBlackListManager.getInstance().add();
                                } else if (!IMSocketUtil.T_REMOVE_FRIEND.equals(str2)) {
                                    this.c.onContentReceive(701, j, str2, jSONObject2);
                                }
                            }
                        }
                        this.c.onActionReceive(701, j, str2);
                    } else {
                        this.c.onContentReceive(701, j, str2, jSONObject2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        } else if (!TextUtils.isEmpty(str)) {
            switch (i) {
                case 102:
                    IMMessageLastManager.getInstance().onNewUnreadMessage(str);
                    String.valueOf(IMMessageLastManager.getInstance().getTempUnreadMsg().getUid());
                    break;
                case 104:
                    jSONObject = new JSONObject(str);
                    break;
                case 204:
                    IMSettingsManager.getInstance().setIMSettings(str);
                    break;
                case 205:
                    IMMessageLastManager.getInstance().onHasReadMsg(str);
                    break;
            }
            if (this.c != null) {
                this.c.onActionReceive(i, j, str2);
            }
        }
        return str2;
    }

    public String getString(Object obj) {
        if (obj instanceof String) {
            return obj.toString();
        }
        try {
            return Integer.parseInt(obj.toString());
        } catch (NumberFormatException e) {
            return "";
        }
    }

    public TcpFactory getTcpFactory() {
        return this.b;
    }

    public void setTcpFactory(TcpFactory tcpFactory) {
        this.b = tcpFactory;
    }

    public String getUid() {
        return this.d;
    }

    public void setUid(String str) {
        this.d = str;
    }

    public String getEncpass() {
        return this.e;
    }

    public void setEncpass(String str) {
        this.e = str;
    }

    public String getRoomId() {
        return this.f;
    }

    public void setRoomId(String str) {
        this.f = str;
    }

    public String getAuthKey() {
        return this.g;
    }

    public void setAuthKey(String str) {
        this.g = str;
    }

    public long getUserListTm() {
        return this.h;
    }

    public void setUserListTm(long j) {
        this.h = j;
    }

    public String getSocketType() {
        return this.i;
    }

    public void setSocketType(String str) {
        this.i = str;
    }

    public void imIgnoreMessage(String str) {
        a(IMSocketUtil.getCommandIgnoreMessage(str));
    }
}
