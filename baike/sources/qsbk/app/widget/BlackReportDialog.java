package qsbk.app.widget;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpTask;
import qsbk.app.im.ChatMsg;
import qsbk.app.im.UserChatManager;

public class BlackReportDialog {
    private Context a;
    private String b;
    private AlertDialog c;
    private IBlackReportSuccessListener d;

    public interface IBlackReportSuccessListener {
        void onBlackReportSuccess();
    }

    public BlackReportDialog(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    public void createDialog() {
        this.c = new Builder(this.a).setItems(new String[]{"拉黑", "拉黑并举报"}, new ae(this)).setCancelable(true).create();
    }

    public void show() {
        if (this.c != null) {
            this.c.show();
        }
    }

    public void dismiss() {
        if (this.c != null) {
            this.c.dismiss();
        }
    }

    public void registerListener(IBlackReportSuccessListener iBlackReportSuccessListener) {
        this.d = iBlackReportSuccessListener;
    }

    public void unregisterListener() {
        this.d = null;
    }

    public void showBlackConfirmDialog() {
        new Builder(this.a).setCancelable(true).setMessage("拉黑TA后，将\n- 糗事各列表都看不到ta的糗事\n- ta无法粉我，粉关系自动解除\n- ta无法评论转发我的帖子\n- ta无法看到我的动态\n\n是否确认拉黑？").setNeutralButton("取消", new ag(this)).setPositiveButton("确认", new af(this)).create().show();
        dismiss();
    }

    public void showRemoveFansDialog() {
        new Builder(this.a).setCancelable(true).setMessage("是否移除粉丝？").setNeutralButton("移除", new ai(this)).setPositiveButton("取消", new ah(this)).create().show();
        dismiss();
    }

    public void showReportDialog() {
        new Builder(this.a).setCancelable(true).setItems(new String[]{"骚扰信息", "欺诈", "政治敏感", "淫秽色情", "其他"}, new aj(this)).create().show();
        dismiss();
    }

    private void a(String str) {
        new Builder(this.a).setCancelable(false).setTitle(str).setMessage("可在\"TA的主页\"和\"设置 > 黑名单\"将其解除").setNeutralButton("知道了", new ak(this)).create().show();
    }

    private void b(String str) {
        String str2 = Constants.REL_BLACK;
        Object[] objArr = new Object[1];
        QsbkApp.getInstance();
        objArr[0] = QsbkApp.currentUser.userId;
        String format = String.format(str2, objArr);
        Map hashMap = new HashMap();
        hashMap.put("uid", this.b);
        if (TextUtils.isEmpty(str)) {
            str2 = "black";
        } else {
            hashMap.put("report", str);
            str2 = "report";
        }
        if (str2.equalsIgnoreCase("black")) {
            a("已拉黑");
        } else {
            a("已举报并拉黑");
        }
        a();
        HttpTask httpTask = new HttpTask(str2, format, new al(this));
        httpTask.setMapParams(hashMap);
        httpTask.execute(new Void[0]);
    }

    private void a() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(0, this.b);
            jSONObject.put("delsession", jSONArray);
            UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).onMessageReceived(new ChatMsg(201, jSONObject.toString()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void c(String str) {
        String str2 = Constants.REL_DELETE_FANS;
        Object[] objArr = new Object[1];
        QsbkApp.getInstance();
        objArr[0] = QsbkApp.currentUser.userId;
        str2 = String.format(str2, objArr);
        Map hashMap = new HashMap();
        hashMap.put("uid", this.b);
        HttpTask httpTask = new HttpTask("removeFans", str2, new am(this));
        httpTask.setMapParams(hashMap);
        httpTask.execute(new Void[0]);
    }
}
