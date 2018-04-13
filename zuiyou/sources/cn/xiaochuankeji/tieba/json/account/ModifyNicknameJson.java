package cn.xiaochuankeji.tieba.json.account;

import cn.xiaochuankeji.tieba.json.MemberInfoBean;
import com.alibaba.fastjson.annotation.JSONField;

public class ModifyNicknameJson {
    @JSONField(name = "member_info")
    public MemberInfoBean memberInfo;
    @JSONField(name = "mid")
    public long mid;
}
