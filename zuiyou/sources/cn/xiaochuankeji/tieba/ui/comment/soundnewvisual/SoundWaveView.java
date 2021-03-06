package cn.xiaochuankeji.tieba.ui.comment.soundnewvisual;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import cn.xiaochuankeji.tieba.R;

public class SoundWaveView extends View {
    private final Bitmap a;
    private final Bitmap b;
    private final Rect c;
    private final Rect d;
    private final Rect e;
    private long f;
    private long g;
    private WaveState h = WaveState.InitState;

    public enum WaveState {
        InitState,
        PlayState,
        PauseState
    }

    public SoundWaveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.img_sound_wave_blue)).getBitmap();
        this.b = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.img_sound_wave_yellow)).getBitmap();
        this.c = new Rect();
        this.d = new Rect();
        this.e = new Rect();
    }

    public void a(int i, long j) {
        this.h = WaveState.PlayState;
        this.f = (long) i;
        this.g = System.currentTimeMillis() - j;
        invalidate();
    }

    public void a(boolean z, long j, long j2) {
        this.h = z ? WaveState.PlayState : WaveState.PauseState;
        this.f = j;
        this.g = System.currentTimeMillis() - j2;
        invalidate();
    }

    public void a() {
        if (this.h == WaveState.PlayState) {
            this.h = WaveState.PauseState;
        }
    }

    public void b() {
        this.h = WaveState.InitState;
        invalidate();
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        int width = getWidth();
        int height = getHeight();
        int width2 = this.a.getWidth();
        int height2 = this.a.getHeight();
        Object obj = width2 <= width ? 1 : null;
        this.c.top = 0;
        this.c.bottom = height2;
        this.d.top = 0;
        this.d.bottom = height2;
        this.e.top = 0;
        this.e.bottom = height;
        Rect rect;
        if (this.h == WaveState.InitState) {
            int i;
            this.c.left = 0;
            rect = this.c;
            if (obj != null) {
                i = width2;
            } else {
                i = width;
            }
            rect.right = i;
            this.e.left = 0;
            this.e.right = width;
            canvas.drawBitmap(this.a, this.c, this.e, null);
        } else if (this.h == WaveState.PlayState) {
            height2 = width2 - width;
            float currentTimeMillis = (((float) (System.currentTimeMillis() - this.g)) * 1.0f) / ((float) this.f);
            if (((double) currentTimeMillis) >= 1.0d) {
                currentTimeMillis = 1.0f;
            }
            int i2 = (int) (((float) height2) * currentTimeMillis);
            this.c.left = obj != null ? 0 : i2;
            this.c.right = obj != null ? width2 : i2 + width;
            this.e.left = 0;
            this.e.right = width;
            canvas.drawBitmap(this.a, this.c, this.e, null);
            Rect rect2 = this.d;
            if (obj != null) {
                i2 = 0;
            }
            rect2.left = i2;
            this.d.right = (int) (((float) width2) * currentTimeMillis);
            this.e.left = 0;
            this.e.right = (int) (((float) width) * currentTimeMillis);
            canvas.drawBitmap(this.b, this.d, this.e, null);
            if (((double) currentTimeMillis) <= 1.0d) {
                invalidate();
            } else {
                this.h = WaveState.InitState;
            }
        } else if (this.h == WaveState.PauseState) {
            float currentTimeMillis2 = (((float) (System.currentTimeMillis() - this.g)) * 1.0f) / ((float) this.f);
            height2 = (int) (((float) (width2 - width)) * currentTimeMillis2);
            this.c.left = obj != null ? 0 : height2;
            this.c.right = obj != null ? width2 : height2 + width;
            this.e.left = 0;
            this.e.right = width;
            canvas.drawBitmap(this.a, this.c, this.e, null);
            rect = this.d;
            if (obj != null) {
                height2 = 0;
            }
            rect.left = height2;
            this.d.right = (int) (((float) width2) * currentTimeMillis2);
            this.e.left = 0;
            this.e.right = (int) (((float) width) * currentTimeMillis2);
            canvas.drawBitmap(this.b, this.d, this.e, null);
        }
    }
}
