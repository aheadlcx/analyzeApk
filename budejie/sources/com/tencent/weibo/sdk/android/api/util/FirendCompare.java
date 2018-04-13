package com.tencent.weibo.sdk.android.api.util;

import com.tencent.weibo.sdk.android.model.Firend;
import java.util.Comparator;

public class FirendCompare implements Comparator<Firend> {
    public int compare(Firend firend, Firend firend2) {
        return HypyUtil.cn2py(firend.getName()).substring(0, 1).toUpperCase().compareTo(HypyUtil.cn2py(firend2.getName()).substring(0, 1).toUpperCase());
    }
}
