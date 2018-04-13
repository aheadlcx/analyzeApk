package com.umeng.commonsdk.statistics.idtracking;

import com.umeng.commonsdk.statistics.common.DeviceConfig;

public class j extends a {
    public j() {
        super("serial");
    }

    public String f() {
        return DeviceConfig.getSerial();
    }
}
