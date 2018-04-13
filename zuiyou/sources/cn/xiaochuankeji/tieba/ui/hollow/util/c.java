package cn.xiaochuankeji.tieba.ui.hollow.util;

import cn.xiaochuankeji.tieba.background.utils.d.a;

public class c {
    public static long a(long j) {
        return j / 1000;
    }

    public static String b(long j) {
        if (j < 10) {
            "0" + j;
        } else {
            "" + j;
        }
        return j + "\"";
    }

    public static String a(int i) {
        return (i / 60 < 10 ? "0" + (i / 60) : "" + (i / 60)) + ":" + (i % 60 < 10 ? "0" + (i % 60) : "" + (i % 60));
    }

    public static String c(long j) {
        return a.a("/img/png/id/", j, "");
    }
}
