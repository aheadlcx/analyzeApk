package cn.v6.sixrooms.surfaceanim.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.LruCache;
import android.view.WindowManager;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimSceneType;
import java.io.File;

public class AnimSceneResManager {
    public static final float W = 1080.0f;
    private static volatile AnimSceneResManager a;
    private static final int b = ((int) (Runtime.getRuntime().maxMemory() / 16));
    private LruCache<String, Bitmap> c = new b(this, b);
    private float d;
    private int e;
    private int f;
    private float g;
    private Context h;
    private DisplayMetrics i;

    public static AnimSceneResManager getInstance() {
        if (a == null) {
            synchronized (AnimSceneResManager.class) {
                if (a == null) {
                    a = new AnimSceneResManager();
                }
            }
        }
        return a;
    }

    private AnimSceneResManager() {
    }

    public synchronized void setContext(Context context) {
        this.h = context;
        this.d = context.getResources().getDisplayMetrics().density;
        int[] screenSize = getScreenSize();
        this.e = screenSize[0];
        this.f = screenSize[1];
        this.g = ((float) (this.e < this.f ? this.e : this.f)) / 1080.0f;
        this.i = context.getResources().getDisplayMetrics();
    }

    public synchronized Context getContext() {
        return this.h;
    }

    public Bitmap getBitmap(String str) {
        return (Bitmap) this.c.get(str);
    }

    public void addSceneBitmaps(IAnimSceneType iAnimSceneType, int[] iArr, boolean z) {
        iAnimSceneType.getIdentification();
        for (int a : iArr) {
            a(a, z);
        }
    }

    public boolean saveBitmap(String str, Bitmap bitmap) {
        this.c.put(str, bitmap);
        return false;
    }

    public synchronized int getResourceId(String str) {
        int i;
        if (this.h == null) {
            i = 0;
        } else {
            i = getResources().getIdentifier(str, "drawable", this.h.getPackageName());
        }
        return i;
    }

    public synchronized void addBitmap(IAnimSceneType iAnimSceneType, int i, boolean z) {
        iAnimSceneType.getIdentification();
        a(i, z);
    }

    public synchronized void addBitmap(IAnimSceneType iAnimSceneType, int i) {
        iAnimSceneType.getIdentification();
        a(i, false);
    }

    public synchronized void addBitmapWithName(IAnimSceneType iAnimSceneType, String str) {
        int identifier = getResources().getIdentifier(str, "drawable", getContext().getPackageName());
        iAnimSceneType.getIdentification();
        a(identifier, false);
    }

    private void a(int i, boolean z) {
        if (this.h != null) {
            Bitmap bitmap = (Bitmap) this.c.get(String.valueOf(i));
            if (bitmap == null || bitmap.isRecycled()) {
                bitmap = c(i, z);
                if (bitmap != null) {
                    this.c.put(String.valueOf(i), bitmap);
                }
            }
        }
    }

    public synchronized void addBitmap(IAnimSceneType iAnimSceneType, String str) {
        if (!(this.h == null || TextUtils.isEmpty(str))) {
            Bitmap bitmap = (Bitmap) this.c.get(str);
            if (bitmap == null || bitmap.isRecycled()) {
                bitmap = a(str);
                if (bitmap != null) {
                    this.c.put(str, bitmap);
                }
            }
        }
    }

    public synchronized Bitmap getBitmap(IAnimSceneType iAnimSceneType, int i, boolean z) {
        iAnimSceneType.getIdentification();
        return b(i, z);
    }

    public synchronized Bitmap getBitmap(IAnimSceneType iAnimSceneType, int i) {
        iAnimSceneType.getIdentification();
        return b(i, false);
    }

    public synchronized Bitmap getBitmapWithName(IAnimSceneType iAnimSceneType, String str) {
        int identifier;
        identifier = getResources().getIdentifier(str, "drawable", getContext().getPackageName());
        iAnimSceneType.getIdentification();
        return b(identifier, false);
    }

    public synchronized Bitmap getBitmap(IAnimSceneType iAnimSceneType, String str) {
        Bitmap bitmap;
        if (this.h == null || TextUtils.isEmpty(str)) {
            bitmap = null;
        } else {
            bitmap = (Bitmap) this.c.get(str);
            if (bitmap == null) {
                bitmap = a(str);
                this.c.put(str, bitmap);
            }
        }
        return bitmap;
    }

