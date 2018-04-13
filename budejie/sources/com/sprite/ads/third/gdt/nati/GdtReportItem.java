package com.sprite.ads.third.gdt.nati;

import com.sprite.ads.DataSourceType;
import com.sprite.ads.internal.bean.data.AdItem;

public abstract class GdtReportItem extends AdItem {
    public abstract String getClickid();

    public abstract String getDstlink();

    public abstract String getGdt_targetid();

    public DataSourceType getDataSourceType() {
        return DataSourceType.API_GDT;
    }
}
