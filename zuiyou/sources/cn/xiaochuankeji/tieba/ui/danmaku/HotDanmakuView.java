package cn.xiaochuankeji.tieba.ui.danmaku;

import android.content.Context;
import android.graphics.RectF;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.ViewCompat;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import cn.xiaochuankeji.tieba.background.danmaku.DanmakuItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.e;
import master.flame.danmaku.danmaku.model.d;
import master.flame.danmaku.danmaku.model.f;
import master.flame.danmaku.danmaku.model.l;
import master.flame.danmaku.danmaku.model.l.c;

public class HotDanmakuView extends master.flame.danmaku.b.a.b {
    private static final int[] b = new int[]{-1591755, -12407563, -4587265, -13710238};
    private DanmakuContext c;
    private int d = 0;
    private b e;
    private boolean f;
    private boolean g;
    private ArrayList<DanmakuItem> h;
    private RectF i;
    private float j;
    private float k;
    private l l;
    private volatile HashMap<Long, Long> m = new HashMap();
    private int n = 0;

    private class a extends master.flame.danmaku.danmaku.a.a {
        final /* synthetic */ HotDanmakuView a;

        private a(HotDanmakuView hotDanmakuView) {
            this.a = hotDanmakuView;
        }

        protected l a() {
            return new e();
        }
    }

    public interface b {
        void a(d dVar, float f, float f2);

        void b(d dVar, float f, float f2);
    }

    public HotDanmakuView(Context context) {
        super(context);
        r();
    }

