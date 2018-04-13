package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.elements.StyleAccumulator;
import com.facebook.stetho.inspector.protocol.module.CSS.CSSProperty;
import com.facebook.stetho.inspector.protocol.module.CSS.SetPropertyTextResult;

class CSS$3 implements Runnable {
    final /* synthetic */ CSS this$0;
    final /* synthetic */ String val$key;
    final /* synthetic */ int val$nodeId;
    final /* synthetic */ SetPropertyTextResult val$result;
    final /* synthetic */ String val$ruleName;
    final /* synthetic */ String val$value;

    CSS$3(CSS css, int i, String str, String str2, String str3, SetPropertyTextResult setPropertyTextResult) {
        this.this$0 = css;
        this.val$nodeId = i;
        this.val$key = str;
        this.val$ruleName = str2;
        this.val$value = str3;
        this.val$result = setPropertyTextResult;
    }

    public void run() {
        Object elementForNodeId = CSS.access$200(this.this$0).getElementForNodeId(this.val$nodeId);
        if (elementForNodeId == null) {
            LogUtil.w("Failed to get style of an element that does not exist, nodeid=" + this.val$nodeId);
            return;
        }
        if (this.val$key != null) {
            CSS.access$200(this.this$0).setElementStyle(elementForNodeId, this.val$ruleName, this.val$key, this.val$value);
        }
        CSS.access$200(this.this$0).getElementStyles(elementForNodeId, this.val$ruleName, new StyleAccumulator() {
            public void store(String str, String str2, boolean z) {
                CSSProperty cSSProperty = new CSSProperty(null);
                cSSProperty.name = str;
                cSSProperty.value = str2;
                CSS$3.this.val$result.style.cssProperties.add(cSSProperty);
            }
        });
    }
}
