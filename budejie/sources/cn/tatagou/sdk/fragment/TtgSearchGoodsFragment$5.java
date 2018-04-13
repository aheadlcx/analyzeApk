package cn.tatagou.sdk.fragment;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cn.tatagou.sdk.e.a.b;

class TtgSearchGoodsFragment$5 implements OnClickListener {
    final /* synthetic */ TtgSearchGoodsFragment a;

    TtgSearchGoodsFragment$5(TtgSearchGoodsFragment ttgSearchGoodsFragment) {
        this.a = ttgSearchGoodsFragment;
    }

    public void onClick(View view) {
        this.a.mEdtSearch.setText(((TextView) view).getText());
        this.a.mEdtSearch.setSelection(((TextView) view).getText().length());
        b.searchStatEvent(this.a.mEdtSearch.getText().toString().trim(), "R");
        this.a.onSearchGoods();
    }
}
