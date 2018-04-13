package qsbk.app.im;

class fk implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ IMChatBaseActivity b;

    fk(IMChatBaseActivity iMChatBaseActivity, int i) {
        this.b = iMChatBaseActivity;
        this.a = i;
    }

    public void run() {
        if (this.b.A != null) {
            if (this.a > 0) {
                String str = this.a > 99 ? "99+" : this.a + "";
                this.b.A.setLeftText(String.format("消息(%s)", new Object[]{str}));
                return;
            }
            this.b.A.setLeftText("消息");
        }
    }
}
