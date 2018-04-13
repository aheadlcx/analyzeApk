package com.facebook.imagepipeline.core;

import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Supplier;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;

class ImagePipeline$1 implements Supplier<DataSource<CloseableReference<CloseableImage>>> {
    final /* synthetic */ ImagePipeline this$0;
    final /* synthetic */ Object val$callerContext;
    final /* synthetic */ ImageRequest val$imageRequest;
    final /* synthetic */ ImageRequest$RequestLevel val$requestLevel;

    ImagePipeline$1(ImagePipeline imagePipeline, ImageRequest imageRequest, Object obj, ImageRequest$RequestLevel imageRequest$RequestLevel) {
        this.this$0 = imagePipeline;
        this.val$imageRequest = imageRequest;
        this.val$callerContext = obj;
        this.val$requestLevel = imageRequest$RequestLevel;
    }

    public DataSource<CloseableReference<CloseableImage>> get() {
        return this.this$0.fetchDecodedImage(this.val$imageRequest, this.val$callerContext, this.val$requestLevel);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("uri", this.val$imageRequest.getSourceUri()).toString();
    }
}
