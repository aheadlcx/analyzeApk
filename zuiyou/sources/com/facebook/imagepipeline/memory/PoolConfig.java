package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.g;
import com.facebook.common.memory.c;
import com.facebook.common.memory.d;
import javax.annotation.concurrent.Immutable;

@Immutable
public class PoolConfig {
    private final PoolParams mBitmapPoolParams;
    private final PoolStatsTracker mBitmapPoolStatsTracker;
    private final PoolParams mFlexByteArrayPoolParams;
    private final c mMemoryTrimmableRegistry;
    private final PoolParams mNativeMemoryChunkPoolParams;
    private final PoolStatsTracker mNativeMemoryChunkPoolStatsTracker;
    private final PoolParams mSmallByteArrayPoolParams;
    private final PoolStatsTracker mSmallByteArrayPoolStatsTracker;

    public static class Builder {
        private PoolParams mBitmapPoolParams;
        private PoolStatsTracker mBitmapPoolStatsTracker;
        private PoolParams mFlexByteArrayPoolParams;
        private c mMemoryTrimmableRegistry;
        private PoolParams mNativeMemoryChunkPoolParams;
        private PoolStatsTracker mNativeMemoryChunkPoolStatsTracker;
        private PoolParams mSmallByteArrayPoolParams;
        private PoolStatsTracker mSmallByteArrayPoolStatsTracker;

        private Builder() {
        }

        public Builder setBitmapPoolParams(PoolParams poolParams) {
            this.mBitmapPoolParams = (PoolParams) g.a((Object) poolParams);
            return this;
        }

        public Builder setBitmapPoolStatsTracker(PoolStatsTracker poolStatsTracker) {
            this.mBitmapPoolStatsTracker = (PoolStatsTracker) g.a((Object) poolStatsTracker);
            return this;
        }

        public Builder setFlexByteArrayPoolParams(PoolParams poolParams) {
            this.mFlexByteArrayPoolParams = poolParams;
            return this;
        }

        public Builder setMemoryTrimmableRegistry(c cVar) {
            this.mMemoryTrimmableRegistry = cVar;
            return this;
        }

        public Builder setNativeMemoryChunkPoolParams(PoolParams poolParams) {
            this.mNativeMemoryChunkPoolParams = (PoolParams) g.a((Object) poolParams);
            return this;
        }

        public Builder setNativeMemoryChunkPoolStatsTracker(PoolStatsTracker poolStatsTracker) {
            this.mNativeMemoryChunkPoolStatsTracker = (PoolStatsTracker) g.a((Object) poolStatsTracker);
            return this;
        }

        public Builder setSmallByteArrayPoolParams(PoolParams poolParams) {
            this.mSmallByteArrayPoolParams = (PoolParams) g.a((Object) poolParams);
            return this;
        }

        public Builder setSmallByteArrayPoolStatsTracker(PoolStatsTracker poolStatsTracker) {
            this.mSmallByteArrayPoolStatsTracker = (PoolStatsTracker) g.a((Object) poolStatsTracker);
            return this;
        }

        public PoolConfig build() {
            return new PoolConfig();
        }
    }

    private PoolConfig(Builder builder) {
        PoolParams poolParams;
        PoolStatsTracker instance;
        c a;
        if (builder.mBitmapPoolParams == null) {
            poolParams = DefaultBitmapPoolParams.get();
        } else {
            poolParams = builder.mBitmapPoolParams;
        }
        this.mBitmapPoolParams = poolParams;
        if (builder.mBitmapPoolStatsTracker == null) {
            instance = NoOpPoolStatsTracker.getInstance();
        } else {
            instance = builder.mBitmapPoolStatsTracker;
        }
        this.mBitmapPoolStatsTracker = instance;
        if (builder.mFlexByteArrayPoolParams == null) {
            poolParams = DefaultFlexByteArrayPoolParams.get();
        } else {
            poolParams = builder.mFlexByteArrayPoolParams;
        }
        this.mFlexByteArrayPoolParams = poolParams;
        if (builder.mMemoryTrimmableRegistry == null) {
            a = d.a();
        } else {
            a = builder.mMemoryTrimmableRegistry;
        }
        this.mMemoryTrimmableRegistry = a;
        if (builder.mNativeMemoryChunkPoolParams == null) {
            poolParams = DefaultNativeMemoryChunkPoolParams.get();
        } else {
            poolParams = builder.mNativeMemoryChunkPoolParams;
        }
        this.mNativeMemoryChunkPoolParams = poolParams;
        if (builder.mNativeMemoryChunkPoolStatsTracker == null) {
            instance = NoOpPoolStatsTracker.getInstance();
        } else {
            instance = builder.mNativeMemoryChunkPoolStatsTracker;
        }
        this.mNativeMemoryChunkPoolStatsTracker = instance;
        if (builder.mSmallByteArrayPoolParams == null) {
            poolParams = DefaultByteArrayPoolParams.get();
        } else {
            poolParams = builder.mSmallByteArrayPoolParams;
        }
        this.mSmallByteArrayPoolParams = poolParams;
        if (builder.mSmallByteArrayPoolStatsTracker == null) {
            instance = NoOpPoolStatsTracker.getInstance();
        } else {
            instance = builder.mSmallByteArrayPoolStatsTracker;
        }
        this.mSmallByteArrayPoolStatsTracker = instance;
    }

    public PoolParams getBitmapPoolParams() {
        return this.mBitmapPoolParams;
    }

    public PoolStatsTracker getBitmapPoolStatsTracker() {
        return this.mBitmapPoolStatsTracker;
    }

    public c getMemoryTrimmableRegistry() {
        return this.mMemoryTrimmableRegistry;
    }

    public PoolParams getNativeMemoryChunkPoolParams() {
        return this.mNativeMemoryChunkPoolParams;
    }

    public PoolStatsTracker getNativeMemoryChunkPoolStatsTracker() {
        return this.mNativeMemoryChunkPoolStatsTracker;
    }

    public PoolParams getFlexByteArrayPoolParams() {
        return this.mFlexByteArrayPoolParams;
    }

    public PoolParams getSmallByteArrayPoolParams() {
        return this.mSmallByteArrayPoolParams;
    }

    public PoolStatsTracker getSmallByteArrayPoolStatsTracker() {
        return this.mSmallByteArrayPoolStatsTracker;
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}
