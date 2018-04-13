package qsbk.app.widget.qiuyoucircle;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.im.TimeUtils;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UserClickDelegate;
import qsbk.app.widget.BaseCell;
import qsbk.app.widget.RoundedDrawable;

public class UnsupportCell extends BaseCell {
    public View actionView;
    public TextView age;
    public ImageView avatarView;
    public ImageView gender;
    public View genderAge;
    public TextView nameView;
    public TextView timeView;
    public ImageView updateBg;
    public ImageView updateBt;

    private static class a extends Handler {
        final WeakReference<Context> a;

        a(Context context) {
            this.a = new WeakReference(context);
        }

        public void handleMessage(Message message) {
            Context context = (Context) this.a.get();
            if (context != null) {
                if (((Boolean) message.obj).booleanValue()) {
                    UnsupportCell.c(context).show();
                } else {
                    ToastAndDialog.makeNeutralToast(context, "没有检测到新版本", Integer.valueOf(1)).show();
                }
            }
        }
    }

    private static Builder c(Context context) {
        Builder builder = new Builder(context);
        View inflate = LayoutInflater.from(context).inflate(R.layout.update_dialog_message, null);
        ((TextView) inflate.findViewById(R.id.updateMessage)).setText(Constants.change);
        builder.setView(inflate);
        builder.setTitle("软件版本更新");
        builder.setPositiveButton("以后再说", new bn()).setNegativeButton("立即下载", new bm(context));
        return builder;
    }

    public void onCreate() {
        OnClickListener boVar;
        setCellView(R.layout.unsupport_cell);
        this.avatarView = (ImageView) findViewById(R.id.avatar);
        displayImage(this.avatarView, null, UIHelper.getDrawable(UIHelper.getDefaultAvatar()));
        this.nameView = (TextView) findViewById(R.id.nickname);
        this.genderAge = findViewById(R.id.gender_age);
        this.gender = (ImageView) findViewById(R.id.gender);
        this.age = (TextView) findViewById(R.id.age);
        this.timeView = (TextView) findViewById(R.id.time);
        this.actionView = findViewById(R.id.action);
        this.updateBg = (ImageView) findViewById(R.id.version_update_bg);
        this.updateBt = (ImageView) findViewById(R.id.version_update_bt);
        this.updateBt.setImageResource(UIHelper.isNightTheme() ? R.drawable.version_update_btn_night : R.drawable.version_update_btn);
        if (QsbkApp.currentUser == null) {
            boVar = new bo(this);
        } else {
            Object loginPermissionClickDelegate = new LoginPermissionClickDelegate(new bp(this), null);
        }
        OnClickListener userClickDelegate = new UserClickDelegate(((CircleArticle) getItem()).user.userId, boVar);
        this.nameView.setOnClickListener(userClickDelegate);
        this.avatarView.setOnClickListener(userClickDelegate);
    }

    public void onUpdate() {
        CircleArticle circleArticle = (CircleArticle) getItem();
        if (circleArticle.user.isAnonymous()) {
            this.avatarView.setImageDrawable(RoundedDrawable.fromDrawable(this.avatarView.getResources().getDrawable(UIHelper.getIconRssAnonymousUser())));
        } else {
            Object absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(circleArticle.user.userIcon, circleArticle.user.userId);
            if (TextUtils.isEmpty(absoluteUrlOfMediumUserIcon)) {
                this.avatarView.setImageResource(UIHelper.getDefaultAvatar());
            } else {
                displayAvatar(this.avatarView, absoluteUrlOfMediumUserIcon);
            }
        }
        CharSequence remark = RemarkManager.getRemark(circleArticle.user.userId);
        TextView textView = this.nameView;
        if (TextUtils.isEmpty(remark)) {
            remark = circleArticle.user.userName;
        }
        textView.setText(remark);
        this.nameView.setTextColor(UIHelper.getNameColor(circleArticle.user.nickStatus));
        this.timeView.setText(TimeUtils.getLastLoginStr(((long) circleArticle.createAt) * 1000));
        this.genderAge.setVisibility(0);
        if (UIHelper.isNightTheme()) {
            this.genderAge.setBackgroundColor(0);
            if ("F".equalsIgnoreCase(circleArticle.user.gender)) {
                this.gender.setImageResource(R.drawable.pinfo_female_dark);
                this.age.setTextColor(getContext().getResources().getColor(R.color.age_female));
            } else if ("M".equalsIgnoreCase(circleArticle.user.gender)) {
                this.gender.setImageResource(R.drawable.pinfo_male_dark);
                this.age.setTextColor(getContext().getResources().getColor(R.color.age_male));
            } else {
                this.genderAge.setVisibility(4);
            }
        } else {
            if ("F".equalsIgnoreCase(circleArticle.user.gender)) {
                this.genderAge.setBackgroundResource(R.drawable.pinfo_female_bg);
                this.gender.setImageResource(R.drawable.pinfo_female);
            } else if ("M".equalsIgnoreCase(circleArticle.user.gender)) {
                this.genderAge.setBackgroundResource(R.drawable.pinfo_man_bg);
                this.gender.setImageResource(R.drawable.pinfo_male);
            } else {
                this.genderAge.setVisibility(4);
            }
            this.age.setTextColor(-1);
        }
        if (circleArticle.user.age <= 0) {
            this.genderAge.setVisibility(4);
        }
        this.updateBg.setOnClickListener(new bq(this));
        this.updateBt.setOnClickListener(new br(this));
    }

    public void onClick() {
    }

    public void checkNewVersion() {
        new bs(this, "qbk-UserSetN2", new a(getContext())).start();
    }
}
