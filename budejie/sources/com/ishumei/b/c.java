package com.ishumei.b;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import cn.v6.sixrooms.room.gift.BoxingVoteBean;
import com.ali.auth.third.core.rpc.protocol.RpcException.ErrorCode;
import com.ali.auth.third.login.LoginConstants;
import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import com.ishumei.f.d;
import com.ishumei.f.e;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.http.entity.mime.MIME;

public class c {
    private static HandlerThread a = null;
    private static Handler b = null;
    private static boolean c = false;
    private static boolean d = true;
    private static String e = "";
    private static String f;

    /* renamed from: com.ishumei.b.c$1 */
    final class AnonymousClass1 implements Runnable {
        final /* synthetic */ Map a;

        AnonymousClass1(Map map) {
            this.a = map;
        }

        public final void run() {
            HttpURLConnection httpURLConnection;
            Throwable th;
            HttpURLConnection httpURLConnection2 = null;
            try {
                StringBuilder stringBuilder = new StringBuilder(e.c);
                for (Entry entry : this.a.entrySet()) {
                    stringBuilder.append("&").append((String) entry.getKey()).append(LoginConstants.EQUAL).append(e.e(d.e((String) entry.getValue())));
                }
                HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(stringBuilder.toString()).openConnection();
                try {
                    httpURLConnection3.setRequestMethod("GET");
                    httpURLConnection3.setRequestProperty("Connection", BoxingVoteBean.BOXING_VOTE_STATE_CLOSE);
                    httpURLConnection3.setRequestProperty(MIME.CONTENT_TYPE, FilePart.DEFAULT_CONTENT_TYPE);
                    httpURLConnection3.setConnectTimeout(ErrorCode.SERVER_SESSIONSTATUS);
                    httpURLConnection3.setReadTimeout(5000);
                    com.ishumei.f.c.b("ExceptionTracker", "response " + httpURLConnection3.getResponseCode());
                    if (httpURLConnection3 != null) {
                        try {
                            httpURLConnection3.disconnect();
                        } catch (Exception e) {
                        }
                    }
                } catch (Throwable th2) {
                    httpURLConnection2 = httpURLConnection3;
                    th = th2;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw th;
            }
        }
    }

    public static void a(String str) {
        f = str;
    }

    public static void a(Throwable th) {
        try {
            Map hashMap = new HashMap();
            hashMap.put("sdkver", "2.3.6");
            hashMap.put("model", Build.MODEL);
            hashMap.put("osver", VERSION.RELEASE);
            hashMap.put("org", e);
            hashMap.put(AppLinkConstants.E, b(th));
            a(hashMap);
        } catch (Throwable th2) {
        }
    }

    private static void a(Map<String, String> map) {
        if (d) {
            if (!c) {
                synchronized (c.class) {
                    if (!c) {
                        HandlerThread handlerThread = new HandlerThread("exception upload thread");
                        a = handlerThread;
                        handlerThread.setDaemon(true);
                        a.start();
                        b = new Handler(a.getLooper());
                        c = true;
                    }
                }
            }
            if (map != null && map.size() != 0) {
                b.post(new AnonymousClass1(map));
            }
        }
    }

    public static void a(boolean z) {
        d = z;
    }

    private static String b(Throwable th) {
        if (th == null) {
            return "";
        }
        try {
            Writer stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
                cause.printStackTrace(printWriter);
            }
            printWriter.close();
            String obj = stringWriter.toString();
            return obj.length() > 4096 ? obj.substring(0, 4096) : obj;
        } catch (Exception e) {
            return "";
        }
    }

    public static void b(String str) {
        e = str;
    }
}
