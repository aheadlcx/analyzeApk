package com.facebook.drawee.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import com.facebook.common.internal.f;
import com.facebook.drawee.d.b;
import com.facebook.drawee.view.a.a;
import javax.annotation.Nullable;

public class c<DH extends b> extends ImageView {
    private final a a = new a();
    private float b = 0.0f;
    private b<DH> c;
    private boolean d = false;

    public c(Context context) {
        super(context);
        a(context);
    }

    public c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public c(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    @TargetApi(21)
    public c(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context);
    }

    private void a(Context context) {
        if (!this.d) {
            this.d = true;
            this.c = b.a(null, context);
            if (VERSION.SDK_INT >= 21) {
                ColorStateList imageTintList = getImageTintList();
                if (imageTintList != null) {
                    setColorFilter(imageTintList.getDefaultColor());
                }
            }
        }
    }

    public void setHierarchy(DH dh) {
        this.c.a((b) dh);
        super.setImageDrawable(this.c.f());
    }

    public DH getHierarchy() {
        return this.c.e();
    }

    @Nullable
    public Drawable getTopLevelDrawable() {
        return this.c.f();
    }

    public void setController(@Nullable com.facebook.drawee.d.a aVar) {
        this.c.a(aVar);
        super.setImageDrawable(this.c.f());
    }

    @Nullable
    public com.facebook.drawee.d.a getController() {
        return this.c.d();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        b();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c();
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        c();
    }

    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        b();
    }

    protected void b() {
        e();
    }

    protected void c() {
        f();
    }

    protected void e() {
        this.c.b();
    }

    protected void f() {
        this.c.c();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.c.a(motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Deprecated
    public void setImageDrawable(Drawable drawable) {
        a(getContext());
        this.c.a(null);
        super.setImageDrawable(drawable);
    }

    @Deprecated
    public void setImageBitmap(Bitmap bitmap) {
        a(getContext());
        this.c.a(null);
        super.setImageBitmap(bitmap);
    }

    @Deprecated
    public void setImageResource(int i) {
        a(getContext());
        this.c.a(null);
        super.setImageResource(i);
    }

    @Deprecated
    public void setImageURI(Uri uri) {
        a(getContext());
        this.c.a(null);
        super.setImageURI(uri);
    }

    public void setAspectRatio(float f) {
        if (f != this.b) {
            this.b = f;
            requestLayout();
        }
    }

    public float getAspectRatio() {
        return this.b;
    }

    protected void onMeasure(int i, int i2) {
        this.a.a = i;
        this.a.b = i2;
        a.a(this.a, this.b, getLayoutParams(), getPaddingLeft() + getPaddingRight(), getPaddingTop() + getPaddingBottom());
        super.onMeasure(this.a.a, this.a.b);
    }

    public String toString() {
        return f.a((Object) this).a("holder", this.c != null ? this.c.toString() : "<no holder set>").toString();
    }
}
