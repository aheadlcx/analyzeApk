package cn.xiaochuankeji.tieba.background.utils.a;

import android.text.TextUtils;
import com.izuiyou.a.a.b;
import java.util.HashMap;
import java.util.Map.Entry;

public class e {
    private static volatile e a;
    private HashMap<String, Long> b = new HashMap();
    private long c;
    private String d;
    private int e;
    private boolean f;
    private String g;
    private long h;
    private long i;
    private long j;
    private long k;
    private int l;

    private e() {
    }

    public static e a() {
        if (a == null) {
            a = new e();
        }
        return a;
    }

    public void b() {
        if (this.b.size() != 0 && this.e != 0) {
            long j = -1;
            long j2 = 0;
            for (Entry entry : this.b.entrySet()) {
                j2 += ((Long) entry.getValue()).longValue();
                if (-1 == j) {
                    j = Long.valueOf((String) entry.getKey()).longValue();
                }
            }
            Object hashMap = new HashMap();
            if (0 != this.h) {
                hashMap.put("tid", Long.valueOf(this.h));
            }
            if (0 != this.i) {
                hashMap.put("pgcid", Long.valueOf(this.i));
            }
            hashMap.put("remain_time", Long.valueOf(j2 / 1000));
            hashMap.put("img_num", Integer.valueOf(this.e));
            hashMap.put("view_num", Integer.valueOf(this.b.size()));
            if (!TextUtils.isEmpty(this.g)) {
                hashMap.put("owner", this.g);
                if (this.g.equals("review")) {
                    hashMap.put("pid", Long.valueOf(this.j));
                    hashMap.put("prid", Long.valueOf(this.k));
                    hashMap.put("rstatus", Integer.valueOf(this.l));
                }
            }
            if (this.f) {
                hashMap.put("imgs_fmt", "gif");
            } else {
                hashMap.put("imgs_fmt", "jpg");
            }
            b.b("pic stat==picture id:" + j + "oid:" + this.c + ", source:" + this.d + ", tid:" + this.h + ", remain_time : " + (j2 / 1000) + ", img num:" + this.e + ", view num:" + this.b.size());
            cn.xiaochuankeji.tieba.background.utils.monitor.a.b.a().a("view", "img", j, this.c, this.d, hashMap);
        }
    }

    public void a(long j, String str, int i, long j2, long j3, String str2, boolean z) {
        this.b.clear();
        this.c = j;
        this.d = str;
        this.e = i;
        this.h = j2;
        this.i = j3;
        this.g = str2;
        this.f = z;
    }

    public void a(long j, long j2, int i) {
        this.j = j;
        this.k = j2;
        this.l = i;
    }

    public void a(long j, long j2) {
        this.b.put(String.valueOf(j), Long.valueOf(j2));
    }
}
