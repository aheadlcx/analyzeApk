package cn.xiaochuankeji.tieba.api.account;

import android.text.TextUtils;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.json.account.AccountCheckJson;
import cn.xiaochuankeji.tieba.json.account.ModifyNicknameJson;
import cn.xiaochuankeji.tieba.json.account.VerifyJson;
import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSONObject;
import rx.b.g;
import rx.d;

public class a {
    private AccountService a = ((AccountService) c.a().a(AccountService.class));

    public d<AccountCheckJson> a() {
        return this.a.checkNicknameModifyEnable();
    }

    public d<JSONObject> b() {
        final long c = cn.xiaochuankeji.tieba.background.a.g().c();
        return a(c).c(new g<JSONObject, d<JSONObject>>(this) {
            final /* synthetic */ a b;

            public /* synthetic */ Object call(Object obj) {
                return a((JSONObject) obj);
            }

            public d<JSONObject> a(JSONObject jSONObject) {
                String string = jSONObject.getString("nonce");
                return this.b.b(cn.htjyb.c.d.c(cn.xiaochuankeji.tieba.background.a.g().b() + string + c), string);
            }
        }).b(rx.f.a.c()).a(rx.f.a.c());
    }

    private d<JSONObject> a(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("mid", Long.valueOf(j));
        return this.a.nonce(jSONObject);
    }

    private d<JSONObject> b(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("key", str);
        jSONObject.put("nonce", str2);
        if (!AppController.instance().deviceIDUpdated()) {
            jSONObject.put("uuid", cn.xiaochuankeji.tieba.common.e.c.a(BaseApplication.getAppContext()));
        }
        return this.a.auth(jSONObject);
    }

    public d<ModifyNicknameJson> a(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", str);
        return this.a.updateNickname(jSONObject);
    }

    public d<JSONObject> b(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("old_pw", cn.xiaochuankeji.tieba.background.a.g().b());
        jSONObject.put("pw", cn.htjyb.c.d.e(str));
        return this.a.modifyPassword(jSONObject);
    }

    public d<JSONObject> a(int i, String str, long j) {
        JSONObject jSONObject = new JSONObject();
        if (i > 0) {
            jSONObject.put("gender", Integer.valueOf(i));
        }
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("sign", str);
        }
        if (j != 0) {
            jSONObject.put("birth", Long.valueOf(j));
        }
        return this.a.modifyGenderAndSign(jSONObject);
    }

    public d<VerifyJson> a(String str, int i, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("phone", str);
        jSONObject.put("region_code", Integer.valueOf(i));
        if ("reg".equalsIgnoreCase(str2)) {
            return this.a.getRegisterVerifyCode(jSONObject);
        }
        if ("pwd".equalsIgnoreCase(str2)) {
            return this.a.getPasswordVerifyCode(jSONObject);
        }
        if ("bind".equalsIgnoreCase(str2)) {
            return this.a.getModifyVerifyCode(jSONObject);
        }
        if (cn.xiaochuan.jsbridge.data.d.a.equalsIgnoreCase(str2)) {
            return this.a.getLoginVerifyCode(jSONObject);
        }
        if ("certify".equalsIgnoreCase(str2)) {
            return this.a.getModifyVerifyCode(jSONObject);
        }
        if ("rebind".equalsIgnoreCase(str2)) {
            return this.a.getRebindVerifyCode(jSONObject);
        }
        jSONObject.put("type", str2);
        return this.a.getVerifyCode(jSONObject);
    }

    public d<JSONObject> a(String str, String str2, int i) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("phone", str);
        jSONObject.put("code", str2);
        jSONObject.put("region_code", Integer.valueOf(i));
        return this.a.bindPhone(jSONObject);
    }

    public d<JSONObject> a(String str, String str2, int i, String str3, String str4, int i2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("old_phone", str);
        jSONObject.put("old_pw", cn.htjyb.c.d.e(str2));
        jSONObject.put("old_region_code", Integer.valueOf(i));
        jSONObject.put("phone", str3);
        jSONObject.put("code", str4);
        jSONObject.put("region_code", Integer.valueOf(i2));
        jSONObject.put("force", Integer.valueOf(0));
        return this.a.rebindPhone(jSONObject);
    }

    public d<JSONObject> b(String str, String str2, int i, String str3, String str4, int i2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("old_phone", str);
        jSONObject.put("old_pw", cn.htjyb.c.d.e(str2));
        jSONObject.put("old_region_code", Integer.valueOf(i));
        jSONObject.put("phone", str3);
        jSONObject.put("code", str4);
        jSONObject.put("region_code", Integer.valueOf(i2));
        jSONObject.put("force", Integer.valueOf(1));
        return this.a.rebindPhone(jSONObject);
    }

    public d<JSONObject> b(String str, String str2, int i) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("phone", str);
        jSONObject.put("code", str2);
        jSONObject.put("region_code", Integer.valueOf(i));
        return this.a.verifyCodeLogin(jSONObject);
    }

    public d<JSONObject> a(JSONObject jSONObject) {
        return this.a.verifyCodeLogin(jSONObject);
    }

    public d<JSONObject> a(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("phone", str);
        jSONObject.put("code", str2);
        jSONObject.put("force", Integer.valueOf(1));
        return this.a.bindPhone(jSONObject);
    }

    public d<JSONObject> c(String str, String str2, int i) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("phone", str);
        jSONObject.put("pw", cn.htjyb.c.d.e(str2));
        jSONObject.put("region_code", Integer.valueOf(i));
        return this.a.login(jSONObject);
    }

    public d<JSONObject> c() {
        return this.a.profile(new JSONObject());
    }

    public static int a(CharSequence charSequence) {
        int i = -1;
        if (!TextUtils.isEmpty(charSequence)) {
            try {
                i = Integer.valueOf(charSequence.toString().trim().replaceAll("\\+", "")).intValue();
            } catch (Exception e) {
            }
        }
        return i;
    }
}
