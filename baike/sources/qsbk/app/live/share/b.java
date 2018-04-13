package qsbk.app.live.share;

class b implements Runnable {
    final /* synthetic */ ImageShareActivity a;

    b(ImageShareActivity imageShareActivity) {
        this.a = imageShareActivity;
    }

    public void run() {
        if (!this.a.isFinishing()) {
            switch (this.a.a) {
                case 0:
                    this.a.shareToWechatTimeline();
                    break;
                case 1:
                    this.a.shareToWechat();
                    break;
                case 2:
                    this.a.shareImageToQQ();
                    break;
                case 3:
                    this.a.shareImageToQQZone();
                    break;
            }
            this.a.finish();
        }
    }
}
