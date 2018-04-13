package cz.msebera.android.httpclient.impl.conn.tsccm;

@Deprecated
public class WaitingThreadAborter {
    private WaitingThread a;
    private boolean b;

    public void abort() {
        this.b = true;
        if (this.a != null) {
            this.a.interrupt();
        }
    }

    public void setWaitingThread(WaitingThread waitingThread) {
        this.a = waitingThread;
        if (this.b) {
            waitingThread.interrupt();
        }
    }
}
