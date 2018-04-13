package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;

public final class ObjectDescriptor extends Descriptor<Object> {
    public void hook(Object obj) {
    }

    public void unhook(Object obj) {
    }

    public NodeType getNodeType(Object obj) {
        return NodeType.ELEMENT_NODE;
    }

    public String getNodeName(Object obj) {
        return obj.getClass().getName();
    }

    public String getLocalName(Object obj) {
        return getNodeName(obj);
    }

    public String getNodeValue(Object obj) {
        return null;
    }

    public void getChildren(Object obj, Accumulator<Object> accumulator) {
    }

    public void getAttributes(Object obj, AttributeAccumulator attributeAccumulator) {
    }

    public void setAttributesAsText(Object obj, String str) {
    }

    public void getStyleRuleNames(Object obj, StyleRuleNameAccumulator styleRuleNameAccumulator) {
    }

    public void getStyles(Object obj, String str, StyleAccumulator styleAccumulator) {
    }

    public void setStyle(Object obj, String str, String str2, String str3) {
    }

    public void getComputedStyles(Object obj, ComputedStyleAccumulator computedStyleAccumulator) {
    }
}
