package cn.xiaochuankeji.tieba.json.hollow;

import cn.xiaochuankeji.tieba.ui.hollow.data.EmotionDataBean;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class EmotionListJson {
    @JSONField(name = "list")
    public List<EmotionDataBean> emotionList;
}
