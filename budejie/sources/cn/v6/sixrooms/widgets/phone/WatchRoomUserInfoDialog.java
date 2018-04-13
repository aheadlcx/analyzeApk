package cn.v6.sixrooms.widgets.phone;

import cn.v6.sixrooms.room.BaseRoomActivity;

public class WatchRoomUserInfoDialog extends UserInfoDialog {
    public WatchRoomUserInfoDialog(BaseRoomActivity baseRoomActivity) {
        super(baseRoomActivity);
    }

    protected void onStart() {
        if (getContext().getResources().getConfiguration().orientation == 2) {
            getWindow().clearFlags(2048);
            getWindow().addFlags(1024);
        } else {
            getWindow().clearFlags(1024);
            getWindow().addFlags(2048);
        }
        super.onStart();
    }
}
