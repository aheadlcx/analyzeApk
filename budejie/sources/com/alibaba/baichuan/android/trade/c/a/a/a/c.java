package com.alibaba.baichuan.android.trade.c.a.a.a;

import android.net.Uri;
import com.ali.auth.third.login.LoginConstants;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.baichuan.android.trade.utils.d;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class c extends com.alibaba.baichuan.android.trade.c.a.a.c {
    private boolean d = false;
    private final int e = 10240;
    private final Set f = new HashSet();
    private String[] g = new String[]{AlibcConstants.TTID};

    public c(int i, String str, Map map) {
        super(i, str, map);
        g();
    }

    private String a(int i) {
        if (this.b == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = null;
        for (Entry entry : this.b.entrySet()) {
            if (1 != i || !this.f.contains(entry.getKey())) {
                List list = (List) entry.getValue();
                int size = list.size();
                int i2 = 0;
                while (i2 < size) {
                    Object obj2;
                    if (obj != null) {
                        stringBuilder.append("&");
                        obj2 = obj;
                    } else {
                        int i3 = 1;
                    }
                    if (f((String) entry.getKey())) {
                        stringBuilder.append(Uri.encode((String) entry.getKey())).append(LoginConstants.EQUAL).append((String) list.get(i2));
                    } else {
                        stringBuilder.append(Uri.encode((String) entry.getKey())).append(LoginConstants.EQUAL).append(Uri.encode((String) list.get(i2)));
                    }
                    i2++;
                    obj = obj2;
                }
            }
        }
        return stringBuilder.toString();
    }

    private boolean f(String str) {
        for (String equals : this.g) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public String b(String str, String str2) {
        Object obj;
        List list = (List) this.b.get(str);
        if (list == null) {
            list = new ArrayList(2);
            this.b.put(str, list);
        }
        if (list.size() > 0) {
            obj = (String) list.set(0, str2);
        } else {
            list.add(str2);
            String str3 = null;
        }
        if (!d.a(obj, (Object) str2)) {
            this.d = true;
        }
        return obj;
    }

    public void c(String str, String str2) {
        if (((List) this.b.get(str)) == null) {
            List arrayList = new ArrayList(2);
            this.b.put(str, arrayList);
            arrayList.add(str2);
        }
        this.d = true;
    }

    public String[] e(String str) {
        List list = (List) this.b.remove(str);
        if (list == null || list.size() <= 0) {
            return null;
        }
        this.d = true;
        return (String[]) list.toArray(new String[0]);
    }

    public void g() {
        this.f.add("ybhpss");
    }

    public void h() {
        if (this.d) {
            int i = 0;
            while (true) {
                String d = d();
                int indexOf = d.indexOf("?");
                StringBuilder stringBuilder = new StringBuilder();
                if (indexOf != -1) {
                    stringBuilder.append(d.substring(0, indexOf));
                } else {
                    indexOf = d.indexOf("#");
                    if (indexOf != -1) {
                        stringBuilder.append(d.subSequence(0, indexOf));
                    } else {
                        stringBuilder.append(d);
                    }
                }
                d = a(i);
                if (d != null) {
                    stringBuilder.append("?").append(d);
                }
                if (this.c != null) {
                    stringBuilder.append("#").append(this.c);
                }
                super.d(stringBuilder.toString());
                if (d().length() <= 10240) {
                    break;
                }
                int i2 = i + 1;
                if (i == 1) {
                    break;
                }
                i = i2;
            }
            this.d = false;
        }
    }
}
