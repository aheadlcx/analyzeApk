package cn.v6.sixrooms.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.util.Map;

final class a implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ ExchangeBean6ToCoin6Adapter b;

    a(ExchangeBean6ToCoin6Adapter exchangeBean6ToCoin6Adapter, int i) {
        this.b = exchangeBean6ToCoin6Adapter;
        this.a = i;
    }

    public final void onClick(View view) {
        this.b.b.exchange((String) ((Map) this.b.a.get(this.a)).get(IXAdRequestInfo.WIDTH));
    }
}
