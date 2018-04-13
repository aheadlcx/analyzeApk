package com.umeng.update.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.facebook.cache.disk.DefaultDiskStorage.FileType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Stack;
import java.util.WeakHashMap;
import u.upd.m;

public class j {
    public static boolean a = false;
    private static final String b = j.class.getName();
    private static final long c = 52428800;
    private static final long d = 10485760;
    private static final long e = 1800000;
    private static final Map<ImageView, String> f = Collections.synchronizedMap(new WeakHashMap());
    private static Thread g;

    /* renamed from: com.umeng.update.net.j$1 */
    class AnonymousClass1 implements Runnable {
        private final /* synthetic */ File a;
        private final /* synthetic */ long b;

        AnonymousClass1(File file, long j) {
            this.a = file;
            this.b = j;
        }

        public void run() {
            j.b(this.a, this.b);
            j.g = null;
        }
    }

    public interface a {
        void a(b bVar);

        void a(u.upd.f.a aVar);
    }

    public enum b {
        BIND_FORM_CACHE,
        BIND_FROM_NET
    }

    static class c extends AsyncTask<Object, Integer, Drawable> {
        private Context a;
        private String b;
        private ImageView c;
        private b d;
        private boolean e;
        private a f;
        private Animation g;
        private boolean h;
        private File i;

        protected /* synthetic */ Object doInBackground(Object... objArr) {
            return a(objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Drawable) obj);
        }

        public c(Context context, ImageView imageView, String str, b bVar, File file, boolean z, a aVar, Animation animation, boolean z2) {
            this.i = file;
            this.a = context;
            this.b = str;
            this.f = aVar;
            this.d = bVar;
            this.e = z;
            this.g = animation;
            this.c = imageView;
            this.h = z2;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            if (this.f != null) {
                this.f.a(this.d);
            }
        }

        protected void a(Drawable drawable) {
            j.b(this.a, this.c, drawable, this.e, this.f, this.g, this.h, this.b);
        }

