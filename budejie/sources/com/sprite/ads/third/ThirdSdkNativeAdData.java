package com.sprite.ads.third;

import com.sprite.ads.nati.NativeAdData;
import mtopsdk.mtop.antiattack.CheckCodeDO;

public abstract class ThirdSdkNativeAdData extends NativeAdData {
    public double screenRatio;

    public int getActionType() {
        return 2;
    }

    public String getResType() {
        return CheckCodeDO.CHECKCODE_IMAGE_URL_KEY;
    }
}
