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
import android.util.Pair;
import android.view.View;
import android.view.View.MeasureSpec;
import qsbk.app.QsbkApp;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UIHelper$Theme;

public class ListTextLayoutView extends View {
    private static float a = 18.0f;
    private static LruCache<CharSequence, Pair<Boolean, StaticLayout>> b = new LruCache(200);
    public static int readMorePaddingTop = -1;
    private int c;
    private boolean d;
    private CharSequence e;
    private StaticLayout f;

    public ListTextLayoutView(Context context) {
        super(context);
        b();
    }

    public ListTextLayoutView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public ListTextLayoutView(Context context, AttributeSet attributeSet, int i) {
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

    public boolean isEllipse() {
        return this.d;
    }

    public int getReadMorePaddingTop() {
        return readMorePaddingTop;
    }

    private void b() {
        if (UIHelper$Theme.THEME_NIGHT.equals(UIHelper.getTheme())) {
            this.c = -9802626;
        } else {
            this.c = -12894910;
        }
    }

    public String getText() {
        if (this.f == null) {
            return null;
        }
        CharSequence charSequence = this.f.getText().toString();
        if (TextUtils.isEmpty(charSequence)) {
            return null;
        }
        return charSequence;
    }

    public void setText(CharSequence charSequence) {
        this.e = charSequence;
        this.f = null;
        requestLayout();
    }

    public void setTextColor(int i) {
        this.c = i;
        postInvalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        if (this.f != null) {
            canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            this.f.getPaint().setColor(this.c);
            this.f.draw(canvas);
        }
        canvas.restore();
    }

    protected void onMeasure(int i, int i2) {
        int size = (MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
        if (this.f == null) {
            StaticLayout a;
            CharSequence charSequence = TextUtils.isEmpty(this.e) ? "" : this.e;
            Pair pair = (Pair) b.get(charSequence + String.valueOf(size));
            if (pair == null) {
                a = a(charSequence, size);
            } else {
                LogUtil.d("get static layout in cache");
                this.d = ((Boolean) pair.first).booleanValue();
                a = (StaticLayout) pair.second;
            }
            this.f = a;
        }
        setMeasuredDimension(resolveSize((this.f.getWidth() + getPaddingLeft()) + getPaddingRight(), i), resolveSize((this.f.getHeight() + getPaddingTop()) + getPaddingBottom(), i2));
    }

    private StaticLayout a(CharSequence charSequence, int i) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(UIHelper.sp2px(QsbkApp.mContext, a));
        textPaint.setAntiAlias(true);
        int min = Math.min((int) Math.ceil((double) Layout.getDesiredWidth(charSequence, textPaint)), i);
        StaticLayout staticLayout = new StaticLayout(charSequence, textPaint, min, Alignment.ALIGN_NORMAL, 0.0f, (float) UIHelper.dip2px(QsbkApp.mContext, 30.0f), false);
        if (staticLayout.getLineCount() > 10) {
            staticLayout = new StaticLayout(charSequence.subSequence(0, staticLayout.getLineStart(10) - 1), textPaint, min, Alignment.ALIGN_NORMAL, 0.0f, (float) UIHelper.dip2px(QsbkApp.mContext, 30.0f), false);
            this.d = true;
            if (readMorePaddingTop == -1) {
                readMorePaddingTop = staticLayout.getLineTop(9);
            }
        } else {
            this.d = false;
        }
        b.put(charSequence + String.valueOf(i), new Pair(Boolean.valueOf(this.d), staticLayout));
        return staticLayout;
    }
}
