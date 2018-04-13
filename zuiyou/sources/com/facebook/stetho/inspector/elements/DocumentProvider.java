package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.ThreadBound;
import javax.annotation.Nullable;

public interface DocumentProvider extends ThreadBound {
    void dispose();

    @Nullable
    NodeDescriptor getNodeDescriptor(@Nullable Object obj);

    @Nullable
    Object getRootElement();

    void hideHighlight();

    void highlightElement(Object obj, int i);

    void setAttributesAsText(Object obj, String str);

    void setInspectModeEnabled(boolean z);

    void setListener(DocumentProviderListener documentProviderListener);
}
