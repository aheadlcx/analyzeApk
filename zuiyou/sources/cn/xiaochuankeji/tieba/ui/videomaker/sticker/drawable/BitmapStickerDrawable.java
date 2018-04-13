package cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import cn.xiaochuan.base.BaseApplication;
import org.json.JSONException;
import org.json.JSONObject;

public class BitmapStickerDrawable extends a {
    private final Paint a = new Paint(6);
    private String b;
    private int c;
    private Bitmap d;
    private Rect e = new Rect();
    private int f;
    private int g;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap b(java.lang.String r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x000a in list [B:14:0x00aa]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r8 = this;
        r7 = 1;
        r6 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r0 = 0;
        r1 = android.text.TextUtils.isEmpty(r9);
        if (r1 == 0) goto L_0x000b;
    L_0x000a:
        return r0;
    L_0x000b:
        r1 = new java.io.File;
        r1.<init>(r9);
        r2 = r1.exists();
        if (r2 == 0) goto L_0x000a;
    L_0x0016:
        r2 = r1.length();
        r4 = 0;
        r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r1 == 0) goto L_0x000a;
    L_0x0020:
        r0 = new android.graphics.BitmapFactory$Options;
        r0.<init>();
        r0.inJustDecodeBounds = r7;
        android.graphics.BitmapFactory.decodeFile(r9, r0);
        r1 = r0.outWidth;
        r2 = r0.outHeight;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "before compress size: w=";
        r3 = r3.append(r4);
        r3 = r3.append(r1);
        r4 = "   h=";
        r3 = r3.append(r4);
        r3 = r3.append(r2);
        r3 = r3.toString();
        com.izuiyou.a.a.b.c(r3);
        r3 = (float) r1;
        r4 = 1144258560; // 0x44340000 float:720.0 double:5.653388445E-315;
        r3 = r3 / r4;
        r4 = (float) r2;
        r5 = 1151336448; // 0x44a00000 float:1280.0 double:5.68835786E-315;
        r4 = r4 / r5;
        r3 = java.lang.Math.max(r3, r4);
        r4 = 0;
        r0.inJustDecodeBounds = r4;
        r4 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r4 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1));
        if (r4 > 0) goto L_0x006a;
    L_0x0065:
        r0 = android.graphics.BitmapFactory.decodeFile(r9, r0);
        goto L_0x000a;
    L_0x006a:
        r1 = (float) r1;
        r1 = r1 / r3;
        r1 = r1 + r6;
        r1 = (int) r1;
        r2 = (float) r2;
        r2 = r2 / r3;
        r2 = r2 + r6;
        r2 = (int) r2;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "after compress size: w=";
        r4 = r4.append(r5);
        r4 = r4.append(r1);
        r5 = "   h=";
        r4 = r4.append(r5);
        r4 = r4.append(r2);
        r5 = "  scale:";
        r4 = r4.append(r5);
        r3 = r4.append(r3);
        r3 = r3.toString();
        com.izuiyou.a.a.b.c(r3);
        r3 = android.graphics.BitmapFactory.decodeFile(r9, r0);
        r0 = 1;
        r0 = android.graphics.Bitmap.createScaledBitmap(r3, r1, r2, r0);	 Catch:{ all -> 0x00b5 }
        if (r3 == 0) goto L_0x000a;
    L_0x00aa:
        r1 = r3.isRecycled();
        if (r1 != 0) goto L_0x000a;
    L_0x00b0:
        r3.recycle();
        goto L_0x000a;
    L_0x00b5:
        r0 = move-exception;
        if (r3 == 0) goto L_0x00c1;
    L_0x00b8:
        r1 = r3.isRecycled();
        if (r1 != 0) goto L_0x00c1;
    L_0x00be:
        r3.recycle();
    L_0x00c1:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.BitmapStickerDrawable.b(java.lang.String):android.graphics.Bitmap");
    }

    public BitmapStickerDrawable(String str, float f) {
        super(BaseApplication.getAppContext());
        this.b = str;
        this.d = a(str);
        if (this.d != null) {
            float f2 = ((float) BaseApplication.getAppContext().getResources().getDisplayMetrics().widthPixels) * f;
            float height = ((float) this.d.getHeight()) / (((float) this.d.getWidth()) / f2);
            this.f = (int) (f2 + 0.5f);
            this.g = (int) (height + 0.5f);
        } else {
            this.g = 0;
            this.f = 0;
        }
        Rect bounds = getBounds();
        this.e.set(0, 0, bounds.width(), bounds.height());
    }

    public BitmapStickerDrawable(int i, int i2, int i3) {
        super(BaseApplication.getAppContext());
        this.c = i;
        this.d = BitmapFactory.decodeResource(BaseApplication.getAppContext().getResources(), i);
        if (this.d != null) {
            this.f = i2;
            this.g = i3;
        } else {
            this.g = 0;
            this.f = 0;
        }
        Rect bounds = getBounds();
        this.e.set(0, 0, bounds.width(), bounds.height());
    }

    public BitmapStickerDrawable(Context context, JSONObject jSONObject) throws JSONException {
        super(context, jSONObject);
        this.b = jSONObject.optString("b_s_d_s");
        if (TextUtils.isEmpty(this.b)) {
            this.c = jSONObject.getInt("b_s_d_r_i");
            this.d = BitmapFactory.decodeResource(context.getResources(), this.c);
        } else {
            this.d = a(this.b);
        }
        Rect bounds = getBounds();
        this.f = bounds.width();
        this.g = bounds.height();
        this.e.set(0, 0, bounds.width(), bounds.height());
    }

    protected void onBoundsChange(Rect rect) {
        if (this.e != null) {
            this.e.set(0, 0, rect.width(), rect.height());
        }
    }

    public void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        if (TextUtils.isEmpty(this.b)) {
            jSONObject.put("b_s_d_r_i", this.c);
        } else {
            jSONObject.put("b_s_d_s", this.b);
        }
    }

    protected void a(Canvas canvas) {
        if (this.d != null && !this.d.isRecycled()) {
            canvas.save();
            canvas.drawBitmap(this.d, null, this.e, this.a);
            canvas.restore();
        }
    }

    public void setAlpha(int i) {
        this.a.setAlpha(i);
        invalidateSelf();
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public int getOpacity() {
        return -3;
    }

    public int getIntrinsicWidth() {
        return this.f;
    }

    public int getIntrinsicHeight() {
        return this.g;
    }

    public int getMinimumWidth() {
        return this.f;
    }

    public int getMinimumHeight() {
        return this.g;
    }

    private Bitmap a(String str) {
        return b(str);
    }

    public void b() {
        super.b();
        if (this.d != null && !this.d.isRecycled()) {
            this.d.recycle();
        }
    }
}
