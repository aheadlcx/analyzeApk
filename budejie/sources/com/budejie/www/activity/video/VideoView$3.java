package com.budejie.www.activity.video;

import android.media.MediaPlayer.OnPreparedListener;

class VideoView$3 implements OnPreparedListener {
    final /* synthetic */ VideoView a;

    VideoView$3(VideoView videoView) {
        this.a = videoView;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPrepared(android.media.MediaPlayer r7) {
        /*
        r6 = this;
        r3 = 1;
        r5 = 3;
        r4 = 0;
        r0 = r6.a;
        r0 = com.budejie.www.activity.video.VideoView.d(r0);
        r1 = "onPrepared mCurrentState = STATE_PREPARED";
        android.util.Log.d(r0, r1);
        r0 = r6.a;
        r1 = 2;
        com.budejie.www.activity.video.VideoView.a(r0, r1);
        r0 = r6.a;
        r1 = r6.a;
        r2 = r6.a;
        r2 = com.budejie.www.activity.video.VideoView.c(r2, r3);
        r1 = com.budejie.www.activity.video.VideoView.b(r1, r2);
        com.budejie.www.activity.video.VideoView.a(r0, r1);
        r0 = r6.a;
        r0 = com.budejie.www.activity.video.VideoView.e(r0);
        if (r0 == 0) goto L_0x003c;
    L_0x002d:
        r0 = r6.a;
        r0 = com.budejie.www.activity.video.VideoView.e(r0);
        r1 = r6.a;
        r1 = com.budejie.www.activity.video.VideoView.a(r1);
        r0.onPrepared(r1);
    L_0x003c:
        r0 = r6.a;
        r0 = com.budejie.www.activity.video.VideoView.f(r0);
        if (r0 == 0) goto L_0x004d;
    L_0x0044:
        r0 = r6.a;
        r0 = com.budejie.www.activity.video.VideoView.f(r0);
        r0.setEnabled(r3);
    L_0x004d:
        r0 = r6.a;
        r1 = r7.getVideoWidth();
        com.budejie.www.activity.video.VideoView.c(r0, r1);
        r0 = r6.a;
        r1 = r7.getVideoHeight();
        com.budejie.www.activity.video.VideoView.d(r0, r1);
        r0 = r6.a;
        r0 = com.budejie.www.activity.video.VideoView.g(r0);
        if (r0 == 0) goto L_0x006c;
    L_0x0067:
        r1 = r6.a;
        r1.a(r0);
    L_0x006c:
        r1 = r6.a;
        r1 = com.budejie.www.activity.video.VideoView.b(r1);
        if (r1 == 0) goto L_0x0176;
    L_0x0074:
        r1 = r6.a;
        r1 = com.budejie.www.activity.video.VideoView.c(r1);
        if (r1 == 0) goto L_0x0176;
    L_0x007c:
        r1 = r6.a;
        r1 = r1.getHolder();
        r2 = r6.a;
        r2 = com.budejie.www.activity.video.VideoView.b(r2);
        r3 = r6.a;
        r3 = com.budejie.www.activity.video.VideoView.c(r3);
        r1.setFixedSize(r2, r3);
        r1 = r6.a;
        r1 = com.budejie.www.activity.video.VideoView.h(r1);
        r2 = r6.a;
        r2 = com.budejie.www.activity.video.VideoView.b(r2);
        if (r1 != r2) goto L_0x00ca;
    L_0x009f:
        r1 = r6.a;
        r1 = com.budejie.www.activity.video.VideoView.i(r1);
        r2 = r6.a;
        r2 = com.budejie.www.activity.video.VideoView.c(r2);
        if (r1 != r2) goto L_0x00ca;
    L_0x00ad:
        r1 = r6.a;
        r1 = com.budejie.www.activity.video.VideoView.j(r1);
        if (r1 != r5) goto L_0x015a;
    L_0x00b5:
        r0 = r6.a;
        r0.a();
        r0 = r6.a;
        r0 = com.budejie.www.activity.video.VideoView.f(r0);
        if (r0 == 0) goto L_0x00ca;
    L_0x00c2:
        r0 = r6.a;
        r0 = r0.getWindowToken();
        if (r0 == 0) goto L_0x00ca;
    L_0x00ca:
        r0 = r6.a;
        r0 = com.budejie.www.activity.video.VideoView.k(r0);
        if (r0 == 0) goto L_0x00f9;
    L_0x00d2:
        r0 = r6.a;
        com.budejie.www.activity.video.VideoView.d(r0, r4);
        r0 = r6.a;
        com.budejie.www.activity.video.VideoView.e(r0, r4);
        r0 = r6.a;
        r0 = com.budejie.www.activity.video.VideoView.j(r0);
        if (r0 != r5) goto L_0x00e9;
    L_0x00e4:
        r0 = r6.a;
        r0.a();
    L_0x00e9:
        r0 = r6.a;
        r0 = com.budejie.www.activity.video.VideoView.f(r0);
        if (r0 == 0) goto L_0x00f9;
    L_0x00f1:
        r0 = r6.a;
        r0 = r0.getWindowToken();
        if (r0 == 0) goto L_0x00f9;
    L_0x00f9:
        r0 = r6.a;
        r0 = com.budejie.www.activity.video.VideoView.f(r0);
        if (r0 == 0) goto L_0x0159;
    L_0x0101:
        r0 = r6.a;
        r0 = com.budejie.www.activity.video.VideoView.f(r0);
        r0 = r0.getVideoType();
        r1 = r6.a;
        com.budejie.www.activity.video.VideoView.f(r1);
        if (r0 != r5) goto L_0x0150;
    L_0x0112:
        r0 = r6.a;
        r0 = com.budejie.www.activity.video.VideoView.b(r0);
        r1 = r6.a;
        com.budejie.www.activity.video.VideoView.c(r1);
        r1 = r6.a;
        r1 = com.budejie.www.activity.video.VideoView.b(r1);
        if (r1 == 0) goto L_0x0145;
    L_0x0125:
        r1 = r6.a;
        r1 = com.budejie.www.activity.video.VideoView.c(r1);
        if (r1 == 0) goto L_0x0145;
    L_0x012d:
        r0 = r6.a;
        r0 = com.budejie.www.activity.video.VideoView.l(r0);
        r0 = r0 * 10000;
        r1 = r6.a;
        r1 = com.budejie.www.activity.video.VideoView.c(r1);
        r0 = r0 / r1;
        r1 = r6.a;
        r1 = com.budejie.www.activity.video.VideoView.b(r1);
        r0 = r0 * r1;
        r0 = r0 / 10000;
    L_0x0145:
        r1 = r6.a;
        r2 = r6.a;
        r2 = com.budejie.www.activity.video.VideoView.l(r2);
        com.budejie.www.activity.video.VideoView.a(r1, r0, r2, r4, r4);
    L_0x0150:
        r0 = r6.a;
        r0 = com.budejie.www.activity.video.VideoView.f(r0);
        r0.p();
    L_0x0159:
        return;
    L_0x015a:
        r1 = r6.a;
        r1 = r1.c();
        if (r1 != 0) goto L_0x00ca;
    L_0x0162:
        if (r0 != 0) goto L_0x016c;
    L_0x0164:
        r0 = r6.a;
        r0 = r0.getCurrentPosition();
        if (r0 <= 0) goto L_0x00ca;
    L_0x016c:
        r0 = r6.a;
        r0 = com.budejie.www.activity.video.VideoView.f(r0);
        if (r0 == 0) goto L_0x00ca;
    L_0x0174:
        goto L_0x00ca;
    L_0x0176:
        r0 = r6.a;
        r0 = com.budejie.www.activity.video.VideoView.j(r0);
        if (r0 != r5) goto L_0x00ca;
    L_0x017e:
        r0 = r6.a;
        r0.a();
        goto L_0x00ca;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.activity.video.VideoView$3.onPrepared(android.media.MediaPlayer):void");
    }
}
