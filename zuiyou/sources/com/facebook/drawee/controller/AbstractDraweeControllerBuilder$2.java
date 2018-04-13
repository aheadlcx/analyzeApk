package com.facebook.drawee.controller;

import com.facebook.common.internal.f;
import com.facebook.common.internal.i;
import com.facebook.datasource.b;
import com.tencent.open.SocialConstants;

class AbstractDraweeControllerBuilder$2 implements i<b<IMAGE>> {
    final /* synthetic */ Object a;
    final /* synthetic */ Object b;
    final /* synthetic */ AbstractDraweeControllerBuilder$CacheLevel c;
    final /* synthetic */ AbstractDraweeControllerBuilder d;

    AbstractDraweeControllerBuilder$2(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, Object obj, Object obj2, AbstractDraweeControllerBuilder$CacheLevel abstractDraweeControllerBuilder$CacheLevel) {
        this.d = abstractDraweeControllerBuilder;
        this.a = obj;
        this.b = obj2;
        this.c = abstractDraweeControllerBuilder$CacheLevel;
    }

    public /* synthetic */ Object b() {
        return a();
    }

    public b<IMAGE> a() {
        return this.d.a(this.a, this.b, this.c);
    }

    public String toString() {
        return f.a(this).a(SocialConstants.TYPE_REQUEST, this.a.toString()).toString();
    }
}
