package cn.xiaochuan.jsbridge.data;

import com.alibaba.fastjson.annotation.JSONField;

public class JSTopic {
    public static final String a = "viewTopic";
    @JSONField(name = "closeCurrent")
    public boolean closeCurrent;
    @JSONField(name = "tid")
    public long tid;
}
