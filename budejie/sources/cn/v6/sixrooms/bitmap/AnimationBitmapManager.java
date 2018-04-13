package cn.v6.sixrooms.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import cn.v6.sixrooms.animation.GiftAnimationManager.CallBackGiftBitmap;
import cn.v6.sixrooms.surfaceanim.util.FrescoUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class AnimationBitmapManager {
    private static int a = 0;
    private static HashMap<Integer, HashMap<Object, Bitmap>> b = new HashMap();
    private static Options c = null;
    private static HashMap<String, ArrayList<Integer>> d = new HashMap();

    public static int getModelId() {
        int i = a + 1;
        a = i;
        return i;
    }

    public static void addBitmap(int i, String str, Bitmap bitmap) {
        if (b.containsKey(Integer.valueOf(i))) {
            ((HashMap) b.get(Integer.valueOf(i))).put(str, bitmap);
        } else {
            bitmap.recycle();
        }
    }

    public static Options getOptions() {
        if (c == null) {
            Options options = new Options();
            c = options;
            options.inPreferredConfig = Config.ARGB_8888;
            c.inPurgeable = true;
            c.inDither = true;
            c.inTargetDensity = 160;
        }
        return c;
    }

    private static Bitmap a(Context context, int i) {
        getOptions();
        return BitmapFactory.decodeResource(context.getResources(), i, c);
    }

    public static Bitmap getBitmapById(Context context, int i, int i2) {
        if (b.containsKey(Integer.valueOf(i))) {
            HashMap hashMap = (HashMap) b.get(Integer.valueOf(i));
            if (hashMap.containsKey(Integer.valueOf(i2))) {
                return (Bitmap) hashMap.get(Integer.valueOf(i2));
            }
            Bitmap a = a(context, i2);
            hashMap.put(Integer.valueOf(i2), a);
            return a;
        }
        HashMap hashMap2 = new HashMap();
        Bitmap a2 = a(context, i2);
        hashMap2.put(Integer.valueOf(i2), a2);
        b.put(Integer.valueOf(i), hashMap2);
        return a2;
    }

    public static Bitmap getBitmapByUrl(Context context, CallBackGiftBitmap callBackGiftBitmap, int i, String str) {
        HashMap hashMap;
        if (b.containsKey(Integer.valueOf(i))) {
            hashMap = (HashMap) b.get(Integer.valueOf(i));
            if (hashMap.containsKey(str)) {
                return (Bitmap) hashMap.get(str);
            }
        }
        hashMap = new HashMap();
        b.put(Integer.valueOf(i), hashMap);
        File fileFromDiskCache = FrescoUtil.getFileFromDiskCache(str);
        if (fileFromDiskCache != null && fileFromDiskCache.exists()) {
            Bitmap decodeFile = BitmapFactory.decodeFile(fileFromDiskCache.getPath(), getOptions());
            if (decodeFile != null) {
                hashMap.put(str, decodeFile);
                return decodeFile;
            }
        }
        ArrayList arrayList;
        if (d.containsKey(str)) {
            arrayList = (ArrayList) d.get(str);
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (((Integer) arrayList.get(i2)).intValue() == i) {
                    return null;
                }
            }
            arrayList.add(Integer.valueOf(i));
        } else {
            arrayList = new ArrayList();
            arrayList.add(Integer.valueOf(i));
            d.put(str, arrayList);
        }
        new ThreadGetAnimationBitmap(callBackGiftBitmap, str, i).start();
        return null;
    }

    public static void recycleAllBitmap() {
        Iterator it = b.keySet().iterator();
        while (it.hasNext()) {
            int intValue = ((Integer) it.next()).intValue();
            HashMap hashMap = (HashMap) b.get(Integer.valueOf(intValue));
            b.remove(Integer.valueOf(intValue));
            Iterator it2 = b.keySet().iterator();
            for (Iterator it3 = hashMap.keySet().iterator(); it3.hasNext(); it3 = hashMap.keySet().iterator()) {
                Object next = it3.next();
                Bitmap bitmap = (Bitmap) hashMap.get(next);
                hashMap.remove(next);
                bitmap.recycle();
            }
            hashMap.clear();
            it = it2;
        }
        b.clear();
    }

    public static void recycleModelBitmap(int i) {
        if (b.containsKey(Integer.valueOf(i))) {
            HashMap hashMap = (HashMap) b.get(Integer.valueOf(i));
            b.remove(Integer.valueOf(i));
            for (Iterator it = hashMap.keySet().iterator(); it.hasNext(); it = hashMap.keySet().iterator()) {
                Object next = it.next();
                Bitmap bitmap = (Bitmap) hashMap.get(next);
                hashMap.remove(next);
                bitmap.recycle();
            }
            hashMap.clear();
        }
    }
}
