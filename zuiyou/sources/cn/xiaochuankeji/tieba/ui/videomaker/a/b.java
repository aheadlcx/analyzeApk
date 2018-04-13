package cn.xiaochuankeji.tieba.ui.videomaker.a;

import android.util.Pair;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.ui.videomaker.h;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import rx.d;
import rx.d$a;
import rx.j;

public class b {
    public d<ArrayList<a>> a() {
        return d.a(new d$a<ArrayList<a>>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super ArrayList<a>> jVar) {
                jVar.onStart();
                List a = this.a.c();
                Collections.sort(a, new Comparator<a>(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ int compare(Object obj, Object obj2) {
                        return a((a) obj, (a) obj2);
                    }

                    public int a(a aVar, a aVar2) {
                        if (aVar.d == aVar2.d) {
                            return 0;
                        }
                        if (aVar.d > aVar2.d) {
                            return -1;
                        }
                        return 1;
                    }
                });
                jVar.onNext(a);
                jVar.onCompleted();
            }
        });
    }

    private ArrayList<a> c() {
        ArrayList<a> arrayList = new ArrayList();
        File[] listFiles = new File(b()).listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    try {
                        String name = file.getName();
                        int indexOf = name.indexOf("_");
                        if (indexOf != -1) {
                            long longValue = Long.valueOf(name.substring(0, indexOf)).longValue();
                            long longValue2 = Long.valueOf(name.substring(indexOf + 1)).longValue();
                            String str = file.getAbsolutePath() + File.separator + "cover.jpg";
                            File file2 = new File(file.getAbsolutePath() + File.separator + "data.json");
                            if (file2.exists()) {
                                arrayList.add(new a(longValue, longValue2, str, file2.lastModified()));
                            }
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return arrayList;
    }

    public static String b() {
        return a.e().u() + "drafts/";
    }

    public static String a(long j, long j2) {
        return b() + String.valueOf(j) + "_" + String.valueOf(j2) + File.separator;
    }

    public Pair<h, JSONObject> a(a aVar) throws JSONException {
        return b(aVar.a, aVar.b);
    }

    public Pair<h, JSONObject> b(long j, long j2) throws JSONException {
        JSONObject a = cn.htjyb.c.a.b.a(new File(a(j, j2), "data.json"), org.apache.commons.io.a.f.name());
        if (a != null) {
            return new Pair(new h(a), a);
        }
        throw new JSONException("data == null");
    }

    public h a(h hVar, JSONObject jSONObject) throws JSONException {
        long j;
        if (hVar.f > 0) {
            j = hVar.f;
        } else {
            j = System.currentTimeMillis();
        }
        String a = a(j, hVar.g);
        new File(a).mkdirs();
        h a2 = hVar.a(j, a);
        a2.a(jSONObject);
        File file = new File(a, "data.json");
        file.delete();
        cn.htjyb.c.a.b.a(jSONObject, file, org.apache.commons.io.a.f.name());
        return a2;
    }

    public void c(long j, long j2) {
        File file = new File(a(j, j2));
        a(file);
        file.delete();
    }

    public void b(a aVar) {
        c(aVar.a, aVar.b);
    }

    private void a(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    a(file2);
                    try {
                        file2.delete();
                    } catch (Exception e) {
                    }
                } else if (file2.exists()) {
                    a(file2);
                    try {
                        file2.delete();
                    } catch (Exception e2) {
                    }
                }
            }
        }
    }
}
