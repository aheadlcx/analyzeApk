package qsbk.app.im;

class fx implements Runnable {
    final /* synthetic */ IMChatBaseActivityEx a;

    fx(IMChatBaseActivityEx iMChatBaseActivityEx) {
        this.a = iMChatBaseActivityEx;
    }

    public void run() {
        this.a.d.setSelection(this.a.g.getCount());
    }
}
