package cn.v6.sixrooms.socket;

import java.io.BufferedWriter;
import java.io.IOException;

public class TcpSendThread extends Thread {
    private Object a = new Object();
    private BufferedWriter b = null;
    private TcpFactory c = null;

    public TcpSendThread(TcpFactory tcpFactory) {
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
                String sendCmd = this.c.getSendCmd();
                TcpLogger.info("【发送】" + sendCmd);
                if (!(sendCmd == null || sendCmd.toString().trim().equals(""))) {
                    this.b.write(sendCmd);
                    this.b.flush();
                }
            } catch (InterruptedException e2) {
                return;
            } catch (IOException e3) {
                TcpLogger.error(e3.getMessage());
                if (!isInterrupted()) {
                    this.c.getRecThread().connect();
                }
            }
        }
    }
}
