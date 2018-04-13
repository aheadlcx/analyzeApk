package cn.xiaochuankeji.tieba.ui.auth;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import cn.xiaochuankeji.tieba.background.beans.GrayConfigBean;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.my.account.InputPhoneNumberActivity;
import java.util.Vector;

public class a {
    private static Vector<Integer> a = new Vector(20);
    private static Vector<Integer> b = new Vector(20);

    public static boolean a(@NonNull FragmentActivity fragmentActivity, String str, int i) {
        return a(fragmentActivity, str, i, -1);
    }

    public static boolean a(@NonNull Activity activity, String str, int i, int i2) {
        if (activity == null) {
            return false;
        }
        if (i == -999) {
            return true;
        }
        if (cn.xiaochuankeji.tieba.background.a.g().d()) {
            LoginActivity.a(activity, str, i, i2);
            if (i == 1000) {
                return false;
            }
            g.a("请先登录");
            return false;
        } else if (cn.xiaochuankeji.tieba.background.a.g().q().isBind()) {
            return true;
        } else {
            if (i >= 1000) {
                return true;
            }
            if (b.contains(Integer.valueOf(i))) {
                a(activity, -1);
                return false;
            }
            SharedPreferences a = cn.xiaochuankeji.tieba.background.a.a();
            if (41901 == a.getInt("kCertifyCounter", -1) || !a.contains(Integer.valueOf(i))) {
                return true;
            }
            if (activity.isFinishing()) {
                return false;
            }
            if (activity instanceof FragmentActivity) {
                CertifyFragment.a(((FragmentActivity) activity).getSupportFragmentManager());
            }
            a.edit().putInt("kCertifyCounter", 41901).apply();
            return false;
        }
    }

    public static void a(@NonNull Activity activity, int i) {
        InputPhoneNumberActivity.a(activity, i, "certify");
    }

    private static boolean a(int i) {
        return a.contains(Integer.valueOf(i));
    }

    public static void a(GrayConfigBean grayConfigBean) {
        a.clear();
        b.clear();
        if (grayConfigBean.ab_smz_open_register_enable == 1) {
            a.add(Integer.valueOf(60));
        }
        if (grayConfigBean.ab_smz_light_enable == 1) {
            if (grayConfigBean.ab_smz_light_force == 1) {
                b.add(Integer.valueOf(12));
                b.add(Integer.valueOf(-12));
                b.add(Integer.valueOf(14));
                b.add(Integer.valueOf(-14));
                b.add(Integer.valueOf(5));
            } else {
                a.add(Integer.valueOf(12));
                a.add(Integer.valueOf(-12));
                a.add(Integer.valueOf(14));
                a.add(Integer.valueOf(-14));
                a.add(Integer.valueOf(5));
            }
        }
        if (grayConfigBean.ab_smz_heavy_enable != 1) {
            return;
        }
        if (grayConfigBean.ab_smz_heavy_force == 1) {
            b.add(Integer.valueOf(7));
            b.add(Integer.valueOf(4));
            b.add(Integer.valueOf(2));
            b.add(Integer.valueOf(8));
            b.add(Integer.valueOf(1));
            b.add(Integer.valueOf(3));
            b.add(Integer.valueOf(6));
            b.add(Integer.valueOf(9));
            b.add(Integer.valueOf(41));
            return;
        }
        a.add(Integer.valueOf(7));
        a.add(Integer.valueOf(4));
        a.add(Integer.valueOf(2));
        a.add(Integer.valueOf(8));
        a.add(Integer.valueOf(1));
        a.add(Integer.valueOf(3));
        a.add(Integer.valueOf(6));
        a.add(Integer.valueOf(9));
        a.add(Integer.valueOf(41));
    }

    public static boolean a() {
        return a(60) && !cn.xiaochuankeji.tieba.background.a.g().q().isBind();
    }
}
