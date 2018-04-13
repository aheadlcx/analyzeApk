package cn.v6.sixrooms.utils;

import android.app.Activity;
import android.app.Dialog;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

public class LoginUtils {
    public static boolean isLogin() {
        return GlobleValue.getUserBean() != null;
    }

    public static String getLoginUID() {
        if (isLogin()) {
            return GlobleValue.getUserBean().getId();
        }
        return "";
    }

    public static String getUidForSocket() {
        if (isLogin()) {
            return GlobleValue.getUserBean().getId();
        }
        return SaveUserInfoUtils.getVisitorId(V6Coop.getInstance().getContext());
    }

    public static String getLoginRid() {
        if (isLogin()) {
            return GlobleValue.getUserBean().getRid();
        }
        return "";
    }

    public static UserBean getLoginUserBean() {
        if (isLogin()) {
            return GlobleValue.getUserBean();
        }
        return null;
    }

    public static Dialog createLoginDialog(Activity activity, DialogListener dialogListener) {
        DialogListener tVar;
        if (dialogListener == null) {
            tVar = new t(activity);
        } else {
            tVar = dialogListener;
        }
        Dialog createConfirmDialog = new DialogUtils(activity).createConfirmDialog(1, activity.getResources().getString(R.string.InfoAbout), activity.getResources().getString(R.string.tip_this_function_need_login), activity.getResources().getString(R.string.tip_login_after), activity.getResources().getString(R.string.tip_login_now), tVar);
        createConfirmDialog.setCanceledOnTouchOutside(false);
        return createConfirmDialog;
    }
}
