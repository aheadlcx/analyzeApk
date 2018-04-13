package cn.v6.sixrooms.socket;

import cn.v6.sdk.sixrooms.base.Base64;
import cn.v6.sixrooms.bean.AddressBean;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.socket.common.SocketAddress;
import cn.v6.sixrooms.socket.common.SocketUtil;
import cn.v6.sixrooms.socket.common.TcpCommand;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class TcpReceiveThread extends Thread {
    private TcpFactory a = null;
    private Socket b = null;
    private BufferedWriter c = null;
    private BufferedReader d = null;
    private boolean e = false;

    public TcpReceiveThread(TcpFactory tcpFactory) {
        this.a = tcpFactory;
        this.e = true;
    }

    public void run() {
        if (connect()) {
            StringBuilder stringBuilder = new StringBuilder();
            while (!isInterrupted() && this.e) {
                try {
                    String readLine = this.d.readLine();
                    if (readLine == null) {
                        throw new IOException("在从服务器读取数据时，连接中断!");
                    } else if (!"".equals(readLine)) {
                        stringBuilder.append(readLine + SocketUtil.CRLF);
                        if (SocketUtil.isDigit(readLine)) {
                            stringBuilder.append(this.d.readLine() + SocketUtil.CRLF);
                            stringBuilder.append(this.d.readLine() + SocketUtil.CRLF);
                            stringBuilder.append(this.d.readLine() + SocketUtil.CRLF);
                        }
                        TcpLogger.info("【接收】" + stringBuilder.toString());
                        TcpCommand tcpCommand = new TcpCommand(stringBuilder.toString());
                        String contentValue = tcpCommand.getContentValue();
                        if (!(contentValue == null || "".equals(contentValue) || "send.success".equals(contentValue))) {
                            this.a.fireOnReceive(new ReceiveEvent(this.a, tcpCommand));
                        }
                        stringBuilder.delete(0, stringBuilder.length());
                        stringBuilder.setLength(0);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    TcpLogger.error(e2.getMessage());
                    if (!isInterrupted()) {
                        connect();
                    }
                }
            }
        }
        a();
    }

    public boolean connect() {
        while (!isInterrupted() && this.e) {
            a();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("typeID", RoomActivity.VIDEOTYPE_UNKNOWN);
            jsonObject.addProperty("content", "正在连接中...");
            try {
                this.a.fireOnReceive(new ReceiveEvent(this.a, new TcpCommand("buffer::-1\r\nenc=no\r\ncommand=local\r\ncontent=" + new String(Base64.encodeBase64(jsonObject.toString().getBytes(), false)) + SocketUtil.CRLF)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TcpLogger.info("正在连接服务器 " + this.a.getHost() + " 端口 " + this.a.getPort());
            try {
                try {
                    this.b = new Socket(InetAddress.getByName(this.a.getHost()), this.a.getPort());
                    this.b.setSoTimeout(this.a.getTimeout());
                    this.c = new BufferedWriter(new OutputStreamWriter(this.b.getOutputStream()));
                    this.d = new BufferedReader(new InputStreamReader(this.b.getInputStream()));
                    TcpLogger.info("连接服务器成功");
                    TcpLogger.info("正在发送权限验证信息...");
                    TcpLogger.info(this.a.getLoginStr());
                    this.c.write(this.a.getLoginStr());
                    this.c.flush();
                    StringBuffer stringBuffer = new StringBuffer();
                    String readLine = this.d.readLine();
                    if (readLine == null) {
                        readLine = "";
                    }
                    stringBuffer.append(readLine + SocketUtil.CRLF);
                    if (SocketUtil.isDigit(readLine)) {
                        char[] cArr = new char[Integer.parseInt(readLine)];
                        this.d.read(cArr);
                        stringBuffer.append(cArr);
                    }
                    TcpLogger.info(stringBuffer.toString());
                    if (SocketUtil.isLoginSuccess(stringBuffer.toString())) {
                        TcpLogger.info("与服务器建权成功");
                        this.a.getSendThread().setOut(this.c);
                        this.a.sendCmd(SocketUtil.authKeyCommand(this.a.getEncpass()));
                        this.a.getHeartBeatThread().setOut(this.c);
                        if ("IM_SOCKET".equals(this.a.getSocketType())) {
                            this.a.sendCmd(SocketUtil.loginRedEnvelope(this.a.getEncpass()));
                        }
                        return true;
                    }
                    throw new IOException("无法通过权限验证，登录名或密码错误！");
                } catch (SocketTimeoutException e2) {
                    throw new SocketTimeoutException("等待返回登录验证响应信息超时！");
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                } catch (IOException e4) {
                    TcpLogger.error("建立连接失败！原因:" + e4.getMessage());
                    if (isInterrupted()) {
                        continue;
                    } else {
                        TcpLogger.info("等待1秒钟后重新建立连接...");
                        try {
                            SocketAddress instance = SocketAddress.getInstance();
                            AddressBean addressBean = null;
                            if ("IM_SOCKET".equals(this.a.getSocketType())) {
                                addressBean = instance.getNextImAddress();
                            } else if ("CHAT_SOCKET".equals(this.a.getSocketType())) {
                                addressBean = instance.getNextChatAddress();
                            }
                            this.a.setHost(addressBean.getAddress());
                            this.a.setPort(addressBean.getPort());
                            sleep(1000);
                        } catch (InterruptedException e5) {
                            return false;
                        }
                    }
                }
            } catch (UnknownHostException e6) {
                TcpLogger.error("主机IP无法识别！");
                return false;
            }
        }
        return false;
    }

    public void close() {
        this.e = false;
        a();
    }

    private void a() {
        if (this.b != null) {
            try {
                this.b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
