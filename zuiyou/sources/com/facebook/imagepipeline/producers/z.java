package com.facebook.imagepipeline.producers;

import com.facebook.common.memory.g;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Executor;

public class z extends y {
    public z(Executor executor, g gVar) {
        super(executor, gVar);
    }

    protected e a(ImageRequest imageRequest) throws IOException {
        return b(new FileInputStream(imageRequest.o().toString()), (int) imageRequest.o().length());
    }

    protected String a() {
        return "LocalFileFetchProducer";
    }
}
