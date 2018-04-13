package com.tencent.mm.opensdk.modelbiz;

import android.os.Bundle;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.mm.opensdk.utils.d;

public class WXNontaxPay {

    public static final class Req extends BaseReq {
        public String url;

        public final boolean checkArgs() {
            if (d.a(this.url)) {
                Log.i("MicroMsg.SDK.WXNontaxPay.Req", "url should not be empty");
                return false;
            } else if (this.url.length() <= 10240) {
                return true;
            } else {
                Log.e("MicroMsg.SDK.WXNontaxPay.Req", "url must be in 10k");
                return false;
            }
        }

        public final void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.url = bundle.getString("_wxapi_nontax_pay_req_url");
        }

        public final int getType() {
            return 21;
        }

        public final void toBundle(Bundle bundle) {
            super.fromBundle(bundle);
            bundle.putString("_wxapi_nontax_pay_req_url", this.url);
        }
    }

    public static final class Resp extends BaseResp {
        public String wxOrderId;

        public final boolean checkArgs() {
            return true;
        }

        public final void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.wxOrderId = bundle.getString("_wxapi_nontax_pay_order_id");
        }

        public final int getType() {
            return 21;
        }

        public final void toBundle(Bundle bundle) {
            super.fromBundle(bundle);
            bundle.putString("_wxapi_nontax_pay_order_id", this.wxOrderId);
        }
    }
}
