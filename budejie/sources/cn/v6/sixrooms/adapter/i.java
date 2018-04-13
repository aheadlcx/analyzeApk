package cn.v6.sixrooms.adapter;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import cn.v6.sixrooms.bean.SubLiveListBean;
import cn.v6.sixrooms.utils.LoginUtils;

final class i implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ SongMenuAdapter b;

    i(SongMenuAdapter songMenuAdapter, int i) {
        this.b = songMenuAdapter;
        this.a = i;
    }

    public final void onClick(View view) {
        if (LoginUtils.getLoginUserBean() == null) {
            this.b.a.showLoginDialog();
            return;
        }
        String str;
        String str2 = "";
        String str3 = "";
        String str4 = "";
        String str5 = "";
        if (!TextUtils.isEmpty(((SubLiveListBean) this.b.b.get(this.a)).getMscName())) {
            str2 = ((SubLiveListBean) this.b.b.get(this.a)).getMscName();
        }
        if (!TextUtils.isEmpty(((SubLiveListBean) this.b.b.get(this.a)).getMscFirst())) {
            str3 = ((SubLiveListBean) this.b.b.get(this.a)).getMscFirst();
        }
        if (TextUtils.isEmpty(((SubLiveListBean) this.b.b.get(this.a)).getUid())) {
            str = str5;
        } else {
            str = ((SubLiveListBean) this.b.b.get(this.a)).getUid();
        }
        this.b.a.sendSetSong(str2, str3, str4, str);
    }
}
