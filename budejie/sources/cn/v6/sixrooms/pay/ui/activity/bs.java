package cn.v6.sixrooms.pay.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import cn.v6.sixrooms.pay.bean.WrapPaySelect.CommodityItem;
import java.util.List;

final class bs implements OnItemClickListener {
    final /* synthetic */ List a;
    final /* synthetic */ WeixinPayActivity b;

    bs(WeixinPayActivity weixinPayActivity, List list) {
        this.b = weixinPayActivity;
        this.a = list;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.b.i = (CommodityItem) this.a.get(i);
        this.b.h.setText(this.b.i.getContent());
        this.b.e.dismiss();
    }
}
