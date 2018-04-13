package com.budejie.www.activity.video;

class VideoView$1 extends Thread {
    final /* synthetic */ VideoView a;

    VideoView$1(VideoView videoView) {
        this.a = videoView;
    }

    public void run() {
        if (VideoView.a(this.a) != null) {
            try {
                VideoView.a(this.a).stop();
                VideoView.a(this.a).release();
                VideoView.a(this.a, null);
                VideoView.a(this.a, 0);
                VideoView.b(this.a, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
