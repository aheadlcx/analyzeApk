package cn.xiaochuankeji.tieba.background.utils.a;

import cn.xiaochuankeji.tieba.api.log.a;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import rx.j;

public class g {
    private static g b;
    public boolean a = true;
    private ArrayList<f> c = new ArrayList();
    private ArrayList<f> d = new ArrayList();

    private g() {
    }

    public static g a() {
        if (b == null) {
            b = new g();
        }
        return b;
    }

    public void a(int i, int i2) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            f fVar = (f) it.next();
            if (fVar.c == 0 && (fVar.d < i || fVar.d > i + i2)) {
                fVar.c = System.currentTimeMillis();
            }
        }
    }

    public void b() {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            f fVar = (f) it.next();
            if (fVar.c == 0) {
                fVar.c = System.currentTimeMillis();
            }
        }
    }

    public void a(long j, long j2, int i, String str, String str2) {
        f fVar = new f();
        fVar.b = j2;
        fVar.a = j;
        fVar.d = i;
        fVar.e = str2;
        fVar.f = str;
        this.c.add(fVar);
        if (this.d.size() + this.c.size() > 20) {
            e();
        }
    }

    public void a(long j, long j2, String str, String str2) {
        f fVar = new f();
        fVar.a = j;
        fVar.b = j2;
        fVar.f = str;
        fVar.e = str2;
        this.d.add(fVar);
        if (this.d.size() + this.c.size() > 20) {
            e();
        }
    }

    public void c() {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            f fVar = (f) it.next();
            if (fVar.c == 0) {
                fVar.c = System.currentTimeMillis();
            }
        }
    }

    public void d() {
        b();
        c();
        e();
        this.a = true;
    }

    public void e() {
        if (this.d.size() + this.c.size() != 0) {
            f fVar;
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            Collection arrayList = new ArrayList();
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                fVar = (f) it.next();
                if (fVar.c > 0) {
                    fVar.g = "show";
                    jSONArray.add(fVar.a());
                    arrayList.add(fVar);
                }
            }
            this.c.removeAll(arrayList);
            arrayList.clear();
            it = this.d.iterator();
            while (it.hasNext()) {
                fVar = (f) it.next();
                if (fVar.c > 0) {
                    fVar.g = "view";
                    jSONArray.add(fVar.a());
                    arrayList.add(fVar);
                }
            }
            this.d.removeAll(arrayList);
            jSONObject.put("list", jSONArray);
            if (jSONArray.size() != 0) {
                new a().c(jSONObject).b(rx.f.a.c()).b(new j<Void>(this) {
                    final /* synthetic */ g a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void onNext(Object obj) {
                        a((Void) obj);
                    }

                    public void onCompleted() {
                    }

                    public void onError(Throwable th) {
                    }

                    public void a(Void voidR) {
                    }
                });
            }
        }
    }
}
