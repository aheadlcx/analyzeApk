package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.elements.ComputedStyleAccumulator;
import com.facebook.stetho.inspector.protocol.module.CSS.CSSComputedStyleProperty;
import com.facebook.stetho.inspector.protocol.module.CSS.GetComputedStyleForNodeRequest;
import com.facebook.stetho.inspector.protocol.module.CSS.GetComputedStyleForNodeResult;

class CSS$1 implements Runnable {
    final /* synthetic */ CSS this$0;
    final /* synthetic */ GetComputedStyleForNodeRequest val$request;
    final /* synthetic */ GetComputedStyleForNodeResult val$result;

    CSS$1(CSS css, GetComputedStyleForNodeRequest getComputedStyleForNodeRequest, GetComputedStyleForNodeResult getComputedStyleForNodeResult) {
        this.this$0 = css;
        this.val$request = getComputedStyleForNodeRequest;
        this.val$result = getComputedStyleForNodeResult;
    }

    public void run() {
        Object elementForNodeId = CSS.access$200(this.this$0).getElementForNodeId(this.val$request.nodeId);
        if (elementForNodeId == null) {
            LogUtil.e("Tried to get the style of an element that does not exist, using nodeid=" + this.val$request.nodeId);
        } else {
            CSS.access$200(this.this$0).getElementComputedStyles(elementForNodeId, new ComputedStyleAccumulator() {
                public void store(String str, String str2) {
                    CSSComputedStyleProperty cSSComputedStyleProperty = new CSSComputedStyleProperty(null);
                    cSSComputedStyleProperty.name = str;
                    cSSComputedStyleProperty.value = str2;
                    CSS$1.this.val$result.computedStyle.add(cSSComputedStyleProperty);
                }
            });
        }
    }
}
