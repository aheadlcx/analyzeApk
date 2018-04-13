package cn.xiaochuankeji.tieba.ui.recommend.data;

import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.ui.recommend.c;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import org.json.JSONException;
import org.json.JSONObject;

public class RecPostDataBean extends PostDataBean implements c {
    public static final int POST_STATUS_EDITOR_RECOMMEND = 3;
    @JSONField(deserialize = false, serialize = false)
    public Post oldPostData = new Post();
    @JSONField(name = "position")
    private int position;

    public int getIndexInGroup() {
        return this.position;
    }

    public void setIndexInGroup(int i) {
        this.position = i;
    }

    public long getId() {
        return this.postId;
    }

    public int localPostType() {
        return 0;
    }

    public boolean equals(Object obj) {
        if ((obj instanceof RecPostDataBean) && ((RecPostDataBean) obj).postId == this.postId) {
            return true;
        }
        return false;
    }

    public static Post getOriginPostData(RecPostDataBean recPostDataBean) {
        String toJSONString = JSON.toJSONString(recPostDataBean);
        Post post = new Post();
        try {
            post.parseBaseInfo(new JSONObject(toJSONString));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return post;
    }
}
