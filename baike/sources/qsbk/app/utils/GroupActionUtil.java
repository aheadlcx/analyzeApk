package qsbk.app.utils;

import android.app.ProgressDialog;
import android.content.Context;
import com.alipay.sdk.cons.b;
import com.tencent.open.SocialConstants;
import com.tencent.stat.DeviceInfo;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.core.utils.ACache;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.http.SimpleHttpTask;

public class GroupActionUtil {
    public static final int INVALID_ID = -1;
    private static final int[] a = new int[]{600, ACache.TIME_HOUR, 43200, ACache.TIME_DAY};

    public static class ProgressDialogCallBack implements SimpleCallBack {
        public ProgressDialog dialog;

        public ProgressDialogCallBack(Context context, String str) {
            this(context, str, true);
        }

        public ProgressDialogCallBack(Context context, String str, boolean z) {
            this.dialog = new ProgressDialog(context);
            if (str != null) {
                this.dialog.setMessage(str);
            }
            this.dialog.setCancelable(false);
            if (z) {
                show();
            }
        }

        public ProgressDialogCallBack setCancelable(boolean z) {
            this.dialog.setCancelable(z);
            return this;
        }

        public ProgressDialogCallBack show() {
            this.dialog.show();
            return this;
        }

        public void onSuccess(JSONObject jSONObject) {
            this.dialog.dismiss();
        }

        public void onFailure(int i, String str) {
            this.dialog.dismiss();
        }
    }

    public static void agreeForJoinGroup(int i, String str, String str2, boolean z, int i2, SimpleCallBack simpleCallBack) {
        String format = String.format(Constants.URL_REPLY_FOR_JOIN_GROUP, new Object[]{Integer.valueOf(i)});
        Map hashMap = new HashMap();
        hashMap.put(b.c, String.valueOf(i));
        hashMap.put(DeviceInfo.TAG_MID, str2);
        hashMap.put("uid", str);
        if (i2 != -1) {
            hashMap.put("iid", String.valueOf(i2));
        }
        hashMap.put(SocialConstants.PARAM_ACT, z ? "1" : "0");
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(format, simpleCallBack);
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    public static void applyForGroup(String str, int i, int i2, SimpleCallBack simpleCallBack) {
        String format = String.format(Constants.URL_APPLY_FOR_GROUP, new Object[]{Integer.valueOf(i)});
        Map hashMap = new HashMap();
        hashMap.put(b.c, String.valueOf(i));
        if (i2 != -1) {
            hashMap.put("fromid", String.valueOf(i2));
        }
        hashMap.put("reason", str);
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(format, simpleCallBack);
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    public static void muteMemberIfConfirm(Context context, int i, String str, SimpleCallBack simpleCallBack) {
        new s(context, simpleCallBack, i, str).show();
    }

    public static void muteMember(int i, String str, int i2, SimpleCallBack simpleCallBack) {
        Map hashMap = new HashMap();
        hashMap.put(b.c, String.valueOf(i));
        hashMap.put("sids", str);
        hashMap.put("time", Integer.valueOf(i2));
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.URL_GROUP_MEMBER_MUTE, new Object[]{Integer.valueOf(i)}), simpleCallBack);
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    public static void unmuteMember(int i, String str, SimpleCallBack simpleCallBack) {
        Map hashMap = new HashMap();
        hashMap.put(b.c, String.valueOf(i));
        hashMap.put("sid", str);
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.URL_GROUP_MEMBER_UNMUTE, new Object[]{Integer.valueOf(i)}), simpleCallBack);
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    public static void deleteMemberIfConfirm(Context context, int i, String str, String str2, SimpleCallBack simpleCallBack) {
        new w(context, str2, simpleCallBack, i, str).show();
    }

    public static void deleteMember(int i, String str, SimpleCallBack simpleCallBack) {
        Map hashMap = new HashMap();
        hashMap.put(b.c, String.valueOf(i));
        hashMap.put("kids", str);
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.URL_GROUP_MEMBER_DELETE, new Object[]{Integer.valueOf(i)}), simpleCallBack);
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    public static void appointAdminIfConfirm(Context context, int i, String str, String str2, SimpleCallBack simpleCallBack) {
        new z(context, str2, simpleCallBack, i, str).show();
    }

    public static void firedAdminIfConfirm(Context context, int i, String str, String str2, SimpleCallBack simpleCallBack) {
        new ac(context, str2, simpleCallBack, i, str).show();
    }

    public static void appointAdmin(int i, String str, SimpleCallBack simpleCallBack) {
        Map hashMap = new HashMap();
        hashMap.put(b.c, String.valueOf(i));
        hashMap.put("aid", str);
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.URL_GROUP_MEMBER_APPOINT, new Object[]{Integer.valueOf(i)}), simpleCallBack);
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    public static void firedAdmin(int i, String str, SimpleCallBack simpleCallBack) {
        Map hashMap = new HashMap();
        hashMap.put(b.c, String.valueOf(i));
        hashMap.put("aid", str);
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.URL_GROUP_MEMBER_FIRED, new Object[]{Integer.valueOf(i)}), simpleCallBack);
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }
}
