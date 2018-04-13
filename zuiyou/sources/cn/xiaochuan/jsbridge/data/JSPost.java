package cn.xiaochuan.jsbridge.data;

import com.alibaba.fastjson.annotation.JSONField;

public class JSPost {
    public static final String a = "viewPost";
    @JSONField(name = "closeCurrent")
    public boolean closeCurrent;
    @JSONField(name = "isScrollToReview")
    public boolean isScrollToReview;
    @JSONField(name = "pid")
    public long pid;
    @JSONField(name = "rid")
    public long rid;
}
