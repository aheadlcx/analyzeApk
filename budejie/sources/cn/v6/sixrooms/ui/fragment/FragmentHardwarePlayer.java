package cn.v6.sixrooms.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.avsolution.common.IPlayer;
import cn.v6.sixrooms.avsolution.common.PlayerCallBack;
import cn.v6.sixrooms.avsolution.common.SixPlayer;
import cn.v6.sixrooms.listener.HomeWatcherReceiver$CallBackHomeKey;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.room.presenter.FrameStatisticsPresenter;

public class FragmentHardwarePlayer extends FragmentBase implements IPlayer, PlayerCallBack, HomeWatcherReceiver$CallBackHomeKey {
    private SixPlayer a;
    private FrameStatisticsPresenter b = new FrameStatisticsPresenter();
    private String c = null;
    private int d = 0;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.a = (SixPlayer) getActivity().getLayoutInflater().inflate(R.layout.fragment_player, viewGroup, false);
        this.a.addCallBack(this.b);
        this.a.addCallBack(this);
        this.a.getHolder().addCallback(new m(this));
        return this.a;
    }

    public void onPause() {
        if (this.d == 1) {
            SixPlayer.closeRender();
            this.d = 2;
        }
        super.onPause();
    }

    public void onStop() {
        super.onStop();
        release();
    }

    public void onDestroy() {
        super.onDestroy();
        this.b.onActivityDestrory();
        this.a.exit();
    }

    public void play(String str) {
        if (TextUtils.isEmpty(str) || !str.equals(this.c)) {
            this.c = str;
            if (this.a == null || this.a.getHolder().getSurface() == null) {
                release();
            } else if (this.a.play(str) < 0) {
                onError(2);
                return;
            }
            this.d = 1;
            return;
        }
        if (!(this.a == null || this.a.getHolder().getSurface() == null)) {
            if (this.d == 2) {
                SixPlayer.openRender(this.a.getHolder().getSurface());
            } else if (this.a.play(str) < 0) {
                onError(2);
                return;
            }
        }
        this.d = 1;
    }

    public void setPlayerParameter(String str, String str2) {
        this.b.setParameter(str, str2);
    }

    public void onResume() {
        super.onResume();
    }

    public void release() {
        this.d = 0;
        this.a.releasePlayer();
    }

    public void onBufferEmpty() {
    }

    public void onBufferLoad() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ((RoomActivity) activity).playerviewPlaying();
        }
    }

    public void onError(int i) {
    }

    public void onVideoEnd() {
    }

    public void onPressHome(int i) {
        release();
    }

    public void onLockScreen() {
        release();
    }

    public void onShowRecentApp() {
    }

    public void onVideoSizeChange(int i, int i2) {
    }
}
