package cn.xiaochuankeji.tieba.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import c.a.i.j;
import cn.xiaochuankeji.tieba.ui.utils.e;

public class ShadowLinearLayout extends j {
    private int a;

    public ShadowLinearLayout(@NonNull Context context) {
        this(context, null);
    }

    public ShadowLinearLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShadowLinearLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setLayerType(1, null);
        this.a = e.a(6.0f);
        setBackground(new j(this.a));
        setPadding(this.a, this.a, this.a, this.a);
        setOrientation(1);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void d() {
        super.d();
        setBackground(new j(this.a));
    }
}
