package com.ishumei.f;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class e {
    public static String a(File file) {
        FileChannel channel;
        FileLock lock;
        Throwable e;
        Closeable closeable;
        FileChannel fileChannel;
        FileChannel fileChannel2 = null;
        if (file == null || !file.exists()) {
            throw new IOException("not exist");
        }
        Closeable fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                channel = fileInputStream.getChannel();
                try {
                    lock = channel.lock(0, 63, true);
                } catch (Exception e2) {
                    e = e2;
                    closeable = fileInputStream;
                    FileChannel fileChannel3 = channel;
                    channel = null;
                    fileChannel2 = fileChannel3;
                    try {
                        throw new IOException(e);
                    } catch (Throwable th) {
                        e = th;
                        fileInputStream = fileChannel;
                        lock = channel;
                        if (lock != null) {
                            lock.release();
                        }
                        if (fileChannel2 != null) {
                            fileChannel2.close();
                        }
                        a(fileInputStream);
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    lock = null;
                    fileChannel2 = channel;
                    if (lock != null) {
                        lock.release();
                    }
                    if (fileChannel2 != null) {
                        fileChannel2.close();
                    }
                    a(fileInputStream);
                    throw e;
                }
            } catch (Exception e3) {
                e = e3;
                channel = null;
                closeable = fileInputStream;
                throw new IOException(e);
            } catch (Throwable th3) {
                e = th3;
                lock = null;
                if (lock != null) {
                    lock.release();
                }
                if (fileChannel2 != null) {
                    fileChannel2.close();
                }
                a(fileInputStream);
                throw e;
            }
            try {
                String str = new String(a(channel), "utf-8");
                if (lock != null) {
                    lock.release();
                }
                if (channel != null) {
                    channel.close();
                }
                a(fileInputStream);
                return str;
            } catch (Exception e4) {
                e = e4;
                fileChannel2 = channel;
                Object obj = lock;
                closeable = fileInputStream;
                throw new IOException(e);
            } catch (Throwable th4) {
                e = th4;
                fileChannel2 = channel;
                if (lock != null) {
                    lock.release();
                }
                if (fileChannel2 != null) {
                    fileChannel2.close();
                }
                a(fileInputStream);
                throw e;
            }
        } catch (Exception e5) {
            e = e5;
            channel = null;
            fileChannel = null;
            throw new IOException(e);
        } catch (Throwable th5) {
            e = th5;
            lock = null;
            fileInputStream = null;
            if (lock != null) {
                lock.release();
            }
            if (fileChannel2 != null) {
                fileChannel2.close();
            }
            a(fileInputStream);
            throw e;
        }
    }

    public static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            if (stringBuffer.length() > 0) {
                stringBuffer.append(":");
            }
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                toHexString = "0" + toHexString;
            }
            stringBuffer.append(toHexString);
        }
        return stringBuffer.toString();
    }

    public static JSONArray a(Object obj) {
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < length; i++) {
                jSONArray.put(b(Array.get(obj, i)));
            }
            return jSONArray;
        }
        throw new JSONException("Not a primitive data: " + obj.getClass());
    }

    public static JSONArray a(Collection collection) {
        JSONArray jSONArray = new JSONArray();
        if (collection != null) {
            for (Object b : collection) {
                jSONArray.put(b(b));
            }
        }
        return jSONArray;
    }

    public static JSONObject a(Map<?, ?> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw new NullPointerException("key == null");
                }
                try {
                    jSONObject.put(str, b(entry.getValue()));
                } catch (JSONException e) {
                }
            }
        } catch (Exception e2) {
        }
        return jSONObject;
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    public static void a(File file, String str) {
        if (file == null || d.a(str)) {
            throw new IOException("file or bytes empty");
        }
        a(file, str.getBytes("utf-8"));
    }

    public static void a(File file, byte[] bArr) {
        FileChannel channel;
        Throwable e;
        FileLock fileLock = null;
        if (file == null || bArr == null) {
            throw new IOException("file or bytes empty");
        }
        Closeable fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                channel = fileOutputStream.getChannel();
                try {
                    fileLock = channel.lock();
                    ByteBuffer wrap = ByteBuffer.wrap(bArr);
                    while (wrap.hasRemaining()) {
                        channel.write(wrap);
                    }
                    fileOutputStream.flush();
                    if (fileLock != null) {
                        fileLock.release();
                    }
                    if (channel != null) {
                        channel.close();
                    }
                    a(fileOutputStream);
                } catch (Exception e2) {
                    e = e2;
                    try {
                        throw new IOException(e);
                    } catch (Throwable th) {
                        e = th;
                        if (fileLock != null) {
                            fileLock.release();
                        }
                        if (channel != null) {
                            channel.close();
                        }
                        a(fileOutputStream);
                        throw e;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                channel = null;
                throw new IOException(e);
            } catch (Throwable th2) {
                e = th2;
                channel = null;
                if (fileLock != null) {
                    fileLock.release();
                }
                if (channel != null) {
                    channel.close();
                }
                a(fileOutputStream);
                throw e;
            }
        } catch (Exception e4) {
            e = e4;
            channel = null;
            fileOutputStream = null;
            throw new IOException(e);
        } catch (Throwable th3) {
            e = th3;
            channel = null;
            fileOutputStream = null;
            if (fileLock != null) {
                fileLock.release();
            }
            if (channel != null) {
                channel.close();
            }
            a(fileOutputStream);
            throw e;
        }
    }

    public static void a(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e) {
            }
        }
    }

    public static boolean a(String str) {
        try {
            return new File(Environment.getExternalStorageDirectory() + "/" + str).exists();
        } catch (Exception e) {
            return false;
        }
    }

    private static byte[] a(FileChannel fileChannel) {
        Closeable byteArrayOutputStream;
        Throwable e;
        int i = 0;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                ByteBuffer allocate = ByteBuffer.allocate(100);
                int i2 = 0;
                while (true) {
                    int read = fileChannel.read(allocate, (long) i2);
                    if (read <= 0) {
                        break;
                    }
                    i2 += read;
                    i += read;
                }
                byte[] array = allocate.array();
                if (i >= 4 && (array[0] & 255) == 0 && (array[1] & 255) == 0 && (array[2] & 255) == 0 && (array[3] & 255) == 0) {
                    throw new IOException("read bytes not utf-8");
                }
                byteArrayOutputStream.write(array, 0, i);
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                a(byteArrayOutputStream);
                return toByteArray;
            } catch (Exception e2) {
                e = e2;
                try {
                    throw new IOException(e);
                } catch (Throwable th) {
                    e = th;
                    a(byteArrayOutputStream);
                    throw e;
                }
            }
        } catch (Exception e3) {
            e = e3;
            byteArrayOutputStream = null;
            throw new IOException(e);
        } catch (Throwable th2) {
            e = th2;
            byteArrayOutputStream = null;
            a(byteArrayOutputStream);
            throw e;
        }
    }

    private static Object b(Object obj) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof JSONArray) || (obj instanceof JSONObject)) {
            return obj;
        }
        try {
            if (obj instanceof Collection) {
                return a((Collection) obj);
            }
            if (obj.getClass().isArray()) {
                return a(obj);
            }
            if (obj instanceof Map) {
                return a((Map) obj);
            }
            if ((obj instanceof Boolean) || (obj instanceof Byte) || (obj instanceof Character) || (obj instanceof Double) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Short) || (obj instanceof String)) {
                return obj;
            }
            if (obj.getClass().getPackage().getName().startsWith("java.")) {
                return obj.toString();
            }
            return null;
        } catch (Exception e) {
        }
    }

    public static String b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(bArr);
            StringBuilder stringBuilder = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                if ((b & 255) < 16) {
                    stringBuilder.append("0");
                }
                stringBuilder.append(Integer.toHexString(b & 255));
            }
            return stringBuilder.toString();
        } catch (Throwable e) {
            c.a(e);
            throw new IOException("fail to md5 data");
        }
    }

    public static boolean b(String str) {
        try {
            return new File(str).exists();
        } catch (Exception e) {
            return false;
        }
    }

    public static List<String> c(String str) {
        Throwable e;
        List<String> arrayList = new ArrayList();
        Closeable bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(str)));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        a(bufferedReader);
                        return arrayList;
                    } else if (!d.a(readLine)) {
                        arrayList.add(readLine);
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            }
        } catch (Exception e3) {
            e = e3;
            bufferedReader = null;
            try {
                throw new IOException(e);
            } catch (Throwable th) {
                e = th;
                a(bufferedReader);
                throw e;
            }
        } catch (Throwable th2) {
            e = th2;
            bufferedReader = null;
            a(bufferedReader);
            throw e;
        }
    }

    public static String d(String str) {
        return (str == null || str.isEmpty()) ? "" : str.replaceAll(":", "").toLowerCase();
    }

    public static String e(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            return "";
        }
    }

    public static String f(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return b(str.getBytes("utf-8"));
        } catch (Exception e) {
            return "";
        }
    }

    public static byte[] g(String str) {
        try {
            return Base64.decode(str.getBytes("utf-8"), 0);
        } catch (Throwable e) {
            throw new IOException(e);
        }
    }
}
