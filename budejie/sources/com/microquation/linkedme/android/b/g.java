package com.microquation.linkedme.android.b;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.ali.auth.third.login.LoginConstants;
import com.microquation.linkedme.android.a;
import com.microquation.linkedme.android.f.b;
import com.microquation.linkedme.android.util.c;
import com.microquation.linkedme.android.util.f;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class g {
    private static int d = 4096;
    protected b a;
    protected final a b;
    private int c = 0;

    public g(Context context) {
        this.a = b.a(context);
        this.b = new a(d);
    }

    private u a(InputStream inputStream, int i, String str, boolean z) {
        BufferedReader bufferedReader;
        IOException e;
        Throwable th;
        u uVar = new u(str, i);
        if (inputStream != null) {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                try {
                    String readLine = bufferedReader.readLine();
                    if (z && (str.contains(com.microquation.linkedme.android.util.c.g.RegisterOpen.a()) || str.contains(com.microquation.linkedme.android.util.c.g.RegisterClose.a()))) {
                        b.a(a.a, "returned" + readLine);
                    } else {
                        b.a("returned" + readLine);
                    }
                    if (readLine != null) {
                        try {
                            uVar.a(new JSONObject(readLine));
                        } catch (JSONException e2) {
                            try {
                                uVar.a(new JSONArray(readLine));
                            } catch (JSONException e3) {
                                if (z) {
                                    b.a(getClass().getSimpleName(), "JSON exception: " + e3.getMessage());
                                }
                            }
                        }
                    }
                } catch (IOException e4) {
                    e = e4;
                    if (z) {
                        try {
                            b.a(getClass().getSimpleName(), "IO exception: " + e.getMessage());
                        } catch (Throwable th2) {
                            th = th2;
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                    if (z) {
                                        b.a(getClass().getSimpleName(), "IO exception: " + e5.getMessage());
                                    }
                                    throw th;
                                }
                            }
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            throw th;
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                            if (z) {
                                b.a(getClass().getSimpleName(), "IO exception: " + e6.getMessage());
                            }
                        }
                    }
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    return uVar;
                }
            } catch (IOException e7) {
                e6 = e7;
                bufferedReader = null;
                if (z) {
                    b.a(getClass().getSimpleName(), "IO exception: " + e6.getMessage());
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return uVar;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
        }
        bufferedReader = null;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e62) {
                e62.printStackTrace();
                if (z) {
                    b.a(getClass().getSimpleName(), "IO exception: " + e62.getMessage());
                }
            }
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        return uVar;
    }

    private com.microquation.linkedme.android.b.u a(java.lang.String r12, org.json.JSONObject r13, java.lang.String r14, int r15, int r16, boolean r17) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x00e6 in list [B:55:0x0170]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r11 = this;
        r3 = new org.json.JSONObject;
        r3.<init>();
        r9 = 0;
        if (r15 > 0) goto L_0x0340;
    L_0x0008:
        r6 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
    L_0x000a:
        r2 = "lm_type=image";
        r2 = r12.contains(r2);
        if (r2 == 0) goto L_0x00e7;
    L_0x0012:
        r2 = r12;
    L_0x0013:
        if (r17 == 0) goto L_0x012a;
    L_0x0015:
        r3 = com.microquation.linkedme.android.util.c.g.RegisterOpen;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r3 = r3.a();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r3 = r12.contains(r3);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        if (r3 != 0) goto L_0x002d;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
    L_0x0021:
        r3 = com.microquation.linkedme.android.util.c.g.RegisterClose;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r3 = r3.a();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r3 = r12.contains(r3);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        if (r3 == 0) goto L_0x012a;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
    L_0x002d:
        r3 = com.microquation.linkedme.android.a.a;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r4 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r4.<init>();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r5 = "getting ";	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r4 = r4.append(r5);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r4 = r4.append(r2);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r4 = r4.toString();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        com.microquation.linkedme.android.f.b.a(r3, r4);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
    L_0x0045:
        r3 = 0;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r11.c = r3;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r3 = new java.net.URL;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r3.<init>(r2);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r2 = android.os.Build.VERSION.SDK_INT;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r7 = 19;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        if (r2 >= r7) goto L_0x006f;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
    L_0x0057:
        r2 = "TLSv1";	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r2 = javax.net.ssl.SSLContext.getInstance(r2);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r7 = 0;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r8 = 0;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r10 = 0;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r2.init(r7, r8, r10);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r7 = new com.microquation.linkedme.android.b.e;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r2 = r2.getSocketFactory();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r7.<init>(r2);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(r7);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
    L_0x006f:
        r2 = r3.openConnection();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r0 = r2;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r9 = r0;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r9.setConnectTimeout(r6);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r9.setReadTimeout(r6);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r2 = 1;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r9.setDoInput(r2);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r2 = r2 - r4;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r2 = (int) r2;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r11.c = r2;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r2 = com.microquation.linkedme.android.a.a();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        if (r2 == 0) goto L_0x00b9;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
    L_0x008f:
        r2 = com.microquation.linkedme.android.a.a();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r3 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r3.<init>();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r3 = r3.append(r14);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r4 = "-";	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r3 = r3.append(r4);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r4 = com.microquation.linkedme.android.util.c.a.Last_Round_Trip_Time;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r4 = r4.a();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r3 = r3.append(r4);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r3 = r3.toString();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r4 = r11.c;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r4 = java.lang.String.valueOf(r4);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r2.a(r3, r4);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
    L_0x00b9:
        r2 = r9.getResponseCode();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r3 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        if (r2 < r3) goto L_0x01a3;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
    L_0x00c1:
        r2 = r11.a;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r2 = r2.e();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r0 = r16;
        if (r0 >= r2) goto L_0x01a3;
    L_0x00cb:
        r2 = r11.a;	 Catch:{ InterruptedException -> 0x0175 }
        r2 = r2.f();	 Catch:{ InterruptedException -> 0x0175 }
        r2 = (long) r2;	 Catch:{ InterruptedException -> 0x0175 }
        java.lang.Thread.sleep(r2);	 Catch:{ InterruptedException -> 0x0175 }
    L_0x00d5:
        r7 = r16 + 1;
        r2 = r11;
        r3 = r12;
        r4 = r13;
        r5 = r14;
        r8 = r17;
        r2 = r2.a(r3, r4, r5, r6, r7, r8);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        if (r9 == 0) goto L_0x00e6;
    L_0x00e3:
        r9.disconnect();
    L_0x00e6:
        return r2;
    L_0x00e7:
        r0 = r16;
        r2 = r11.a(r3, r0);
        if (r2 == 0) goto L_0x0122;
    L_0x00ef:
        if (r13 == 0) goto L_0x010b;
    L_0x00f1:
        r4 = r13.keys();
    L_0x00f5:
        r2 = r4.hasNext();
        if (r2 == 0) goto L_0x010b;
    L_0x00fb:
        r2 = r4.next();
        r2 = (java.lang.String) r2;
        r5 = r13.getString(r2);	 Catch:{ JSONException -> 0x0109 }
        r3.put(r2, r5);	 Catch:{ JSONException -> 0x0109 }
        goto L_0x00f5;
    L_0x0109:
        r2 = move-exception;
        goto L_0x00f5;
    L_0x010b:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2 = r2.append(r12);
        r3 = r11.b(r3);
        r2 = r2.append(r3);
        r2 = r2.toString();
        goto L_0x0013;
    L_0x0122:
        r2 = new com.microquation.linkedme.android.b.u;
        r3 = -1234; // 0xfffffffffffffb2e float:NaN double:NaN;
        r2.<init>(r14, r3);
        goto L_0x00e6;
    L_0x012a:
        r3 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r3.<init>();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r4 = "getting ";	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r3 = r3.append(r4);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r3 = r3.append(r2);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r3 = r3.toString();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        com.microquation.linkedme.android.f.b.a(r3);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        goto L_0x0045;
    L_0x0142:
        r2 = move-exception;
        if (r17 == 0) goto L_0x0167;
    L_0x0145:
        r3 = r11.getClass();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r3 = r3.getSimpleName();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r4 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r4.<init>();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r5 = "Http connect exception: ";	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r4 = r4.append(r5);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2 = r2.getMessage();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2 = r4.append(r2);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2 = r2.toString();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        com.microquation.linkedme.android.f.b.a(r3, r2);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
    L_0x0167:
        r2 = new com.microquation.linkedme.android.b.u;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r3 = -1009; // 0xfffffffffffffc0f float:NaN double:NaN;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2.<init>(r14, r3);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        if (r9 == 0) goto L_0x00e6;
    L_0x0170:
        r9.disconnect();
        goto L_0x00e6;
    L_0x0175:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        goto L_0x00d5;
    L_0x017b:
        r2 = move-exception;
    L_0x017c:
        r2 = r11.a;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2 = r2.e();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r0 = r16;
        if (r0 >= r2) goto L_0x022e;
    L_0x0186:
        r2 = r11.a;	 Catch:{ InterruptedException -> 0x0221 }
        r2 = r2.f();	 Catch:{ InterruptedException -> 0x0221 }
        r2 = (long) r2;	 Catch:{ InterruptedException -> 0x0221 }
        java.lang.Thread.sleep(r2);	 Catch:{ InterruptedException -> 0x0221 }
    L_0x0190:
        r7 = r16 + 1;
        r2 = r11;
        r3 = r12;
        r4 = r13;
        r5 = r14;
        r8 = r17;
        r2 = r2.a(r3, r4, r5, r6, r7, r8);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        if (r9 == 0) goto L_0x00e6;
    L_0x019e:
        r9.disconnect();
        goto L_0x00e6;
    L_0x01a3:
        r2 = r9.getResponseCode();	 Catch:{ FileNotFoundException -> 0x01f4 }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;	 Catch:{ FileNotFoundException -> 0x01f4 }
        if (r2 == r3) goto L_0x01c6;	 Catch:{ FileNotFoundException -> 0x01f4 }
    L_0x01ab:
        r2 = r9.getErrorStream();	 Catch:{ FileNotFoundException -> 0x01f4 }
        if (r2 == 0) goto L_0x01c6;	 Catch:{ FileNotFoundException -> 0x01f4 }
    L_0x01b1:
        r2 = r9.getErrorStream();	 Catch:{ FileNotFoundException -> 0x01f4 }
        r3 = r9.getResponseCode();	 Catch:{ FileNotFoundException -> 0x01f4 }
        r0 = r17;	 Catch:{ FileNotFoundException -> 0x01f4 }
        r2 = r11.a(r2, r3, r14, r0);	 Catch:{ FileNotFoundException -> 0x01f4 }
        if (r9 == 0) goto L_0x00e6;
    L_0x01c1:
        r9.disconnect();
        goto L_0x00e6;
    L_0x01c6:
        r2 = "lm_type=image";	 Catch:{ FileNotFoundException -> 0x01f4 }
        r2 = r14.contains(r2);	 Catch:{ FileNotFoundException -> 0x01f4 }
        if (r2 == 0) goto L_0x01df;	 Catch:{ FileNotFoundException -> 0x01f4 }
    L_0x01ce:
        r2 = r9.getResponseCode();	 Catch:{ FileNotFoundException -> 0x01f4 }
        r0 = r17;	 Catch:{ FileNotFoundException -> 0x01f4 }
        r2 = r11.a(r9, r2, r14, r0);	 Catch:{ FileNotFoundException -> 0x01f4 }
        if (r9 == 0) goto L_0x00e6;
    L_0x01da:
        r9.disconnect();
        goto L_0x00e6;
    L_0x01df:
        r2 = r9.getInputStream();	 Catch:{ FileNotFoundException -> 0x01f4 }
        r3 = r9.getResponseCode();	 Catch:{ FileNotFoundException -> 0x01f4 }
        r0 = r17;	 Catch:{ FileNotFoundException -> 0x01f4 }
        r2 = r11.a(r2, r3, r14, r0);	 Catch:{ FileNotFoundException -> 0x01f4 }
        if (r9 == 0) goto L_0x00e6;
    L_0x01ef:
        r9.disconnect();
        goto L_0x00e6;
    L_0x01f4:
        r2 = move-exception;
        if (r17 == 0) goto L_0x020f;
    L_0x01f7:
        r2 = com.microquation.linkedme.android.a.a;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r3 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r3.<init>();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r4 = "A resource conflict occurred with this request ";	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r3 = r3.append(r4);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r3 = r3.append(r14);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r3 = r3.toString();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        com.microquation.linkedme.android.f.b.a(r2, r3);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
    L_0x020f:
        r2 = 0;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r3 = r9.getResponseCode();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r0 = r17;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        r2 = r11.a(r2, r3, r14, r0);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x017b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308 }
        if (r9 == 0) goto L_0x00e6;
    L_0x021c:
        r9.disconnect();
        goto L_0x00e6;
    L_0x0221:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        goto L_0x0190;
    L_0x0227:
        r2 = move-exception;
        if (r9 == 0) goto L_0x022d;
    L_0x022a:
        r9.disconnect();
    L_0x022d:
        throw r2;
    L_0x022e:
        r2 = new com.microquation.linkedme.android.b.u;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r3 = -111; // 0xffffffffffffff91 float:NaN double:NaN;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2.<init>(r14, r3);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        if (r9 == 0) goto L_0x00e6;
    L_0x0237:
        r9.disconnect();
        goto L_0x00e6;
    L_0x023c:
        r2 = move-exception;
        if (r17 == 0) goto L_0x0261;
    L_0x023f:
        r3 = r11.getClass();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r3 = r3.getSimpleName();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r4 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r4.<init>();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r5 = "Http connect exception: ";	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r4 = r4.append(r5);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2 = r2.getMessage();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2 = r4.append(r2);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2 = r2.toString();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        com.microquation.linkedme.android.f.b.a(r3, r2);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
    L_0x0261:
        r2 = new com.microquation.linkedme.android.b.u;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r3 = -1009; // 0xfffffffffffffc0f float:NaN double:NaN;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2.<init>(r14, r3);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        if (r9 == 0) goto L_0x00e6;
    L_0x026a:
        r9.disconnect();
        goto L_0x00e6;
    L_0x026f:
        r2 = move-exception;
        if (r17 == 0) goto L_0x0294;
    L_0x0272:
        r3 = r11.getClass();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r3 = r3.getSimpleName();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r4 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r4.<init>();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r5 = "IO exception: ";	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r4 = r4.append(r5);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2 = r2.getMessage();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2 = r4.append(r2);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2 = r2.toString();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        com.microquation.linkedme.android.f.b.a(r3, r2);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
    L_0x0294:
        r2 = new com.microquation.linkedme.android.b.u;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r3 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2.<init>(r14, r3);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        if (r9 == 0) goto L_0x00e6;
    L_0x029d:
        r9.disconnect();
        goto L_0x00e6;
    L_0x02a2:
        r2 = move-exception;
        if (r17 == 0) goto L_0x02c7;
    L_0x02a5:
        r3 = r11.getClass();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r3 = r3.getSimpleName();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r4 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r4.<init>();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r5 = "IO exception: ";	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r4 = r4.append(r5);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2 = r2.getMessage();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2 = r4.append(r2);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2 = r2.toString();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        com.microquation.linkedme.android.f.b.a(r3, r2);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
    L_0x02c7:
        r2 = new com.microquation.linkedme.android.b.u;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r3 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2.<init>(r14, r3);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        if (r9 == 0) goto L_0x00e6;
    L_0x02d0:
        r9.disconnect();
        goto L_0x00e6;
    L_0x02d5:
        r2 = move-exception;
        if (r17 == 0) goto L_0x02fa;
    L_0x02d8:
        r3 = r11.getClass();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r3 = r3.getSimpleName();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r4 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r4.<init>();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r5 = "IO exception: ";	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r4 = r4.append(r5);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2 = r2.getMessage();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2 = r4.append(r2);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2 = r2.toString();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        com.microquation.linkedme.android.f.b.a(r3, r2);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
    L_0x02fa:
        r2 = new com.microquation.linkedme.android.b.u;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r3 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2.<init>(r14, r3);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        if (r9 == 0) goto L_0x00e6;
    L_0x0303:
        r9.disconnect();
        goto L_0x00e6;
    L_0x0308:
        r2 = move-exception;
        if (r17 == 0) goto L_0x032d;
    L_0x030b:
        r3 = r11.getClass();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r3 = r3.getSimpleName();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r4 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r4.<init>();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r5 = "IO exception: ";	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r4 = r4.append(r5);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2 = r2.getMessage();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2 = r4.append(r2);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2 = r2.toString();	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        com.microquation.linkedme.android.f.b.a(r3, r2);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
    L_0x032d:
        r2 = new com.microquation.linkedme.android.b.u;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r3 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        r2.<init>(r14, r3);	 Catch:{ SocketException -> 0x0142, SocketTimeoutException -> 0x033b, UnknownHostException -> 0x023c, IOException -> 0x026f, NoSuchAlgorithmException -> 0x02a2, KeyManagementException -> 0x02d5, Exception -> 0x0308, all -> 0x0227 }
        if (r9 == 0) goto L_0x00e6;
    L_0x0336:
        r9.disconnect();
        goto L_0x00e6;
    L_0x033b:
        r2 = move-exception;
        r16 = r7;
        goto L_0x017c;
    L_0x0340:
        r6 = r15;
        goto L_0x000a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microquation.linkedme.android.b.g.a(java.lang.String, org.json.JSONObject, java.lang.String, int, int, boolean):com.microquation.linkedme.android.b.u");
    }

    private u a(HttpURLConnection httpURLConnection, int i, String str, boolean z) {
        try {
            return new u(str, i, a(httpURLConnection));
        } catch (IOException e) {
            if (z) {
                b.a(getClass().getSimpleName(), "IO exception: " + e.getMessage());
            }
            return new u(str, i);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.microquation.linkedme.android.b.u a(org.json.JSONObject r15, java.lang.String r16, java.lang.String r17, int r18, int r19, boolean r20) {
        /*
        r14 = this;
        r9 = 0;
        if (r18 > 0) goto L_0x0393;
    L_0x0003:
        r6 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
    L_0x0005:
        r3 = new org.json.JSONObject;
        r3.<init>();
        r4 = new org.json.JSONObject;
        r4.<init>();
        r5 = r15.keys();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
    L_0x0013:
        r2 = r5.hasNext();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        if (r2 == 0) goto L_0x0067;
    L_0x0019:
        r2 = r5.next();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2 = (java.lang.String) r2;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r7 = r15.get(r2);	 Catch:{ JSONException -> 0x002e }
        r3.put(r2, r7);	 Catch:{ JSONException -> 0x002e }
        r7 = r15.get(r2);	 Catch:{ JSONException -> 0x002e }
        r4.put(r2, r7);	 Catch:{ JSONException -> 0x002e }
        goto L_0x0013;
    L_0x002e:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        goto L_0x0013;
    L_0x0033:
        r2 = move-exception;
        if (r20 == 0) goto L_0x0058;
    L_0x0036:
        r3 = r14.getClass();	 Catch:{ all -> 0x0331 }
        r3 = r3.getSimpleName();	 Catch:{ all -> 0x0331 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0331 }
        r4.<init>();	 Catch:{ all -> 0x0331 }
        r5 = "Http connect exception: ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0331 }
        r2 = r2.getMessage();	 Catch:{ all -> 0x0331 }
        r2 = r4.append(r2);	 Catch:{ all -> 0x0331 }
        r2 = r2.toString();	 Catch:{ all -> 0x0331 }
        com.microquation.linkedme.android.f.b.a(r3, r2);	 Catch:{ all -> 0x0331 }
    L_0x0058:
        r2 = new com.microquation.linkedme.android.b.u;	 Catch:{ all -> 0x0331 }
        r3 = -1009; // 0xfffffffffffffc0f float:NaN double:NaN;
        r0 = r17;
        r2.<init>(r0, r3);	 Catch:{ all -> 0x0331 }
        if (r9 == 0) goto L_0x0066;
    L_0x0063:
        r9.disconnect();
    L_0x0066:
        return r2;
    L_0x0067:
        r2 = "/track/";
        r0 = r16;
        r2 = r0.contains(r2);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        if (r2 == 0) goto L_0x012e;
    L_0x0071:
        r2 = r14.a(r3);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        if (r2 != 0) goto L_0x0086;
    L_0x0077:
        r2 = new com.microquation.linkedme.android.b.u;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r4 = -1234; // 0xfffffffffffffb2e float:NaN double:NaN;
        r0 = r17;
        r2.<init>(r0, r4);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        if (r9 == 0) goto L_0x0066;
    L_0x0082:
        r9.disconnect();
        goto L_0x0066;
    L_0x0086:
        r14.a(r4);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
    L_0x0089:
        r0 = r19;
        r14.a(r4, r0);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        if (r20 == 0) goto L_0x0146;
    L_0x0090:
        r2 = com.microquation.linkedme.android.util.c.g.RegisterOpen;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2 = r2.a();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r0 = r16;
        r2 = r0.contains(r2);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        if (r2 != 0) goto L_0x00ac;
    L_0x009e:
        r2 = com.microquation.linkedme.android.util.c.g.RegisterClose;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2 = r2.a();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r0 = r16;
        r2 = r0.contains(r2);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        if (r2 == 0) goto L_0x0146;
    L_0x00ac:
        r2 = com.microquation.linkedme.android.a.a;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r5 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r5.<init>();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r7 = "posting to ";
        r5 = r5.append(r7);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r0 = r16;
        r5 = r5.append(r0);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r5 = r5.toString();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        com.microquation.linkedme.android.f.b.a(r2, r5);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2 = com.microquation.linkedme.android.util.c.a.LKME_CLOSE_SESSION;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2 = r2.a();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r4.remove(r2);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2 = com.microquation.linkedme.android.a.a;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r5 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r5.<init>();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r7 = "Post value = ";
        r5 = r5.append(r7);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r7 = 4;
        r4 = r4.toString(r7);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r4 = r5.append(r4);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r4 = r4.toString();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        com.microquation.linkedme.android.f.b.a(r2, r4);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
    L_0x00ec:
        r5 = r3.keys();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r4 = "";
    L_0x00f2:
        r2 = r5.hasNext();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        if (r2 == 0) goto L_0x01b7;
    L_0x00f8:
        r2 = r5.next();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2 = (java.lang.String) r2;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r7 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x01b0 }
        r7.<init>();	 Catch:{ JSONException -> 0x01b0 }
        r7 = r7.append(r4);	 Catch:{ JSONException -> 0x01b0 }
        r8 = "%s=%s&";
        r10 = 2;
        r10 = new java.lang.Object[r10];	 Catch:{ JSONException -> 0x01b0 }
        r11 = 0;
        r10[r11] = r2;	 Catch:{ JSONException -> 0x01b0 }
        r11 = 1;
        r2 = r3.get(r2);	 Catch:{ JSONException -> 0x01b0 }
        r2 = r2.toString();	 Catch:{ JSONException -> 0x01b0 }
        r12 = "utf-8";
        r2 = java.net.URLEncoder.encode(r2, r12);	 Catch:{ JSONException -> 0x01b0 }
        r10[r11] = r2;	 Catch:{ JSONException -> 0x01b0 }
        r2 = java.lang.String.format(r8, r10);	 Catch:{ JSONException -> 0x01b0 }
        r2 = r7.append(r2);	 Catch:{ JSONException -> 0x01b0 }
        r2 = r2.toString();	 Catch:{ JSONException -> 0x01b0 }
    L_0x012c:
        r4 = r2;
        goto L_0x00f2;
    L_0x012e:
        r0 = r19;
        r2 = r14.a(r3, r0);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        if (r2 != 0) goto L_0x0089;
    L_0x0136:
        r2 = new com.microquation.linkedme.android.b.u;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r4 = -1234; // 0xfffffffffffffb2e float:NaN double:NaN;
        r0 = r17;
        r2.<init>(r0, r4);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        if (r9 == 0) goto L_0x0066;
    L_0x0141:
        r9.disconnect();
        goto L_0x0066;
    L_0x0146:
        r2 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2.<init>();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r4 = "posting to ";
        r2 = r2.append(r4);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r0 = r16;
        r2 = r2.append(r0);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2 = r2.toString();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        com.microquation.linkedme.android.f.b.a(r2);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2.<init>();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r4 = "Post value = ";
        r2 = r2.append(r4);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r4 = 4;
        r4 = r3.toString(r4);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2 = r2.append(r4);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2 = r2.toString();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        com.microquation.linkedme.android.f.b.a(r2);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        goto L_0x00ec;
    L_0x017b:
        r2 = move-exception;
        if (r20 == 0) goto L_0x01a0;
    L_0x017e:
        r3 = r14.getClass();	 Catch:{ all -> 0x0331 }
        r3 = r3.getSimpleName();	 Catch:{ all -> 0x0331 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0331 }
        r4.<init>();	 Catch:{ all -> 0x0331 }
        r5 = "Http connect exception: ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0331 }
        r2 = r2.getMessage();	 Catch:{ all -> 0x0331 }
        r2 = r4.append(r2);	 Catch:{ all -> 0x0331 }
        r2 = r2.toString();	 Catch:{ all -> 0x0331 }
        com.microquation.linkedme.android.f.b.a(r3, r2);	 Catch:{ all -> 0x0331 }
    L_0x01a0:
        r2 = new com.microquation.linkedme.android.b.u;	 Catch:{ all -> 0x0331 }
        r3 = -1009; // 0xfffffffffffffc0f float:NaN double:NaN;
        r0 = r17;
        r2.<init>(r0, r3);	 Catch:{ all -> 0x0331 }
        if (r9 == 0) goto L_0x0066;
    L_0x01ab:
        r9.disconnect();
        goto L_0x0066;
    L_0x01b0:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2 = r4;
        goto L_0x012c;
    L_0x01b7:
        r2 = r4.length();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        if (r2 <= 0) goto L_0x01c8;
    L_0x01bd:
        r2 = 0;
        r5 = r4.length();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r5 = r5 + -1;
        r4 = r4.substring(r2, r5);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
    L_0x01c8:
        r2 = new java.net.URL;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r0 = r16;
        r2.<init>(r0);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r5 = android.os.Build.VERSION.SDK_INT;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r7 = 19;
        if (r5 >= r7) goto L_0x01ed;
    L_0x01d5:
        r5 = "TLSv1";
        r5 = javax.net.ssl.SSLContext.getInstance(r5);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r7 = 0;
        r8 = 0;
        r10 = 0;
        r5.init(r7, r8, r10);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r7 = new com.microquation.linkedme.android.b.e;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r5 = r5.getSocketFactory();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r7.<init>(r5);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(r7);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
    L_0x01ed:
        r2 = r2.openConnection();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r0 = r2;
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r9 = r0;
        r9.setConnectTimeout(r6);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r9.setReadTimeout(r6);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2 = 1;
        r9.setDoInput(r2);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2 = 1;
        r9.setDoOutput(r2);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2 = "Accept";
        r5 = "application/json";
        r9.setRequestProperty(r2, r5);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2 = "POST";
        r9.setRequestMethod(r2);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2 = 0;
        r14.c = r2;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r10 = java.lang.System.currentTimeMillis();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2 = new java.io.OutputStreamWriter;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r5 = r9.getOutputStream();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2.<init>(r5);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r12 = java.lang.System.currentTimeMillis();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r10 = r12 - r10;
        r5 = (int) r10;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r14.c = r5;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r5 = com.microquation.linkedme.android.a.a();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        if (r5 == 0) goto L_0x025a;
    L_0x022e:
        r5 = com.microquation.linkedme.android.a.a();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r7 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r7.<init>();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r0 = r17;
        r7 = r7.append(r0);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r8 = "-";
        r7 = r7.append(r8);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r8 = com.microquation.linkedme.android.util.c.a.Last_Round_Trip_Time;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r8 = r8.a();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r7 = r7.append(r8);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r7 = r7.toString();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r8 = r14.c;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r8 = java.lang.String.valueOf(r8);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r5.a(r7, r8);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
    L_0x025a:
        r2.write(r4);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2.flush();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2 = r9.getResponseCode();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r4 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        if (r2 < r4) goto L_0x02be;
    L_0x0268:
        r2 = r14.a;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r2 = r2.e();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r0 = r19;
        if (r0 >= r2) goto L_0x02be;
    L_0x0272:
        r2 = r14.a;	 Catch:{ InterruptedException -> 0x0290 }
        r2 = r2.f();	 Catch:{ InterruptedException -> 0x0290 }
        r4 = (long) r2;	 Catch:{ InterruptedException -> 0x0290 }
        java.lang.Thread.sleep(r4);	 Catch:{ InterruptedException -> 0x0290 }
    L_0x027c:
        r7 = r19 + 1;
        r2 = r14;
        r4 = r16;
        r5 = r17;
        r8 = r20;
        r2 = r2.a(r3, r4, r5, r6, r7, r8);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x038e, Exception -> 0x0348 }
        if (r9 == 0) goto L_0x0066;
    L_0x028b:
        r9.disconnect();
        goto L_0x0066;
    L_0x0290:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        goto L_0x027c;
    L_0x0295:
        r2 = move-exception;
    L_0x0296:
        r2 = r14.a;	 Catch:{ all -> 0x0331 }
        r2 = r2.e();	 Catch:{ all -> 0x0331 }
        r0 = r19;
        if (r0 >= r2) goto L_0x0338;
    L_0x02a0:
        r2 = r14.a;	 Catch:{ InterruptedException -> 0x032b }
        r2 = r2.f();	 Catch:{ InterruptedException -> 0x032b }
        r4 = (long) r2;	 Catch:{ InterruptedException -> 0x032b }
        java.lang.Thread.sleep(r4);	 Catch:{ InterruptedException -> 0x032b }
    L_0x02aa:
        r7 = r19 + 1;
        r2 = r14;
        r4 = r16;
        r5 = r17;
        r8 = r20;
        r2 = r2.a(r3, r4, r5, r6, r7, r8);	 Catch:{ all -> 0x0331 }
        if (r9 == 0) goto L_0x0066;
    L_0x02b9:
        r9.disconnect();
        goto L_0x0066;
    L_0x02be:
        r2 = r9.getResponseCode();	 Catch:{ FileNotFoundException -> 0x02fa }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 == r4) goto L_0x02e3;
    L_0x02c6:
        r2 = r9.getErrorStream();	 Catch:{ FileNotFoundException -> 0x02fa }
        if (r2 == 0) goto L_0x02e3;
    L_0x02cc:
        r2 = r9.getErrorStream();	 Catch:{ FileNotFoundException -> 0x02fa }
        r4 = r9.getResponseCode();	 Catch:{ FileNotFoundException -> 0x02fa }
        r0 = r17;
        r1 = r20;
        r2 = r14.a(r2, r4, r0, r1);	 Catch:{ FileNotFoundException -> 0x02fa }
        if (r9 == 0) goto L_0x0066;
    L_0x02de:
        r9.disconnect();
        goto L_0x0066;
    L_0x02e3:
        r2 = r9.getInputStream();	 Catch:{ FileNotFoundException -> 0x02fa }
        r4 = r9.getResponseCode();	 Catch:{ FileNotFoundException -> 0x02fa }
        r0 = r17;
        r1 = r20;
        r2 = r14.a(r2, r4, r0, r1);	 Catch:{ FileNotFoundException -> 0x02fa }
        if (r9 == 0) goto L_0x0066;
    L_0x02f5:
        r9.disconnect();
        goto L_0x0066;
    L_0x02fa:
        r2 = move-exception;
        if (r20 == 0) goto L_0x0317;
    L_0x02fd:
        r2 = com.microquation.linkedme.android.a.a;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r4 = new java.lang.StringBuilder;	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r4.<init>();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r5 = "A resource conflict occurred with this request ";
        r4 = r4.append(r5);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r0 = r17;
        r4 = r4.append(r0);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r4 = r4.toString();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        com.microquation.linkedme.android.f.b.a(r2, r4);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
    L_0x0317:
        r2 = 0;
        r4 = r9.getResponseCode();	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        r0 = r17;
        r1 = r20;
        r2 = r14.a(r2, r4, r0, r1);	 Catch:{ SocketException -> 0x0033, UnknownHostException -> 0x017b, SocketTimeoutException -> 0x0295, Exception -> 0x0348 }
        if (r9 == 0) goto L_0x0066;
    L_0x0326:
        r9.disconnect();
        goto L_0x0066;
    L_0x032b:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ all -> 0x0331 }
        goto L_0x02aa;
    L_0x0331:
        r2 = move-exception;
        if (r9 == 0) goto L_0x0337;
    L_0x0334:
        r9.disconnect();
    L_0x0337:
        throw r2;
    L_0x0338:
        r2 = new com.microquation.linkedme.android.b.u;	 Catch:{ all -> 0x0331 }
        r3 = -111; // 0xffffffffffffff91 float:NaN double:NaN;
        r0 = r17;
        r2.<init>(r0, r3);	 Catch:{ all -> 0x0331 }
        if (r9 == 0) goto L_0x0066;
    L_0x0343:
        r9.disconnect();
        goto L_0x0066;
    L_0x0348:
        r2 = move-exception;
        if (r20 == 0) goto L_0x036d;
    L_0x034b:
        r3 = r14.getClass();	 Catch:{ all -> 0x0331 }
        r3 = r3.getSimpleName();	 Catch:{ all -> 0x0331 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0331 }
        r4.<init>();	 Catch:{ all -> 0x0331 }
        r5 = "Exception: ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0331 }
        r5 = r2.getMessage();	 Catch:{ all -> 0x0331 }
        r4 = r4.append(r5);	 Catch:{ all -> 0x0331 }
        r4 = r4.toString();	 Catch:{ all -> 0x0331 }
        com.microquation.linkedme.android.f.b.a(r3, r4);	 Catch:{ all -> 0x0331 }
    L_0x036d:
        r3 = android.os.Build.VERSION.SDK_INT;	 Catch:{ all -> 0x0331 }
        r4 = 11;
        if (r3 < r4) goto L_0x037e;
    L_0x0373:
        r2 = r2 instanceof android.os.NetworkOnMainThreadException;	 Catch:{ all -> 0x0331 }
        if (r2 == 0) goto L_0x037e;
    L_0x0377:
        r2 = com.microquation.linkedme.android.a.a;	 Catch:{ all -> 0x0331 }
        r3 = "LinkedME Error: Don't call our synchronous methods on the main thread!!!";
        android.util.Log.i(r2, r3);	 Catch:{ all -> 0x0331 }
    L_0x037e:
        r2 = new com.microquation.linkedme.android.b.u;	 Catch:{ all -> 0x0331 }
        r3 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        r0 = r17;
        r2.<init>(r0, r3);	 Catch:{ all -> 0x0331 }
        if (r9 == 0) goto L_0x0066;
    L_0x0389:
        r9.disconnect();
        goto L_0x0066;
    L_0x038e:
        r2 = move-exception;
        r19 = r7;
        goto L_0x0296;
    L_0x0393:
        r6 = r18;
        goto L_0x0005;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microquation.linkedme.android.b.g.a(org.json.JSONObject, java.lang.String, java.lang.String, int, int, boolean):com.microquation.linkedme.android.b.u");
    }

    private boolean a(JSONObject jSONObject) {
        try {
            jSONObject.putOpt(c.a.LKME_OS.a(), a.a().h().j());
            jSONObject.putOpt(c.a.LKME_OS_VERSION.a(), a.a().h().l());
            jSONObject.put(c.a.LKME_DEVICE_MODEL.a(), a.a().h().i());
            jSONObject.put(c.a.LKME_APP_VERSION.a(), a.a().h().b());
            DisplayMetrics m = a.a().h().m();
            jSONObject.putOpt(c.a.LKME_SCREEN_HEIGHT.a(), Integer.valueOf(m.heightPixels));
            jSONObject.putOpt(c.a.LKME_SCREEN_WIDTH.a(), Integer.valueOf(m.widthPixels));
            jSONObject.putOpt(c.a.LKME_SESSION_ID.a(), this.a.k());
            jSONObject.put(c.a.LKME_TIMESTAMP.a(), System.currentTimeMillis());
            b(jSONObject, 0);
        } catch (JSONException e) {
        }
        return false;
    }

    private boolean a(JSONObject jSONObject, int i) {
        try {
            CharSequence h = this.a.h();
            jSONObject.put(c.a.LKME_SDK_VERSION.a(), "android1.0.15");
            jSONObject.put(c.a.LKME_IMEI.a(), a.a().h().q());
            jSONObject.put(c.a.LKME_ANDROID_ID.a(), a.a().h().r());
            jSONObject.put(c.a.LKME_DEVICE_ID.a(), a.a().f());
            jSONObject.put(c.a.LKME_APP_NAME.a(), a.a().h().y());
            jSONObject.put(c.a.LKME_RETRY_TIMES.a(), i);
            if (!TextUtils.equals(h, "lkme_no_value")) {
                jSONObject.put("linkedme_key", this.a.h());
                jSONObject.put(c.a.LKME_SIGN.a(), f.a(jSONObject, "qeradszmxcoiusdj"));
                return true;
            }
        } catch (JSONException e) {
        }
        return false;
    }

    private byte[] a(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream;
        Throwable th;
        f fVar = new f(this.b, httpURLConnection.getContentLength());
        try {
            inputStream = httpURLConnection.getInputStream();
            if (inputStream == null) {
                try {
                    throw new IOException();
                } catch (Throwable th2) {
                    th = th2;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    this.b.a(null);
                    fVar.close();
                    throw th;
                }
            }
            byte[] a = this.b.a(1024);
            while (true) {
                int read = inputStream.read(a);
                if (read == -1) {
                    break;
                }
                fVar.write(a, 0, read);
            }
            byte[] toByteArray = fVar.toByteArray();
            if (inputStream != null) {
                inputStream.close();
            }
            this.b.a(a);
            fVar.close();
            return toByteArray;
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
            if (inputStream != null) {
                inputStream.close();
            }
            this.b.a(null);
            fVar.close();
            throw th;
        }
    }

    @Nullable
    private String b(JSONObject jSONObject) {
        StringBuilder stringBuilder = new StringBuilder();
        if (jSONObject != null) {
            JSONArray names = jSONObject.names();
            if (names != null) {
                Object obj = 1;
                int length = names.length();
                int i = 0;
                while (i < length) {
                    try {
                        String string = names.getString(i);
                        if (obj != null) {
                            stringBuilder.append("?");
                            obj = null;
                        } else {
                            stringBuilder.append("&");
                        }
                        stringBuilder.append(string).append(LoginConstants.EQUAL).append(URLEncoder.encode(jSONObject.getString(string), "utf-8"));
                        i++;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return null;
                    } catch (UnsupportedEncodingException e2) {
                        e2.printStackTrace();
                        return null;
                    }
                }
            }
        }
        return stringBuilder.toString();
    }

    private boolean b(JSONObject jSONObject, int i) {
        try {
            jSONObject.putOpt(c.a.LKME_OS.a(), a.a().h().j());
            jSONObject.putOpt(c.a.LKME_ANDROID_ID_MD5.a(), f.a(a.a().h().r()));
            jSONObject.putOpt(c.a.LKME_IMEI_MD5.a(), f.a(a.a().h().q()));
            jSONObject.put(c.a.LKME_TIMESTAMP.a(), System.currentTimeMillis());
            a(jSONObject, i);
        } catch (JSONException e) {
        }
        return false;
    }

    public u a(String str, JSONObject jSONObject, String str2, int i) {
        return a(str, jSONObject, str2, i, 0, true);
    }

    public u a(JSONObject jSONObject, String str, String str2, int i, boolean z) {
        return a(jSONObject, str, str2, i, 0, z);
    }
}