    private Bitmap b(int i, boolean z) {
        if (this.h == null) {
            return null;
        }
        Bitmap bitmap = (Bitmap) this.c.get(String.valueOf(i));
        if (bitmap != null) {
            return bitmap;
        }
        bitmap = c(i, z);
        this.c.put(String.valueOf(i), bitmap);
        return bitmap;
    }

    private Bitmap a(String str) {
        if (!new File(str).exists()) {
            return null;
        }
        Options options = new Options();
        options.inJustDecodeBounds = false;
        options.inScaled = true;
        options.inDensity = 480;
        options.inTargetDensity = this.i.densityDpi;
        options.inScreenDensity = this.i.densityDpi;
        return BitmapFactory.decodeFile(str, options);
    }

    private Bitmap c(int i, boolean z) {
        if (z) {
            return BitmapFactory.decodeResource(this.h.getResources(), i);
        }
        Options options = new Options();
        options.inScaled = false;
        Bitmap decodeResource = BitmapFactory.decodeResource(this.h.getResources(), i, options);
        Matrix matrix = new Matrix();
        matrix.postScale(this.g, this.g);
        return Bitmap.createBitmap(decodeResource, 0, 0, decodeResource.getWidth(), decodeResource.getHeight(), matrix, true);
    }

    public synchronized void clearSceneBitmaps(IAnimSceneType iAnimSceneType) {
    }

    public synchronized void clearAllBitmaps() {
        if (this.c != null && this.c.size() > 0) {
            this.c.evictAll();
        }
    }

    public synchronized Resources getResources() {
        Resources resources;
        if (this.h == null) {
            resources = null;
        } else {
            resources = this.h.getResources();
        }
        return resources;
    }

    public synchronized void release() {
        clearAllBitmaps();
    }

    public int[] getScreenSize() {
        int[] iArr = new int[2];
        if (this.h == null) {
            return iArr;
        }
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        iArr[0] = windowManager.getDefaultDisplay().getWidth();
        iArr[1] = windowManager.getDefaultDisplay().getHeight();
        return iArr;
    }

    public int getScreenW() {
        return this.e;
    }

    public int getScreenY() {
        return this.f;
    }

    public int dp2px(float f) {
        return (int) ((this.d * f) + 0.5f);
    }

    public int px2dp(float f) {
        return (int) ((f / this.d) + 0.5f);
    }

    public int getScalePx(int i, boolean z) {
        if (z) {
            return (int) (((double) (((float) (i / 3)) * this.d)) + 0.5d);
        }
        return (int) (((double) (((float) i) * this.g)) + 0.5d);
    }

    public float getScalePx(float f, boolean z) {
        if (z) {
            return ((f / 3.0f) * this.d) + 0.5f;
        }
        return (this.g * f) + 0.5f;
    }

    public int getScalePx(int i) {
        return getScalePx(i, false);
    }

    public float getScalePx(float f) {
        return getScalePx(f, false);
    }

    public Bitmap drawableToBitmap(int i, int i2, int i3) {
        Drawable drawable = this.h.getResources().getDrawable(i);
        Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Config.ARGB_8888);
        if (createBitmap == null) {
            return null;
        }
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, i2, i3);
        drawable.draw(canvas);
        return createBitmap;
    }

    public Bitmap drawableToBitmap(int i) {
        Drawable drawable = this.h.getResources().getDrawable(i);
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
        if (createBitmap == null) {
            return null;
        }
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public void surfaceChanged() {
        int[] screenSize = getScreenSize();
        this.e = screenSize[0];
        this.f = screenSize[1];
    }

    public synchronized Bitmap getExternalBitmap(String str) {
        Bitmap decodeFile;
        try {
            decodeFile = BitmapFactory.decodeFile(str);
            Matrix matrix = new Matrix();
            matrix.postScale(this.g, this.g);
            decodeFile = Bitmap.createBitmap(decodeFile, 0, 0, decodeFile.getWidth(), decodeFile.getHeight(), matrix, true);
        } catch (Exception e) {
            e.printStackTrace();
            decodeFile = null;
        }
        return decodeFile;
    }
}
