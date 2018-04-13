package cn.xiaochuan.jsbridge.data;

import com.alibaba.fastjson.annotation.JSONField;

public class JSMenuConfig {
    public static final String a = "initMenuConfig";
    public static final String b = "share";
    public Item[] c;

    public static class Item {
        @JSONField(name = "callback")
        public String callback;
        @JSONField(name = "id")
        public String id;
        @JSONField(name = "title")
        public String title;
    }
}
