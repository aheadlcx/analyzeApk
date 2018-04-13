package com.baidu.mobads;

import com.baidu.mobads.VideoAdView.VideoDuration;
import com.baidu.mobads.VideoAdView.VideoSize;

public class VideoAdRequest {
    private VideoDuration a;
    private boolean b;
    private VideoSize c;

    public static class Builder {
        private VideoDuration a;
        private boolean b = false;
        private VideoSize c;

        public Builder setVideoDuration(VideoDuration videoDuration) {
            this.a = videoDuration;
            return this;
        }

        public Builder isShowCountdown(boolean z) {
            this.b = z;
            return this;
        }

        public Builder setVideoSize(VideoSize videoSize) {
            this.c = videoSize;
            return this;
        }

        public VideoAdRequest build() {
            return new VideoAdRequest();
        }
    }

    private VideoAdRequest(Builder builder) {
        this.a = builder.a;
        this.b = builder.b;
        this.c = builder.c;
    }
}
