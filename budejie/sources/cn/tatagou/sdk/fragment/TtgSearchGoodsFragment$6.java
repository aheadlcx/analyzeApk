package cn.tatagou.sdk.fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

class TtgSearchGoodsFragment$6 implements TextWatcher {
    final /* synthetic */ TtgSearchGoodsFragment a;

    TtgSearchGoodsFragment$6(TtgSearchGoodsFragment ttgSearchGoodsFragment) {
        this.a = ttgSearchGoodsFragment;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (TextUtils.isEmpty(charSequence.toString())) {
            TtgSearchGoodsFragment.e(this.a).setVisibility(8);
            TtgSearchGoodsFragment.f(this.a).setVisibility(4);
            TtgSearchGoodsFragment.a(this.a).setVisibility(8);
            TtgSearchGoodsFragment.b(this.a).setVisibility(0);
            return;
        }
        TtgSearchGoodsFragment.f(this.a).setVisibility(0);
    }

    public void afterTextChanged(Editable editable) {
    }
}
