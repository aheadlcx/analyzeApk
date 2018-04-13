package cn.xiaochuankeji.tieba.json.feed;

import cn.xiaochuankeji.tieba.ui.recommend.data.RecPostDataBean;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class FeedListJson {
    @JSONField(name = "down_offset")
    public long downOffset = -1;
    @JSONField(name = "go_suggest_page")
    public int goSuggestPage;
    @JSONField(name = "list")
    public List<RecPostDataBean> list;
    @JSONField(name = "more")
    public int more;
    @JSONField(name = "tips")
    public String tips;
    @JSONField(name = "up_offset")
    public long upOffset = -1;

    public String toString() {
        return "FeedListJson{downOffset=" + this.downOffset + ", goSuggestPage=" + this.goSuggestPage + ", more=" + this.more + ", tips='" + this.tips + '\'' + ", upOffset=" + this.upOffset + ", list=" + this.list + '}';
    }
}
