package com.facebook.common.file;

import java.io.IOException;

public class FileUtils$RenameException extends IOException {
    public FileUtils$RenameException(String str) {
        super(str);
    }

    public FileUtils$RenameException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
