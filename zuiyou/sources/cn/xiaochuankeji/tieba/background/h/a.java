package cn.xiaochuankeji.tieba.background.h;

import android.os.AsyncTask;
import android.os.Build.VERSION;
import cn.htjyb.c.a.b;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class a {
    private static ArrayList<String> a = new ArrayList();
    private static b b;

    static {
        a.add(cn.xiaochuankeji.tieba.background.a.e().t());
        a.add(cn.xiaochuankeji.tieba.background.a.e().z());
        a.add(cn.xiaochuankeji.tieba.background.a.e().B());
        a.add(cn.xiaochuankeji.tieba.common.c.a.a());
    }

    public static ArrayList<String> a() {
        ArrayList<String> arrayList = new ArrayList();
        arrayList.addAll(a);
        return arrayList;
    }

    public static void b() {
        if (b == null) {
            b = new b();
            AsyncTask anonymousClass1 = new AsyncTask() {
                protected Object doInBackground(Object[] objArr) {
                    a.f();
                    if (a.b.a >= 209715200) {
                        a.b.a(52428800);
                    }
                    return null;
                }

                protected void onPostExecute(Object obj) {
                    super.onPostExecute(obj);
                    if (a.b != null) {
                        a.b.a();
                    }
                    a.b = null;
                }
            };
            if (VERSION.SDK_INT < 11) {
                anonymousClass1.execute(new Object[0]);
            } else {
                anonymousClass1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
            }
        }
    }

    private static void f() {
        try {
            Iterator it = a.iterator();
            while (it.hasNext()) {
                a(new File((String) it.next()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void a(File file) throws Exception {
        File[] listFiles = file.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isDirectory()) {
                a(listFiles[i]);
            } else {
                long length = listFiles[i].length();
                b bVar = b;
                bVar.getClass();
                b.a(new cn.xiaochuankeji.tieba.background.h.b.a(bVar, listFiles[i].getPath(), length, listFiles[i].lastModified()));
            }
        }
    }

    public static long c() {
        try {
            Iterator it = a.iterator();
            long j = 0;
            while (it.hasNext()) {
                j += b.a(new File((String) it.next()));
            }
            return j;
        } catch (Exception e) {
            e.printStackTrace();
            com.izuiyou.a.a.b.e("计算文件大小出错.");
            return 0;
        }
    }
}
