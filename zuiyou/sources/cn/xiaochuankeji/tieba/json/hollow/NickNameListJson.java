package cn.xiaochuankeji.tieba.json.hollow;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class NickNameListJson {
    @JSONField(name = "list")
    public List<NickName> nickNameList;

    public static class NickName {
        @JSONField(name = "name")
        public String name;
        @JSONField(name = "id")
        public long nameId;
    }
}
