package qsbk.app.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.util.LruCache;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import qsbk.app.QsbkApp;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UIHelper$Theme;

public class TextLayoutView extends View {
    private static float a = 18.0f;
    private static LruCache<CharSequence, StaticLayout> b = new LruCache(200);
    private int c;
    private CharSequence d;
    private StaticLayout e;

    public TextLayoutView(Context context) {
        super(context);
        b();
    }

    public TextLayoutView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public TextLayoutView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    public static void setTextSize(float f) {
        if (a == 0.0f || a == f) {
            a = f;
            return;
        }
        a = f;
        a();
    }

    private static void a() {
        b.evictAll();
    }

    private void b() {
        if (UIHelper$Theme.THEME_NIGHT.equals(UIHelper.getTheme())) {
            this.c = -9802626;
        } else {
            this.c = -12894910;
        }
    }

    public void setTextColor(int i) {
        this.c = i;
        postInvalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        if (this.e != null) {
            canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            this.e.getPaint().setColor(this.c);
            this.e.draw(canvas);
        }
        canvas.restore();
    }

    public void setText(CharSequence charSequence) {
        this.d = charSequence;
        this.e = null;
        requestLayout();
    }

    protected void onMeasure(int i, int i2) {
        int size = (MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
        if (this.e == null) {
            CharSequence charSequence = TextUtils.isEmpty(this.d) ? "" : this.d;
            StaticLayout staticLayout = (StaticLayout) b.get(charSequence + String.valueOf(size));
            if (staticLayout == null) {
                staticLayout = a(charSequence, size);
            } else {
                LogUtil.d("get static layout in cache");
            }
            this.e = staticLayout;
        }
        setMeasuredDimension(resolveSize((this.e.getWidth() + getPaddingLeft()) + getPaddingRight(), i), resolveSize((this.e.getHeight() + getPaddingTop()) + getPaddingBottom(), i2));
    }

    private StaticLayout a(CharSequence charSequence, int i) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(UIHelper.sp2px(QsbkApp.mContext, a));
        textPaint.setAntiAlias(true);
        StaticLayout staticLayout = new StaticLayout(charSequence, textPaint, Math.min((int) Math.ceil((double) Layout.getDesiredWidth(charSequence, textPaint)), i), Alignment.ALIGN_NORMAL, 0.0f, (float) UIHelper.dip2px(QsbkApp.mContext, 30.0f), false);
        b.put(charSequence + String.valueOf(i), staticLayout);
        return staticLayout;
    }
}
