package cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.b.e;
import org.json.JSONException;
import org.json.JSONObject;

public class VectorStickerDrawable extends a {
    @DrawableRes
    int a;
    int b;
    int c;
    private Drawable d;

    public VectorStickerDrawable(@DrawableRes int i, int i2, int i3) {
        super(BaseApplication.getAppContext());
        this.a = i;
        if (i > 0) {
            this.d = e.a(BaseApplication.getAppContext().getResources(), i, null);
            this.b = i2;
            this.c = i3;
            this.d.setBounds(0, 0, this.b, this.c);
        }
    }

    public VectorStickerDrawable(@DrawableRes int i, float f) {
        super(BaseApplication.getAppContext());
        this.a = i;
        if (i > 0) {
            this.d = e.a(BaseApplication.getAppContext().getResources(), i, null);
            int b = (int) ((((float) cn.xiaochuankeji.tieba.ui.utils.e.b()) * f) + 0.5f);
            int intrinsicHeight = (int) ((((float) this.d.getIntrinsicHeight()) / (((float) this.d.getIntrinsicWidth()) / ((float) b))) + 0.5f);
            this.b = b;
            this.c = intrinsicHeight;
            this.d.setBounds(0, 0, this.b, this.c);
        }
    }

    public VectorStickerDrawable(Context context, JSONObject jSONObject) throws JSONException {
        super(context, jSONObject);
        this.a = BaseApplication.getAppContext().getResources().getIdentifier(jSONObject.getString("r_s_d_s"), jSONObject.getString("r_s_d_r_t_n"), jSONObject.getString("r_s_d_r_p"));
        if (this.a > 0) {
            this.d = e.a(context.getResources(), this.a, null);
            Rect bounds = getBounds();
            this.b = bounds.width();
            this.c = bounds.height();
            this.d.setBounds(0, 0, this.b, this.c);
        }
    }

    public void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        Resources resources = BaseApplication.getAppContext().getResources();
        jSONObject.put("r_s_d_s", resources.getResourceEntryName(this.a));
        jSONObject.put("r_s_d_r_t_n", resources.getResourceTypeName(this.a));
        jSONObject.put("r_s_d_r_p", resources.getResourcePackageName(this.a));
    }

    protected boolean c() {
        return true;
    }

    protected void a(Canvas canvas) {
        if (this.d != null) {
            canvas.save();
            this.d.draw(canvas);
            canvas.restore();
        }
    }

    public void setAlpha(int i) {
        if (this.d != null) {
            this.d.setAlpha(i);
        }
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        if (this.d != null) {
            this.d.setColorFilter(colorFilter);
        }
    }

    public int getOpacity() {
        return -3;
    }

    public int getIntrinsicWidth() {
        return this.b;
    }

    public int getIntrinsicHeight() {
        return this.c;
    }

    public void a(Callback callback) {
        super.a(callback);
        if (this.d != null) {
            this.d.setCallback(callback);
        }
    }
}
