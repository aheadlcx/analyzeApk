package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.ThreadBound;
import javax.annotation.Nullable;

public interface Descriptor$Host extends ThreadBound {
    @Nullable
    Descriptor<?> getDescriptor(@Nullable Object obj);

    void onAttributeModified(Object obj, String str, String str2);

    void onAttributeRemoved(Object obj, String str);
}
