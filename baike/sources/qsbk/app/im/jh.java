package qsbk.app.im;

class jh implements Runnable {
    final /* synthetic */ UserChatManager a;

    jh(UserChatManager userChatManager) {
        this.a = userChatManager;
    }

    public void run() {
        if (this.a.f != null) {
            this.a.f.save();
        }
    }
}
