package cn.xiaochuankeji.tieba.ui.mediabrowse.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import cn.xiaochuankeji.tieba.ui.utils.e;

public class ImgDownloadFinishAnimView extends View {
    private Paint a;
    private int b;
    private int c;
    private long d;

    public ImgDownloadFinishAnimView(Context context) {
        this(context, null);
    }

    public ImgDownloadFinishAnimView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ImgDownloadFinishAnimView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = e.a(5.0f);
        this.a = new Paint();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float currentTimeMillis = (((float) (System.currentTimeMillis() - this.d)) * 1.0f) / (((float) this.c) * 1.0f);
        int width = getWidth() / 2;
        int i = width - (this.b / 2);
        this.a.setAntiAlias(true);
        this.a.setStrokeWidth((float) this.b);
        this.a.setColor(Color.parseColor("#098EFF"));
        RectF rectF = new RectF((float) (width - i), (float) (width - i), (float) (width + i), (float) (width + i));
        this.a.setStyle(Style.STROKE);
        canvas.drawArc(rectF, 270.0f, 360.0f * currentTimeMillis, false, this.a);
        if (((double) currentTimeMillis) < 1.0d) {
            invalidate();
        }
    }

    public void a(int i) {
        this.c = i;
        this.d = System.currentTimeMillis();
        invalidate();
    }
}
