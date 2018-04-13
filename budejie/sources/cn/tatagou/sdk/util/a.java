package cn.tatagou.sdk.util;

import android.app.Activity;
import android.content.Context;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.android.TtgInterface;
import cn.tatagou.sdk.android.TtgSDK;
import cn.tatagou.sdk.view.IUpdateViewManager;
import com.ali.auth.third.core.model.Session;
import com.alibaba.baichuan.android.trade.adapter.login.AlibcLogin;
import com.alibaba.fastjson.JSON;

public class a {
    private static final String a = a.class.getSimpleName();

    public static boolean checkTaobaoLogin() {
        return !p.isEmpty(getTaoBaoUserId());
    }

    public static void checkoutTaobaoLogin() {
        String taoBaoUserInfo = getTaoBaoUserInfo();
        if (p.isEmpty(taoBaoUserInfo)) {
            IUpdateViewManager.getInstance().notifyIUpdateView(TtgInterface.TB_AUTHORIZE, null);
        } else {
            IUpdateViewManager.getInstance().notifyIUpdateView(TtgInterface.TB_AUTHORIZE, taoBaoUserInfo);
        }
    }

    public static String getTaoBaoUserId() {
        try {
            Session session = AlibcLogin.getInstance().getSession();
            if (!(session == null || p.isEmpty(session.openId))) {
                return session.openId;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static String getTaoBaoUserInfo() {
        try {
            Session session = AlibcLogin.getInstance().getSession();
            if (!(session == null || p.isEmpty(session.openId))) {
                return JSON.toJSONString(session);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void logout(Activity activity, c cVar) {
        if (!isBcSucc(activity)) {
            return;
        }
        if (p.isNetworkOpen(activity)) {
            AlibcLogin.getInstance().logout(activity, new a$1(cVar));
        } else {
            l.showToastCenter(activity, activity.getResources().getString(R.string.set_net_prompt));
        }
    }

    public static void showLogin(Activity activity, c cVar) {
        if (!isBcSucc(activity)) {
            return;
        }
        if (p.isNetworkOpen(activity)) {
            AlibcLogin.getInstance().showLogin(activity, new a$2(cVar));
        } else {
            l.showToastCenter(activity, activity.getResources().getString(R.string.set_net_prompt));
        }
    }

    public static boolean isBcSucc(Context context) {
        if (TtgSDK.getContext() == null) {
            return false;
        }
        if (TtgSDK.sBcInitFlag == 0) {
            l.showToastCenter(context, context.getResources().getString(R.string.ttg_bc_init));
            return false;
        } else if (TtgSDK.sBcInitFlag == 1) {
            return true;
        } else {
            if (TtgSDK.sBcInitFlag != -1) {
                return false;
            }
            l.showToastCenter(context, context.getResources().getString(R.string.ttg_bc_fail));
            bcInitFailNotice();
            return false;
        }
    }

    public static String getWebTitle(String str) {
        if (p.isEmpty(str)) {
            return null;
        }
        return "TAOBAO".equals(str) ? "淘宝商品" : "天猫商品";
    }

    public static void bcInitFailNotice() {
        IUpdateViewManager.getInstance().notifyIUpdateView("bcInitFail", Boolean.valueOf(true));
    }
}
