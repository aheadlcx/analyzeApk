package cn.v6.sixrooms.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import cn.v6.sixrooms.utils.DensityUtil;

public class CornerLinearLayout extends LinearLayout {
    private float a;
    private float[] b;

    public CornerLinearLayout(Context context) {
        this(context, null, 0);
    }

    public CornerLinearLayout(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CornerLinearLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = (float) DensityUtil.dip2px(5.0f);
        this.b = new float[]{this.a, this.a, this.a, this.a, this.a, this.a, this.a, this.a};
        if (VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
    }

    public void draw(Canvas canvas) {
        Path path = new Path();
        path.addRoundRect(new RectF(0.0f, 0.0f, (float) getWidth(), (float) getHeight()), this.b, Direction.CW);
        canvas.clipPath(path);
        super.draw(canvas);
    }
}
