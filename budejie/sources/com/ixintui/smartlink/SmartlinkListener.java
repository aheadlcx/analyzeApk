package com.ixintui.smartlink;

public interface SmartlinkListener {
    void onError(String str);

    void onParamsReturn(ParamInfo paramInfo);
}
