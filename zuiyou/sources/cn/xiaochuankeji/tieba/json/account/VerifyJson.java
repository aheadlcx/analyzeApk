package cn.xiaochuankeji.tieba.json.account;

import com.alibaba.fastjson.annotation.JSONField;

public class VerifyJson {
    @JSONField(name = "hash_code")
    public String hash_code;
    @JSONField(name = "is_phone_reg")
    public int is_phone_reg;
}
