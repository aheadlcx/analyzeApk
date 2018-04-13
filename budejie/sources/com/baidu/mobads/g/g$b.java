package com.baidu.mobads.g;

import com.baidu.mobads.utils.XAdSDKFoundationFacade;

protected final class g$b extends Exception {
    public g$b(String str) {
        XAdSDKFoundationFacade.getInstance().getAdLogger().e(str);
    }
}
