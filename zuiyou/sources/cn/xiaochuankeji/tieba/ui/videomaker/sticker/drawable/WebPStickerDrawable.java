package cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.c.a;
import com.izuiyou.a.a.b;
import org.json.JSONException;
import org.json.JSONObject;

public class WebPStickerDrawable extends AnimatedStickerDrawable {
    private String a;
    private a b;
    private int c;
    private int d;

    public WebPStickerDrawable(String str, float f) {
        super(BaseApplication.getAppContext());
        this.a = str;
        a a = a.a(str);
        this.b = a;
        float f2 = ((float) BaseApplication.getAppContext().getResources().getDisplayMetrics().widthPixels) * f;
        float intrinsicHeight = ((float) a.getIntrinsicHeight()) / (((float) a.getIntrinsicWidth()) / f2);
        this.c = (int) (f2 + 0.5f);
        this.d = (int) (intrinsicHeight + 0.5f);
        setBounds(0, 0, this.c, this.d);
        this.b.setBounds(0, 0, this.c, this.d);
        b.c("percent:" + f + " mWidth:" + this.c + "  mHeight:" + this.d + "   DstRect:" + a.c());
    }

    public WebPStickerDrawable(Context context, JSONObject jSONObject) throws JSONException {
        super(context, jSONObject);
        this.a = jSONObject.getString("w_s_d_s");
        a a = a.a(this.a);
        this.b = a;
        Rect bounds = getBounds();
        this.c = bounds.width();
        this.d = bounds.height();
        this.b.setBounds(0, 0, this.c, this.d);
        b.c("mWidth:" + this.c + "  mHeight:" + this.d + "   DstRect:" + a.c());
    }

    public void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        jSONObject.put("w_s_d_s", this.a);
    }

    public void a(Callback callback) {
        super.a(callback);
        if (this.b != null) {
            this.b.setCallback(getCallback());
        }
    }

    public void start() {
        if (this.b != null) {
            this.b.start();
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
            return this.b.a(j);
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
        return this.c;
    }

    public int getIntrinsicHeight() {
        return this.d;
    }

    public int getMinimumWidth() {
        return this.c;
    }

    public int getMinimumHeight() {
        return this.d;
    }

    public void setTintList(@Nullable ColorStateList colorStateList) {
        super.setTintList(colorStateList);
        if (this.b != null) {
            this.b.setTintList(colorStateList);
        }
    }

    public boolean isStateful() {
        if (this.b != null) {
            return this.b.isStateful();
        }
        return super.isStateful();
    }

    public boolean setState(@NonNull int[] iArr) {
        if (this.b != null) {
            return this.b.setState(iArr);
        }
        return super.setState(iArr);
    }

    public boolean setVisible(boolean z, boolean z2) {
        if (this.b != null) {
            return this.b.setVisible(z, z2);
        }
        return super.setVisible(z, z2);
    }

    public void b() {
        super.b();
        if (this.b != null) {
            this.b.a();
        }
    }

    protected boolean c() {
        return true;
    }

    protected void onBoundsChange(Rect rect) {
        if (this.b != null) {
            this.b.setBounds(0, 0, rect.width(), rect.height());
        }
    }
}
