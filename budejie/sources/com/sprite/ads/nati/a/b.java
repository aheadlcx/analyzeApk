package com.sprite.ads.nati.a;

import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.nati.NativeAdData;

public class b extends NativeAdData {
    AdItem a;

    public b(AdItem adItem) {
        this.a = adItem;
    }

    public int getActionType() {
        return 2;
    }

    public String getDesc() {
        return this.a.getDesc();
    }

    public String getMovie() {
        return this.a.getMovie();
    }

    public String getPic() {
        return this.a.getPic();
    }

    public String getResType() {
        return this.a.getResType();
    }

    public String getTitle() {
        return this.a.getTitle();
    }

    public String getUrl() {
        return this.a.getUrl();
    }
}
