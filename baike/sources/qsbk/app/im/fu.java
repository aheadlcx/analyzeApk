package qsbk.app.im;

class fu implements Runnable {
    final /* synthetic */ IMChatBaseActivityEx a;

    fu(IMChatBaseActivityEx iMChatBaseActivityEx) {
        this.a = iMChatBaseActivityEx;
    }

    public void run() {
        this.a.d.setSelection(this.a.g.getCount());
    }
}
