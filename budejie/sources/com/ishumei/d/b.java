package com.ishumei.d;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import com.ishumei.b.d;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.vidageek.a.b.c;

public class b {
    private static b e = null;
    public int a;
    public int b;
    private Context c;
    private Object d;

    b() {
        this.a = 0;
        this.b = 0;
        this.c = null;
        this.d = null;
        this.c = d.a;
        if (this.c != null) {
            try {
                this.d = new c().a(this.c).a().a(com.ishumei.f.d.g("989a8baf9e9c949e989ab29e919e989a8d")).a();
            } catch (Exception e) {
            }
        }
    }

    public static b a() {
        if (e == null) {
            synchronized (b.class) {
                if (e == null) {
                    e = new b();
                }
            }
        }
        return e;
    }

    public synchronized Map<String, Object> a(Map<String, com.ishumei.b.b.c> map, int i, int i2) {
        Map<String, Object> map2;
        this.a = 0;
        this.b = 0;
        Map<String, Object> hashMap = new HashMap();
        List arrayList = new ArrayList();
        Map hashMap2 = new HashMap();
        Map hashMap3 = new HashMap();
        if (this.c == null) {
            map2 = hashMap;
        } else {
            if (map != null) {
                for (Entry entry : map.entrySet()) {
                    hashMap3.put(((com.ishumei.b.b.c) entry.getValue()).b(), (String) entry.getKey());
                }
            }
            try {
                if (this.d == null) {
                    throw new Exception();
                }
                List list = (List) new c().a(this.d).a().a(com.ishumei.f.d.g("989a8bb6918c8b9e93939a9baf9e9c949e989a8c")).a(new Object[]{Integer.valueOf(0)});
                Collections.sort(list, new Comparator<PackageInfo>(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public int a(PackageInfo packageInfo, PackageInfo packageInfo2) {
                        try {
                            ((Long) new c().a(packageInfo).b().a(com.ishumei.f.d.g("99968d8c8bb6918c8b9e9393ab96929a"))).longValue();
                            ((Long) new c().a(packageInfo2).b().a(com.ishumei.f.d.g("99968d8c8bb6918c8b9e9393ab96929a"))).longValue();
                            Object a = new c().a(packageInfo).b().a(com.ishumei.f.d.g("9e8f8f93969c9e8b969091b6919990"));
                            Object a2 = new c().a(packageInfo2).b().a(com.ishumei.f.d.g("9e8f8f93969c9e8b969091b6919990"));
                            int intValue = ((Integer) new c().a(a).b().a(com.ishumei.f.d.g("99939e988c"))).intValue();
                            int intValue2 = ((Integer) new c().a(a2).b().a(com.ishumei.f.d.g("99939e988c"))).intValue();
                            return ((intValue & 1) == 0 && (intValue & 128) == 0 && ((intValue2 & 1) > 0 || (intValue2 & 128) > 0)) ? -1 : Long.valueOf(-packageInfo.firstInstallTime).compareTo(Long.valueOf(-packageInfo2.firstInstallTime));
                        } catch (Exception e) {
                            e.printStackTrace();
                            return 0;
                        }
                    }

                    public /* synthetic */ int compare(Object obj, Object obj2) {
                        return a((PackageInfo) obj, (PackageInfo) obj2);
                    }
                });
                for (int i3 = 0; i3 < list.size(); i3++) {
                    Object obj = list.get(i3);
                    long longValue = ((Long) new c().a(obj).b().a(com.ishumei.f.d.g("99968d8c8bb6918c8b9e9393ab96929a"))).longValue();
                    String str = (String) new c().a(obj).b().a(com.ishumei.f.d.g("8f9e9c949e989ab19e929a"));
                    Object a = new c().a(obj).b().a(com.ishumei.f.d.g("9e8f8f93969c9e8b969091b6919990"));
                    String str2 = (String) new c().a(a).a().a(com.ishumei.f.d.g("93909e9bb39e9d9a93")).a(new Object[]{r11});
                    int intValue = ((Integer) new c().a(a).b().a(com.ishumei.f.d.g("99939e988c"))).intValue();
                    int i4 = 0;
                    if ((intValue & 1) > 0 || (intValue & 128) > 0) {
                        i4 = 0;
                    } else if ((intValue & 128) == 0) {
                        i4 = 1;
                    }
                    if (hashMap3.containsKey(str)) {
                        hashMap2.put((String) hashMap3.get(str), Integer.valueOf(1));
                    } else if ((i4 != 0 || this.a < i) && (i4 != 1 || this.b < i2)) {
                        arrayList.add(longValue + "," + str + "," + str2 + "," + i4);
                        if (i4 == 1) {
                            this.b++;
                        } else {
                            this.a++;
                        }
                    }
                }
                hashMap.put(com.ishumei.f.d.g("9e8f8f8c"), arrayList);
                hashMap.put(com.ishumei.f.d.g("8897968b9a9e8f8f8c"), hashMap2);
                map2 = hashMap;
            } catch (Exception e) {
            }
        }
        return map2;
    }

    public int b() {
        return this.c.getApplicationInfo().targetSdkVersion;
    }

    public synchronized int c() {
        return this.b;
    }

    public synchronized int d() {
        return this.a;
    }

    public String e() {
        if (this.c == null) {
            return "";
        }
        try {
            String str = (String) new c().a(this.c.getPackageManager().getPackageInfo(f(), 0)).b().a("versionName");
            if (str != null) {
                return str;
            }
            try {
                return "";
            } catch (NameNotFoundException e) {
                return str;
            }
        } catch (NameNotFoundException e2) {
            return "";
        }
    }

    public String f() {
        String str = "";
        if (this.c == null) {
            return "";
        }
        String str2;
        try {
            str2 = (String) new c().a(this.c).a().a(com.ishumei.f.d.g("989a8baf9e9c949e989ab19e929a")).a();
        } catch (Exception e) {
            str2 = str;
        }
        return str2 == null ? "" : str2;
    }

    public String g() {
        try {
            if (this.c == null) {
                return "";
            }
            if (this.d == null) {
                throw new Exception();
            }
            String str = (String) new c().a(new c().a(new c().a(this.d).a().a("getPackageInfo").a(new Object[]{d.a.getPackageName(), Integer.valueOf(0)})).b().a(com.ishumei.f.d.g("9e8f8f93969c9e8b969091b6919990"))).a().a(com.ishumei.f.d.g("93909e9bb39e9d9a93")).a(new Object[]{this.d});
            if (str != null) {
                return str;
            }
            try {
                return "";
            } catch (Exception e) {
                return str;
            }
        } catch (Exception e2) {
            return "";
        }
    }
}
