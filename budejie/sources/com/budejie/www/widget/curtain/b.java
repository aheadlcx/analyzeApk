package com.budejie.www.widget.curtain;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.ali.auth.third.core.rpc.protocol.RpcException.ErrorCode;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.video.barrage.a.f;
import com.budejie.www.activity.video.barrage.danmaku.loader.IllegalDataException;
import com.budejie.www.activity.video.barrage.danmaku.loader.a.c;
import com.budejie.www.activity.video.barrage.danmaku.model.android.DanmakuContext;
import com.budejie.www.activity.video.barrage.danmaku.model.android.g;
import com.budejie.www.activity.video.barrage.danmaku.model.l;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.util.an;
import com.budejie.www.widget.FavorLayout;
import com.budejie.www.widget.curtain.BarrageStatusManager.BarrageState;
import com.budejie.www.widget.curtain.c.a;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class b {
    a a = new b$5(this);
    private Context b;
    private f c;
    private FavorLayout d;
    private DanmakuContext e;
    private com.budejie.www.activity.video.barrage.danmaku.a.a f;
    private ArrayList<String> g = new ArrayList();
    private com.budejie.www.activity.video.barrage.b h = new com.budejie.www.activity.video.barrage.b();
    private int i;
    private BarrageState j;
    private int k = -1;
    private SharedPreferences l;
    private net.tsz.afinal.a.a<String> m = new b$4(this);

    public b(Context context, f fVar, FavorLayout favorLayout) {
        this.b = context;
        this.c = fVar;
        this.d = favorLayout;
        this.l = this.b.getSharedPreferences("weiboprefer", 0);
        e();
    }

    public void a(BarrageState barrageState) {
        a(barrageState, false);
    }

    public void a(BarrageState barrageState, boolean z) {
        if (barrageState != this.j) {
            this.j = barrageState;
            if (barrageState == BarrageState.SINGLE) {
                this.c.a();
                if (!z) {
                    this.d.setVisibility(0);
                    this.c.setVisibility(0);
                }
                this.c.d();
            } else if (barrageState == BarrageState.MULTI) {
                this.c.a();
                if (!z) {
                    this.d.setVisibility(0);
                    this.c.setVisibility(0);
                }
                this.c.d();
            } else {
                b();
            }
        }
    }

    void a() {
    }

    void b() {
        this.c.e();
        this.c.setVisibility(8);
        this.d.setVisibility(8);
    }

    void c() {
        if (this.j == BarrageState.SINGLE || this.j == BarrageState.MULTI) {
            this.d.setVisibility(0);
            this.c.setVisibility(0);
            this.c.d();
        }
    }

    @SuppressLint({"UseSparseArrays"})
    private void e() {
        this.e = DanmakuContext.a();
        f();
        this.f = a(this.b.getResources().openRawResource(R.raw.comment));
        this.c.setOnDanmakuClickListener(new b$1(this));
        this.c.setCallback(new b$2(this));
        this.c.a(this.f, this.e);
        this.c.b(false);
        this.c.a(false);
        this.c.setDrawingThreadType(2);
    }

    private void f() {
        Map hashMap = new HashMap();
        hashMap.put(Integer.valueOf(1), Integer.valueOf(12));
        Map hashMap2 = new HashMap();
        hashMap2.put(Integer.valueOf(1), Boolean.valueOf(true));
        this.e.a(3, new float[]{3.0f, 3.0f, 153.0f, 1.0f}).a(false).c(1.0f).b(1.0f).a(0.6f).a(new g()).a(hashMap).b(hashMap2);
    }

    private com.budejie.www.activity.video.barrage.danmaku.a.a a(InputStream inputStream) {
        if (inputStream == null) {
            return new b$3(this);
        }
        com.budejie.www.activity.video.barrage.danmaku.loader.a a = c.a(c.a);
        try {
            a.a(inputStream);
        } catch (IllegalDataException e) {
            e.printStackTrace();
        }
        com.budejie.www.activity.video.barrage.danmaku.a.a bVar = new com.budejie.www.activity.video.barrage.danmaku.a.a.b();
        bVar.a(a.a());
        return bVar;
    }

    private void a(com.budejie.www.activity.video.barrage.a aVar) {
        com.budejie.www.activity.video.barrage.danmaku.model.c a = this.e.t.a(1);
        if (a != null) {
            a.a = aVar;
            a.c = TextUtils.isEmpty(aVar.b) ? "" : aVar.b.replace("\n", "").replace("\t", "");
            a.p = new com.budejie.www.activity.video.barrage.danmaku.model.f(5000);
            a.l = 15;
            if (aVar.e) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a.m = (byte) 1;
                a.e = -57856;
                a.b = this.c.getCurrentTime() + 500;
            } else {
                a.m = (byte) 0;
                if (!TextUtils.isEmpty(aVar.c) && Integer.parseInt(aVar.c) > 10) {
                    a.e = -3840;
                } else if (TextUtils.isEmpty(aVar.c) || Integer.parseInt(aVar.c) <= 5 || Integer.parseInt(aVar.c) > 10) {
                    a.e = -1;
                } else {
                    a.e = -16712449;
                }
                a.b = this.c.getCurrentTime() + 1000;
            }
            a.t = false;
            if (this.f.b() == null) {
                l b = this.e.b();
                DisplayMetrics displayMetrics = this.b.getResources().getDisplayMetrics();
                b.a(displayMetrics.density, displayMetrics.densityDpi, displayMetrics.scaledDensity);
                this.f.a(b);
            }
            a.j = (float) an.a(this.b, 16.0f);
            a.h = -16777216;
            a.i = 0;
            this.c.a(a);
        }
    }

    private void b(com.budejie.www.activity.video.barrage.a aVar) {
        com.budejie.www.activity.video.barrage.danmaku.model.c a = this.e.t.a(5);
        if (a != null) {
            a.a = aVar;
            a.c = TextUtils.isEmpty(aVar.b) ? "" : aVar.b.replace("\n", "").replace("\t", "");
            a.p = new com.budejie.www.activity.video.barrage.danmaku.model.f(3000);
            a.l = 15;
            a.m = (byte) 0;
            a.e = -1;
            a.b = this.c.getCurrentTime() + 1000;
            a.t = false;
            if (this.f.b() == null) {
                l b = this.e.b();
                DisplayMetrics displayMetrics = this.b.getResources().getDisplayMetrics();
                b.a(displayMetrics.density, displayMetrics.densityDpi, displayMetrics.scaledDensity);
                this.f.a(b);
            }
            a.j = (float) an.a(this.b, 16.0f);
            a.h = -16777216;
            a.i = 0;
            this.c.a(a);
        }
    }

    public void a(String str) {
        this.h = new com.budejie.www.activity.video.barrage.b();
        if (!TextUtils.isEmpty(str)) {
            BudejieApplication.a.a(RequstMethod.GET, j.c(str), new j(), this.m);
        }
    }

    public void a(int i) {
        Log.d("BarrageDisplayHandler", "updateBarrage");
        BarrageState a = BarrageStatusManager.a(this.l);
        com.budejie.www.activity.video.barrage.a aVar;
        if (a == BarrageState.SINGLE) {
            Log.d("BarrageDisplayHandler", "updateBarrage BarrageState.SINGLE");
            int i2 = i + 1;
            if (this.i == 0 || Math.abs(i2 - this.i) >= ErrorCode.SERVER_OPERATIONTYPEMISSED) {
                if (this.i == 0 && (this.h.a == null || this.h.a.isEmpty())) {
                    aVar = new com.budejie.www.activity.video.barrage.a();
                    aVar.d = "barrage_empty";
                    aVar.b = "还没有弹幕哦";
                    aVar.g = true;
                    new b$a(this, aVar).execute(new Object[0]);
                } else {
                    b(i2);
                }
                this.i = i2;
            }
        } else if (a == BarrageState.MULTI) {
            Log.d("BarrageDisplayHandler", "updateBarrage BarrageState.MULTI");
            if (Math.abs(this.k - i) >= 1000 || this.k == -1) {
                this.k = i;
                if (this.h.b != null) {
                    ArrayList arrayList = (ArrayList) this.h.b.get(String.valueOf(this.k / 1000));
                    if (arrayList != null) {
                        for (int i3 = 0; i3 < arrayList.size(); i3++) {
                            aVar = (com.budejie.www.activity.video.barrage.a) arrayList.get(i3);
                            aVar.f = false;
                            new b$a(this, aVar).execute(new Object[0]);
                        }
                    }
                }
            }
        }
    }

    private void b(int i) {
        if (this.h != null && this.h.a != null) {
            com.budejie.www.activity.video.barrage.a aVar;
            int i2 = i / 1000;
            for (int i3 = 0; i3 < 3; i3++) {
                ArrayList arrayList = (ArrayList) this.h.a.get(String.valueOf(i2 + i3));
                if (arrayList != null && arrayList.size() > 0) {
                    aVar = (com.budejie.www.activity.video.barrage.a) arrayList.get(0);
                    break;
                }
            }
            aVar = null;
            if (aVar != null) {
                aVar.g = true;
                new b$a(this, aVar).execute(new Object[0]);
            }
        }
    }

    public void d() {
        this.c.a();
        this.c.c();
        g();
    }

    private void g() {
        if (this.g != null && !this.g.isEmpty()) {
            this.g.remove(null);
            Object join = TextUtils.join(",", new HashSet(this.g));
            if (!TextUtils.isEmpty(join)) {
                BudejieApplication.a.a(RequstMethod.POST, "http://d.api.budejie.com/danmu/like/", j.q(this.b, join), null);
                this.g.clear();
            }
        }
    }
}
