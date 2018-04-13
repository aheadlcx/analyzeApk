package com.facebook.stetho.inspector.elements;

public interface DescriptorRegistrar {
    DescriptorRegistrar registerDescriptor(Class<?> cls, Descriptor descriptor);
}
