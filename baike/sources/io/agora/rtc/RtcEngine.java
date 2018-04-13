package io.agora.rtc;

import android.content.Context;
import android.view.SurfaceView;
import io.agora.rtc.internal.DeviceUtils;
import io.agora.rtc.internal.RtcEngineImpl;
import io.agora.rtc.video.AgoraVideoFrame;
import io.agora.rtc.video.ViEAndroidGLES20;
import io.agora.rtc.video.VideoCanvas;
import io.agora.rtc.video.VideoCompositingLayout;

public abstract class RtcEngine {
    private static RtcEngineImpl mInstance = null;

    public abstract int adjustAudioMixingVolume(int i);

    public abstract int adjustPlaybackSignalVolume(int i);

    public abstract int adjustRecordingSignalVolume(int i);

    public abstract int clearVideoCompositingLayout();

    public abstract int complain(String str, String str2);

    public abstract int configPublisher(PublisherConfiguration publisherConfiguration);

    public abstract int createDataStream(boolean z, boolean z2);

    public abstract int disableAudio();

    public abstract int disableLastmileTest();

    public abstract int disableVideo();

    public abstract int enableAudio();

    public abstract int enableAudioQualityIndication(boolean z);

    public abstract int enableAudioVolumeIndication(int i, int i2);

    public abstract int enableDualStreamMode(boolean z);

    public abstract boolean enableHighPerfWifiMode(boolean z);

    public abstract int enableInEarMonitoring(boolean z);

    public abstract int enableLastmileTest();

    public abstract int enableLocalVideo(boolean z);

    public abstract int enableVideo();

    public abstract int enableWebSdkInteroperability(boolean z);

    public abstract IAudioEffectManager getAudioEffectManager();

    public abstract int getAudioMixingCurrentPosition();

    public abstract int getAudioMixingDuration();

    public abstract String getCallId();

    public abstract long getNativeHandle();

    public abstract String getParameter(String str, String str2);

    public abstract boolean isSpeakerphoneEnabled();

    public abstract boolean isTextureEncodeSupported();

    public abstract int joinChannel(String str, String str2, String str3, int i);

    public abstract int leaveChannel();

    @Deprecated
    public abstract void monitorBluetoothHeadsetEvent(boolean z);

    public abstract void monitorConnectionEvent(boolean z);

    @Deprecated
    public abstract void monitorHeadsetEvent(boolean z);

    public abstract int muteAllRemoteAudioStreams(boolean z);

    public abstract int muteAllRemoteVideoStreams(boolean z);

    public abstract int muteLocalAudioStream(boolean z);

    public abstract int muteLocalVideoStream(boolean z);

    public abstract int muteRemoteAudioStream(int i, boolean z);

    public abstract int muteRemoteVideoStream(int i, boolean z);

    public abstract int pauseAudioMixing();

    public abstract boolean pushExternalVideoFrame(AgoraVideoFrame agoraVideoFrame);

    public abstract int rate(String str, int i, String str2);

    public abstract int refreshRecordingServiceStatus();

    public abstract int registerAudioFrameObserver(IAudioFrameObserver iAudioFrameObserver);

    public abstract int renewChannelKey(String str);

    public abstract int resumeAudioMixing();

    public abstract int sendStreamMessage(int i, byte[] bArr);

    public abstract int setAudioMixingPosition(int i);

    public abstract int setChannelProfile(int i);

    public abstract int setClientRole(int i, String str);

    public abstract int setDefaultAudioRoutetoSpeakerphone(boolean z);

    public abstract int setEnableSpeakerphone(boolean z);

    public abstract int setEncryptionMode(String str);

    public abstract int setEncryptionSecret(String str);

    public abstract void setExternalVideoSource(boolean z, boolean z2, boolean z3);

    public abstract int setHighQualityAudioParameters(boolean z, boolean z2, boolean z3);

    public abstract int setLocalRenderMode(int i);

    public abstract int setLogFile(String str);

    public abstract int setLogFilter(int i);

    public abstract int setMixedAudioFrameParameters(int i, int i2);

    public abstract int setParameters(String str);

    public abstract int setPlaybackAudioFrameParameters(int i, int i2, int i3, int i4);

    @Deprecated
    public abstract void setPreferHeadset(boolean z);

    public abstract int setRecordingAudioFrameParameters(int i, int i2, int i3, int i4);

    public abstract int setRemoteRenderMode(int i, int i2);

    public abstract int setRemoteVideoStreamType(int i, int i2);

    public abstract int setSpeakerphoneVolume(int i);

    public abstract int setVideoCompositingLayout(VideoCompositingLayout videoCompositingLayout);

    public abstract int setVideoProfile(int i, boolean z);

    public abstract int setVideoQualityParameters(boolean z);

    public abstract int setupLocalVideo(VideoCanvas videoCanvas);

    public abstract int setupRemoteVideo(VideoCanvas videoCanvas);

    public abstract int startAudioMixing(String str, boolean z, boolean z2, int i);

    public abstract int startAudioRecording(String str);

    public abstract int startEchoTest();

    public abstract int startPlayingStream(String str);

    public abstract int startPreview();

    public abstract int startRecordingService(String str);

    public abstract int stopAudioMixing();

    public abstract int stopAudioRecording();

    public abstract int stopEchoTest();

    public abstract int stopPlayingStream();

    public abstract int stopPreview();

    public abstract int stopRecordingService(String str);

    public abstract int switchCamera();

    public abstract void switchView(int i, int i2);

    public abstract int useExternalAudioDevice();

    public static synchronized RtcEngine create(Context context, String str, IRtcEngineEventHandler iRtcEngineEventHandler) throws SecurityException {
        RtcEngine rtcEngine;
        synchronized (RtcEngine.class) {
            if (context != null) {
                if (RtcEngineImpl.initializeNativeLibs()) {
                    if (mInstance == null) {
                        mInstance = new RtcEngineImpl(context, str, iRtcEngineEventHandler);
                    } else {
                        mInstance.reinitialize(context, str, iRtcEngineEventHandler);
                    }
                    rtcEngine = mInstance;
                }
            }
            rtcEngine = null;
        }
        return rtcEngine;
    }

    public static synchronized void destroy() {
        synchronized (RtcEngine.class) {
            if (mInstance != null) {
                mInstance.doDestroy();
                mInstance = null;
                System.gc();
            }
        }
    }

    public static String getSdkVersion() {
        if (RtcEngineImpl.initializeNativeLibs()) {
            return RtcEngineImpl.nativeGetSdkVersion();
        }
        return "";
    }

    public static String getMediaEngineVersion() {
        if (RtcEngineImpl.initializeNativeLibs()) {
            return RtcEngineImpl.nativeGetChatEngineVersion();
        }
        return "";
    }

    public static String getErrorDescription(int i) {
        if (RtcEngineImpl.initializeNativeLibs()) {
            return RtcEngineImpl.nativeGetErrorDescription(i);
        }
        return "";
    }

    public static SurfaceView CreateRendererView(Context context) {
        SurfaceView viEAndroidGLES20;
        if (ViEAndroidGLES20.IsSupported(context)) {
            viEAndroidGLES20 = new ViEAndroidGLES20(context);
        } else {
            viEAndroidGLES20 = new SurfaceView(context);
        }
        viEAndroidGLES20.setVisibility(0);
        return viEAndroidGLES20;
    }

    public static int getRecommendedEncoderType() {
        return DeviceUtils.getRecommendedEncoderType();
    }
}
