package com.facebook.drawee.controller;

import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Supplier;
import com.facebook.datasource.DataSource;

class AbstractDraweeControllerBuilder$2 implements Supplier<DataSource<IMAGE>> {
    final /* synthetic */ AbstractDraweeControllerBuilder this$0;
    final /* synthetic */ AbstractDraweeControllerBuilder$CacheLevel val$cacheLevel;
    final /* synthetic */ Object val$callerContext;
    final /* synthetic */ Object val$imageRequest;

    AbstractDraweeControllerBuilder$2(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, Object obj, Object obj2, AbstractDraweeControllerBuilder$CacheLevel abstractDraweeControllerBuilder$CacheLevel) {
        this.this$0 = abstractDraweeControllerBuilder;
        this.val$imageRequest = obj;
        this.val$callerContext = obj2;
        this.val$cacheLevel = abstractDraweeControllerBuilder$CacheLevel;
    }

    public DataSource<IMAGE> get() {
        return this.this$0.getDataSourceForRequest(this.val$imageRequest, this.val$callerContext, this.val$cacheLevel);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("request", this.val$imageRequest.toString()).toString();
    }
}
