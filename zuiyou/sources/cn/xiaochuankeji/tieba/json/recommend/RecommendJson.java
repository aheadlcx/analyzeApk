package cn.xiaochuankeji.tieba.json.recommend;

import cn.xiaochuankeji.tieba.ui.recommend.data.RecPostDataBean;
import cn.xiaochuankeji.tieba.ui.recommend.data.RecUgcDataBean;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class RecommendJson {
    @JSONField(name = "list")
    public List<RecPostDataBean> postList;
    @JSONField(name = "ugcvideo_items")
    public List<UgcDataWrapper> ugcList;

    public static class UgcDataWrapper {
        @JSONField(name = "pos")
        public int position;
        @JSONField(name = "ugcvideo")
        public RecUgcDataBean ugcDataBean;
    }
}
