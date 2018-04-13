package cn.v6.sixrooms.room.fragment;

import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.ui.phone.input.BaseRoomInputDialog.OnKeyBoardLister;

final class q implements OnKeyBoardLister {
    final /* synthetic */ FullScreenRoomFragment a;

    q(FullScreenRoomFragment fullScreenRoomFragment) {
        this.a = fullScreenRoomFragment;
    }

    public final void OnKeyBoardChange(boolean z, int i) {
        if (this.a.mRoomType != 1) {
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            if (z) {
                if (this.a.mRoomType != 0) {
                    if (FullScreenRoomFragment.U(this.a)) {
                        layoutParams.topMargin = 0;
                        layoutParams.height = 0;
                        layoutParams.width = -1;
                    } else {
                        layoutParams.topMargin = -(i - FullScreenRoomFragment.V(this.a).getHeight());
                        layoutParams.height = (int) this.a.getResources().getDimension(R.dimen.room_top_height);
                        layoutParams.width = -1;
                    }
                    FullScreenRoomFragment.W(this.a).setLayoutParams(layoutParams);
                } else {
                    if (FullScreenRoomFragment.U(this.a)) {
                        layoutParams.topMargin = -(i - FullScreenRoomFragment.V(this.a).getHeight());
                    } else {
                        layoutParams.bottomMargin = i - FullScreenRoomFragment.V(this.a).getHeight();
                    }
                    FullScreenRoomFragment.X(this.a).setLayoutParams(layoutParams);
                }
                FullScreenRoomFragment.f(this.a).isInputShow = true;
                if (!FullScreenRoomFragment.U(this.a)) {
                    FullScreenRoomFragment.V(this.a).setVisibility(4);
                    FullScreenRoomFragment.d(this.a).setVisibility(8);
                    if (this.a.mRoomType == 0) {
                        FullScreenRoomFragment.Y(this.a).setVisibility(4);
                        FullScreenRoomFragment.Z(this.a).setBackgroundResource(R.color.transparent_background);
                    }
                    if (!FullScreenRoomFragment.i(this.a)) {
                        this.a.hideRed();
                    }
                }
                if (this.a.mRoomType == 0 && this.a.mPublicChatPage != null) {
                    this.a.mPublicChatPage.notifyAdapter();
                    return;
                }
                return;
            }
            FullScreenRoomFragment.f(this.a).isInputShow = false;
            layoutParams.topMargin = 0;
            if (this.a.mRoomType != 0) {
                layoutParams.height = (int) this.a.getResources().getDimension(R.dimen.room_top_height);
                layoutParams.width = -1;
                FullScreenRoomFragment.W(this.a).setLayoutParams(layoutParams);
            } else {
                FullScreenRoomFragment.X(this.a).setLayoutParams(layoutParams);
            }
            if (!FullScreenRoomFragment.U(this.a)) {
                FullScreenRoomFragment.V(this.a).setVisibility(0);
                if (!(FullScreenRoomFragment.b(this.a) == null || FullScreenRoomFragment.aa(this.a) || (this.a.mRoomType != 0 && this.a.mRoomType != 4))) {
                    FullScreenRoomFragment.d(this.a).setVisibility(0);
                }
                if (this.a.mRoomType == 0 && !FullScreenRoomFragment.aa(this.a)) {
                    FullScreenRoomFragment.Y(this.a).setVisibility(0);
                    FullScreenRoomFragment.Z(this.a).setBackgroundResource(R.drawable.room_chat_common_backgroud);
                }
                if (!FullScreenRoomFragment.i(this.a)) {
                    this.a.showRed();
                }
            }
            if (this.a.mRoomType == 0 && this.a.mPublicChatPage != null) {
                this.a.mPublicChatPage.notifyAdapter();
            }
        }
    }
}
