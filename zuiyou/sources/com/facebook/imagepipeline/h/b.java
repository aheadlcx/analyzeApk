package com.facebook.imagepipeline.h;

import com.facebook.imagepipeline.producers.al;
import com.facebook.imagepipeline.request.ImageRequest;

public interface b extends al {
    void a(ImageRequest imageRequest, Object obj, String str, boolean z);

    void a(ImageRequest imageRequest, String str, Throwable th, boolean z);

    void a(ImageRequest imageRequest, String str, boolean z);

    void a(String str);
}
