package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;

public class ModifyMemberCoverJson {
    @JSONField(name = "member_info")
    public MemberInfoBean memberInfo;
    @JSONField(name = "mid")
    public long mid;
}
