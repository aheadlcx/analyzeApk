package cn.xiaochuankeji.tieba.ui.videomaker.sticker;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View;
import android.widget.FrameLayout;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.AnimatedStickerDrawable;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.TemplatedTextStickerDrawable;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.TextStickerDrawable;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.StickerTrace;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends FrameLayout implements OnGestureListener, OnScaleGestureListener, cn.xiaochuankeji.tieba.ui.videomaker.b.b.a {
    private boolean A;
    private AnimatorSet B;
    private AnimatorSet C;
    private int D;
    private Callback E;
    private a a;
    private ArrayList<cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a> b;
    private volatile boolean c;
    private GestureDetector d;
    private ScaleGestureDetector e;
    private float f;
    private float g;
    private cn.xiaochuankeji.tieba.ui.videomaker.b.b h;
    private cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a i;
    private boolean j;
    private float k;
    private c l;
    private c m;
    private Rect n;
    private Rect o;
    private Matrix p;
    private RectF q;
    private RectF r;
    private float[] s;
    private int t;
    private int u;
    private View v;
    private View w;
    private View x;
    private Rect y;
    private boolean z;

    public interface a {
        void a(cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar);

        void b(cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar);

        void c(cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar);
    }

    public b(Context context) {
        this(context, null);
    }

    public b(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public b(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new ArrayList();
        this.n = new Rect();
        this.o = new Rect();
        this.p = new Matrix();
        this.q = new RectF();
        this.r = new RectF();
        this.s = new float[8];
        this.t = -1;
        this.u = -1;
        this.A = true;
        this.E = new Callback(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void invalidateDrawable(@NonNull Drawable drawable) {
                this.a.invalidate();
            }

            public void scheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable, long j) {
            }

            public void unscheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable) {
            }
        };
        this.D = e.a(16.0f);
        a(BaseApplication.getAppContext());
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.sticker_drawable_container, this);
        setVisibility(4);
        setWillNotDraw(false);
        this.v = findViewById(R.id.top_padding_hint);
        this.w = findViewById(R.id.bottom_padding_hint);
        this.x = findViewById(R.id.btn_remove_sticker);
        this.d = new GestureDetector(context, this);
        this.e = new ScaleGestureDetector(context, this);
        if (VERSION.SDK_INT >= 19) {
            this.e.setQuickScaleEnabled(true);
        }
        this.h = new cn.xiaochuankeji.tieba.ui.videomaker.b.b(context, this);
    }

    public void setDelegate(a aVar) {
        this.a = aVar;
    }

    public void a(JSONObject jSONObject) {
        Iterator it = e.a(getContext(), jSONObject).iterator();
        while (it.hasNext()) {
            a((cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a) it.next());
        }
    }

    public void b(JSONObject jSONObject) {
        try {
            jSONObject.put("view_width", getWidth());
            jSONObject.put("view_height", getHeight());
            jSONObject.put("video_aspect_ratio", (double) e());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        e.a(this.b, jSONObject);
    }

    public void a(cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar) {
        aVar.a(this.E);
        this.b.add(aVar);
        this.c = true;
        setVisibility(0);
        invalidate();
    }

    public void b(cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar) {
        this.b.remove(aVar);
        if (aVar != null) {
            aVar.b();
        }
        if (this.b.size() == 0) {
            this.c = false;
            setVisibility(4);
        }
        invalidate();
    }

    public int a() {
        return this.b.size();
    }

    public ArrayList<cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a> b() {
        return a(null);
    }

    public ArrayList<cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a> a(Class<?> cls) {
        ArrayList<cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a> arrayList = new ArrayList();
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar = (cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a) it.next();
            if (cls == null || cls.isInstance(aVar)) {
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    public List<StickerTrace> getTrace() {
        List<cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a> b = b();
        List<StickerTrace> arrayList = new ArrayList();
        if (!(b == null || b.isEmpty())) {
            for (cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a j : b) {
                StickerTrace j2 = j.j();
                if (j2 != null) {
                    arrayList.add(j2);
                }
            }
        }
        return arrayList;
    }

    public void a(int i, int i2) {
        this.t = i;
        this.u = i2;
    }

    public int c() {
        return this.t;
    }

    public int d() {
        return this.u;
    }

    private void i() {
        if (this.i != null) {
            this.i.g();
            if (this.a != null) {
                this.a.a(this.i);
            }
            if (c() != -1) {
                this.v.getLayoutParams().height = c();
                this.v.setVisibility(0);
            }
            if (d() != -1) {
                this.w.getLayoutParams().height = d();
                this.w.setVisibility(0);
            }
        }
    }

    private void j() {
        if (this.i != null) {
            this.i.h();
            if (this.a != null) {
                this.a.b(this.i);
            }
            this.v.setVisibility(8);
            this.w.setVisibility(8);
        }
    }

    public void setVideoAspectRatio(float f) {
        this.k = f;
    }

    public float e() {
        return this.k;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.l = new c(i, i2, this.k);
        this.m = new c(i, i2, this.k);
    }

    public c getAndLockCurrentStickerFrame() {
        if (!this.c) {
            return null;
        }
        if (this.l.b() >= this.m.b()) {
            if (this.l.e()) {
                return this.l;
            }
            if (this.m.e()) {
                return this.m;
            }
            return null;
        } else if (this.m.e()) {
            return this.m;
        } else {
            if (this.l.e()) {
                return this.l;
            }
            return null;
        }
    }

    protected void onDraw(Canvas canvas) {
        c cVar;
        if (this.l.b() <= this.m.b()) {
            if (this.l.d()) {
                cVar = this.l;
            } else {
                if (this.m.d()) {
                    cVar = this.m;
                }
                cVar = null;
            }
        } else if (this.m.d()) {
            cVar = this.m;
        } else {
            if (this.l.d()) {
                cVar = this.l;
            }
            cVar = null;
        }
        if (cVar != null) {
            Bitmap a = cVar.a();
            Canvas canvas2 = new Canvas(a);
            canvas2.drawColor(0, Mode.SRC);
            int width = (a.getWidth() - getWidth()) / 2;
            int height = (a.getHeight() - getHeight()) / 2;
            canvas2.translate((float) width, (float) height);
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                ((cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a) it.next()).draw(canvas2);
            }
            this.n.set(width, height, getWidth() + width, getHeight() + height);
            this.o.set(0, 0, getWidth(), getHeight());
            canvas.drawBitmap(a, this.n, this.o, null);
            cVar.c();
            cVar.f();
        }
    }

    public void f() {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar = (cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a) it.next();
            if (aVar.a()) {
                ((AnimatedStickerDrawable) aVar).stop();
            }
        }
    }

    public void g() {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar = (cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a) it.next();
            if (aVar.a()) {
                ((AnimatedStickerDrawable) aVar).start();
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        if (!isEnabled()) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        if ((action == 0 || action == 5) && this.i == null) {
            this.j = false;
            int pointerCount = motionEvent.getPointerCount();
            if (pointerCount == 1) {
                this.i = a(motionEvent.getX(0), motionEvent.getY(0));
            } else if (pointerCount == 2) {
                this.i = a(motionEvent.getX(0), motionEvent.getY(0), motionEvent.getX(1), motionEvent.getY(1));
            }
            if (this.i != null) {
                this.b.remove(this.i);
                this.b.add(this.i);
            }
        }
        this.d.onTouchEvent(motionEvent);
        this.e.onTouchEvent(motionEvent);
        this.h.a(motionEvent);
        if (action == 1 || action == 3 || action == 4) {
            if (this.j && this.i != null) {
                j();
                a(motionEvent);
                this.x.setVisibility(4);
            }
            this.i = null;
            this.j = false;
            return false;
        } else if (action == 0 || this.i != null) {
            return true;
        } else {
            return false;
        }
    }

    private cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a a(float f, float f2) {
        return a(f, f2, f, f2);
    }

    private cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a a(float f, float f2, float f3, float f4) {
        for (int size = this.b.size() - 1; size >= 0; size--) {
            cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar = (cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a) this.b.get(size);
            Rect copyBounds = aVar.copyBounds();
            if (copyBounds.width() < this.D) {
                copyBounds.inset(copyBounds.width() - this.D, 0);
            }
            if (copyBounds.height() < this.D) {
                copyBounds.inset(0, copyBounds.height() - this.D);
            }
            this.p.reset();
            this.p.preScale(aVar.e(), aVar.e(), copyBounds.exactCenterX(), copyBounds.exactCenterY());
            this.q.set(copyBounds);
            this.p.mapRect(this.q);
            this.p.reset();
            this.p.preRotate(-aVar.f(), copyBounds.exactCenterX(), copyBounds.exactCenterY());
            this.s[0] = f;
            this.s[1] = f2;
            this.s[2] = f3;
            this.s[3] = f4;
            this.p.mapPoints(this.s);
            this.r.set(Math.min(this.s[0], this.s[2]), Math.min(this.s[1], this.s[3]), Math.max(this.s[0], this.s[2]), Math.max(this.s[1], this.s[3]));
            if (RectF.intersects(this.q, this.r)) {
                return aVar;
            }
        }
        return null;
    }

    private void a(cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar, float f, float f2, float f3, float f4, RectF rectF) {
        float f5 = 2.14748365E9f;
        float f6 = -2.14748365E9f;
        Rect copyBounds = aVar.copyBounds();
        copyBounds.offset((int) f, (int) f2);
        this.p.reset();
        this.p.preScale(f3, f3, copyBounds.exactCenterX(), copyBounds.exactCenterY());
        this.p.preRotate(f4, copyBounds.exactCenterX(), copyBounds.exactCenterY());
        this.s[0] = (float) copyBounds.left;
        this.s[1] = (float) copyBounds.top;
        this.s[2] = (float) copyBounds.left;
        this.s[3] = (float) copyBounds.bottom;
        this.s[4] = (float) copyBounds.right;
        this.s[5] = (float) copyBounds.top;
        this.s[6] = (float) copyBounds.right;
        this.s[7] = (float) copyBounds.bottom;
        this.p.mapPoints(this.s);
        int i = 0;
        float f7 = -2.14748365E9f;
        float f8 = 2.14748365E9f;
        while (i < 4) {
            float f9 = this.s[i * 2];
            float f10 = this.s[(i * 2) + 1];
            if (f9 < f8) {
                f8 = f9;
            }
            if (f9 <= f7) {
                f9 = f7;
            }
            if (f10 < f5) {
                f5 = f10;
            }
            if (f10 <= f6) {
                f10 = f6;
            }
            i++;
            f7 = f9;
            f6 = f10;
        }
        rectF.set(f8, f5, f7, f6);
    }

    private boolean k() {
        return (c() == -1 && d() == -1) ? false : true;
    }

    private void a(cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar, float f, float f2) {
        if (k()) {
            a(aVar, f, f2, aVar.e(), aVar.f(), this.q);
            float f3 = this.q.top;
            float f4 = this.q.bottom;
            if (f2 < 0.0f) {
                int c = c();
                if (c != -1 && f3 < ((float) c)) {
                    f2 = Math.min(f2 - (f3 - ((float) c)), 0.0f);
                }
            } else {
                int d = d();
                int height = getHeight();
                if (d != -1 && f4 > ((float) (height - d))) {
                    f2 = Math.max(f2 - (((float) d) + (f4 - ((float) height))), 0.0f);
                }
            }
        }
        aVar.a((int) f, (int) f2);
    }

    private boolean b(cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar, float f, float f2) {
        if (k()) {
            a(aVar, 0.0f, 0.0f, f, f2, this.q);
            float f3 = this.q.top;
            float f4 = this.q.bottom;
            int c = c();
            int d = d();
            int height = getHeight();
            if (c != -1 && f3 < ((float) c)) {
                return true;
            }
            if (d != -1 && f4 > ((float) (height - d))) {
                return true;
            }
        }
        return false;
    }

    private boolean a(MotionEvent motionEvent) {
        if (this.y == null) {
            this.y = new Rect();
            this.x.getFocusedRect(this.y);
            offsetDescendantRectToMyCoords(this.x, this.y);
        }
        int action = motionEvent.getAction() & 255;
        boolean contains = this.y.contains((int) motionEvent.getX(), (int) motionEvent.getY());
        if (action == 2) {
            if (contains != this.z) {
                this.z = contains;
                if (this.z) {
                    Rect bounds = this.i.getBounds();
                    this.i.a(this.y.centerX() - bounds.centerX(), this.y.centerY() - bounds.centerY(), 0.1f);
                    l();
                    this.B.start();
                    this.x.performHapticFeedback(0, 2);
                } else {
                    this.i.i();
                    m();
                    this.C.start();
                }
            }
        } else if (action == 1 || action == 3 || action == 4) {
            if (contains && this.z) {
                b(this.i);
            }
            this.z = false;
        }
        return contains;
    }

    private void l() {
        if (this.B == null) {
            this.B = new AnimatorSet();
            Animator ofFloat = ObjectAnimator.ofFloat(this.x, "scaleX", new float[]{1.0f, 1.2f});
            Animator ofFloat2 = ObjectAnimator.ofFloat(this.x, "scaleY", new float[]{1.0f, 1.2f});
            this.B.setDuration(300);
            this.B.play(ofFloat).with(ofFloat2);
        }
    }

    private void m() {
        if (this.C == null) {
            this.C = new AnimatorSet();
            Animator ofFloat = ObjectAnimator.ofFloat(this.x, "scaleX", new float[]{1.2f, 1.0f});
            Animator ofFloat2 = ObjectAnimator.ofFloat(this.x, "scaleY", new float[]{1.2f, 1.0f});
            this.C.setDuration(300);
            this.C.play(ofFloat).with(ofFloat2);
        }
    }

    public boolean onDown(MotionEvent motionEvent) {
        if (this.i == null) {
            return false;
        }
        return true;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        if (this.i == null && this.A) {
            if (this.a != null) {
                this.a.c(null);
            }
        } else if ((this.i instanceof TextStickerDrawable) || (this.i instanceof TemplatedTextStickerDrawable)) {
            b(this.i);
            if (this.a != null) {
                this.a.c(this.i);
            }
        }
        return true;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (!(this.i == null || this.j)) {
            this.j = true;
            i();
            this.x.setVisibility(0);
        }
        if (this.e.isInProgress() || this.h.b()) {
            return false;
        }
        if (!(this.i == null || !this.j || a(motionEvent2))) {
            a(this.i, (float) ((int) (-f)), (float) ((int) (-f2)));
        }
        return true;
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        float focusX = scaleGestureDetector.getFocusX();
        float focusY = scaleGestureDetector.getFocusY();
        float e = this.i.e() * scaleGestureDetector.getScaleFactor();
        if (scaleGestureDetector.getScaleFactor() < 1.0f || !b(this.i, e, this.i.f())) {
            this.i.a(e);
        }
        a(this.i, (float) ((int) (focusX - this.f)), (float) ((int) (focusY - this.g)));
        this.f = focusX;
        this.g = focusY;
        return true;
    }

    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        if (this.i == null) {
            return false;
        }
        this.f = scaleGestureDetector.getFocusX();
        this.g = scaleGestureDetector.getFocusY();
        return true;
    }

    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
    }

    public boolean a(cn.xiaochuankeji.tieba.ui.videomaker.b.b bVar) {
        float f = this.i.f() - bVar.c();
        if (!b(this.i, this.i.e(), f)) {
            this.i.b(f);
        }
        return true;
    }

    public boolean b(cn.xiaochuankeji.tieba.ui.videomaker.b.b bVar) {
        if (this.i != null) {
            return true;
        }
        return false;
    }

    public void c(cn.xiaochuankeji.tieba.ui.videomaker.b.b bVar) {
    }

    public void h() {
        this.A = false;
    }

    protected void onVisibilityChanged(@NonNull View view, int i) {
        if (this.b != null) {
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                ((cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a) it.next()).setVisible(i == 0, i == 0);
            }
        }
        super.onVisibilityChanged(view, i);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    protected void onDetachedFromWindow() {
        Bitmap a;
        if (this.b != null) {
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                ((cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a) it.next()).b();
            }
        }
        if (this.l != null) {
            a = this.l.a();
            if (!(a == null || a.isRecycled())) {
                a.recycle();
            }
        }
        if (this.m != null) {
            a = this.m.a();
            if (!(a == null || a.isRecycled())) {
                a.recycle();
            }
        }
        super.onDetachedFromWindow();
    }
}
