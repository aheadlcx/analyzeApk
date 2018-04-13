package cn.tatagou.sdk.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import cn.tatagou.sdk.e.a.b;

class TtgSearchGoodsFragment$7 implements OnItemClickListener {
    final /* synthetic */ TtgSearchGoodsFragment a;

    TtgSearchGoodsFragment$7(TtgSearchGoodsFragment ttgSearchGoodsFragment) {
        this.a = ttgSearchGoodsFragment;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.mEdtSearch.setText((CharSequence) TtgSearchGoodsFragment.g(this.a).get(i));
        this.a.mEdtSearch.setSelection(((String) TtgSearchGoodsFragment.g(this.a).get(i)).length());
        b.searchStatEvent(this.a.mEdtSearch.getText().toString().trim(), "H");
        this.a.onSearchGoods();
    }
}
