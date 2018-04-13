package com.facebook.imageformat;

import com.facebook.common.internal.ByteStreams;
import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.imageformat.ImageFormat.FormatChecker;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.annotation.Nullable;

public class ImageFormatChecker {
    private static ImageFormatChecker sInstance;
    @Nullable
    private List<FormatChecker> mCustomImageFormatCheckers;
    private final FormatChecker mDefaultFormatChecker = new DefaultImageFormatChecker();
    private int mMaxHeaderLength;

    private ImageFormatChecker() {
        updateMaxHeaderLength();
    }

    public void setCustomImageFormatCheckers(@Nullable List<FormatChecker> list) {
        this.mCustomImageFormatCheckers = list;
        updateMaxHeaderLength();
    }

    public ImageFormat determineImageFormat(InputStream inputStream) throws IOException {
        ImageFormat determineFormat;
        Preconditions.checkNotNull(inputStream);
        byte[] bArr = new byte[this.mMaxHeaderLength];
        int readHeaderFromStream = readHeaderFromStream(this.mMaxHeaderLength, inputStream, bArr);
        if (this.mCustomImageFormatCheckers != null) {
            for (FormatChecker determineFormat2 : this.mCustomImageFormatCheckers) {
                determineFormat = determineFormat2.determineFormat(bArr, readHeaderFromStream);
                if (determineFormat != null && determineFormat != ImageFormat.UNKNOWN) {
                    return determineFormat;
                }
            }
        }
        determineFormat = this.mDefaultFormatChecker.determineFormat(bArr, readHeaderFromStream);
        if (determineFormat == null) {
            return ImageFormat.UNKNOWN;
        }
        return determineFormat;
    }

    private void updateMaxHeaderLength() {
        this.mMaxHeaderLength = this.mDefaultFormatChecker.getHeaderSize();
        if (this.mCustomImageFormatCheckers != null) {
            for (FormatChecker headerSize : this.mCustomImageFormatCheckers) {
                this.mMaxHeaderLength = Math.max(this.mMaxHeaderLength, headerSize.getHeaderSize());
            }
        }
    }

    private static int readHeaderFromStream(int i, InputStream inputStream, byte[] bArr) throws IOException {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(bArr);
        Preconditions.checkArgument(bArr.length >= i);
        if (!inputStream.markSupported()) {
            return ByteStreams.read(inputStream, bArr, 0, i);
        }
        try {
            inputStream.mark(i);
            int read = ByteStreams.read(inputStream, bArr, 0, i);
            return read;
        } finally {
            inputStream.reset();
        }
    }

    public static synchronized ImageFormatChecker getInstance() {
        ImageFormatChecker imageFormatChecker;
        synchronized (ImageFormatChecker.class) {
            if (sInstance == null) {
                sInstance = new ImageFormatChecker();
            }
            imageFormatChecker = sInstance;
        }
        return imageFormatChecker;
    }

    public static ImageFormat getImageFormat(InputStream inputStream) throws IOException {
        return getInstance().determineImageFormat(inputStream);
    }

    public static ImageFormat getImageFormat_WrapIOException(InputStream inputStream) {
        try {
            return getImageFormat(inputStream);
        } catch (Throwable e) {
            throw Throwables.propagate(e);
        }
    }

    public static ImageFormat getImageFormat(String str) {
        InputStream fileInputStream;
        ImageFormat imageFormat;
        Throwable th;
        try {
            fileInputStream = new FileInputStream(str);
            try {
                imageFormat = getImageFormat(fileInputStream);
                Closeables.closeQuietly(fileInputStream);
            } catch (IOException e) {
                try {
                    imageFormat = ImageFormat.UNKNOWN;
                    Closeables.closeQuietly(fileInputStream);
                    return imageFormat;
                } catch (Throwable th2) {
                    th = th2;
                    Closeables.closeQuietly(fileInputStream);
                    throw th;
                }
            }
        } catch (IOException e2) {
            fileInputStream = null;
            imageFormat = ImageFormat.UNKNOWN;
            Closeables.closeQuietly(fileInputStream);
            return imageFormat;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            fileInputStream = null;
            th = th4;
            Closeables.closeQuietly(fileInputStream);
            throw th;
        }
        return imageFormat;
    }
}
