package cn.xiaochuankeji.tieba.ui.widget.indicator;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.v7.widget.AppCompatTextView;
import android.widget.FrameLayout;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.text.BadgeTextView;

public class e extends FrameLayout implements g {
    private AppCompatTextView a;
    private BadgeTextView b;
    @ColorInt
    private int c;
    @ColorInt
    private int d;

    public e(Context context) {
        super(context, null);
        a(context);
    }

    private void a(Context context) {
        inflate(context, R.layout.view_recommend_indicator_title, this);
        this.a = (AppCompatTextView) findViewById(R.id.title);
        this.b = (BadgeTextView) findViewById(R.id.crumb);
    }

    public void a(int i, int i2) {
        if (this.a != null) {
            this.a.setTextColor(this.c);
            this.a.getPaint().setFakeBoldText(false);
        }
    }

    public void b(int i, int i2) {
        if (this.a != null) {
            this.a.setTextColor(this.d);
            this.a.getPaint().setFakeBoldText(false);
        }
    }

    public void a(int i, int i2, float f, boolean z) {
    }

    public void b(int i, int i2, float f, boolean z) {
    }

    public int getContentLeft() {
        return getLeft();
    }

    public int getContentTop() {
        return getTop();
    }

    public int getContentRight() {
        return getRight();
    }

    public int getContentBottom() {
        return getBottom();
    }

    public int getSelectedColor() {
        return this.c;
    }

    public void setSelectedColor(int i) {
        this.c = i;
    }

    public int getNormalColor() {
        return this.d;
    }

    public void setNormalColor(int i) {
        this.d = i;
    }

    public void setText(String str) {
        if (this.a != null) {
            this.a.setText(str);
        }
    }

    public void setCrumbCount(int i) {
        if (this.b == null) {
            return;
        }
        if (i == -1) {
            this.b.setVisibility(8);
        } else if (i >= 0) {
            this.b.setVisibility(0);
            this.b.a();
        }
    }
}
