package cn.xiaochuan.jsbridge.data;

import com.alibaba.fastjson.annotation.JSONField;

public class JSMarket {
    public static final String a = "openMarket";
    @JSONField(name = "closeCurrent")
    public boolean closeCurrent;
    @JSONField(name = "packageName")
    public String packageName;
}
