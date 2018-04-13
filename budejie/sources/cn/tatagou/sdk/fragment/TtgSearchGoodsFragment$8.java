package cn.tatagou.sdk.fragment;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

class TtgSearchGoodsFragment$8 implements OnEditorActionListener {
    final /* synthetic */ TtgSearchGoodsFragment a;

    TtgSearchGoodsFragment$8(TtgSearchGoodsFragment ttgSearchGoodsFragment) {
        this.a = ttgSearchGoodsFragment;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 3) {
            return false;
        }
        this.a.onTitleBarRightIconClick();
        return true;
    }
}
