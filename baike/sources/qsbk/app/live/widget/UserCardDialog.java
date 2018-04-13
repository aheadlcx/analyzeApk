package qsbk.app.live.widget;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import qsbk.app.core.model.User;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.PreferenceUtils;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.live.R;
import qsbk.app.live.ui.helper.LevelHelper;

public class UserCardDialog extends BaseDialog {
    private ImageView c;
    private ImageView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private FrameLayout i;
    private LinearLayout j;
    private TextView k;
    private LinearLayout l;
    private TextView m;
    private TextView n;
    private FrameLayout o;
    private TextView p;
    private TextView q;
    private TextView r;
    private ProgressBar s;
    private TextView t;
    private FamilyLevelView u;
    private User v;
    private User w;
    private User x;
    private long y;
    private OnUserCardBtnClickListener z;

    public interface OnUserCardBtnClickListener {
        void doMicConnect(User user);

        void doReport(User user);

        void doSilent(User user, int i);
    }

    public UserCardDialog(Context context, User user, User user2, User user3, long j) {
        super(context);
        this.v = user;
        this.w = user2;
        this.x = user3;
        this.y = j;
    }

    protected int c() {
        return R.layout.live_user_info_view;
    }

    protected void d() {
        this.c = (ImageView) a(R.id.iv_close);
        this.d = (ImageView) a(R.id.avatar);
        this.e = (TextView) a(R.id.tv_name);
        this.f = (TextView) a(R.id.tv_remark);
        this.g = (TextView) a(R.id.btn_at);
        this.h = (TextView) a(R.id.btn_follow);
        this.i = (FrameLayout) a(R.id.ll_follow);
        this.j = (LinearLayout) a(R.id.ll_report);
        this.k = (TextView) a(R.id.tv_report);
        this.l = (LinearLayout) a(R.id.ll_silent);
        this.m = (TextView) a(R.id.tv_silent);
        this.n = (TextView) a(R.id.tv_user_lv);
        this.o = (FrameLayout) a(R.id.fl_userpage);
        this.p = (TextView) a(R.id.tv_userpage);
        this.q = (TextView) a(R.id.tv_admin_label);
        this.r = (TextView) a(R.id.tv_admin);
        this.s = (ProgressBar) a(R.id.progress_bar);
        this.t = (TextView) a(R.id.tv_nick_id);
        this.u = (FamilyLevelView) a(R.id.fl_level);
    }

    protected void e() {
        a(this.x, 0);
        c(this.x);
    }

    private void a(User user, int i) {
        int i2;
        AppUtils.getInstance().getImageProvider().loadAvatar(this.d, user.headurl);
        this.e.setText(user.name);
        LevelHelper.setLevelText(this.n, user.level);
        this.t.setText(this.a.getString(R.string.user_nick_id, new Object[]{String.valueOf(user.nick_id)}));
        if (TextUtils.isEmpty(user.intro)) {
            this.f.setVisibility(8);
        } else {
            this.f.setText(user.intro);
            this.f.setVisibility(0);
        }
        if (PreferenceUtils.instance().getString("inspect_mode", "inspect_off").equals("inspect_on")) {
            this.k.setText(R.string.share_superadmin);
        }
        this.c.setOnClickListener(new jf(this));
        this.e.setOnClickListener(new jk(this, user));
        this.d.setOnClickListener(new jl(this, user));
        if (isAnchor(this.w.getOriginId(), this.w.getOrigin())) {
            this.o.setVisibility(user.isMe() ? 8 : 0);
            this.p.setText(R.string.live_mic);
            this.p.setCompoundDrawablesWithIntrinsicBounds(R.drawable.live_mic_drawable_left, 0, 0, 0);
            this.o.setOnClickListener(new jm(this, user));
        } else {
            this.p.setText(R.string.homepage);
            this.p.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            this.o.setOnClickListener(new jn(this, user));
        }
        this.r.setOnClickListener(new jo(this, user));
        this.g.setOnClickListener(new jq(this, user));
        if (user.isFollow()) {
            this.h.setText(R.string.user_has_followed);
            this.h.setTextColor(this.a.getResources().getColor(R.color.color_b0abb7));
            this.h.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            this.i.setOnClickListener(null);
        } else {
            this.h.setText(R.string.user_follow);
            this.h.setTextColor(this.a.getResources().getColor(R.color.black_41364F));
            this.h.setCompoundDrawablesWithIntrinsicBounds(R.drawable.live_user_follow, 0, 0, 0);
            this.i.setOnClickListener(new jr(this, user));
        }
        TextView textView = this.q;
        if (user.isAdmin()) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        textView.setVisibility(i2);
        if (user.isMe()) {
            this.j.setVisibility(8);
            this.l.setVisibility(8);
            this.r.setVisibility(8);
        } else {
            this.j.setVisibility(0);
            this.r.setVisibility(isAnchor(this.w.getOriginId(), this.w.getOrigin()) ? 0 : 8);
            this.r.setText(user.isAdmin() ? R.string.admin_cancel : R.string.admin_set);
            if (i == 0 || isAnchor(user.getOriginId(), user.getOrigin())) {
                this.l.setVisibility(8);
            } else {
                this.l.setVisibility(0);
                this.m.setText(i == 1 ? R.string.live_silent : R.string.live_silent_cancel);
            }
            this.j.setOnClickListener(new js(this, user));
            this.l.setOnClickListener(new jg(this, user, i));
        }
        if (TextUtils.isEmpty(user.badge)) {
            this.u.setVisibility(8);
            return;
        }
        this.u.setVisibility(0);
        this.u.setLevelAndName(user.family_level, user.badge);
    }

    private void a(User user) {
        String str;
        user.reverseAdmin();
        this.r.setText(user.isAdmin() ? R.string.admin_cancel : R.string.admin_set);
        NetRequest instance = NetRequest.getInstance();
        if (user.isAdmin()) {
            str = UrlConstants.LIVE_ADMIN_ADD;
        } else {
            str = UrlConstants.LIVE_ADMIN_CANCEL;
        }
        instance.post(str, new jh(this, user), "addOrCancelAdmin", true);
    }

    private void b(User user) {
        if (!isAnchor(this.w.getOriginId(), this.w.getOrigin())) {
            AppUtils.getInstance().getUserInfoProvider().toUserPage((Activity) this.a, user);
        }
    }

    public boolean isAnchor(long j, long j2) {
        return this.v != null && this.v.getOriginId() == j && this.v.getOrigin() == j2;
    }

    private void c(User user) {
        NetRequest.getInstance().get(UrlConstants.LIVE_USER_INFO, new ji(this, user));
    }

    private void a(User user, TextView textView, FrameLayout frameLayout) {
        textView.setText(R.string.user_has_followed);
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        textView.setTextColor(this.a.getResources().getColor(R.color.color_b0abb7));
        NetRequest.getInstance().post(UrlConstants.USER_FOLLOW_NEW, new jj(this, user, frameLayout, textView), "follow", true);
    }

    public void setOnUserCardBtnClickListener(OnUserCardBtnClickListener onUserCardBtnClickListener) {
        this.z = onUserCardBtnClickListener;
    }
}
