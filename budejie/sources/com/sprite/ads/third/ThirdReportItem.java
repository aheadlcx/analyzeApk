package com.sprite.ads.third;

import com.sprite.ads.DataSourceType;
import com.sprite.ads.internal.bean.data.AdItem;
import java.util.List;

public abstract class ThirdReportItem extends AdItem {
    public abstract String getClickid();

    public DataSourceType getDataSourceType() {
        return DataSourceType.API_GDT;
    }

    public List<String> getDownloadCompleteReport() {
        return null;
    }

    public List<String> getDownloadInstalledReport() {
        return null;
    }

    public List<String> getDownloadStartReport() {
        return null;
    }

    public abstract String getDstlink();

    public abstract String getGdt_targetid();

    public abstract boolean getIsNewApiVersion();
}
