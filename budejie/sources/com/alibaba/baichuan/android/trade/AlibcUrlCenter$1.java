package com.alibaba.baichuan.android.trade;

import com.alibaba.baichuan.android.trade.AlibcContext.Environment;

/* synthetic */ class AlibcUrlCenter$1 {
    static final /* synthetic */ int[] a = new int[Environment.values().length];

    static {
        try {
            a[Environment.ONLINE.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[Environment.PRE.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[Environment.TEST.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
