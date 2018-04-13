package qsbk.app.core.utils.websocket;

import android.text.TextUtils;
import com.qiushibaike.httpdns.lib.HttpDNSManager;

class b implements Runnable {
    final /* synthetic */ WebSocketClient a;

    b(WebSocketClient webSocketClient) {
        this.a = webSocketClient;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r13 = this;
        r1 = 1;
        r2 = 0;
        r6 = 0;
        r0 = r13.a;
        r0.a();
        r0 = r13.a;	 Catch:{ EOFException -> 0x03b9, SSLException -> 0x03a5, SocketException -> 0x0391, HttpResponseException -> 0x037d, Exception -> 0x0369, all -> 0x0357 }
        r9 = r0.b();	 Catch:{ EOFException -> 0x03b9, SSLException -> 0x03a5, SocketException -> 0x0391, HttpResponseException -> 0x037d, Exception -> 0x0369, all -> 0x0357 }
        r0 = r13.a;	 Catch:{ EOFException -> 0x03b9, SSLException -> 0x03a5, SocketException -> 0x0391, HttpResponseException -> 0x037d, Exception -> 0x0369, all -> 0x0357 }
        r0 = r0.a;	 Catch:{ EOFException -> 0x03b9, SSLException -> 0x03a5, SocketException -> 0x0391, HttpResponseException -> 0x037d, Exception -> 0x0369, all -> 0x0357 }
        r0 = r0.getScheme();	 Catch:{ EOFException -> 0x03b9, SSLException -> 0x03a5, SocketException -> 0x0391, HttpResponseException -> 0x037d, Exception -> 0x0369, all -> 0x0357 }
        r3 = "wss";
        r10 = r0.equals(r3);	 Catch:{ EOFException -> 0x03b9, SSLException -> 0x03a5, SocketException -> 0x0391, HttpResponseException -> 0x037d, Exception -> 0x0369, all -> 0x0357 }
        r0 = r13.a;	 Catch:{ EOFException -> 0x03b9, SSLException -> 0x03a5, SocketException -> 0x0391, HttpResponseException -> 0x037d, Exception -> 0x0369, all -> 0x0357 }
        r0 = r0.a;	 Catch:{ EOFException -> 0x03b9, SSLException -> 0x03a5, SocketException -> 0x0391, HttpResponseException -> 0x037d, Exception -> 0x0369, all -> 0x0357 }
        r5 = r0.getHost();	 Catch:{ EOFException -> 0x03b9, SSLException -> 0x03a5, SocketException -> 0x0391, HttpResponseException -> 0x037d, Exception -> 0x0369, all -> 0x0357 }
        r0 = r13.a;	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r0 = r0.a;	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r0 = r0.getPort();	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r3 = -1;
        if (r0 == r3) goto L_0x01b8;
    L_0x0035:
        r0 = r13.a;	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r0 = r0.a;	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r0 = r0.getPort();	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r8 = r0;
    L_0x0040:
        r0 = r13.a;	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r0 = r0.a;	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r0 = r0.getPath();	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        if (r0 == 0) goto L_0x01c4;
    L_0x0050:
        r0 = "/";
    L_0x0052:
        r3 = r13.a;	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r3 = r3.a;	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r3 = r3.getRawQuery();	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r3 = android.text.TextUtils.isEmpty(r3);	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        if (r3 != 0) goto L_0x03d2;
    L_0x0062:
        r3 = new java.lang.StringBuilder;	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r3.<init>();	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r0 = r3.append(r0);	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r3 = "?";
        r0 = r0.append(r3);	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r3 = r13.a;	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r3 = r3.a;	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r3 = r3.getRawQuery();	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r0 = r0.append(r3);	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r0 = r0.toString();	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r7 = r0;
    L_0x0084:
        if (r10 == 0) goto L_0x01d0;
    L_0x0086:
        r0 = "https";
    L_0x0088:
        r11 = new java.net.URI;	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r3 = new java.lang.StringBuilder;	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r3.<init>();	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r4 = "//";
        r3 = r3.append(r4);	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r3 = r3.append(r5);	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r3 = r3.toString();	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r4 = 0;
        r11.<init>(r0, r3, r4);	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r0 = com.qiushibaike.httpdns.lib.HttpDNSManager.instance();	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r3 = r0.getHttpDnsIp(r5);	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r0 = android.text.TextUtils.isEmpty(r3);	 Catch:{ EOFException -> 0x03c3, SSLException -> 0x03b0, SocketException -> 0x039c, HttpResponseException -> 0x0388, Exception -> 0x0374, all -> 0x0357 }
        if (r0 == 0) goto L_0x03cf;
    L_0x00af:
        r4 = r5;
    L_0x00b0:
        r3 = new java.net.InetSocketAddress;	 Catch:{ EOFException -> 0x03c9, SSLException -> 0x03b5, SocketException -> 0x03a1, HttpResponseException -> 0x038d, Exception -> 0x0379, all -> 0x0357 }
        r3.<init>(r4, r8);	 Catch:{ EOFException -> 0x03c9, SSLException -> 0x03b5, SocketException -> 0x03a1, HttpResponseException -> 0x038d, Exception -> 0x0379, all -> 0x0357 }
        if (r10 == 0) goto L_0x01d4;
    L_0x00b7:
        r0 = r13.a;	 Catch:{ EOFException -> 0x03c9, SSLException -> 0x03b5, SocketException -> 0x03a1, HttpResponseException -> 0x038d, Exception -> 0x0379, all -> 0x0357 }
        r0 = r0.c();	 Catch:{ EOFException -> 0x03c9, SSLException -> 0x03b5, SocketException -> 0x03a1, HttpResponseException -> 0x038d, Exception -> 0x0379, all -> 0x0357 }
    L_0x00bd:
        r8 = r13.a;	 Catch:{ EOFException -> 0x03c9, SSLException -> 0x03b5, SocketException -> 0x03a1, HttpResponseException -> 0x038d, Exception -> 0x0379, all -> 0x0357 }
        r0 = r0.createSocket();	 Catch:{ EOFException -> 0x03c9, SSLException -> 0x03b5, SocketException -> 0x03a1, HttpResponseException -> 0x038d, Exception -> 0x0379, all -> 0x0357 }
        r8.c = r0;	 Catch:{ EOFException -> 0x03c9, SSLException -> 0x03b5, SocketException -> 0x03a1, HttpResponseException -> 0x038d, Exception -> 0x0379, all -> 0x0357 }
        r0 = r13.a;	 Catch:{ EOFException -> 0x03c9, SSLException -> 0x03b5, SocketException -> 0x03a1, HttpResponseException -> 0x038d, Exception -> 0x0379, all -> 0x0357 }
        r0 = r0.c;	 Catch:{ EOFException -> 0x03c9, SSLException -> 0x03b5, SocketException -> 0x03a1, HttpResponseException -> 0x038d, Exception -> 0x0379, all -> 0x0357 }
        r8 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
        r0.connect(r3, r8);	 Catch:{ EOFException -> 0x03c9, SSLException -> 0x03b5, SocketException -> 0x03a1, HttpResponseException -> 0x038d, Exception -> 0x0379, all -> 0x0357 }
        r3 = new java.io.PrintWriter;	 Catch:{ EOFException -> 0x03c9, SSLException -> 0x03b5, SocketException -> 0x03a1, HttpResponseException -> 0x038d, Exception -> 0x0379, all -> 0x0357 }
        r0 = r13.a;	 Catch:{ EOFException -> 0x03c9, SSLException -> 0x03b5, SocketException -> 0x03a1, HttpResponseException -> 0x038d, Exception -> 0x0379, all -> 0x0357 }
        r0 = r0.c;	 Catch:{ EOFException -> 0x03c9, SSLException -> 0x03b5, SocketException -> 0x03a1, HttpResponseException -> 0x038d, Exception -> 0x0379, all -> 0x0357 }
        r0 = r0.getOutputStream();	 Catch:{ EOFException -> 0x03c9, SSLException -> 0x03b5, SocketException -> 0x03a1, HttpResponseException -> 0x038d, Exception -> 0x0379, all -> 0x0357 }
        r3.<init>(r0);	 Catch:{ EOFException -> 0x03c9, SSLException -> 0x03b5, SocketException -> 0x03a1, HttpResponseException -> 0x038d, Exception -> 0x0379, all -> 0x0357 }
        r0 = new java.lang.StringBuilder;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0.<init>();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r8 = "GET ";
        r0 = r0.append(r8);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = r0.append(r7);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r7 = " HTTP/1.1\r\n";
        r0 = r0.append(r7);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = r0.toString();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r3.print(r0);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = "Upgrade: websocket\r\n";
        r3.print(r0);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = "Connection: Upgrade\r\n";
        r3.print(r0);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = new java.lang.StringBuilder;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0.<init>();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r7 = "Host: ";
        r0 = r0.append(r7);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = r0.append(r5);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r7 = "\r\n";
        r0 = r0.append(r7);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = r0.toString();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r3.print(r0);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = new java.lang.StringBuilder;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0.<init>();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r7 = "Origin: ";
        r0 = r0.append(r7);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r7 = r11.toString();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = r0.append(r7);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r7 = "\r\n";
        r0 = r0.append(r7);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = r0.toString();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r3.print(r0);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = new java.lang.StringBuilder;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0.<init>();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r7 = "Sec-WebSocket-Key: ";
        r0 = r0.append(r7);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = r0.append(r9);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r7 = "\r\n";
        r0 = r0.append(r7);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = r0.toString();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r3.print(r0);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = "Sec-WebSocket-Version: 13\r\n";
        r3.print(r0);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = r13.a;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = r0.f;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        if (r0 == 0) goto L_0x01da;
    L_0x016b:
        r0 = r13.a;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = r0.f;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r7 = r0.iterator();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
    L_0x0175:
        r0 = r7.hasNext();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        if (r0 == 0) goto L_0x01da;
    L_0x017b:
        r0 = r7.next();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = (cz.msebera.android.httpclient.NameValuePair) r0;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r8 = "%s: %s\r\n";
        r10 = 2;
        r10 = new java.lang.Object[r10];	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r11 = 0;
        r12 = r0.getName();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r10[r11] = r12;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r11 = 1;
        r0 = r0.getValue();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r10[r11] = r0;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = java.lang.String.format(r8, r10);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r3.print(r0);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        goto L_0x0175;
    L_0x019c:
        r0 = move-exception;
        r1 = r3;
        r2 = r4;
        r3 = r5;
    L_0x01a0:
        r4 = "websocket";
        r5 = "websocket EOF!";
        qsbk.app.core.utils.LogUtils.e(r4, r5, r0);	 Catch:{ all -> 0x0366 }
        r0 = r13.a;	 Catch:{ all -> 0x0366 }
        r4 = 0;
        r5 = "EOF";
        r0.a(r4, r5);	 Catch:{ all -> 0x0366 }
        r13.reportHttpDnsError(r3, r2);	 Catch:{ all -> 0x0366 }
        if (r1 == 0) goto L_0x01b7;
    L_0x01b4:
        r1.close();	 Catch:{ Exception -> 0x0351 }
    L_0x01b7:
        return;
    L_0x01b8:
        if (r10 == 0) goto L_0x01bf;
    L_0x01ba:
        r0 = 443; // 0x1bb float:6.21E-43 double:2.19E-321;
        r8 = r0;
        goto L_0x0040;
    L_0x01bf:
        r0 = 80;
        r8 = r0;
        goto L_0x0040;
    L_0x01c4:
        r0 = r13.a;	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r0 = r0.a;	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        r0 = r0.getPath();	 Catch:{ EOFException -> 0x03be, SSLException -> 0x03ab, SocketException -> 0x0397, HttpResponseException -> 0x0383, Exception -> 0x036f, all -> 0x0357 }
        goto L_0x0052;
    L_0x01d0:
        r0 = "http";
        goto L_0x0088;
    L_0x01d4:
        r0 = javax.net.SocketFactory.getDefault();	 Catch:{ EOFException -> 0x03c9, SSLException -> 0x03b5, SocketException -> 0x03a1, HttpResponseException -> 0x038d, Exception -> 0x0379, all -> 0x0357 }
        goto L_0x00bd;
    L_0x01da:
        r0 = "\r\n";
        r3.print(r0);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r3.flush();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r7 = new qsbk.app.core.utils.websocket.HybiParser$HappyDataInputStream;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = r13.a;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = r0.c;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = r0.getInputStream();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r7.<init>(r0);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = r13.a;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r8 = r13.a;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r8 = r8.a(r7);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r8 = r0.a(r8);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        if (r8 != 0) goto L_0x0225;
    L_0x01ff:
        r0 = new cz.msebera.android.httpclient.HttpException;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r1 = "Received no reply from server.";
        r0.<init>(r1);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        throw r0;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
    L_0x0207:
        r0 = move-exception;
    L_0x0208:
        r1 = "websocket";
        r2 = "websocket SSL error!";
        qsbk.app.core.utils.LogUtils.e(r1, r2, r0);	 Catch:{ all -> 0x0364 }
        r0 = r13.a;	 Catch:{ all -> 0x0364 }
        r1 = 0;
        r2 = "SSL";
        r0.a(r1, r2);	 Catch:{ all -> 0x0364 }
        r13.reportHttpDnsError(r5, r4);	 Catch:{ all -> 0x0364 }
        if (r3 == 0) goto L_0x01b7;
    L_0x021c:
        r3.close();	 Catch:{ Exception -> 0x0220 }
        goto L_0x01b7;
    L_0x0220:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x01b7;
    L_0x0225:
        r0 = r8.getStatusCode();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r10 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r0 == r10) goto L_0x027e;
    L_0x022d:
        r0 = r13.a;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = r0.parseResponseError(r7);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        if (r1 != 0) goto L_0x0244;
    L_0x0239:
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0278 }
        r1.<init>(r0);	 Catch:{ JSONException -> 0x0278 }
        r0 = "m";
        r0 = r1.optString(r0);	 Catch:{ JSONException -> 0x0278 }
    L_0x0244:
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        if (r1 == 0) goto L_0x024e;
    L_0x024a:
        r0 = r8.getReasonPhrase();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
    L_0x024e:
        r1 = new cz.msebera.android.httpclient.client.HttpResponseException;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r2 = r8.getStatusCode();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r1.<init>(r2, r0);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        throw r1;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
    L_0x0258:
        r0 = move-exception;
    L_0x0259:
        r1 = "websocket";
        r2 = "websocket socket error!";
        qsbk.app.core.utils.LogUtils.e(r1, r2, r0);	 Catch:{ all -> 0x0364 }
        r0 = r13.a;	 Catch:{ all -> 0x0364 }
        r1 = 0;
        r2 = "Socket Error";
        r0.a(r1, r2);	 Catch:{ all -> 0x0364 }
        r13.reportHttpDnsError(r5, r4);	 Catch:{ all -> 0x0364 }
        if (r3 == 0) goto L_0x01b7;
    L_0x026d:
        r3.close();	 Catch:{ Exception -> 0x0272 }
        goto L_0x01b7;
    L_0x0272:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x01b7;
    L_0x0278:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = r2;
        goto L_0x0244;
    L_0x027e:
        r0 = r6;
    L_0x027f:
        r2 = r13.a;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r2 = r2.a(r7);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r6 = android.text.TextUtils.isEmpty(r2);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        if (r6 != 0) goto L_0x02e0;
    L_0x028b:
        r6 = r13.a;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r2 = r6.b(r2);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r6 = r2.getName();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r8 = "Sec-WebSocket-Accept";
        r6 = r6.equals(r8);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        if (r6 == 0) goto L_0x027f;
    L_0x029d:
        r0 = r13.a;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = r0.c(r9);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r2 = r2.getValue();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r2 = r2.trim();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = r0.equals(r2);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        if (r0 != 0) goto L_0x02de;
    L_0x02b1:
        r0 = new cz.msebera.android.httpclient.HttpException;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r1 = "Bad Sec-WebSocket-Accept header value.";
        r0.<init>(r1);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        throw r0;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
    L_0x02b9:
        r0 = move-exception;
    L_0x02ba:
        r1 = "websocket";
        r2 = "websocket response error!";
        qsbk.app.core.utils.LogUtils.e(r1, r2, r0);	 Catch:{ all -> 0x0364 }
        r1 = r13.a;	 Catch:{ all -> 0x0364 }
        r2 = r0.getStatusCode();	 Catch:{ all -> 0x0364 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0364 }
        r1.a(r2, r0);	 Catch:{ all -> 0x0364 }
        r13.reportHttpDnsError(r5, r4);	 Catch:{ all -> 0x0364 }
        if (r3 == 0) goto L_0x01b7;
    L_0x02d3:
        r3.close();	 Catch:{ Exception -> 0x02d8 }
        goto L_0x01b7;
    L_0x02d8:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x01b7;
    L_0x02de:
        r0 = r1;
        goto L_0x027f;
    L_0x02e0:
        if (r0 != 0) goto L_0x0319;
    L_0x02e2:
        r0 = new cz.msebera.android.httpclient.HttpException;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r1 = "No Sec-WebSocket-Accept header.";
        r0.<init>(r1);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        throw r0;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
    L_0x02ea:
        r0 = move-exception;
    L_0x02eb:
        r1 = "websocket";
        r2 = "websocket error!";
        qsbk.app.core.utils.LogUtils.e(r1, r2, r0);	 Catch:{ all -> 0x0364 }
        r1 = r13.a;	 Catch:{ all -> 0x0364 }
        r1 = r1.isDisconnected();	 Catch:{ all -> 0x0364 }
        if (r1 != 0) goto L_0x0300;
    L_0x02fa:
        r1 = r13.a;	 Catch:{ all -> 0x0364 }
        r2 = 0;
        r1.h = r2;	 Catch:{ all -> 0x0364 }
    L_0x0300:
        r1 = r13.a;	 Catch:{ all -> 0x0364 }
        r1 = r1.b;	 Catch:{ all -> 0x0364 }
        r1.onError(r0);	 Catch:{ all -> 0x0364 }
        r13.reportHttpDnsError(r5, r4);	 Catch:{ all -> 0x0364 }
        if (r3 == 0) goto L_0x01b7;
    L_0x030e:
        r3.close();	 Catch:{ Exception -> 0x0313 }
        goto L_0x01b7;
    L_0x0313:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x01b7;
    L_0x0319:
        r0 = r13.a;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r1 = 2;
        r0.h = r1;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = r13.a;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = r0.b;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0.onConnect();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = android.text.TextUtils.isEmpty(r4);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        if (r0 != 0) goto L_0x033b;
    L_0x032e:
        r0 = android.text.TextUtils.equals(r4, r5);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        if (r0 != 0) goto L_0x033b;
    L_0x0334:
        r0 = com.qiushibaike.httpdns.lib.HttpDNSManager.instance();	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0.reportOK(r5, r4);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
    L_0x033b:
        r0 = r13.a;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0 = r0.g;	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        r0.start(r7);	 Catch:{ EOFException -> 0x019c, SSLException -> 0x0207, SocketException -> 0x0258, HttpResponseException -> 0x02b9, Exception -> 0x02ea }
        if (r3 == 0) goto L_0x01b7;
    L_0x0346:
        r3.close();	 Catch:{ Exception -> 0x034b }
        goto L_0x01b7;
    L_0x034b:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x01b7;
    L_0x0351:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x01b7;
    L_0x0357:
        r0 = move-exception;
        r3 = r2;
    L_0x0359:
        if (r3 == 0) goto L_0x035e;
    L_0x035b:
        r3.close();	 Catch:{ Exception -> 0x035f }
    L_0x035e:
        throw r0;
    L_0x035f:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x035e;
    L_0x0364:
        r0 = move-exception;
        goto L_0x0359;
    L_0x0366:
        r0 = move-exception;
        r3 = r1;
        goto L_0x0359;
    L_0x0369:
        r0 = move-exception;
        r3 = r2;
        r4 = r2;
        r5 = r2;
        goto L_0x02eb;
    L_0x036f:
        r0 = move-exception;
        r3 = r2;
        r4 = r2;
        goto L_0x02eb;
    L_0x0374:
        r0 = move-exception;
        r4 = r3;
        r3 = r2;
        goto L_0x02eb;
    L_0x0379:
        r0 = move-exception;
        r3 = r2;
        goto L_0x02eb;
    L_0x037d:
        r0 = move-exception;
        r3 = r2;
        r4 = r2;
        r5 = r2;
        goto L_0x02ba;
    L_0x0383:
        r0 = move-exception;
        r3 = r2;
        r4 = r2;
        goto L_0x02ba;
    L_0x0388:
        r0 = move-exception;
        r4 = r3;
        r3 = r2;
        goto L_0x02ba;
    L_0x038d:
        r0 = move-exception;
        r3 = r2;
        goto L_0x02ba;
    L_0x0391:
        r0 = move-exception;
        r3 = r2;
        r4 = r2;
        r5 = r2;
        goto L_0x0259;
    L_0x0397:
        r0 = move-exception;
        r3 = r2;
        r4 = r2;
        goto L_0x0259;
    L_0x039c:
        r0 = move-exception;
        r4 = r3;
        r3 = r2;
        goto L_0x0259;
    L_0x03a1:
        r0 = move-exception;
        r3 = r2;
        goto L_0x0259;
    L_0x03a5:
        r0 = move-exception;
        r3 = r2;
        r4 = r2;
        r5 = r2;
        goto L_0x0208;
    L_0x03ab:
        r0 = move-exception;
        r3 = r2;
        r4 = r2;
        goto L_0x0208;
    L_0x03b0:
        r0 = move-exception;
        r4 = r3;
        r3 = r2;
        goto L_0x0208;
    L_0x03b5:
        r0 = move-exception;
        r3 = r2;
        goto L_0x0208;
    L_0x03b9:
        r0 = move-exception;
        r1 = r2;
        r3 = r2;
        goto L_0x01a0;
    L_0x03be:
        r0 = move-exception;
        r1 = r2;
        r3 = r5;
        goto L_0x01a0;
    L_0x03c3:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        r3 = r5;
        goto L_0x01a0;
    L_0x03c9:
        r0 = move-exception;
        r1 = r2;
        r3 = r5;
        r2 = r4;
        goto L_0x01a0;
    L_0x03cf:
        r4 = r3;
        goto L_0x00b0;
    L_0x03d2:
        r7 = r0;
        goto L_0x0084;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.core.utils.websocket.b.run():void");
    }

    public void reportHttpDnsError(String str, String str2) {
        if (!TextUtils.isEmpty(str2) && !TextUtils.equals(str2, str)) {
            HttpDNSManager.instance().reportError(str, str2);
        }
    }
}
