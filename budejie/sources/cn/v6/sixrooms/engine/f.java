package cn.v6.sixrooms.engine;

import android.util.SparseArray;
import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.bean.BillBean;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.JsonParseUtils;
import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class f extends VLAsyncHandler<String> {
    final /* synthetic */ int a;
    final /* synthetic */ BillEngine b;

    f(BillEngine billEngine, int i) {
        this.b = billEngine;
        this.a = i;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            List arrayList = new ArrayList();
            SparseArray sparseArray = new SparseArray();
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                if (string.equals("001")) {
                    jSONObject = jSONObject.getJSONObject("content");
                    JSONArray jSONArray = jSONObject.getJSONArray("data");
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.add((BillBean) JsonParseUtils.json2Obj(jSONArray.getString(i), BillBean.class));
                    }
                    if (this.a != 2) {
                        sparseArray.put(0, jSONObject.getString(AppLinkConstants.TIME));
                    }
                    sparseArray.put(1, jSONObject.getString("page_number"));
                    sparseArray.put(2, jSONObject.getString("page_count"));
                    BillEngine.a(this.b).success(sparseArray, arrayList, this.a);
                    return;
                }
                BillEngine.a(this.b).handleErrorInfo(string, jSONObject.getString("content"));
            } catch (JSONException e) {
                e.printStackTrace();
                BillEngine.a(this.b).error(1006);
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && BillEngine.a(this.b) != null) {
            BillEngine.a(this.b).error(1006);
        }
    }
}
