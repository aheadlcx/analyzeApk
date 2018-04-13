package qsbk.app.live.ui.bag;

import android.support.v4.app.NotificationCompat;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveMarketPayRecord;

class aa extends Callback {
    final /* synthetic */ MarketPayDialog a;

    aa(MarketPayDialog marketPayDialog) {
        this.a = marketPayDialog;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("id", this.a.o + "");
        return hashMap;
    }

    public void onSuccess(BaseResponse baseResponse) {
        if (baseResponse.getSimpleDataInt(NotificationCompat.CATEGORY_ERROR) == 0) {
            this.a.p = baseResponse.getListResponse("prices", new ab(this));
            if (baseResponse.getSimpleDataBoolean("c")) {
                this.a.m.setText(R.string.pay_enteranim_renew);
            }
            if (this.a.p != null && this.a.p.size() > 0) {
                this.a.q = new int[this.a.p.size()];
                for (int i = 0; i < this.a.q.length; i++) {
                    this.a.q[i] = ((LiveMarketPayRecord) this.a.p.get(i)).d;
                }
                if (this.a.p.size() == 3) {
                    this.a.e.setText(((LiveMarketPayRecord) this.a.p.get(2)).de);
                    if (((LiveMarketPayRecord) this.a.p.get(2)).p > ((LiveMarketPayRecord) this.a.p.get(2)).n) {
                        this.a.h.setVisibility(0);
                        this.a.h.setText(new DecimalFormat("#.#").format((((double) ((LiveMarketPayRecord) this.a.p.get(2)).n) * 10.0d) / ((double) ((LiveMarketPayRecord) this.a.p.get(2)).p)) + "折");
                    }
                }
                if (this.a.p.size() >= 2) {
                    this.a.d.setText(((LiveMarketPayRecord) this.a.p.get(1)).de);
                    if (((LiveMarketPayRecord) this.a.p.get(1)).p > ((LiveMarketPayRecord) this.a.p.get(1)).n) {
                        this.a.g.setVisibility(0);
                        this.a.g.setText(new DecimalFormat("#.#").format((((double) ((LiveMarketPayRecord) this.a.p.get(1)).n) * 10.0d) / ((double) ((LiveMarketPayRecord) this.a.p.get(1)).p)) + "折");
                    }
                }
                if (this.a.p.size() >= 1) {
                    this.a.c.setText(((LiveMarketPayRecord) this.a.p.get(0)).de);
                    if (((LiveMarketPayRecord) this.a.p.get(0)).p > ((LiveMarketPayRecord) this.a.p.get(0)).n) {
                        this.a.f.setVisibility(0);
                        this.a.f.setText(new DecimalFormat("#.#").format((((double) ((LiveMarketPayRecord) this.a.p.get(0)).n) * 10.0d) / ((double) ((LiveMarketPayRecord) this.a.p.get(0)).p)) + "折");
                    }
                }
                switch (this.a.p.size()) {
                    case 1:
                        this.a.findViewById(R.id.fl_item2).setVisibility(8);
                        this.a.findViewById(R.id.fl_item3).setVisibility(8);
                        break;
                    case 2:
                        this.a.findViewById(R.id.fl_item3).setVisibility(8);
                        break;
                }
                this.a.b(0);
            }
        }
    }

    public void onFinished() {
        this.a.n.setRefreshing(false);
    }
}
