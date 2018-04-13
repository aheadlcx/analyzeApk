package cn.xiaochuankeji.tieba.ui.utils;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.media.ExifInterface;
import android.text.TextUtils;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.math.BigDecimal;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

public class d {
    public static Type a(int i) {
        if (a.g().d()) {
            return Type.kAvatarMale;
        }
        Type type = Type.kAvatarMale;
        if (i == 2) {
            return Type.kAvatarFemale;
        }
        return type;
    }

    @TargetApi(11)
    public static void a(String str) {
        if (cn.htjyb.c.a.a(11)) {
            ((ClipboardManager) BaseApplication.getAppContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text_label", str));
        } else {
            ((android.text.ClipboardManager) BaseApplication.getAppContext().getSystemService("clipboard")).setText(str);
        }
    }

    public static int a() {
        return (int) Math.ceil((double) ((((float) e.b()) / 26.0f) * 15.0f));
    }

    public static boolean b() {
        List runningTasks = ((ActivityManager) BaseApplication.getAppContext().getApplicationContext().getSystemService("activity")).getRunningTasks(1);
        if (runningTasks.isEmpty() || ((RunningTaskInfo) runningTasks.get(0)).topActivity.getPackageName().equals(BaseApplication.getAppContext().getApplicationContext().getPackageName())) {
            return false;
        }
        return true;
    }

    public static void a(Context context) {
        String str = "推荐一个有趣的APP\"" + context.getString(R.string.app_name) + "\" \n这里有逗比神评论、暖萌右友，每天笑不停，快来一起玩吧! " + cn.xiaochuankeji.tieba.background.utils.d.a.f();
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", "好友推荐");
        intent.putExtra("android.intent.extra.TEXT", str);
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.app_name)));
    }

    public static String b(int i) {
        String str = i >= 0 ? "" : "-";
        String str2 = Math.abs(i) + "";
        int abs = Math.abs(i);
        if (abs >= 10000) {
            str2 = new BigDecimal((double) (((float) abs) / 10000.0f)).setScale(1, 4).doubleValue() + ExifInterface.LONGITUDE_WEST;
        }
        return str + str2;
    }

    @NonNull
    public static String b(String str) {
        return a(str, 20);
    }

    @NonNull
    public static String a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return " ";
        }
        StringBuilder stringBuilder = new StringBuilder();
        char[] toCharArray = str.toCharArray();
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < toCharArray.length; i4++) {
            char c = toCharArray[i4];
            if (!Character.isISOControl(c)) {
                if (c < '\u0000' || c > 'ÿ') {
                    i3 += 2;
                } else {
                    i3++;
                }
                if (i3 < i) {
                    i2 = i4;
                }
                stringBuilder.append(c);
            }
        }
        String stringBuilder2 = stringBuilder.toString();
        return i3 > i ? stringBuilder2.substring(0, i2 + 1) + "…" : stringBuilder2;
    }

    public static String a(long j) {
        Appendable stringBuilder = new StringBuilder();
        Formatter formatter = new Formatter(stringBuilder, Locale.getDefault());
        long j2 = j / 1000;
        long j3 = j2 % 60;
        long j4 = (j2 / 60) % 60;
        j2 /= 3600;
        stringBuilder.setLength(0);
        if (j2 > 0) {
            return formatter.format("%d:%02d:%02d", new Object[]{Long.valueOf(j2), Long.valueOf(j4), Long.valueOf(j3)}).toString();
        }
        return formatter.format("%02d:%02d", new Object[]{Long.valueOf(j4), Long.valueOf(j3)}).toString();
    }
}
