package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.common.UncheckedCallable;
import com.facebook.stetho.inspector.protocol.module.DOM.ResolveNodeRequest;

class DOM$4 implements UncheckedCallable<Object> {
    final /* synthetic */ DOM this$0;
    final /* synthetic */ ResolveNodeRequest val$request;

    DOM$4(DOM dom, ResolveNodeRequest resolveNodeRequest) {
        this.this$0 = dom;
        this.val$request = resolveNodeRequest;
    }

    public Object call() {
        return DOM.access$300(this.this$0).getElementForNodeId(this.val$request.nodeId);
    }
}
