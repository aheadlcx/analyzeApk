package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.ArrayList;
import java.util.List;

public class UgcVideoDanmakuJson {
    @JSONField(name = "ct")
    public long createTime;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "isgod")
    public int isGod;
    @JSONField(name = "liked")
    public int liked;
    @JSONField(name = "likes")
    public int likes;
    @JSONField(name = "member")
    public MemberInfoBean member = new MemberInfoBean();
    @JSONField(name = "pid")
    public long pid;
    @JSONField(name = "rid")
    public long rid;
    @JSONField(name = "showlike")
    public boolean showLike;
    @JSONField(name = "sinfo")
    public UgcVideoDanmakuJson sinfo;
    @JSONField(name = "snaptime")
    public int snaptime;
    @JSONField(name = "subs")
    public int subCount;
    @JSONField(name = "sub_infos")
    public List<UgcVideoDanmakuJson> subInfos = new ArrayList();
    @JSONField(name = "text")
    public String text;
    @JSONField(name = "vid")
    public long vid;
}
