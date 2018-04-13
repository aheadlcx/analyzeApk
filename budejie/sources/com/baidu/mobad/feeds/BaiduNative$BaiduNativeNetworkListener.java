package com.baidu.mobad.feeds;

import java.util.List;

public interface BaiduNative$BaiduNativeNetworkListener {
    void onNativeFail(NativeErrorCode nativeErrorCode);

    void onNativeLoad(List<NativeResponse> list);
}
