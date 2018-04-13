package qsbk.app.widget;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.OthersQiuShiActivity;
import qsbk.app.model.UserInfo;

class cx implements OnClickListener {
    final /* synthetic */ UserInfo a;
    final /* synthetic */ PersonalInfoView b;

    cx(PersonalInfoView personalInfoView, UserInfo userInfo) {
        this.b = personalInfoView;
        this.a = userInfo;
    }

    public void onClick(View view) {
        Intent intent = new Intent(PersonalInfoView.b(this.b), OthersQiuShiActivity.class);
        intent.putExtra("user_info", this.a.toString());
        intent.putExtra("uid", this.a.userId);
        PersonalInfoView.b(this.b).startActivity(intent);
    }
}
