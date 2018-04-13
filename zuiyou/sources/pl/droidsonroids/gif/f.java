package pl.droidsonroids.gif;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

class f {
    private static final String a = System.mapLibraryName("pl_droidsonroids_gif");

    private f() {
    }

    @SuppressLint({"UnsafeDynamicallyLoadedCode"})
    static void a(Context context) {
        synchronized (f.class) {
            System.load(b(context).getAbsolutePath());
        }
    }

    private static File b(Context context) {
        Throwable th;
        int i = 0;
        Closeable closeable = null;
        String str = a + "unspecified";
        File file = new File(context.getDir(ShareConstants.SO_PATH, 0), str);
        if (file.isFile()) {
            return file;
        }
        File file2 = new File(context.getCacheDir(), str);
        if (file2.isFile()) {
            return file2;
        }
        Closeable inputStream;
        str = System.mapLibraryName("pl_droidsonroids_gif_surface");
        FilenameFilter anonymousClass1 = new FilenameFilter() {
            public boolean accept(File file, String str) {
                return str.startsWith(f.a) || str.startsWith(str);
            }
        };
        a(file, anonymousClass1);
        a(file2, anonymousClass1);
        try {
            Closeable fileOutputStream;
            ZipFile a = a(new File(context.getApplicationInfo().sourceDir));
            while (true) {
                int i2 = i + 1;
                if (i >= 5) {
                    break;
                }
                try {
                    ZipEntry a2 = a(a);
                    if (a2 == null) {
                        throw new IllegalStateException("Library " + a + " for supported ABIs not found in APK file");
                    }
                    inputStream = a.getInputStream(a2);
                    try {
                        fileOutputStream = new FileOutputStream(file);
                    } catch (IOException e) {
                        fileOutputStream = null;
                        if (i2 > 2) {
                            file = file2;
                        }
                        a(inputStream);
                        a(fileOutputStream);
                        i = i2;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    try {
                        a((InputStream) inputStream, (OutputStream) fileOutputStream);
                        break;
                    } catch (IOException e2) {
                        if (i2 > 2) {
                            file = file2;
                        }
                        a(inputStream);
                        a(fileOutputStream);
                        i = i2;
                    } catch (Throwable th3) {
                        th = th3;
                        closeable = fileOutputStream;
                    }
                } catch (IOException e3) {
                    fileOutputStream = null;
                    inputStream = null;
                    if (i2 > 2) {
                        file = file2;
                    }
                    a(inputStream);
                    a(fileOutputStream);
                    i = i2;
                } catch (Throwable th4) {
                    th = th4;
                    r3 = a;
                }
                a(inputStream);
                a(fileOutputStream);
                i = i2;
            }
            a(inputStream);
            a(fileOutputStream);
            b(file);
            if (a == null) {
                return file;
            }
            try {
                a.close();
                return file;
            } catch (IOException e4) {
                return file;
            }
        } catch (Throwable th5) {
            th = th5;
            ZipFile zipFile;
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException e5) {
                }
            }
            throw th;
        }
        a(inputStream);
        a(closeable);
        throw th;
    }

    private static ZipEntry a(ZipFile zipFile) {
        for (String a : b()) {
            ZipEntry a2 = a(zipFile, a);
            if (a2 != null) {
                return a2;
            }
        }
        return null;
    }

    private static String[] b() {
        if (VERSION.SDK_INT >= 21) {
            return Build.SUPPORTED_ABIS;
        }
        return new String[]{Build.CPU_ABI, Build.CPU_ABI2};
    }

    private static ZipEntry a(ZipFile zipFile, String str) {
        return zipFile.getEntry("lib/" + str + "/" + a);
    }

    private static ZipFile a(File file) {
        ZipFile zipFile;
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i >= 5) {
                break;
            }
            try {
                zipFile = new ZipFile(file, 1);
                break;
            } catch (IOException e) {
                i = i2;
            }
        }
        zipFile = null;
        if (zipFile != null) {
            return zipFile;
        }
        throw new IllegalStateException("Could not open APK file: " + file.getAbsolutePath());
    }

    private static void a(File file, FilenameFilter filenameFilter) {
        File[] listFiles = file.getParentFile().listFiles(filenameFilter);
        if (listFiles != null) {
            for (File delete : listFiles) {
                delete.delete();
            }
        }
    }

    @SuppressLint({"SetWorldReadable"})
    private static void b(File file) {
        file.setReadable(true, false);
        file.setExecutable(true, false);
        file.setWritable(true);
    }

    private static void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }
}
