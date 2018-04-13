package cn.v6.sixrooms.login.interfaces;

import java.util.Map;

public interface GetVerifyCodeCallback extends CommonCallback {
    void getVerifyCodeSuccess(String str);

    void getVerifyCodeSuccess(Map<String, String> map);
}
