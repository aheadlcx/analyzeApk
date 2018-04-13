package cn.xiaochuankeji.tieba.ui.videomaker.edittext;

import android.graphics.Color;
import android.graphics.Rect;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewCompat;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.push.proto.Push.Packet;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class e {
    private static ArrayList<b> a = new ArrayList();

    public static class a {
        public float a;
        public float b;
        public int c;

        public void a(JSONObject jSONObject) throws JSONException {
            jSONObject.put("shadow_ratio_x", (double) this.a);
            jSONObject.put("shadow_ratio_y", (double) this.b);
            jSONObject.put("color", this.c);
        }

        public a(JSONObject jSONObject) throws JSONException {
            this.a = (float) jSONObject.getDouble("shadow_ratio_x");
            this.b = (float) jSONObject.getDouble("shadow_ratio_y");
            this.c = jSONObject.getInt("color");
        }
    }

    public static class b {
        public int a;
        public int b;
        public int c;
        public int d;
        public int e;
        public Rect f;
        public Rect g;
        public int h;
        public int i;
        public int j;
        public a k;
        public int l;
        public int m = 1;
    }

    static {
        b bVar = new b();
        bVar.a = Packet.CLIENTVER_FIELD_NUMBER;
        bVar.b = R.drawable.img_template_text_thumb_4;
        bVar.d = cn.xiaochuankeji.tieba.ui.utils.e.a(187.0f);
        bVar.e = cn.xiaochuankeji.tieba.ui.utils.e.a(53.0f);
        bVar.h = 1;
        bVar.i = -1;
        bVar.l = 2;
        a aVar = new a();
        aVar.c = ViewCompat.MEASURED_STATE_MASK;
        aVar.a = -0.04f;
        aVar.b = 0.09f;
        bVar.k = aVar;
        bVar.j = ViewCompat.MEASURED_STATE_MASK;
        a.add(bVar);
        bVar = new b();
        bVar.a = 1006;
        bVar.b = R.drawable.img_template_text_thumb_5;
        bVar.c = R.drawable.template_text_5;
        bVar.d = cn.xiaochuankeji.tieba.ui.utils.e.a(228.0f);
        bVar.e = cn.xiaochuankeji.tieba.ui.utils.e.a(66.0f);
        bVar.g = new Rect(cn.xiaochuankeji.tieba.ui.utils.e.a(24.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(5.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(24.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(10.0f));
        bVar.h = 1;
        bVar.i = Color.parseColor("#2DB35E");
        bVar.l = 2;
        a.add(bVar);
        bVar = new b();
        bVar.a = 1003;
        bVar.b = R.drawable.img_template_text_thumb_2;
        bVar.c = R.drawable.template_text_2;
        bVar.d = cn.xiaochuankeji.tieba.ui.utils.e.a(164.0f);
        bVar.e = cn.xiaochuankeji.tieba.ui.utils.e.a(86.0f);
        bVar.g = new Rect(cn.xiaochuankeji.tieba.ui.utils.e.a(13.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(41.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(21.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(13.0f));
        bVar.h = 0;
        bVar.i = -1;
        bVar.l = 2;
        a.add(bVar);
        bVar = new b();
        bVar.a = 1004;
        bVar.b = R.drawable.img_template_text_thumb_3;
        bVar.c = R.drawable.template_text_3;
        bVar.d = cn.xiaochuankeji.tieba.ui.utils.e.a(135.0f);
        bVar.e = cn.xiaochuankeji.tieba.ui.utils.e.a(120.0f);
        bVar.g = new Rect(cn.xiaochuankeji.tieba.ui.utils.e.a(19.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(29.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(9.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(20.0f));
        bVar.h = 2;
        bVar.i = -1;
        bVar.l = 3;
        a.add(bVar);
        bVar = new b();
        bVar.a = 1002;
        bVar.b = R.drawable.img_template_text_thumb_1;
        bVar.c = R.drawable.template_text_1;
        bVar.d = cn.xiaochuankeji.tieba.ui.utils.e.a(135.0f);
        bVar.e = cn.xiaochuankeji.tieba.ui.utils.e.a(120.0f);
        bVar.g = new Rect(0, cn.xiaochuankeji.tieba.ui.utils.e.a(40.0f), 0, cn.xiaochuankeji.tieba.ui.utils.e.a(38.0f));
        bVar.f = new Rect(cn.xiaochuankeji.tieba.ui.utils.e.a(14.0f), 0, cn.xiaochuankeji.tieba.ui.utils.e.a(15.0f), 0);
        bVar.h = 1;
        bVar.i = -1;
        bVar.l = 2;
        a.add(bVar);
        bVar = new b();
        bVar.a = PointerIconCompat.TYPE_VERTICAL_TEXT;
        bVar.b = R.drawable.img_template_text_thumb_8;
        bVar.c = R.drawable.template_text_8;
        bVar.d = cn.xiaochuankeji.tieba.ui.utils.e.a(176.0f);
        bVar.e = cn.xiaochuankeji.tieba.ui.utils.e.a(133.0f);
        bVar.g = new Rect(cn.xiaochuankeji.tieba.ui.utils.e.a(22.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(42.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(17.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(47.0f));
        bVar.h = 1;
        bVar.i = ViewCompat.MEASURED_STATE_MASK;
        bVar.j = -1;
        aVar = new a();
        aVar.c = -4384082;
        aVar.a = 0.0f;
        aVar.b = 0.143f;
        bVar.k = aVar;
        bVar.l = 2;
        a.add(bVar);
        bVar = new b();
        bVar.a = PointerIconCompat.TYPE_TEXT;
        bVar.b = R.drawable.img_template_text_thumb_7;
        bVar.c = R.drawable.template_text_7;
        bVar.d = cn.xiaochuankeji.tieba.ui.utils.e.a(217.0f);
        bVar.e = cn.xiaochuankeji.tieba.ui.utils.e.a(56.0f);
        bVar.g = new Rect(cn.xiaochuankeji.tieba.ui.utils.e.a(32.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(7.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(32.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(7.0f));
        bVar.h = 1;
        bVar.i = -1;
        bVar.l = 2;
        a.add(bVar);
        bVar = new b();
        bVar.a = 1001;
        bVar.b = R.drawable.img_template_text_thumb_0;
        bVar.c = R.drawable.template_text_0;
        bVar.d = cn.xiaochuankeji.tieba.ui.utils.e.a(177.0f);
        bVar.e = cn.xiaochuankeji.tieba.ui.utils.e.a(99.0f);
        bVar.g = new Rect(cn.xiaochuankeji.tieba.ui.utils.e.a(21.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(16.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(17.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(45.0f));
        bVar.h = 1;
        bVar.i = Color.parseColor("#FFEF01");
        bVar.l = 2;
        a.add(bVar);
        bVar = new b();
        bVar.a = PointerIconCompat.TYPE_CROSSHAIR;
        bVar.b = R.drawable.img_template_text_thumb_6;
        bVar.c = R.drawable.template_text_6;
        bVar.d = cn.xiaochuankeji.tieba.ui.utils.e.a(143.0f);
        bVar.e = cn.xiaochuankeji.tieba.ui.utils.e.a(127.0f);
        bVar.g = new Rect(cn.xiaochuankeji.tieba.ui.utils.e.a(34.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(22.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(19.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(22.0f));
        bVar.h = 2;
        bVar.i = ViewCompat.MEASURED_STATE_MASK;
        bVar.l = 4;
        a.add(bVar);
        bVar = new b();
        bVar.a = PointerIconCompat.TYPE_ALIAS;
        bVar.b = R.drawable.img_template_text_thumb_9;
        bVar.c = R.drawable.template_text_9;
        bVar.d = cn.xiaochuankeji.tieba.ui.utils.e.a(176.0f);
        bVar.e = cn.xiaochuankeji.tieba.ui.utils.e.a(108.0f);
        bVar.g = new Rect(cn.xiaochuankeji.tieba.ui.utils.e.a(39.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(25.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(33.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(42.0f));
        bVar.h = 0;
        bVar.i = Color.parseColor("#333333");
        bVar.l = 2;
        a.add(bVar);
        bVar = new b();
        bVar.a = 1011;
        bVar.b = R.drawable.img_template_text_thumb_10;
        bVar.c = R.drawable.template_text_10;
        bVar.d = cn.xiaochuankeji.tieba.ui.utils.e.a(164.0f);
        bVar.e = cn.xiaochuankeji.tieba.ui.utils.e.a(83.0f);
        bVar.g = new Rect(cn.xiaochuankeji.tieba.ui.utils.e.a(25.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(8.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(25.0f), cn.xiaochuankeji.tieba.ui.utils.e.a(8.0f));
        bVar.h = 0;
        bVar.i = Color.parseColor("#f9f417");
        aVar = new a();
        aVar.c = -4749047;
        aVar.b = 0.07f;
        aVar.a = 0.07f;
        bVar.k = aVar;
        bVar.l = 3;
        a.add(bVar);
    }

    public static ArrayList<b> a() {
        return a;
    }
}
