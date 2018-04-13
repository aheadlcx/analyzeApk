package cn.v6.sixrooms.room.gift;

import cn.v6.sixrooms.room.gift.GiftWebview.AndroidJavaScript;

class GiftWebview$AndroidJavaScript$1 implements Runnable {
    final /* synthetic */ AndroidJavaScript this$1;

    GiftWebview$AndroidJavaScript$1(AndroidJavaScript androidJavaScript) {
        this.this$1 = androidJavaScript;
    }

    public void run() {
        if (GiftWebview.access$100(this.this$1.this$0) != null) {
            GiftWebview.access$100(this.this$1.this$0).animComplete();
        }
    }
}
