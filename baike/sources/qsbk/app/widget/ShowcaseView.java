package qsbk.app.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

public class ShowcaseView extends View {
    Map<View, String> a = new HashMap();
    Map<View, Drawable> b = new HashMap();
    Map<Point, Drawable> c = new HashMap();
    private int d;
    private int e;
    private Paint f = new Paint();
    private int g;
    private Xfermode h = new PorterDuffXfermode(Mode.SRC_OUT);
    private Xfermode i = new PorterDuffXfermode(Mode.SRC);
    private int j;
    private int k;
    private int l = 200;

    public static class Builder {
        Map<View, String> a = new HashMap();
        Map<View, Drawable> b = new HashMap();
        Map<Point, Drawable> c = new HashMap();
        int d;

        public Builder setBackgroundColor(int i) {
            this.d = i;
            return this;
        }

        public Builder addShowcase(View view, String str) {
            this.a.put(view, str);
            return this;
        }

        public Builder addShowcase(View view, Drawable drawable) {
            this.b.put(view, drawable);
            return this;
        }

        public Builder addShowcase(Point point, Drawable drawable) {
            this.c.put(point, drawable);
            return this;
        }

        public ShowcaseView build(Context context) {
            ShowcaseView showcaseView = new ShowcaseView(context);
            showcaseView.setShowcace(this.a);
            showcaseView.setShowcaseDrawable(this.b);
            showcaseView.setBackColor(this.d);
            showcaseView.setPointDrawable(this.c);
            return showcaseView;
        }
    }

    public ShowcaseView(Context context) {
        super(context);
        a(context, null, 0);
    }

    public ShowcaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    public ShowcaseView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    void a(Context context, AttributeSet attributeSet, int i) {
        setWillNotDraw(false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShowcaseView, i, 0);
        this.d = obtainStyledAttributes.getColor(0, context.getResources().getColor(17170444));
        this.e = obtainStyledAttributes.getColor(1, context.getResources().getColor(17170445));
        this.j = obtainStyledAttributes.getColor(3, context.getResources().getColor(17170443));
        obtainStyledAttributes.recycle();
        this.k = UIHelper.dip2px(context, 3.0f);
        this.g = UIHelper.dip2px(QsbkApp.getInstance(), 12.0f);
    }

    public void setShowcace(Map<View, String> map) {
        this.a = map;
        invalidate();
    }

    private void setShowcaseDrawable(Map<View, Drawable> map) {
        this.b = map;
    }

    public void setPointDrawable(Map<Point, Drawable> map) {
        this.c = map;
    }

    public void setBackColor(int i) {
        this.d = i;
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        Paint paint = new Paint(1);
        paint.setStyle(Style.FILL);
        int saveLayer = canvas.saveLayer(0.0f, 0.0f, (float) width, (float) height, null, 31);
        paint.setColor(this.d);
        canvas.drawRect(0.0f, 0.0f, (float) width, (float) height, paint);
        paint.setXfermode(this.h);
        paint.setColor(this.e);
        this.f.setColor(this.j);
        this.f.setTextSize((float) UIHelper.dip2px(getContext(), 17.0f));
        if (this.a != null && this.a.size() > 0) {
            for (Entry entry : this.a.entrySet()) {
                View view = (View) entry.getKey();
                a(canvas, paint, view);
                a(canvas, this.f, view, (String) entry.getValue());
            }
        }
        if (this.b != null && this.b.size() > 0) {
            for (Entry entry2 : this.b.entrySet()) {
                view = (View) entry2.getKey();
                a(canvas, paint, view);
                a(canvas, view, (Drawable) entry2.getValue());
            }
        }
        if (this.c != null && this.c.size() > 0) {
            for (Entry entry22 : this.c.entrySet()) {
                a(canvas, (Point) entry22.getKey(), (Drawable) entry22.getValue());
            }
        }
        paint.setXfermode(this.i);
        paint.setColor(this.d);
        canvas.restoreToCount(saveLayer);
    }

    private void a(Canvas canvas, Paint paint, View view, String str) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        Point point = new Point(iArr[0] + (view.getWidth() / 2), iArr[1] + view.getHeight());
        canvas.drawText(str, (float) ((int) (((float) point.x) - (paint.measureText(str) / 2.0f))), (float) (point.y + this.l), paint);
    }

    private void a(Canvas canvas, View view, Drawable drawable) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int width = (iArr[0] + (view.getWidth() / 2)) - (intrinsicWidth / 2);
        int height = ((iArr[1] + (view.getHeight() / 2)) - (intrinsicHeight / 2)) + this.l;
        drawable.setBounds(width, height, intrinsicWidth + width, intrinsicHeight + height);
        drawable.draw(canvas);
    }

    private void a(Canvas canvas, Point point, Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int i = point.x - (intrinsicWidth / 2);
        int i2 = (point.y - (intrinsicHeight / 2)) + this.l;
        drawable.setBounds(i, i2, intrinsicWidth + i, intrinsicHeight + i2);
        drawable.draw(canvas);
    }

    private void a(Canvas canvas, Paint paint, View view) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        Point point = new Point(iArr[0] + (view.getWidth() / 2), iArr[1] + (view.getHeight() / 2));
        if (view.getWidth() == view.getHeight()) {
            a(canvas, paint, point, (view.getWidth() / 2) + this.k);
        } else {
            a(canvas, paint, new Rect(iArr[0] - this.k, iArr[1] - this.k, (iArr[0] + view.getWidth()) + this.k, (iArr[1] + view.getHeight()) + this.k));
        }
    }

    private void a(Canvas canvas, Paint paint, Rect rect) {
        canvas.drawRoundRect(new RectF(rect), ((float) this.g) + 0.5f, ((float) this.g) + 0.5f, paint);
    }

    private void a(Canvas canvas, Paint paint, Point point, int i) {
        canvas.drawCircle((float) point.x, (float) point.y, (float) i, paint);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        a();
        return true;
    }

    public void show(Activity activity) {
        ((ViewGroup) activity.getWindow().getDecorView()).addView(this);
    }

    public void dismiss() {
        a();
    }

    private void a() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup != null) {
            viewGroup.removeView(this);
        }
    }
}
