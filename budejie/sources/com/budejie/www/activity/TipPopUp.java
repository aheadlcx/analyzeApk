package com.budejie.www.activity;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.budejie.www.R;
import com.budejie.www.bean.RuleBean;
import com.budejie.www.c.k;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.util.an;
import java.util.Calendar;

public class TipPopUp {

    public enum TypeControl {
        dingtie,
        pinglun,
        shenhe,
        jubao,
        sex,
        education,
        birthday,
        post,
        share,
        commit,
        modify,
        text,
        pic,
        voice,
        vedio,
        tiezi,
        zhuanqu,
        kehuduan
    }

    public static void a(Context context, TypeControl typeControl) {
        float f = 1.0f;
        SharedPreferences sharedPreferences = context.getSharedPreferences("tip", 0);
        Editor edit = sharedPreferences.edit();
        k kVar = new k(context);
        String str = "";
        if (an.a(context.getSharedPreferences("weiboprefer", 0))) {
            String str2;
            RuleBean a;
            switch (TipPopUp$3.a[typeControl.ordinal()]) {
                case 1:
                    a = kVar.a(context, "审核帖子");
                    if (a != null) {
                        f = a.money;
                    }
                    str2 = "每次审核帖子 +" + ((int) f) + "积分";
                    break;
                case 2:
                    a = kVar.a(context, "顶帖子");
                    if (a != null) {
                        f = a.money;
                    }
                    str2 = "每次顶帖 +" + ((int) f) + "积分";
                    break;
                case 3:
                    a = kVar.a(context, "评论帖子");
                    if (a != null) {
                        f = a.money;
                    }
                    str2 = "每次评论帖子 +" + ((int) f) + "积分";
                    break;
                default:
                    str2 = str;
                    break;
            }
            if (sharedPreferences.getBoolean(typeControl.toString(), true)) {
                try {
                    a(context, str2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                edit.putBoolean(typeControl.toString(), false);
                edit.commit();
            }
        }
    }

    public static void a(Context context, String str) {
        Builder builder = new Builder(context);
        builder.setTitle(R.string.count_tip_title);
        builder.setMessage(str);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.count_tip_bt, new TipPopUp$1());
        builder.create().show();
    }

    public static void a(Context context, k kVar, Editor editor, RuleBean ruleBean, TypeControl typeControl) {
        float f;
        switch (TipPopUp$3.a[typeControl.ordinal()]) {
            case 7:
                ruleBean = kVar.a(context, "发段子");
                break;
            case 8:
                ruleBean = kVar.a(context, "发图片");
                break;
            case 9:
                ruleBean = kVar.a(context, "发音频");
                break;
            case 10:
                ruleBean = kVar.a(context, "发视频");
                break;
            case 11:
                ruleBean = kVar.a(context, "分享帖子");
                break;
            case 12:
                ruleBean = kVar.a(context, "分享专区");
                break;
            case 13:
                ruleBean = kVar.a(context, "分享客户端");
                break;
            case 14:
                ruleBean = kVar.a(context, "举报帖子");
                break;
        }
        if (ruleBean == null) {
            f = 5.0f;
        } else {
            f = ruleBean.up_limit;
        }
        editor.putFloat(typeControl.toString(), f);
        editor.commit();
    }

    public static float a(Context context, k kVar, RuleBean ruleBean, TypeControl typeControl) {
        switch (TipPopUp$3.a[typeControl.ordinal()]) {
            case 7:
                ruleBean = kVar.a(context, "发段子");
                break;
            case 8:
                ruleBean = kVar.a(context, "发图片");
                break;
            case 9:
                ruleBean = kVar.a(context, "发音频");
                break;
            case 10:
                ruleBean = kVar.a(context, "发视频");
                break;
            case 11:
                ruleBean = kVar.a(context, "分享帖子");
                break;
            case 12:
                ruleBean = kVar.a(context, "分享专区");
                break;
            case 13:
                ruleBean = kVar.a(context, "分享客户端");
                break;
            case 14:
                ruleBean = kVar.a(context, "举报帖子");
                break;
        }
        if (ruleBean == null) {
            return 1.0f;
        }
        return ruleBean.money;
    }

    public static void a(Context context, TypeControl typeControl, TypeControl typeControl2) {
        k kVar = new k(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("tip", 0);
        Editor edit = sharedPreferences.edit();
        RuleBean ruleBean = new RuleBean();
        Calendar instance = Calendar.getInstance();
        int i = instance.get(1);
        int i2 = instance.get(2);
        int i3 = instance.get(5);
        if (sharedPreferences.getBoolean("DATE", true)) {
            a(context, kVar, edit, ruleBean, typeControl2);
            edit.putInt("YEAR", instance.get(1));
            edit.putInt("MONTH", instance.get(2));
            edit.putInt("DAY_OF_MONTH", instance.get(5));
            edit.putBoolean("DATE", false);
            edit.commit();
        }
        if (!(i3 == sharedPreferences.getInt("DAY_OF_MONTH", 0) && i2 == sharedPreferences.getInt("MONTH", 0) && i == sharedPreferences.getInt("YEAR", 0))) {
            a(context, kVar, edit, ruleBean, typeControl2);
            edit.putBoolean("DATE", true);
            edit.commit();
        }
        if (sharedPreferences.getFloat(typeControl2.toString(), -2.0f) == -2.0f) {
            a(context, kVar, edit, ruleBean, typeControl2);
        }
        if (an.a(context.getSharedPreferences("weiboprefer", 0))) {
            float a = a(context, kVar, ruleBean, typeControl2);
            if (sharedPreferences.getFloat(typeControl2.toString(), -2.0f) > 0.0f) {
                float f = sharedPreferences.getFloat(typeControl2.toString(), -2.0f);
                a(context, typeControl, (int) a, false);
                edit.putFloat(typeControl2.toString(), (float) (((int) f) - 1));
                edit.commit();
                return;
            }
            a(context, typeControl, (int) a, true);
            edit.putFloat(typeControl2.toString(), 0.0f);
            edit.commit();
        } else if (typeControl == TypeControl.share) {
            a(context, typeControl, (int) 0.0f, true);
        }
    }

    private static void a(Context context, TypeControl typeControl, int i, boolean z) {
        try {
            new LTCountTipDialog(context, typeControl, i, z).show();
        } catch (Exception e) {
        }
    }

    public static void a(Context context) {
        BudejieApplication.a.a(RequstMethod.GET, j.g(), new j(context), new TipPopUp$2(context));
    }
}
