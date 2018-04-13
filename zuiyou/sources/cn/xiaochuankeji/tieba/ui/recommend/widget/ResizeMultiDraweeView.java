package cn.xiaochuankeji.tieba.ui.recommend.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
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
import cn.xiaochuankeji.tieba.background.beans.GrayConfigBean;
import cn.xiaochuankeji.tieba.json.recommend.ServerImageBean;
import com.facebook.drawee.drawable.n$b;
import com.facebook.drawee.generic.b;
import com.facebook.drawee.view.e;
import com.facebook.imagepipeline.common.c;
import com.facebook.imagepipeline.g.f;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.util.ArrayList;
import java.util.List;

public class ResizeMultiDraweeView extends View implements u {
    public static final String a = MultiDraweeView.class.getSimpleName();
    private static int c = 9;
    private static float d = 1.78f;
    e<com.facebook.drawee.generic.a> b;
    private int e = 3;
    private int f;
    private int g;
    private List<ServerImageBean> h = new ArrayList();
    private GestureDetector i;
    private a j;
    private int k;
    private int l;

    public interface a {
        void a();

        void a(int i, Rect rect);
    }

    public ResizeMultiDraweeView(Context context) {
        super(context);
        a(context);
    }

    public ResizeMultiDraweeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public ResizeMultiDraweeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public void setOnItemClickListener(a aVar) {
        this.j = aVar;
    }

