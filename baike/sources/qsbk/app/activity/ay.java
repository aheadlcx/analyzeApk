package qsbk.app.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.support.v4.app.Fragment;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.GroupInfo;
import qsbk.app.utils.ToastAndDialog;

final class ay implements SimpleCallBack {
    final /* synthetic */ Context a;
    final /* synthetic */ Fragment b;
    final /* synthetic */ GroupInfo c;
    final /* synthetic */ int d;
    final /* synthetic */ int e;

    ay(Context context, Fragment fragment, GroupInfo groupInfo, int i, int i2) {
        this.a = context;
        this.b = fragment;
        this.c = groupInfo;
        this.d = i;
        this.e = i2;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            int i = jSONObject.getInt("left_count");
            ApplyForGroupActivity.b(this.a, this.b, this.c, this.d, jSONObject.getInt("join_count"), i);
        } catch (JSONException e) {
            e.printStackTrace();
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "解析数据失败").show();
        } catch (Exception e2) {
            e2.printStackTrace();
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "网络错误，请重试！").show();
        }
    }

    public void onFailure(int i, String str) {
        if (!(this.a instanceof Activity) || !((Activity) this.a).isFinishing()) {
            if (i == 113) {
                new Builder(this.a).setMessage(str).setNegativeButton("管理我的群", new ba(this)).setPositiveButton("我知道了", new az(this)).show();
            } else if (i == 40006) {
                AlertDialog create = new Builder(this.a).setTitle(str).setPositiveButton("绑定手机", new bb(this)).setNegativeButton("取消", null).create();
                create.setCanceledOnTouchOutside(true);
                create.show();
            } else if (i <= 0 || this.e < 0) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str).show();
            } else {
                new Builder(this.a).setMessage(str).setNegativeButton("换一个群", new bc(this)).setPositiveButton("我知道了", null).show();
            }
        }
    }
}
