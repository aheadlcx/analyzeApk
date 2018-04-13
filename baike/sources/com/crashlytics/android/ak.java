package com.crashlytics.android;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

final class ak extends FileOutputStream {
    public static final FilenameFilter a = new al();
    private final String b;
    private File c;
    private boolean d = false;

    public ak(File file, String str) throws FileNotFoundException {
        super(new File(file, str + ".cls_temp"));
        this.b = file + File.separator + str;
        this.c = new File(this.b + ".cls_temp");
    }

    public final synchronized void close() throws IOException {
        if (!this.d) {
            this.d = true;
            super.flush();
            super.close();
            File file = new File(this.b + ".cls");
            if (this.c.renameTo(file)) {
                this.c = null;
            } else {
                String str = "";
                if (file.exists()) {
                    str = " (target already exists)";
                } else if (!this.c.exists()) {
                    str = " (source does not exist)";
                }
                throw new IOException("Could not rename temp file: " + this.c + " -> " + file + str);
            }
        }
    }

    public final void a() throws IOException {
        if (!this.d) {
            this.d = true;
            super.flush();
            super.close();
        }
    }
}
