package cn.v6.sixrooms.socket.chat;

import cn.v6.sixrooms.bean.AddressBean;
import cn.v6.sixrooms.engine.ServerAddressEngine;
import cn.v6.sixrooms.socket.ReceiveEvent;
import cn.v6.sixrooms.socket.ReceiveListener;
import cn.v6.sixrooms.socket.SocketResultInterface;
import cn.v6.sixrooms.socket.TcpFactory;
import cn.v6.sixrooms.socket.common.SocketAddress;
import cn.v6.sixrooms.socket.common.SocketUtil;
import cn.v6.sixrooms.socket.common.TcpCommand;
import cn.v6.sixrooms.utils.LogUtils;
import java.util.List;
import org.json.JSONObject;

public class ChatSocketService implements ReceiveListener {
    private TcpFactory a;
    private String b;
    private String c;
    private String d;
    private String e;
    private boolean f = true;
    private SocketResultInterface g;

    public ChatSocketService(String str, String str2, String str3, String str4, SocketResultInterface socketResultInterface) {
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.g = socketResultInterface;
        this.f = false;
    }

    public void reConnect() {
        if (this.a != null) {
            try {
                this.a.sendCmd(SocketUtil.authKeyCommand(this.a.getEncpass()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        SocketAddress instance = SocketAddress.getInstance();
        ServerAddressEngine serverAddressEngine = new ServerAddressEngine(new c(this));
        if (!"IM_SOCKET".equals(this.e)) {
            serverAddressEngine.getChatServerAddress(this.d);
        } else if (instance.getImAddressList() == null) {
            serverAddressEngine.getIMServerAddress(this.b);
        } else {
            a(instance.getImAddressList(), this.e);
        }
    }

    private void a(List<String> list, String str) {
        AddressBean currentImAddress;
        SocketAddress instance = SocketAddress.getInstance();
        if ("IM_SOCKET".equals(str)) {
            instance.setImAddressList(list);
            currentImAddress = instance.getCurrentImAddress();
        } else {
            instance.setChatAddressList(list);
            currentImAddress = instance.getCurrentChatAddress();
        }
        stop();
        this.a = new TcpFactory();
        this.a.setHost(currentImAddress.getAddress());
        this.a.setPort(currentImAddress.getPort());
        this.a.setLoginStr(SocketUtil.loginCommand(this.b, this.c, this.d));
        this.a.setEncpass(this.c);
        this.a.setSocketType(this.e);
        this.a.setTimeout(18000);
        if ("CHAT_SOCKET".equals(this.e)) {
            this.a.addReceiveListener(this);
        }
        this.a.start();
    }

    public void stop() {
        this.f = true;
        if (this.a != null) {
            try {
                this.a.sendCmd(SocketUtil.disconnectCommand());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.a.stop();
                this.a = null;
            }
        }
    }

    public void onReceive(ReceiveEvent receiveEvent) {
        TcpCommand recCmd = receiveEvent.getRecCmd();
        if ("receivemessage".equals(recCmd.getCommandValue())) {
            String decryptContent = SocketUtil.decryptContent(recCmd.getContentValue(), recCmd.getEncBoolValue());
            try {
                JSONObject jSONObject = new JSONObject(decryptContent);
                String string = jSONObject.getString("flag");
                if ("001".equals(string)) {
                    jSONObject = jSONObject.getJSONObject("content");
                    if (jSONObject != null) {
                        this.g.onReceiveSuccess(jSONObject, string);
                        return;
                    }
                    return;
                }
                this.g.onReceiveFail(jSONObject, string);
            } catch (Exception e) {
                LogUtils.i("ChatSocketService", decryptContent + "==>>" + e.getMessage());
                e.printStackTrace();
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

    public String getSocketType() {
        return this.e;
    }

    public void setSocketType(String str) {
        this.e = str;
    }
}
