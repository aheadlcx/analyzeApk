package com.tencent.bugly.beta.global;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.tencent.bugly.beta.download.DownloadListener;
import com.tencent.bugly.beta.download.DownloadTask;
import com.tencent.bugly.beta.download.a;
import com.tencent.bugly.beta.download.b;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.p;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class f {
    public static f a = new f();
    final Map<String, DownloadTask> b = Collections.synchronizedMap(new HashMap());
    Handler c = new Handler(Looper.getMainLooper());
    private List<Runnable> d = new ArrayList();

    public synchronized void a(Runnable runnable, int i) {
        if (this.b.size() == 0) {
            runnable.run();
        } else {
            Runnable dVar = new d(6, Boolean.valueOf(false), runnable);
            this.c.postDelayed(dVar, (long) i);
            a(dVar);
        }
    }

    public synchronized void a(Runnable runnable) {
        if (this.b.size() == 0) {
            runnable.run();
        } else {
            this.d.add(runnable);
        }
    }

    public void a(b bVar, Map<String, String> map) {
        if (bVar != null) {
            if (map == null || map.isEmpty()) {
                this.b.clear();
                ResBean.a = new ResBean();
                a.a("rb.bch", ResBean.a);
                return;
            }
            DownloadTask a;
            for (DownloadTask a2 : this.b.values()) {
                a2.delete(true);
            }
            this.b.clear();
            DownloadListener aVar = new a(1, new Object[]{this, this.b});
            String[] strArr = ResBean.b;
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                String str = strArr[i];
                if (map.containsKey(str)) {
                    String str2 = (String) map.get(str);
                    if (!str.startsWith("IMG_") || TextUtils.isEmpty(str2)) {
                        ResBean.a.a(str, str2);
                    } else {
                        ResBean.a.a(str, "");
                        for (DownloadTask downloadTask : this.b.values()) {
                            if (downloadTask.getDownloadUrl().equals(str2)) {
                                break;
                            }
                        }
                        DownloadTask downloadTask2 = null;
                        if (downloadTask2 == null) {
                            a2 = bVar.a(str2, e.E.r.getAbsolutePath(), null, null);
                        } else {
                            a2 = downloadTask2;
                        }
                        if (a2 != null) {
                            a2.addListener(aVar);
                            a2.setNeededNotify(false);
                            this.b.put(str, a2);
                        }
                    }
                    i++;
                } else {
                    this.b.clear();
                    ResBean.a = new ResBean();
                    a.a("rb.bch", ResBean.a);
                    return;
                }
            }
            a.a("rb.bch", ResBean.a);
            if (!this.b.isEmpty()) {
                for (DownloadTask a22 : this.b.values()) {
                    a22.download();
                }
            }
        }
    }

    public void a() {
        int i;
        List<String> arrayList = new ArrayList();
        for (String str : ResBean.b) {
            CharSequence a = ResBean.a.a(str);
            if (str.startsWith("IMG_") && !TextUtils.isEmpty(a)) {
                arrayList.add(a);
            }
        }
        File[] listFiles = e.E.r.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (File file : listFiles) {
                int i2 = 1;
                for (String equals : arrayList) {
                    if (file.getAbsolutePath().equals(equals)) {
                        i = 0;
                    } else {
                        i = i2;
                    }
                    i2 = i;
                }
                if (i2 != 0) {
                    p.a.b(file.getAbsolutePath());
                    if (!file.delete()) {
                        an.e("cannot deleteCache file:%s", file.getAbsolutePath());
                    }
                }
            }
        }
    }

    public void b() {
        synchronized (this) {
            for (Runnable run : this.d) {
                run.run();
            }
            for (DownloadTask delete : this.b.values()) {
                delete.delete(false);
            }
            this.d.clear();
            this.b.clear();
        }
    }
}
