package com.sprite.ads.third.baidu.nati;

import com.baidu.mobad.feeds.NativeResponse;
import com.sprite.ads.third.ThirdSdkNativeAdData;

public class BaiduNativeAdData extends ThirdSdkNativeAdData {
    NativeResponse nativeResponse;

    public BaiduNativeAdData(NativeResponse nativeResponse) {
        this.nativeResponse = nativeResponse;
    }

    public String getTitle() {
        return this.nativeResponse.getTitle();
    }

    public String getUrl() {
        return "";
    }

    public String getDesc() {
        return this.nativeResponse.getDesc();
    }

    public String getPic() {
        return this.nativeResponse.getImageUrl();
    }

    public String getMovie() {
        return "";
    }
}
