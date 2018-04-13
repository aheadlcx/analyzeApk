package cn.v6.sixrooms.pay.engine;

import cn.v6.sixrooms.pay.bean.OrderBean;

public interface MakeOrderEngine$CoopRechargeCallBack {
    void error(int i);

    void handleError(String str, String str2);

    void handleResult(String str, OrderBean orderBean);
}
