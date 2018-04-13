package cn.v6.sixrooms.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import cn.v6.sixrooms.utils.DensityUtil;
import com.facebook.drawee.view.SimpleDraweeView;

public class UpRoundImageView extends SimpleDraweeView {
    private float a;
    private Path b;
    private RectF c;
    private float[] d;

    public UpRoundImageView(Context context) {
        this(context, null, 0);
    }

    public UpRoundImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public UpRoundImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = (float) DensityUtil.dip2px(5.0f);
        this.b = new Path();
        this.c = new RectF();
        this.d = new float[]{this.a, this.a, this.a, this.a, 0.0f, 0.0f, 0.0f, 0.0f};
        if (VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
    }

    protected void onDraw(Canvas canvas) {
        if (VERSION.SDK_INT < 21) {
            this.c.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
            this.b.addRoundRect(this.c, this.d, Direction.CW);
            canvas.clipPath(this.b);
        }
        super.onDraw(canvas);
    }
}
