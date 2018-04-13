package com.facebook.imagepipeline.common;

import android.graphics.Bitmap.Config;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import java.util.Locale;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class ImageDecodeOptions {
    private static final ImageDecodeOptions DEFAULTS = newBuilder().build();
    public final Config bitmapConfig;
    @Nullable
    public final ImageDecoder customImageDecoder;
    public final boolean decodeAllFrames;
    public final boolean decodePreviewFrame;
    public final boolean forceStaticImage;
    public final int minDecodeIntervalMs;
    public final boolean useLastFrameForPreview;

    public ImageDecodeOptions(ImageDecodeOptionsBuilder imageDecodeOptionsBuilder) {
        this.minDecodeIntervalMs = imageDecodeOptionsBuilder.getMinDecodeIntervalMs();
        this.decodePreviewFrame = imageDecodeOptionsBuilder.getDecodePreviewFrame();
        this.useLastFrameForPreview = imageDecodeOptionsBuilder.getUseLastFrameForPreview();
        this.decodeAllFrames = imageDecodeOptionsBuilder.getDecodeAllFrames();
        this.forceStaticImage = imageDecodeOptionsBuilder.getForceStaticImage();
        this.bitmapConfig = imageDecodeOptionsBuilder.getBitmapConfig();
        this.customImageDecoder = imageDecodeOptionsBuilder.getCustomImageDecoder();
    }

    public static ImageDecodeOptions defaults() {
        return DEFAULTS;
    }

    public static ImageDecodeOptionsBuilder newBuilder() {
        return new ImageDecodeOptionsBuilder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ImageDecodeOptions imageDecodeOptions = (ImageDecodeOptions) obj;
        if (this.decodePreviewFrame != imageDecodeOptions.decodePreviewFrame) {
            return false;
        }
        if (this.useLastFrameForPreview != imageDecodeOptions.useLastFrameForPreview) {
            return false;
        }
        if (this.decodeAllFrames != imageDecodeOptions.decodeAllFrames) {
            return false;
        }
        if (this.forceStaticImage != imageDecodeOptions.forceStaticImage) {
            return false;
        }
        if (this.bitmapConfig != imageDecodeOptions.bitmapConfig) {
            return false;
        }
        if (this.customImageDecoder != imageDecodeOptions.customImageDecoder) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i;
        int i2 = 1;
        int i3 = 0;
        int i4 = ((this.decodePreviewFrame ? 1 : 0) + (this.minDecodeIntervalMs * 31)) * 31;
        if (this.useLastFrameForPreview) {
            i = 1;
        } else {
            i = 0;
        }
        i4 = (i + i4) * 31;
        if (this.decodeAllFrames) {
            i = 1;
        } else {
            i = 0;
        }
        i = (i + i4) * 31;
        if (!this.forceStaticImage) {
            i2 = 0;
        }
        i = (((i + i2) * 31) + this.bitmapConfig.ordinal()) * 31;
        if (this.customImageDecoder != null) {
            i3 = this.customImageDecoder.hashCode();
        }
        return i + i3;
    }

    public String toString() {
        return String.format((Locale) null, "%d-%b-%b-%b-%b-%s-%s", new Object[]{Integer.valueOf(this.minDecodeIntervalMs), Boolean.valueOf(this.decodePreviewFrame), Boolean.valueOf(this.useLastFrameForPreview), Boolean.valueOf(this.decodeAllFrames), Boolean.valueOf(this.forceStaticImage), this.bitmapConfig.name(), this.customImageDecoder});
    }
}
