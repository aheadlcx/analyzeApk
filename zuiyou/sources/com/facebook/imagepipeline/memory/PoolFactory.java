package com.facebook.imagepipeline.memory;

import com.facebook.common.memory.a;
import com.facebook.common.memory.g;
import com.facebook.common.memory.j;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class PoolFactory {
    private a mBitmapPool;
    private final PoolConfig mConfig;
    private FlexByteArrayPool mFlexByteArrayPool;
    private NativeMemoryChunkPool mNativeMemoryChunkPool;
    private g mPooledByteBufferFactory;
    private j mPooledByteStreams;
    private SharedByteArray mSharedByteArray;
    private a mSmallByteArrayPool;

    public PoolFactory(PoolConfig poolConfig) {
        this.mConfig = (PoolConfig) com.facebook.common.internal.g.a((Object) poolConfig);
    }

    public a getBitmapPool() {
        if (this.mBitmapPool == null) {
            this.mBitmapPool = new a(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getBitmapPoolParams(), this.mConfig.getBitmapPoolStatsTracker());
        }
        return this.mBitmapPool;
    }

    public FlexByteArrayPool getFlexByteArrayPool() {
        if (this.mFlexByteArrayPool == null) {
            this.mFlexByteArrayPool = new FlexByteArrayPool(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getFlexByteArrayPoolParams());
        }
        return this.mFlexByteArrayPool;
    }

    public int getFlexByteArrayPoolMaxNumThreads() {
        return this.mConfig.getFlexByteArrayPoolParams().maxNumThreads;
    }

    public NativeMemoryChunkPool getNativeMemoryChunkPool() {
        if (this.mNativeMemoryChunkPool == null) {
            this.mNativeMemoryChunkPool = new NativeMemoryChunkPool(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getNativeMemoryChunkPoolParams(), this.mConfig.getNativeMemoryChunkPoolStatsTracker());
        }
        return this.mNativeMemoryChunkPool;
    }

    public g getPooledByteBufferFactory() {
        if (this.mPooledByteBufferFactory == null) {
            this.mPooledByteBufferFactory = new NativePooledByteBufferFactory(getNativeMemoryChunkPool(), getPooledByteStreams());
        }
        return this.mPooledByteBufferFactory;
    }

    public j getPooledByteStreams() {
        if (this.mPooledByteStreams == null) {
            this.mPooledByteStreams = new j(getSmallByteArrayPool());
        }
        return this.mPooledByteStreams;
    }

    public SharedByteArray getSharedByteArray() {
        if (this.mSharedByteArray == null) {
            this.mSharedByteArray = new SharedByteArray(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getFlexByteArrayPoolParams());
        }
        return this.mSharedByteArray;
    }

    public a getSmallByteArrayPool() {
        if (this.mSmallByteArrayPool == null) {
            this.mSmallByteArrayPool = new GenericByteArrayPool(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getSmallByteArrayPoolParams(), this.mConfig.getSmallByteArrayPoolStatsTracker());
        }
        return this.mSmallByteArrayPool;
    }
}
