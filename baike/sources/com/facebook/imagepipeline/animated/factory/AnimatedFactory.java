package com.facebook.imagepipeline.animated.factory;

import android.content.Context;
import android.graphics.Bitmap.Config;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public interface AnimatedFactory {
    @Nullable
    DrawableFactory getAnimatedDrawableFactory(Context context);

    @Nullable
    ImageDecoder getGifDecoder(Config config);

    @Nullable
    ImageDecoder getWebPDecoder(Config config);
}
