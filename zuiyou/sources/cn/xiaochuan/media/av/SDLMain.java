package cn.xiaochuan.media.av;

class SDLMain implements Runnable {
    private XPlayer mHost;

    public SDLMain(XPlayer xPlayer) {
        this.mHost = xPlayer;
    }

    public void run() {
        XPlayer.log("SDLMain.run begin serial=" + this.mHost.mSerial);
        int state = this.mHost.getState();
        XPlayer xPlayer = this.mHost;
        if (state == XPlayer.state_none) {
            XPlayer.log("SDLMain.run player is stopped!");
            return;
        }
        this.mHost.nativeStart();
        XPlayer.log("SDLMain.run end serial=" + this.mHost.mSerial);
    }
}
