package com.youth.banner;

class Banner$1 implements Runnable {
    final /* synthetic */ Banner this$0;

    Banner$1(Banner banner) {
        this.this$0 = banner;
    }

    public void run() {
        if (Banner.access$000(this.this$0) > 1 && Banner.access$100(this.this$0)) {
            Banner.access$202(this.this$0, (Banner.access$200(this.this$0) % (Banner.access$000(this.this$0) + 1)) + 1);
            if (Banner.access$200(this.this$0) == 1) {
                Banner.access$300(this.this$0).setCurrentItem(Banner.access$200(this.this$0), false);
                Banner.access$500(this.this$0).post(Banner.access$400(this.this$0));
                return;
            }
            Banner.access$300(this.this$0).setCurrentItem(Banner.access$200(this.this$0));
            Banner.access$500(this.this$0).postDelayed(Banner.access$400(this.this$0), (long) Banner.access$600(this.this$0));
        }
    }
}
