package cn.v6.sixrooms.ui.view.indicator;

import android.content.Context;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.text.TextUtils.TruncateAt;
import android.widget.TextView;
import cn.v6.sixrooms.utils.DensityUtil;

public class SimplePagerTitleView extends TextView implements IMeasurablePagerTitleView {
    protected int mNormalBg;
    protected int mSelectedBg;
    protected int mTextNormalColor;
    protected int mTextSelectedColor;

    public SimplePagerTitleView(Context context) {
        super(context, null);
        setGravity(17);
        int dip2px = DensityUtil.dip2px(13.0f);
        setPadding(dip2px, 0, dip2px, 0);
        setSingleLine();
        setEllipsize(TruncateAt.END);
    }

    public void onSelected(int i, int i2) {
        setTextColor(this.mTextSelectedColor);
        setBackgroundResource(this.mSelectedBg);
    }

    public void onDeselected(int i, int i2) {
        setTextColor(this.mTextNormalColor);
        setBackgroundResource(this.mNormalBg);
    }

    public void onLeave(int i, int i2, float f, boolean z) {
    }

    public void onEnter(int i, int i2, float f, boolean z) {
    }

    public int getContentLeft() {
        Rect rect = new Rect();
        getPaint().getTextBounds(getText().toString(), 0, getText().length(), rect);
        return (getLeft() + (getWidth() / 2)) - (rect.width() / 2);
    }

    public int getContentTop() {
        FontMetrics fontMetrics = getPaint().getFontMetrics();
        return (int) (((float) (getHeight() / 2)) - ((fontMetrics.bottom - fontMetrics.top) / 2.0f));
    }

    public int getContentRight() {
        Rect rect = new Rect();
        getPaint().getTextBounds(getText().toString(), 0, getText().length(), rect);
        return (rect.width() / 2) + (getLeft() + (getWidth() / 2));
    }

    public int getContentBottom() {
        FontMetrics fontMetrics = getPaint().getFontMetrics();
        return (int) (((fontMetrics.bottom - fontMetrics.top) / 2.0f) + ((float) (getHeight() / 2)));
    }

    public void setNormalBackground(int i) {
        this.mNormalBg = i;
    }

    public void setSelectedBackground(int i) {
        this.mSelectedBg = i;
    }

    public void setTextSelectedColor(int i) {
        this.mTextSelectedColor = i;
    }

    public void setTextNormalColor(int i) {
        this.mTextNormalColor = i;
    }
}
