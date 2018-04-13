package com.sprite.ads.nati;

import com.sprite.ads.DataSourceType;
import com.sprite.ads.nati.reporter.Reporter;

public abstract class NativeAdRef implements Reporter {
    public abstract DataSourceType getDataSourceType();

    public abstract int getPosition();

    public abstract String getResType();
}
