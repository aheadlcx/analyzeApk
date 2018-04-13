package cn.xiaochuankeji.tieba.background.member;

import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import java.io.Serializable;
import org.json.JSONObject;

public class MemberCommentInfo implements Serializable {
    public Comment comment;
    public boolean isSelected;
    public Comment parentComment;
    public Post relativePost;

    public MemberCommentInfo(JSONObject jSONObject) {
        this.comment = new Comment(jSONObject.optJSONObject("review"));
        JSONObject optJSONObject = jSONObject.optJSONObject("post");
        if (optJSONObject != null) {
            this.relativePost = new Post(optJSONObject);
        }
        optJSONObject = jSONObject.optJSONObject("preview");
        if (optJSONObject != null) {
            this.parentComment = new Comment(optJSONObject);
        }
    }
}
