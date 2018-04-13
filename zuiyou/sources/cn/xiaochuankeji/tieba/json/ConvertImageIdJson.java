package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.HashMap;

public class ConvertImageIdJson {
    @JSONField(name = "resinfo")
    public HashMap<String, ConvertMediaInfo> convertMediaMap;
}
