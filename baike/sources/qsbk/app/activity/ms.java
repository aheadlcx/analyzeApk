package qsbk.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.GroupInfo;

class ms implements OnClickListener {
    final /* synthetic */ GroupInfo a;
    final /* synthetic */ GroupInfoActivity b;

    ms(GroupInfoActivity groupInfoActivity, GroupInfo groupInfo) {
        this.b = groupInfoActivity;
        this.a = groupInfo;
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(this.b, GroupIntroduceActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(ImageViewer.KEY_GROUP_ID, this.a.id);
        bundle.putString("groupName", this.a.name);
        bundle.putString("groupIntro", this.a.description);
        intent.putExtras(bundle);
        this.b.startActivity(intent);
    }
}
