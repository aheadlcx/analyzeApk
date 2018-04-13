package in.srain.cube.views.ptr.header;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import java.util.Random;

public class StoreHouseBarItem extends Animation {
    private final Paint a = new Paint();
    private float b = 1.0f;
    private float c = 0.4f;
    private PointF d;
    private PointF e;
    public int index;
    public PointF midPoint;
    public float translationX;

    public StoreHouseBarItem(int i, PointF pointF, PointF pointF2, int i2, int i3) {
        this.index = i;
        this.midPoint = new PointF((pointF.x + pointF2.x) / 2.0f, (pointF.y + pointF2.y) / 2.0f);
        this.d = new PointF(pointF.x - this.midPoint.x, pointF.y - this.midPoint.y);
        this.e = new PointF(pointF2.x - this.midPoint.x, pointF2.y - this.midPoint.y);
        setColor(i2);
        setLineWidth(i3);
        this.a.setAntiAlias(true);
        this.a.setStyle(Style.STROKE);
    }

    public void setLineWidth(int i) {
        this.a.setStrokeWidth((float) i);
    }

    public void setColor(int i) {
        this.a.setColor(i);
    }

    public void resetPosition(int i) {
        this.translationX = (float) ((-new Random().nextInt(i)) + i);
    }

    protected void applyTransformation(float f, Transformation transformation) {
        float f2 = this.b;
        setAlpha(f2 + ((this.c - f2) * f));
    }

    public void start(float f, float f2) {
        this.b = f;
        this.c = f2;
        super.start();
    }

    public void setAlpha(float f) {
        this.a.setAlpha((int) (255.0f * f));
    }

    public void draw(Canvas canvas) {
        canvas.drawLine(this.d.x, this.d.y, this.e.x, this.e.y, this.a);
    }
}
