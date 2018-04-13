package cn.v6.sixrooms.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Rect;
import android.os.Environment;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.pay.ui.activity.CoopPayAcitvity;
import cn.v6.sixrooms.pay.ui.activity.RechargeActivity;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.room.fragment.RoomBaseFragment;
import cn.v6.sixrooms.ui.phone.RegisterActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class SixRoomsUtils {
    public static final int MATCH_PARENT = -1;
    public static final int WRAP_CONTENT = -2;

    public static LayoutParams paramsFrame(int i, int i2) {
        return new LayoutParams(i, i2);
    }

    public static LayoutParams paramsFrame(int i, int i2, int i3) {
        LayoutParams paramsFrame = paramsFrame(i, i2);
        paramsFrame.gravity = i3;
        return paramsFrame;
    }

    public static LayoutParams paramsFrame(int i, int i2, int i3, int i4) {
        LayoutParams paramsFrame = paramsFrame(i, i2);
        paramsFrame.leftMargin = i3;
        paramsFrame.topMargin = i4;
        return paramsFrame;
    }

    public static ViewGroup.LayoutParams paramsGroup(int i, int i2) {
        return new ViewGroup.LayoutParams(i, i2);
    }

    public static LinearLayout.LayoutParams paramsLinear(int i, int i2) {
        return paramsLinear(i, i2, 0.0f);
    }

    public static LinearLayout.LayoutParams paramsLinear(int i, int i2, float f) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i2);
        layoutParams.weight = f;
        return layoutParams;
    }

    public static LinearLayout.LayoutParams paramsLinear(int i, int i2, float f, int i3) {
        LinearLayout.LayoutParams paramsLinear = paramsLinear(i, i2, f);
        paramsLinear.gravity = i3;
        return paramsLinear;
    }

    public static RelativeLayout.LayoutParams paramsRelative(int i, int i2) {
        return new RelativeLayout.LayoutParams(i, i2);
    }

    public static RelativeLayout.LayoutParams paramsRelative(int i, int i2, int i3) {
        RelativeLayout.LayoutParams paramsRelative = paramsRelative(i, i2);
        paramsRelative.addRule(i3);
        paramsRelative.addRule(15);
        return paramsRelative;
    }

    public static RelativeLayout.LayoutParams paramsRelative(int i, int i2, int i3, int i4) {
        RelativeLayout.LayoutParams paramsRelative = paramsRelative(i, i2);
        paramsRelative.addRule(i3);
        paramsRelative.addRule(i4);
        return paramsRelative;
    }

    public static final int dip2px(float f) {
        return (int) ((V6Coop.getInstance().getContext().getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public static String parseTypeToTitle(String str) {
        String str2 = "热门";
        Object obj = -1;
        switch (str.hashCode()) {
            case -2057196854:
                if (str.equals(CommonStrs.TYPE_FOLLOW_FOCUS)) {
                    obj = 16;
                    break;
                }
                break;
            case -2027282449:
                if (str.equals(CommonStrs.TYPE_FOLLOW)) {
                    obj = 15;
                    break;
                }
                break;
            case -2008465223:
                if (str.equals(CommonStrs.TYPE_SPECIAL)) {
                    obj = 18;
                    break;
                }
                break;
            case -1893281248:
                if (str.equals(CommonStrs.TYPE_GIRL_TEAM)) {
                    obj = 21;
                    break;
                }
                break;
            case -1115058732:
                if (str.equals(CommonStrs.TYPE_HEADLINE)) {
                    obj = 14;
                    break;
                }
                break;
            case -808338504:
                if (str.equals(CommonStrs.TYPE_VRECOM)) {
                    obj = 13;
                    break;
                }
                break;
            case 0:
                if (str.equals("")) {
                    obj = 12;
                    break;
                }
                break;
            case 3583:
                if (str.equals(CommonStrs.TYPE_STAR)) {
                    obj = 3;
                    break;
                }
                break;
            case 3584:
                if (str.equals(CommonStrs.TYPE_RED)) {
                    obj = 4;
                    break;
                }
                break;
            case 3586:
                if (str.equals(CommonStrs.TYPE_BIGSTAR)) {
                    obj = 2;
                    break;
                }
                break;
            case 3587:
                if (str.equals(CommonStrs.TYPE_SUPERSTAR)) {
                    obj = 1;
                    break;
                }
                break;
            case 3675:
                if (str.equals(CommonStrs.TYPE_MUSIC)) {
                    obj = 5;
                    break;
                }
                break;
            case 3676:
                if (str.equals(CommonStrs.TYPE_DANCE)) {
                    obj = 8;
                    break;
                }
                break;
            case 3677:
                if (str.equals(CommonStrs.TYPE_MC)) {
                    obj = 6;
                    break;
                }
                break;
            case 3678:
                if (str.equals(CommonStrs.TYPE_TALK)) {
                    obj = 7;
                    break;
                }
                break;
            case 111121:
                if (str.equals(CommonStrs.TYPE_HOTSTAR)) {
                    obj = null;
                    break;
                }
                break;
            case 3343885:
                if (str.equals(CommonStrs.TYPE_MALE)) {
                    obj = 10;
                    break;
                }
                break;
            case 103985881:
                if (str.equals(CommonStrs.TYPE_MLIVE)) {
                    obj = 9;
                    break;
                }
                break;
            case 165177003:
                if (str.equals(CommonStrs.TYPE_LIANMAI)) {
                    obj = 11;
                    break;
                }
                break;
            case 565809721:
                if (str.equals(CommonStrs.TYPE_FOLLOW_COMMON)) {
                    obj = 17;
                    break;
                }
                break;
            case 784886313:
                if (str.equals(CommonStrs.TYPE_SIX_LIVE)) {
                    obj = 20;
                    break;
                }
                break;
            case 1901043637:
                if (str.equals(CommonStrs.TYPE_LOCATION)) {
                    obj = 19;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return "炽星";
            case 1:
                return "超星";
            case 2:
                return "巨星";
            case 3:
                return "明星";
            case 4:
                return "红人";
            case 5:
                return "好声音";
            case 6:
                return "搞笑";
            case 7:
                return "唠嗑";
            case 8:
                return "舞蹈";
            case 9:
                return "手机红人";
            case 10:
                return "男神";
            case 11:
                return "连麦";
            case 12:
                return "热门";
            case 13:
                return "小V推荐";
            case 14:
                return "全站头条";
            case 15:
                return "我的关注";
            case 16:
                return "重点关注";
            case 17:
                return "其他关注";
            case 18:
                return "特色";
            case 19:
                return "附近";
            case 20:
                return "六现场";
            case 21:
                return "女团";
            default:
                return str2;
        }
    }

    public static int parseTypeId(String str) {
        int i = -1;
        switch (str.hashCode()) {
            case -2057196854:
                if (str.equals(CommonStrs.TYPE_FOLLOW_FOCUS)) {
                    i = 12;
                    break;
                }
                break;
            case -2008465223:
                if (str.equals(CommonStrs.TYPE_SPECIAL)) {
                    i = 13;
                    break;
                }
                break;
            case -1115058732:
                if (str.equals(CommonStrs.TYPE_HEADLINE)) {
                    i = 11;
                    break;
                }
                break;
            case -808338504:
                if (str.equals(CommonStrs.TYPE_VRECOM)) {
                    i = 10;
                    break;
                }
                break;
            case 0:
                if (str.equals("")) {
                    i = 9;
                    break;
                }
                break;
            case 3583:
                if (str.equals(CommonStrs.TYPE_STAR)) {
                    i = 3;
                    break;
                }
                break;
            case 3584:
                if (str.equals(CommonStrs.TYPE_RED)) {
                    i = 4;
                    break;
                }
                break;
            case 3586:
                if (str.equals(CommonStrs.TYPE_BIGSTAR)) {
                    i = 2;
                    break;
                }
                break;
            case 3587:
                if (str.equals(CommonStrs.TYPE_SUPERSTAR)) {
                    i = 1;
                    break;
                }
                break;
            case 3675:
                if (str.equals(CommonStrs.TYPE_MUSIC)) {
                    i = 5;
                    break;
                }
                break;
            case 3676:
                if (str.equals(CommonStrs.TYPE_DANCE)) {
                    i = 8;
                    break;
                }
                break;
            case 3677:
                if (str.equals(CommonStrs.TYPE_MC)) {
                    i = 6;
                    break;
                }
                break;
            case 3678:
                if (str.equals(CommonStrs.TYPE_TALK)) {
                    i = 7;
                    break;
                }
                break;
            case 111121:
                if (str.equals(CommonStrs.TYPE_HOTSTAR)) {
                    i = 0;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            case 7:
                return 9;
            case 8:
                return 10;
            case 9:
                return 0;
            case 10:
                return 13;
            case 11:
                return 14;
            case 12:
                return 15;
            case 13:
                return 17;
            default:
                return 0;
        }
    }

    public static int parseTypeToDrawableId(String str) {
        int i = -1;
        switch (str.hashCode()) {
            case -2027282449:
                if (str.equals(CommonStrs.TYPE_FOLLOW)) {
                    i = 8;
                    break;
                }
                break;
            case 0:
                if (str.equals("")) {
                    i = 0;
                    break;
                }
                break;
            case 3675:
                if (str.equals(CommonStrs.TYPE_MUSIC)) {
                    i = 2;
                    break;
                }
                break;
            case 3676:
                if (str.equals(CommonStrs.TYPE_DANCE)) {
                    i = 5;
                    break;
                }
                break;
            case 3677:
                if (str.equals(CommonStrs.TYPE_MC)) {
                    i = 3;
                    break;
                }
                break;
            case 3678:
                if (str.equals(CommonStrs.TYPE_TALK)) {
                    i = 4;
                    break;
                }
                break;
            case 3343885:
                if (str.equals(CommonStrs.TYPE_MALE)) {
                    i = 6;
                    break;
                }
                break;
            case 103985881:
                if (str.equals(CommonStrs.TYPE_MLIVE)) {
                    i = 1;
                    break;
                }
                break;
            case 1901043637:
                if (str.equals(CommonStrs.TYPE_LOCATION)) {
                    i = 7;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                return R.drawable.hall_column_type_hot;
            case 1:
                return R.drawable.hall_column_type_live_phone;
            case 2:
                return R.drawable.hall_column_type_good_sound;
            case 3:
                return R.drawable.hall_column_type_funny;
            case 4:
                return R.drawable.hall_column_type_talk;
            case 5:
                return R.drawable.hall_column_type_madden;
            case 6:
                return R.drawable.hall_column_type_mengod;
            case 7:
                return R.drawable.hall_column_type_location;
            case 8:
                return R.drawable.hall_column_type_follow;
            default:
                return 0;
        }
    }

    public static void gotoLogin(Activity activity) {
        if (V6Coop.getInstance().getNotifyAppLoginCallBack() == null) {
            V6Coop.getInstance().goToV6Login(activity);
        } else if ("1".equals(V6Coop.COOP_LOGIN_TYPE)) {
            V6Coop.getInstance().getNotifyAppLoginCallBack().appNeedLogin();
        } else {
            V6Coop.getInstance().goToV6Login(activity);
        }
    }

    public static void gotoRegister(Activity activity, boolean z) {
        Intent intent = new Intent(activity, RegisterActivity.class);
        intent.putExtra("isBroadCast", z);
        activity.startActivity(intent);
    }

    public static void gotoRegister(Activity activity) {
        gotoRegister(activity, false);
    }

    public static void goToRechargeActivi(Activity activity) {
        if ("1".equals(V6Coop.COOP_PAY_TYPE)) {
            activity.startActivity(new Intent(activity, CoopPayAcitvity.class));
        } else {
            activity.startActivity(new Intent(activity, RechargeActivity.class));
        }
    }

    public static void gotoRoomActivity(Activity activity, String str, String str2) {
        if (!V6Coop.flag) {
            V6Coop.flag = true;
            Intent intent = new Intent(activity, RoomActivity.class);
            intent.putExtra("rid", str);
            intent.putExtra(RoomBaseFragment.RUID_KEY, str2);
            activity.startActivity(intent);
        }
    }

    public static boolean isShouldHideInput(View view, MotionEvent motionEvent) {
        if (view == null || !(view instanceof EditText)) {
            return false;
        }
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        int i = rect.left;
        int i2 = rect.top;
        int i3 = rect.bottom;
        int i4 = rect.right;
        if (motionEvent.getX() <= ((float) i) || motionEvent.getX() >= ((float) i4) || motionEvent.getY() <= ((float) i2) || motionEvent.getY() >= ((float) i3)) {
            return true;
        }
        return false;
    }

    public static FileOutputStream fileOpenToWrite(String str) {
        try {
            File file = new File(str);
            if (file.isDirectory()) {
                return null;
            }
            if (file.exists() && !file.delete()) {
                return null;
            }
            File parentFile = file.getParentFile();
            if (parentFile == null || parentFile.exists() || parentFile.mkdirs()) {
                return new FileOutputStream(file);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static final String V(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    public static boolean appIsDebug() {
        if ((androidPackageInfo(V6Coop.getInstance().getContext(), 0).applicationInfo.flags & 2) != 0) {
            return true;
        }
        return false;
    }

    public static PackageInfo androidPackageInfo(Context context, int i) {
        return androidPackageInfo(context, context.getPackageName(), i);
    }

    public static PackageInfo androidPackageInfo(Context context, String str, int i) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, i);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo;
    }

    public static boolean externalStorageExist() {
        return Environment.getExternalStorageState().equalsIgnoreCase("mounted");
    }

    public static final String stringLastToken(String str, char c) {
        if (str == null) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf(c);
        return lastIndexOf >= 0 ? str.substring(lastIndexOf + 1) : str;
    }

    public static String appName(@NonNull Context context) {
        return stringLastToken(context.getPackageName(), '.');
    }

    public static boolean isWeixinAvilible(Context context) {
        List installedPackages = context.getPackageManager().getInstalledPackages(0);
        if (installedPackages == null) {
            return false;
        }
        for (int i = 0; i < installedPackages.size(); i++) {
            if (((PackageInfo) installedPackages.get(i)).packageName.equals("com.tencent.mm")) {
                return true;
            }
        }
        return false;
    }

    public static final boolean threadInMain() {
        return Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId();
    }

    public static void notifyCoopRecharge(String str, String str2) {
        if (V6Coop.getInstance().getNotifyAppRechargeable() != null) {
            V6Coop.getInstance().getNotifyAppRechargeable().appNeedRecharge(str, str2);
        }
    }
}
