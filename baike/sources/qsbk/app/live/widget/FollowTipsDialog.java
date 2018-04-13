package qsbk.app.live.widget;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import qsbk.app.core.model.User;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.live.R;
import qsbk.app.live.ui.helper.LevelHelper;

public class FollowTipsDialog extends BaseDialog {
    private User c;
    private ImageView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private ProgressBar h;
    private FamilyLevelView i;

    public FollowTipsDialog(Context context, User user) {
        super(context);
        this.c = user;
    }

    protected int c() {
        return R.layout.live_follow_tips_view;
    }

    protected void d() {
        this.d = (ImageView) a(R.id.avatar);
        this.e = (TextView) a(R.id.tv_name);
        this.f = (TextView) a(R.id.tv_user_lv);
        this.g = (TextView) a(R.id.btn_follow);
        this.h = (ProgressBar) a(R.id.progress_bar);
        this.i = (FamilyLevelView) a(R.id.fl_level);
    }

    protected void e() {
        k();
        j();
    }

    protected void onStart() {
        super.onStart();
    }

    private void j() {
        NetRequest.getInstance().get(UrlConstants.LIVE_USER_INFO, new bl(this));
    }

    private void k() {
        AppUtils.getInstance().getImageProvider().loadAvatar(this.d, this.c.headurl);
        this.e.setText(this.c.name);
        LevelHelper.setLevelText(this.f, this.c.level);
        if (TextUtils.isEmpty(this.c.badge)) {
            this.i.setVisibility(8);
        } else {
            this.i.setVisibility(0);
            this.i.setLevelAndName(this.c.family_level, this.c.badge);
        }
        this.g.setOnClickListener(new bm(this));
        this.d.setOnClickListener(new bn(this));
    }
}
