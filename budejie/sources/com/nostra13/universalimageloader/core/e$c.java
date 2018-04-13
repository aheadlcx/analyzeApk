package com.nostra13.universalimageloader.core;

import com.nostra13.universalimageloader.core.assist.b;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import java.io.IOException;
import java.io.InputStream;

class e$c implements ImageDownloader {
    private final ImageDownloader a;

    public e$c(ImageDownloader imageDownloader) {
        this.a = imageDownloader;
    }

    public InputStream getStream(String str, Object obj) throws IOException {
        InputStream stream = this.a.getStream(str, obj);
        switch (e$1.a[Scheme.ofUri(str).ordinal()]) {
            case 1:
            case 2:
                return new b(stream);
            default:
                return stream;
        }
    }
}
