package cn.v6.sixrooms.socket.IM;

import android.text.TextUtils;

public class IMMsgSocket {
    private static final String a = IMMsgSocket.class.getSimpleName();
    private static volatile IMMsgSocket d;
    private IMSocketService b;
    private String c = "2";

    public static IMMsgSocket createInstance(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException();
        }
        if (d == null) {
            synchronized (IMMsgSocket.class) {
                if (d == null) {
                    d = new IMMsgSocket(str, str2);
                }
            }
        }
        return d;
    }

    public static IMMsgSocket getInstanceForStop() {
        return d;
    }

    private IMMsgSocket(String str, String str2) {
        this.b = new IMSocketService(str, str2, this.c, "IM_SOCKET");
        this.b.start();
    }

    public void setImListener(IMListener iMListener) {
        this.b.addImListener(iMListener);
    }

    public void stopIMSocket() {
        if (this.b != null) {
            this.b.stop();
            this.b.removeAllListener();
            d = null;
        }
    }

    public void imLoginImServer() {
        this.b.imLoginImServer();
    }

    public void imGetOnLineList() {
        this.b.imGetOnLineList();
    }

    public void imGetOutLineList(int i, int i2) {
        this.b.imGetOutLineList(i, i2);
    }

    public void imGetGroupList() {
        this.b.imGetGroupList();
    }

    public void imGetLastUsers() {
        this.b.imGetLastUsers();
    }

    public void imGetGroupOnLineUserNum(String str) {
        this.b.imGetGroupOnLineUserNum(str);
    }

    public void imGetGroupOnLineUserList(String str) {
        this.b.imGetGroupOnLineUserList(str);
    }

    public void imSetOption(boolean z, boolean z2, boolean z3) {
        this.b.imSetOption(z, z2, z3);
    }

    public void imSetHidden(boolean z) {
        this.b.imSetHidden(z);
    }

    public void imSendFriendRequest(String str) {
        this.b.imSendFriendRequest(str);
    }

    public void imSearchFriend(String str) {
        this.b.imSearchFriend(str);
    }

    public void imFriendRequestAgree(String str) {
        this.b.imFriendRequestAgree(str);
    }

    public void imFriendRequestRefuse(String str) {
        this.b.imFriendRequestRefuse(str);
    }

    public void imBlackListAdd(String str) {
        this.b.imBlackListAdd(str);
    }

    public void imBlackListRemove(String str) {
        this.b.imBlackListRemove(str);
    }

    public void imRemoveFriend(String str) {
        this.b.imRemoveFriend(str);
    }

    public void imSendChatMessage(String str, String str2, String str3) {
        this.b.imSendChatMessage(str, str2, str3);
    }

    public void imSendGroupChatMessage(String str, String str2, String str3) {
        this.b.imSendGroupChatMessage(str, str2, str3);
    }

    public void imGetMessage(String str, long j, long j2) {
        this.b.imGetMessage(str, j, j2);
    }

    public void imGetMessageHistory(String str, long j, long j2, String str2) {
        this.b.imGetMessageHistory(str, j, j2, str2);
    }

    public void imIgnoreAllMessage() {
        this.b.imIgnoreAllMessage();
    }

    public void imJoinGroupAgree(String str, String str2) {
        this.b.imJoinGroupAgree(str, str2);
    }

    public void imJoinGroupRefuse(String str, String str2) {
        this.b.imJoinGroupRefuse(str, str2);
    }

    public void imGroupAdminAdd(String str, String str2) {
        this.b.imGroupAdminAdd(str, str2);
    }

    public void imGroupAdminRemove(String str, String str2) {
        this.b.imGroupAdminRemove(str, str2);
    }

    public void imRemoveFromGroup(String str, String str2) {
        this.b.imRemoveFromGroup(str, str2);
    }

    public void removeImListener(IMListener iMListener) {
        this.b.removeListener(iMListener);
    }

    public void imIgnoreMessage(String str) {
        this.b.imIgnoreMessage(str);
    }
}
