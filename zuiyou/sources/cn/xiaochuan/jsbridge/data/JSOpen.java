package cn.xiaochuan.jsbridge.data;

import com.alibaba.fastjson.annotation.JSONField;

public class JSOpen {
    public static final String a = "openWindow";
    @JSONField(name = "closeCurrent")
    public boolean closeCurrent;
    @JSONField(name = "title")
    public String title;
    @JSONField(name = "url")
    public String url;
}
