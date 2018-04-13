package cn.xiaochuankeji.tieba.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import cn.xiaochuankeji.tieba.ui.utils.e;

public class ShadowRelativeLayout extends RelativeLayout {
    private int a;

    public ShadowRelativeLayout(@NonNull Context context) {
        this(context, null);
    }

    public ShadowRelativeLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShadowRelativeLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setLayerType(1, null);
        this.a = e.a(6.0f);
        setBackground(new i(this.a));
        setPadding(this.a, this.a, this.a, this.a);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
