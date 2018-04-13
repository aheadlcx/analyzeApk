package qsbk.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.SpringFestivalDialogActivity.SpringFestivalIntentBuilder;
import qsbk.app.utils.SpringFestivalUtil;

class sy extends BroadcastReceiver {
    final /* synthetic */ MainActivity a;

    sy(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (MainActivity.b(this.a) && SpringFestivalUtil.needRemind()) {
            try {
                JSONObject jSONObject = new JSONObject(intent.getStringExtra("data"));
                String str = "";
                str = "";
                str = "";
                str = "";
                str = "";
                if (jSONObject != null && jSONObject.has("t") && TextUtils.equals(jSONObject.optString("t"), SpringFestivalUtil.HAMMER_QSJX)) {
                    CharSequence optString = jSONObject.optString("sub_title");
                    int optInt = jSONObject.optInt("gold_hammer", 0);
                    int optInt2 = jSONObject.optInt("color_hammer", 0);
                    CharSequence optString2 = jSONObject.optString("title");
                    CharSequence optString3 = jSONObject.optString("content");
                    this.a.startActivity(new SpringFestivalIntentBuilder().title(optString2).subTitle(optString).buttonText(jSONObject.optString("btn_cnt")).content(optString3).goldNum(optInt).colorNum(optInt2).link(jSONObject.optString("btn_url")).build(this.a));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
