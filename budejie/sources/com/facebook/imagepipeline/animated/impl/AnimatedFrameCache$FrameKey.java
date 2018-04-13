package com.facebook.imagepipeline.animated.impl;

import android.net.Uri;
import cn.v6.sixrooms.constants.CommonInts;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.VisibleForTesting;

@VisibleForTesting
class AnimatedFrameCache$FrameKey implements CacheKey {
    private final int mFrameIndex;
    private final CacheKey mImageCacheKey;

    public AnimatedFrameCache$FrameKey(CacheKey cacheKey, int i) {
        this.mImageCacheKey = cacheKey;
        this.mFrameIndex = i;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("imageCacheKey", this.mImageCacheKey).add("frameIndex", this.mFrameIndex).toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AnimatedFrameCache$FrameKey)) {
            return false;
        }
        AnimatedFrameCache$FrameKey animatedFrameCache$FrameKey = (AnimatedFrameCache$FrameKey) obj;
        if (this.mImageCacheKey == animatedFrameCache$FrameKey.mImageCacheKey && this.mFrameIndex == animatedFrameCache$FrameKey.mFrameIndex) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.mImageCacheKey.hashCode() * CommonInts.REPLY_RESULT) + this.mFrameIndex;
    }

    public boolean containsUri(Uri uri) {
        return this.mImageCacheKey.containsUri(uri);
    }

    public String getUriString() {
        return null;
    }
}
