package qsbk.app.im;

class fy implements Runnable {
    final /* synthetic */ IMChatBaseActivityEx a;

    fy(IMChatBaseActivityEx iMChatBaseActivityEx) {
        this.a = iMChatBaseActivityEx;
    }

    public void run() {
        this.a.d.setSelection(this.a.g.getCount());
    }
}
