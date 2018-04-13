package cn.xiaochuankeji.tieba.api.account;

import cn.xiaochuankeji.tieba.json.account.AccountCheckJson;
import cn.xiaochuankeji.tieba.json.account.ModifyNicknameJson;
import cn.xiaochuankeji.tieba.json.account.VerifyJson;
import com.alibaba.fastjson.JSONObject;
import retrofit2.a.a;
import retrofit2.a.o;
import rx.d;

public interface AccountService {
    @o(a = "/account/auth")
    d<JSONObject> auth(@a JSONObject jSONObject);

    @o(a = "/account/update_phone")
    d<JSONObject> bindPhone(@a JSONObject jSONObject);

    @o(a = "/account/enable_editname")
    d<AccountCheckJson> checkNicknameModifyEnable();

    @o(a = "/verifycode/login")
    d<VerifyJson> getLoginVerifyCode(@a JSONObject jSONObject);

    @o(a = "/verifycode/update_phone")
    d<VerifyJson> getModifyVerifyCode(@a JSONObject jSONObject);

    @o(a = "/verifycode/password")
    d<VerifyJson> getPasswordVerifyCode(@a JSONObject jSONObject);

    @o(a = "/verifycode/rebind")
    d<VerifyJson> getRebindVerifyCode(@a JSONObject jSONObject);

    @o(a = "/verifycode/register")
    d<VerifyJson> getRegisterVerifyCode(@a JSONObject jSONObject);

    @o(a = "/verifycode/get")
    d<VerifyJson> getVerifyCode(@a JSONObject jSONObject);

    @o(a = "/account/login")
    d<JSONObject> login(@a JSONObject jSONObject);

    @o(a = "/account/update")
    d<JSONObject> modifyGenderAndSign(@a JSONObject jSONObject);

    @o(a = "/account/update_password")
    d<JSONObject> modifyPassword(@a JSONObject jSONObject);

    @o(a = "/account/nonce")
    d<JSONObject> nonce(@a JSONObject jSONObject);

    @o(a = "/my/profile")
    d<JSONObject> profile(@a JSONObject jSONObject);

    @o(a = "/account/rebind_phone")
    d<JSONObject> rebindPhone(@a JSONObject jSONObject);

    @o(a = "/account/update_name")
    d<ModifyNicknameJson> updateNickname(@a JSONObject jSONObject);

    @o(a = "/account/verifycode_login")
    d<JSONObject> verifyCodeLogin(@a JSONObject jSONObject);
}
