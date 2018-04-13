package cn.xiaochuan.jsbridge.data;

import com.alibaba.fastjson.annotation.JSONField;

public class JSProfile {
    public static final String a = "viewProfile";
    @JSONField(name = "closeCurrent")
    public boolean closeCurrent;
    @JSONField(name = "mid")
    public long mid;
}
