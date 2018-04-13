package com.facebook.imagepipeline.producers;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import com.facebook.common.memory.g;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.IOException;
import java.util.concurrent.Executor;

public class u extends y {
    private final AssetManager a;

    public u(Executor executor, g gVar, AssetManager assetManager) {
        super(executor, gVar);
        this.a = assetManager;
    }

    protected e a(ImageRequest imageRequest) throws IOException {
        return b(this.a.open(c(imageRequest), 2), b(imageRequest));
    }

    private int b(ImageRequest imageRequest) {
        int length;
        Throwable th;
        AssetFileDescriptor openFd;
        try {
            openFd = this.a.openFd(c(imageRequest));
            try {
                length = (int) openFd.getLength();
                if (openFd != null) {
                    try {
                        openFd.close();
                    } catch (IOException e) {
                    }
                }
            } catch (IOException e2) {
                length = -1;
                if (openFd != null) {
                    try {
                        openFd.close();
                    } catch (IOException e3) {
                    }
                }
                return length;
            } catch (Throwable th2) {
                th = th2;
                if (openFd != null) {
                    try {
                        openFd.close();
                    } catch (IOException e4) {
                    }
                }
                throw th;
            }
        } catch (IOException e5) {
            openFd = null;
            length = -1;
            if (openFd != null) {
                openFd.close();
            }
            return length;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            openFd = null;
            th = th4;
            if (openFd != null) {
                openFd.close();
            }
            throw th;
        }
        return length;
    }

    protected String a() {
        return "LocalAssetFetchProducer";
    }

    private static String c(ImageRequest imageRequest) {
        return imageRequest.b().getPath().substring(1);
    }
}
