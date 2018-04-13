package qsbk.app.model;

import android.content.Context;
import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.EvaluateCard.ViewHolder;
import qsbk.app.utils.LogUtil;

final class i implements HttpCallBack {
    final /* synthetic */ Context a;
    final /* synthetic */ ViewHolder b;

    i(Context context, ViewHolder viewHolder) {
        this.a = context;
        this.b = viewHolder;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        LogUtil.d("评价提交成功");
        EvaluateCard.e = -2;
        EvaluateCard.c(this.a);
        this.b.onSubmitted();
    }

    public void onFailure(String str, String str2) {
        onSuccess(null, null);
    }
}
