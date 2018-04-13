package com.facebook.cache.disk;

import android.content.Context;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.common.disk.DiskTrimmableRegistry;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.Suppliers;
import java.io.File;
import javax.annotation.Nullable;

public class DiskCacheConfig$Builder {
    private String mBaseDirectoryName;
    private Supplier<File> mBaseDirectoryPathSupplier;
    private CacheErrorLogger mCacheErrorLogger;
    private CacheEventListener mCacheEventListener;
    @Nullable
    private final Context mContext;
    private DiskTrimmableRegistry mDiskTrimmableRegistry;
    private EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier;
    private boolean mIndexPopulateAtStartupEnabled;
    private long mMaxCacheSize;
    private long mMaxCacheSizeOnLowDiskSpace;
    private long mMaxCacheSizeOnVeryLowDiskSpace;
    private int mVersion;

    private DiskCacheConfig$Builder(@Nullable Context context) {
        this.mVersion = 1;
        this.mBaseDirectoryName = "image_cache";
        this.mMaxCacheSize = 41943040;
        this.mMaxCacheSizeOnLowDiskSpace = 10485760;
        this.mMaxCacheSizeOnVeryLowDiskSpace = 2097152;
        this.mEntryEvictionComparatorSupplier = new DefaultEntryEvictionComparatorSupplier();
        this.mContext = context;
    }

    public DiskCacheConfig$Builder setVersion(int i) {
        this.mVersion = i;
        return this;
    }

    public DiskCacheConfig$Builder setBaseDirectoryName(String str) {
        this.mBaseDirectoryName = str;
        return this;
    }

    public DiskCacheConfig$Builder setBaseDirectoryPath(File file) {
        this.mBaseDirectoryPathSupplier = Suppliers.of(file);
        return this;
    }

    public DiskCacheConfig$Builder setBaseDirectoryPathSupplier(Supplier<File> supplier) {
        this.mBaseDirectoryPathSupplier = supplier;
        return this;
    }

    public DiskCacheConfig$Builder setMaxCacheSize(long j) {
        this.mMaxCacheSize = j;
        return this;
    }

    public DiskCacheConfig$Builder setMaxCacheSizeOnLowDiskSpace(long j) {
        this.mMaxCacheSizeOnLowDiskSpace = j;
        return this;
    }

    public DiskCacheConfig$Builder setMaxCacheSizeOnVeryLowDiskSpace(long j) {
        this.mMaxCacheSizeOnVeryLowDiskSpace = j;
        return this;
    }

    public DiskCacheConfig$Builder setEntryEvictionComparatorSupplier(EntryEvictionComparatorSupplier entryEvictionComparatorSupplier) {
        this.mEntryEvictionComparatorSupplier = entryEvictionComparatorSupplier;
        return this;
    }

    public DiskCacheConfig$Builder setCacheErrorLogger(CacheErrorLogger cacheErrorLogger) {
        this.mCacheErrorLogger = cacheErrorLogger;
        return this;
    }

    public DiskCacheConfig$Builder setCacheEventListener(CacheEventListener cacheEventListener) {
        this.mCacheEventListener = cacheEventListener;
        return this;
    }

    public DiskCacheConfig$Builder setDiskTrimmableRegistry(DiskTrimmableRegistry diskTrimmableRegistry) {
        this.mDiskTrimmableRegistry = diskTrimmableRegistry;
        return this;
    }

    public DiskCacheConfig$Builder setIndexPopulateAtStartupEnabled(boolean z) {
        this.mIndexPopulateAtStartupEnabled = z;
        return this;
    }

    public DiskCacheConfig build() {
        boolean z = (this.mBaseDirectoryPathSupplier == null && this.mContext == null) ? false : true;
        Preconditions.checkState(z, "Either a non-null context or a base directory path or supplier must be provided.");
        if (this.mBaseDirectoryPathSupplier == null && this.mContext != null) {
            this.mBaseDirectoryPathSupplier = new DiskCacheConfig$Builder$1(this);
        }
        return new DiskCacheConfig(this, null);
    }
}
