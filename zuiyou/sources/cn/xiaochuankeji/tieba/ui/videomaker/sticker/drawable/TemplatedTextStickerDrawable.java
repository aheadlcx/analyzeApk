package cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.text.Spannable;
import cn.xiaochuankeji.tieba.ui.videomaker.edittext.e.b;
import org.json.JSONException;
import org.json.JSONObject;

public class TemplatedTextStickerDrawable extends a {
    private BitmapStickerDrawable a;
    private TextStickerDrawable b;
    private int c;
    private int d;
    private int e;

    public TemplatedTextStickerDrawable(Context context, Spannable spannable, float f, float f2, float f3, b bVar) {
        super(context);
        this.c = bVar.d;
        this.d = bVar.e;
        if (bVar.g != null) {
            this.b = new TextStickerDrawable(context, spannable, f, (this.c - bVar.g.left) - bVar.g.right, f2, f3, bVar.h);
            this.b.setBounds(bVar.g.left, bVar.g.top, this.c - bVar.g.right, this.d - bVar.g.bottom);
        } else {
            this.b = new TextStickerDrawable(context, spannable, f, this.c, f2, f3, bVar.h);
            this.b.setBounds(0, 0, this.c, this.d);
        }
        if (bVar.f != null) {
            this.a = new BitmapStickerDrawable(bVar.c, (this.c - bVar.f.left) - bVar.f.right, (bVar.e - bVar.f.top) - bVar.f.bottom);
            this.a.setBounds(bVar.f.left, bVar.f.top, this.c - bVar.f.right, this.d - bVar.f.bottom);
        } else {
            this.a = new BitmapStickerDrawable(bVar.c, this.c, this.d);
            this.a.setBounds(0, 0, this.c, this.d);
        }
        this.e = bVar.a;
    }

    public TemplatedTextStickerDrawable(Context context, JSONObject jSONObject) throws JSONException {
        super(context, jSONObject);
        this.a = new BitmapStickerDrawable(context, jSONObject.getJSONObject("key_bitmap_draw"));
        this.b = new TextStickerDrawable(context, jSONObject.getJSONObject("key_text_draw"));
        this.e = jSONObject.getInt("key_template_id");
        this.c = getBounds().width();
        this.d = getBounds().height();
    }

    public Spannable k() {
        return this.b.k();
    }

    public int l() {
        return this.e;
    }

    public void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        JSONObject jSONObject2 = new JSONObject();
        this.a.a(jSONObject2);
        jSONObject.put("key_bitmap_draw", jSONObject2);
        jSONObject2 = new JSONObject();
        this.b.a(jSONObject2);
        jSONObject.put("key_text_draw", jSONObject2);
        jSONObject.put("key_template_id", this.e);
    }

    protected void a(Canvas canvas) {
        this.a.draw(canvas);
        this.b.draw(canvas);
    }

    public void setAlpha(@IntRange(from = 0, to = 255) int i) {
        this.a.setAlpha(i);
        this.b.setAlpha(i);
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
        this.b.setColorFilter(colorFilter);
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
}
