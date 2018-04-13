package cn.v6.sixrooms.presenter.runnable;

public interface SocketMemuRequestable {
    void requestAddAdmin(String str);

    void requestAddManager(String str);

    void requestKickRoom(String str);

    void requestRecoverMsg(String str);

    void requestRevokeAdmin(String str);

    void requestRevokeManager(String str);

    void requestStopMsg(String str);
}
