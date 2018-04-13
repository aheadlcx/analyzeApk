package qsbk.app.live.ui.bag;

import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.ToastUtil;

class e extends Callback {
    final /* synthetic */ long a;
    final /* synthetic */ BagAdapter b;

    e(BagAdapter bagAdapter, long j) {
        this.b = bagAdapter;
        this.a = j;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("id", this.a + "");
        return hashMap;
    }

    public void onSuccess(BaseResponse baseResponse) {
        ToastUtil.Long("装备成功");
        if (this.b.c != null) {
            this.b.c.onRefresh();
        }
    }
}
