package qsbk.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import com.facebook.drawee.view.SimpleDraweeView;
import qsbk.app.R;

public class RatioImageView extends SimpleDraweeView {
    private float a;

    public RatioImageView(Context context) {
        super(context);
        a(null, 0);
    }

    public RatioImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, 0);
    }

    public RatioImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet, i);
    }

    private void a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.RatioImageView, i, 0);
        this.a = obtainStyledAttributes.getFloat(0, 0.5625f);
        obtainStyledAttributes.recycle();
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        super.onMeasure(MeasureSpec.makeMeasureSpec(size, 1073741824), MeasureSpec.makeMeasureSpec((int) ((((float) size) * this.a) + 0.5f), 1073741824));
    }
}
