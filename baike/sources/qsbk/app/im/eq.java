package qsbk.app.im;

class eq implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ GroupConversationActivity d;

    eq(GroupConversationActivity groupConversationActivity, String str, int i, int i2) {
        this.d = groupConversationActivity;
        this.a = str;
        this.b = i;
        this.c = i2;
    }

    public void run() {
        if (this.a.equals(String.valueOf(this.b))) {
            switch (this.c) {
                case 1:
                    this.d.setTitle(this.d.f());
                    return;
                case 2:
                    this.d.c("您已被踢出群!");
                    return;
                case 3:
                    this.d.finish();
                    return;
                default:
                    return;
            }
        }
    }
}
