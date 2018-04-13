package qsbk.app.im.group.vote.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.im.group.vote.bean.UserAvatarInfo;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.UIHelper;

public class GroupManagerAvatarView extends RelativeLayout {
    private static final String a = GroupManagerAvatarView.class.getSimpleName();
    private Context b;
    private ImageView c;
    private ImageView d;
    private UserAvatarInfo e;

    public GroupManagerAvatarView(Context context) {
        super(context);
        b();
        this.b = context;
    }

    public GroupManagerAvatarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
        b();
        a();
    }

    public GroupManagerAvatarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = context;
        b();
        a();
    }

    private void a() {
        a(LayoutInflater.from(this.b).inflate(R.layout.layout_group_manager_candidate_avatar_item, this));
    }

    private void b() {
    }

    private void a(View view) {
        this.c = (ImageView) view.findViewById(R.id.iv_avatar_bg);
        this.d = (ImageView) view.findViewById(R.id.avatar);
    }

    public void setData(UserAvatarInfo userAvatarInfo) {
        this.e = userAvatarInfo;
    }

    public void setView(int i, int i2, int i3) {
        DebugUtil.debug("luolong", a + ",setData, position=" + i + ",size=" + i2 + ",selectedItemPos=" + i3);
        if (i < i2 - 1) {
            Object absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(this.e.mUserIcon, String.valueOf(this.e.mUserId));
            if (TextUtils.isEmpty(absoluteUrlOfMediumUserIcon)) {
                this.d.setImageResource(UIHelper.getDefaultAvatar());
            } else {
                FrescoImageloader.displayAvatar(this.d, absoluteUrlOfMediumUserIcon);
            }
        } else if (i == i2 - 1) {
            this.d.setImageResource(R.drawable.voting_box);
        }
        if (i3 == i) {
            this.c.setVisibility(0);
        } else {
            this.c.setVisibility(4);
        }
    }
}
