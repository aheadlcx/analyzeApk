package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.protocol.module.DOM.SetAttributesAsTextRequest;

class DOM$5 implements Runnable {
    final /* synthetic */ DOM this$0;
    final /* synthetic */ SetAttributesAsTextRequest val$request;

    DOM$5(DOM dom, SetAttributesAsTextRequest setAttributesAsTextRequest) {
        this.this$0 = dom;
        this.val$request = setAttributesAsTextRequest;
    }

    public void run() {
        Object elementForNodeId = DOM.access$300(this.this$0).getElementForNodeId(this.val$request.nodeId);
        if (elementForNodeId != null) {
            DOM.access$300(this.this$0).setAttributesAsText(elementForNodeId, this.val$request.text);
        }
    }
}
