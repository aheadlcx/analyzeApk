package cn.xiaochuankeji.tieba.ui.widget.bigImage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.UiThread;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import c.a.c;
import cn.xiaochuankeji.tieba.R;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BigImageView extends FrameLayout implements cn.xiaochuankeji.tieba.ui.widget.bigImage.a.a {
    public static final ScaleType[] a = new ScaleType[]{ScaleType.CENTER, ScaleType.CENTER_CROP, ScaleType.CENTER_INSIDE, ScaleType.FIT_CENTER, ScaleType.FIT_END, ScaleType.FIT_START, ScaleType.FIT_XY};
    private final a b;
    private final List<File> c;
    private final a d;
    private View e;
    private View f;
    private ImageView g;
    private cn.xiaochuankeji.tieba.ui.widget.bigImage.a.a h;
    private Uri i;
    private Uri j;
    private h k;
    private d l;
    private int m;
    private ScaleType n;
    private boolean o;
    private boolean p;
    private int q;
    private boolean r;
    private final i s;

    private class a extends SubsamplingScaleImageView {
        final /* synthetic */ BigImageView a;
        private GestureDetector b;
        private b c;

        public a(BigImageView bigImageView, Context context, AttributeSet attributeSet) {
            this.a = bigImageView;
            super(context, attributeSet);
            a(context);
        }

        private void a(Context context) {
            this.b = new GestureDetector(context, new SimpleOnGestureListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                    if (this.a.c != null) {
                        this.a.c.a(f, f2);
                    }
                    return false;
                }
            });
        }

        public void a(b bVar) {
            this.c = bVar;
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            this.b.onTouchEvent(motionEvent);
            return super.onTouchEvent(motionEvent);
        }
    }

    public interface b {
        void a(float f, float f2);
    }

    public BigImageView(Context context) {
        this(context, null);
    }

    public BigImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BigImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.q = 0;
        this.r = true;
        this.s = new i(this) {
            final /* synthetic */ BigImageView a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.k != null) {
                    this.a.k.a(this.b);
                    a();
                }
            }
        };
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.BigImageView, i, 0);
        this.m = obtainStyledAttributes.getInteger(0, 1);
        if (obtainStyledAttributes.hasValue(3)) {
            int integer = obtainStyledAttributes.getInteger(4, 3);
            if (integer < 0 || a.length <= integer) {
                throw new IllegalArgumentException("Bad failureImageInitScaleType value: " + integer);
            }
            this.n = a[integer];
            setFailureImage(obtainStyledAttributes.getDrawable(3));
        }
        this.o = obtainStyledAttributes.getBoolean(2, false);
        this.p = obtainStyledAttributes.getBoolean(1, true);
        obtainStyledAttributes.recycle();
        this.d = new a(this, context, attributeSet);
        addView(this.d);
        if (isInEditMode()) {
            this.b = null;
        } else {
            this.b = b.a();
        }
        this.c = new ArrayList();
        this.d.setLayoutParams(new LayoutParams(-1, -1));
        this.d.setMinimumTileDpi(160);
        if (c.e().c()) {
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
            View view = new View(context);
            view.setBackgroundResource(R.color.image_cover_night);
            addView(view, layoutParams);
        }
        setOptimizeDisplay(this.o);
        setInitScaleType(this.m);
    }

    public void setFailureImageInitScaleType(ScaleType scaleType) {
        this.n = scaleType;
    }

    public void setFailureImage(Drawable drawable) {
        if (drawable != null) {
            if (this.g == null) {
                this.g = new ImageView(getContext());
                this.g.setVisibility(8);
                if (this.n != null) {
                    this.g.setScaleType(this.n);
                }
                addView(this.g);
            }
            this.g.setImageDrawable(drawable);
        }
    }

    public void setInitScaleType(int i) {
        this.m = i;
        switch (i) {
            case 2:
                this.d.setMinimumScaleType(2);
                break;
            case 3:
                this.d.setMinimumScaleType(3);
                break;
            case 4:
                this.d.setMinimumScaleType(4);
                break;
            default:
                this.d.setMinimumScaleType(1);
                break;
        }
        if (this.l != null) {
            this.l.a(i);
        }
    }

    public void setOptimizeDisplay(boolean z) {
        this.o = z;
        if (this.o) {
            this.l = new d(this.d);
            this.d.setOnImageEventListener(this.l);
            return;
        }
        this.l = null;
        this.d.setOnImageEventListener(null);
    }

    public void setTapToRetry(boolean z) {
        this.p = z;
    }

    public void setProgressIndicator(h hVar) {
        this.k = hVar;
    }

    public void setImageLoaderCallback(cn.xiaochuankeji.tieba.ui.widget.bigImage.a.a aVar) {
        this.h = aVar;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.b.a(hashCode());
        if (this.r) {
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                ((File) this.c.get(i)).delete();
            }
            this.c.clear();
            if (this.d != null) {
                this.d.a();
            }
        }
    }

    public void a(Uri uri) {
        a(Uri.EMPTY, uri);
    }

    public void a(Uri uri, Uri uri2) {
        this.i = uri;
        this.j = uri2;
        this.b.a(uri2.hashCode(), uri2, (cn.xiaochuankeji.tieba.ui.widget.bigImage.a.a) this);
        com.izuiyou.a.a.b.c("showImage:" + uri2.hashCode() + "  callback:" + hashCode());
        if (this.g != null) {
            this.g.setVisibility(8);
        }
    }

    public void c() {
        this.b.a(hashCode());
    }

    public SubsamplingScaleImageView getSSIV() {
        return this.d;
    }

    @UiThread
    public void a(File file) {
        this.c.add(file);
        c(file);
        if (this.h != null) {
            this.h.a(file);
        }
    }

    @UiThread
    public void a() {
        if (this.i != Uri.EMPTY) {
            this.f = this.b.a(this, this.i, 3);
            addView(this.f);
        }
        if (this.k != null) {
            this.e = this.k.a(this);
            if (this.e != null) {
                addView(this.e);
            }
            this.k.a();
        }
        if (this.h != null) {
            this.h.a();
        }
    }

    @UiThread
    public void a(int i) {
        if (this.k != null && this.s.a(i)) {
            post(this.s);
        }
        if (this.h != null) {
            this.h.a(i);
        }
    }

    @UiThread
    public void b() {
        this.q = 0;
        d();
        if (this.h != null) {
            this.h.b();
        }
    }

    @UiThread
    public void b(File file) {
        if (this.h != null) {
            this.h.b(file);
        }
    }

    @UiThread
    public void a(Throwable th) {
        if (this.q < 3) {
            com.izuiyou.a.a.b.e("bigImage: " + this.j + " load fail and should retry");
            this.q++;
            if (this.j != null) {
                String uri = this.j.toString();
                if (!TextUtils.isEmpty(uri) && uri.contains("/img/webp/id/")) {
                    this.j = Uri.parse(uri.replaceAll("/img/webp/id/", "/img/view/id/"));
                }
                a(this.j);
                if (this.h != null) {
                    this.h.a(th);
                }
            }
        }
    }

    @UiThread
    private void d() {
        if (this.o) {
            Animation animationSet = new AnimationSet(true);
            Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(10);
            alphaAnimation.setFillAfter(true);
            animationSet.addAnimation(alphaAnimation);
            if (this.f != null) {
                this.f.setAnimation(animationSet);
            }
            if (this.k != null) {
                this.k.b();
            }
            if (this.e != null) {
                this.e.setAnimation(animationSet);
            }
            alphaAnimation.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ BigImageView a;

                {
                    this.a = r1;
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    if (this.a.f != null) {
                        this.a.f.setVisibility(8);
                        if (this.a.f.getParent() == this.a) {
                            this.a.removeViewInLayout(this.a.f);
                        }
                    }
                    if (this.a.e != null) {
                        this.a.e.setVisibility(8);
                    }
                }

                public void onAnimationRepeat(Animation animation) {
                }
            });
            return;
        }
        if (this.k != null) {
            this.k.b();
        }
        if (this.f != null) {
            this.f.setVisibility(8);
            if (this.f.getParent() == this) {
                removeViewInLayout(this.f);
            }
        }
        if (this.e != null) {
            this.e.setVisibility(8);
        }
    }

    @UiThread
    private void c(File file) {
        if (file != null && file.exists()) {
            this.d.setImage(com.davemorrissey.labs.subscaleview.a.a(Uri.fromFile(file)));
            if (this.g != null) {
                this.g.setVisibility(8);
            }
            this.d.setVisibility(0);
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.d.setOnClickListener(onClickListener);
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.d.setOnLongClickListener(onLongClickListener);
    }

    public SubsamplingScaleImageView getImageView() {
        return this.d;
    }

    public void setOnGestureScrollListener(b bVar) {
        this.d.a(bVar);
    }

    public void setRecycleWhenDetached(boolean z) {
        this.r = z;
    }
}
