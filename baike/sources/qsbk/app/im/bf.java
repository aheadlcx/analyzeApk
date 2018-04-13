package qsbk.app.im;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.activity.SimpleWebActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.web.ui.WebActivity;
import qsbk.app.model.ShareMsgItem;

class bf implements OnClickListener {
    final /* synthetic */ ChatMsg a;
    final /* synthetic */ ShareMsgItem b;
    final /* synthetic */ ba c;

    bf(ba baVar, ChatMsg chatMsg, ShareMsgItem shareMsgItem) {
        this.c = baVar;
        this.a = chatMsg;
        this.b = shareMsgItem;
    }

    public void onClick(View view) {
        boolean z = true;
        if (this.a.type == 29) {
            Intent intent = new Intent();
            intent.setClass(AppUtils.getInstance().getAppContext(), WebActivity.class);
            intent.putExtra("link", this.b.link);
            intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
            AppUtils.getInstance().getAppContext().startActivity(intent);
            return;
        }
        Context context = view.getContext();
        String str = this.b.link;
        if (this.b.shareFor != 1) {
            z = false;
        }
        SimpleWebActivity.launch(context, str, z);
    }
}
