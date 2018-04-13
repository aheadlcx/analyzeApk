package cn.tatagou.sdk.util;

import android.content.Context;
import com.bumptech.glide.e.a;
import com.bumptech.glide.i;
import com.bumptech.glide.j;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.b.f;
import com.bumptech.glide.load.engine.b.g;

public class CustomGlideModule implements a {
    public void applyOptions(Context context, j jVar) {
        jVar.a(DecodeFormat.PREFER_ARGB_8888);
        com.bumptech.glide.load.engine.b.j jVar2 = new com.bumptech.glide.load.engine.b.j(context);
        int a = jVar2.a() * 3;
        int b = jVar2.b() * 3;
        jVar.a(new g(context, a));
        jVar.a(new f(context, b));
    }

    public void registerComponents(Context context, i iVar) {
    }
}
