package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.bean.ExchangeRulesBean;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

final class y extends VLAsyncHandler<String> {
    final /* synthetic */ GetExchangeBean6ToCoin6RulesEngine a;

    y(GetExchangeBean6ToCoin6RulesEngine getExchangeBean6ToCoin6RulesEngine) {
        this.a = getExchangeBean6ToCoin6RulesEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if ("001".equals(string)) {
                    ExchangeRulesBean exchangeRulesBean = (ExchangeRulesBean) JsonParseUtils.json2Obj(string2, ExchangeRulesBean.class);
                    if (exchangeRulesBean == null) {
                        GetExchangeBean6ToCoin6RulesEngine.a(this.a).error(1007);
                        return;
                    } else {
                        GetExchangeBean6ToCoin6RulesEngine.a(this.a).success(exchangeRulesBean);
                        return;
                    }
                }
                GetExchangeBean6ToCoin6RulesEngine.a(this.a).handleErrorInfo(string, string2);
            } catch (JSONException e) {
                GetExchangeBean6ToCoin6RulesEngine.a(this.a).error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && GetExchangeBean6ToCoin6RulesEngine.a(this.a) != null) {
            GetExchangeBean6ToCoin6RulesEngine.a(this.a).error(1006);
        }
    }
}
