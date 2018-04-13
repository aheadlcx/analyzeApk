package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;

public class DownsampleUtil {
    public static final int DEFAULT_SAMPLE_SIZE = 1;
    private static final float INTERVAL_ROUNDING = 0.33333334f;

    private DownsampleUtil() {
    }

    public static int determineSampleSize(ImageRequest imageRequest, EncodedImage encodedImage) {
        if (!EncodedImage.isMetaDataAvailable(encodedImage)) {
            return 1;
        }
        int ratioToSampleSizeJPEG;
        float determineDownsampleRatio = determineDownsampleRatio(imageRequest, encodedImage);
        if (encodedImage.getImageFormat() == DefaultImageFormats.JPEG) {
            ratioToSampleSizeJPEG = ratioToSampleSizeJPEG(determineDownsampleRatio);
        } else {
            ratioToSampleSizeJPEG = ratioToSampleSize(determineDownsampleRatio);
        }
        int max = Math.max(encodedImage.getHeight(), encodedImage.getWidth());
        ResizeOptions resizeOptions = imageRequest.getResizeOptions();
        float f = resizeOptions != null ? resizeOptions.maxBitmapSize : 2048.0f;
        while (((float) (max / ratioToSampleSizeJPEG)) > f) {
            if (encodedImage.getImageFormat() == DefaultImageFormats.JPEG) {
                ratioToSampleSizeJPEG *= 2;
            } else {
                ratioToSampleSizeJPEG++;
            }
        }
        return ratioToSampleSizeJPEG;
    }

    @VisibleForTesting
    static float determineDownsampleRatio(ImageRequest imageRequest, EncodedImage encodedImage) {
        Preconditions.checkArgument(EncodedImage.isMetaDataAvailable(encodedImage));
        ResizeOptions resizeOptions = imageRequest.getResizeOptions();
        if (resizeOptions == null || resizeOptions.height <= 0 || resizeOptions.width <= 0 || encodedImage.getWidth() == 0 || encodedImage.getHeight() == 0) {
            return 1.0f;
        }
        int rotationAngle = getRotationAngle(imageRequest, encodedImage);
        int i = (rotationAngle == 90 || rotationAngle == 270) ? 1 : 0;
        FLog.v("DownsampleUtil", "Downsample - Specified size: %dx%d, image size: %dx%d ratio: %.1f x %.1f, ratio: %.3f for %s", Integer.valueOf(resizeOptions.width), Integer.valueOf(resizeOptions.height), Integer.valueOf(i != 0 ? encodedImage.getHeight() : encodedImage.getWidth()), Integer.valueOf(i != 0 ? encodedImage.getWidth() : encodedImage.getHeight()), Float.valueOf(((float) resizeOptions.width) / ((float) (i != 0 ? encodedImage.getHeight() : encodedImage.getWidth()))), Float.valueOf(((float) resizeOptions.height) / ((float) (i != 0 ? encodedImage.getWidth() : encodedImage.getHeight()))), Float.valueOf(Math.max(((float) resizeOptions.width) / ((float) (i != 0 ? encodedImage.getHeight() : encodedImage.getWidth())), ((float) resizeOptions.height) / ((float) (i != 0 ? encodedImage.getWidth() : encodedImage.getHeight())))), imageRequest.getSourceUri().toString());
        return Math.max(((float) resizeOptions.width) / ((float) (i != 0 ? encodedImage.getHeight() : encodedImage.getWidth())), ((float) resizeOptions.height) / ((float) (i != 0 ? encodedImage.getWidth() : encodedImage.getHeight())));
    }

    @VisibleForTesting
    static int ratioToSampleSize(float f) {
        if (f > 0.6666667f) {
            return 1;
        }
        int i = 2;
        while (true) {
            if (((1.0d / (Math.pow((double) i, 2.0d) - ((double) i))) * 0.3333333432674408d) + (1.0d / ((double) i)) <= ((double) f)) {
                return i - 1;
            }
            i++;
        }
    }

    @VisibleForTesting
    static int ratioToSampleSizeJPEG(float f) {
        if (f > 0.6666667f) {
            return 1;
        }
        int i = 2;
        while (true) {
            if (((1.0d / ((double) (i * 2))) * 0.3333333432674408d) + (1.0d / ((double) (i * 2))) <= ((double) f)) {
                return i;
            }
            i *= 2;
        }
    }

    private static int getRotationAngle(ImageRequest imageRequest, EncodedImage encodedImage) {
        boolean z = false;
        if (!imageRequest.getRotationOptions().useImageMetadata()) {
            return 0;
        }
        int rotationAngle = encodedImage.getRotationAngle();
        if (rotationAngle == 0 || rotationAngle == 90 || rotationAngle == 180 || rotationAngle == 270) {
            z = true;
        }
        Preconditions.checkArgument(z);
        return rotationAngle;
    }

    @VisibleForTesting
    static int roundToPowerOfTwo(int i) {
        int i2 = 1;
        while (i2 < i) {
            i2 *= 2;
        }
        return i2;
    }
}
