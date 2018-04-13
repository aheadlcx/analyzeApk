package io.agora.rtc;

import android.opengl.EGLContext;

public abstract class RtcEngineEx extends RtcEngine {
    public abstract int enableRecap(int i);

    public abstract int enableTransportQualityIndication(boolean z);

    public abstract String getParameters(String str);

    public abstract String makeQualityReportUrl(String str, int i, int i2, int i3);

    public abstract int monitorAudioRouteChange(boolean z);

    public abstract int playRecap();

    public abstract int setProfile(String str, boolean z);

    public abstract int setTextureId(int i, EGLContext eGLContext, int i2, int i3, long j);

    public abstract int setTextureId(int i, javax.microedition.khronos.egl.EGLContext eGLContext, int i2, int i3, long j);

    public abstract int setVideoProfileEx(int i, int i2, int i3, int i4);

    public abstract int updateSharedContext(EGLContext eGLContext);

    public abstract int updateSharedContext(javax.microedition.khronos.egl.EGLContext eGLContext);
}
