package com.facebook.imagepipeline.producers;

import java.io.IOException;
import java.io.InputStream;

public interface NetworkFetcher$Callback {
    void onCancellation();

    void onFailure(Throwable th);

    void onResponse(InputStream inputStream, int i) throws IOException;
}
