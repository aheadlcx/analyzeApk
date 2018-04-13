package qsbk.app.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

public class QBImageView extends SimpleDraweeView {
    private Drawable a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f = 51;

    public QBImageView(Context context, GenericDraweeHierarchy genericDraweeHierarchy) {
        super(context, genericDraweeHierarchy);
        a(context, null, 0);
    }

    public QBImageView(Context context) {
        super(context);
        a(context, null, 0);
    }

    public QBImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    public QBImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    public static int dp2px(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        this.b = dp2px(context, 4.0f);
        this.c = dp2px(context, 4.0f);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.a != null) {
            switch (this.f) {
                case 51:
                    this.a.setBounds(this.b, this.c, this.b + this.a.getIntrinsicWidth(), this.c + this.a.getIntrinsicHeight());
                    break;
                case 85:
                    int measuredWidth = getMeasuredWidth();
                    this.a.setBounds((measuredWidth - this.a.getIntrinsicWidth()) - this.d, (getMeasuredHeight() - this.a.getIntrinsicHeight()) - this.e, this.a.getIntrinsicWidth(), this.a.getIntrinsicHeight());
                    break;
            }
            this.a.draw(canvas);
        }
    }

    public void setTypeImageResouce(int i) {
        if (i == 0) {
            this.a = null;
        } else {
            this.a = getResources().getDrawable(i);
        }
        invalidate();
    }
}
