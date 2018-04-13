package com.facebook.drawee.a.a;

import android.content.Context;
import android.content.res.Resources;
import com.facebook.common.internal.ImmutableList;
import com.facebook.common.internal.i;
import com.facebook.drawee.controller.c;
import com.facebook.imagepipeline.animated.factory.a;
import com.facebook.imagepipeline.c.t;
import com.facebook.imagepipeline.d.g;
import com.facebook.imagepipeline.d.j;
import java.util.Set;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class f implements i<e> {
    private final Context a;
    private final g b;
    private final g c;
    private final Set<c> d;

    public /* synthetic */ Object b() {
        return a();
    }

    public f(Context context) {
        this(context, null);
    }

    public f(Context context, @Nullable b bVar) {
        this(context, j.a(), bVar);
    }

    public f(Context context, j jVar, @Nullable b bVar) {
        this(context, jVar, null, bVar);
    }

    public f(Context context, j jVar, Set<c> set, @Nullable b bVar) {
        a a;
        ImmutableList a2;
        i iVar = null;
        this.a = context;
        this.b = jVar.i();
        com.facebook.imagepipeline.animated.factory.c b = jVar.b();
        if (b != null) {
            a = b.a(context);
        } else {
            a = null;
        }
        if (bVar == null || bVar.b() == null) {
            this.c = new g();
        } else {
            this.c = bVar.b();
        }
        g gVar = this.c;
        Resources resources = context.getResources();
        com.facebook.drawee.components.a a3 = com.facebook.drawee.components.a.a();
        Executor b2 = com.facebook.common.b.i.b();
        t b3 = this.b.b();
        if (bVar != null) {
            a2 = bVar.a();
        } else {
            a2 = null;
        }
        if (bVar != null) {
            iVar = bVar.c();
        }
        gVar.a(resources, a3, a, b2, b3, a2, iVar);
        this.d = set;
    }

    public e a() {
        return new e(this.a, this.c, this.b, this.d);
    }
}
