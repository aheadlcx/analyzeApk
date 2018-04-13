package com.facebook.imagepipeline.cache;

import com.facebook.common.memory.MemoryTrimType;

public interface CountingMemoryCache$CacheTrimStrategy {
    double getTrimRatio(MemoryTrimType memoryTrimType);
}
