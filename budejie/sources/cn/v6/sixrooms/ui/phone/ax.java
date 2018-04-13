package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.adapter.UsernameListAdapter;
import cn.v6.sixrooms.bean.FindUserNameBean;
import cn.v6.sixrooms.engine.FindUsernameEngine.CallBack;
import java.util.ArrayList;

final class ax implements CallBack {
    final /* synthetic */ FindUsernameActivity a;

    ax(FindUsernameActivity findUsernameActivity) {
        this.a = findUsernameActivity;
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.c.setVisibility(8);
        this.a.a(str2);
    }

    public final void findUsernameSucceed(ArrayList<FindUserNameBean> arrayList) {
        this.a.c.setVisibility(8);
        this.a.b = new UsernameListAdapter(this.a, arrayList);
        this.a.a.setAdapter(this.a.b);
        this.a.b.notifyDataSetChanged();
    }

    public final void error(int i) {
        this.a.c.setVisibility(8);
        FindUsernameActivity.a(this.a, i);
    }
}
