package mtopsdk.common.util;

import android.os.Looper;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import mtopsdk.mtop.a.f;

public final class g {
    private static AtomicInteger a = new AtomicInteger();

    public static int a() {
        return a.incrementAndGet() & Integer.MAX_VALUE;
    }

    public static Serializable a(File file, String str) {
        FileInputStream fileInputStream;
        Serializable serializable;
        Throwable th;
        Throwable th2;
        try {
            File file2 = new File(file, str);
            if (!file2.exists()) {
                return null;
            }
            ObjectInputStream objectInputStream;
            fileInputStream = new FileInputStream(file2);
            try {
                objectInputStream = new ObjectInputStream(new BufferedInputStream(fileInputStream));
                serializable = (Serializable) objectInputStream.readObject();
            } catch (Throwable th3) {
                Throwable th4 = th3;
                serializable = null;
                th2 = th4;
                try {
                    m.a("mtopsdk.MtopUtils", String.format("readObject error.fileDir={%s},fileName={%s}", new Object[]{file, str}), th2);
                    if (fileInputStream != null) {
                        return serializable;
                    }
                    try {
                        fileInputStream.close();
                        return serializable;
                    } catch (IOException e) {
                        return serializable;
                    }
                } catch (Throwable th5) {
                    th3 = th5;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e2) {
                        }
                    }
                    throw th3;
                }
            }
            try {
                objectInputStream.close();
                try {
                    fileInputStream.close();
                    return serializable;
                } catch (IOException e3) {
                    return serializable;
                }
            } catch (Throwable th6) {
                th2 = th6;
                m.a("mtopsdk.MtopUtils", String.format("readObject error.fileDir={%s},fileName={%s}", new Object[]{file, str}), th2);
                if (fileInputStream != null) {
                    return serializable;
                }
                fileInputStream.close();
                return serializable;
            }
        } catch (Throwable th7) {
            th3 = th7;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th3;
        }
    }

    public static boolean a(Serializable serializable, File file, String str) {
        Throwable th;
        File file2;
        OutputStream outputStream;
        FileOutputStream fileOutputStream = null;
        boolean z = true;
        try {
            OutputStream fileOutputStream2;
            if (!file.exists()) {
                file.mkdirs();
            }
            File file3 = new File(file, str + new Random().nextInt(10));
            try {
                fileOutputStream2 = new FileOutputStream(file3);
            } catch (Throwable th2) {
                th = th2;
                file2 = file3;
                try {
                    m.a("mtopsdk.MtopUtils", String.format("writeObject error.fileDir={%s},fileName={%s},object={%s}", new Object[]{file, str, serializable}), th);
                    if (fileOutputStream != null) {
                        z = false;
                    } else {
                        try {
                            fileOutputStream.close();
                            z = false;
                        } catch (IOException e) {
                            z = false;
                        }
                    }
                    return z ? file2.renameTo(new File(file, str)) : z;
                } catch (Throwable th3) {
                    th = th3;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e2) {
                        }
                    }
                    throw th;
                }
            }
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(fileOutputStream2));
                objectOutputStream.writeObject(serializable);
                objectOutputStream.flush();
                objectOutputStream.close();
                try {
                    fileOutputStream2.close();
                    file2 = file3;
                } catch (IOException e3) {
                    file2 = file3;
                }
            } catch (Throwable th4) {
                th = th4;
                outputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            file2 = null;
            m.a("mtopsdk.MtopUtils", String.format("writeObject error.fileDir={%s},fileName={%s},object={%s}", new Object[]{file, str, serializable}), th);
            if (fileOutputStream != null) {
                fileOutputStream.close();
                z = false;
            } else {
                z = false;
            }
            if (z) {
            }
        }
        if (z) {
        }
    }

    public static boolean b() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    public static boolean c() {
        try {
            return (f.a().b().getApplicationInfo().flags & 2) != 0;
        } catch (Throwable th) {
            return false;
        }
    }
}
