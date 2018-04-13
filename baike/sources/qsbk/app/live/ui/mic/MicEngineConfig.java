package qsbk.app.live.ui.mic;

public class MicEngineConfig {
    public String mChannel;
    public int mClientRole;
    public int mUid;
    public int mVideoProfile;

    public void reset() {
        this.mChannel = null;
    }

    MicEngineConfig() {
    }
}
