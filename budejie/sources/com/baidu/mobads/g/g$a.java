package com.baidu.mobads.g;

import com.baidu.mobads.utils.XAdSDKFoundationFacade;

public final class g$a extends Exception {
    public g$a(String str) {
        XAdSDKFoundationFacade.getInstance().getAdLogger().e(str);
    }
}
