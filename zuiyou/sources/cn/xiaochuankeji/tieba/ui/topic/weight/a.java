package cn.xiaochuankeji.tieba.ui.topic.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class a extends View {
    private float a;
    private Paint b;

    public a(Context context) {
        super(context);
        a();
    }

    private void a() {
        this.b = new Paint();
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(this.a, this.a, this.a, this.b);
        super.onDraw(canvas);
    }

    void setCircleRadius(float f) {
        this.a = f;
        invalidate();
    }

    void setCircleColor(int i) {
        this.b.setColor(i);
        invalidate();
    }
}
