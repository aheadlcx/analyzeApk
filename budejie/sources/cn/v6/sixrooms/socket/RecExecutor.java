package cn.v6.sixrooms.socket;

public class RecExecutor implements Runnable {
    private TcpFactory a;

    public RecExecutor(TcpFactory tcpFactory) {
        this.a = tcpFactory;
    }

    public void run() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r4 = this;
    L_0x0000:
        r0 = r4.a;	 Catch:{ InterruptedException -> 0x0021 }
        r2 = r0.getRecEvent();	 Catch:{ InterruptedException -> 0x0021 }
        r0 = r4.a;	 Catch:{ InterruptedException -> 0x0021 }
        r3 = r0.getRecListener();	 Catch:{ InterruptedException -> 0x0021 }
        r0 = 0;	 Catch:{ InterruptedException -> 0x0021 }
        r1 = r0;	 Catch:{ InterruptedException -> 0x0021 }
    L_0x000e:
        r0 = r3.size();	 Catch:{ InterruptedException -> 0x0021 }
        if (r1 >= r0) goto L_0x0000;	 Catch:{ InterruptedException -> 0x0021 }
    L_0x0014:
        r0 = r3.get(r1);	 Catch:{ InterruptedException -> 0x0021 }
        r0 = (cn.v6.sixrooms.socket.ReceiveListener) r0;	 Catch:{ InterruptedException -> 0x0021 }
        r0.onReceive(r2);	 Catch:{ InterruptedException -> 0x0021 }
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x000e;
    L_0x0021:
        r0 = move-exception;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.v6.sixrooms.socket.RecExecutor.run():void");
    }
}
