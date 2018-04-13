package cn.xiaochuankeji.tieba.json.recommend;

import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.CommentSound;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class CommentBean implements Serializable {
    @JSONField(name = "audio")
    public CommentAudio audio;
    @JSONField(name = "avatar")
    public long avatarId;
    @JSONField(name = "id")
    public long commentId;
    @JSONField(name = "videos")
    public Map<String, ServerVideoBean> commentVideos;
    @JSONField(name = "ct")
    public long createTime;
    @JSONField(name = "disable_reply")
    public int disableReply;
    @JSONField(name = "gender")
    public int gender;
    @JSONField(name = "isgod")
    public int isGod;
    @JSONField(name = "hide")
    public int isHide;
    @JSONField(name = "likes")
    public int likeCount;
    @JSONField(name = "liked")
    public int liked;
    @JSONField(name = "liken")
    public int liken;
    @JSONField(name = "mid")
    public long mid;
    @JSONField(name = "mname")
    public String nickName;
    @JSONField(name = "prid")
    public long parentCommentId;
    @JSONField(name = "pid")
    public long postId;
    @JSONField(name = "review")
    public String reviewContent;
    @JSONField(name = "imgs")
    public List<ServerImageBean> serverImages;
    @JSONField(name = "saudio")
    public CommentAudio sourceAudio;
    @JSONField(name = "svideos")
    public Map<String, ServerVideoBean> sourceCommentVideos;
    @JSONField(name = "sdel_flag")
    public int sourceDeleted;
    @JSONField(name = "sid")
    public long sourceId;
    @JSONField(name = "simgs")
    public List<ServerImageBean> sourceImages;
    @JSONField(name = "sname")
    public String sourceName;
    @JSONField(name = "sreview")
    public String sourceReview;
    @JSONField(name = "subreviewcnt")
    public int subReviewCount;
    @JSONField(name = "subreview")
    public List<SubReview> subReviews;

    public static class CommentAudio implements Serializable {
        @JSONField(name = "dur")
        public int audioDur;
        @JSONField(name = "text")
        public String audioText;
        @JSONField(name = "url")
        public String audioUrl;

        public static CommentSound getCommentSoundFromCommentAudio(CommentAudio commentAudio) {
            try {
                return new CommentSound(new JSONObject(JSON.toJSONString(commentAudio)));
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static class SubReview implements Serializable {
        @JSONField(name = "mname")
        public String memberName;
        @JSONField(name = "mid")
        public long mid;
        @JSONField(name = "review")
        public String reviewContent;
        @JSONField(name = "rid")
        public long reviewId;
        @JSONField(name = "smid")
        public long sid;
        @JSONField(name = "sname")
        public String sname;
    }

    public static Comment getCommentFromBean(CommentBean commentBean) {
        try {
            return new Comment(new JSONObject(JSON.toJSONString(commentBean)));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
