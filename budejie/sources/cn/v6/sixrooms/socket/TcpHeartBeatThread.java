package cn.v6.sixrooms.socket;

import cn.v6.sixrooms.socket.common.SocketUtil;
import java.io.BufferedWriter;

public class TcpHeartBeatThread extends Thread {
    private Object a = new Object();
    private BufferedWriter b = null;
    private TcpFactory c = null;
    private int d = 10000;

    public TcpHeartBeatThread(TcpFactory tcpFactory) {
        this.c = tcpFactory;
    }

    public void setOut(BufferedWriter bufferedWriter) {
        synchronized (this.a) {
            this.b = bufferedWriter;
            this.a.notify();
        }
    }

    public void run() {
        while (!isInterrupted()) {
            synchronized (this.a) {
                if (this.b == null) {
                    try {
                        this.a.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
            try {
                this.c.sendCmd(SocketUtil.keepCommand());
                Thread.sleep((long) this.d);
            } catch (InterruptedException e2) {
                return;
            } catch (Exception e3) {
                TcpLogger.error(e3.getMessage());
                if (!isInterrupted()) {
                    this.c.getRecThread().connect();
                }
            }
        }
    }
}
