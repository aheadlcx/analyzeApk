package cn.xiaochuankeji.tieba.json;

import cn.xiaochuankeji.tieba.json.imgjson.ServerImgJson;
import cn.xiaochuankeji.tieba.json.videojson.ServerVideoJson;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UgcVideoInfoBean implements Serializable {
    @JSONField(name = "ct")
    public long createTime;
    @JSONField(name = "danmakus")
    public int danmakus;
    public boolean deleted = false;
    @JSONField(name = "hot_flag")
    public int hotFlag;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "img")
    public ServerImgJson img = new ServerImgJson();
    public boolean isCreatedByUserJustNow = false;
    @JSONField(name = "isgod")
    public int isGod;
    @JSONField(name = "likes")
    public int likeCount;
    @JSONField(name = "like")
    public int liked;
    @JSONField(name = "post")
    public UgcVideoInfoBean mainPost;
    @JSONField(name = "member")
    public MemberInfoBean member = new MemberInfoBean();
    @JSONField(name = "pid")
    public long pid;
    @JSONField(name = "plays")
    public int plays;
    @JSONField(name = "reviews")
    public int reviews;
    @JSONField(name = "status")
    public int status;
    @JSONField(name = "sub_imgs")
    public List<ServerImgJson> subImgs = new ArrayList();
    @JSONField(name = "video")
    public ServerVideoJson videoInfo;
}
