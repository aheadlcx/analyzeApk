package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.MediaVariations.Variant;
import java.util.Comparator;

@VisibleForTesting
class MediaVariationsFallbackProducer$VariantComparator implements Comparator<Variant> {
    private final ResizeOptions mResizeOptions;

    MediaVariationsFallbackProducer$VariantComparator(ResizeOptions resizeOptions) {
        this.mResizeOptions = resizeOptions;
    }

    public int compare(Variant variant, Variant variant2) {
        boolean access$300 = MediaVariationsFallbackProducer.access$300(variant, this.mResizeOptions);
        boolean access$3002 = MediaVariationsFallbackProducer.access$300(variant2, this.mResizeOptions);
        if (access$300 && access$3002) {
            return variant.getWidth() - variant2.getWidth();
        }
        if (access$300) {
            return -1;
        }
        if (access$3002) {
            return 1;
        }
        return variant2.getWidth() - variant.getWidth();
    }
}
