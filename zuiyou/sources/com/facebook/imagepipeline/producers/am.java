package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import com.facebook.common.memory.g;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.IOException;
import java.util.concurrent.Executor;

public class am extends y {
    private final ContentResolver a;

    public am(Executor executor, g gVar, ContentResolver contentResolver) {
        super(executor, gVar);
        this.a = contentResolver;
    }

    protected e a(ImageRequest imageRequest) throws IOException {
        return b(this.a.openInputStream(imageRequest.b()), -1);
    }

    protected String a() {
        return "QualifiedResourceFetchProducer";
    }
}
