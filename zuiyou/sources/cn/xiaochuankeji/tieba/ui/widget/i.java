package cn.xiaochuankeji.tieba.ui.widget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import c.a.d.a.a;
import cn.xiaochuankeji.tieba.R;

public class i extends Drawable {
    private int a;
    private Paint b = new Paint();

    public i(int i) {
        this.a = i;
        this.b.setColor(a.a().a((int) R.color.tale_card_bg));
        this.b.setStyle(Style.FILL);
        this.b.setAntiAlias(true);
        this.b.setShadowLayer((float) i, 0.0f, 0.0f, Color.parseColor("#20000000"));
    }

    public void draw(@NonNull Canvas canvas) {
        canvas.drawRoundRect(new RectF((float) this.a, (float) this.a, (float) (getBounds().width() - this.a), (float) (getBounds().height() - this.a)), 0.0f, 0.0f, this.b);
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    public int getOpacity() {
        return 0;
    }
}
