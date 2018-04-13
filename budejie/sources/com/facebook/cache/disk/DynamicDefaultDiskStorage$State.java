package com.facebook.cache.disk;

import com.facebook.common.internal.VisibleForTesting;
import java.io.File;
import javax.annotation.Nullable;

@VisibleForTesting
class DynamicDefaultDiskStorage$State {
    @Nullable
    public final DiskStorage delegate;
    @Nullable
    public final File rootDirectory;

    @VisibleForTesting
    DynamicDefaultDiskStorage$State(@Nullable File file, @Nullable DiskStorage diskStorage) {
        this.delegate = diskStorage;
        this.rootDirectory = file;
    }
}
