package qsbk.app.live.ui.mic;

import io.agora.rtc.RtcEngine;
import qsbk.app.core.ui.base.BaseActivity;

public abstract class MicConnectBaseActivity extends BaseActivity {
    private MicWorkerThread a;

    protected synchronized void ar() {
        if (this.a == null) {
            this.a = new MicWorkerThread(getApplicationContext());
            this.a.start();
            this.a.waitForReady();
        }
    }

    protected synchronized void as() {
        if (this.a != null) {
            this.a.exit();
            try {
                this.a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.a = null;
        }
    }

    protected synchronized MicWorkerThread at() {
        return this.a;
    }

    protected RtcEngine au() {
        return at() != null ? at().getRtcEngine() : null;
    }

    protected final MicEngineConfig av() {
        return at().getEngineConfig();
    }

    protected final MicEngineEventHandler aw() {
        return at().eventHandler();
    }
}
