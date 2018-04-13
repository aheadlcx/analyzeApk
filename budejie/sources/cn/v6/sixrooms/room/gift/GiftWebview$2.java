package cn.v6.sixrooms.room.gift;

class GiftWebview$2 implements Runnable {
    final /* synthetic */ GiftWebview this$0;

    GiftWebview$2(GiftWebview giftWebview) {
        this.this$0 = giftWebview;
    }

    public void run() {
        GiftWebview.access$908(this.this$0);
        if (GiftWebview.access$900(this.this$0) >= 10) {
            GiftWebview.access$200(this.this$0);
            if (GiftWebview.access$100(this.this$0) != null) {
                GiftWebview.access$100(this.this$0).animTimeout();
                return;
            }
            return;
        }
        GiftWebview.access$800(this.this$0);
    }
}
