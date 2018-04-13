package qsbk.app.im;

class jn implements Runnable {
    final /* synthetic */ VoiceHelper a;

    jn(VoiceHelper voiceHelper) {
        this.a = voiceHelper;
    }

    public void run() {
        if (this.a.o) {
            this.a.n = true;
            this.a.j = this.a.j + 1;
            if (20 - this.a.j <= 0) {
                this.a.n = false;
                this.a.stopRecord();
            }
            if (this.a.n) {
                this.a.f.postDelayed(this, 1000);
            }
        }
    }
}
