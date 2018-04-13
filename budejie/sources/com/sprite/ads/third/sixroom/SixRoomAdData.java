package com.sprite.ads.third.sixroom;

import com.sprite.ads.internal.bean.data.LiveItem;
import com.sprite.ads.nati.NativeAdData;
import java.util.List;

public class SixRoomAdData extends NativeAdData {
    private List<LiveItem> liveItems;

    public int getActionType() {
        return 3;
    }

    public String getDesc() {
        return "";
    }

    public List<LiveItem> getLiveItems() {
        return this.liveItems;
    }

    public String getMovie() {
        return "";
    }

    public String getPic() {
        return "";
    }

    public String getResType() {
        return "live";
    }

    public String getTitle() {
        return "";
    }

    public String getUrl() {
        return "";
    }

    public void setLiveItems(List<LiveItem> list) {
        this.liveItems = list;
    }
}
