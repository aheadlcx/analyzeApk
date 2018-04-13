package com.sprite.ads.third.gdt.nati;

import com.qq.e.ads.nativ.NativeADDataRef;
import com.sprite.ads.third.ThirdSdkNativeAdData;

public class GdtNativeAdData extends ThirdSdkNativeAdData {
    NativeADDataRef adDataRef;

    public GdtNativeAdData(NativeADDataRef nativeADDataRef) {
        this.adDataRef = nativeADDataRef;
    }

    public String getTitle() {
        return this.adDataRef.getTitle();
    }

    public String getUrl() {
        return "";
    }

    public String getDesc() {
        return this.adDataRef.getDesc();
    }

    public String getPic() {
        return this.adDataRef.getImgUrl();
    }

    public String getMovie() {
        return "";
    }
}
