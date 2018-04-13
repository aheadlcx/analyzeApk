package com.budejie.www.activity.video.barrage.danmaku.model.android;

import android.graphics.Typeface;
import com.budejie.www.activity.video.barrage.danmaku.model.b;
import com.budejie.www.activity.video.barrage.danmaku.model.i;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DanmakuContext {
    private final com.budejie.www.activity.video.barrage.danmaku.model.a A = new a();
    public Typeface a = null;
    public int b = b.a;
    public float c = 1.0f;
    public boolean d = true;
    public boolean e = true;
    public boolean f = true;
    public boolean g = true;
    public boolean h = true;
    List<Integer> i = new ArrayList();
    public int j = -1;
    public float k = 1.0f;
    public int l = 15;
    public BorderType m = BorderType.SHADOW;
    public int n = 3;
    List<Integer> o = new ArrayList();
    List<Integer> p = new ArrayList();
    List<String> q = new ArrayList();
    public final i r = new i();
    public final com.budejie.www.activity.video.barrage.a.b s = new com.budejie.www.activity.video.barrage.a.b();
    public final com.budejie.www.activity.video.barrage.danmaku.a.b t = new com.budejie.www.activity.video.barrage.danmaku.a.b();
    private List<WeakReference<a>> u;
    private boolean v = false;
    private boolean w = false;
    private b x;
    private boolean y;
    private boolean z;

    public interface a {
        boolean a(DanmakuContext danmakuContext, DanmakuConfigTag danmakuConfigTag, Object... objArr);
    }

    public enum BorderType {
        NONE,
        SHADOW,
        STROKEN
    }

    public enum DanmakuConfigTag {
        FT_DANMAKU_VISIBILITY,
        FB_DANMAKU_VISIBILITY,
        L2R_DANMAKU_VISIBILITY,
        R2L_DANMAKU_VISIBILIY,
        SPECIAL_DANMAKU_VISIBILITY,
        TYPEFACE,
        TRANSPARENCY,
        SCALE_TEXTSIZE,
        MAXIMUM_NUMS_IN_SCREEN,
        DANMAKU_STYLE,
        DANMAKU_BOLD,
        COLOR_VALUE_WHITE_LIST,
        USER_ID_BLACK_LIST,
        USER_HASH_BLACK_LIST,
        SCROLL_SPEED_FACTOR,
        BLOCK_GUEST_DANMAKU,
        DUPLICATE_MERGING_ENABLED,
        MAXIMUN_LINES,
        OVERLAPPING_ENABLE;

        public boolean isVisibilityRelatedTag() {
            return equals(FT_DANMAKU_VISIBILITY) || equals(FB_DANMAKU_VISIBILITY) || equals(L2R_DANMAKU_VISIBILITY) || equals(R2L_DANMAKU_VISIBILIY) || equals(SPECIAL_DANMAKU_VISIBILITY) || equals(COLOR_VALUE_WHITE_LIST) || equals(USER_ID_BLACK_LIST);
        }
    }

    public static DanmakuContext a() {
        return new DanmakuContext();
    }

    public com.budejie.www.activity.video.barrage.danmaku.model.a b() {
        return this.A;
    }

    public DanmakuContext a(float f) {
        int i = (int) (((float) b.a) * f);
        if (i != this.b) {
            this.b = i;
            this.A.a(i);
            a(DanmakuConfigTag.TRANSPARENCY, Float.valueOf(f));
        }
        return this;
    }

    public DanmakuContext b(float f) {
        if (this.c != f) {
            this.c = f;
            this.A.c();
            this.A.a(f);
            this.r.c();
            this.r.b();
            a(DanmakuConfigTag.SCALE_TEXTSIZE, Float.valueOf(f));
        }
        return this;
    }

    private <T> void a(String str, T t, boolean z) {
        this.s.a(str, z).a(t);
    }

    public DanmakuContext a(int i, float... fArr) {
        this.A.a(i, fArr);
        a(DanmakuConfigTag.DANMAKU_STYLE, Integer.valueOf(i), fArr);
        return this;
    }

    public DanmakuContext c(float f) {
        if (this.k != f) {
            this.k = f;
            this.t.a(f);
            this.r.c();
            this.r.b();
            a(DanmakuConfigTag.SCROLL_SPEED_FACTOR, Float.valueOf(f));
        }
        return this;
    }

    public DanmakuContext a(boolean z) {
        if (this.w != z) {
            this.w = z;
            this.r.d();
            a(DanmakuConfigTag.DUPLICATE_MERGING_ENABLED, Boolean.valueOf(z));
        }
        return this;
    }

    public boolean c() {
        return this.w;
    }

    public DanmakuContext a(Map<Integer, Integer> map) {
        this.y = map != null;
        if (map == null) {
            this.s.c("1018_Filter", false);
        } else {
            a("1018_Filter", map, false);
        }
        this.r.d();
        a(DanmakuConfigTag.MAXIMUN_LINES, map);
        return this;
    }

    public DanmakuContext b(Map<Integer, Boolean> map) {
        this.z = map != null;
        if (map == null) {
            this.s.c("1019_Filter", false);
        } else {
            a("1019_Filter", map, false);
        }
        this.r.d();
        a(DanmakuConfigTag.OVERLAPPING_ENABLE, map);
        return this;
    }

    public boolean d() {
        return this.y;
    }

    public boolean e() {
        return this.z;
    }

    public DanmakuContext a(b bVar) {
        this.x = bVar;
        if (this.x != null) {
            this.A.a(this.x);
        }
        return this;
    }

    public void a(a aVar) {
        if (aVar == null || this.u == null) {
            this.u = Collections.synchronizedList(new ArrayList());
        }
        for (WeakReference weakReference : this.u) {
            if (aVar.equals(weakReference.get())) {
                return;
            }
        }
        this.u.add(new WeakReference(aVar));
    }

    public void f() {
        if (this.u != null) {
            this.u.clear();
            this.u = null;
        }
    }

    private void a(DanmakuConfigTag danmakuConfigTag, Object... objArr) {
        if (this.u != null) {
            for (WeakReference weakReference : this.u) {
                a aVar = (a) weakReference.get();
                if (aVar != null) {
                    aVar.a(this, danmakuConfigTag, objArr);
                }
            }
        }
    }
}
