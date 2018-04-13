package com.budejie.www.util;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.widget.Toast;
import com.alipay.sdk.cons.b;
import com.alipay.sdk.util.j;
import com.budejie.www.R;
import net.tsz.afinal.a.a;
import org.json.JSONException;
import org.json.JSONObject;

class b$2 extends a<String> {
    final /* synthetic */ int a;
    final /* synthetic */ Context b;
    final /* synthetic */ String c;
    final /* synthetic */ Context d;

    b$2(int i, Context context, String str, Context context2) {
        this.a = i;
        this.b = context;
        this.c = str;
        this.d = context2;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void a(String str) {
        int i;
        String string;
        String str2 = "发送失败";
        aa.a("success", "结果-->" + str);
        String str3 = "";
        str2 = "";
        try {
            JSONObject jSONObject = new JSONObject(str);
            i = jSONObject.getInt(j.c);
            string = jSONObject.getString("result_desc");
            if (jSONObject.has(b.c)) {
                str3 = jSONObject.getString(b.c);
            }
            if (jSONObject.has("landuri")) {
                str2 = jSONObject.getString("landuri");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            i = -1;
            string = "发送失败";
        }
        if (i == 0) {
            b.a().a(this.a, true, R.string.tougao_successed);
        } else {
            b.a().a(this.a, false, string);
            a(this.b, string);
        }
        Message obtainMessage = b.a.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = Integer.valueOf(this.a);
        b.a.sendMessageDelayed(obtainMessage, 2000);
        Intent intent = new Intent("com.budejie.www.draft.action");
        intent.putExtra("result_code", i);
        intent.putExtra("create_time", this.c);
        intent.putExtra("request_id", this.a);
        intent.putExtra(b.c, str3);
        intent.putExtra("landuri", str2);
        this.d.sendStickyBroadcast(intent);
        this.d.sendBroadcast(new Intent("action.send.topic.success"));
    }

    protected void a(Context context, String str) {
        Toast makeText = Toast.makeText(context, str, 1);
        makeText.setGravity(17, 0, 0);
        makeText.show();
    }

    public void onFailure(Throwable th, int i, String str) {
        b.a().a(this.a, false, R.string.tougao_failed);
        Message obtainMessage = b.a.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = Integer.valueOf(this.a);
        b.a.sendMessageDelayed(obtainMessage, 2000);
        a(this.b, this.b.getResources().getString(R.string.tougao_failed));
        Intent intent = new Intent("com.budejie.www.draft.action");
        intent.putExtra("result_code", -1);
        intent.putExtra("create_time", this.c);
        intent.putExtra("request_id", this.a);
        this.d.sendStickyBroadcast(intent);
    }
}
