package qsbk.app.activity;

import android.support.v7.app.AlertDialog.Builder;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import org.json.JSONObject;
import qsbk.app.R;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class ee implements HttpCallBack {
    final /* synthetic */ CheckInActivity a;

    ee(CheckInActivity checkInActivity) {
        this.a = checkInActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (!this.a.isFinishing()) {
            int optInt = jSONObject.optInt("count");
            CharSequence charSequence = "确定使用" + this.a.h.size() + "张补签卡";
            CharSequence spannableStringBuilder = new SpannableStringBuilder("补签后，连续签到到达");
            int length = spannableStringBuilder.length();
            spannableStringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + optInt + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            optInt = spannableStringBuilder.length();
            spannableStringBuilder.append("天");
            spannableStringBuilder.setSpan(new ForegroundColorSpan(this.a.getResources().getColor(R.color.yellow)), length, optInt, 33);
            spannableStringBuilder.setSpan(new RelativeSizeSpan(1.4f), length, optInt, 33);
            new Builder(this.a, R.style.MyDialogStyleNormal).setTitle(charSequence).setMessage(spannableStringBuilder).setPositiveButton("确定补签", new eh(this)).setNegativeButton("取消", new eg(this)).setOnCancelListener(new ef(this)).create().show();
        }
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeText(this.a, str2).show();
        if (this.a.J != null && this.a.J.isShowing()) {
            this.a.J.dismiss();
        }
    }
}
