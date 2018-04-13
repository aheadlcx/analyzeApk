package com.sprite.ads.third.gdt.media;

import com.qq.e.ads.nativ.NativeMediaADData;
import com.sprite.ads.DataSourceType;
import com.sprite.ads.internal.bean.data.MediaAdItem;

public class GdtMediaAdItem extends MediaAdItem<NativeMediaADData> {
    public GdtMediaAdItem(NativeMediaADData nativeMediaADData) {
        super(nativeMediaADData, DataSourceType.SDK_GDT);
    }

    public String getTitle() {
        return ((NativeMediaADData) this.mThirdMediaData).getTitle();
    }

    public String getUrl() {
        return null;
    }

    public String getDesc() {
        return ((NativeMediaADData) this.mThirdMediaData).getDesc();
    }

    public String getPic() {
        return ((NativeMediaADData) this.mThirdMediaData).getImgUrl();
    }

    public String getMovie() {
        return null;
    }

    public String getResType() {
        return ((NativeMediaADData) this.mThirdMediaData).isVideoAD() ? "moive" : null;
    }

    public int getActionType() {
        return 2;
    }

    public boolean isAPP() {
        return ((NativeMediaADData) this.mThirdMediaData).isAPP();
    }

    public int getAPPStatus() {
        return ((NativeMediaADData) this.mThirdMediaData).getAPPStatus();
    }

    public String getIconUri() {
        return ((NativeMediaADData) this.mThirdMediaData).getIconUrl();
    }
}
