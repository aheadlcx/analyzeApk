package qsbk.app.im;

class ft implements Runnable {
    final /* synthetic */ IMChatBaseActivityEx a;

    ft(IMChatBaseActivityEx iMChatBaseActivityEx) {
        this.a = iMChatBaseActivityEx;
    }

    public void run() {
        this.a.d.setSelection(this.a.g.getCount());
    }
}
