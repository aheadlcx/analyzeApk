package com.facebook.imagepipeline.producers;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.IOException;
import java.util.concurrent.Executor;

public class LocalResourceFetchProducer extends LocalFetchProducer {
    public static final String PRODUCER_NAME = "LocalResourceFetchProducer";
    private final Resources mResources;

    public LocalResourceFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, Resources resources) {
        super(executor, pooledByteBufferFactory);
        this.mResources = resources;
    }

    protected EncodedImage getEncodedImage(ImageRequest imageRequest) throws IOException {
        return getEncodedImage(this.mResources.openRawResource(getResourceId(imageRequest)), getLength(imageRequest));
    }

    private int getLength(ImageRequest imageRequest) {
        int length;
        Throwable th;
        AssetFileDescriptor openRawResourceFd;
        try {
            openRawResourceFd = this.mResources.openRawResourceFd(getResourceId(imageRequest));
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

    protected String getProducerName() {
        return PRODUCER_NAME;
    }

    private static int getResourceId(ImageRequest imageRequest) {
        return Integer.parseInt(imageRequest.getSourceUri().getPath().substring(1));
    }
}
