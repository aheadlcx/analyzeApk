package qsbk.app.activity;

class aae implements Runnable {
    final /* synthetic */ QiuYouActivity a;

    aae(QiuYouActivity qiuYouActivity) {
        this.a = qiuYouActivity;
    }

    public void run() {
        this.a.openOptionsMenu();
    }
}
