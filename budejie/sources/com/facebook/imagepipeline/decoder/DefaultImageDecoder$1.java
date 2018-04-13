package com.facebook.imagepipeline.decoder;

import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.QualityInfo;

class DefaultImageDecoder$1 implements ImageDecoder {
    final /* synthetic */ DefaultImageDecoder this$0;

    DefaultImageDecoder$1(DefaultImageDecoder defaultImageDecoder) {
        this.this$0 = defaultImageDecoder;
    }

    public CloseableImage decode(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
        ImageFormat imageFormat = encodedImage.getImageFormat();
        if (imageFormat == DefaultImageFormats.JPEG) {
            return this.this$0.decodeJpeg(encodedImage, i, qualityInfo, imageDecodeOptions);
        }
        if (imageFormat == DefaultImageFormats.GIF) {
            return this.this$0.decodeGif(encodedImage, i, qualityInfo, imageDecodeOptions);
        }
        if (imageFormat == DefaultImageFormats.WEBP_ANIMATED) {
            return this.this$0.decodeAnimatedWebp(encodedImage, i, qualityInfo, imageDecodeOptions);
        }
        if (imageFormat != ImageFormat.UNKNOWN) {
            return this.this$0.decodeStaticImage(encodedImage, imageDecodeOptions);
        }
        throw new IllegalArgumentException("unknown image format");
    }
}
