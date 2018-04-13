package cn.xiaochuankeji.tieba.json.feed;

import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import cn.xiaochuankeji.tieba.ui.topic.data.UgcDataBean;
import cn.xiaochuankeji.tieba.ui.topic.data.c;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.LinkedList;
import java.util.List;

public class FeedPostListJson {
    @JSONField(name = "down_offset")
    public long downOffset = -1;
    @JSONField(name = "go_suggest_page")
    public int goSuggestPage;
    @JSONField(name = "list")
    public JSONArray jsonArray;
    @JSONField(name = "more")
    public int more;
    @JSONField(name = "tips")
    public String tips;
    @JSONField(name = "up_offset")
    public long upOffset = -1;

    public List<c> postVisitableList() {
        List<c> linkedList = new LinkedList();
        for (int i = 0; i < this.jsonArray.size(); i++) {
            JSONObject jSONObject = this.jsonArray.getJSONObject(i);
            switch (jSONObject.getInteger("c_type").intValue()) {
                case 3:
                    linkedList.add((UgcDataBean) JSON.parseObject(JSON.toJSONString(jSONObject), UgcDataBean.class));
                    break;
                default:
                    linkedList.add(PostDataBean.getPostDataBeanFromJson(this.jsonArray.getJSONObject(i)));
                    break;
            }
        }
        return linkedList;
    }

    public String toString() {
        return "FeedListJson{downOffset=" + this.downOffset + ", goSuggestPage=" + this.goSuggestPage + ", more=" + this.more + ", tips='" + this.tips + '\'' + ", upOffset=" + this.upOffset + ", list=" + this.jsonArray + '}';
    }
}
