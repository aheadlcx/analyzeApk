package cn.v6.sixrooms.socket.IM;

import cn.v6.sixrooms.bean.AddressBean;
import cn.v6.sixrooms.engine.ServerAddressEngine;
import cn.v6.sixrooms.socket.TcpFactory;
import cn.v6.sixrooms.socket.common.SocketAddress;
import cn.v6.sixrooms.socket.common.SocketUtil;
import java.util.List;

public class IMService {
    private TcpFactory a;
    private String b;
    private String c;
    private String d;
    private String e;
    private long f;
    private String g;

    public IMService(String str, String str2, String str3, String str4) {
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.g = str4;
    }

    public void start() {
        SocketAddress.getInstance();
        ServerAddressEngine serverAddressEngine = new ServerAddressEngine(new a(this));
        if ("IM_SOCKET".equals(this.g)) {
            serverAddressEngine.getIMServerAddress(this.b);
        } else {
            serverAddressEngine.getChatServerAddress(this.d);
        }
    }

    public void run(List<String> list, String str) {
        AddressBean currentImAddress;
        SocketAddress instance = SocketAddress.getInstance();
        if ("IM_SOCKET".equals(str)) {
            instance.setImAddressList(list);
            currentImAddress = instance.getCurrentImAddress();
        } else {
            instance.setChatAddressList(list);
            currentImAddress = instance.getCurrentChatAddress();
        }
        this.a = new TcpFactory();
        this.a.setHost(currentImAddress.getAddress());
        this.a.setPort(currentImAddress.getPort());
        this.a.setLoginStr(SocketUtil.loginCommand(this.b, this.c, this.d));
        this.a.setEncpass(this.c);
        this.a.setSocketType(this.g);
        this.a.setTimeout(18000);
        this.a.start();
    }

    public void stop() {
        if (this.a != null) {
            try {
                this.a.sendCmd(SocketUtil.disconnectCommand());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.a.stop();
            }
        }
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
        return this.a;
    }

    public void setTcpFactory(TcpFactory tcpFactory) {
        this.a = tcpFactory;
    }

    public String getUid() {
        return this.b;
    }

    public void setUid(String str) {
        this.b = str;
    }

    public String getEncpass() {
        return this.c;
    }

    public void setEncpass(String str) {
        this.c = str;
    }

    public String getRoomId() {
        return this.d;
    }

    public void setRoomId(String str) {
        this.d = str;
    }

    public String getAuthKey() {
        return this.e;
    }

    public void setAuthKey(String str) {
        this.e = str;
    }

    public long getUserListTm() {
        return this.f;
    }

    public void setUserListTm(long j) {
        this.f = j;
    }

    public String getSocketType() {
        return this.g;
    }

    public void setSocketType(String str) {
        this.g = str;
    }
}
