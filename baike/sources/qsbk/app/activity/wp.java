package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.push.PushMessageShow;
import qsbk.app.utils.UIHelper;

class wp implements OnClickListener {
    final /* synthetic */ MyInfoActivity a;

    wp(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void onClick(View view) {
        String str = "http://static.qiushibaike.com/level.html";
        if (UIHelper.isNightTheme()) {
            str = str + "?ref=night";
        }
        Intent intent = new Intent(this.a, PushMessageShow.class);
        intent.putExtra("id", PushMessageShow.setOpenURL(str));
        intent.putExtra("title", "返回");
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        this.a.startActivity(intent);
    }
}
