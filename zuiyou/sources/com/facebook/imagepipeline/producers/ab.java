package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.references.a;
import com.facebook.imagepipeline.b.g;
import com.facebook.imagepipeline.g.c;
import com.facebook.imagepipeline.g.d;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Map;
import java.util.concurrent.Executor;

public class ab implements ai<a<c>> {
    private final Executor a;

    public ab(Executor executor) {
        this.a = executor;
    }

    public void a(j<a<c>> jVar, aj ajVar) {
        al c = ajVar.c();
        String b = ajVar.b();
        final ImageRequest a = ajVar.a();
        final al alVar = c;
        final String str = b;
        final Runnable anonymousClass1 = new aq<a<c>>(this, jVar, c, "VideoThumbnailProducer", b) {
            final /* synthetic */ ab e;

            protected /* synthetic */ void b(Object obj) {
                c((a) obj);
            }

            protected /* synthetic */ Object c() throws Exception {
                return d();
            }

            protected /* synthetic */ Map c(Object obj) {
                return b((a) obj);
            }

            protected void a(a<c> aVar) {
                super.a((Object) aVar);
                alVar.a(str, "VideoThumbnailProducer", aVar != null);
            }

            protected void a(Exception exception) {
                super.a(exception);
                alVar.a(str, "VideoThumbnailProducer", false);
            }

            protected a<c> d() throws Exception {
                Bitmap createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(a.o().getPath(), ab.b(a));
                if (createVideoThumbnail == null) {
                    return null;
                }
                return a.a(new d(createVideoThumbnail, g.a(), com.facebook.imagepipeline.g.g.a, 0));
            }

            protected Map<String, String> b(a<c> aVar) {
                return ImmutableMap.of("createdThumbnail", String.valueOf(aVar != null));
            }

            protected void c(a<c> aVar) {
                a.c(aVar);
            }
        };
        ajVar.a(new e(this) {
            final /* synthetic */ ab b;

            public void a() {
                anonymousClass1.a();
            }
        });
        this.a.execute(anonymousClass1);
    }

    private static int b(ImageRequest imageRequest) {
        if (imageRequest.e() > 96 || imageRequest.f() > 96) {
            return 1;
        }
        return 3;
    }
}
