package qsbk.app.live.ui.mic;

import android.content.Context;
import io.agora.rtc.IRtcEngineEventHandler;
import java.util.concurrent.ConcurrentHashMap;

public class MicEngineEventHandler {
    final IRtcEngineEventHandler a = new b(this);
    private final MicEngineConfig b;
    private final Context c;
    private final ConcurrentHashMap<MicAGEventHandler, Integer> d = new ConcurrentHashMap();

    public MicEngineEventHandler(Context context, MicEngineConfig micEngineConfig) {
        this.c = context;
        this.b = micEngineConfig;
    }

    public void addEventHandler(MicAGEventHandler micAGEventHandler) {
        this.d.put(micAGEventHandler, Integer.valueOf(0));
    }

    public void removeEventHandler(MicAGEventHandler micAGEventHandler) {
        this.d.remove(micAGEventHandler);
    }
}
