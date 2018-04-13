package qsbk.app.activity;

import qsbk.app.core.AsyncTask;

class ai extends AsyncTask<Void, Void, Long> {
    final /* synthetic */ ActionBarUserSettingNavi a;

    ai(ActionBarUserSettingNavi actionBarUserSettingNavi) {
        this.a = actionBarUserSettingNavi;
    }

    protected Long a(Void... voidArr) {
        Long valueOf;
        synchronized (this.a.m) {
            valueOf = Long.valueOf(this.a.c(this.a.m));
        }
        return valueOf;
    }

    protected void a(Long l) {
        if (!isCancelled() && !this.a.isFinishing()) {
            if (l == null) {
                this.a.j.setSubTitle("0M");
                return;
            }
            if (l.longValue() <= 0) {
                this.a.j.setSubTitle("0M");
            } else {
                String str = "0M";
                try {
                    str = String.format("%.2fM", new Object[]{Float.valueOf((((float) l.longValue()) / 1024.0f) / 1024.0f)});
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.a.j.setSubTitle(str);
            }
            super.a(l);
        }
    }
}
