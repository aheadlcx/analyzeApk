package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;

public class UgcVideoShareJson {
    @JSONField(name = "desc")
    public UgcVideoShareContentJson shareTxt;

    public class UgcVideoShareContentJson {
        @JSONField(name = "sub_title")
        public String desp;
        @JSONField(name = "title")
        public String title;
    }
}
