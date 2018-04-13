package qsbk.app.core.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Process;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;
import cz.msebera.android.httpclient.message.TokenParser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.core.model.CustomButton;

public class ACache {
    public static final int TIME_DAY = 86400;
    public static final int TIME_HOUR = 3600;
    private static Map<String, ACache> a = new HashMap();
    private ACacheManager b;

    public class ACacheManager {
        protected File a;
        final /* synthetic */ ACache b;
        private final AtomicLong c;
        private final AtomicInteger d;
        private final long e;
        private final int f;
        private final Map<File, Long> g;

        private ACacheManager(ACache aCache, File file, long j, int i) {
            this.b = aCache;
            this.g = Collections.synchronizedMap(new HashMap());
            this.a = file;
            this.e = j;
            this.f = i;
            this.c = new AtomicLong();
            this.d = new AtomicInteger();
            a();
        }

        private void a() {
            new Thread(new b(this)).start();
        }

        private void a(File file) {
            int i = this.d.get();
            while (i + 1 > this.f) {
                this.c.addAndGet(-c());
                i = this.d.addAndGet(-1);
            }
            this.d.addAndGet(1);
            long b = b(file);
            long j = this.c.get();
            while (j + b > this.e) {
                j = this.c.addAndGet(-c());
            }
            this.c.addAndGet(b);
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            file.setLastModified(valueOf.longValue());
            this.g.put(file, valueOf);
        }

        private File a(String str) {
            File b = b(str);
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            if (b != null) {
                b.setLastModified(valueOf.longValue());
                this.g.put(b, valueOf);
            }
            return b;
        }

        private File b(String str) {
            if (this.a.exists() || this.a.mkdirs()) {
                return new File(this.a, str.hashCode() + "");
            }
            return null;
        }

        private boolean c(String str) {
            return a(str).delete();
        }

        private void b() {
            this.g.clear();
            this.c.set(0);
            File[] listFiles = this.a.listFiles();
            if (listFiles != null) {
                for (File delete : listFiles) {
                    delete.delete();
                }
            }
        }

        private long c() {
            File file = null;
            if (this.g.isEmpty()) {
                return 0;
            }
            Set<Entry> entrySet = this.g.entrySet();
            synchronized (this.g) {
                Long l = null;
                for (Entry entry : entrySet) {
                    File file2;
                    Long l2;
                    if (file == null) {
                        file2 = (File) entry.getKey();
                        l2 = (Long) entry.getValue();
                    } else {
                        Long l3 = (Long) entry.getValue();
                        if (l3.longValue() < l.longValue()) {
                            File file3 = (File) entry.getKey();
                            l2 = l3;
                            file2 = file3;
                        } else {
                            file2 = file;
                            l2 = l;
                        }
                    }
                    file = file2;
                    l = l2;
                }
            }
            long b = b(file);
            if (!file.delete()) {
                return b;
            }
            this.g.remove(file);
            return b;
        }

        private long b(File file) {
            if (file == null || !file.exists()) {
                return 0;
            }
            return file.length();
        }
    }

    private static class a {
        private static boolean c(String str) {
            return d(str.getBytes());
        }

        private static boolean d(byte[] bArr) {
            String[] g = g(bArr);
            if (g != null && g.length == 2) {
                String str = g[0];
                while (str.startsWith("0")) {
                    str = str.substring(1, str.length());
                }
                if (System.currentTimeMillis() > Long.valueOf(str).longValue() + (Long.valueOf(g[1]).longValue() * 1000)) {
                    return true;
                }
            }
            return false;
        }

        private static String b(int i, String str) {
            return a(i) + str;
        }

        private static byte[] b(int i, byte[] bArr) {
            Object bytes = a(i).getBytes();
            Object obj = new byte[(bytes.length + bArr.length)];
            System.arraycopy(bytes, 0, obj, 0, bytes.length);
            System.arraycopy(bArr, 0, obj, bytes.length, bArr.length);
            return obj;
        }

        private static String d(String str) {
            if (str == null || !f(str.getBytes())) {
                return str;
            }
            return str.substring(str.indexOf(32) + 1, str.length());
        }

