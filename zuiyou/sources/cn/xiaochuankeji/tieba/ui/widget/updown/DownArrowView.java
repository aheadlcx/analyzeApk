package cn.xiaochuankeji.tieba.ui.widget.updown;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.AttributeSet;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.utils.e;

public class DownArrowView extends a {
    public DownArrowView(Context context) {
        super(context);
    }

    public DownArrowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DownArrowView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void a(Resources resources) {
        this.a = resources.getDrawable(R.drawable.icon_video_hate);
        this.b = resources.getDrawable(R.drawable.icon_video_hate_hl);
        this.c = resources.getDrawable(R.drawable.bg_round_transparent);
    }

    protected void a(int i, int i2, Rect rect) {
        int intrinsicWidth = (i - this.a.getIntrinsicWidth()) / 2;
        int intrinsicHeight = ((i2 - this.a.getIntrinsicHeight()) / 2) + e.a(0.75f);
        rect.set(intrinsicWidth, intrinsicHeight, this.a.getIntrinsicWidth() + intrinsicWidth, this.a.getIntrinsicHeight() + intrinsicHeight);
    }
}
