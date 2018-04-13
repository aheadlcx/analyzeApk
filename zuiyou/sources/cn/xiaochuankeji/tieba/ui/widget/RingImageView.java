package cn.xiaochuankeji.tieba.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import c.a.d.a.a;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class RingImageView extends WebImageView {
    private Paint a = new Paint();

    public RingImageView(Context context) {
        super(context);
    }

    public RingImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RingImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onDraw(Canvas canvas) {
        this.a.setAntiAlias(true);
        this.a.setColor(a.a().a((int) R.color.CB));
        setPadding(e.a(2.0f), e.a(2.0f), e.a(2.0f), e.a(2.0f));
        canvas.drawCircle((float) (getWidth() / 2), (float) (getWidth() / 2), (float) (getWidth() / 2), this.a);
        super.onDraw(canvas);
    }
}
