package com.meizu.cloud.pushsdk.pushtracer.dataload;

import java.util.Map;

public interface DataLoad {
    void add(String str, Object obj);

    void add(String str, String str2);

    void addMap(Map<String, Object> map);

    void addMap(Map map, Boolean bool, String str, String str2);

    long getByteSize();

    Map getMap();

    String toString();
}
