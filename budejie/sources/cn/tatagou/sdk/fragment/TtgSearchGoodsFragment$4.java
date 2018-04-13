package cn.tatagou.sdk.fragment;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cn.tatagou.sdk.android.TtgConfig;
import cn.tatagou.sdk.android.TtgInterface;
import cn.tatagou.sdk.pojo.HotSearch;

class TtgSearchGoodsFragment$4 implements OnClickListener {
    final /* synthetic */ HotSearch a;
    final /* synthetic */ TtgSearchGoodsFragment b;

    TtgSearchGoodsFragment$4(TtgSearchGoodsFragment ttgSearchGoodsFragment, HotSearch hotSearch) {
        this.b = ttgSearchGoodsFragment;
        this.a = hotSearch;
    }

    public void onClick(View view) {
        this.b.mEdtSearch.setText(((TextView) view).getText());
        this.b.mEdtSearch.setSelection(((TextView) view).getText().length());
        if (TextUtils.isEmpty(this.a.getUrl())) {
            this.b.onTitleBarRightIconClick();
        } else {
            TtgInterface.openTtgMain(this.b.getActivity(), this.a.getUrl(), TtgConfig.getInstance().getPid());
        }
    }
}
