package qsbk.app.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.ListUtil;

class to implements OnItemClickListener {
    final /* synthetic */ MemberManagerActivity a;

    to(MemberManagerActivity memberManagerActivity) {
        this.a = memberManagerActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerCount = ListUtil.getHeaderCount(this.a.g);
        if (i >= headerCount) {
            headerCount = i - headerCount;
            if (this.a.h.getItemViewType(headerCount) == 0) {
                BaseUserInfo baseUserInfo = (BaseUserInfo) this.a.h.getItem(headerCount);
                this.a.a(baseUserInfo.userId, baseUserInfo.userIcon, baseUserInfo.userName);
            }
        }
    }
}
