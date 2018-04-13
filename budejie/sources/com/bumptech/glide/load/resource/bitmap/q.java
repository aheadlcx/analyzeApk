package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.a.c;
import java.io.IOException;

public class q implements a<ParcelFileDescriptor> {
    private static final a a = new a();
    private a b;
    private int c;

    static class a {
        a() {
        }

        public MediaMetadataRetriever a() {
            return new MediaMetadataRetriever();
        }
    }

    public q() {
        this(a, -1);
    }

    q(a aVar, int i) {
        this.b = aVar;
        this.c = i;
    }

    public Bitmap a(ParcelFileDescriptor parcelFileDescriptor, c cVar, int i, int i2, DecodeFormat decodeFormat) throws IOException {
        Bitmap frameAtTime;
        MediaMetadataRetriever a = this.b.a();
        a.setDataSource(parcelFileDescriptor.getFileDescriptor());
        if (this.c >= 0) {
            frameAtTime = a.getFrameAtTime((long) this.c);
        } else {
            frameAtTime = a.getFrameAtTime();
        }
        a.release();
        parcelFileDescriptor.close();
        return frameAtTime;
    }

    public String a() {
        return "VideoBitmapDecoder.com.bumptech.glide.load.resource.bitmap";
    }
}
