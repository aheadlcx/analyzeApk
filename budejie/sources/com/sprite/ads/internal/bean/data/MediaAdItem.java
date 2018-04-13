package com.sprite.ads.internal.bean.data;

import com.sprite.ads.DataSourceType;
import com.sprite.ads.nati.NativeAdData;

public abstract class MediaAdItem<T> extends NativeAdData {
    private DataSourceType mDataSourceType;
    protected T mThirdMediaData;

    public MediaAdItem(T t, DataSourceType dataSourceType) {
        this.mThirdMediaData = t;
        this.mDataSourceType = dataSourceType;
    }

    public int getAPPStatus() {
        return -1;
    }

    public DataSourceType getDataSourceType() {
        return this.mDataSourceType;
    }

    public abstract String getIconUri();

    public T getThirdMediaData() {
        return this.mThirdMediaData;
    }

    public boolean isAPP() {
        return true;
    }
}
