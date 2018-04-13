package qsbk.app.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.im.IMNotifyManager;
import qsbk.app.push.PushMessageManager;
import qsbk.app.push.PushTest;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.NotificationSettingItem;
import qsbk.app.widget.NotificationSettingItem.OnCheckedChange;
import qsbk.app.widget.SettingItem;

public class NotificationSettingActivity extends BaseActionBarActivity implements OnCheckedChange {
    private static final String a = NotificationSettingActivity.class.getSimpleName();
    private NotificationSettingItem b;
    private NotificationSettingItem c;
    private NotificationSettingItem d;
    private NotificationSettingItem e;
    private NotificationSettingItem f;
    private NotificationSettingItem g;
    private NotificationSettingItem h;
    private NotificationSettingItem i;
    private NotificationSettingItem j;
    private NotificationSettingItem k;
    private SettingItem l;
    private NotificationSettingItem m;
    private NotificationSettingItem n;

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

    protected String f() {
        return "消息提醒";
    }

    protected int a() {
        return R.layout.notification_setting;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        g();
        i();
    }

    private void g() {
        this.b = (NotificationSettingItem) findViewById(R.id.qiushi);
        this.c = (NotificationSettingItem) findViewById(R.id.sound);
        this.d = (NotificationSettingItem) findViewById(R.id.vibrate);
        this.e = (NotificationSettingItem) findViewById(R.id.new_msg_detail);
        this.f = (NotificationSettingItem) findViewById(R.id.silent_mode);
        this.g = (NotificationSettingItem) findViewById(R.id.qiushi_notification);
        this.h = (NotificationSettingItem) findViewById(R.id.qiuyou_circle_notification);
        this.i = (NotificationSettingItem) findViewById(R.id.join_group_notification);
        this.j = (NotificationSettingItem) findViewById(R.id.new_fans_notification);
        this.k = (NotificationSettingItem) findViewById(R.id.new_msg);
        this.m = (NotificationSettingItem) findViewById(R.id.qiushi_smile_number_list);
        this.l = (SettingItem) findViewById(R.id.official_number_list);
        this.n = (NotificationSettingItem) findViewById(R.id.topic);
        this.l.setOnClickListener(new yi(this));
        this.b.setOnCheckedChangeListener(this);
        this.c.setOnCheckedChangeListener(this);
        this.d.setOnCheckedChangeListener(this);
        this.e.setOnCheckedChangeListener(this);
        this.f.setOnCheckedChangeListener(this);
        this.g.setOnCheckedChangeListener(this);
        this.h.setOnCheckedChangeListener(this);
        this.i.setOnCheckedChangeListener(this);
        this.j.setOnCheckedChangeListener(this);
        this.k.setOnCheckedChangeListener(this);
        this.m.setOnCheckedChangeListener(this);
        this.n.setOnCheckedChangeListener(this);
    }

    private void i() {
        this.b.setChecked(PushMessageManager.getReceiveMsgFromLocal());
        this.c.setChecked(IMNotifyManager.isNewMsgSound(this));
        this.d.setChecked(IMNotifyManager.isNewMsgVibrate(this));
        this.f.setChecked(IMNotifyManager.isSilentMode(this));
        this.e.setChecked(IMNotifyManager.isNewMsgShowDetail(this));
        this.g.setChecked(IMNotifyManager.isShowQiushiNotification(this));
        this.h.setChecked(IMNotifyManager.isShowQiuyouCircleNotificaiton(this));
        this.i.setChecked(IMNotifyManager.isShowJoinGroupNotificaiton(this));
        this.j.setChecked(IMNotifyManager.isNewFansNotify(this));
        this.k.setChecked(IMNotifyManager.isShowNewMsgNotificaiton(this));
        this.m.setChecked(IMNotifyManager.isQiushiSmileNotify(this));
        this.n.setChecked(IMNotifyManager.isShowCircleTopicNotification(this));
        if (!j()) {
            a((LinearLayout) findViewById(R.id.container));
        }
    }

    private void a(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if ("gone".equals(childAt.getTag())) {
                childAt.setVisibility(8);
            } else if (!(!(childAt instanceof ViewGroup) || (childAt instanceof NotificationSettingItem) || (childAt instanceof SettingItem))) {
                a((ViewGroup) childAt);
            }
        }
    }

    private boolean j() {
        return QsbkApp.currentUser != null;
    }

    public void onCheckedChanged(NotificationSettingItem notificationSettingItem, boolean z) {
        switch (notificationSettingItem.getId()) {
            case R.id.sound:
                IMNotifyManager.canNewMsgSound(this, z, true);
                return;
            case R.id.qiushi_notification:
                IMNotifyManager.canShowQiushiNotification(this, z, true);
                return;
            case R.id.qiushi:
                a(z);
                return;
            case R.id.vibrate:
                IMNotifyManager.canNewMsgVibrate(this, z, true);
                return;
            case R.id.new_msg_detail:
                IMNotifyManager.canNewMsgShowDetail(this, z, true);
                return;
            case R.id.qiuyou_circle_notification:
                IMNotifyManager.canShowQiuyouCircleNotification(this, z, true);
                return;
            case R.id.new_msg:
                IMNotifyManager.canShowNewMsgNotification(this, z, true);
                return;
            case R.id.join_group_notification:
                IMNotifyManager.canShowJoinGroupNotification(this, z, true);
                return;
            case R.id.new_fans_notification:
                IMNotifyManager.canNewFansNotify(this, z, true);
                return;
            case R.id.topic:
                IMNotifyManager.canShowCircleTopic(this, z, true);
                return;
            case R.id.qiushi_smile_number_list:
                IMNotifyManager.canShowQiushiSmile(this, z, true);
                return;
            case R.id.silent_mode:
                IMNotifyManager.canSilentMode(this, z, true);
                return;
            default:
                return;
        }
    }

    private void a(boolean z) {
        if (z) {
            PushTest.initMiPush(QsbkApp.mContext);
            return;
        }
        AlertDialog create = new Builder(this).setTitle("温馨提示").setMessage("关闭推送就享受不到便便君每天精选的无节操糗事，你确定关闭吗？").setPositiveButton("关闭", new yk(this)).setNegativeButton("取消", new yj(this)).create();
        create.setCanceledOnTouchOutside(false);
        create.show();
    }

    protected void onStop() {
        super.onStop();
        if (j()) {
            IMNotifyManager.saveSettingToCloud();
        }
        PushMessageManager.updateRecvMsg(this.b.isChecked(), this);
    }
}
