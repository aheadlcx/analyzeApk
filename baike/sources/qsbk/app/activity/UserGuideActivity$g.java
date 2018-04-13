package qsbk.app.activity;

import android.content.Intent;

class UserGuideActivity$g implements Runnable {
    Intent a;
    final /* synthetic */ UserGuideActivity b;

    private UserGuideActivity$g(UserGuideActivity userGuideActivity) {
        this.b = userGuideActivity;
        this.a = null;
    }

    public void run() {
        this.a = new Intent(this.b, MainActivity.class);
        this.b.startActivity(this.a);
        this.b.finish();
    }

    protected void finalize() throws Throwable {
        this.a = null;
        super.finalize();
    }
}
