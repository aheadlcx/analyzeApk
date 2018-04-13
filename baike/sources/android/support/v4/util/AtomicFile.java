package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AtomicFile {
    private final File a;
    private final File b;

    public AtomicFile(@NonNull File file) {
        this.a = file;
        this.b = new File(file.getPath() + ".bak");
    }

    @NonNull
    public File getBaseFile() {
        return this.a;
    }

    public void delete() {
        this.a.delete();
        this.b.delete();
    }

    @NonNull
    public FileOutputStream startWrite() throws IOException {
        if (this.a.exists()) {
            if (this.b.exists()) {
                this.a.delete();
            } else if (!this.a.renameTo(this.b)) {
                Log.w("AtomicFile", "Couldn't rename file " + this.a + " to backup file " + this.b);
            }
        }
        try {
            return new FileOutputStream(this.a);
        } catch (FileNotFoundException e) {
            if (this.a.getParentFile().mkdirs()) {
                try {
                    return new FileOutputStream(this.a);
                } catch (FileNotFoundException e2) {
                    throw new IOException("Couldn't create " + this.a);
                }
            }
            throw new IOException("Couldn't create directory " + this.a);
        }
    }

    public void finishWrite(@Nullable FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            a(fileOutputStream);
            try {
                fileOutputStream.close();
                this.b.delete();
            } catch (Throwable e) {
                Log.w("AtomicFile", "finishWrite: Got exception:", e);
            }
        }
    }

    public void failWrite(@Nullable FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            a(fileOutputStream);
            try {
                fileOutputStream.close();
                this.a.delete();
                this.b.renameTo(this.a);
            } catch (Throwable e) {
                Log.w("AtomicFile", "failWrite: Got exception:", e);
            }
        }
    }

    @NonNull
    public FileInputStream openRead() throws FileNotFoundException {
        if (this.b.exists()) {
            this.a.delete();
            this.b.renameTo(this.a);
        }
        return new FileInputStream(this.a);
    }

    @NonNull
    public byte[] readFully() throws IOException {
        int i = 0;
        FileInputStream openRead = openRead();
        try {
            Object obj = new byte[openRead.available()];
            while (true) {
                int read = openRead.read(obj, i, obj.length - i);
                if (read <= 0) {
                    break;
                }
                Object obj2;
                read += i;
                i = openRead.available();
                if (i > obj.length - read) {
                    obj2 = new byte[(i + read)];
                    System.arraycopy(obj, 0, obj2, 0, read);
                } else {
                    obj2 = obj;
                }
                obj = obj2;
                i = read;
            }
            return obj;
        } finally {
            openRead.close();
        }
    }

    private static boolean a(@NonNull FileOutputStream fileOutputStream) {
        try {
            fileOutputStream.getFD().sync();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
