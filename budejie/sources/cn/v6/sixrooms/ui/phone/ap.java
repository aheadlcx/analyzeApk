package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.adapter.ExchangeBean6ToCoin6Adapter;
import cn.v6.sixrooms.bean.ExchangeRulesBean;
import cn.v6.sixrooms.engine.GetExchangeBean6ToCoin6RulesEngine.CallBack;

final class ap implements CallBack {
    final /* synthetic */ ExchangeBean6ToCoin6Activity a;

    ap(ExchangeBean6ToCoin6Activity exchangeBean6ToCoin6Activity) {
        this.a = exchangeBean6ToCoin6Activity;
    }

    public final void success(ExchangeRulesBean exchangeRulesBean) {
        this.a.f.setAdapter(new ExchangeBean6ToCoin6Adapter(exchangeRulesBean.getList(), this.a.b));
        this.a.g.setText(exchangeRulesBean.getDesc());
        this.a.showLoadingScreen(false);
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.handleErrorResult(str, str2, this.a.b);
        this.a.showLoadingScreen(false);
    }

    public final void error(int i) {
        this.a.showErrorToast(i);
        this.a.showLoadingScreen(false);
    }
}
