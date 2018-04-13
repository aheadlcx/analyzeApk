package com.alipay.sdk.util;

import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import qsbk.app.live.ui.NetworkDiagnosisActivity;

public enum d {
    a(0, NetworkDiagnosisActivity.NETWORKTYPE_WIFI),
    NETWORK_TYPE_1(1, "unicom2G"),
    NETWORK_TYPE_2(2, "mobile2G"),
    NETWORK_TYPE_4(4, "telecom2G"),
    NETWORK_TYPE_5(5, "telecom3G"),
    NETWORK_TYPE_6(6, "telecom3G"),
    NETWORK_TYPE_12(12, "telecom3G"),
    NETWORK_TYPE_8(8, "unicom3G"),
    NETWORK_TYPE_3(3, "unicom3G"),
    NETWORK_TYPE_13(13, "LTE"),
    NETWORK_TYPE_11(11, "IDEN"),
    NETWORK_TYPE_9(9, "HSUPA"),
    NETWORK_TYPE_10(10, "HSPA"),
    NETWORK_TYPE_15(15, "HSPAP"),
    NONE(-1, IXAdSystemUtils.NT_NONE);
    
    public String p;
    private int q;

    private d(int i, String str) {
        this.q = i;
        this.p = str;
    }

    public static d a(int i) {
        for (d dVar : values()) {
            if (dVar.q == i) {
                return dVar;
            }
        }
        return NONE;
    }
}