        protected Drawable a(Object... objArr) {
            if (j.a) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Drawable drawable;
            if (this.i == null || !this.i.exists()) {
                try {
                    j.a(this.a, this.b);
                    File b = j.b(this.a, this.b);
                    if (b == null || !b.exists()) {
                        drawable = null;
                    } else {
                        drawable = j.c(b.getAbsolutePath());
                    }
                    u.upd.b.c(j.b, "get drawable from net else file.");
                    return drawable;
                } catch (Exception e2) {
                    u.upd.b.d(j.b, e2.toString(), e2);
                    return null;
                }
            }
            drawable = j.c(this.i.getAbsolutePath());
            if (drawable == null) {
                this.i.delete();
            }
            u.upd.b.c(j.b, "get drawable from cacheFile.");
            return drawable;
        }
    }

    private static String b(String str) {
        int lastIndexOf = str.lastIndexOf(".");
        String str2 = "";
        if (lastIndexOf >= 0) {
            str2 = str.substring(lastIndexOf);
        }
        return m.a(str) + str2;
    }

    public static String a(Context context, String str) {
        File file;
        Exception e;
        if (m.d(str)) {
            return null;
        }
        try {
            Object canonicalPath;
            long j;
            String str2 = b(str) + FileType.TEMP;
            if (u.upd.a.a()) {
                canonicalPath = Environment.getExternalStorageDirectory().getCanonicalPath();
                j = c;
            } else {
                canonicalPath = context.getCacheDir().getCanonicalPath();
                j = d;
            }
            File file2 = new File(new StringBuilder(String.valueOf(canonicalPath)).append(com.umeng.update.a.a).toString());
            a(file2, j, (long) e);
            file = new File(file2, str2);
            try {
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                InputStream inputStream = (InputStream) new URL(str).openConnection().getContent();
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        fileOutputStream.flush();
                        inputStream.close();
                        fileOutputStream.close();
                        File file3 = new File(file.getParent(), file.getName().replace(FileType.TEMP, ""));
                        file.renameTo(file3);
                        u.upd.b.a(b, "download img[" + str + "]  to " + file3.getCanonicalPath());
                        return file3.getCanonicalPath();
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            file = null;
            u.upd.b.a(b, new StringBuilder(String.valueOf(e.getStackTrace().toString())).append("\t url:\t").append(m.a).append(str).toString());
            if (file != null && file.exists()) {
                file.deleteOnExit();
            }
            return null;
        }
    }

    public static File a(String str, Context context, boolean[] zArr) throws IOException {
        File file;
        if (u.upd.a.a()) {
            file = new File(new StringBuilder(String.valueOf(Environment.getExternalStorageDirectory().getCanonicalPath())).append(com.umeng.update.a.a).append(str).toString());
            file.mkdirs();
            if (file.exists()) {
                zArr[0] = true;
                return file;
            }
        }
        String absolutePath = context.getCacheDir().getAbsolutePath();
        new File(absolutePath).mkdir();
        a(absolutePath, 505, -1, -1);
        String stringBuilder = new StringBuilder(String.valueOf(absolutePath)).append(com.umeng.update.a.b).toString();
        new File(stringBuilder).mkdir();
        a(stringBuilder, 505, -1, -1);
        file = new File(stringBuilder);
        zArr[0] = false;
        return file;
    }

    public static boolean a(String str, int i, int i2, int i3) {
        try {
            Class.forName("android.os.FileUtils").getMethod("setPermissions", new Class[]{String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE}).invoke(null, new Object[]{str, Integer.valueOf(i), Integer.valueOf(-1), Integer.valueOf(-1)});
            return true;
        } catch (Exception e) {
            u.upd.b.b(b, "error when set permissions:", e);
        } catch (Exception e2) {
            u.upd.b.b(b, "error when set permissions:", e2);
        } catch (Exception e22) {
            u.upd.b.b(b, "error when set permissions:", e22);
        } catch (Exception e222) {
            u.upd.b.b(b, "error when set permissions:", e222);
        } catch (Exception e2222) {
            u.upd.b.b(b, "error when set permissions:", e2222);
        }
        return false;
    }

    public static boolean a(String str, int i) {
        int i2 = 432;
        if ((i & 1) != 0) {
            i2 = 436;
        }
        if ((i & 2) != 0) {
            i2 |= 2;
        }
        return a(str, i2, -1, -1);
    }

    public static void a(File file, long j, long j2) throws IOException {
        if (file.exists()) {
            if (a(file.getCanonicalFile()) > j) {
                if (g == null) {
                    g = new Thread(new AnonymousClass1(file, j2));
                }
                synchronized (g) {
                    g.start();
                }
            }
        } else if (!file.mkdirs()) {
            u.upd.b.b(b, "Failed to create directory" + file.getAbsolutePath() + ". Check permission. Make sure WRITE_EXTERNAL_STORAGE is added in your Manifest.xml");
        }
    }

    private static long a(File file) {
        if (file == null || !file.exists() || !file.isDirectory()) {
            return 0;
        }
        Stack stack = new Stack();
        stack.clear();
        stack.push(file);
        long j = 0;
        while (!stack.isEmpty()) {
            long j2 = j;
            for (File file2 : ((File) stack.pop()).listFiles()) {
                if (!file2.isDirectory()) {
                    j2 += file2.length();
                }
            }
            j = j2;
        }
        return j;
    }

    private static void b(File file, long j) {
        if (file != null && file.exists() && file.canWrite() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (!file2.isDirectory() && new Date().getTime() - file2.lastModified() > j) {
                    file2.delete();
                }
            }
        }
    }

    protected static File b(Context context, String str) throws IOException {
        Object canonicalPath;
        String b = b(str);
        if (u.upd.a.a()) {
            canonicalPath = Environment.getExternalStorageDirectory().getCanonicalPath();
        } else {
            canonicalPath = context.getCacheDir().getCanonicalPath();
        }
        File file = new File(new File(new StringBuilder(String.valueOf(canonicalPath)).append(com.umeng.update.a.a).toString()), b);
        return file.exists() ? file : null;
    }

    public static void a(Context context, ImageView imageView, String str, boolean z) {
        a(context, imageView, str, z, null, null, false);
    }

    public static void a(Context context, ImageView imageView, String str, boolean z, a aVar) {
        a(context, imageView, str, z, aVar, null, false);
    }

    public static void a(Context context, ImageView imageView, String str, boolean z, a aVar, Animation animation) {
        a(context, imageView, str, z, aVar, null, false);
    }

    public static void a(Context context, ImageView imageView, String str, boolean z, a aVar, Animation animation, boolean z2) {
        if (imageView != null) {
            f.put(imageView, str);
            try {
                File b = b(context, str);
                if (b == null || !b.exists() || a) {
                    new c(context, imageView, str, b.BIND_FROM_NET, null, z, aVar, animation, z2).execute(new Object[0]);
                    return;
                }
                if (aVar != null) {
                    aVar.a(b.BIND_FORM_CACHE);
                }
                Drawable c = c(b.getAbsolutePath());
                if (c == null) {
                    b.delete();
                }
                b(context, imageView, c, z, aVar, animation, z2, str);
            } catch (Exception e) {
                u.upd.b.b(b, "", e);
                if (aVar != null) {
                    aVar.a(u.upd.f.a.b);
                }
            }
        }
    }

    private static boolean a(ImageView imageView, String str) {
        String str2 = (String) f.get(imageView);
        if (str2 == null || str2.equals(str)) {
            return false;
        }
        return true;
    }

    private static synchronized void b(Context context, ImageView imageView, Drawable drawable, boolean z, a aVar, Animation animation, boolean z2, String str) {
        synchronized (j.class) {
            if (z2 && drawable != null) {
                try {
                    drawable = new BitmapDrawable(a(((BitmapDrawable) drawable).getBitmap()));
                } catch (Exception e) {
                    u.upd.b.b(b, "bind failed", e);
                    if (aVar != null) {
                        aVar.a(u.upd.f.a.b);
                    }
                }
            }
            if (drawable == null || imageView == null) {
                if (aVar != null) {
                    aVar.a(u.upd.f.a.b);
                }
                u.upd.b.d(b, "bind drawable failed. drawable [" + drawable + "]  imageView[+" + imageView + "+]");
            } else {
                if (!a(imageView, str)) {
                    if (z) {
                        imageView.setBackgroundDrawable(drawable);
                    } else {
                        imageView.setImageDrawable(drawable);
                    }
                    if (animation != null) {
                        imageView.startAnimation(animation);
                    }
                    if (aVar != null) {
                        aVar.a(u.upd.f.a.a);
                    }
                } else if (aVar != null) {
                    aVar.a(u.upd.f.a.b);
                }
            }
        }
    }

    private static Drawable c(String str) {
        Drawable drawable = null;
        try {
            drawable = Drawable.createFromPath(str);
        } catch (OutOfMemoryError e) {
            u.upd.b.d(b, "Resutil fetchImage OutOfMemoryError:" + e.toString());
        }
        return drawable;
    }

    private static Bitmap a(Bitmap bitmap) {
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            RectF rectF = new RectF(rect);
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(-12434878);
            canvas.drawRoundRect(rectF, (float) (bitmap.getWidth() / 6), (float) (bitmap.getHeight() / 6), paint);
            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
            bitmap.recycle();
            return createBitmap;
        } catch (OutOfMemoryError e) {
            u.upd.b.d(b, "Cant`t create round corner bitmap. [OutOfMemoryError] ");
            return null;
        }
    }
}
