package cn.xiaochuankeji.tieba.widget.rich;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.AttributeSet;
import c.a.i.w;

public class LineSpaceExtraCompatTextView extends w implements b {
    private static final String a = LineSpaceExtraCompatTextView.class.getSimpleName();
    private Rect b;

    public LineSpaceExtraCompatTextView(Context context) {
        this(context, null);
    }

    public LineSpaceExtraCompatTextView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LineSpaceExtraCompatTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new Rect();
    }

    public int getSpaceExtra() {
        return a();
    }

    public int a() {
        int lineCount = getLineCount() - 1;
        if (lineCount < 0) {
            return 0;
        }
        Layout layout = getLayout();
        lineCount = getLineBounds(lineCount, this.b);
        if (getMeasuredHeight() == getLayout().getHeight()) {
            return this.b.bottom - (lineCount + layout.getPaint().getFontMetricsInt().descent);
        }
        return 0;
    }
}
