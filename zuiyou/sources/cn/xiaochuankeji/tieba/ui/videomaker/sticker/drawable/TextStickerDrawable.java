package cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Typeface;
import android.support.v4.view.ViewCompat;
import android.text.Layout.Alignment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextPaint;
import cn.xiaochuankeji.tieba.ui.videomaker.edittext.a;
import cn.xiaochuankeji.tieba.ui.videomaker.edittext.b;
import cn.xiaochuankeji.tieba.ui.videomaker.edittext.c;
import cn.xiaochuankeji.tieba.ui.videomaker.edittext.d;
import cn.xiaochuankeji.tieba.ui.videomaker.edittext.e;
import org.json.JSONException;
import org.json.JSONObject;

public class TextStickerDrawable extends a {
    private final int a;
    private final TextPaint b;
    private final Spannable c;
    private final float d;
    private final float e;
    private StaticLayout f;
    private int g = -1;
    private int h = 0;

    public TextStickerDrawable(Context context, Spannable spannable, float f, int i, float f2, float f3, int i2) {
        int i3;
        int i4;
        super(context);
        this.c = spannable;
        this.a = i;
        this.d = f2;
        this.e = f3;
        this.h = i2;
        this.b = new TextPaint();
        this.b.setColor(-1);
        this.b.density = context.getResources().getDisplayMetrics().density;
        this.b.setTextSize(f);
        this.b.setAntiAlias(true);
        this.b.setDither(true);
        this.b.setFilterBitmap(true);
        if (this.h == 0) {
            this.b.setFakeBoldText(true);
        } else {
            Typeface a = a.a(context, this.h);
            if (a != null) {
                this.b.setTypeface(a);
            }
        }
        int i5 = -1;
        e.a aVar = null;
        Object[] spans = this.c.getSpans(0, this.c.length(), b.class);
        if (spans == null || spans.length <= 0) {
            i3 = ViewCompat.MEASURED_STATE_MASK;
            i4 = 0;
        } else {
            int a2 = ((b) spans[0]).a();
            i5 = ((b) spans[0]).b();
            e.a d = ((b) spans[0]).d();
            if (((b) spans[0]).c()) {
                i3 = i5;
                i5 = a2;
                e.a aVar2 = d;
                i4 = 2;
                aVar = aVar2;
            } else {
                aVar = d;
                i4 = 0;
                i3 = i5;
                i5 = a2;
            }
        }
        d.a(spannable, b.class);
        l();
        Object[] spans2 = this.c.getSpans(0, this.c.length(), c.class);
        if (spans2 != null && spans2.length > 0) {
            this.c.removeSpan(spans2[0]);
            i4 = 1;
            int i6 = i5;
            i5 = i3;
            i3 = i6;
        }
        a(i5, i3, i4, aVar);
    }

    public TextStickerDrawable(Context context, JSONObject jSONObject) throws JSONException {
        int i;
        int i2;
        e.a aVar;
        int i3 = -1;
        super(context, jSONObject);
        this.c = new SpannableString(jSONObject.getString("text"));
        this.a = jSONObject.getInt("layout_width");
        this.d = (float) jSONObject.getInt("inset_x");
        this.e = (float) jSONObject.getInt("inset_y");
        this.h = jSONObject.getInt("font_index");
        int i4 = jSONObject.getInt("text_size");
        this.b = new TextPaint();
        this.b.setColor(-1);
        this.b.density = context.getResources().getDisplayMetrics().density;
        this.b.setTextSize((float) i4);
        this.b.setAntiAlias(true);
        this.b.setDither(true);
        this.b.setFilterBitmap(true);
        if (this.h == 0) {
            this.b.setFakeBoldText(true);
        } else {
            Typeface a = a.a(context, this.h);
            if (a != null) {
                this.b.setTypeface(a);
            }
        }
        l();
        if (jSONObject.has("foreground_color")) {
            boolean z;
            i4 = jSONObject.getInt("foreground_color");
            if (jSONObject.has("background_color")) {
                i = i4;
                i4 = jSONObject.getInt("background_color");
                z = true;
            } else {
                if (i4 == -1) {
                    i3 = ViewCompat.MEASURED_STATE_MASK;
                }
                i = i3;
                z = false;
            }
            i2 = i;
            i = z;
            aVar = null;
        } else {
            i4 = jSONObject.getInt("major_color");
            i2 = jSONObject.getInt("minor_color");
            i = jSONObject.getInt("text_style");
            JSONObject optJSONObject = jSONObject.optJSONObject("text_shadow");
            aVar = optJSONObject != null ? new e.a(optJSONObject) : null;
        }
        a(i4, i2, i, aVar);
    }

