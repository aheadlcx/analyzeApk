package cn.xiaochuan.jsbridge.data;

import com.alibaba.fastjson.annotation.JSONField;

public class JSOpenUgcVideo {
    public static final String a = "viewUGC";
    @JSONField(name = "closeCurrent")
    public boolean closeCurrent;
    @JSONField(name = "pid")
    public long pid;
    @JSONField(name = "rid")
    public long rid;
}
