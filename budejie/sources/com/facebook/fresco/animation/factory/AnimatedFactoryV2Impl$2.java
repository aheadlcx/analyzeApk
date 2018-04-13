package com.facebook.fresco.animation.factory;

import android.graphics.Bitmap.Config;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.QualityInfo;

class AnimatedFactoryV2Impl$2 implements ImageDecoder {
    final /* synthetic */ AnimatedFactoryV2Impl this$0;
    final /* synthetic */ Config val$bitmapConfig;

    AnimatedFactoryV2Impl$2(AnimatedFactoryV2Impl animatedFactoryV2Impl, Config config) {
        this.this$0 = animatedFactoryV2Impl;
        this.val$bitmapConfig = config;
    }

    public CloseableImage decode(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
        return AnimatedFactoryV2Impl.access$000(this.this$0).decodeWebP(encodedImage, imageDecodeOptions, this.val$bitmapConfig);
    }
}
