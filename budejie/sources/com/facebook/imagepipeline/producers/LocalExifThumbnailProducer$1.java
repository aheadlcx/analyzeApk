package com.facebook.imagepipeline.producers;

import android.media.ExifInterface;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Map;

class LocalExifThumbnailProducer$1 extends StatefulProducerRunnable<EncodedImage> {
    final /* synthetic */ LocalExifThumbnailProducer this$0;
    final /* synthetic */ ImageRequest val$imageRequest;

    LocalExifThumbnailProducer$1(LocalExifThumbnailProducer localExifThumbnailProducer, Consumer consumer, ProducerListener producerListener, String str, String str2, ImageRequest imageRequest) {
        this.this$0 = localExifThumbnailProducer;
        this.val$imageRequest = imageRequest;
        super(consumer, producerListener, str, str2);
    }

    protected EncodedImage getResult() throws Exception {
        ExifInterface exifInterface = this.this$0.getExifInterface(this.val$imageRequest.getSourceUri());
        if (exifInterface == null || !exifInterface.hasThumbnail()) {
            return null;
        }
        return LocalExifThumbnailProducer.access$100(this.this$0, LocalExifThumbnailProducer.access$000(this.this$0).newByteBuffer(exifInterface.getThumbnail()), exifInterface);
    }

    protected void disposeResult(EncodedImage encodedImage) {
        EncodedImage.closeSafely(encodedImage);
    }

    protected Map<String, String> getExtraMapOnSuccess(EncodedImage encodedImage) {
        return ImmutableMap.of("createdThumbnail", Boolean.toString(encodedImage != null));
    }
}
