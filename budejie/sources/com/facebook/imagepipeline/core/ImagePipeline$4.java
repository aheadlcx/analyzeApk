package com.facebook.imagepipeline.core;

import bolts.f;
import bolts.g;
import com.facebook.datasource.SimpleDataSource;

class ImagePipeline$4 implements f<Boolean, Void> {
    final /* synthetic */ ImagePipeline this$0;
    final /* synthetic */ SimpleDataSource val$dataSource;

    ImagePipeline$4(ImagePipeline imagePipeline, SimpleDataSource simpleDataSource) {
        this.this$0 = imagePipeline;
        this.val$dataSource = simpleDataSource;
    }

    public Void then(g<Boolean> gVar) throws Exception {
        SimpleDataSource simpleDataSource = this.val$dataSource;
        boolean z = (gVar.c() || gVar.d() || !((Boolean) gVar.e()).booleanValue()) ? false : true;
        simpleDataSource.setResult(Boolean.valueOf(z));
        return null;
    }
}
