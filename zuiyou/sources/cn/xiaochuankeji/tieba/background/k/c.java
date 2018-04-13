package cn.xiaochuankeji.tieba.background.k;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.util.Base64;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.json.StatisticsJson;
import com.alibaba.fastjson.JSON;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import rx.j;

public class c {
    private static final byte[] d = "0102030405060708".getBytes();
    Context a;
    List<a> b;
    private PackageManager c;

    public static void a(final Context context, Handler handler) {
        if (handler != null) {
            long j;
            Long valueOf = Long.valueOf(a.a().getLong("statistics", -500));
            Runnable anonymousClass1 = new Runnable() {
                public void run() {
                    if (cn.xiaochuankeji.tieba.background.utils.c.a.c().C().statistics_enable != 0) {
                        SharedPreferences a = a.a();
                        Long valueOf = Long.valueOf(a.getLong("statistics", -500));
                        Long valueOf2 = Long.valueOf(System.currentTimeMillis());
                        if (valueOf2.longValue() - valueOf.longValue() > 604800000 || valueOf.longValue() == -500) {
                            a.edit().putLong("statistics", valueOf2.longValue()).apply();
                            c cVar = new c(context);
                            b bVar = new b();
                            bVar.a(cVar.b());
                            bVar.b(cVar.a());
                            JSON.toJSONString(bVar);
                            new cn.xiaochuankeji.tieba.api.statistics.a().a(c.a(bVar)).a(rx.a.b.a.a()).b(new j<StatisticsJson>(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public /* synthetic */ void onNext(Object obj) {
                                    a((StatisticsJson) obj);
                                }

                                public void onCompleted() {
                                }

                                public void onError(Throwable th) {
                                }

                                public void a(StatisticsJson statisticsJson) {
                                    if (statisticsJson.ret != 1) {
                                    }
                                }
                            });
                        }
                    }
                }
            };
            if (valueOf.longValue() == -500) {
                j = 60000;
            } else {
                j = 300000;
            }
            handler.postDelayed(anonymousClass1, j);
        }
    }

    private c(Context context) {
        if (context != null) {
            this.a = context;
            this.c = context.getPackageManager();
        }
    }

    private List<a> a() {
        List<a> arrayList = new ArrayList();
        Map hashMap = new HashMap();
        for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) this.a.getSystemService("activity")).getRunningAppProcesses()) {
            int i = runningAppProcessInfo.pid;
            String str = runningAppProcessInfo.processName;
            String[] strArr = runningAppProcessInfo.pkgList;
            for (Object put : strArr) {
                hashMap.put(put, runningAppProcessInfo);
            }
        }
        for (a aVar : this.b) {
            if (hashMap.containsKey(aVar.a())) {
                a aVar2 = new a();
                aVar2.a(aVar.a());
                aVar2.b(aVar.b());
                arrayList.add(aVar2);
            }
        }
        return arrayList;
    }

    private List<a> b() {
        List<a> arrayList = new ArrayList();
        for (PackageInfo packageInfo : this.c.getInstalledPackages(0)) {
            String str = packageInfo.applicationInfo.packageName;
            String charSequence = this.c.getApplicationLabel(packageInfo.applicationInfo).toString();
            a aVar = new a();
            aVar.a(str);
            aVar.b(charSequence);
            arrayList.add(aVar);
        }
        this.b = arrayList;
        return arrayList;
    }

    public static String a(b bVar) {
        return new String(Base64.encode(a("fantasticbabyohy".getBytes(), a(JSON.toJSONString(bVar))), 0));
    }

    public static byte[] a(String str) {
        if (str == null || str.length() <= 0) {
            return new byte[0];
        }
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(str.getBytes());
            gZIPOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            Key secretKeySpec = new SecretKeySpec(bArr, "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, secretKeySpec, new IvParameterSpec(d));
            return instance.doFinal(bArr2);
        } catch (Exception e) {
            return new byte[0];
        }
    }
}
