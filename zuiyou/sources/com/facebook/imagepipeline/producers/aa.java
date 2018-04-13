package com.facebook.imagepipeline.producers;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import com.facebook.common.memory.g;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.IOException;
import java.util.concurrent.Executor;

public class aa extends y {
    private final Resources a;

    public aa(Executor executor, g gVar, Resources resources) {
        super(executor, gVar);
        this.a = resources;
    }

    protected e a(ImageRequest imageRequest) throws IOException {
        return b(this.a.openRawResource(c(imageRequest)), b(imageRequest));
    }

    private int b(ImageRequest imageRequest) {
        int length;
        Throwable th;
        AssetFileDescriptor openRawResourceFd;
        try {
            openRawResourceFd = this.a.openRawResourceFd(c(imageRequest));
            try {
                length = (int) openRawResourceFd.getLength();
                if (openRawResourceFd != null) {
                    try {
                        openRawResourceFd.close();
                    } catch (IOException e) {
                    }
                }
            } catch (NotFoundException e2) {
                length = -1;
                if (openRawResourceFd != null) {
                    try {
                        openRawResourceFd.close();
                    } catch (IOException e3) {
                    }
                }
                return length;
            } catch (Throwable th2) {
                th = th2;
                if (openRawResourceFd != null) {
                    try {
                        openRawResourceFd.close();
                    } catch (IOException e4) {
                    }
                }
                throw th;
            }
        } catch (NotFoundException e5) {
            openRawResourceFd = null;
            length = -1;
            if (openRawResourceFd != null) {
                openRawResourceFd.close();
            }
            return length;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            openRawResourceFd = null;
            th = th4;
            if (openRawResourceFd != null) {
                openRawResourceFd.close();
            }
            throw th;
        }
        return length;
    }

    protected String a() {
        return "LocalResourceFetchProducer";
    }

    private static int c(ImageRequest imageRequest) {
        return Integer.parseInt(imageRequest.b().getPath().substring(1));
    }
}
