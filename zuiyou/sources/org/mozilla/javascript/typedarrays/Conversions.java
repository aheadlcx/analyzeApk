package org.mozilla.javascript.typedarrays;

import android.support.v4.view.InputDeviceCompat;
import org.mozilla.javascript.ScriptRuntime;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class Conversions {
    public static final int EIGHT_BIT = 256;
    public static final int SIXTEEN_BIT = 65536;
    public static final long THIRTYTWO_BIT = 4294967296L;

    public static int toInt8(Object obj) {
        int intValue;
        if (obj instanceof Integer) {
            intValue = ((Integer) obj).intValue();
        } else {
            intValue = ScriptRuntime.toInt32(obj);
        }
        intValue %= 256;
        return intValue >= 128 ? intValue + InputDeviceCompat.SOURCE_ANY : intValue;
    }

    public static int toUint8(Object obj) {
        int intValue;
        if (obj instanceof Integer) {
            intValue = ((Integer) obj).intValue();
        } else {
            intValue = ScriptRuntime.toInt32(obj);
        }
        return intValue % 256;
    }

    public static int toUint8Clamp(Object obj) {
        double toNumber = ScriptRuntime.toNumber(obj);
        if (toNumber <= 0.0d) {
            return 0;
        }
        if (toNumber >= 255.0d) {
            return 255;
        }
        double floor = Math.floor(toNumber);
        if (floor + 0.5d < toNumber) {
            return (int) (1.0d + floor);
        }
        if (toNumber < floor + 0.5d) {
            return (int) floor;
        }
        if (((int) floor) % 2 != 0) {
            return ((int) floor) + 1;
        }
        return (int) floor;
    }

    public static int toInt16(Object obj) {
        int intValue;
        if (obj instanceof Integer) {
            intValue = ((Integer) obj).intValue();
        } else {
            intValue = ScriptRuntime.toInt32(obj);
        }
        intValue %= 65536;
        return intValue >= 32768 ? intValue - 65536 : intValue;
    }

    public static int toUint16(Object obj) {
        int intValue;
        if (obj instanceof Integer) {
            intValue = ((Integer) obj).intValue();
        } else {
            intValue = ScriptRuntime.toInt32(obj);
        }
        return intValue % 65536;
    }

    public static int toInt32(Object obj) {
        long toNumber = ((long) ScriptRuntime.toNumber(obj)) % 4294967296L;
        if (toNumber >= IjkMediaMeta.AV_CH_WIDE_LEFT) {
            toNumber -= 4294967296L;
        }
        return (int) toNumber;
    }

    public static long toUint32(Object obj) {
        return ((long) ScriptRuntime.toNumber(obj)) % 4294967296L;
    }
}
