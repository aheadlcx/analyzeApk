package qsbk.app.activity.group;

class a implements Runnable {
    final /* synthetic */ SplashAdActivity a;

    a(SplashAdActivity splashAdActivity) {
        this.a = splashAdActivity;
    }

    public void run() {
        this.a.a.setText(String.format("%ds | 跳过", new Object[]{Integer.valueOf(this.a.d)}));
        if (this.a.d <= 0) {
            this.a.a();
            this.a.finish();
            return;
        }
        this.a.d = this.a.d - 1;
        this.a.a.postDelayed(this, 1000);
    }
}
