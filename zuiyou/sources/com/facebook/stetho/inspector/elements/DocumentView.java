package com.facebook.stetho.inspector.elements;

public interface DocumentView {
    ElementInfo getElementInfo(Object obj);

    Object getRootElement();
}
