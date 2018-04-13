package qsbk.app.nearby.ui;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Pair;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.model.UserInfo;
import qsbk.app.utils.AstrologyUtils;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ToastAndDialog;

class v extends AsyncTask<String, Void, Pair<Integer, String>> {
    JSONObject a = null;
    final /* synthetic */ InfoCompleteActivity b;

    v(InfoCompleteActivity infoCompleteActivity) {
        this.b = infoCompleteActivity;
    }

    protected Pair<Integer, String> a(String... strArr) {
        try {
            Map hashMap = new HashMap();
            if (this.b.A()) {
                hashMap.put("gender", String.valueOf(this.b.D));
            }
            if (this.b.B() && !this.b.G.equalsIgnoreCase(QsbkApp.currentUser.userName)) {
                hashMap.put(QsbkDatabase.LOGIN, this.b.G);
            }
            if (this.b.F != QsbkApp.currentUser.birthday * 1000) {
                hashMap.put("birthday", String.valueOf((int) (this.b.F / 1000)));
            }
            if (!(TextUtils.isEmpty(this.b.O) || this.b.O.equals(QsbkApp.currentUser.job))) {
                hashMap.put("job", this.b.O);
            }
            if (!(TextUtils.isEmpty(this.b.H) || this.b.H.equals(QsbkApp.currentUser.hometown))) {
                hashMap.put(HometownDialogFragment.KEY_HOMETOWN, this.b.H);
            }
            if (!(TextUtils.isEmpty(this.b.J) || this.b.I.equals(QsbkApp.currentUser.mobileBrand))) {
                hashMap.put("mobile_brand", this.b.J);
            }
            if (!(TextUtils.isEmpty(this.b.E) || this.b.E.equals(QsbkApp.currentUser.emotion))) {
                hashMap.put("emotion", this.b.E);
            }
            if (!(this.b.K.equals(QsbkApp.currentUser.haunt) || this.b.K.equals(HauntEditorActivity.HAUNT_NAME_OF_SHOW))) {
                if (TextUtils.isEmpty(this.b.K)) {
                    hashMap.put("haunt", "hide");
                } else {
                    hashMap.put("haunt", this.b.K);
                }
            }
            hashMap.put("device_info", String.format("%s|%s|%s|%s", new Object[]{Build.MODEL, Build.BRAND, Build.DEVICE, Build.DISPLAY}));
            String format = String.format(Constants.PERSONAL_INFO_URL, new Object[]{QsbkApp.currentUser.userId});
            LogUtil.d("url:" + format);
            this.a = new JSONObject(HttpClient.getIntentce().post(format, hashMap));
            return new Pair(Integer.valueOf(this.a.getInt(NotificationCompat.CATEGORY_ERROR)), this.a.optString("err_msg"));
        } catch (Exception e) {
            e.printStackTrace();
            return new Pair(Integer.valueOf(9999), HttpClient.getLocalErrorStr());
        }
    }

    protected void a(Pair<Integer, String> pair) {
        this.b.G();
        if (((Integer) pair.first).equals(Integer.valueOf(0))) {
            String str;
            LogUtil.e("emotion====" + this.b.E);
            QsbkApp.currentUser.gender = this.b.D;
            QsbkApp.currentUser.birthday = this.b.F / 1000;
            QsbkApp.currentUser.job = this.b.O;
            UserInfo userInfo = QsbkApp.currentUser;
            if (this.b.K.equals(HauntEditorActivity.HAUNT_NAME_OF_SHOW)) {
                str = "";
            } else {
                str = this.b.K;
            }
            userInfo.haunt = str;
            QsbkApp.currentUser.mobileBrand = this.b.I;
            QsbkApp.currentUser.hometown = this.b.H;
            QsbkApp.currentUser.userName = this.b.G;
            QsbkApp.currentUser.emotion = this.b.E;
            QsbkApp.currentUser.age = AstrologyUtils.getAge(this.b.q);
            QsbkApp.getInstance().setCurrentUserToLocal();
            Intent intent = new Intent();
            intent.setAction(InfoCompleteActivity.ACTION_CHANGE_USERINFO);
            LocalBroadcastManager.getInstance(this.b).sendBroadcast(intent);
            intent = new Intent();
            intent.putExtra("user_info", QsbkApp.currentUser);
            this.b.setResult(-1, intent);
            ToastAndDialog.makePositiveToast(this.b, "个人资料保存成功。").show();
            super.finish();
            return;
        }
        ToastAndDialog.makeNegativeToast(this.b, (String) pair.second).show();
    }
}
