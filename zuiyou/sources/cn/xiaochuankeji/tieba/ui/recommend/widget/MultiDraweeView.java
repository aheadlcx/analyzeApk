package cn.xiaochuankeji.tieba.ui.recommend.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import c.a.i.u;
import cn.xiaochuankeji.tieba.R;
import com.facebook.drawee.drawable.n$b;
import com.facebook.drawee.generic.b;
import com.facebook.drawee.view.e;
import com.facebook.imagepipeline.common.c;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.util.ArrayList;
import java.util.List;

public class MultiDraweeView extends View implements u {
    public static final String a = MultiDraweeView.class.getSimpleName();
    private static int c = 9;
    e<com.facebook.drawee.generic.a> b;
    private int d = 3;
    private int e;
    private int f;
    private List<String> g = new ArrayList();
    private GestureDetector h;
    private a i;
    private int j;
    private int k;

    public interface a {
        void a();

        void a(int i, Rect rect);
    }

    public MultiDraweeView(Context context) {
        super(context);
        a(context);
    }

    public MultiDraweeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public MultiDraweeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public void setOnItemClickListener(a aVar) {
        this.i = aVar;
    }

    private void a(Context context) {
        int i = 0;
        this.e = (int) a(context, 3.0f);
        b a = new b(getResources()).a(new ColorDrawable(c.a.d.a.a.a().a((int) R.color.image_placeholder))).c(new ColorDrawable(c.a.d.a.a.a().a((int) R.color.tale_img_bg))).e(n$b.g).a(0);
        this.b = new e();
        while (i < c) {
            com.facebook.drawee.view.b a2 = com.facebook.drawee.view.b.a(a.t(), context);
            a2.f().setCallback(this);
            this.b.a(a2);
            i++;
        }
        this.h = new GestureDetector(context, new SimpleOnGestureListener(this) {
            final /* synthetic */ MultiDraweeView a;

            {
                this.a = r1;
            }

            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                if (this.a.i != null) {
                    if (this.a.g.size() == 1) {
                        this.a.i.a(0, this.a.a(0));
                    } else {
                        int a = this.a.a(motionEvent.getX(), motionEvent.getY());
                        this.a.i.a(a, this.a.a(a));
                    }
                }
                return super.onSingleTapConfirmed(motionEvent);
            }

            public boolean onDown(MotionEvent motionEvent) {
                return true;
            }

            public void onLongPress(MotionEvent motionEvent) {
                if (this.a.i != null) {
                    this.a.i.a();
                }
            }
        });
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.h != null) {
            return this.h.onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    protected void onMeasure(int i, int i2) {
        long currentTimeMillis = System.currentTimeMillis();
        int size = MeasureSpec.getSize(i);
        this.f = (MeasureSpec.getSize(i) - (this.e * (this.d - 1))) / this.d;
        int size2 = (this.g.size() % this.d == 0 ? 0 : 1) + (this.g.size() / this.d);
        setMeasuredDimension(size, ((size2 - 1) * this.e) + (this.f * size2));
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
    }

    public void setImageUris(List<String> list) {
        for (int i = 0; i < c; i++) {
            this.b.a(i).f().setCallback(null);
        }
        this.j = 0;
        this.k = 0;
        this.g = list;
        if (list != null && list.size() > 0) {
            c cVar = new c((int) a(getContext(), 100.0f), (int) a(getContext(), 100.0f));
            for (int i2 = 0; i2 < list.size(); i2++) {
                Object obj;
                if (i2 < list.size()) {
                    ImageRequest n = ImageRequestBuilder.a(Uri.parse((String) list.get(i2))).a(cVar).b(false).n();
                    ((com.facebook.drawee.generic.a) this.b.a(i2).e()).b(new ColorDrawable(c.a.d.a.a.a().a((int) R.color.image_placeholder)));
                    obj = n;
                } else {
                    obj = null;
                }
                this.b.a(i2).a(((com.facebook.drawee.a.a.e) ((com.facebook.drawee.a.a.e) com.facebook.drawee.a.a.c.a().b(obj)).a(this.b.a(i2).d())).k());
                this.b.a(i2).f().setCallback(this);
            }
        }
        requestLayout();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b();
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        b();
    }

    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        a();
    }

    void a() {
        this.b.a();
    }

    void b() {
        for (int i = 0; i < c; i++) {
            this.b.a(i).f().setCallback(null);
        }
        this.b.b();
    }

    protected void onDraw(Canvas canvas) {
        if (getMeasuredWidth() != 0 || getMeasuredHeight() != 0) {
            d();
            long currentTimeMillis = System.currentTimeMillis();
            super.onDraw(canvas);
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (i < this.g.size()) {
                Drawable f = this.b.a(i).f();
                if (i % this.d == 0) {
                    i3 = 0;
                }
                if (f != null) {
                    f.setBounds(i3, i2, this.f + i3, this.f + i2);
                    f.draw(canvas);
                }
                i3 += this.f + this.e;
                if (i % this.d != 0 && i % this.d == this.d - 1) {
                    i2 += this.f + this.e;
                }
                i++;
            }
            long currentTimeMillis2 = System.currentTimeMillis();
            this.j++;
            this.k = (int) ((currentTimeMillis2 - currentTimeMillis) + ((long) this.k));
        }
    }

    private Rect a(int i) {
        Rect rect = new Rect();
        if (i >= 0) {
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (i2 < this.g.size()) {
                if (i2 % this.d == 0) {
                    i4 = 0;
                }
                if (this.g.size() == 1) {
                    rect.set(i4, i3, getMeasuredWidth() + i4, getMeasuredHeight() + i3);
                    break;
                } else if (i2 == i) {
                    rect.set(i4, i3, this.f + i4, this.f + i3);
                    break;
                } else {
                    i4 += this.f + this.e;
                    if (i2 % this.d != 0 && i2 % this.d == this.d - 1) {
                        i3 += this.f + this.e;
                    }
                    i2++;
                }
            }
        } else {
            rect.set(0, 0, getMeasuredWidth() + 0, getMeasuredHeight() + 0);
        }
        return rect;
    }

    private int a(float f, float f2) {
        int i = 0;
        if (this.g.size() == 1) {
            return 0;
        }
        int i2;
        int i3 = 0;
        for (i2 = 0; i2 < this.d; i2++) {
            int i4 = (this.f * i2) + (this.e * i2);
            int i5 = this.f + i4;
            if (f >= ((float) i4) && f < ((float) i5)) {
                i3 = i2;
            }
        }
        while (true) {
            i2 = (this.f * i) + (this.e * i);
            i4 = this.f + i2;
            if (f2 >= ((float) i2) && f2 < ((float) i4)) {
                break;
            }
            i++;
        }
        i2 = (this.d * i) + i3;
        if (i2 >= this.g.size()) {
            i2 = -1;
        }
        return i2;
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return this.b.a(drawable) || super.verifyDrawable(drawable);
    }

    public void invalidateDrawable(@NonNull Drawable drawable) {
        int i = 0;
        while (i < this.b.c()) {
            if (drawable == this.b.a(i).f()) {
                break;
            }
            i++;
        }
        i = -1;
        if (i != -1) {
            Rect a = a(i);
            if (a.height() != 0 && a.width() != 0) {
                invalidate(a);
            }
        }
    }

    public float a(Context context, float f) {
        return TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public void d() {
        if (this.b != null && this.b.c() != 0) {
            for (int i = 0; i < this.b.c(); i++) {
                this.b.a(i).f().setColorFilter(new PorterDuffColorFilter(c.a.d.a.a.a().a((int) R.color.image_cover), Mode.SRC_ATOP));
            }
        }
    }
}
