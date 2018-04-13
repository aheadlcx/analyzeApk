package qsbk.app.ad.feedsad.gdtad;

import com.qq.e.ads.nativ.NativeMediaADData;
import java.lang.ref.WeakReference;

public class GdtVideoManager {
    private static WeakReference<NativeMediaADData> lastRef = null;

    private GdtVideoManager() {
    }

    public static NativeMediaADData getLastRef() {
        return lastRef != null ? (NativeMediaADData) lastRef.get() : null;
    }

    public static void onMediaViewPlay(NativeMediaADData nativeMediaADData) {
        if (lastRef != null) {
            NativeMediaADData nativeMediaADData2 = (NativeMediaADData) lastRef.get();
            if (!(nativeMediaADData2 == null || nativeMediaADData2.equals(nativeMediaADData) || !nativeMediaADData2.isPlaying())) {
                nativeMediaADData2.stop();
            }
        }
        lastRef = new WeakReference(nativeMediaADData);
    }

    public static void onMediaViewStop(NativeMediaADData nativeMediaADData) {
        if (lastRef != null) {
            NativeMediaADData nativeMediaADData2 = (NativeMediaADData) lastRef.get();
            if (nativeMediaADData2 != null && nativeMediaADData2.equals(nativeMediaADData)) {
                lastRef = null;
            }
        }
    }
}