        private static byte[] e(byte[] bArr) {
            if (f(bArr)) {
                return a(bArr, a(bArr, (char) TokenParser.SP) + 1, bArr.length);
            }
            return bArr;
        }

        private static boolean f(byte[] bArr) {
            return bArr != null && bArr.length > 15 && bArr[13] == (byte) 45 && a(bArr, (char) TokenParser.SP) > 14;
        }

        private static String[] g(byte[] bArr) {
            if (!f(bArr)) {
                return null;
            }
            String str = new String(a(bArr, 0, 13));
            String str2 = new String(a(bArr, 14, a(bArr, (char) TokenParser.SP)));
            return new String[]{str, str2};
        }

        private static int a(byte[] bArr, char c) {
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] == c) {
                    return i;
                }
            }
            return -1;
        }

        private static byte[] a(byte[] bArr, int i, int i2) {
            int i3 = i2 - i;
            if (i3 < 0) {
                throw new IllegalArgumentException(i + " > " + i2);
            }
            Object obj = new byte[i3];
            System.arraycopy(bArr, i, obj, 0, Math.min(bArr.length - i, i3));
            return obj;
        }

        private static String a(int i) {
            String str = System.currentTimeMillis() + "";
            while (str.length() < 13) {
                str = "0" + str;
            }
            return str + Constants.ACCEPT_TIME_SEPARATOR_SERVER + i + TokenParser.SP;
        }

        private static byte[] c(Bitmap bitmap) {
            if (bitmap == null) {
                return null;
            }
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }

        private static Bitmap h(byte[] bArr) {
            if (bArr.length == 0) {
                return null;
            }
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }

        private static Bitmap b(Drawable drawable) {
            if (drawable == null) {
                return null;
            }
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Config.ARGB_8888 : Config.RGB_565);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            drawable.draw(canvas);
            return createBitmap;
        }

        private static Drawable d(Bitmap bitmap) {
            if (bitmap == null) {
                return null;
            }
            return new BitmapDrawable(bitmap);
        }
    }

    public static ACache get(Context context) {
        return get(context, "ACache");
    }

    public static ACache get(Context context, String str) {
        return get(new File(context.getCacheDir(), str), 50000000, Integer.MAX_VALUE);
    }

    public static ACache get(File file) {
        return get(file, 50000000, Integer.MAX_VALUE);
    }

    public static ACache get(Context context, long j, int i) {
        return get(new File(context.getCacheDir(), "ACache"), j, i);
    }

    public static ACache get(File file, long j, int i) {
        ACache aCache = (ACache) a.get(file.getAbsoluteFile() + a());
        if (aCache != null) {
            return aCache;
        }
        aCache = new ACache(file, j, i);
        a.put(file.getAbsolutePath() + a(), aCache);
        return aCache;
    }

    private static String a() {
        return "_" + Process.myPid();
    }

    private ACache(File file, long j, int i) {
        if (file.exists() || file.mkdirs()) {
            this.b = new ACacheManager(file, j, i);
            return;
        }
        throw new RuntimeException("can't make dirs in " + file.getAbsolutePath());
    }

    public void put(String str, String str2) {
        BufferedWriter bufferedWriter;
        Exception e;
        Throwable th;
        File a = this.b.b(str);
        BufferedWriter bufferedWriter2 = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(a), 1024);
            try {
                bufferedWriter.write(str2);
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.flush();
                        bufferedWriter.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                if (a != null) {
                    this.b.a(a);
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.flush();
                            bufferedWriter.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    if (a != null) {
                        this.b.a(a);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedWriter2 = bufferedWriter;
                    if (bufferedWriter2 != null) {
                        try {
                            bufferedWriter2.flush();
                            bufferedWriter2.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    if (a != null) {
                        this.b.a(a);
                    }
                    throw th;
                }
            }
        } catch (Exception e5) {
            e = e5;
            bufferedWriter = null;
            e.printStackTrace();
            if (bufferedWriter != null) {
                bufferedWriter.flush();
                bufferedWriter.close();
            }
            if (a != null) {
                this.b.a(a);
            }
        } catch (Throwable th3) {
            th = th3;
            if (bufferedWriter2 != null) {
                bufferedWriter2.flush();
                bufferedWriter2.close();
            }
            if (a != null) {
                this.b.a(a);
            }
            throw th;
        }
    }

    public void put(String str, String str2, int i) {
        put(str, a.b(i, str2));
    }

    public int getAsInt(String str, int i) {
        Object asString = getAsString(str);
        if (!TextUtils.isEmpty(asString) && TextUtils.isDigitsOnly(asString)) {
            try {
                i = Integer.parseInt(asString);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public String getAsString(String str) {
        BufferedReader bufferedReader;
        IOException e;
        Throwable th;
        String str2 = null;
        if (this.b != null) {
            File b = this.b.a(str);
            if (b != null && b.exists()) {
                try {
                    bufferedReader = new BufferedReader(new FileReader(b));
                    try {
                        String str3 = "";
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            str3 = str3 + readLine;
                        }
                        if (a.c(str3)) {
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            remove(str);
                        } else {
                            str2 = a.d(str3);
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e22) {
                                    e22.printStackTrace();
                                }
                            }
                        }
                    } catch (IOException e3) {
                        e22 = e3;
                        try {
                            e22.printStackTrace();
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e222) {
                                    e222.printStackTrace();
                                }
                            }
                            return str2;
                        } catch (Throwable th2) {
                            th = th2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e2222) {
                                    e2222.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                } catch (IOException e4) {
                    e2222 = e4;
                    bufferedReader = null;
                    e2222.printStackTrace();
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    return str2;
                } catch (Throwable th3) {
                    bufferedReader = null;
                    th = th3;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
            }
        }
        return str2;
    }

    public void put(String str, JSONObject jSONObject) {
        put(str, jSONObject.toString());
    }

    public void put(String str, JSONObject jSONObject, int i) {
        put(str, jSONObject.toString(), i);
    }

    public JSONObject getAsJSONObject(String str) {
        try {
            return new JSONObject(getAsString(str));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void put(String str, JSONArray jSONArray) {
        put(str, jSONArray.toString());
    }

    public void put(String str, JSONArray jSONArray, int i) {
        put(str, jSONArray.toString(), i);
    }

    public JSONArray getAsJSONArray(String str) {
        try {
            return new JSONArray(getAsString(str));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void put(String str, byte[] bArr) {
        FileOutputStream fileOutputStream;
        Exception e;
        Throwable th;
        File a = this.b.b(str);
        try {
            fileOutputStream = new FileOutputStream(a);
            try {
                fileOutputStream.write(bArr);
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                this.b.a(a);
            } catch (Exception e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    this.b.a(a);
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    this.b.a(a);
                    throw th;
                }
            }
        } catch (Exception e5) {
            e = e5;
            fileOutputStream = null;
            e.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
            this.b.a(a);
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
            this.b.a(a);
            throw th;
        }
    }

    public void put(String str, byte[] bArr, int i) {
        put(str, a.b(i, bArr));
    }

    public byte[] getAsBinary(String str) {
        RandomAccessFile randomAccessFile;
        Exception e;
        Throwable th;
        byte[] bArr = null;
        RandomAccessFile randomAccessFile2 = null;
        try {
            File b = this.b.a(str);
            if (b.exists()) {
                randomAccessFile = new RandomAccessFile(b, CustomButton.POSITION_RIGHT);
                try {
                    byte[] bArr2 = new byte[((int) randomAccessFile.length())];
                    randomAccessFile.read(bArr2);
                    if (a.d(bArr2)) {
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        remove(str);
                    } else {
                        bArr = a.e(bArr2);
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        e.printStackTrace();
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        return bArr;
                    } catch (Throwable th2) {
                        th = th2;
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e2222) {
                                e2222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } else if (null != null) {
                try {
                    randomAccessFile2.close();
                } catch (IOException e22222) {
                    e22222.printStackTrace();
                }
            }
        } catch (Exception e4) {
            e = e4;
            randomAccessFile = null;
            e.printStackTrace();
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            return bArr;
        } catch (Throwable th3) {
            randomAccessFile = null;
            th = th3;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            throw th;
        }
        return bArr;
    }

    public void put(String str, Serializable serializable) {
        put(str, serializable, -1);
    }

    public void put(String str, Serializable serializable, int i) {
        Exception e;
        Throwable th;
        ObjectOutputStream objectOutputStream;
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            try {
                objectOutputStream.writeObject(serializable);
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                if (i != -1) {
                    put(str, toByteArray, i);
                } else {
                    put(str, toByteArray);
                }
                try {
                    objectOutputStream.close();
                } catch (IOException e2) {
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    try {
                        objectOutputStream.close();
                    } catch (IOException e4) {
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        objectOutputStream.close();
                    } catch (IOException e5) {
                    }
                    throw th;
                }
            }
        } catch (Exception e6) {
            e = e6;
            objectOutputStream = null;
            e.printStackTrace();
            objectOutputStream.close();
        } catch (Throwable th3) {
            th = th3;
            objectOutputStream = null;
            objectOutputStream.close();
            throw th;
        }
    }

    public Object getAsObject(String str) {
        ByteArrayInputStream byteArrayInputStream;
        Exception e;
        InputStream inputStream;
        Object readObject;
        Throwable th;
        InputStream inputStream2 = null;
        byte[] asBinary = getAsBinary(str);
        if (asBinary != null) {
            ObjectInputStream objectInputStream;
            try {
                byteArrayInputStream = new ByteArrayInputStream(asBinary);
                try {
                    objectInputStream = new ObjectInputStream(byteArrayInputStream);
                } catch (Exception e2) {
                    e = e2;
                    inputStream = inputStream2;
                    try {
                        e.printStackTrace();
                        if (byteArrayInputStream != null) {
                            try {
                                byteArrayInputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e32) {
                                e32.printStackTrace();
                            }
                        }
                        return readObject;
                    } catch (Throwable th2) {
                        th = th2;
                        if (byteArrayInputStream != null) {
                            try {
                                byteArrayInputStream.close();
                            } catch (IOException e322) {
                                e322.printStackTrace();
                            }
                        }
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e3222) {
                                e3222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    inputStream = inputStream2;
                    th = th3;
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    throw th;
                }
                try {
                    readObject = objectInputStream.readObject();
                    if (byteArrayInputStream != null) {
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e32222) {
                            e32222.printStackTrace();
                        }
                    }
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e322222) {
                            e322222.printStackTrace();
                        }
                    }
                } catch (Exception e4) {
                    e = e4;
                    e.printStackTrace();
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    return readObject;
                }
            } catch (Exception e5) {
                e = e5;
                objectInputStream = inputStream2;
                byteArrayInputStream = inputStream2;
                e.printStackTrace();
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                return readObject;
            } catch (Throwable th32) {
                objectInputStream = inputStream2;
                byteArrayInputStream = inputStream2;
                th = th32;
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                throw th;
            }
        }
        return readObject;
    }

    public void put(String str, Bitmap bitmap) {
        put(str, a.c(bitmap));
    }

    public void put(String str, Bitmap bitmap, int i) {
        put(str, a.c(bitmap), i);
    }

    public Bitmap getAsBitmap(String str) {
        if (getAsBinary(str) == null) {
            return null;
        }
        return a.h(getAsBinary(str));
    }

    public void put(String str, Drawable drawable) {
        put(str, a.b(drawable));
    }

    public void put(String str, Drawable drawable, int i) {
        put(str, a.b(drawable), i);
    }

    public Drawable getAsDrawable(String str) {
        if (getAsBinary(str) == null) {
            return null;
        }
        return a.d(a.h(getAsBinary(str)));
    }

    public File file(String str) {
        File a = this.b.b(str);
        return a.exists() ? a : null;
    }

    public boolean remove(String str) {
        return this.b.c(str);
    }

    public void clear() {
        this.b.b();
    }
}