    private void a(Context context) {
        int i = 0;
        this.f = (int) a(context, 4.4f);
        b a = new b(getResources()).e(n$b.g).a(0).a(new ColorDrawable(c.a.d.a.a.a().a((int) R.color.image_placeholder)));
        this.b = new e();
        while (i < c) {
            com.facebook.drawee.view.b a2 = com.facebook.drawee.view.b.a(a.t(), context);
            a2.f().setCallback(this);
            this.b.a(a2);
            i++;
        }
        this.i = new GestureDetector(context, new SimpleOnGestureListener(this) {
            final /* synthetic */ ResizeMultiDraweeView a;

            {
                this.a = r1;
            }

            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                if (this.a.j == null) {
                    return super.onSingleTapConfirmed(motionEvent);
                }
                if (this.a.h.size() == 1) {
                    this.a.j.a(0, this.a.a(0));
                    return true;
                }
                int a = this.a.a(motionEvent.getX(), motionEvent.getY());
                this.a.j.a(a, this.a.a(a));
                return true;
            }

            public boolean onDown(MotionEvent motionEvent) {
                return this.a.a(motionEvent.getX(), motionEvent.getY()) >= 0;
            }

            public void onLongPress(MotionEvent motionEvent) {
                if (this.a.j != null) {
                    this.a.j.a();
                }
            }
        });
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.i != null) {
            return this.i.onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    protected void onMeasure(int i, int i2) {
        long currentTimeMillis = System.currentTimeMillis();
        int size = MeasureSpec.getSize(i);
        int i3 = 0;
        int size2 = this.h.size();
        if (size2 == 1) {
            i3 = (int) (((((float) size) * 1.0f) / d) + 0.5f);
        } else if (size2 == 2) {
            i3 = (size - this.f) / 2;
            this.g = i3;
        } else if (size2 == 3) {
            i3 = (size - (this.f * 2)) / 3;
            this.g = i3;
        } else if (size2 == 4) {
            this.g = (size - this.f) / 2;
            i3 = size;
        } else if (size2 <= 6) {
            i3 = (((size - (this.f * 2)) / 3) * 2) + this.f;
            this.g = (size - (this.f * 2)) / 3;
        } else if (size2 >= 7) {
            this.g = (size - (this.f * 2)) / 3;
            i3 = size;
        }
        setMeasuredDimension(size, i3);
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
    }

    public void setImageUris(List<ServerImageBean> list) {
        int size = list.size();
        if (size <= 3) {
            this.e = size;
        } else if (size == 4) {
            this.e = 2;
        } else {
            this.e = 3;
        }
        for (size = 0; size < c; size++) {
            this.b.a(size).f().setCallback(null);
        }
        this.k = 0;
        this.l = 0;
        if (list.size() > 9) {
            list = list.subList(0, 8);
        }
        this.h = list;
        if (!this.h.isEmpty()) {
            c cVar = new c((int) a(getContext(), 100.0f), (int) a(getContext(), 100.0f));
            int i = 0;
            while (true) {
                if (i >= (this.h.size() < c ? this.h.size() : c)) {
                    break;
                }
                Object obj = null;
                if (i < this.h.size()) {
                    cn.xiaochuankeji.tieba.ui.widget.image.a a;
                    boolean z;
                    int i2;
                    ServerImageBean serverImageBean = (ServerImageBean) this.h.get(i);
                    GrayConfigBean C = cn.xiaochuankeji.tieba.background.utils.c.a.c().C();
                    if (C == null || C.newPostThumbSize != 1) {
                        a = cn.xiaochuankeji.tieba.background.f.b.a(serverImageBean.id);
                    } else {
                        boolean z2;
                        long j = serverImageBean.id;
                        if (this.h.size() == 1) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        a = cn.xiaochuankeji.tieba.background.f.b.b(j, z2);
                    }
                    ImageRequest n = ImageRequestBuilder.a(Uri.parse(a.b())).a(cVar).b(false).n();
                    com.facebook.drawee.generic.a aVar = (com.facebook.drawee.generic.a) this.b.a(i).e();
                    aVar.b(new ColorDrawable(c.a.d.a.a.a().a((int) R.color.image_placeholder)));
                    if (((double) ((float) (serverImageBean.height / serverImageBean.width))) > 2.5d) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        i2 = 1;
                    } else if (ServerImageBean.asGif(serverImageBean)) {
                        i2 = 2;
                    } else if (!ServerImageBean.asVideo(serverImageBean)) {
                        i2 = 0;
                    } else if (this.h.size() == 1) {
                        i2 = 3;
                    } else {
                        i2 = 4;
                    }
                    Drawable aVar2 = new a(i2);
                    if (i2 >= 3 && serverImageBean.videoBean != null) {
                        aVar2.a((long) serverImageBean.videoBean.videoDur);
                        if (this.h.size() == 1) {
                            aVar2.b(serverImageBean.danmuCount);
                            aVar2.a(serverImageBean.videoBean.playCount);
                        }
                    }
                    aVar.d(aVar2);
                    obj = n;
                }
                this.b.a(i).a(((com.facebook.drawee.a.a.e) ((com.facebook.drawee.a.a.e) ((com.facebook.drawee.a.a.e) ((com.facebook.drawee.a.a.e) com.facebook.drawee.a.a.c.a().b(obj)).a(new com.facebook.drawee.controller.b<f>(this) {
                    final /* synthetic */ ResizeMultiDraweeView a;

                    {
                        this.a = r1;
                    }

                    public void a(String str, f fVar, Animatable animatable) {
                    }

                    public void a(String str, Throwable th) {
                    }
                })).a(this.b.a(i).d())).a(true)).k());
                this.b.a(i).f().setCallback(this);
                i++;
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
            long currentTimeMillis = System.currentTimeMillis();
            super.onDraw(canvas);
            int min = Math.min(this.h.size(), c);
            if (min == 1) {
                Drawable f = this.b.a(0).f();
                if (f != null) {
                    f.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                    f.draw(canvas);
                }
            } else {
                int i = 0;
                int i2 = 0;
                int i3 = 0;
                while (i < min) {
                    Drawable f2 = this.b.a(i).f();
                    if (i % this.e == 0) {
                        i3 = 0;
                    }
                    if (f2 != null) {
                        f2.setBounds(i3, i2, this.g + i3, this.g + i2);
                        f2.draw(canvas);
                    }
                    int i4 = (this.g + this.f) + i3;
                    if (i % this.e != 0 && i % this.e == this.e - 1) {
                        i2 += this.g + this.f;
                    }
                    i++;
                    i3 = i4;
                }
            }
            long currentTimeMillis2 = System.currentTimeMillis();
            this.k++;
            this.l = (int) ((currentTimeMillis2 - currentTimeMillis) + ((long) this.l));
        }
    }

    private Rect a(int i) {
        Rect rect = new Rect();
        int min = Math.min(this.h.size(), c);
        if (i >= 0) {
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (i2 < min) {
                if (i2 % this.e == 0) {
                    i4 = 0;
                }
                if (this.h.size() == 1) {
                    rect.set(i4, i3, getMeasuredWidth() + i4, getMeasuredHeight() + i3);
                    break;
                } else if (i2 == i) {
                    rect.set(i4, i3, this.g + i4, this.g + i3);
                    break;
                } else {
                    int i5 = (this.g + this.f) + i4;
                    if (i2 % this.e == 0 || i2 % this.e != this.e - 1) {
                        i4 = i3;
                    } else {
                        i4 = (this.g + this.f) + i3;
                    }
                    i2++;
                    i3 = i4;
                    i4 = i5;
                }
            }
        } else {
            rect.set(0, 0, getMeasuredWidth() + 0, getMeasuredHeight() + 0);
        }
        return rect;
    }

    private int a(float f, float f2) {
        int i = 0;
        if (this.h.size() == 1) {
            return 0;
        }
        int i2;
        int i3 = 0;
        for (i2 = 0; i2 < this.e; i2++) {
            int i4 = (this.g * i2) + (this.f * i2);
            int i5 = this.g + i4;
            if (f >= ((float) i4) && f < ((float) i5)) {
                i3 = i2;
            }
        }
        i2 = 0;
        while (((double) i) < Math.ceil((double) ((((float) this.h.size()) * 1.0f) / 3.0f))) {
            i4 = (this.g * i2) + (this.f * i2);
            i5 = this.g + i4;
            if (f2 >= ((float) i4) && f2 < ((float) i5)) {
                break;
            }
            i2++;
            i++;
        }
        i2 = (i2 * this.e) + i3;
        if (i2 >= this.h.size()) {
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
