package cn.v6.sixrooms.room.gift;

import android.text.TextUtils;

class GiftWebview$1 implements Runnable {
    final /* synthetic */ GiftWebview this$0;

    GiftWebview$1(GiftWebview giftWebview) {
        this.this$0 = giftWebview;
    }

    public void run() {
        Object anigift = GiftWebview.access$000(this.this$0).getAnigift();
        String anipic = GiftWebview.access$000(this.this$0).getAnipic();
        String id = GiftWebview.access$000(this.this$0).getId();
        if (!TextUtils.isEmpty(anigift)) {
            this.this$0.loadUrl("http://m.v.6.cn/gift-animation?anigift=" + anigift + "&anipic=" + anipic + "&autoplay=1&scale=0.35&gid=" + id);
        } else if (GiftWebview.access$100(this.this$0) != null) {
            GiftWebview.access$100(this.this$0).animError("礼物参数不完整");
        }
    }
}
