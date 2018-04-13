package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

class a extends ImageView {
    int a;
    private AnimationListener b;

    private class a extends OvalShape {
        final /* synthetic */ a a;
        private RadialGradient b;
        private Paint c = new Paint();

        a(a aVar, int i) {
            this.a = aVar;
            aVar.a = i;
            a((int) rect().width());
        }

        protected void onResize(float f, float f2) {
            super.onResize(f, f2);
            a((int) f);
        }

        public void draw(Canvas canvas, Paint paint) {
            int width = this.a.getWidth();
            int height = this.a.getHeight();
            canvas.drawCircle((float) (width / 2), (float) (height / 2), (float) (width / 2), this.c);
            canvas.drawCircle((float) (width / 2), (float) (height / 2), (float) ((width / 2) - this.a.a), paint);
        }

        private void a(int i) {
            this.b = new RadialGradient((float) (i / 2), (float) (i / 2), (float) this.a.a, new int[]{1023410176, 0}, null, TileMode.CLAMP);
            this.c.setShader(this.b);
        }
    }

    a(Context context, int i) {
        Drawable shapeDrawable;
        super(context);
        float f = getContext().getResources().getDisplayMetrics().density;
        int i2 = (int) (1.75f * f);
        int i3 = (int) (0.0f * f);
        this.a = (int) (3.5f * f);
        if (a()) {
            shapeDrawable = new ShapeDrawable(new OvalShape());
            ViewCompat.setElevation(this, f * 4.0f);
        } else {
            shapeDrawable = new ShapeDrawable(new a(this, this.a));
            setLayerType(1, shapeDrawable.getPaint());
            shapeDrawable.getPaint().setShadowLayer((float) this.a, (float) i3, (float) i2, 503316480);
            int i4 = this.a;
            setPadding(i4, i4, i4, i4);
        }
        shapeDrawable.getPaint().setColor(i);
        ViewCompat.setBackground(this, shapeDrawable);
    }

    private boolean a() {
        return VERSION.SDK_INT >= 21;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (!a()) {
            setMeasuredDimension(getMeasuredWidth() + (this.a * 2), getMeasuredHeight() + (this.a * 2));
        }
    }

    public void setAnimationListener(AnimationListener animationListener) {
        this.b = animationListener;
    }

    public void onAnimationStart() {
        super.onAnimationStart();
        if (this.b != null) {
            this.b.onAnimationStart(getAnimation());
        }
    }

    public void onAnimationEnd() {
        super.onAnimationEnd();
        if (this.b != null) {
            this.b.onAnimationEnd(getAnimation());
        }
    }

    public void setBackgroundColorRes(int i) {
        setBackgroundColor(ContextCompat.getColor(getContext(), i));
    }

    public void setBackgroundColor(int i) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(i);
        }
    }
}
