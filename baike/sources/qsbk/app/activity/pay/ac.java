package qsbk.app.activity.pay;

class ac implements Runnable {
    final /* synthetic */ ItemUnivasualBuyActivity a;

    ac(ItemUnivasualBuyActivity itemUnivasualBuyActivity) {
        this.a = itemUnivasualBuyActivity;
    }

    public void run() {
        this.a.setResult(-1);
        this.a.finish();
    }
}
