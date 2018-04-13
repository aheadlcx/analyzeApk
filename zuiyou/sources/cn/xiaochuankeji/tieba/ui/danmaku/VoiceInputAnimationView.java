package cn.xiaochuankeji.tieba.ui.danmaku;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.utils.e;

public class VoiceInputAnimationView extends View {
    private Drawable a;
    private Drawable b;
    private Drawable c;
    private Rect d;
    private Rect e;
    private int f;
    private Animation g;

    public VoiceInputAnimationView(Context context) {
        this(context, null);
    }

    public VoiceInputAnimationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VoiceInputAnimationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new Rect();
        this.e = new Rect();
        this.g = new Animation(this) {
            final /* synthetic */ VoiceInputAnimationView a;

            {
                this.a = r1;
            }

            protected void applyTransformation(float f, Transformation transformation) {
                super.applyTransformation(f, transformation);
                this.a.f = (int) (100.0f * f);
                this.a.invalidate();
            }
        };
        this.g.setDuration(2000);
        this.g.setRepeatCount(-1);
        Resources resources = context.getResources();
        this.a = resources.getDrawable(R.drawable.voice_anim_circle);
        this.b = resources.getDrawable(R.drawable.voice_anim_ring);
        this.c = resources.getDrawable(R.drawable.voice_anim_ring);
    }

    public void a() {
        startAnimation(this.g);
    }

    public void b() {
        clearAnimation();
        postInvalidate();
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int intrinsicWidth = (i - this.a.getIntrinsicWidth()) / 2;
        int intrinsicHeight = (i2 - this.a.getIntrinsicHeight()) / 2;
        this.d.set(intrinsicWidth, intrinsicHeight, this.a.getIntrinsicWidth() + intrinsicWidth, this.a.getIntrinsicHeight() + intrinsicHeight);
        intrinsicWidth = (i - this.b.getIntrinsicWidth()) / 2;
        intrinsicHeight = (i2 - this.b.getIntrinsicHeight()) / 2;
        this.e.set(intrinsicWidth, intrinsicHeight, this.b.getIntrinsicWidth() + intrinsicWidth, this.b.getIntrinsicHeight() + intrinsicHeight);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.g.hasEnded()) {
            a(canvas, this.f);
        }
    }

    private void a(Canvas canvas, int i) {
        if (i <= 60) {
            float f = (((float) i) / 35.3f) + 0.7f;
            this.a.setAlpha((int) ((1.0f - (((float) i) / 60.0f)) * 255.0f));
            this.a.setBounds(e.a(this.d, f));
            this.a.draw(canvas);
        }
        if (16 <= i && i <= 70) {
            f = ((float) (i - 16)) / 54.0f;
            this.b.setAlpha((int) ((0.7f - (((float) (i - 16)) / 77.0f)) * 255.0f));
            this.b.setBounds(e.a(this.e, f));
            this.b.draw(canvas);
        }
        if (30 <= i) {
            f = ((float) (i - 30)) / 70.0f;
            this.c.setAlpha((int) ((0.7f - (((float) (i - 30)) / 100.0f)) * 255.0f));
            this.c.setBounds(e.a(this.e, f));
            this.c.draw(canvas);
        }
    }
}
