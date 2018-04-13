package com.facebook.stetho.inspector.elements;

public interface ChainedDescriptor<E> {
    void setSuper(Descriptor<? super E> descriptor);
}
