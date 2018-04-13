package qsbk.app.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import qsbk.app.R;

public class UpgradeView extends ViewGroup {
    private Drawable a;
    private ImageView b;
    private TextView c;

    public UpgradeView(Context context) {
        super(context);
        a();
    }

    public UpgradeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public UpgradeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public void setLevel(int i) {
        this.c.setText("LV" + i);
        requestLayout();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int width = getWidth();
        int intrinsicHeight = (this.a.getIntrinsicHeight() * width) / this.a.getIntrinsicWidth();
        int height = getHeight() - intrinsicHeight;
        this.b.layout(0, height, width, getHeight());
        this.c.setTextSize(0, (float) (width / 16));
        this.c.measure(0, 0);
        int measuredWidth = this.c.getMeasuredWidth();
        intrinsicHeight = (intrinsicHeight / 13) + height;
        width = ((width * 15) / 31) - (measuredWidth / 2);
        this.c.layout(width, intrinsicHeight, measuredWidth + width, this.c.getMeasuredHeight() + intrinsicHeight);
    }

    private void a() {
        this.b = new ImageView(getContext());
        this.c = new TextView(getContext());
        this.a = getResources().getDrawable(R.drawable.img_circle_upgrade);
        this.b.setImageDrawable(this.a);
        this.b.setScaleType(ScaleType.FIT_XY);
        this.c.setTextColor(-3840);
        addView(this.b);
        addView(this.c);
    }
}
