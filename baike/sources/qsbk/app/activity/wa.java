package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.model.FamilyInfo;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.ui.family.FamilyDetailActivity;

class wa implements OnClickListener {
    final /* synthetic */ FamilyInfo a;
    final /* synthetic */ MyInfoActivity b;

    wa(MyInfoActivity myInfoActivity, FamilyInfo familyInfo) {
        this.b = myInfoActivity;
        this.a = familyInfo;
    }

    public void onClick(View view) {
        Intent intent = new Intent(this.b, FamilyDetailActivity.class);
        intent.putExtra("familyName", this.a.getFamilyName());
        intent.putExtra("familyBadge", this.a.getFamilyBadge());
        intent.putExtra("familyHead", AppUtils.getInstance().getUserInfoProvider().getUser());
        intent.putExtra("familyId", this.a.getFamilyId());
        this.b.startActivity(intent);
    }
}
