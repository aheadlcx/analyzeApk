package com.tencent.bugly.crashreport.biz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.ae;
import com.tencent.bugly.proguard.ah;
import com.tencent.bugly.proguard.aj;
import com.tencent.bugly.proguard.ak;
import com.tencent.bugly.proguard.am;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ap;
import com.tencent.bugly.proguard.bd;
import com.tencent.bugly.proguard.be;
import com.tencent.bugly.proguard.m;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a {
    private Context a;
    private long b;
    private int c;
    private boolean d = true;

    class a implements Runnable {
        final /* synthetic */ a a;
        private boolean b;
        private UserInfoBean c;

        public a(a aVar, UserInfoBean userInfoBean, boolean z) {
            this.a = aVar;
            this.c = userInfoBean;
            this.b = z;
        }

        private void a(UserInfoBean userInfoBean) {
            if (userInfoBean != null) {
                com.tencent.bugly.crashreport.common.info.a b = com.tencent.bugly.crashreport.common.info.a.b();
                if (b != null) {
                    userInfoBean.j = b.e();
                }
            }
        }

        public void run() {
            try {
                if (this.c != null) {
                    a(this.c);
                    an.c("[UserInfo] Record user info.", new Object[0]);
                    this.a.a(this.c, false);
                }
                if (this.b) {
                    this.a.b();
                }
            } catch (Throwable th) {
                if (!an.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    class b implements Runnable {
        final /* synthetic */ a a;

        b(a aVar) {
            this.a = aVar;
        }

        public void run() {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < this.a.b) {
                am.a().a(new b(this.a), (this.a.b - currentTimeMillis) + 5000);
                return;
            }
            this.a.a(3, false, 0);
            this.a.a();
        }
    }

    class c implements Runnable {
        final /* synthetic */ a a;
        private long b = 21600000;

        public c(a aVar, long j) {
            this.a = aVar;
            this.b = j;
        }

        public void run() {
            this.a.b();
            this.a.b(this.b);
        }
    }

    public a(Context context, boolean z) {
        this.a = context;
        this.d = z;
    }

    private static UserInfoBean a(Context context, int i) {
        int i2 = 1;
        com.tencent.bugly.crashreport.common.info.a a = com.tencent.bugly.crashreport.common.info.a.a(context);
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.b = i;
        userInfoBean.c = a.e;
        userInfoBean.d = a.g();
        userInfoBean.e = System.currentTimeMillis();
        userInfoBean.f = -1;
        userInfoBean.n = a.o;
        if (i != 1) {
            i2 = 0;
        }
        userInfoBean.o = i2;
        userInfoBean.l = a.a();
        userInfoBean.m = a.u;
        userInfoBean.g = a.v;
        userInfoBean.h = a.w;
        userInfoBean.i = a.x;
        userInfoBean.k = a.y;
        userInfoBean.r = a.B();
        userInfoBean.s = a.G();
        userInfoBean.p = a.H();
        userInfoBean.q = a.I();
        return userInfoBean;
    }

    public void a(int i, boolean z, long j) {
        com.tencent.bugly.crashreport.common.strategy.a a = com.tencent.bugly.crashreport.common.strategy.a.a();
        if (a == null || a.c().h || i == 1 || i == 3) {
            if (i == 1 || i == 3) {
                this.c++;
            }
            am.a().a(new a(this, a(this.a, i), z), j);
            return;
        }
        an.e("UserInfo is disable", new Object[0]);
    }

    public void a(long j) {
        am.a().a(new a(this, null, true), j);
    }

    public void b(long j) {
        am.a().a(new c(this, j), j);
    }

    public void a() {
        this.b = ap.b() + com.umeng.analytics.a.i;
        am.a().a(new b(this), (this.b - System.currentTimeMillis()) + 5000);
    }

    private synchronized void c() {
        boolean z = false;
        synchronized (this) {
            if (this.d) {
                ak a = ak.a();
                if (a != null) {
                    com.tencent.bugly.crashreport.common.strategy.a a2 = com.tencent.bugly.crashreport.common.strategy.a.a();
                    if (a2 != null && (!a2.b() || a.b(1001))) {
                        boolean z2;
                        List list;
                        String str = com.tencent.bugly.crashreport.common.info.a.a(this.a).e;
                        List arrayList = new ArrayList();
                        List a3 = a(str);
                        if (a3 != null) {
                            int i;
                            UserInfoBean userInfoBean;
                            int i2;
                            int size = a3.size() - 20;
                            if (size > 0) {
                                for (int i3 = 0; i3 < a3.size() - 1; i3++) {
                                    for (i = i3 + 1; i < a3.size(); i++) {
                                        if (((UserInfoBean) a3.get(i3)).e > ((UserInfoBean) a3.get(i)).e) {
                                            userInfoBean = (UserInfoBean) a3.get(i3);
                                            a3.set(i3, a3.get(i));
                                            a3.set(i, userInfoBean);
                                        }
                                    }
                                }
                                for (i2 = 0; i2 < size; i2++) {
                                    arrayList.add(a3.get(i2));
                                }
                            }
                            Iterator it = a3.iterator();
                            i = 0;
                            while (it.hasNext()) {
                                userInfoBean = (UserInfoBean) it.next();
                                if (userInfoBean.f != -1) {
                                    it.remove();
                                    if (userInfoBean.e < ap.b()) {
                                        arrayList.add(userInfoBean);
                                    }
                                }
                                if (userInfoBean.e <= System.currentTimeMillis() - 600000 || !(userInfoBean.b == 1 || userInfoBean.b == 4 || userInfoBean.b == 3)) {
                                    i2 = i;
                                } else {
                                    i2 = i + 1;
                                }
                                i = i2;
                            }
                            if (i > 15) {
                                an.d("[UserInfo] Upload user info too many times in 10 min: %d", new Object[]{Integer.valueOf(i)});
                                z2 = false;
                            } else {
                                z2 = true;
                            }
                            list = a3;
                        } else {
                            Object arrayList2 = new ArrayList();
                            z2 = true;
                        }
                        if (arrayList.size() > 0) {
                            a(arrayList);
                        }
                        if (!z2 || list.size() == 0) {
                            an.c("[UserInfo] There is no user info in local database.", new Object[0]);
                        } else {
                            an.c("[UserInfo] Upload user info(size: %d)", new Object[]{Integer.valueOf(list.size())});
                            m a4 = ah.a(list, this.c == 1 ? 1 : 2);
                            if (a4 == null) {
                                an.d("[UserInfo] Failed to create UserInfoPackage.", new Object[0]);
                            } else {
                                byte[] a5 = ah.a(a4);
                                if (a5 == null) {
                                    an.d("[UserInfo] Failed to encode data.", new Object[0]);
                                } else {
                                    bd a6 = ah.a(this.a, a.b ? 840 : 640, a5);
                                    if (a6 == null) {
                                        an.d("[UserInfo] Request package is null.", new Object[0]);
                                    } else {
                                        aj anonymousClass1 = new aj(this) {
                                            final /* synthetic */ a b;

                                            public void a(int i) {
                                            }

                                            public void a(int i, be beVar, long j, long j2, boolean z, String str) {
                                                if (z) {
                                                    an.c("[UserInfo] Successfully uploaded user info.", new Object[0]);
                                                    long currentTimeMillis = System.currentTimeMillis();
                                                    for (UserInfoBean userInfoBean : list) {
                                                        userInfoBean.f = currentTimeMillis;
                                                        this.b.a(userInfoBean, true);
                                                    }
                                                }
                                            }
                                        };
                                        StrategyBean c = com.tencent.bugly.crashreport.common.strategy.a.a().c();
                                        String str2 = a.b ? c.r : c.t;
                                        String str3 = a.b ? StrategyBean.b : StrategyBean.a;
                                        ak a7 = ak.a();
                                        if (this.c == 1) {
                                            z = true;
                                        }
                                        a7.a(1001, a6, str2, str3, anonymousClass1, z);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void b() {
        am a = am.a();
        if (a != null) {
            a.a(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    try {
                        this.a.c();
                    } catch (Throwable th) {
                        an.a(th);
                    }
                }
            });
        }
    }

    private void a(UserInfoBean userInfoBean, boolean z) {
        if (userInfoBean != null) {
            if (!(z || userInfoBean.b == 1)) {
                List a = a(com.tencent.bugly.crashreport.common.info.a.a(this.a).e);
                if (a != null && a.size() >= 20) {
                    an.a("[UserInfo] There are too many user info in local: %d", new Object[]{Integer.valueOf(a.size())});
                    return;
                }
            }
            long a2 = ae.a().a("t_ui", a(userInfoBean), null, true);
            if (a2 >= 0) {
                an.c("[Database] insert %s success with ID: %d", new Object[]{"t_ui", Long.valueOf(a2)});
                userInfoBean.a = a2;
            }
        }
    }

    public List<UserInfoBean> a(String str) {
        Throwable th;
        Cursor cursor;
        Cursor a;
        try {
            a = ae.a().a("t_ui", null, ap.a(str) ? null : "_pc = '" + str + "'", null, null, true);
            if (a == null) {
                if (a != null) {
                    a.close();
                }
                return null;
            }
            try {
                StringBuilder stringBuilder = new StringBuilder();
                List<UserInfoBean> arrayList = new ArrayList();
                while (a.moveToNext()) {
                    UserInfoBean a2 = a(a);
                    if (a2 != null) {
                        arrayList.add(a2);
                    } else {
                        try {
                            stringBuilder.append(" or ").append("_id").append(" = ").append(a.getLong(a.getColumnIndex("_id")));
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                }
                String stringBuilder2 = stringBuilder.toString();
                if (stringBuilder2.length() > 0) {
                    int a3 = ae.a().a("t_ui", stringBuilder2.substring(" or ".length()), null, null, true);
                    an.d("[Database] deleted %s error data %d", new Object[]{"t_ui", Integer.valueOf(a3)});
                }
                if (a != null) {
                    a.close();
                }
                return arrayList;
            } catch (Throwable th22) {
                th = th22;
            }
        } catch (Throwable th3) {
            th = th3;
            a = null;
            if (a != null) {
                a.close();
            }
            throw th;
        }
    }

    public void a(List<UserInfoBean> list) {
        if (list != null && list.size() != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            int i = 0;
            while (i < list.size() && i < 50) {
                stringBuilder.append(" or ").append("_id").append(" = ").append(((UserInfoBean) list.get(i)).a);
                i++;
            }
            String stringBuilder2 = stringBuilder.toString();
            if (stringBuilder2.length() > 0) {
                stringBuilder2 = stringBuilder2.substring(" or ".length());
            }
            stringBuilder.setLength(0);
            try {
                int a = ae.a().a("t_ui", stringBuilder2, null, null, true);
                an.c("[Database] deleted %s data %d", new Object[]{"t_ui", Integer.valueOf(a)});
            } catch (Throwable th) {
                if (!an.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    protected ContentValues a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (userInfoBean.a > 0) {
                contentValues.put("_id", Long.valueOf(userInfoBean.a));
            }
            contentValues.put("_tm", Long.valueOf(userInfoBean.e));
            contentValues.put("_ut", Long.valueOf(userInfoBean.f));
            contentValues.put("_tp", Integer.valueOf(userInfoBean.b));
            contentValues.put("_pc", userInfoBean.c);
            contentValues.put("_dt", ap.a(userInfoBean));
            return contentValues;
        } catch (Throwable th) {
            if (an.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    protected UserInfoBean a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex("_id"));
            UserInfoBean userInfoBean = (UserInfoBean) ap.a(blob, UserInfoBean.CREATOR);
            if (userInfoBean == null) {
                return userInfoBean;
            }
            userInfoBean.a = j;
            return userInfoBean;
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}
