package com.facebook.cache.disk;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheErrorLogger.CacheErrorCategory;
import com.facebook.cache.disk.DiskStorage.DiskDumpInfo;
import com.facebook.cache.disk.DiskStorage.Entry;
import com.facebook.cache.disk.DiskStorage.Inserter;
import com.facebook.common.file.FileTree;
import com.facebook.common.file.FileUtils;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class DynamicDefaultDiskStorage implements DiskStorage {
    private static final Class<?> TAG = DynamicDefaultDiskStorage.class;
    private final String mBaseDirectoryName;
    private final Supplier<File> mBaseDirectoryPathSupplier;
    private final CacheErrorLogger mCacheErrorLogger;
    @VisibleForTesting
    volatile DynamicDefaultDiskStorage$State mCurrentState = new DynamicDefaultDiskStorage$State(null, null);
    private final int mVersion;

    public DynamicDefaultDiskStorage(int i, Supplier<File> supplier, String str, CacheErrorLogger cacheErrorLogger) {
        this.mVersion = i;
        this.mCacheErrorLogger = cacheErrorLogger;
        this.mBaseDirectoryPathSupplier = supplier;
        this.mBaseDirectoryName = str;
    }

    public boolean isEnabled() {
        try {
            return get().isEnabled();
        } catch (IOException e) {
            return false;
        }
    }

    public boolean isExternal() {
        try {
            return get().isExternal();
        } catch (IOException e) {
            return false;
        }
    }

    public String getStorageName() {
        try {
            return get().getStorageName();
        } catch (IOException e) {
            return "";
        }
    }

    public BinaryResource getResource(String str, Object obj) throws IOException {
        return get().getResource(str, obj);
    }

    public boolean contains(String str, Object obj) throws IOException {
        return get().contains(str, obj);
    }

    public boolean touch(String str, Object obj) throws IOException {
        return get().touch(str, obj);
    }

    public void purgeUnexpectedResources() {
        try {
            get().purgeUnexpectedResources();
        } catch (Throwable e) {
            FLog.e(TAG, "purgeUnexpectedResources", e);
        }
    }

    public Inserter insert(String str, Object obj) throws IOException {
        return get().insert(str, obj);
    }

    public Collection<Entry> getEntries() throws IOException {
        return get().getEntries();
    }

    public long remove(Entry entry) throws IOException {
        return get().remove(entry);
    }

    public long remove(String str) throws IOException {
        return get().remove(str);
    }

    public void clearAll() throws IOException {
        get().clearAll();
    }

    public DiskDumpInfo getDumpInfo() throws IOException {
        return get().getDumpInfo();
    }

    @VisibleForTesting
    synchronized DiskStorage get() throws IOException {
        if (shouldCreateNewStorage()) {
            deleteOldStorageIfNecessary();
            createStorage();
        }
        return (DiskStorage) Preconditions.checkNotNull(this.mCurrentState.delegate);
    }

    private boolean shouldCreateNewStorage() {
        DynamicDefaultDiskStorage$State dynamicDefaultDiskStorage$State = this.mCurrentState;
        return dynamicDefaultDiskStorage$State.delegate == null || dynamicDefaultDiskStorage$State.rootDirectory == null || !dynamicDefaultDiskStorage$State.rootDirectory.exists();
    }

    @VisibleForTesting
    void deleteOldStorageIfNecessary() {
        if (this.mCurrentState.delegate != null && this.mCurrentState.rootDirectory != null) {
            FileTree.deleteRecursively(this.mCurrentState.rootDirectory);
        }
    }

    private void createStorage() throws IOException {
        File file = new File((File) this.mBaseDirectoryPathSupplier.get(), this.mBaseDirectoryName);
        createRootDirectoryIfNecessary(file);
        this.mCurrentState = new DynamicDefaultDiskStorage$State(file, new DefaultDiskStorage(file, this.mVersion, this.mCacheErrorLogger));
    }

    @VisibleForTesting
    void createRootDirectoryIfNecessary(File file) throws IOException {
        try {
            FileUtils.mkdirs(file);
            FLog.d(TAG, "Created cache directory %s", file.getAbsolutePath());
        } catch (Throwable e) {
            this.mCacheErrorLogger.logError(CacheErrorCategory.WRITE_CREATE_DIR, TAG, "createRootDirectoryIfNecessary", e);
            throw e;
        }
    }
}
