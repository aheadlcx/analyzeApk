package org.apache.commons.io;

import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;

public class b {
    public static final BigInteger a = BigInteger.valueOf(1024);
    public static final BigInteger b = a.multiply(a);
    public static final BigInteger c = a.multiply(b);
    public static final BigInteger d = a.multiply(c);
    public static final BigInteger e = a.multiply(d);
    public static final BigInteger f = a.multiply(e);
    public static final BigInteger g = BigInteger.valueOf(1024).multiply(BigInteger.valueOf(1152921504606846976L));
    public static final BigInteger h = a.multiply(g);
    public static final File[] i = new File[0];

    public static FileInputStream a(File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        } else if (file.isDirectory()) {
            throw new IOException("File '" + file + "' exists but is a directory");
        } else if (file.canRead()) {
            return new FileInputStream(file);
        } else {
            throw new IOException("File '" + file + "' cannot be read");
        }
    }

    public static FileOutputStream a(File file, boolean z) throws IOException {
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (!(parentFile == null || parentFile.mkdirs() || parentFile.isDirectory())) {
                throw new IOException("Directory '" + parentFile + "' could not be created");
            }
        } else if (file.isDirectory()) {
            throw new IOException("File '" + file + "' exists but is a directory");
        } else if (!file.canWrite()) {
            throw new IOException("File '" + file + "' cannot be written to");
        }
        return new FileOutputStream(file, z);
    }

    public static void a(File file, File file2) throws IOException {
        a(file, file2, true);
    }

    public static void a(File file, File file2, boolean z) throws IOException {
        b(file, file2);
        if (file.isDirectory()) {
            throw new IOException("Source '" + file + "' exists but is a directory");
        } else if (file.getCanonicalPath().equals(file2.getCanonicalPath())) {
            throw new IOException("Source '" + file + "' and destination '" + file2 + "' are the same");
        } else {
            File parentFile = file2.getParentFile();
            if (parentFile != null && !parentFile.mkdirs() && !parentFile.isDirectory()) {
                throw new IOException("Destination '" + parentFile + "' directory cannot be created");
            } else if (!file2.exists() || file2.canWrite()) {
                b(file, file2, z);
            } else {
                throw new IOException("Destination '" + file2 + "' exists but is read-only");
            }
        }
    }

    private static void b(File file, File file2, boolean z) throws IOException {
        FileOutputStream fileOutputStream;
        Throwable th;
        Object obj;
        Closeable closeable;
        FileChannel channel;
        Object obj2;
        Object obj3;
        if (file2.exists() && file2.isDirectory()) {
            throw new IOException("Destination '" + file2 + "' exists but is a directory");
        }
        Closeable closeable2 = null;
        Closeable closeable3 = null;
        Closeable closeable4 = null;
        try {
            ReadableByteChannel channel2;
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (Throwable th2) {
                th = th2;
                obj = fileInputStream;
                Closeable closeable5 = closeable4;
                closeable4 = null;
                closeable = closeable5;
                d.a(closeable4, closeable3, closeable, closeable2);
                throw th;
            }
            try {
                channel2 = fileInputStream.getChannel();
                try {
                    channel = fileOutputStream.getChannel();
                } catch (Throwable th3) {
                    th = th3;
                    obj2 = fileOutputStream;
                    obj = fileInputStream;
                    obj3 = channel2;
                    closeable4 = null;
                    d.a(closeable4, closeable3, closeable, closeable2);
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                obj2 = fileOutputStream;
                obj = fileInputStream;
                closeable = closeable4;
                closeable4 = null;
                d.a(closeable4, closeable3, closeable, closeable2);
                throw th;
            }
            try {
                long size = channel2.size();
                long j = 0;
                while (j < size) {
                    long j2 = size - j;
                    if (j2 > 31457280) {
                        j2 = 31457280;
                    }
                    j2 = channel.transferFrom(channel2, j, j2);
                    if (j2 == 0) {
                        break;
                    }
                    j += j2;
                }
                d.a(channel, fileOutputStream, channel2, fileInputStream);
                long length = file.length();
                j = file2.length();
                if (length != j) {
                    throw new IOException("Failed to copy full contents from '" + file + "' to '" + file2 + "' Expected length: " + length + " Actual: " + j);
                } else if (z) {
                    file2.setLastModified(file.lastModified());
                }
            } catch (Throwable th5) {
                obj2 = fileOutputStream;
                obj = fileInputStream;
                ReadableByteChannel readableByteChannel = channel2;
                Object obj4 = channel;
                th = th5;
                obj3 = readableByteChannel;
                d.a(closeable4, closeable3, closeable, closeable2);
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            closeable = closeable4;
            closeable4 = null;
            d.a(closeable4, closeable3, closeable, closeable2);
            throw th;
        }
    }

    private static void b(File file, File file2) throws FileNotFoundException {
        if (file == null) {
            throw new NullPointerException("Source must not be null");
        } else if (file2 == null) {
            throw new NullPointerException("Destination must not be null");
        } else if (!file.exists()) {
            throw new FileNotFoundException("Source '" + file + "' does not exist");
        }
    }

    public static void b(File file) throws IOException {
        if (file.exists()) {
            if (!f(file)) {
                c(file);
            }
            if (!file.delete()) {
                throw new IOException("Unable to delete directory " + file + ".");
            }
        }
    }

    public static void c(File file) throws IOException {
        IOException iOException = null;
        for (File d : g(file)) {
            try {
                d(d);
            } catch (IOException e) {
                iOException = e;
            }
        }
        if (iOException != null) {
            throw iOException;
        }
    }

    private static File[] g(File file) throws IOException {
        if (!file.exists()) {
            throw new IllegalArgumentException(file + " does not exist");
        } else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                return listFiles;
            }
            throw new IOException("Failed to list contents of " + file);
        } else {
            throw new IllegalArgumentException(file + " is not a directory");
        }
    }

    public static String a(File file, Charset charset) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = a(file);
            String a = d.a(inputStream, a.a(charset));
            return a;
        } finally {
            d.a(inputStream);
        }
    }

    public static void a(File file, String str, Charset charset, boolean z) throws IOException {
        OutputStream outputStream = null;
        try {
            outputStream = a(file, z);
            d.a(str, outputStream, charset);
            outputStream.close();
        } finally {
            d.a(outputStream);
        }
    }

    public static void a(File file, String str, String str2, boolean z) throws IOException {
        a(file, str, a.a(str2), z);
    }

    public static void a(File file, CharSequence charSequence, Charset charset) throws IOException {
        a(file, charSequence, charset, false);
    }

    public static void a(File file, CharSequence charSequence, Charset charset, boolean z) throws IOException {
        a(file, charSequence == null ? null : charSequence.toString(), charset, z);
    }

    public static void d(File file) throws IOException {
        if (file.isDirectory()) {
            b(file);
            return;
        }
        boolean exists = file.exists();
        if (!file.delete()) {
            if (exists) {
                throw new IOException("Unable to delete file: " + file);
            }
            throw new FileNotFoundException("File does not exist: " + file);
        }
    }

    public static void e(File file) throws IOException {
        if (file.exists()) {
            if (!file.isDirectory()) {
                throw new IOException("File " + file + " exists and is " + "not a directory. Unable to create directory.");
            }
        } else if (!file.mkdirs() && !file.isDirectory()) {
            throw new IOException("Unable to create directory " + file);
        }
    }

    public static boolean f(File file) throws IOException {
        if (e.a()) {
            return e.a(file);
        }
        if (file == null) {
            throw new NullPointerException("File must not be null");
        } else if (c.a()) {
            return false;
        } else {
            File file2;
            if (file.getParent() == null) {
                file2 = file;
            } else {
                file2 = new File(file.getParentFile().getCanonicalFile(), file.getName());
            }
            if (file2.getCanonicalFile().equals(file2.getAbsoluteFile())) {
                return h(file);
            }
            return true;
        }
    }

    private static boolean h(File file) throws IOException {
        if (file.exists()) {
            return false;
        }
        final File canonicalFile = file.getCanonicalFile();
        File parentFile = canonicalFile.getParentFile();
        if (parentFile == null || !parentFile.exists()) {
            return false;
        }
        File[] listFiles = parentFile.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.equals(canonicalFile);
            }
        });
        if (listFiles == null || listFiles.length <= 0) {
            return false;
        }
        return true;
    }
}
