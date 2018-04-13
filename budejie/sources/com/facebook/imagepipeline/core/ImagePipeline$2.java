package com.facebook.imagepipeline.core;

import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.request.ImageRequest;

class ImagePipeline$2 implements Supplier<DataSource<CloseableReference<PooledByteBuffer>>> {
    final /* synthetic */ ImagePipeline this$0;
    final /* synthetic */ Object val$callerContext;
    final /* synthetic */ ImageRequest val$imageRequest;

    ImagePipeline$2(ImagePipeline imagePipeline, ImageRequest imageRequest, Object obj) {
        this.this$0 = imagePipeline;
        this.val$imageRequest = imageRequest;
        this.val$callerContext = obj;
    }

    public DataSource<CloseableReference<PooledByteBuffer>> get() {
        return this.this$0.fetchEncodedImage(this.val$imageRequest, this.val$callerContext);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("uri", this.val$imageRequest.getSourceUri()).toString();
    }
}
