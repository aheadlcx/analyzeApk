package cn.htjyb.c.a;

import android.content.res.AssetManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.json.JSONObject;

public class b {
    public static boolean a(InputStream inputStream, File file) {
        FileNotFoundException e;
        Throwable th;
        if (file.exists()) {
            if (!file.isFile()) {
                return false;
            }
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e2) {
                com.izuiyou.a.a.b.d(e2.toString());
            }
        }
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[1024];
                int read = inputStream.read(bArr);
                while (-1 != read) {
                    fileOutputStream.write(bArr, 0, read);
                    read = inputStream.read(bArr);
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                        return false;
                    }
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return true;
            } catch (FileNotFoundException e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (fileOutputStream != null) {
                        return false;
                    }
                    fileOutputStream.close();
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            }
        } catch (FileNotFoundException e4) {
            e = e4;
            fileOutputStream = null;
            e.printStackTrace();
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileOutputStream != null) {
                return false;
            }
            fileOutputStream.close();
            return false;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    public static boolean a(File file, File file2) {
        FileOutputStream fileOutputStream;
        FileNotFoundException e;
        Throwable th;
        FileInputStream fileInputStream = null;
        if (!file.isFile()) {
            return false;
        }
        if (file2.exists()) {
            if (!file2.isFile()) {
                return false;
            }
            file2.delete();
            try {
                file2.createNewFile();
            } catch (IOException e2) {
                com.izuiyou.a.a.b.d(e2.toString());
            }
        }
        FileInputStream fileInputStream2;
        try {
            fileInputStream2 = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    byte[] bArr = new byte[1024];
                    for (int read = fileInputStream2.read(bArr); -1 != read; read = fileInputStream2.read(bArr)) {
                        fileOutputStream.write(bArr, 0, read);
                    }
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                            return false;
                        }
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    return true;
                } catch (FileNotFoundException e3) {
                    e = e3;
                    fileInputStream = fileInputStream2;
                    try {
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream != null) {
                            return false;
                        }
                        fileOutputStream.close();
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream2 = fileInputStream;
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (FileNotFoundException e4) {
                e = e4;
                fileOutputStream = null;
                fileInputStream = fileInputStream2;
                e.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    return false;
                }
                fileOutputStream.close();
                return false;
            } catch (Throwable th4) {
                th = th4;
                fileOutputStream = null;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (FileNotFoundException e5) {
            e = e5;
            fileOutputStream = null;
            e.printStackTrace();
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (fileOutputStream != null) {
                return false;
            }
            fileOutputStream.close();
            return false;
        } catch (Throwable th5) {
            th = th5;
            fileOutputStream = null;
            fileInputStream2 = null;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    public static void a(String str) {
        File file = new File(str);
        if (file.isFile() && file.exists()) {
            file.delete();
        }
    }

    public static long a(File file) throws Exception {
        long j = 0;
        File[] listFiles = file.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isDirectory()) {
                j += a(listFiles[i]);
            } else {
                j += listFiles[i].length();
            }
        }
        return j;
    }

    public static JSONObject a(File file, String str) {
        com.izuiyou.a.a.b.a("file: " + file);
        if (!file.exists()) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return new JSONObject(new String(bArr, str));
        } catch (Exception e) {
            com.izuiyou.a.a.b.d(e.toString());
            return null;
        }
    }

    public static String a(AssetManager assetManager, String str, String str2) {
        try {
            InputStream open = assetManager.open(str);
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            return new String(bArr, str2);
        } catch (Exception e) {
            com.izuiyou.a.a.b.d(e.toString());
            return null;
        }
    }

    public static boolean a(JSONObject jSONObject, File file, String str) {
        com.izuiyou.a.a.b.a("file: " + file);
        File parentFile = file.getParentFile();
        if (!(parentFile == null || parentFile.exists())) {
            parentFile.mkdirs();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(jSONObject.toString().getBytes(str));
            fileOutputStream.close();
            return true;
        } catch (Throwable th) {
            com.izuiyou.a.a.b.d(th.toString());
            return false;
        }
    }

    public static String a(String str, String str2) {
        ZipFile zipFile;
        String str3;
        Exception e;
        InputStream inputStream;
        ZipFile zipFile2;
        String str4;
        Object obj;
        int i = 0;
        OutputStream outputStream = null;
        com.izuiyou.a.a.b.a("archive: " + str + ", unzip_dir: " + str2);
        if (!str2.endsWith(File.separator)) {
            str2 = str2 + File.separator;
        }
        byte[] bArr = new byte[16384];
        try {
            zipFile = new ZipFile(str);
            try {
                Enumeration entries = zipFile.entries();
                str3 = null;
                while (entries.hasMoreElements()) {
                    ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                    File file = new File(str2 + zipEntry.getName());
                    if (zipEntry.isDirectory()) {
                        file.mkdirs();
                    } else {
                        try {
                            InputStream inputStream2 = zipFile.getInputStream(zipEntry);
                            try {
                                OutputStream fileOutputStream = new FileOutputStream(file);
                                while (true) {
                                    try {
                                        int read = inputStream2.read(bArr);
                                        if (read <= 0) {
                                            break;
                                        }
                                        i += read;
                                        fileOutputStream.write(bArr, 0, read);
                                    } catch (Exception e2) {
                                        e = e2;
                                        outputStream = fileOutputStream;
                                        inputStream = inputStream2;
                                        zipFile2 = zipFile;
                                        str4 = str3;
                                    }
                                }
                                inputStream2.close();
                                try {
                                    fileOutputStream.close();
                                    str3 = file.getPath();
                                } catch (Exception e3) {
                                    e = e3;
                                    zipFile2 = zipFile;
                                    str4 = str3;
                                    OutputStream outputStream2 = fileOutputStream;
                                    inputStream = null;
                                    outputStream = outputStream2;
                                }
                            } catch (Exception e4) {
                                e = e4;
                                inputStream = inputStream2;
                                zipFile2 = zipFile;
                                str4 = str3;
                            }
                        } catch (Exception e5) {
                            e = e5;
                            inputStream = null;
                            zipFile2 = zipFile;
                            str4 = str3;
                        }
                    }
                }
                inputStream = null;
            } catch (Exception e6) {
                e = e6;
                inputStream = null;
                zipFile2 = zipFile;
                obj = null;
                com.izuiyou.a.a.b.d(e.toString());
                str3 = str4;
                zipFile = zipFile2;
                if (zipFile != null) {
                    try {
                        zipFile.close();
                    } catch (IOException e7) {
                    }
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                com.izuiyou.a.a.b.a("archive_size: " + new File(str).length() + ", unzip_size: " + i);
                return str3;
            }
        } catch (Exception e8) {
            e = e8;
            inputStream = null;
            Object obj2 = null;
            obj = null;
            com.izuiyou.a.a.b.d(e.toString());
            str3 = str4;
            zipFile = zipFile2;
            if (zipFile != null) {
                zipFile.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            com.izuiyou.a.a.b.a("archive_size: " + new File(str).length() + ", unzip_size: " + i);
            return str3;
        }
        if (zipFile != null) {
            zipFile.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
        if (outputStream != null) {
            outputStream.close();
        }
        com.izuiyou.a.a.b.a("archive_size: " + new File(str).length() + ", unzip_size: " + i);
        return str3;
    }

    public static String b(String str) {
        if (str != null) {
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf >= 0 && lastIndexOf < str.length() - 1) {
                return str.substring(lastIndexOf + 1);
            }
        }
        return null;
    }

    public static String b(File file) {
        if (!file.isFile()) {
            return null;
        }
        byte[] bArr = new byte[1024];
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int read = fileInputStream.read(bArr, 0, 1024);
                if (read != -1) {
                    instance.update(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    return new BigInteger(1, instance.digest()).toString(16);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File c(File file) throws IOException {
        Throwable th;
        InputStream inputStream = null;
        File createTempFile = File.createTempFile("temp", ".zip");
        GZIPOutputStream gZIPOutputStream;
        try {
            InputStream fileInputStream = new FileInputStream(file);
            try {
                gZIPOutputStream = new GZIPOutputStream(new FileOutputStream(createTempFile));
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        gZIPOutputStream.write(bArr, 0, read);
                    }
                    gZIPOutputStream.flush();
                    gZIPOutputStream.close();
                    gZIPOutputStream = null;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                        }
                    }
                    if (null != null) {
                        gZIPOutputStream.close();
                    }
                    return createTempFile;
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = fileInputStream;
                }
            } catch (Throwable th3) {
                th = th3;
                gZIPOutputStream = null;
                inputStream = fileInputStream;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        throw th;
                    }
                }
                if (gZIPOutputStream != null) {
                    gZIPOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            gZIPOutputStream = null;
            if (inputStream != null) {
                inputStream.close();
            }
            if (gZIPOutputStream != null) {
                gZIPOutputStream.close();
            }
            throw th;
        }
    }

    public static boolean c(String str) {
        try {
            return new File(str).exists();
        } catch (Exception e) {
            return false;
        }
    }
}
