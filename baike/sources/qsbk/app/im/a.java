package qsbk.app.im;

class a implements Runnable {
    final /* synthetic */ ChatClientManager a;

    a(ChatClientManager chatClientManager) {
        this.a = chatClientManager;
    }

    public void run() {
        ChatClientManager.a(this.a, false);
        ChatClientManager.a(this.a, 3);
        ChatClientManager.a(this.a);
    }
}
