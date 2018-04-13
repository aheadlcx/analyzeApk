package cn.v6.sixrooms.surfaceanim.protocolframe;

import android.util.SparseArray;

public class ProtocolSceneConfig {
    private static SparseArray<ProtocolScene> a = new SparseArray();

    public static void registerSpecialScene(int i, ProtocolScene protocolScene) {
        a.put(i, protocolScene);
    }

    public static ProtocolScene getSpecialScene(int i) {
        return (ProtocolScene) a.get(i);
    }

    public static boolean isNativeSpecialScene(int i) {
        return a.get(i) != null;
    }

    public static void clearSpecialScenes() {
        a.clear();
    }
}
