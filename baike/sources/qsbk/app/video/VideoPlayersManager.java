package qsbk.app.video;

import java.lang.ref.WeakReference;

public class VideoPlayersManager {
    private static WeakReference<VideoPlayerView> a;

    public static VideoPlayerView getLast() {
        return a != null ? (VideoPlayerView) a.get() : null;
    }

    public static void onVideoPlayerPlay(VideoPlayerView videoPlayerView) {
        if (a != null) {
            VideoPlayerView videoPlayerView2 = (VideoPlayerView) a.get();
            if (!(videoPlayerView2 == null || videoPlayerView2 == videoPlayerView)) {
                videoPlayerView2.stop();
            }
        }
        a = new WeakReference(videoPlayerView);
    }

    public static void onVideoPlayerPause(VideoPlayerView videoPlayerView, boolean z) {
        if (a != null) {
            VideoPlayerView videoPlayerView2 = (VideoPlayerView) a.get();
            if (videoPlayerView2 != null && videoPlayerView2 == videoPlayerView) {
                a = null;
            }
        }
    }
}
