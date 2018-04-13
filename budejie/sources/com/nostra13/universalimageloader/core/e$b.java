package com.nostra13.universalimageloader.core;

import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import java.io.IOException;
import java.io.InputStream;

class e$b implements ImageDownloader {
    private final ImageDownloader a;

    public e$b(ImageDownloader imageDownloader) {
        this.a = imageDownloader;
    }

    public InputStream getStream(String str, Object obj) throws IOException {
        switch (e$1.a[Scheme.ofUri(str).ordinal()]) {
            case 1:
            case 2:
                throw new IllegalStateException();
            default:
                return this.a.getStream(str, obj);
        }
    }
}