    public HotDanmakuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        r();
    }

    public HotDanmakuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        r();
    }

    private void r() {
        this.i = new RectF();
        new HashMap().put(Integer.valueOf(1), Integer.valueOf(5));
        Map hashMap = new HashMap();
        hashMap.put(Integer.valueOf(1), Boolean.valueOf(true));
        hashMap.put(Integer.valueOf(5), Boolean.valueOf(true));
        this.c = DanmakuContext.a();
        this.c.a(0, new float[]{3.0f}).a(false).b(1.5f).a(1.2f).a(new c(), null).a(14).a(hashMap);
        setCallback(new master.flame.danmaku.a.c.a(this) {
            final /* synthetic */ HotDanmakuView a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.a.f) {
                            this.a.a.n();
                            this.a.a.f = false;
                        }
                        if (this.a.a.h != null) {
                            this.a.a.a(this.a.a.h);
                            this.a.a.h = null;
                        }
                    }
                });
            }

            public void a(f fVar) {
            }

            public void a(d dVar) {
            }

            public void b() {
            }
        });
        a(new a(), this.c);
        b(false);
    }

    public void setCustomTextSize(int i) {
        this.n = i;
    }

    public boolean a() {
        return this.g;
    }

    public void b() {
        this.g = false;
    }

    public void a(long j) {
        if (!this.g) {
            if (k()) {
                super.a(j);
                this.g = true;
                return;
            }
            this.f = true;
        }
    }

    public void c() {
        if (a()) {
            super.c();
        }
    }

    public void d() {
        if (a()) {
            super.d();
        }
    }

    public void e() {
        a(true);
        super.e();
    }

    public void a(ArrayList<DanmakuItem> arrayList) {
        if (k()) {
            DanmakuItem danmakuItem;
            int size = arrayList.size();
            List<DanmakuItem> arrayList2 = new ArrayList();
            int i = 0;
            while (i < size && i < arrayList.size()) {
                danmakuItem = (DanmakuItem) arrayList.get(i);
                if (danmakuItem != null && (this.m.get(Long.valueOf(danmakuItem.id)) == null || ((Long) this.m.get(Long.valueOf(danmakuItem.id))).longValue() < 0)) {
                    this.m.put(Long.valueOf(danmakuItem.id), Long.valueOf(danmakuItem.createTime));
                    arrayList2.add(danmakuItem);
                }
                i++;
            }
            com.izuiyou.a.a.b.d("add danmu:" + arrayList2.size());
            for (DanmakuItem danmakuItem2 : arrayList2) {
                if (!TextUtils.isEmpty(danmakuItem2.text)) {
                    a(a(danmakuItem2, (byte) 0));
                }
            }
            return;
        }
        this.h = arrayList;
    }

    public void a(DanmakuItem danmakuItem) {
        if (!TextUtils.isEmpty(danmakuItem.text)) {
            a(a(danmakuItem, (byte) 1));
        }
    }

    private d a(DanmakuItem danmakuItem, byte b) {
        int length;
        int length2;
        d bVar = new b(this.c.v.a(1, this.c).r);
        CharSequence spannableStringBuilder = new SpannableStringBuilder();
        if (danmakuItem.hasSound) {
            length = spannableStringBuilder.length();
            spannableStringBuilder.append(danmakuItem.text);
            length2 = spannableStringBuilder.length();
        } else {
            length = spannableStringBuilder.length();
            spannableStringBuilder.append(danmakuItem.text);
            length2 = spannableStringBuilder.length();
        }
        if (danmakuItem.showLikes) {
            int length3 = spannableStringBuilder.length();
            spannableStringBuilder.append("^" + cn.xiaochuankeji.tieba.ui.utils.d.b(danmakuItem.likes));
            int[] iArr = b;
            int i = this.d;
            this.d = i + 1;
            int i2 = iArr[i % b.length];
            spannableStringBuilder.setSpan(new f(-1, (ViewCompat.MEASURED_SIZE_MASK & i2) | -1728053248), length3, spannableStringBuilder.length(), 18);
            bVar.g = i2;
        } else {
            bVar.g = -1;
        }
        spannableStringBuilder.setSpan(new g(bVar.g, -12303292), length, length2, 18);
        bVar.c = spannableStringBuilder;
        bVar.n = cn.htjyb.c.a.a(3.0f, getContext());
        bVar.o = b;
        bVar.y = false;
        bVar.d((long) Math.max(0, danmakuItem.pos + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED));
        if (this.n != 0) {
            bVar.l = (float) cn.htjyb.c.a.b((float) this.n, getContext());
        } else {
            bVar.l = (float) cn.htjyb.c.a.b(14.0f, getContext());
        }
        bVar.a = danmakuItem;
        return bVar;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = true;
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        switch (motionEvent.getAction()) {
            case 0:
                this.j = motionEvent.getX();
                this.k = motionEvent.getY();
                this.l = a(this.j, this.k);
                return true;
            case 1:
                if (!(this.l == null || this.l.e())) {
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();
                    d d = this.l.d();
                    float f = d.q / 2.0f;
                    if (Math.abs(y - this.k) > Math.abs(x - this.j) && this.e != null) {
                        if (motionEvent.getY() - this.k > f) {
                            this.e.b(d, this.j, this.k);
                        } else if (motionEvent.getY() - this.k < (-f)) {
                            this.e.a(d, this.j, this.k);
                        }
                        this.l = null;
                        return z;
                    }
                }
                z = onTouchEvent;
                this.l = null;
                return z;
            default:
                return onTouchEvent;
        }
    }

    public void setOnDanmakuSwipeListener(b bVar) {
        this.e = bVar;
    }

    private l a(final float f, final float f2) {
        final l eVar = new e();
        this.i.setEmpty();
        l currentVisibleDanmakus = getCurrentVisibleDanmakus();
        if (!(currentVisibleDanmakus == null || currentVisibleDanmakus.e())) {
            currentVisibleDanmakus.a(new c<d>(this) {
                final /* synthetic */ HotDanmakuView d;

                public int a(d dVar) {
                    if (dVar != null) {
                        this.d.i.set(dVar.k(), dVar.l(), dVar.m(), dVar.n());
                        if (this.d.i.contains(f, f2)) {
                            eVar.a(dVar);
                        }
                    }
                    return 0;
                }
            });
        }
        return eVar;
    }
}
