package com.facebook.stetho.inspector.elements.android;

import com.facebook.stetho.inspector.elements.android.ViewDescriptor.ViewCSSProperty;
import java.util.Comparator;

class ViewDescriptor$1 implements Comparator<ViewCSSProperty> {
    final /* synthetic */ ViewDescriptor this$0;

    ViewDescriptor$1(ViewDescriptor viewDescriptor) {
        this.this$0 = viewDescriptor;
    }

    public int compare(ViewCSSProperty viewCSSProperty, ViewCSSProperty viewCSSProperty2) {
        return viewCSSProperty.getCSSName().compareTo(viewCSSProperty2.getCSSName());
    }
}
