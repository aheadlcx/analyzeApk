package cn.v6.sixrooms.utils;

import cn.v6.sixrooms.engine.AppUpdateEngine;

public class AppCount {
    public static void sendAppCountInfo(String str) {
        new AppUpdateEngine(new a()).update(str, "3");
    }
}
