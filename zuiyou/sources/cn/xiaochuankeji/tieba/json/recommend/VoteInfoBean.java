package cn.xiaochuankeji.tieba.json.recommend;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.util.List;
import org.json.JSONObject;

public class VoteInfoBean implements Serializable {
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "voted_item")
    public String voteItem;
    @JSONField(name = "opt")
    public List<VoteItem> voteItems;

    public static class VoteItem implements Serializable {
        @JSONField(name = "pollcn")
        public int voteCount;
        @JSONField(name = "id")
        public String voteId;
        @JSONField(name = "name")
        public String voteName;
    }

    public static VoteInfoBean parse(JSONObject jSONObject) {
        return (VoteInfoBean) JSON.parseObject(jSONObject.toString(), VoteInfoBean.class);
    }
}
