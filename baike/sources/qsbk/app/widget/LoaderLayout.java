package qsbk.app.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

public class LoaderLayout extends RelativeLayout implements Recyclable {
    private static int a;
    private static int b = -1;
    private ProgressBar c;
    private TextView d;

    public LoaderLayout(Context context) {
        super(context);
        a();
    }

    public LoaderLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public LoaderLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        Resources resources = getResources();
        b = (int) (((float) resources.getDimensionPixelSize(R.dimen.g_txt_middle)) / resources.getDisplayMetrics().scaledDensity);
        a = UIHelper.isNightTheme() ? resources.getColor(R.color.loader_layout_text_color_dark) : resources.getColor(R.color.loader_layout_text_color);
    }

    private void b() {
        setGravity(17);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.ab_progress_bar_size_small);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize);
        layoutParams.addRule(9);
        this.c = new ProgressBar(getContext());
        this.c.setIndeterminate(true);
        this.c.setId(R.id.progressBar);
        this.c.setLayoutParams(layoutParams);
        addView(this.c);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams2.addRule(15);
        layoutParams2.addRule(1, R.id.progressBar);
        layoutParams2.addRule(6, R.id.progressBar);
        layoutParams2.leftMargin = 20;
        this.d = new TextView(getContext());
        this.d.setGravity(16);
        this.d.setMaxLines(2);
        this.d.setTextColor(a);
        this.d.setTextSize((float) b);
        this.d.setText(R.string.loading);
        this.d.setLayoutParams(layoutParams2);
        addView(this.d);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        b();
    }

    public void setLoading(String str) {
        this.d.setText(str);
        this.c.setVisibility(0);
    }

    public void setLoaded(String str) {
        this.d.setText(str);
        this.c.setVisibility(4);
    }

    public void setLoadFailed(String str) {
        this.d.setText(str);
        this.c.setVisibility(0);
    }

    public void setImgAndTextViewGone() {
        this.d.setVisibility(8);
        this.c.setVisibility(8);
    }

    public void recycle() {
        removeAllViews();
        this.d = null;
        this.c = null;
    }
}
