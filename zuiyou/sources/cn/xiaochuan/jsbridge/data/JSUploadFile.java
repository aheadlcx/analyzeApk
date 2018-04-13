package cn.xiaochuan.jsbridge.data;

import com.alibaba.fastjson.annotation.JSONField;

public class JSUploadFile {
    public static final String a = "uploadFile";
    @JSONField(name = "count")
    public int count;
    @JSONField(name = "file_type")
    public String file_type;
    @JSONField(name = "multiple")
    public boolean multiple;
}
