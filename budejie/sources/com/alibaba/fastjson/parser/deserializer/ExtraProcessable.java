package com.alibaba.fastjson.parser.deserializer;

public interface ExtraProcessable {
    void processExtra(String str, Object obj);
}
