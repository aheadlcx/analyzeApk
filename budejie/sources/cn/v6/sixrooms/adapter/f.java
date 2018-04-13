package cn.v6.sixrooms.adapter;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import cn.v6.sixrooms.bean.PropBean;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

final class f implements OnCheckedChangeListener {
    final /* synthetic */ PropBean a;
    final /* synthetic */ PropListAdapter b;

    f(PropListAdapter propListAdapter, PropBean propBean) {
        this.b = propListAdapter;
        this.a = propBean;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        String str;
        if (z) {
            str = "1";
        } else {
            str = "0";
        }
        PropListAdapter.b(this.b).actionProp(GlobleValue.getUserBean().getId(), SaveUserInfoUtils.getEncpass(PropListAdapter.a(this.b)), "pt", str, this.a.getPropid());
    }
}
