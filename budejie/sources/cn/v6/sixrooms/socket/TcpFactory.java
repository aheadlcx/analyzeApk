package cn.v6.sixrooms.socket;

import cn.v6.sixrooms.socket.common.SocketUtil;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TcpFactory {
    private BlockingQueue a = null;
    private BlockingQueue b = null;
    private int c = 100;
    private String d;
    private int e;
    private int f;
    private String g;
    private String h;
    private String i;
    private boolean j = false;
    private int k = 5;
    private ExecutorService l = null;
    private TcpReceiveThread m = null;
    private TcpSendThread n = null;
    private TcpHeartBeatThread o = null;
    private transient Vector p = null;

    public void start() {
        this.b = new ArrayBlockingQueue(this.c);
        this.a = new ArrayBlockingQueue(this.c);
        if (this.j) {
            a();
        }
        this.m = new TcpReceiveThread(this);
        this.n = new TcpSendThread(this);
        this.o = new TcpHeartBeatThread(this);
        this.m.start();
        this.n.start();
        this.o.start();
    }

    private void a() {
        if (this.l == null) {
            TcpLogger.info("初始化后台处理线程池...");
            this.l = Executors.newFixedThreadPool(this.k);
            TcpLogger.info("将" + this.k + "个后台处理线程加入线程池中...");
            for (int i = 0; i < this.k; i++) {
                this.l.execute(new RecExecutor(this));
            }
            TcpLogger.info("成功启动后台处理线程");
        }
    }

    public void stop() {
        if (this.m != null) {
            this.m.interrupt();
            new Thread(new b(this)).start();
        }
        if (this.n != null) {
            this.n.interrupt();
        }
        if (this.o != null) {
            this.o.interrupt();
        }
        TcpLogger.info("成功关闭接受、发送和心跳线程。");
        if (this.l != null) {
            this.l.shutdown();
            if (!this.l.isTerminated()) {
                TcpLogger.info("正在关闭后台连接池，请等待...");
                try {
                    this.l.awaitTermination(30, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!this.l.isTerminated()) {
                this.l.shutdownNow();
            }
        }
        this.l = null;
        TcpLogger.info("成功关闭后台连接池。");
    }

    public ReceiveEvent getRecEvent() throws InterruptedException {
        if (this.b != null) {
            return (ReceiveEvent) this.b.take();
        }
        return null;
    }

    public String getSendCmd() throws InterruptedException {
        if (this.a != null) {
            return (String) this.a.take();
        }
        return null;
    }

    public void sendCmd(String str) throws InterruptedException {
        if (this.a != null) {
            this.a.put(str);
        }
    }

    public synchronized void addReceiveListener(ReceiveListener receiveListener) {
        if (this.p == null) {
            this.p = new Vector(2);
        }
        if (!this.p.contains(receiveListener)) {
            this.p.add(receiveListener);
        }
    }

    public synchronized void removeReceiveListener(ReceiveListener receiveListener) {
        if (this.p != null && this.p.contains(receiveListener)) {
            this.p.remove(receiveListener);
        }
    }

    protected void fireOnReceive(ReceiveEvent receiveEvent) throws InterruptedException {
        if (this.p == null) {
            return;
        }
        if (this.l != null) {
            this.b.put(receiveEvent);
            return;
        }
        for (int i = 0; i < this.p.size(); i++) {
            ((ReceiveListener) this.p.get(i)).onReceive(receiveEvent);
        }
    }

    public String getHost() {
        return this.d;
    }

    public void setHost(String str) {
        this.d = str;
    }

    public int getPort() {
        return this.e;
    }

    public void setPort(int i) {
        this.e = i;
    }

    public int getTimeout() {
        return this.f;
    }

    public void setTimeout(int i) {
        this.f = i;
    }

    public String getLoginStr() {
        return this.g;
    }

    public void setLoginStr(String str) {
        this.g = str;
    }

    public String getEncpass() {
        return this.h;
    }

    public void setEncpass(String str) {
        this.h = str;
    }

    public String getSocketType() {
        return this.i;
    }

    public void setSocketType(String str) {
        this.i = str;
    }

    public int getBufSize() {
        return this.c;
    }

    public void setBufSize(int i) {
        this.c = i;
    }

    public int getRecThreads() {
        return this.k;
    }

    public void setRecThreads(int i) {
        this.k = i;
    }

    public Vector getRecListener() {
        return this.p;
    }

    public void setRecListener(Vector vector) {
        this.p = vector;
    }

    public TcpReceiveThread getRecThread() {
        return this.m;
    }

    public void setRecThread(TcpReceiveThread tcpReceiveThread) {
        this.m = tcpReceiveThread;
    }

    public TcpSendThread getSendThread() {
        return this.n;
    }

    public void setSendThread(TcpSendThread tcpSendThread) {
        this.n = tcpSendThread;
    }

    public TcpHeartBeatThread getHeartBeatThread() {
        return this.o;
    }

    public void setHeartBeatThread(TcpHeartBeatThread tcpHeartBeatThread) {
        this.o = tcpHeartBeatThread;
    }

    public static void main(String[] strArr) {
        TcpFactory tcpFactory = new TcpFactory();
        tcpFactory.setHost("61.237.235.23");
        tcpFactory.setPort(50000);
        tcpFactory.setLoginStr(SocketUtil.loginCommand("22588316", "7Ibv4|nLXACidhSNW2D8IThl8jtV7L1k_pkcpolNXGZiRSEGKSwLSuIvuZC1ePWj5YEStKFr10004", "25592641"));
        tcpFactory.setTimeout(18000);
        tcpFactory.start();
    }
}
