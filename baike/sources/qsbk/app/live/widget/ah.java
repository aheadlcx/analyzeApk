package qsbk.app.live.widget;

import android.support.v4.app.NotificationCompat;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.live.R;

class ah extends Callback {
    final /* synthetic */ ag a;

    ah(ag agVar) {
        this.a = agVar;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("c", this.a.a.f.getText().toString());
        return hashMap;
    }

    public void onSuccess(BaseResponse baseResponse) {
        int simpleDataInt = baseResponse.getSimpleDataInt("count_left");
        if (!(this.a.a.c == null || this.a.a.c.isFinishing())) {
            this.a.a.c.updateBugleCount(simpleDataInt);
        }
        if (baseResponse.getSimpleDataInt(NotificationCompat.CATEGORY_ERROR) == 0) {
            ToastUtil.Short(R.string.family_bugle_success);
            this.a.a.dismiss();
        }
    }

    public void onFailed(int i, String str) {
        super.onFailed(i, str);
    }
}
