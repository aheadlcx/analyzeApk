package cn.xiaochuankeji.tieba.background.assessor;

import cn.xiaochuankeji.tieba.background.net.a.b;
import org.json.JSONObject;

public class a extends cn.xiaochuankeji.tieba.background.net.a {
    public a(b<JSONObject> bVar, cn.xiaochuankeji.tieba.background.net.a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/assessor/get_posts"), a(), null, bVar, aVar);
    }

    public static JSONObject a() {
        return cn.xiaochuankeji.tieba.background.utils.d.a.b();
    }
}
