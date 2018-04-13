package com.facebook.cache.disk;

import android.content.Context;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.common.NoOpCacheErrorLogger;
import com.facebook.cache.common.NoOpCacheEventListener;
import com.facebook.common.disk.DiskTrimmableRegistry;
import com.facebook.common.disk.NoOpDiskTrimmableRegistry;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import java.io.File;
import javax.annotation.Nullable;

public class DiskCacheConfig {
    private final String mBaseDirectoryName;
    private final Supplier<File> mBaseDirectoryPathSupplier;
    private final CacheErrorLogger mCacheErrorLogger;
    private final CacheEventListener mCacheEventListener;
    private final Context mContext;
    private final long mDefaultSizeLimit;
    private final DiskTrimmableRegistry mDiskTrimmableRegistry;
    private final EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier;
    private final boolean mIndexPopulateAtStartupEnabled;
    private final long mLowDiskSpaceSizeLimit;
    private final long mMinimumSizeLimit;
    private final int mVersion;

    private DiskCacheConfig(DiskCacheConfig$Builder diskCacheConfig$Builder) {
        CacheErrorLogger instance;
        CacheEventListener instance2;
        DiskTrimmableRegistry instance3;
        this.mVersion = DiskCacheConfig$Builder.access$000(diskCacheConfig$Builder);
        this.mBaseDirectoryName = (String) Preconditions.checkNotNull(DiskCacheConfig$Builder.access$100(diskCacheConfig$Builder));
        this.mBaseDirectoryPathSupplier = (Supplier) Preconditions.checkNotNull(DiskCacheConfig$Builder.access$200(diskCacheConfig$Builder));
        this.mDefaultSizeLimit = DiskCacheConfig$Builder.access$300(diskCacheConfig$Builder);
        this.mLowDiskSpaceSizeLimit = DiskCacheConfig$Builder.access$400(diskCacheConfig$Builder);
        this.mMinimumSizeLimit = DiskCacheConfig$Builder.access$500(diskCacheConfig$Builder);
        this.mEntryEvictionComparatorSupplier = (EntryEvictionComparatorSupplier) Preconditions.checkNotNull(DiskCacheConfig$Builder.access$600(diskCacheConfig$Builder));
        if (DiskCacheConfig$Builder.access$700(diskCacheConfig$Builder) == null) {
            instance = NoOpCacheErrorLogger.getInstance();
        } else {
            instance = DiskCacheConfig$Builder.access$700(diskCacheConfig$Builder);
        }
        this.mCacheErrorLogger = instance;
        if (DiskCacheConfig$Builder.access$800(diskCacheConfig$Builder) == null) {
            instance2 = NoOpCacheEventListener.getInstance();
        } else {
            instance2 = DiskCacheConfig$Builder.access$800(diskCacheConfig$Builder);
        }
        this.mCacheEventListener = instance2;
        if (DiskCacheConfig$Builder.access$900(diskCacheConfig$Builder) == null) {
            instance3 = NoOpDiskTrimmableRegistry.getInstance();
        } else {
            instance3 = DiskCacheConfig$Builder.access$900(diskCacheConfig$Builder);
        }
        this.mDiskTrimmableRegistry = instance3;
        this.mContext = DiskCacheConfig$Builder.access$1000(diskCacheConfig$Builder);
        this.mIndexPopulateAtStartupEnabled = DiskCacheConfig$Builder.access$1100(diskCacheConfig$Builder);
    }

    public int getVersion() {
        return this.mVersion;
    }

    public String getBaseDirectoryName() {
        return this.mBaseDirectoryName;
    }

    public Supplier<File> getBaseDirectoryPathSupplier() {
        return this.mBaseDirectoryPathSupplier;
    }

    public long getDefaultSizeLimit() {
        return this.mDefaultSizeLimit;
    }

    public long getLowDiskSpaceSizeLimit() {
        return this.mLowDiskSpaceSizeLimit;
    }

    public long getMinimumSizeLimit() {
        return this.mMinimumSizeLimit;
    }

    public EntryEvictionComparatorSupplier getEntryEvictionComparatorSupplier() {
        return this.mEntryEvictionComparatorSupplier;
    }

    public CacheErrorLogger getCacheErrorLogger() {
        return this.mCacheErrorLogger;
    }

    public CacheEventListener getCacheEventListener() {
        return this.mCacheEventListener;
    }

    public DiskTrimmableRegistry getDiskTrimmableRegistry() {
        return this.mDiskTrimmableRegistry;
    }

    public Context getContext() {
        return this.mContext;
    }

    public boolean getIndexPopulateAtStartupEnabled() {
        return this.mIndexPopulateAtStartupEnabled;
    }

    public static DiskCacheConfig$Builder newBuilder(@Nullable Context context) {
        return new DiskCacheConfig$Builder(context, null);
    }
}
