package cn.v6.sixrooms.surfaceanim.specialframe.util;

import android.util.SparseArray;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialScene;

public class SpecialSceneConfig {
    private static SparseArray<Class<? extends SpecialScene>> a = new SparseArray();

    public static void registerSpecialScene(int i, Class<? extends SpecialScene> cls) {
        a.put(i, cls);
    }

    public static Class<? extends SpecialScene> getSpecialScene(int i) {
        return (Class) a.get(i);
    }

    public static boolean isNativeSpecialScene(int i) {
        return a.get(i) != null;
    }
}
