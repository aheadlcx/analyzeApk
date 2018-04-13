package cn.v6.sixrooms.room.fragment;

import cn.v6.sixrooms.room.dialog.InputSongDialog;
import cn.v6.sixrooms.room.dialog.SongDialog.SongOnClick;
import cn.v6.sixrooms.utils.LoginUtils;

final class y implements SongOnClick {
    final /* synthetic */ FullScreenRoomFragment a;

    y(FullScreenRoomFragment fullScreenRoomFragment) {
        this.a = fullScreenRoomFragment;
    }

    public final void close() {
        if (LoginUtils.getLoginUserBean() == null) {
            this.a.showLoginDialog();
        } else if (FullScreenRoomFragment.e(this.a) != null) {
            FullScreenRoomFragment.e(this.a).dismiss();
            if (FullScreenRoomFragment.B(this.a) == null) {
                FullScreenRoomFragment.a(this.a, new InputSongDialog(FullScreenRoomFragment.f(this.a)));
                FullScreenRoomFragment.B(this.a).setOnDismissListener(new z(this));
            }
            FullScreenRoomFragment.B(this.a).show();
        }
    }

    public final void clickMenuList() {
        if (FullScreenRoomFragment.f(this.a) != null) {
            String id = FullScreenRoomFragment.p(this.a).getRoominfoBean().getId();
            if (id != null) {
                FullScreenRoomFragment.f(this.a).sendSongListMessage(id);
            }
        }
    }

    public final void clickSongList() {
        if (FullScreenRoomFragment.f(this.a) != null) {
            String id = FullScreenRoomFragment.p(this.a).getRoominfoBean().getId();
            if (id != null) {
                FullScreenRoomFragment.f(this.a).sendSongCalledListMessage(id);
            }
        }
    }
}
