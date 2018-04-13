package cn.v6.sdk.sixrooms.coop;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import cn.v6.sixrooms.Manage;
import cn.v6.sixrooms.RoomManage;
import cn.v6.sixrooms.base.SixRoomsUtils;
import cn.v6.sixrooms.base.VLDebug;
import cn.v6.sixrooms.base.VLDebug.VLLogLevel;
import cn.v6.sixrooms.base.VLScheduler;
import cn.v6.sixrooms.constants.CustomBroadcast;
import cn.v6.sixrooms.engine.CoopEncyptEngine;
import cn.v6.sixrooms.engine.GetCoopTypeEngine;
import cn.v6.sixrooms.engine.PartnerLoginEngine;
import cn.v6.sixrooms.engine.UserInfoEngine;
import cn.v6.sixrooms.event.EventManager;
import cn.v6.sixrooms.event.LoginEvent;
import cn.v6.sixrooms.hall.HallActivity;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.room.fragment.RoomBaseFragment;
import cn.v6.sixrooms.surfaceanim.util.FrescoUtil;
import cn.v6.sixrooms.ui.phone.UserManagerActivity;
import cn.v6.sixrooms.utils.AppInfoUtils;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.MD5Utils;
import cn.v6.sixrooms.utils.SPUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import cn.v6.sixrooms.utils.SendBroadcastUtils;
import com.ishumei.smantifraud.SmAntiFraud;
import com.ishumei.smantifraud.SmAntiFraud.SmOption;
import com.umeng.analytics.a;

public class V6Coop {
    public static final String CALLBACK_LOGINTYPE = "callBackLoginType";
    public static String COOP_LOGIN_TYPE = "1";
    public static String COOP_PAY_TYPE = "0";
    public static final String ERROR = "未知异常";
    public static final String ILLAGE_COOP = "合作截止";
    public static final String MISSPARAMS = "缺少参数";
    public static final String NET_ERROR = "网络连接异常";
    public static final String PARSE_JSON_ERROR = "json 解析错误";
    public static final String TAG = V6Coop.class.getSimpleName();
    private static boolean debug = true;
    public static boolean firstGetGift = true;
    public static boolean flag = false;
    private static V6Coop instance = null;
    public static boolean isGetOperatorFlow = false;
    public static String mCoop = null;
    public static String mCoopUid = null;
    public static String mLoginKey = null;
    public static String mPackageName = null;
    public static String mReleaseNum = null;
    public static String mStatisticsName = null;
    public static int repeated = 0;
    private static final String shumeiOrganization = "TKWQ4vmgC3PJLGDTMIoJ";
    private static boolean useCoopPaySDK = false;
    private boolean coopEncypt = false;
    private CoopEncyptEngine coopEncyptEngine;
    private GetCoopTypeEngine getCoopTypeEngine;
    private UserInfoEngine getUserInfoEngine;
    private boolean isLoading = false;
    private boolean isShowMiniGame = true;
    private Dialog loadingDialog;
    private Context mContext;
    private Handler mHandler = new Handler();
    private RechargeCallBack mRechargeCallBack;
    private LocalBroadcastManager manager;
    private NotifyAppLoginCallBack notifyAppLoginCallBack;
    private NotifyAppLogoutCallBack notifyAppLogoutCallBack;
    private NotifyAppRechargeable notifyAppRechargeable;
    private NotifyV6LoginCallBack notifyV6LoginCallBack;
    private PartnerLoginEngine partnerLoginEngine;
    private boolean showBack = true;
    private SyncLoginCallBack syncLoginCallBack;
    private SyncLogoutCallBack syncLogoutCallBack;

    public Context getContext() {
        return this.mContext;
    }

    public static boolean getUserCoopPaySDK() {
        return useCoopPaySDK;
    }

    public NotifyV6LoginCallBack getNotifyV6LoginCallBack() {
        return this.notifyV6LoginCallBack;
    }

    public NotifyAppLoginCallBack getNotifyAppLoginCallBack() {
        return this.notifyAppLoginCallBack;
    }

    private V6Coop() {
    }

