package cn.xiaochuankeji.tieba.ui.videomaker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.c.a.e;
import cn.xiaochuankeji.tieba.c.a.f;
import java.util.ArrayList;

public class g {
    public static final ArrayList<c> a = new ArrayList();

    public static abstract class c {
        public String a;
        public int b;

        public abstract e a(Context context);

        public c(String str, int i) {
            this.a = str;
            this.b = i;
        }
    }

    private static class a extends c {
        private int c;
        private int d;

        a(String str, int i, int i2, int i3) {
            super(str, i);
            this.c = i2;
            this.d = i3;
        }

        public e a(Context context) {
            e eVar = new e(new ArrayList());
            cn.xiaochuankeji.tieba.c.a.b bVar = new cn.xiaochuankeji.tieba.c.a.b();
            bVar.a(BitmapFactory.decodeResource(context.getResources(), this.c), true);
            eVar.j().add(bVar);
            f fVar = new f(1.0f);
            Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), this.d);
            if (decodeResource != null) {
                fVar.a(decodeResource, false);
                eVar.j().add(fVar);
            }
            eVar.i();
            return eVar;
        }
    }

    private static class b extends c {
        private int c;

        b(String str, int i, int i2) {
            super(str, i);
            this.c = i2;
        }

        public e a(Context context) {
            e eVar = new e(new ArrayList());
            f fVar = new f(1.0f);
            Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), this.c);
            if (decodeResource != null) {
                fVar.a(decodeResource, false);
                eVar.j().add(fVar);
            }
            eVar.i();
            return eVar;
        }
    }

    static {
        a.add(new b("原图", R.drawable.img_video_filter_original, R.drawable.lookup_origin));
        a.add(new b("Pure", R.drawable.img_video_filter_pure, R.drawable.lookup_pure));
        a.add(new b("Blossom", R.drawable.img_video_filter_blossom, R.drawable.lookup_blossom));
        a.add(new b("Jasper", R.drawable.img_video_filter_jasper, R.drawable.lookup_jasper));
        a.add(new b("Cake", R.drawable.img_video_filter_cake, R.drawable.lookup_cake));
        a.add(new b("Kiss", R.drawable.img_video_filter_kiss, R.drawable.lookup_kiss));
        a.add(new b("Coffee", R.drawable.img_video_filter_coffee, R.drawable.lookup_coffee));
        a.add(new a("Vintage", R.drawable.img_video_filter_vintage, R.drawable.vintage_overlay, R.drawable.lookup_vintage));
        a.add(new b("Black", R.drawable.img_video_filter_black, R.drawable.lookup_black));
    }
}
