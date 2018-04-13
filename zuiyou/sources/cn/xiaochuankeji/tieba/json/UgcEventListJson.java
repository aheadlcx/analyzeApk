package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.ArrayList;
import java.util.List;

public class UgcEventListJson {
    @JSONField(name = "activities")
    public List<UgcEventJson> eventList = new ArrayList();
}
