package cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.StickerTrace;
import com.sina.weibo.sdk.constant.WBConstants;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class a extends Drawable {
    private float a = 1.0f;
    private boolean b;
    private float c = 1.0f;
    private float d;
    private ValueAnimator e;
    private int f;
    private int g;
    private float h = 1.0f;
    private final long i = System.currentTimeMillis();
    private StickerTrace j;

    protected abstract void a(Canvas canvas);

    public a(Context context) {
    }

    public a(Context context, JSONObject jSONObject) throws JSONException {
        this.a = (float) jSONObject.getDouble("scale_factor");
        this.d = (float) jSONObject.getDouble("rotation");
        setBounds(jSONObject.getInt("bounds_left"), jSONObject.getInt("bounds_top"), jSONObject.getInt("bounds_right"), jSONObject.getInt("bounds_bottom"));
    }

    public void a(JSONObject jSONObject) throws JSONException {
        jSONObject.put(WBConstants.GAME_PARAMS_GAME_CREATE_TIME, this.i);
        jSONObject.put("scale_factor", (double) this.a);
        jSONObject.put("rotation", (double) this.d);
        Rect bounds = getBounds();
        jSONObject.put("bounds_left", bounds.left);
        jSONObject.put("bounds_top", bounds.top);
        jSONObject.put("bounds_right", bounds.right);
        jSONObject.put("bounds_bottom", bounds.bottom);
    }

    public void draw(Canvas canvas) {
        float f;
        Rect copyBounds = copyBounds();
        copyBounds.offset(this.f, this.g);
        canvas.save();
        if (this.d != 0.0f) {
            canvas.rotate(this.d, copyBounds.exactCenterX(), copyBounds.exactCenterY());
        }
        if (this.b) {
            f = this.c;
        } else {
            f = this.a;
        }
        f *= this.h;
        if (f != 1.0f) {
            canvas.scale(f, f, copyBounds.exactCenterX(), copyBounds.exactCenterY());
        }
        canvas.translate((float) copyBounds.left, (float) copyBounds.top);
        a(canvas);
        canvas.restore();
    }

    public long d() {
        return this.i;
    }

    public boolean a() {
        return false;
    }

    public boolean a(long j) {
        return false;
    }

    public void b(Canvas canvas, int i) {
        Rect copyBounds = copyBounds();
        copyBounds.offset(this.f, this.g);
        canvas.save();
        if (this.d != 0.0f) {
            canvas.rotate(this.d, copyBounds.exactCenterX(), copyBounds.exactCenterY());
        }
        if (this.a != 1.0f) {
            canvas.scale(this.a, this.a, copyBounds.exactCenterX(), copyBounds.exactCenterY());
        }
        canvas.translate((float) copyBounds.left, (float) copyBounds.top);
        a(canvas, i);
        canvas.restore();
    }

    protected void a(Canvas canvas, int i) {
        a(canvas);
    }

    public void a(int i, int i2) {
        getBounds().offset(i, i2);
        invalidateSelf();
    }

    public void a(float f) {
        if (this.c != f) {
            this.c = f;
            invalidateSelf();
        }
    }

    public float e() {
        return this.b ? this.c : this.a;
    }

    public void b(float f) {
        this.d = f;
    }

    public float f() {
        return this.d;
    }

    protected boolean c() {
        return false;
    }

    public void g() {
        this.b = true;
        this.c = this.a;
    }

    public void h() {
        if (c()) {
            Rect copyBounds = copyBounds();
            copyBounds.inset((int) ((((float) copyBounds.width()) - (((float) copyBounds.width()) * this.c)) / 2.0f), (int) ((((float) copyBounds.height()) - (((float) copyBounds.height()) * this.c)) / 2.0f));
            setBounds(copyBounds);
            this.a = 1.0f;
        } else {
            this.a = this.c;
        }
        this.c = 1.0f;
        this.b = false;
        invalidateSelf();
    }

    public void a(int i, int i2, float f) {
        PropertyValuesHolder ofInt = PropertyValuesHolder.ofInt("transitionDx", new int[]{0, i});
        PropertyValuesHolder ofInt2 = PropertyValuesHolder.ofInt("transitionDy", new int[]{0, i2});
        PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("transitionScale", new float[]{1.0f, f});
        this.e = ValueAnimator.ofPropertyValuesHolder(new PropertyValuesHolder[]{ofInt, ofInt2, ofFloat});
        this.e.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.a.f = ((Integer) valueAnimator.getAnimatedValue("transitionDx")).intValue();
                this.a.g = ((Integer) valueAnimator.getAnimatedValue("transitionDy")).intValue();
                this.a.h = ((Float) valueAnimator.getAnimatedValue("transitionScale")).floatValue();
                this.a.invalidateSelf();
            }
        });
        this.e.setDuration(300);
        this.e.start();
    }

    public void i() {
        if (this.e != null) {
            this.e.reverse();
        }
    }

    @CallSuper
    public void a(Callback callback) {
        setCallback(callback);
    }

    @CallSuper
    public void b() {
    }

    public void a(StickerTrace stickerTrace) {
        this.j = stickerTrace;
    }

    @Nullable
    public StickerTrace j() {
        return this.j;
    }
}
