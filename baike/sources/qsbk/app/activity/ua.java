package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;

class ua implements OnClickListener {
    final /* synthetic */ MessageInfoReminderActivity a;

    ua(MessageInfoReminderActivity messageInfoReminderActivity) {
        this.a = messageInfoReminderActivity;
    }

    public void onClick(View view) {
        new Builder(this.a).setMessage("将清空所有糗友和群等小纸条聊天记录？").setNegativeButton("确定", new uc(this)).setPositiveButton("取消", new ub(this)).show();
    }
}
