package cn.xiaochuankeji.tieba.background.ad;

import cn.xiaochuankeji.tieba.background.net.a;
import cn.xiaochuankeji.tieba.background.net.a.b;
import org.json.JSONObject;

public class PrizeRequest extends a {
    public PrizeRequest(String str, Object obj, b<JSONObject> bVar, a.a aVar) {
        super(str, createParams(), obj, bVar, aVar);
    }

    public static JSONObject createParams() {
        return cn.xiaochuankeji.tieba.background.utils.d.a.b();
    }
}
