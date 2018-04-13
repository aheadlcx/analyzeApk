package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.common.UncheckedCallable;
import com.facebook.stetho.inspector.protocol.module.DOM.Node;

class DOM$1 implements UncheckedCallable<Node> {
    final /* synthetic */ DOM this$0;

    DOM$1(DOM dom) {
        this.this$0 = dom;
    }

    public Node call() {
        return DOM.access$400(this.this$0, DOM.access$300(this.this$0).getRootElement(), DOM.access$300(this.this$0).getDocumentView(), null);
    }
}
