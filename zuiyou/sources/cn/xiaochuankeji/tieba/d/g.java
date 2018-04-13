package cn.xiaochuankeji.tieba.d;

import android.telephony.TelephonyManager;
import android.text.TextUtils;
import cn.xiaochuan.base.BaseApplication;
import com.tencent.bugly.beta.tinker.TinkerReport;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.URL;

public class g {

    public static class a {
        public String a;
        public String b;
        public String c;
        public String d;
    }

    public static HttpURLConnection a(URL url, String str) throws IOException {
        HttpURLConnection httpURLConnection;
        int i = 0;
        String str2 = str;
        while (true) {
            int i2 = i + 1;
            if (i <= 20) {
                httpURLConnection = (HttpURLConnection) url.openConnection();
                if (str2 == null) {
                    str2 = "Mozilla/5.0 (Linux; U; Android 4.0.2; en-us; Galaxy Nexus Build/ICL53F) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";
                }
                httpURLConnection.addRequestProperty("User-Agent", str2);
                httpURLConnection.setInstanceFollowRedirects(true);
                httpURLConnection.connect();
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 300 || responseCode == 301 || responseCode == 302 || (responseCode == 303 && (responseCode == TinkerReport.KEY_LOADED_MISSING_DEX_OPT || responseCode == TinkerReport.KEY_LOADED_MISSING_RES))) {
                    String headerField = httpURLConnection.getHeaderField("Location");
                    httpURLConnection.disconnect();
                    url = b(url, headerField);
                    i = i2;
                }
            } else {
                throw new NoRouteToHostException("Too many redirects: " + i2);
            }
        }
        return httpURLConnection;
    }

    public static String a(HttpURLConnection httpURLConnection) {
        try {
            InputStream inputStream = httpURLConnection.getInputStream();
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
            while (true) {
                int read = inputStream.read(bArr);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    String str = new String(byteArrayOutputStream.toByteArray(), "utf-8");
                    inputStream.close();
                    byteArrayOutputStream.close();
                    return str;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void b(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    private static URL b(URL url, String str) throws IOException {
        if (str == null) {
            throw new ProtocolException("Null location redirect");
        }
        URL url2 = new URL(url, str);
        String protocol = url2.getProtocol();
        if ("https".equals(protocol) || "http".equals(protocol)) {
            return url2;
        }
        throw new ProtocolException("Unsupported protocol redirect: " + protocol);
    }

    public static cn.xiaochuankeji.tieba.d.g.a a() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
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
        r6 = 1;
        r1 = 0;
        r5 = new cn.xiaochuankeji.tieba.d.g$a;
        r5.<init>();
        r0 = cn.xiaochuan.base.BaseApplication.getAppContext();
        r3 = r0.getApplicationContext();
        r0 = "connectivity";
        r0 = r3.getSystemService(r0);
        r0 = (android.net.ConnectivityManager) r0;
        if (r0 == 0) goto L_0x0120;
    L_0x001a:
        r0 = r0.getActiveNetworkInfo();
        if (r0 == 0) goto L_0x0120;
    L_0x0020:
        r2 = r0.getState();
        r4 = android.net.NetworkInfo.State.CONNECTED;
        if (r2 != r4) goto L_0x0107;
    L_0x0028:
        r4 = "已联网";
        r2 = r0.getType();
        if (r2 != r6) goto L_0x009b;
    L_0x0031:
        r2 = "wifi";
        r0 = "wifi";
        r0 = r3.getSystemService(r0);
        r0 = (android.net.wifi.WifiManager) r0;
        if (r0 == 0) goto L_0x011d;
    L_0x003f:
        r3 = r0.getConnectionInfo();
        r0 = r0.getDhcpInfo();
        if (r3 == 0) goto L_0x011d;
    L_0x0049:
        if (r0 == 0) goto L_0x011d;
    L_0x004b:
        r1 = r3.getIpAddress();
        r1 = a(r1);
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r6 = r0.dns1;
        r6 = a(r6);
        r3 = r3.append(r6);
        r6 = ", ";
        r3 = r3.append(r6);
        r0 = r0.dns2;
        r0 = a(r0);
        r0 = r3.append(r0);
        r0 = r0.toString();
    L_0x0077:
        r9 = r0;
        r0 = r1;
        r1 = r9;
    L_0x007a:
        r3 = r4;
        r9 = r0;
        r0 = r1;
        r1 = r9;
    L_0x007e:
        if (r3 != 0) goto L_0x0083;
    L_0x0080:
        r3 = "未知";
    L_0x0083:
        if (r2 != 0) goto L_0x0088;
    L_0x0085:
        r2 = "未知";
    L_0x0088:
        if (r1 != 0) goto L_0x008d;
    L_0x008a:
        r1 = "未知";
    L_0x008d:
        if (r0 != 0) goto L_0x0092;
    L_0x008f:
        r0 = "未知";
    L_0x0092:
        r5.a = r3;
        r5.d = r0;
        r5.b = r1;
        r5.c = r2;
        return r5;
    L_0x009b:
        if (r2 != 0) goto L_0x0119;
    L_0x009d:
        r0 = r0.getSubtype();
        r2 = 4;
        if (r0 == r2) goto L_0x00a9;
    L_0x00a4:
        if (r0 == r6) goto L_0x00a9;
    L_0x00a6:
        r2 = 2;
        if (r0 != r2) goto L_0x00dd;
    L_0x00a9:
        r3 = "2G";
    L_0x00ac:
        r6 = java.net.NetworkInterface.getNetworkInterfaces();	 Catch:{ SocketException -> 0x00fe }
        r2 = r1;
    L_0x00b1:
        r0 = r6.hasMoreElements();	 Catch:{ SocketException -> 0x0110 }
        if (r0 == 0) goto L_0x00fa;	 Catch:{ SocketException -> 0x0110 }
    L_0x00b7:
        r0 = r6.nextElement();	 Catch:{ SocketException -> 0x0110 }
        r0 = (java.net.NetworkInterface) r0;	 Catch:{ SocketException -> 0x0110 }
        r7 = r0.getInetAddresses();	 Catch:{ SocketException -> 0x0110 }
    L_0x00c1:
        r0 = r7.hasMoreElements();	 Catch:{ SocketException -> 0x0110 }
        if (r0 == 0) goto L_0x00b1;	 Catch:{ SocketException -> 0x0110 }
    L_0x00c7:
        r0 = r7.nextElement();	 Catch:{ SocketException -> 0x0110 }
        r0 = (java.net.InetAddress) r0;	 Catch:{ SocketException -> 0x0110 }
        r8 = r0.isLoopbackAddress();	 Catch:{ SocketException -> 0x0110 }
        if (r8 != 0) goto L_0x0115;	 Catch:{ SocketException -> 0x0110 }
    L_0x00d3:
        r8 = r0 instanceof java.net.Inet4Address;	 Catch:{ SocketException -> 0x0110 }
        if (r8 == 0) goto L_0x0115;	 Catch:{ SocketException -> 0x0110 }
    L_0x00d7:
        r0 = r0.getHostAddress();	 Catch:{ SocketException -> 0x0110 }
    L_0x00db:
        r2 = r0;
        goto L_0x00c1;
    L_0x00dd:
        r2 = 3;
        if (r0 == r2) goto L_0x00ee;
    L_0x00e0:
        r2 = 8;
        if (r0 == r2) goto L_0x00ee;
    L_0x00e4:
        r2 = 6;
        if (r0 == r2) goto L_0x00ee;
    L_0x00e7:
        r2 = 5;
        if (r0 == r2) goto L_0x00ee;
    L_0x00ea:
        r2 = 12;
        if (r0 != r2) goto L_0x00f2;
    L_0x00ee:
        r3 = "3G";
        goto L_0x00ac;
    L_0x00f2:
        r2 = 13;
        if (r0 != r2) goto L_0x0117;
    L_0x00f6:
        r3 = "4G";
        goto L_0x00ac;
    L_0x00fa:
        r0 = r2;
        r2 = r3;
        goto L_0x007a;
    L_0x00fe:
        r0 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x0101:
        r2.printStackTrace();
        r2 = r3;
        goto L_0x007a;
    L_0x0107:
        r3 = "未联网";
        r2 = "未连接";
        r0 = r1;
        goto L_0x007e;
    L_0x0110:
        r0 = move-exception;
        r9 = r0;
        r0 = r2;
        r2 = r9;
        goto L_0x0101;
    L_0x0115:
        r0 = r2;
        goto L_0x00db;
    L_0x0117:
        r3 = r1;
        goto L_0x00ac;
    L_0x0119:
        r0 = r1;
        r2 = r1;
        goto L_0x007a;
    L_0x011d:
        r0 = r1;
        goto L_0x0077;
    L_0x0120:
        r0 = r1;
        r2 = r1;
        r3 = r1;
        goto L_0x007e;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xiaochuankeji.tieba.d.g.a():cn.xiaochuankeji.tieba.d.g$a");
    }

    public static String b() {
        String subscriberId = ((TelephonyManager) BaseApplication.getAppContext().getApplicationContext().getSystemService("phone")).getSubscriberId();
        if (TextUtils.isEmpty(subscriberId)) {
            return "unknown";
        }
        if (subscriberId.startsWith("46000") || subscriberId.startsWith("46002")) {
            return "中国移动";
        }
        if (subscriberId.startsWith("46001")) {
            return "中国联通";
        }
        return subscriberId.startsWith("46003") ? "中国电信" : null;
    }

    private static String a(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }
}
