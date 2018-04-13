package com.xiaomi.push.service;

import android.os.Process;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.network.Host;
import com.xiaomi.push.protobuf.a.a;
import com.xiaomi.stats.f;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class ae {
    private static final Pattern a = Pattern.compile("([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})");
    private static long b = 0;
    private static ThreadPoolExecutor c = new ThreadPoolExecutor(1, 1, 20, TimeUnit.SECONDS, new LinkedBlockingQueue());

    public static void a() {
        long currentTimeMillis = System.currentTimeMillis();
        if ((c.getActiveCount() <= 0 || currentTimeMillis - b >= 1800000) && f.a().c()) {
            a d = at.a().d();
            if (d != null && d.m() > 0) {
                b = currentTimeMillis;
                a(d.l(), true);
            }
        }
    }

    public static void a(List<String> list, boolean z) {
        c.execute(new t(list, z));
    }

    public static void b() {
        String c = c("/proc/self/net/tcp");
        if (!TextUtils.isEmpty(c)) {
            b.a("dump tcp for uid = " + Process.myUid());
            b.a(c);
        }
        c = c("/proc/self/net/tcp6");
        if (!TextUtils.isEmpty(c)) {
            b.a("dump tcp6 for uid = " + Process.myUid());
            b.a(c);
        }
    }

    private static boolean b(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            b.a("ConnectivityTest: begin to connect to " + str);
            Socket socket = new Socket();
            socket.connect(Host.b(str, 5222), 5000);
            socket.setTcpNoDelay(true);
            b.a("ConnectivityTest: connect to " + str + " in " + (System.currentTimeMillis() - currentTimeMillis));
            socket.close();
            return true;
        } catch (Throwable th) {
            b.d("ConnectivityTest: could not connect to:" + str + " exception: " + th.getClass().getSimpleName() + " description: " + th.getMessage());
            return false;
        }
    }

    private static String c(String str) {
        Reader bufferedReader;
        Throwable th;
        String str2 = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(str)));
            try {
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuilder.append("\n");
                    stringBuilder.append(readLine);
                }
                str2 = stringBuilder.toString();
                com.xiaomi.channel.commonutils.file.a.a(bufferedReader);
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.file.a.a(bufferedReader);
                return str2;
            } catch (Throwable th2) {
                th = th2;
                com.xiaomi.channel.commonutils.file.a.a(bufferedReader);
                throw th;
            }
        } catch (Exception e2) {
            Object obj = str2;
            com.xiaomi.channel.commonutils.file.a.a(bufferedReader);
            return str2;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            bufferedReader = str2;
            th = th4;
            com.xiaomi.channel.commonutils.file.a.a(bufferedReader);
            throw th;
        }
        return str2;
    }
}
