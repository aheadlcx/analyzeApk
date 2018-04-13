package com.zhihu.matisse.internal.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.ProgressBar;
import com.zhihu.matisse.R;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.lib.IjkSmartPlayer2;
import com.zhihu.matisse.internal.ui.widget.AspectRatioFrameLayout2;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnCompletionListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnErrorListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnPreparedListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnVideoSizeChangedListener;

public class PreviewVideoItemFragment extends Fragment implements Callback {
    private static final String ARGS_ITEM = "args_item";
    private static final int MSG_CLOSE_PLAYER = 2;
    private static final int MSG_REFRESH_PLAYBACK_PROGRESS = 1;
    private Item item;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    PreviewVideoItemFragment.this.refreshPlaybackProgress();
                    return;
                case 2:
                    PreviewVideoItemFragment.this.releasePlayer();
                    return;
                default:
                    return;
            }
        }
    };
    private boolean mIsVisibleToUser = false;
    private int mPendingSeekPos = 0;
    private IjkSmartPlayer2 mPlayer;
    private MediaPlayerControl mPlayerControl;
    private SurfaceHolder mSurfaceHolder;
    private boolean mSurfacePrepared = false;
    private SurfaceView mSurfaceView;
    private AspectRatioFrameLayout2 mVideoFrame;
    private int mVideoHeight;
    private View mVideoPlayError;
    private ProgressBar mVideoProgress;
    private int mVideoWidth;

    public static PreviewVideoItemFragment newInstance(Item item) {
        PreviewVideoItemFragment previewVideoItemFragment = new PreviewVideoItemFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGS_ITEM, item);
        previewVideoItemFragment.setArguments(bundle);
        return previewVideoItemFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_preview_video_item, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.item = (Item) getArguments().getParcelable(ARGS_ITEM);
        if (this.item != null) {
            this.mVideoFrame = (AspectRatioFrameLayout2) view.findViewById(R.id.video_frame);
            this.mSurfaceView = (SurfaceView) view.findViewById(R.id.video_surface_view);
            this.mSurfaceHolder = this.mSurfaceView.getHolder();
            this.mSurfaceHolder.addCallback(this);
            this.mVideoPlayError = view.findViewById(R.id.video_play_error);
            this.mVideoProgress = (ProgressBar) view.findViewById(R.id.video_progressbar);
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.mIsVisibleToUser = z;
        if (this.mIsVisibleToUser && this.mSurfacePrepared) {
            startPlayer();
        } else {
            releasePlayer();
        }
    }

    public void onResume() {
        super.onResume();
        if (this.mIsVisibleToUser && this.mSurfacePrepared) {
            startPlayer();
        }
    }

    public void onPause() {
        super.onPause();
        if (this.mPlayerControl != null && this.mPendingSeekPos == 0) {
            this.mPendingSeekPos = this.mPlayerControl.getCurrentPosition();
        }
        releasePlayer();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (this.mSurfaceHolder != null) {
            this.mSurfaceHolder.removeCallback(this);
        }
        this.mSurfaceHolder = surfaceHolder;
        this.mSurfacePrepared = true;
        this.mSurfaceHolder.addCallback(this);
        startPlayer();
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mSurfacePrepared = false;
        this.mSurfaceHolder = null;
        releasePlayer();
    }

    private void startPlayer() {
        if (this.mIsVisibleToUser && this.mSurfacePrepared) {
            releasePlayer();
            preparePlayer();
            this.mVideoProgress.setProgress(0);
            refreshPlaybackProgress();
        }
    }

    private void releasePlayer() {
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        if (this.mPlayer != null) {
            this.mPlayer.release();
            this.mPlayer = null;
            this.mPlayerControl = null;
        }
    }

    private void preparePlayer() {
        if (this.mPlayer == null) {
            this.mPlayer = new IjkSmartPlayer2(getActivity());
            this.mPlayer.setAutoUseAndroidMediaPlayerIfError(false);
            this.mPlayer.setOnPreparedListener(new IMediaPlayer$OnPreparedListener() {
                public void onPrepared(IMediaPlayer iMediaPlayer) {
                    PreviewVideoItemFragment.this.mPendingSeekPos = 0;
                }
            });
            this.mPlayer.setOnCompletionListener(new IMediaPlayer$OnCompletionListener() {
                public void onCompletion(IMediaPlayer iMediaPlayer) {
                    if (PreviewVideoItemFragment.this.mPlayer != null) {
                        PreviewVideoItemFragment.this.mPlayer.seekTo(0);
                        PreviewVideoItemFragment.this.mPlayer.start();
                    }
                }
            });
            this.mVideoPlayError.setVisibility(4);
            this.mPlayer.setOnErrorListener(new IMediaPlayer$OnErrorListener() {
                public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                    return PreviewVideoItemFragment.this.handleMediaPlayerError(iMediaPlayer);
                }
            });
            this.mPlayer.setOnVideoSizeChangedListener(new IMediaPlayer$OnVideoSizeChangedListener() {
                public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
                    float f = 1.0f;
                    if (PreviewVideoItemFragment.this.mVideoWidth == 0 || PreviewVideoItemFragment.this.mVideoHeight == 0) {
                        PreviewVideoItemFragment.this.mVideoHeight = i2;
                        PreviewVideoItemFragment.this.mVideoWidth = i;
                        AspectRatioFrameLayout2 access$700 = PreviewVideoItemFragment.this.mVideoFrame;
                        if (i2 != 0) {
                            f = (1.0f * ((float) i)) / ((float) i2);
                        }
                        access$700.setAspectRatio(f);
                    }
                }
            });
            this.mPlayer.setDataSource("file://" + this.item.path);
        }
        this.mPlayerControl = this.mPlayer.getPlayerControl();
        if (this.mPendingSeekPos > 0) {
            this.mPlayer.seekTo(this.mPendingSeekPos);
        }
        this.mPlayer.setDisplay(this.mSurfaceHolder);
        this.mPlayer.start();
    }

    private void refreshPlaybackProgress() {
        if (!(this.mPlayerControl == null || this.mVideoProgress == null)) {
            int currentPosition = this.mPlayerControl.getCurrentPosition();
            this.mVideoProgress.setMax(this.mPlayerControl.getDuration());
            this.mVideoProgress.setProgress(currentPosition);
        }
        this.mHandler.sendEmptyMessageDelayed(1, 1000);
    }

    private boolean handleMediaPlayerError(IMediaPlayer iMediaPlayer) {
        if (this.mPendingSeekPos == 0) {
            this.mPendingSeekPos = (int) iMediaPlayer.getCurrentPosition();
        }
        this.mHandler.sendEmptyMessage(2);
        this.mVideoPlayError.setVisibility(0);
        return true;
    }
}