    private void a(int i, int i2, int i3, e.a aVar) {
        int i4;
        if (1 == i3) {
            for (i4 = 0; i4 < this.c.length(); i4++) {
                if (cn.xiaochuankeji.tieba.ui.utils.e.a(this.c.charAt(i4))) {
                    this.c.setSpan(new b(i2, i, false, aVar), i4, i4 + 1, 18);
                }
            }
            this.c.setSpan(new c(i, this.f, this.d, this.e), 0, this.c.length(), 18);
            return;
        }
        for (i4 = 0; i4 < this.c.length(); i4++) {
            if (cn.xiaochuankeji.tieba.ui.utils.e.a(this.c.charAt(i4))) {
                boolean z;
                if (2 == i3) {
                    z = true;
                } else {
                    z = false;
                }
                this.c.setSpan(new b(i, i2, z, aVar), i4, i4 + 1, 18);
            }
        }
        if (i3 == 0) {
            this.b.setShadowLayer(2.0f, 0.0f, 4.0f, 855638016);
        }
    }

    public void a(JSONObject jSONObject) throws JSONException {
        int i;
        e.a aVar;
        int i2;
        int i3 = 0;
        super.a(jSONObject);
        jSONObject.put("text", this.c);
        Object[] spans = this.c.getSpans(0, this.c.length(), c.class);
        if (spans == null || spans.length <= 0) {
            i = 0;
        } else {
            i = 1;
        }
        Object[] spans2 = this.c.getSpans(0, this.c.length(), b.class);
        if (spans2 == null || spans2.length <= 0) {
            aVar = null;
            i2 = i;
            i = 0;
        } else if (1 == i) {
            r4 = ((b) spans2[0]).b();
            i3 = ((b) spans2[0]).a();
            aVar = null;
            i2 = i;
            i = r4;
        } else {
            r4 = ((b) spans2[0]).a();
            i2 = ((b) spans2[0]).b();
            if (((b) spans2[0]).c()) {
                i = 2;
            } else {
                i = 0;
            }
            aVar = ((b) spans2[0]).d();
            i3 = i2;
            i2 = i;
            i = r4;
        }
        jSONObject.put("major_color", i);
        jSONObject.put("minor_color", i3);
        jSONObject.put("text_style", i2);
        if (aVar != null) {
            JSONObject jSONObject2 = new JSONObject();
            aVar.a(jSONObject2);
            jSONObject.put("text_shadow", jSONObject2);
        }
        jSONObject.put("text_size", (double) this.b.getTextSize());
        jSONObject.put("layout_width", this.a);
        jSONObject.put("inset_x", (double) this.d);
        jSONObject.put("inset_y", (double) this.e);
        jSONObject.put("font_index", this.h);
    }

    public Spannable k() {
        return this.c;
    }

    private void l() {
        if (this.c != null) {
            this.f = new StaticLayout(this.c, this.b, this.a, Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            this.g = this.f.getHeight();
        }
    }

    protected void a(Canvas canvas) {
        canvas.save();
        canvas.translate(this.d, this.e);
        this.f.draw(canvas);
        canvas.restore();
    }

    public void setAlpha(int i) {
        this.b.setAlpha(i);
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.b.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public int getOpacity() {
        return -3;
    }

    public int getIntrinsicWidth() {
        return this.a;
    }

    public int getIntrinsicHeight() {
        return (int) (((float) this.g) + (2.0f * this.e));
    }
}
