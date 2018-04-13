package cn.xiaochuankeji.tieba.ui.videomaker.sticker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class d {
    private final int a;
    private final int b;
    private final c c;
    private ArrayList<a> d = new ArrayList();

    public d(int i, int i2, float f, ArrayList<a> arrayList) {
        this.a = i;
        this.b = i2;
        this.c = new c(i, i2, f);
        this.d.addAll(arrayList);
    }

    public static d a(Context context, JSONObject jSONObject) {
        try {
            return new d(jSONObject.getInt("view_width"), jSONObject.getInt("view_height"), (float) jSONObject.getDouble("video_aspect_ratio"), e.a(context, jSONObject));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public c a(int i) {
        int i2;
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            if (((a) it.next()).a((long) i)) {
                i2 = 1;
                break;
            }
        }
        i2 = 0;
        if (this.c.b() == 0 || r0 != 0) {
            Bitmap a = this.c.a();
            Canvas canvas = new Canvas(a);
            canvas.drawColor(0, Mode.SRC);
            canvas.translate((float) ((a.getWidth() - this.a) / 2), (float) ((a.getHeight() - this.b) / 2));
            Iterator it2 = this.d.iterator();
            while (it2.hasNext()) {
                ((a) it2.next()).b(canvas, i);
            }
            this.c.c();
        }
        return this.c;
    }
}
