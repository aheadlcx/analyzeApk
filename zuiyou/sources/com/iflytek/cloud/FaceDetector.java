package com.iflytek.cloud;

import android.content.Context;
import android.graphics.Bitmap;
import com.iflytek.cloud.thirdparty.bh;
import com.iflytek.cloud.thirdparty.ch;

public class FaceDetector extends bh {
    private static FaceDetector a;
    private ch d;

    private FaceDetector(Context context) throws SpeechError {
        try {
            this.d = new ch(context, null);
        } catch (UnsatisfiedLinkError e) {
            throw new SpeechError(20021);
        }
    }

    public static synchronized FaceDetector createDetector(Context context, String str) throws SpeechError {
        FaceDetector faceDetector;
        synchronized (FaceDetector.class) {
            synchronized (b) {
                if (a == null && SpeechUtility.getUtility() != null) {
                    a = new FaceDetector(context);
                }
                faceDetector = a;
            }
        }
        return faceDetector;
    }

    public static synchronized FaceDetector getDetector() {
        FaceDetector faceDetector;
        synchronized (FaceDetector.class) {
            faceDetector = a;
        }
        return faceDetector;
    }

    public synchronized boolean destroy() {
        ch chVar = this.d;
        synchronized (this) {
            this.d = null;
        }
        if (chVar != null) {
            chVar.a();
        }
        boolean destroy = super.destroy();
        if (destroy) {
            synchronized (b) {
                a = null;
            }
        }
        return destroy;
    }

    public synchronized String detectARGB(Bitmap bitmap) {
        String str;
        str = null;
        synchronized (this) {
            if (this.d != null) {
                str = this.d.a(bitmap);
            }
        }
        return str;
    }

    public synchronized String detectGray(Bitmap bitmap) {
        String str;
        str = null;
        synchronized (this) {
            if (this.d != null) {
                str = this.d.b(bitmap);
            }
        }
        return str;
    }

    public synchronized String trackNV21(byte[] bArr, int i, int i2, int i3, int i4) {
        String str;
        str = null;
        synchronized (this) {
            if (this.d != null) {
                str = this.d.a(bArr, i, i2, i3, i4);
            }
        }
        return str;
    }
}
