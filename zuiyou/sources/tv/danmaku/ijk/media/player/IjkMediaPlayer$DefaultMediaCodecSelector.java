package tv.danmaku.ijk.media.player;

import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class IjkMediaPlayer$DefaultMediaCodecSelector implements IjkMediaPlayer$OnMediaCodecSelectListener {
    public static final IjkMediaPlayer$DefaultMediaCodecSelector sInstance = new IjkMediaPlayer$DefaultMediaCodecSelector();

    @TargetApi(16)
    public String onMediaCodecSelect(IMediaPlayer iMediaPlayer, String str, int i, int i2) {
        if (VERSION.SDK_INT < 16) {
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Log.i(IjkMediaPlayer.access$100(), String.format(Locale.US, "onSelectCodec: mime=%s, profile=%d, level=%d", new Object[]{str, Integer.valueOf(i), Integer.valueOf(i2)}));
        ArrayList arrayList = new ArrayList();
        int codecCount = MediaCodecList.getCodecCount();
        for (int i3 = 0; i3 < codecCount; i3++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i3);
            Log.d(IjkMediaPlayer.access$100(), String.format(Locale.US, "  found codec: %s", new Object[]{codecInfoAt.getName()}));
            if (!codecInfoAt.isEncoder()) {
                String[] supportedTypes = codecInfoAt.getSupportedTypes();
                if (supportedTypes != null) {
                    for (Object obj : supportedTypes) {
                        if (!TextUtils.isEmpty(obj)) {
                            Log.d(IjkMediaPlayer.access$100(), String.format(Locale.US, "    mime: %s", new Object[]{obj}));
                            if (obj.equalsIgnoreCase(str)) {
                                IjkMediaCodecInfo ijkMediaCodecInfo = IjkMediaCodecInfo.setupCandidate(codecInfoAt, str);
                                if (ijkMediaCodecInfo != null) {
                                    arrayList.add(ijkMediaCodecInfo);
                                    Log.i(IjkMediaPlayer.access$100(), String.format(Locale.US, "candidate codec: %s rank=%d", new Object[]{codecInfoAt.getName(), Integer.valueOf(ijkMediaCodecInfo.mRank)}));
                                    ijkMediaCodecInfo.dumpProfileLevels(str);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        IjkMediaCodecInfo ijkMediaCodecInfo2 = (IjkMediaCodecInfo) arrayList.get(0);
        Iterator it = arrayList.iterator();
        IjkMediaCodecInfo ijkMediaCodecInfo3 = ijkMediaCodecInfo2;
        while (it.hasNext()) {
            ijkMediaCodecInfo2 = (IjkMediaCodecInfo) it.next();
            if (ijkMediaCodecInfo2.mRank <= ijkMediaCodecInfo3.mRank) {
                ijkMediaCodecInfo2 = ijkMediaCodecInfo3;
            }
            ijkMediaCodecInfo3 = ijkMediaCodecInfo2;
        }
        if (ijkMediaCodecInfo3.mRank < 600) {
            Log.w(IjkMediaPlayer.access$100(), String.format(Locale.US, "unaccetable codec: %s", new Object[]{ijkMediaCodecInfo3.mCodecInfo.getName()}));
            return null;
        }
        Log.i(IjkMediaPlayer.access$100(), String.format(Locale.US, "selected codec: %s rank=%d", new Object[]{ijkMediaCodecInfo3.mCodecInfo.getName(), Integer.valueOf(ijkMediaCodecInfo3.mRank)}));
        return ijkMediaCodecInfo3.mCodecInfo.getName();
    }
}
