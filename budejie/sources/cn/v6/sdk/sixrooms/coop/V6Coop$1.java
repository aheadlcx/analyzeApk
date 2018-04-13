package cn.v6.sdk.sixrooms.coop;

class V6Coop$1 implements Runnable {
    final /* synthetic */ V6Coop this$0;

    V6Coop$1(V6Coop v6Coop) {
        this.this$0 = v6Coop;
    }

    public void run() {
        V6Coop.access$002(this.this$0, false);
        if (V6Coop.access$100(this.this$0) != null && V6Coop.access$100(this.this$0).isShowing()) {
            V6Coop.access$100(this.this$0).dismiss();
            V6Coop.access$102(this.this$0, null);
        }
    }
}
