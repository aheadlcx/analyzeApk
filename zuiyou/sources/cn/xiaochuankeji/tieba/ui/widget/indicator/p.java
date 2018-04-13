package cn.xiaochuankeji.tieba.ui.widget.indicator;

import android.content.Context;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils.TruncateAt;

public class p extends AppCompatTextView implements g {
    protected int b;
    protected int c;
    protected Rect d = new Rect();

    public p(Context context) {
        super(context, null);
        a(context);
    }

    private void a(Context context) {
        setGravity(17);
        setSingleLine();
        setEllipsize(TruncateAt.END);
    }

    public void a(int i, int i2) {
        setTextColor(this.b);
    }

    public void b(int i, int i2) {
        setTextColor(this.c);
    }

    public void a(int i, int i2, float f, boolean z) {
    }

    public void b(int i, int i2, float f, boolean z) {
    }

    public int getContentLeft() {
        getPaint().getTextBounds(getText().toString(), 0, getText().length(), this.d);
        return (getLeft() + (getWidth() / 2)) - (this.d.width() / 2);
    }

    public int getContentTop() {
        FontMetrics fontMetrics = getPaint().getFontMetrics();
        return (int) (((float) (getHeight() / 2)) - ((fontMetrics.bottom - fontMetrics.top) / 2.0f));
    }

    public int getContentRight() {
        getPaint().getTextBounds(getText().toString(), 0, getText().length(), this.d);
        return (this.d.width() / 2) + (getLeft() + (getWidth() / 2));
    }

    public int getContentBottom() {
        FontMetrics fontMetrics = getPaint().getFontMetrics();
        return (int) (((fontMetrics.bottom - fontMetrics.top) / 2.0f) + ((float) (getHeight() / 2)));
    }

    public int getSelectedColor() {
        return this.b;
    }

    public void setSelectedColor(int i) {
        this.b = i;
    }

    public int getNormalColor() {
        return this.c;
    }

    public void setNormalColor(int i) {
        this.c = i;
    }
}
