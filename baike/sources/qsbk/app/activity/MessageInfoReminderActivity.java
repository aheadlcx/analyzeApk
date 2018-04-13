package qsbk.app.activity;

import android.os.Bundle;
import android.widget.TextView;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.im.IMNotifyManager;
import qsbk.app.im.datastore.ChatMsgStore;
import qsbk.app.im.datastore.ContactListItemStore;
import qsbk.app.im.datastore.GroupChatMsgStore;
import qsbk.app.im.datastore.GroupNoticeStore;
import qsbk.app.im.datastore.Util;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.NotificationSettingItem;
import qsbk.app.widget.NotificationSettingItem.OnCheckedChange;

public class MessageInfoReminderActivity extends BaseActionBarActivity implements OnCheckedChange {
    private NotificationSettingItem a;
    private TextView b;
    private ChatMsgStore c = null;
    private GroupChatMsgStore d = null;
    private GroupNoticeStore e = null;
    private ContactListItemStore f = null;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Setting_Night);
        } else {
            setTheme(R.style.Setting);
        }
    }

    protected int a() {
        return R.layout.activity_message_info_reminder;
    }

    protected String f() {
        return "小纸条";
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        initWidget();
        initSettingValue();
        initListener();
    }

    public void initWidget() {
        this.a = (NotificationSettingItem) findViewById(R.id.group_tem_note);
        this.b = (TextView) findViewById(R.id.clear_info);
        this.c = ChatMsgStore.getChatMsgStore(QsbkApp.currentUser.userId);
        this.d = GroupChatMsgStore.getInstance(QsbkApp.currentUser.userId);
        this.e = GroupNoticeStore.getInstance(QsbkApp.currentUser.userId);
        this.f = ContactListItemStore.getContactStore(QsbkApp.currentUser.userId);
    }

    public void initSettingValue() {
        if (QsbkApp.currentUser != null) {
            this.a.setChecked(IMNotifyManager.isShowGroupTempNotificaiton(this));
            this.b.setOnClickListener(new ua(this));
        }
    }

    public void clearAllMsgs() {
        Util.imStorageQueue.postRunnable(new ud(this));
    }

    public void initListener() {
        this.a.setOnCheckedChangeListener(this);
    }

    public void onCheckedChanged(NotificationSettingItem notificationSettingItem, boolean z) {
        switch (notificationSettingItem.getId()) {
            case R.id.group_tem_note:
                IMNotifyManager.canShowTemNoteNotification(this, z, true);
                return;
            case R.id.new_msg:
                IMNotifyManager.canShowNewMsgNotification(this, z, true);
                return;
            default:
                return;
        }
    }

    protected void onStop() {
        super.onStop();
        if (g()) {
            IMNotifyManager.saveSettingToCloud();
        }
    }

    private boolean g() {
        return QsbkApp.currentUser != null;
    }
}
