package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.ThreadBound;
import javax.annotation.Nullable;

public interface NodeDescriptor<E> extends ThreadBound {
    void getAttributes(E e, AttributeAccumulator attributeAccumulator);

    void getChildren(E e, Accumulator<Object> accumulator);

    void getComputedStyles(E e, ComputedStyleAccumulator computedStyleAccumulator);

    String getLocalName(E e);

    String getNodeName(E e);

    NodeType getNodeType(E e);

    @Nullable
    String getNodeValue(E e);

    void getStyleRuleNames(E e, StyleRuleNameAccumulator styleRuleNameAccumulator);

    void getStyles(E e, String str, StyleAccumulator styleAccumulator);

    void hook(E e);

    void setAttributesAsText(E e, String str);

    void setStyle(E e, String str, String str2, String str3);

    void unhook(E e);
}
