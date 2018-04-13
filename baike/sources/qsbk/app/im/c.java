package qsbk.app.im;

class c implements Runnable {
    final /* synthetic */ ChatClientManager a;

    c(ChatClientManager chatClientManager) {
        this.a = chatClientManager;
    }

    public void run() {
        this.a.disconnect();
    }
}
