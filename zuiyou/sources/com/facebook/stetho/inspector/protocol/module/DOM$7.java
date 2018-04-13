package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.common.ArrayListAccumulator;
import com.facebook.stetho.inspector.protocol.module.DOM.PerformSearchRequest;

class DOM$7 implements Runnable {
    final /* synthetic */ DOM this$0;
    final /* synthetic */ PerformSearchRequest val$request;
    final /* synthetic */ ArrayListAccumulator val$resultNodeIds;

    DOM$7(DOM dom, PerformSearchRequest performSearchRequest, ArrayListAccumulator arrayListAccumulator) {
        this.this$0 = dom;
        this.val$request = performSearchRequest;
        this.val$resultNodeIds = arrayListAccumulator;
    }

    public void run() {
        DOM.access$300(this.this$0).findMatchingElements(this.val$request.query, this.val$resultNodeIds);
    }
}
