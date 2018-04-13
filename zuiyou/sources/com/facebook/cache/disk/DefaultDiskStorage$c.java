package com.facebook.cache.disk;

import com.facebook.cache.disk.DefaultDiskStorage.FileType;
import java.io.File;
import java.io.IOException;
import javax.annotation.Nullable;

class DefaultDiskStorage$c {
    public final FileType a;
    public final String b;

    private DefaultDiskStorage$c(FileType fileType, String str) {
        this.a = fileType;
        this.b = str;
    }

    public String toString() {
        return this.a + "(" + this.b + ")";
    }

    public String a(String str) {
        return str + File.separator + this.b + this.a.extension;
    }

    public File a(File file) throws IOException {
        return File.createTempFile(this.b + ".", ".tmp", file);
    }

    @Nullable
    public static DefaultDiskStorage$c b(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf <= 0) {
            return null;
        }
        FileType fromExtension = FileType.fromExtension(name.substring(lastIndexOf));
        if (fromExtension == null) {
            return null;
        }
        name = name.substring(0, lastIndexOf);
        if (fromExtension.equals(FileType.TEMP)) {
            lastIndexOf = name.lastIndexOf(46);
            if (lastIndexOf <= 0) {
                return null;
            }
            name = name.substring(0, lastIndexOf);
        }
        return new DefaultDiskStorage$c(fromExtension, name);
    }
}
