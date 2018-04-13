package cn.v6.sixrooms.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import cn.v6.sixrooms.R;

public class UserInfoProgressBar extends RelativeLayout {
    private View a;
    private View b;
    private int c = 100;
    private int d;

    @SuppressLint({"NewApi"})
    public UserInfoProgressBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context);
    }

    public UserInfoProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public UserInfoProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public UserInfoProgressBar(Context context) {
        super(context);
        a(context);
    }

    @SuppressLint({"NewApi"})
    protected void onMeasure(int i, int i2) {
        this.b.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        super.onMeasure(i, this.b.getMeasuredHeightAndState());
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.userinfo_progressbar_layout, this);
        this.a = findViewById(R.id.progress_img);
        this.b = findViewById(R.id.progress_bg);
    }

    public void setProgress(int i) {
        this.d = i;
        a();
    }

    private void a() {
        LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.a.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        }
        layoutParams.width = (getMeasuredWidth() * this.d) / this.c;
        this.a.setLayoutParams(layoutParams);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        a();
    }

    public void setMax(int i) {
        this.c = i;
    }

    public void setProgressBackground(int i) {
        this.b.setBackgroundResource(i);
    }

    public void setProgressBackgroundColor(int i) {
        this.b.setBackgroundColor(i);
    }

    public void setProgressImage(int i) {
        this.a.setBackgroundResource(i);
    }

    public void setProgressColor(int i) {
        this.a.setBackgroundColor(i);
    }

    public int getMax() {
        return this.c;
    }
}
