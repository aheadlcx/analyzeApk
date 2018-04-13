package cn.xiaochuan.jsbridge.data;

import com.alibaba.fastjson.annotation.JSONField;

public class JSToast {
    public static final String a = "toast";
    @JSONField(name = "text")
    public String text;
}
