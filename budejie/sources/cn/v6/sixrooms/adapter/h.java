package cn.v6.sixrooms.adapter;

import cn.v6.sixrooms.bean.ShopItemCarBean.Item;
import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class h implements DialogListener {
    final /* synthetic */ Item a;
    final /* synthetic */ String b;
    final /* synthetic */ g c;

    h(g gVar, Item item, String str) {
        this.c = gVar;
        this.a = item;
        this.b = str;
    }

    public final void positive(int i) {
        if (i == 1) {
            this.c.a.b.showLoadingScreen();
            this.c.a.b.buyItem(this.a.getProp(), this.b);
        }
    }

    public final void negative(int i) {
    }
}
