package cn.xiaochuankeji.tieba.ui.recommend.widget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.utils.e;

public class a extends Drawable {
    private static final int a = e.a(7.0f);
    private static final int b = e.a(8.0f);
    private static final int c = e.a(32.0f);
    private static final int d = e.a(18.0f);
    private static final int e = e.a(10.0f);
    private static final int f = e.a(5.0f);
    private static final int g = e.a(12.0f);
    private int h;
    private int i;
    private int j;
    private long k;

    public a(int i) {
        this.h = i;
    }

    public void draw(@NonNull Canvas canvas) {
        int i = getBounds().left;
        int i2 = getBounds().right;
        int i3 = getBounds().top;
        int i4 = getBounds().bottom;
        Paint paint;
        if (this.h == 1) {
            paint = new Paint();
            paint.setColor(Color.parseColor("#33000000"));
            i2 = (i2 - b) - c;
            i3 = (i4 - a) - d;
            RectF rectF = new RectF((float) i2, (float) i3, (float) (i2 + c), (float) (i3 + d));
            canvas.drawRoundRect(rectF, (float) f, (float) f, paint);
            paint = new TextPaint(1);
            paint.setColor(c.a.d.a.a.a().a((int) R.color.CW));
            paint.setTextSize((float) e);
            FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
            i2 = (((((int) rectF.bottom) + ((int) rectF.top)) - fontMetricsInt.bottom) - fontMetricsInt.top) / 2;
            paint.setTextAlign(Align.CENTER);
            canvas.drawText("长图", rectF.centerX(), (float) i2, paint);
        } else if (this.h == 2) {
            r4 = c.a.d.a.a.a().b(R.drawable.ic_gif2_flag);
            r5 = e.a(35.0f);
            r4.setBounds((((i2 - i) - r5) / 2) + i, (((i4 - i3) - r5) / 2) + i3, i + (((i2 - i) + r5) / 2), (((i4 - i3) + r5) / 2) + i3);
            r4.draw(canvas);
        } else if (this.h == 3) {
            r4 = c.a.d.a.a.a().b(R.drawable.ic_video2_flag);
            r5 = r4.getIntrinsicWidth();
            int intrinsicHeight = r4.getIntrinsicHeight();
            r4.setBounds(((i2 - i) - r5) / 2, ((i4 - i3) - intrinsicHeight) / 2, (r5 + (i2 - i)) / 2, ((i4 - i3) + intrinsicHeight) / 2);
            r4.draw(canvas);
            Paint textPaint = new TextPaint(1);
            textPaint.setColor(c.a.d.a.a.a().a((int) R.color.CW));
            textPaint.setTextSize((float) g);
            int descent = (int) ((textPaint.descent() + textPaint.ascent()) / 2.0f);
            if (this.i > 0) {
                Drawable b = c.a.d.a.a.a().b(R.drawable.recommend_video_cover);
                b.setBounds(new Rect(i, i4 - e.a(50.0f), i2, i4));
                b.draw(canvas);
                r5 = i4 - e.a(25.0f);
                i += e.a(17.0f);
                intrinsicHeight = e.a(12.0f);
                int a = e.a(7.0f);
                Drawable b2 = c.a.d.a.a.a().b(R.drawable.ic_play_count_flag);
                b2.setBounds(i, r5, i + intrinsicHeight, r5 + intrinsicHeight);
                b2.draw(canvas);
                i += intrinsicHeight + a;
                String b3 = d.b(this.i);
                Rect rect = new Rect();
                textPaint.getTextBounds(b3, 0, b3.length(), rect);
                canvas.drawText(b3, (float) i, (float) (((intrinsicHeight / 2) - descent) + r5), textPaint);
                if (this.j > 0) {
                    i += rect.width() + e.a(21.0f);
                    b2 = c.a.d.a.a.a().b(R.drawable.ic_danmu_count_flag);
                    b2.setBounds(i, r5, i + intrinsicHeight, r5 + intrinsicHeight);
                    b2.draw(canvas);
                    canvas.drawText(d.b(this.j), (float) (i + (a + intrinsicHeight)), (float) (r5 + ((intrinsicHeight / 2) - descent)), textPaint);
                }
            }
            if (this.k > 0) {
                String a2 = d.a(this.k * 1000);
                canvas.drawText(a2, (float) ((i2 - e.a(15.0f)) - ((int) textPaint.measureText(a2))), (float) ((i4 - e.a(25.0f)) + ((e.a(12.0f) / 2) - descent)), textPaint);
            }
        } else if (this.h == 4) {
            r4 = c.a.d.a.a.a().b(R.drawable.ic_video3_flag);
            r5 = e.a(35.0f);
            r4.setBounds((((i2 - i) - r5) / 2) + i, (((i4 - i3) - r5) / 2) + i3, i + (((i2 - i) + r5) / 2), i3 + ((r5 + (i4 - i3)) / 2));
            r4.draw(canvas);
            if (this.k > 0) {
                paint = new TextPaint(1);
                paint.setColor(c.a.d.a.a.a().a((int) R.color.CW));
                paint.setTextSize((float) g);
                i3 = (int) ((paint.descent() + paint.ascent()) / 2.0f);
                String a3 = d.a(this.k * 1000);
                Rect rect2 = new Rect();
                paint.getTextBounds(a3, 0, a3.length(), rect2);
                canvas.drawText(a3, (float) ((i2 - e.a(10.0f)) - rect2.width()), (float) ((i4 - e.a(20.0f)) + (e.a(12.0f) / 2)), paint);
            }
        }
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    public int getOpacity() {
        return -3;
    }

    public void a(int i) {
        this.i = i;
    }

    public void b(int i) {
        this.j = i;
    }

    public void a(long j) {
        this.k = j;
    }
}
