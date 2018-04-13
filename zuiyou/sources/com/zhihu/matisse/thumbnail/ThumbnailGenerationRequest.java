package com.zhihu.matisse.thumbnail;

class ThumbnailGenerationRequest {
    final boolean isVideo;
    final long orig_id;
    final String orig_path;

    ThumbnailGenerationRequest(long j, String str, boolean z) {
        this.orig_id = j;
        this.orig_path = str;
        this.isVideo = z;
    }
}
