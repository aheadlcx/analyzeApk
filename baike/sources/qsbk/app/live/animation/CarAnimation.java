package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import com.baidu.mobstat.Config;
import java.lang.ref.SoftReference;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveGiftMessage;
import qsbk.app.live.widget.LargeGiftLayout;

public class CarAnimation extends BaseLargeAnimation {
    private int f;
    private int g;
    private int h;
    private int i;
    private Runnable j;
    private Runnable k;
    private SoftReference<Bitmap>[] l;

    public void attach(Context context, LargeGiftLayout largeGiftLayout) {
        super.attach(context, largeGiftLayout);
        this.f = this.d;
        this.g = (int) (((double) this.d) * 0.614d);
    }

    public long getGiftId() {
        return 7;
    }

    protected void a(View view) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(view.getWidth(), view.getHeight());
        layoutParams.topMargin = (this.e / 4) - view.getHeight();
        layoutParams.leftMargin = (-(this.f + view.getWidth())) / 2;
        view.setVisibility(0);
        view.setLayoutParams(layoutParams);
    }

    public void onLoadAnim(LiveGiftMessage liveGiftMessage) {
        Bitmap a = a(R.drawable.live_car_full);
        Bitmap a2 = a(R.drawable.live_car_wheel);
        if (!a(liveGiftMessage, a, a2)) {
            View imageView = new ImageView(this.a);
            imageView.setImageBitmap(a2);
            e(imageView);
            b((ImageView) imageView);
            View imageView2 = new ImageView(this.a);
            imageView2.setImageBitmap(a);
            c(imageView2);
            View imageView3 = new ImageView(this.a);
            imageView3.setImageBitmap(a2);
            d((ImageView) imageView3);
            c((ImageView) imageView3);
            AnimatorSet d = d(imageView2);
            AnimatorSet d2 = d(this.b.mUserInfoLayout);
            d.addListener(new c(this, imageView2));
            AnimatorSet d3 = d(imageView3);
            d3.addListener(new d(this, imageView3));
            AnimatorSet d4 = d(imageView);
            d3.addListener(new e(this, imageView));
            d.start();
            d2.start();
            d3.start();
            d4.start();
        }
    }

    private void b(ImageView imageView) {
        if (!c()) {
            if (this.k == null) {
                this.k = new f(this, imageView);
            }
            a(this.k, 150);
        }
    }

    private void c(ImageView imageView) {
        if (!c()) {
            if (this.j == null) {
                this.j = new g(this, imageView);
            }
            a(this.j, 150);
        }
    }

    private void d(ImageView imageView) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) ((((double) this.g) * 0.32d) * 0.5d), (int) (((double) this.g) * 0.32d));
        layoutParams.topMargin = (this.e / 4) + ((int) (((double) this.g) * 0.48d));
        layoutParams.leftMargin = (-this.f) + ((int) (((double) this.f) * 0.369d));
        h(imageView);
        imageView.setScaleType(ScaleType.FIT_XY);
        imageView.setRotation(-10.15f);
        imageView.setLayoutParams(layoutParams);
        b(imageView);
    }

    private void e(ImageView imageView) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) ((((double) this.g) * 0.32d) * 0.3145d), (int) ((((double) this.g) * 0.32d) * 0.805d));
        layoutParams.topMargin = (this.e / 4) + ((int) (((double) this.g) * 0.306d));
        layoutParams.leftMargin = (-this.f) + ((int) (((double) this.f) * 0.048d));
        h(imageView);
        imageView.setScaleType(ScaleType.FIT_XY);
        imageView.setRotation(-5.45f);
        imageView.setLayoutParams(layoutParams);
        b(imageView);
    }

    private void f(ImageView imageView) {
        h(imageView);
        if (this.l != null && this.h < this.l.length) {
            imageView.setImageBitmap((Bitmap) this.l[this.h].get());
        }
        this.h = (this.h + 1) % 4;
    }

    private void g(ImageView imageView) {
        h(imageView);
        if (this.l != null && this.i < this.l.length) {
            imageView.setImageBitmap((Bitmap) this.l[this.i].get());
        }
        this.i = (this.i + 1) % 4;
    }

    private void h(ImageView imageView) {
        if (!c()) {
            Bitmap bitmap;
            BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
            if (bitmapDrawable != null) {
                bitmap = bitmapDrawable.getBitmap();
            } else {
                bitmap = null;
            }
            if (bitmap != null && !bitmap.isRecycled()) {
                Matrix matrix;
                if (this.l == null || this.l.length == 0) {
                    matrix = new Matrix();
                    this.l = new SoftReference[4];
                    for (int i = 1; i <= 4; i++) {
                        matrix.setRotate((float) (i * 90));
                        this.l[i - 1] = new SoftReference(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true));
                    }
                }
                if (this.l[this.h] == null || this.l[this.h].get() == null || ((Bitmap) this.l[this.h].get()).isRecycled()) {
                    matrix = new Matrix();
                    matrix.setRotate((float) ((this.h + 1) * 90));
                    this.l[this.h] = new SoftReference(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true));
                }
                if (this.l[this.i] == null || this.l[this.i].get() == null || ((Bitmap) this.l[this.i].get()).isRecycled()) {
                    matrix = new Matrix();
                    matrix.setRotate((float) ((this.i + 1) * 90));
                    this.l[this.i] = new SoftReference(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true));
                }
            }
        }
    }

    private void c(View view) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.f, this.g);
        layoutParams.topMargin = this.e / 4;
        layoutParams.leftMargin = -this.f;
        ((ImageView) view).setScaleType(ScaleType.FIT_XY);
        view.setLayoutParams(layoutParams);
        b(view);
    }

    private AnimatorSet d(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet animatorSet2 = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{0.0f, (float) (((this.d + this.f) * 1) / 2)});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{0.0f, (float) (((this.e / 8) * 1) / 2)});
        animatorSet2.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet2.setDuration(1000);
        AnimatorSet animatorSet3 = new AnimatorSet();
        ofFloat2 = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{(float) (((this.d + this.f) * 1) / 2), (float) (((this.d + this.f) * 5) / 8)});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) (((this.e / 8) * 1) / 2), (float) (((this.e / 8) * 5) / 8)});
        animatorSet3.playTogether(new Animator[]{ofFloat2, ofFloat3});
        animatorSet3.setDuration(Config.BPLUS_DELAY_TIME);
        AnimatorSet animatorSet4 = new AnimatorSet();
        ofFloat3 = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{(float) (((this.d + this.f) * 5) / 8), (float) (this.d + this.f)});
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) (((this.e / 8) * 5) / 8), (float) (this.e / 8)});
        animatorSet4.playTogether(new Animator[]{ofFloat3, ofFloat4});
        animatorSet4.setDuration(1000);
        animatorSet.playSequentially(new Animator[]{animatorSet2, animatorSet3, animatorSet4});
        animatorSet.setTarget(view);
        return animatorSet;
    }

    public void releaseResource() {
        this.b.mHandler.removeCallbacksAndMessages(null);
        if (this.l != null && this.l.length > 0) {
            for (SoftReference softReference : this.l) {
                if (softReference != null) {
                    Bitmap bitmap = (Bitmap) softReference.get();
                    if (!(bitmap == null || bitmap.isRecycled())) {
                        bitmap.recycle();
                    }
                }
            }
            this.l = null;
        }
    }
}
