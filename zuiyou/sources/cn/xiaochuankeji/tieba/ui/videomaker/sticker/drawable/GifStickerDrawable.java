package cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.Nullable;
import cn.xiaochuan.base.BaseApplication;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import pl.droidsonroids.gif.b;

public class GifStickerDrawable extends AnimatedStickerDrawable {
    private String a;
    private b b;
    private int c;
    private int d;

    public GifStickerDrawable(String str, float f) {
        super(BaseApplication.getAppContext());
        this.a = str;
        try {
            int i = BaseApplication.getAppContext().getResources().getDisplayMetrics().widthPixels;
            this.b = new b(str);
            float f2 = ((float) i) * f;
            float intrinsicHeight = ((float) this.b.getIntrinsicHeight()) / (((float) this.b.getIntrinsicWidth()) / f2);
            this.c = (int) (f2 + 0.5f);
            this.d = (int) (intrinsicHeight + 0.5f);
            setBounds(0, 0, this.c, this.d);
            this.b.setBounds(0, 0, this.c, this.d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GifStickerDrawable(Context context, JSONObject jSONObject) throws JSONException {
        super(context, jSONObject);
        this.a = jSONObject.getString("g_s_d_s");
        try {
            this.b = new b(this.a);
            Rect bounds = getBounds();
            this.c = bounds.width();
            this.d = bounds.height();
            this.b.setBounds(0, 0, this.c, this.d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void onBoundsChange(Rect rect) {
        if (this.b != null) {
            this.b.setBounds(0, 0, rect.width(), rect.height());
        }
    }

    public void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        jSONObject.put("g_s_d_s", this.a);
    }

    public void a(Callback callback) {
        super.a(callback);
        this.b.setCallback(getCallback());
    }

    public void start() {
        if (this.b != null) {
            this.b.c();
        }
    }

    public void stop() {
        if (this.b != null) {
            this.b.stop();
        }
    }

    public boolean isRunning() {
        if (this.b != null) {
            return this.b.isRunning();
        }
        return false;
    }

    protected void a(Canvas canvas) {
        if (this.b != null) {
            canvas.save();
            this.b.draw(canvas);
            canvas.restore();
        }
    }

    public boolean a(long j) {
        if (this.b != null) {
            return this.b.b(j);
        }
        return false;
    }

    protected void a(Canvas canvas, int i) {
        if (this.b != null) {
            canvas.save();
            this.b.a(canvas, i);
            canvas.restore();
        }
    }

    public void setAlpha(int i) {
        if (this.b != null) {
            this.b.setAlpha(i);
        }
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        if (this.b != null) {
            this.b.setColorFilter(colorFilter);
        }
    }

    public int getOpacity() {
        return -3;
    }

    public int getIntrinsicWidth() {
        return this.c != 0 ? this.c : super.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        return this.d != 0 ? this.d : super.getIntrinsicHeight();
    }

    public void b() {
        super.b();
        if (this.b != null) {
            this.b.stop();
            this.b.a();
            this.b = null;
        }
    }

    protected boolean c() {
        return true;
    }
}
