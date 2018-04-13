package cn.v6.sixrooms.socket;

final class b implements Runnable {
    final /* synthetic */ TcpFactory a;

    b(TcpFactory tcpFactory) {
        this.a = tcpFactory;
    }

    public final void run() {
        if (this.a.m != null) {
            try {
                this.a.m.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
