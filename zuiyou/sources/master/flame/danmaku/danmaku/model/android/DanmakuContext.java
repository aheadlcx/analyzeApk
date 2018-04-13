package master.flame.danmaku.danmaku.model.android;

import android.graphics.Typeface;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import master.flame.danmaku.danmaku.model.b;
import master.flame.danmaku.danmaku.model.c;
import master.flame.danmaku.danmaku.model.j;

public class DanmakuContext {
    private boolean A = false;
    private boolean B = false;
    private b C;
    private boolean D;
    private boolean E;
    private final b F = new a();
    public Typeface a = null;
    public int b = c.a;
    public float c = 1.0f;
    public int d = 0;
    public boolean e = true;
    public boolean f = true;
    public boolean g = true;
    public boolean h = true;
    public boolean i = true;
    List<Integer> j = new ArrayList();
    public int k = -1;
    public float l = 1.0f;
    public int m = 15;
    public BorderType n = BorderType.SHADOW;
    public master.flame.danmaku.danmaku.model.a o;
    public int p = 3;
    List<Integer> q = new ArrayList();
    List<Integer> r = new ArrayList();
    List<String> s = new ArrayList();
    public final j t = new j();
    public final master.flame.danmaku.a.b u = new master.flame.danmaku.a.b();
    public final d v = d.a();
    public c w = c.c;
    public byte x = (byte) 0;
    private List<WeakReference<a>> y;
    private boolean z = false;

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
        OVERLAPPING_ENABLE,
        ALIGN_BOTTOM,
        DANMAKU_MARGIN;

        public boolean isVisibilityRelatedTag() {
            return equals(FT_DANMAKU_VISIBILITY) || equals(FB_DANMAKU_VISIBILITY) || equals(L2R_DANMAKU_VISIBILITY) || equals(R2L_DANMAKU_VISIBILIY) || equals(SPECIAL_DANMAKU_VISIBILITY) || equals(COLOR_VALUE_WHITE_LIST) || equals(USER_ID_BLACK_LIST);
        }
    }

    public static DanmakuContext a() {
        return new DanmakuContext();
    }

    public b b() {
        return this.F;
    }

    public DanmakuContext a(float f) {
        if (this.c != f) {
            this.c = f;
            this.F.c();
            this.F.a(f);
            this.t.c();
            this.t.b();
            a(DanmakuConfigTag.SCALE_TEXTSIZE, Float.valueOf(f));
        }
        return this;
    }

    public DanmakuContext a(int i) {
        if (this.d != i) {
            this.d = i;
            this.F.a(i);
            this.t.d();
            this.t.b();
            a(DanmakuConfigTag.DANMAKU_MARGIN, Integer.valueOf(i));
        }
        return this;
    }

    private <T> void a(String str, T t, boolean z) {
        this.u.a(str, z).a(t);
    }

    public DanmakuContext a(int i, float... fArr) {
        this.F.a(i, fArr);
        a(DanmakuConfigTag.DANMAKU_STYLE, Integer.valueOf(i), fArr);
        return this;
    }

    public DanmakuContext b(float f) {
        if (this.l != f) {
            this.l = f;
            this.v.a(f);
            this.t.c();
            this.t.b();
            a(DanmakuConfigTag.SCROLL_SPEED_FACTOR, Float.valueOf(f));
        }
        return this;
    }

    public DanmakuContext a(boolean z) {
        if (this.A != z) {
            this.A = z;
            this.t.d();
            a(DanmakuConfigTag.DUPLICATE_MERGING_ENABLED, Boolean.valueOf(z));
        }
        return this;
    }

    public boolean c() {
        return this.A;
    }

    public boolean d() {
        return this.B;
    }

    public DanmakuContext a(Map<Integer, Boolean> map) {
        this.E = map != null;
        if (map == null) {
            this.u.c("1019_Filter", false);
        } else {
            a("1019_Filter", map, false);
        }
        this.t.d();
        a(DanmakuConfigTag.OVERLAPPING_ENABLE, map);
        return this;
    }

    public boolean e() {
        return this.D;
    }

    public boolean f() {
        return this.E;
    }

    public DanmakuContext a(b bVar, master.flame.danmaku.danmaku.model.android.b.a aVar) {
        this.C = bVar;
        if (this.C != null) {
            this.C.a(aVar);
            this.F.a(this.C);
        }
        return this;
    }

    public void a(a aVar) {
        if (aVar == null || this.y == null) {
            this.y = Collections.synchronizedList(new ArrayList());
        }
        for (WeakReference weakReference : this.y) {
            if (aVar.equals(weakReference.get())) {
                return;
            }
        }
        this.y.add(new WeakReference(aVar));
    }

    public void g() {
        if (this.y != null) {
            this.y.clear();
            this.y = null;
        }
    }

    private void a(DanmakuConfigTag danmakuConfigTag, Object... objArr) {
        if (this.y != null) {
            for (WeakReference weakReference : this.y) {
                a aVar = (a) weakReference.get();
                if (aVar != null) {
                    aVar.a(this, danmakuConfigTag, objArr);
                }
            }
        }
    }
}
