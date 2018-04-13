package com.sprite.ads;

public enum DataSourceType {
    SELF_SIX_ROOM("self.sixroomslive"),
    SELF_HUAXI_BOOK("self.huaxibook"),
    SELF("self"),
    SDK_GDT("sdk.gdt"),
    API_GDT("api"),
    API_ACTIVE("api.linkactive"),
    SDK_BAIDU("sdk.baidu"),
    SDK_360("sdk.360"),
    SDK_MINTEGRAL("sdk.mobvista"),
    SDK_CM("sdk.cm"),
    SDK_TOUTIAO("sdk.toutiao");
    
    String value;

    private DataSourceType(String str) {
        this.value = str;
    }

    public static DataSourceType getDataSourceType(String str) {
        return str.contains(SDK_BAIDU.getValue()) ? SDK_BAIDU : str.contains(SDK_GDT.getValue()) ? SDK_GDT : str.contains(API_ACTIVE.getValue()) ? API_ACTIVE : str.contains(API_GDT.getValue()) ? API_GDT : str.contains(SDK_360.getValue()) ? SDK_360 : str.contains(SDK_CM.getValue()) ? SDK_CM : str.contains(SDK_MINTEGRAL.getValue()) ? SDK_MINTEGRAL : str.contains(SDK_TOUTIAO.getValue()) ? SDK_TOUTIAO : str.contains(SELF_SIX_ROOM.getValue()) ? SELF_SIX_ROOM : str.contains(SELF_HUAXI_BOOK.getValue()) ? SELF_HUAXI_BOOK : SELF;
    }

    public String getValue() {
        return this.value;
    }
}
