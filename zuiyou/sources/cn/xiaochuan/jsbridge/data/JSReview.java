package cn.xiaochuan.jsbridge.data;

import com.alibaba.fastjson.annotation.JSONField;

public class JSReview {
    public static final String a = "viewReview";
    @JSONField(name = "closeCurrent")
    public boolean closeCurrent;
    @JSONField(name = "pid")
    public long pid;
    @JSONField(name = "rid")
    public long rid;
}
