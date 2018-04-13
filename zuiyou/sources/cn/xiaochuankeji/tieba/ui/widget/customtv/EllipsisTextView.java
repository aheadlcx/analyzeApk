package cn.xiaochuankeji.tieba.ui.widget.customtv;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.Layout.Alignment;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;

public class EllipsisTextView extends AppCompatTextView {
    private String a;
    private String b;
    private int c;
    private SpannableString d;
    private SpannableString e;

    private class a extends ClickableSpan {
        final /* synthetic */ EllipsisTextView a;

        private a(EllipsisTextView ellipsisTextView) {
            this.a = ellipsisTextView;
        }

        public void onClick(View view) {
        }

        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            int currentTextColor = this.a.getCurrentTextColor();
            textPaint.setUnderlineText(false);
            textPaint.setColor(currentTextColor);
        }
    }

    private class b extends ClickableSpan {
        final /* synthetic */ EllipsisTextView a;

        private b(EllipsisTextView ellipsisTextView) {
            this.a = ellipsisTextView;
        }

        public void onClick(View view) {
        }

        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setUnderlineText(false);
            textPaint.setColor(Color.parseColor("#999999"));
        }
    }

    public EllipsisTextView(Context context) {
        this(context, null);
    }

    public EllipsisTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = 4;
        this.b = " … ";
    }

    public void setTextMaxLine(int i) {
        this.c = i;
    }

    @SuppressLint({"DrawAllocation"})
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.a != null) {
            Pair a = a(this.a, (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), this.c, this.b);
            this.e = new SpannableString(this.a);
            a();
            if (((Boolean) a.first).booleanValue()) {
                this.d = new SpannableString((String) a.second);
                b();
                setText(this.d);
            } else {
                setText(this.e);
            }
            this.a = null;
        }
    }

    public Pair<Boolean, String> a(String str, int i, int i2, String str2) {
        StaticLayout staticLayout;
        TextPaint paint = getPaint();
        if (VERSION.SDK_INT >= 16) {
            staticLayout = new StaticLayout(str, paint, i, Alignment.ALIGN_NORMAL, getLineSpacingMultiplier(), getLineSpacingExtra(), false);
        } else {
            staticLayout = new StaticLayout(str, paint, i, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        }
        if (staticLayout.getLineCount() <= i2) {
            return new Pair(Boolean.FALSE, str);
        }
        int offsetForHorizontal;
        int i3 = i2 - 1;
        float measureText = paint.measureText(str2);
        int lineEnd = staticLayout.getLineEnd(i3) - 1;
        if (staticLayout.getLineWidth(i3) + measureText > ((float) i)) {
            offsetForHorizontal = staticLayout.getOffsetForHorizontal(i3, ((float) i) - measureText);
        } else {
            offsetForHorizontal = lineEnd;
        }
        return new Pair(Boolean.TRUE, str.substring(0, offsetForHorizontal) + str2);
    }

    private void a() {
        this.e.setSpan(new a(), 0, this.e.length(), 33);
    }

    private void b() {
        this.d.setSpan(new b(), Math.max(0, this.d.length() - this.b.length()), this.d.length(), 33);
    }

    public void setText(String str) {
        this.a = str;
        requestLayout();
    }

    public void setOnClickListener(@Nullable OnClickListener onClickListener) {
        super.setOnClickListener(onClickListener);
    }
}
