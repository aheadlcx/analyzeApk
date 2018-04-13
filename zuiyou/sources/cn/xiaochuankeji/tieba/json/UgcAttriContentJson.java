package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.ArrayList;
import java.util.List;

public class UgcAttriContentJson {
    @JSONField(name = "clearcache")
    public boolean clearCache;
    @JSONField(name = "downoffset")
    public String downOffset;
    @JSONField(name = "list")
    public List<UgcVideoInfoBean> list = new ArrayList();
    @JSONField(name = "more")
    public int more;
    @JSONField(name = "upoffset")
    public String upOffset;
}