    public V6Coop init(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
            throw new IllegalArgumentException("v6coop初始化参数异常！");
        }
        this.manager = LocalBroadcastManager.getInstance(context);
        this.mContext = context.getApplicationContext();
        mPackageName = context.getPackageName();
        mCoop = str;
        mReleaseNum = str2;
        mStatisticsName = str3;
        mLoginKey = str4;
        FrescoUtil.initFresco(context);
        VLDebug.configDebug(this.mContext, SixRoomsUtils.appIsDebug() ? VLLogLevel.Debug : VLLogLevel.Error, a.i, 259200000);
        initShumei();
        v6LoginCoopEncypt();
        return this;
    }

    public void openSdkCrashHandler(boolean z) {
        if (z) {
            CrashHandler.getInstance().init();
        }
    }

    private void initShumei() {
        SmOption smOption = new SmOption();
        smOption.setOrganization(shumeiOrganization);
        SmAntiFraud.create(this.mContext, smOption);
    }

    public static V6Coop getInstance() {
        if (instance == null) {
            synchronized (V6Coop.class) {
                if (instance == null) {
                    instance = new V6Coop();
                }
            }
        }
        return instance;
    }

    @Deprecated
    public V6Coop useCoopPaySDK() {
        useCoopPaySDK = true;
        return this;
    }

    public V6Coop setRechargeCallBack(RechargeCallBack rechargeCallBack) {
        this.mRechargeCallBack = rechargeCallBack;
        return this;
    }

    private void closeLoadingDialog() {
        this.mHandler.post(new V6Coop$1(this));
    }

    private void showLoadingDialog(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            if (this.loadingDialog == null) {
                this.loadingDialog = new DialogUtils(activity).createLoadingDialog();
            }
            if (!this.loadingDialog.isShowing()) {
                this.loadingDialog.show();
            }
        }
    }

    public void rechargeResult(int i, String str, String str2) {
        if (this.mRechargeCallBack != null) {
            if (debug) {
                Log.d(TAG, "type = " + str2 + " 充值：" + i + "元成功");
            }
            this.mRechargeCallBack.result(i, str, str2, mCoopUid);
        }
    }

    public void clearLoginData(Context context) {
        SaveUserInfoUtils.clearEncpass(context);
        GlobleValue.clearUserBean(context);
        pushLogoutMsg();
        SendBroadcastUtils.sendUserLogout(context);
        SPUtils.clear(context);
    }

    public static String getVersion() {
        return AppInfoUtils.getAppVersFion();
    }

    public static void closeAll() {
        Manage.getInstance().closeAll();
    }

    public void syncLoginStatus(@NonNull Activity activity, @NonNull CoopBean coopBean, boolean z) {
        if (TextUtils.isEmpty(mCoop) || TextUtils.isEmpty(mReleaseNum) || TextUtils.isEmpty(mLoginKey)) {
            throw new IllegalArgumentException("v6coop初始化参数异常！");
        } else if (TextUtils.isEmpty(coopBean.getCoopUid()) || TextUtils.isEmpty(coopBean.getCoopNick()) || TextUtils.isEmpty(coopBean.getFlag()) || TextUtils.isEmpty(coopBean.getToken())) {
            if (debug) {
                Log.e(TAG, "同步登录状态,cooPUid coopNick coopflag  token有为null的参数");
            }
            if (this.syncLoginCallBack != null) {
                this.syncLoginCallBack.syncLoginFailed("61055", MISSPARAMS);
            }
        } else {
            if (TextUtils.isEmpty(coopBean.getCoopUid()) || !SaveUserInfoUtils.getCoopUid(this.mContext).equals(coopBean.getCoopUid())) {
                SaveUserInfoUtils.saveCoopUid(this.mContext, coopBean.getCoopUid());
                clearLoginData(this.mContext);
            }
            if (!TextUtils.isEmpty(coopBean.getCoopUid()) && !TextUtils.isEmpty(coopBean.getCoopNick()) && TextUtils.isEmpty(SaveUserInfoUtils.getEncpass(this.mContext))) {
                if (z) {
                    showLoadingActivity(activity);
                }
                coopLoginCoopEncypt(coopBean);
            } else if (!TextUtils.isEmpty(SaveUserInfoUtils.getEncpass(this.mContext))) {
                getUserInfo(null, this.syncLoginCallBack);
            }
        }
    }

    public void syncLoginStatus(@NonNull Activity activity, @NonNull CoopBean coopBean) {
        syncLoginStatus(activity, coopBean, true);
    }

    private void coopLoginCoopEncypt(CoopBean coopBean) {
        this.coopEncyptEngine = new CoopEncyptEngine(new V6Coop$2(this, coopBean));
        this.coopEncyptEngine.getEncyptKey(mPackageName, mCoop, MD5Utils.getMD5Str(mPackageName + mLoginKey));
    }

    public void syncLogoutStatus() {
        clearLoginData(this.mContext);
        pushLogoutMsg();
        if (this.syncLogoutCallBack != null) {
            this.syncLogoutCallBack.syncLogoutSuccess();
        }
    }

    public void goToRoom(Activity activity, String str, String str2) {
        if (checkSetNotifyCallBack()) {
            RoomManage.getInstance().exit();
            Intent intent = new Intent(activity, RoomActivity.class);
            intent.putExtra("rid", str);
            intent.putExtra(RoomBaseFragment.RUID_KEY, str2);
            activity.startActivity(intent);
            this.mHandler.postDelayed(new V6Coop$3(this, activity), 300);
            return;
        }
        throw new IllegalArgumentException("must set notifyAppLoginCallBack or notifyAppLogoutCallBack");
    }

    public void gotoHall(Activity activity) {
        this.showBack = true;
        if (checkSetNotifyCallBack()) {
            HallActivity.startSelf(activity);
            this.mHandler.postDelayed(new V6Coop$4(this, activity), 300);
            return;
        }
        throw new IllegalArgumentException("must set notifyAppLoginCallBack or notifyAppLogoutCallBack");
    }

    public void gotoHall(Activity activity, boolean z) {
        this.showBack = z;
        if (checkSetNotifyCallBack()) {
            HallActivity.startSelf(activity);
            this.mHandler.postDelayed(new V6Coop$5(this, activity), 300);
            return;
        }
        throw new IllegalArgumentException("must set notifyAppLoginCallBack or notifyAppLogoutCallBack");
    }

    public V6Coop setNotifyAppLogoutCallBack(NotifyAppLogoutCallBack notifyAppLogoutCallBack) {
        this.notifyAppLogoutCallBack = notifyAppLogoutCallBack;
        return this;
    }

    public V6Coop setNotifyAppLoginCallBack(NotifyAppLoginCallBack notifyAppLoginCallBack) {
        this.notifyAppLoginCallBack = notifyAppLoginCallBack;
        return this;
    }

    public boolean checkSetNotifyCallBack() {
        return (this.notifyAppLoginCallBack == null && this.notifyAppLogoutCallBack == null) ? false : true;
    }

    private void doCooperateLogin(String str, CoopBean coopBean, SyncLoginCallBack syncLoginCallBack) {
        if (this.partnerLoginEngine == null) {
            this.partnerLoginEngine = new PartnerLoginEngine(new V6Coop$6(this, syncLoginCallBack));
        }
        this.partnerLoginEngine.login3(str, coopBean);
    }

    private void getUserInfo(String str, SyncLoginCallBack syncLoginCallBack) {
        if (this.getUserInfoEngine == null) {
            this.getUserInfoEngine = new UserInfoEngine(new V6Coop$7(this, syncLoginCallBack, str));
        }
        this.getUserInfoEngine.getUserInfo(SaveUserInfoUtils.getEncpass(this.mContext), "");
    }

    public void showLoadingActivity(Activity activity) {
        Log.d(TAG, "展示loadingActivity");
        this.isLoading = true;
        activity.startActivity(new Intent(activity, CoopLoadingActivity.class));
        activity.overridePendingTransition(0, 0);
    }

    public void dismissLoadingActivity() {
        Log.d(TAG, "发送loading 消失广播");
        this.isLoading = false;
        Intent intent = new Intent();
        intent.setAction(CustomBroadcast.COOPLOGIN_LOADING);
        this.manager.sendBroadcast(intent);
    }

    public void goToV6Login(Activity activity) {
        if (this.coopEncypt) {
            activity.startActivity(new Intent(activity, UserManagerActivity.class));
            return;
        }
        showLoadingDialog(activity);
        v6LoginCoopEncypt(activity);
    }

    private void v6LoginCoopEncypt(Activity activity) {
        if (this.coopEncyptEngine == null) {
            this.coopEncyptEngine = new CoopEncyptEngine(new V6Coop$8(this, activity));
        }
        this.coopEncyptEngine.getEncyptKey(mPackageName, mCoop, MD5Utils.getMD5Str(mPackageName + mLoginKey));
    }

    private void v6LoginCoopEncypt() {
        v6LoginCoopEncypt(null);
    }

    public V6Coop setNotifyV6LoginCallBack(NotifyV6LoginCallBack notifyV6LoginCallBack) {
        this.notifyV6LoginCallBack = notifyV6LoginCallBack;
        return this;
    }

    public V6Coop setSyncLoginCallBack(SyncLoginCallBack syncLoginCallBack) {
        this.syncLoginCallBack = syncLoginCallBack;
        return this;
    }

    public V6Coop setSyncLogoutCallBack(SyncLogoutCallBack syncLogoutCallBack) {
        this.syncLogoutCallBack = syncLogoutCallBack;
        return this;
    }

    public void pushLoginMsg() {
        Intent intent = new Intent();
        intent.setAction(CustomBroadcast.COOPLOGIN_LOGIN);
        this.manager.sendBroadcast(intent);
        EventManager.getDefault().nodifyObservers(new LoginEvent(), "");
    }

    public void pushLogoutMsg() {
        Intent intent = new Intent();
        intent.setAction(CustomBroadcast.COOPLOGIN_LOGOUT);
        this.manager.sendBroadcast(intent);
    }

    public void getCoopType(@Nullable V6Coop$GetCoopTypeListener v6Coop$GetCoopTypeListener) {
        if (this.getCoopTypeEngine == null) {
            this.getCoopTypeEngine = new GetCoopTypeEngine(new V6Coop$9(this, v6Coop$GetCoopTypeListener));
        }
        this.getCoopTypeEngine.getCoopType(mCoop);
    }

    public void setCoopType(@NonNull String str, @NonNull String str2) {
        COOP_LOGIN_TYPE = str;
        COOP_PAY_TYPE = str2;
        useCoopPaySDK = !"0".equals(str2);
    }

    public boolean isShowBack() {
        return this.showBack;
    }

    public void setShowBack(boolean z) {
        this.showBack = z;
    }

    public void syncRechargeStatus(String str) {
        repeated = 0;
        Intent intent = new Intent();
        intent.setAction(CustomBroadcast.COOP_RECHARGE);
        this.manager.sendBroadcast(intent);
        repeatGetUserInfo();
    }

    public NotifyAppRechargeable getNotifyAppRechargeable() {
        return this.notifyAppRechargeable;
    }

    public V6Coop setNotifyAppRechargeable(NotifyAppRechargeable notifyAppRechargeable) {
        this.notifyAppRechargeable = notifyAppRechargeable;
        return this;
    }

    public void syncUserInfo(String str, String str2) {
        if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
            String str3;
            String str4;
            if (str == null) {
                str3 = "";
            } else {
                str3 = str;
            }
            if (str2 == null) {
                str4 = "";
            } else {
                str4 = str2;
            }
            repeated = 0;
            new CoopAliasChangeEngine(new V6Coop$10(this)).coopAliasChange(mCoop, str3, SaveUserInfoUtils.getEncpass(this.mContext), LoginUtils.getLoginUID(), str4);
        }
    }

    public void repeatGetUserInfo() {
        if (repeated < 2 && !TextUtils.isEmpty(SaveUserInfoUtils.getEncpass(this.mContext))) {
            if (this.getUserInfoEngine == null) {
                this.getUserInfoEngine = new UserInfoEngine(new V6Coop$11(this));
            }
            this.getUserInfoEngine.getUserInfo(SaveUserInfoUtils.getEncpass(this.mContext), "");
        }
    }

    public void delayGetUserInfo() {
        VLScheduler.instance.schedule(5000, 0, new V6Coop$12(this));
    }

    public boolean isShowMiniGame() {
        return this.isShowMiniGame;
    }

    public void setShowMiniGame(boolean z) {
        this.isShowMiniGame = z;
    }
}
