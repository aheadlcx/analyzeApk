package cn.xiaochuankeji.tieba.ui.homepage.banner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.util.AttributeSet;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class UgcEventAnimWebImageView extends WebImageView {
    private float a;
    private int b;
    private RectF c;
    private RectF d;
    private Paint e;

    public UgcEventAnimWebImageView(Context context) {
        this(context, null);
    }

    public UgcEventAnimWebImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public UgcEventAnimWebImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.b = e.a(5.0f);
        this.e = new Paint();
        this.e.setColor(-1);
        this.c = new RectF();
        this.c.left = 0.0f;
        this.c.top = 0.0f;
        this.c.right = (float) (e.b(getContext()) - e.a(20.0f));
        this.c.bottom = this.c.right / 2.55f;
        this.d = new RectF();
        this.d.left = (float) e.a(39.0f);
        this.d.top = 0.0f;
        this.d.right = this.d.left + ((float) e.a(27.0f));
        this.d.bottom = (float) e.a(27.0f);
    }

    public void setPercent(float f) {
        this.a = f;
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        if (this.a >= 0.7f) {
            Path path = new Path();
            float a = ((float) e.a(27.0f)) / 2.0f;
            path.addCircle(this.d.left + a, a, a, Direction.CW);
            canvas.clipPath(path);
        } else {
            RectF rectF = new RectF();
            rectF.left = (((this.d.left - this.c.left) * this.a) / 0.7f) + this.c.left;
            rectF.top = 0.0f;
            rectF.right = (((this.d.right - this.c.right) * this.a) / 0.7f) + this.c.right;
            rectF.bottom = (((this.d.bottom - this.c.bottom) * this.a) / 0.7f) + this.c.bottom;
            Path path2 = new Path();
            float f = ((float) this.b) + ((((float) (90 - this.b)) * this.a) / 0.7f);
            path2.addRoundRect(rectF, f, f, Direction.CW);
            canvas.clipPath(path2);
        }
        super.onDraw(canvas);
    }
}
