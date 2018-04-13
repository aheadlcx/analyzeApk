package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Util;
import java.util.IdentityHashMap;
import java.util.Map;
import javax.annotation.Nullable;

public final class DescriptorMap implements DescriptorRegistrar {
    private Descriptor$Host mHost;
    private boolean mIsInitializing;
    private final Map<Class<?>, Descriptor> mMap = new IdentityHashMap();

    public DescriptorMap beginInit() {
        Util.throwIf(this.mIsInitializing);
        this.mIsInitializing = true;
        return this;
    }

    public DescriptorMap registerDescriptor(Class<?> cls, Descriptor descriptor) {
        Util.throwIfNull(cls);
        Util.throwIfNull(descriptor);
        Util.throwIf(descriptor.isInitialized());
        Util.throwIfNot(this.mIsInitializing);
        if (this.mMap.containsKey(cls)) {
            throw new UnsupportedOperationException();
        } else if (this.mMap.containsValue(descriptor)) {
            throw new UnsupportedOperationException();
        } else {
            this.mMap.put(cls, descriptor);
            return this;
        }
    }

    public DescriptorMap setHost(Descriptor$Host descriptor$Host) {
        Util.throwIfNull(descriptor$Host);
        Util.throwIfNot(this.mIsInitializing);
        Util.throwIfNotNull(this.mHost);
        this.mHost = descriptor$Host;
        return this;
    }

    public DescriptorMap endInit() {
        Util.throwIfNot(this.mIsInitializing);
        Util.throwIfNull(this.mHost);
        this.mIsInitializing = false;
        for (Class cls : this.mMap.keySet()) {
            Descriptor descriptor = (Descriptor) this.mMap.get(cls);
            if (descriptor instanceof ChainedDescriptor) {
                ((ChainedDescriptor) descriptor).setSuper(getImpl(cls.getSuperclass()));
            }
            descriptor.initialize(this.mHost);
        }
        return this;
    }

    @Nullable
    public Descriptor get(Class<?> cls) {
        Util.throwIfNull(cls);
        Util.throwIf(this.mIsInitializing);
        return getImpl(cls);
    }

    @Nullable
    private Descriptor getImpl(Class<?> cls) {
        while (cls != null) {
            Descriptor descriptor = (Descriptor) this.mMap.get(cls);
            if (descriptor != null) {
                return descriptor;
            }
            cls = cls.getSuperclass();
        }
        return null;
    }
}
