package qsbk.app.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.live.LivePullLauncher;
import qsbk.app.utils.UIHelper;

public class FollowLiveFragment extends BaseLiveTabFragment {
    private String e;
    private View f;
    private View g;

    public String getUrl() {
        return String.format(Constants.LIVE_FOLLOW, new Object[]{Integer.valueOf(30), Integer.valueOf(this.a)});
    }

    protected String a() {
        return LivePullLauncher.STSOURCE_followlist;
    }

    public void refresh() {
        if (QsbkApp.currentUser != null) {
            super.refresh();
        }
    }

    public int getLayoutId() {
        return R.layout.activity_circle_follow;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.f = onCreateView.findViewById(R.id.login_layout);
        this.g = onCreateView.findViewById(R.id.id_btn_login);
        ((TextView) onCreateView.findViewById(R.id.tips_text_unlogin)).setText(R.string.nologin_when_open_live);
        this.e = QsbkApp.currentUser == null ? null : QsbkApp.currentUser.userId;
        return onCreateView;
    }

    public void onResume() {
        Object obj;
        super.onResume();
        if (QsbkApp.currentUser == null) {
            obj = null;
        } else {
            obj = QsbkApp.currentUser.userId;
        }
        if (QsbkApp.currentUser == null) {
            this.f.setVisibility(0);
            if (!UIHelper.isNightTheme()) {
                this.f.setBackgroundColor(UIHelper.getColor(R.color.white));
            }
            this.g.setOnClickListener(new cp(this));
            this.d.setVisibility(8);
        } else {
            this.f.setVisibility(8);
            this.d.setVisibility(0);
        }
        if (!TextUtils.equals(this.e, obj) || this.b.size() == 0) {
            this.e = obj;
            onRefresh();
        }
    }

    public void onFollowStateChange(String str, boolean z) {
        if (!z) {
            onRoomClosed(str);
        }
    }

    public void loadLiveRooms() {
        if (QsbkApp.currentUser != null) {
            super.loadLiveRooms();
        }
    }

    public String getDataEmptyTip() {
        if (isAdded()) {
            return getResources().getString(R.string.has_no_followed_anchor);
        }
        return "";
    }
}
