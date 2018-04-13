package qsbk.app.core.widget.refresh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatImageView;
import android.view.animation.Animation.AnimationListener;

class a extends AppCompatImageView {
    private AnimationListener a;
    private int b;

    private class a extends OvalShape {
        final /* synthetic */ a a;
        private RadialGradient b;
        private int c;
        private Paint d = new Paint();
        private int e;

        public a(a aVar, int i, int i2) {
            this.a = aVar;
            this.c = i;
            this.e = i2;
            this.b = new RadialGradient((float) (this.e / 2), (float) (this.e / 2), (float) this.c, new int[]{1023410176, 0}, null, TileMode.CLAMP);
            this.d.setShader(this.b);
        }

        public void draw(Canvas canvas, Paint paint) {
            int width = this.a.getWidth();
            int height = this.a.getHeight();
            canvas.drawCircle((float) (width / 2), (float) (height / 2), (float) ((this.e / 2) + this.c), this.d);
            canvas.drawCircle((float) (width / 2), (float) (height / 2), (float) (this.e / 2), paint);
        }
    }

    public a(Context context, int i, float f) {
        Drawable shapeDrawable;
        super(context);
        float f2 = getContext().getResources().getDisplayMetrics().density;
        int i2 = (int) ((f * f2) * 2.0f);
        int i3 = (int) (1.75f * f2);
        int i4 = (int) (0.0f * f2);
        this.b = (int) (3.5f * f2);
        if (a()) {
            shapeDrawable = new ShapeDrawable(new OvalShape());
            ViewCompat.setElevation(this, f2 * 4.0f);
        } else {
            shapeDrawable = new ShapeDrawable(new a(this, this.b, i2));
            ViewCompat.setLayerType(this, 1, shapeDrawable.getPaint());
            shapeDrawable.getPaint().setShadowLayer((float) this.b, (float) i4, (float) i3, 503316480);
            int i5 = this.b;
            setPadding(i5, i5, i5, i5);
        }
        shapeDrawable.getPaint().setColor(i);
        setBackgroundDrawable(shapeDrawable);
    }

    private boolean a() {
        return VERSION.SDK_INT >= 21;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (!a()) {
            setMeasuredDimension(getMeasuredWidth() + (this.b * 2), getMeasuredHeight() + (this.b * 2));
        }
    }

    public void setAnimationListener(AnimationListener animationListener) {
        this.a = animationListener;
    }

    public void onAnimationStart() {
        super.onAnimationStart();
        if (this.a != null) {
            this.a.onAnimationStart(getAnimation());
        }
    }

    public void onAnimationEnd() {
        super.onAnimationEnd();
        if (this.a != null) {
            this.a.onAnimationEnd(getAnimation());
        }
    }

    public void setBackgroundColor(int i) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(getResources().getColor(i));
        }
    }
}
