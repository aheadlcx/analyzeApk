package cn.v6.sixrooms.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.base.VLScheduler;
import cn.v6.sixrooms.constants.CommonInts;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.engine.AppUpdateEngine;

public class HandleErrorUtils {
    private HandleErrorUtils() {
    }

    public static void handleErrorResult(String str, String str2, Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            if (CommonStrs.FLAG_TYPE_NEED_LOGIN.equals(str)) {
                if (GlobleValue.getUserBean() != null || !TextUtils.isEmpty(SaveUserInfoUtils.getEncpass(activity))) {
                    logout(activity);
                    if (!activity.isFinishing()) {
                        showLogoutDialog(activity);
                    }
                }
            } else if ("105".equals(str)) {
                show6CoinNotEnoughDialog(str2, activity);
            } else if ("406".equals(str)) {
                showNotBoundMobileDialog(str2, activity);
            } else if (TextUtils.isEmpty(str2)) {
                ToastUtils.showToast(V6Coop.getInstance().getContext().getResources().getString(R.string.tip_network_error_title));
            } else {
                new DialogUtils(activity).createDiaglog(str2).show();
            }
        }
    }

    public static void logout(Activity activity) {
        new AppUpdateEngine(new j()).update("logout", "3");
        SaveUserInfoUtils.clearEncpass(activity);
        SendBroadcastUtils.sendUserLogout(activity);
        GlobleValue.clearUserBean(activity);
        SPUtils.clear(activity);
    }

    public static void showLogoutDialog(Context context) {
        if (context instanceof Activity) {
            Dialog createConfirmDialogs = new DialogUtils(context).createConfirmDialogs(CommonInts.USER_MANAGER_REQUEST_CODE, context.getResources().getString(R.string.InfoAbout), context.getResources().getString(R.string.tip_shot_off_errro_str), context.getResources().getString(R.string.confirm), new k(context));
            createConfirmDialogs.setOnDismissListener(new l(context));
            createConfirmDialogs.show();
        }
    }

    public static void showNotBoundMobileDialog(String str, Activity activity) {
        Dialog createBundleDialog = DialogUtils.createBundleDialog(activity, 1000, "提示", str, activity.getString(17039360), activity.getString(R.string.immediate_binding), new m(activity));
        createBundleDialog.setCancelable(false);
        createBundleDialog.show();
    }

    public static void show6CoinNotEnoughDialog(String str, Activity activity) {
        new DialogUtils(activity).createConfirmDialog(105, activity.getString(R.string.tip_show_tip_title), str, activity.getString(R.string.tip_not_save), activity.getString(R.string.tip_to_save), new o(activity)).show();
    }

    public static void showErrorToast(int i) {
        ToastUtils.showToast(getError(i));
    }

    public static String getError(int i) {
        if (i == 1007 || i == CommonInts.NUMBER_FORMAT_EXCEPTION || i == CommonInts.STRING_OUTOFBOUNDS_EXCEPTION) {
            return V6Coop.getInstance().getContext().getResources().getString(R.string.tip_json_parse_error_title);
        }
        return V6Coop.getInstance().getContext().getResources().getString(R.string.tip_network_error_title);
    }

    public static void handleIMSocketErrorResult(int i, String str, String str2, String str3, Activity activity) {
        CharSequence topActivity = ActivityManagerUtils.getTopActivity(activity);
        if (TextUtils.isEmpty(topActivity)) {
            ToastUtils.showToast(activity.getResources().getString(R.string.tip_network_error_title));
        } else if (!activity.toString().contains(topActivity)) {
        } else {
            if (TextUtils.isEmpty(str3)) {
                ToastUtils.showToast(activity.getResources().getString(R.string.tip_network_error_title));
            } else {
                ToastUtils.showToast(str3);
            }
        }
    }

    public static void showLoginDialog(Fragment fragment) {
        Activity activity = fragment.getActivity();
        LoginUtils.createLoginDialog(activity, new p(activity)).show();
    }

    public static void showLoginDialog(Activity activity) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            LogUtils.e("HandleErrorUtils", "主线程");
            LoginUtils.createLoginDialog(activity, null).show();
            return;
        }
        LogUtils.e("HandleErrorUtils", "非主线程");
        VLScheduler.instance.schedule(0, 0, new r(activity));
    }
}
