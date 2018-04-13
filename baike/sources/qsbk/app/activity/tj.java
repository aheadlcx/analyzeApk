package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.ListUtil;
import qsbk.app.utils.ToastAndDialog;

class tj implements OnItemClickListener {
    final /* synthetic */ MemberChooseActivity a;

    tj(MemberChooseActivity memberChooseActivity) {
        this.a = memberChooseActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerCount = ListUtil.getHeaderCount(this.a.e);
        if (i >= headerCount) {
            headerCount = i - headerCount;
            if (this.a.f.getItemViewType(headerCount) == 0) {
                BaseUserInfo baseUserInfo = (BaseUserInfo) this.a.f.getItem(headerCount);
                Intent intent = new Intent();
                intent.putExtra("uid", baseUserInfo.userId);
                intent.putExtra("uname", baseUserInfo.userName);
                intent.putExtra("type", 0);
                this.a.setResult(-1, intent);
                this.a.finish();
            } else if (this.a.f.getItemViewType(headerCount) != 3) {
            } else {
                if (this.a.q > 0) {
                    Intent intent2 = new Intent();
                    intent2.putExtra("uid", "all");
                    intent2.putExtra("uname", AtInfo.AT_ALL_NAME);
                    intent2.putExtra("type", 1);
                    this.a.setResult(-1, intent2);
                    this.a.finish();
                    return;
                }
                ToastAndDialog.makeText(this.a, "@全体成员的次数，今日已用完").show();
            }
        }
    }
}
