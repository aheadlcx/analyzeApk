package cn.v6.sixrooms.base;

import cn.v6.sixrooms.base.VLDebug.VLLogLevel;

final /* synthetic */ class e {
    static final /* synthetic */ int[] a = new int[VLLogLevel.values().length];

    static {
        try {
            a[VLLogLevel.None.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[VLLogLevel.Error.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[VLLogLevel.Warning.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[VLLogLevel.Info.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[VLLogLevel.Debug.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            a[VLLogLevel.Verbose.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
    }
}
