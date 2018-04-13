package qsbk.app.activity;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.TradeRecord;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

class agi implements HttpCallBack {
    final /* synthetic */ WalletTradeListActivity a;

    agi(WalletTradeListActivity walletTradeListActivity) {
        this.a = walletTradeListActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (jSONObject != null) {
            boolean z = jSONObject.optInt("has_more") == 1;
            this.a.g = jSONObject.optString(ParamKey.OFFSET);
            if (this.a.f) {
                this.a.g = "0";
                this.a.h.clear();
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("records");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    try {
                        TradeRecord parse = TradeRecord.parse(optJSONArray.getJSONObject(i));
                        if (!this.a.h.contains(parse)) {
                            this.a.h.add(parse);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            this.a.a.notifyDataSetChanged();
            if (this.a.f) {
                this.a.b.refreshDone();
            } else {
                this.a.b.loadMoreDone(true);
            }
            if (this.a.h.size() == 0) {
                this.a.d.set(UIHelper.getEmptyImg(), "还没有交易记录");
                this.a.d.show();
            } else {
                this.a.d.hide();
            }
            if (!z) {
                this.a.b.loadMoreDone(true);
                this.a.b.setLoadMoreState(0, "已经到底了");
            }
            this.a.f = false;
        }
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeNegativeToast(this.a, str2).show();
        if (this.a.h.size() == 0) {
            this.a.d.set(UIHelper.getEmptyImg(), "还没有交易记录");
            this.a.d.show();
        } else {
            this.a.d.hide();
        }
        if (this.a.f) {
            this.a.b.refreshComplete();
        }
    }
}
