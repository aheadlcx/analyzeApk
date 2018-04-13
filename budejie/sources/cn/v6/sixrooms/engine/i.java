package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import org.json.JSONException;
import org.json.JSONObject;

final class i extends VLAsyncHandler<String> {
    final /* synthetic */ BuyItemInShopEngine a;

    i(BuyItemInShopEngine buyItemInShopEngine) {
        this.a = buyItemInShopEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                String string2 = jSONObject.getString("content");
                if (string.equals("001")) {
                    BuyItemInShopEngine.a(this.a).result(string2);
                } else {
                    BuyItemInShopEngine.a(this.a).handleErrorInfo(string, string2);
                }
            } catch (JSONException e) {
                BuyItemInShopEngine.a(this.a).error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && BuyItemInShopEngine.a(this.a) != null) {
            BuyItemInShopEngine.a(this.a).error(1006);
        }
    }
}
