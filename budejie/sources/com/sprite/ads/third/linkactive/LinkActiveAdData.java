package com.sprite.ads.third.linkactive;

import com.sprite.ads.internal.bean.data.LinkActiveAdItem;
import com.sprite.ads.nati.NativeAdData;
import mtopsdk.mtop.antiattack.CheckCodeDO;

public class LinkActiveAdData extends NativeAdData {
    private LinkActiveAdItem mAdItem;

    public LinkActiveAdData(LinkActiveAdItem linkActiveAdItem) {
        this.mAdItem = linkActiveAdItem;
    }

    public int getActionType() {
        return 1;
    }

    public LinkActiveAdItem getAdItem() {
        return this.mAdItem;
    }

    public String getDesc() {
        return this.mAdItem.title;
    }

    public String getMovie() {
        return "";
    }

    public String getPic() {
        return this.mAdItem.img_url;
    }

    public String getResType() {
        return CheckCodeDO.CHECKCODE_IMAGE_URL_KEY;
    }

    public String getTitle() {
        return this.mAdItem.title;
    }

    public String getUrl() {
        return "";
    }
}
