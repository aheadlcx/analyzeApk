package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.protocol.module.DOM.HighlightNodeRequest;
import com.facebook.stetho.inspector.protocol.module.DOM.RGBAColor;

class DOM$2 implements Runnable {
    final /* synthetic */ DOM this$0;
    final /* synthetic */ RGBAColor val$contentColor;
    final /* synthetic */ HighlightNodeRequest val$request;

    DOM$2(DOM dom, HighlightNodeRequest highlightNodeRequest, RGBAColor rGBAColor) {
        this.this$0 = dom;
        this.val$request = highlightNodeRequest;
        this.val$contentColor = rGBAColor;
    }

    public void run() {
        Object elementForNodeId = DOM.access$300(this.this$0).getElementForNodeId(this.val$request.nodeId.intValue());
        if (elementForNodeId != null) {
            DOM.access$300(this.this$0).highlightElement(elementForNodeId, this.val$contentColor.getColor());
        }
    }
}
