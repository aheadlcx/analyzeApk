package io.agora.rtc.internal;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.IntentFilter;
import android.hardware.Camera;
import android.media.AudioManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.os.Build.VERSION;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.TelephonyManager;
import android.view.OrientationEventListener;
import android.view.SurfaceView;
import com.xiaomi.mipush.sdk.Constants;
import io.agora.rtc.IAudioEffectManager;
import io.agora.rtc.IAudioFrameObserver;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.IRtcEngineEventHandler$AudioVolumeInfo;
import io.agora.rtc.IRtcEngineEventHandler$RtcStats;
import io.agora.rtc.IRtcEngineEventHandlerEx;
import io.agora.rtc.PublisherConfiguration;
import io.agora.rtc.RtcEngineEx;
import io.agora.rtc.internal.AudioRoutingController.AudioRoutingListener;
import io.agora.rtc.internal.RtcEngineEvent.EvtType;
import io.agora.rtc.internal.RtcEngineMessage.MediaAppContext;
import io.agora.rtc.internal.RtcEngineMessage.MediaNetworkInfo;
import io.agora.rtc.internal.RtcEngineMessage.MediaResSetupTime;
import io.agora.rtc.internal.RtcEngineMessage.PApiCallExecuted;
import io.agora.rtc.internal.RtcEngineMessage.PError;
import io.agora.rtc.internal.RtcEngineMessage.PFirstLocalAudioFrame;
import io.agora.rtc.internal.RtcEngineMessage.PFirstLocalVideoFrame;
import io.agora.rtc.internal.RtcEngineMessage.PFirstRemoteAudioFrame;
import io.agora.rtc.internal.RtcEngineMessage.PFirstRemoteVideoDecoded;
import io.agora.rtc.internal.RtcEngineMessage.PFirstRemoteVideoFrame;
import io.agora.rtc.internal.RtcEngineMessage.PLocalVideoStat;
import io.agora.rtc.internal.RtcEngineMessage.PMediaEngineEvent;
import io.agora.rtc.internal.RtcEngineMessage.PMediaResAudioQuality;
import io.agora.rtc.internal.RtcEngineMessage.PMediaResJoinMedia;
import io.agora.rtc.internal.RtcEngineMessage.PMediaResLastmileQuality;
import io.agora.rtc.internal.RtcEngineMessage.PMediaResNetworkQuality;
import io.agora.rtc.internal.RtcEngineMessage.PMediaResRtcStats;
import io.agora.rtc.internal.RtcEngineMessage.PMediaResSpeakersReport;
import io.agora.rtc.internal.RtcEngineMessage.PMediaResTransportQuality;
import io.agora.rtc.internal.RtcEngineMessage.PMediaResUserJoinedEvent;
import io.agora.rtc.internal.RtcEngineMessage.PMediaResUserOfflineEvent;
import io.agora.rtc.internal.RtcEngineMessage.PMediaResUserState;
import io.agora.rtc.internal.RtcEngineMessage.PRecordingServiceStatus;
import io.agora.rtc.internal.RtcEngineMessage.PRemoteVideoStat;
import io.agora.rtc.internal.RtcEngineMessage.PStreamMessage;
import io.agora.rtc.internal.RtcEngineMessage.PStreamMessageError;
import io.agora.rtc.internal.RtcEngineMessage.PVideoCompositingLayout;
import io.agora.rtc.internal.RtcEngineMessage.PVideoNetOptions;
import io.agora.rtc.internal.RtcEngineMessage.PVideoSizeChanged;
import io.agora.rtc.video.AgoraVideoFrame;
import io.agora.rtc.video.CameraHelper;
import io.agora.rtc.video.VideoCanvas;
import io.agora.rtc.video.VideoCompositingLayout;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.microedition.khronos.egl.EGLContext;

public class RtcEngineImpl extends RtcEngineEx implements IAudioEffectManager, AudioRoutingListener {
    private static final String TAG = "RtcEngine";
    private static boolean sLibLoaded = false;
    static float[] sMatrix = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private long lastOrientationTs;
    private AudioRoutingController mAudioRoutingController;
    private int mCameraIndex;
    private int mChannelProfile;
    private int mClientRole;
    private PublisherConfiguration mConfiguration;
    private ConnectionChangeBroadcastReceiver mConnectionBroadcastReceiver;
    private WeakReference<Context> mContext;
    private int mCurrentDeviceOrientation;
    private String mDeviceId;
    private IRtcEngineEventHandler mHandler;
    private int mMobileType;
    private boolean mMonitorAudioRouting;
    private long mNativeHandle;
    private OrientationEventListener mOrientationListener;
    private RtcEngineImpl$AgoraPhoneStateListener mPhoneStateLinstner;
    private IRtcEngineEventHandler$RtcStats mRtcStats;
    private int mTotalRotation;
    private boolean mUseExternalVideoSource;
    private boolean mUsingFrontCamera;
    private boolean mVideoEnabled;
    private WifiLock mWifiLock;

    private native int deliverFrame(long j, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j2, int i8);

    private static native int nativeClassInit();

    private native int nativeComplain(long j, String str, String str2);

    private native int nativeCreateDataStream(long j, boolean z, boolean z2);

    private native int nativeDestroy(long j);

    private native String nativeGetCallId(long j);

    public static native String nativeGetChatEngineVersion();

    public static native String nativeGetErrorDescription(int i);

    private native long nativeGetHandle(long j);

    private native int nativeGetIntParameter(long j, String str, String str2);

    private static native byte[] nativeGetOptionsByVideoProfile(int i);

    private native String nativeGetParameter(long j, String str, String str2);

    private native String nativeGetParameters(long j, String str);

    private native String nativeGetProfile(long j);

    public static native String nativeGetSdkVersion();

    private native int nativeJoinChannel(long j, byte[] bArr, String str, String str2, String str3, int i);

    private native int nativeLeaveChannel(long j);

    static native int nativeLog(int i, String str);

    private native String nativeMakeQualityReportUrl(long j, String str, int i, int i2, int i3);

    private native int nativeNotifyNetworkChange(long j, byte[] bArr);

    private native long nativeObjectInit(Object obj, String str, String str2, String str3, String str4, String str5);

    private native int nativeRate(long j, String str, int i, String str2);

    private native int nativeRegisterAudioFrameObserver(long j, Object obj);

    private native int nativeSendStreamMessage(long j, int i, byte[] bArr);

    private native int nativeSetEGL10Context(long j, EGLContext eGLContext);

    private native int nativeSetEGL10TextureId(long j, int i, EGLContext eGLContext, int i2, int i3, int i4, long j2, float[] fArr);

    private native int nativeSetEGL14Context(long j, android.opengl.EGLContext eGLContext);

    private native int nativeSetEGL14TextureId(long j, int i, android.opengl.EGLContext eGLContext, int i2, int i3, int i4, long j2, float[] fArr);

    private native int nativeSetEncryptionSecret(long j, String str);

    private native int nativeSetParameters(long j, String str);

    private native int nativeSetProfile(long j, String str, boolean z);

    private native int nativeSetVideoCompositingLayout(long j, byte[] bArr);

    private native int nativeSetVideoProfileEx(long j, int i, int i2, int i3, int i4);

    private native int nativeSetupVideoLocal(long j, SurfaceView surfaceView, int i);

    private native int nativeSetupVideoRemote(long j, SurfaceView surfaceView, int i, int i2);

    private native int nativeStartEchoTest(long j, byte[] bArr);

    private native int nativeStopEchoTest(long j);

    private native void setExtVideoSource(long j, int i, int i2);

    public void onAudioRoutingChanged(int i) {
        setParameter("che.audio.output.routing", i);
        if (this.mHandler != null) {
            this.mHandler.onAudioRouteChanged(i);
        }
    }

    public void onAudioRoutingError(int i) {
        Logging.i(TAG, "on Audio routing error:" + i);
        if (this.mHandler != null) {
            this.mHandler.onError(i);
        }
    }

    private void checkVoipPermissions(Context context, String str) throws SecurityException {
        if (context == null || context.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(str + " is not granted");
        }
    }

    private void checkVoipPermissions(Context context) throws SecurityException {
        checkVoipPermissions(context, "android.permission.INTERNET");
        checkVoipPermissions(context, "android.permission.RECORD_AUDIO");
        checkVoipPermissions(context, "android.permission.MODIFY_AUDIO_SETTINGS");
        if (this.mVideoEnabled && !this.mUseExternalVideoSource) {
            checkVoipPermissions(context, "android.permission.CAMERA");
        }
    }

    private int checkVoipPermissions(Context context, int i) {
        switch (i) {
            case 1:
                try {
                    checkVoipPermissions(context);
                    break;
                } catch (Throwable e) {
                    Logging.e(TAG, "Do not have enough permission! ", e);
                    return -9;
                }
            case 2:
                try {
                    checkVoipPermissions(context, "android.permission.INTERNET");
                    break;
                } catch (SecurityException e2) {
                    Logging.e(TAG, "Do not have Internet permission!");
                    return -9;
                }
            default:
                return -2;
        }
        return 0;
    }

    public static synchronized boolean initializeNativeLibs() {
        boolean z;
        synchronized (RtcEngineImpl.class) {
            if (!sLibLoaded) {
                System.loadLibrary("agora-rtc-sdk-jni");
                sLibLoaded = nativeClassInit() == 0;
            }
            z = sLibLoaded;
        }
        return z;
    }

    public RtcEngineImpl(Context context, String str, IRtcEngineEventHandler iRtcEngineEventHandler) throws SecurityException {
        this.mVideoEnabled = false;
        this.mCameraIndex = 0;
        this.mUseExternalVideoSource = false;
        this.lastOrientationTs = 0;
        this.mTotalRotation = 1000;
        this.mNativeHandle = 0;
        this.mHandler = null;
        this.mRtcStats = null;
        this.mConnectionBroadcastReceiver = null;
        this.mWifiLock = null;
        this.mPhoneStateLinstner = null;
        this.mMobileType = -1;
        this.mCurrentDeviceOrientation = 0;
        this.mChannelProfile = 0;
        this.mClientRole = 2;
        this.mDeviceId = null;
        this.mMonitorAudioRouting = true;
        this.mVideoEnabled = false;
        this.mContext = new WeakReference(context);
        this.mHandler = iRtcEngineEventHandler;
        this.mCameraIndex = selectFrontCamera();
        String appStorageDir = getAppStorageDir(context);
        String absolutePath = context.getCacheDir().getAbsolutePath();
        String str2 = context.getApplicationInfo().nativeLibraryDir;
        String deviceId = DeviceUtils.getDeviceId();
        Logging.i(TAG, "Initialize Agora Rtc Engine device '" + deviceId + "' dir '" + appStorageDir);
        this.mNativeHandle = nativeObjectInit(context, str, deviceId, appStorageDir, absolutePath, str2);
        this.mAudioRoutingController = new AudioRoutingController(context, this);
        if (this.mAudioRoutingController.initialize() != 0) {
            Logging.e(TAG, "failed to init audio routing controller");
        }
        monitorConnectionEvent(true);
        try {
            this.mPhoneStateLinstner = new RtcEngineImpl$AgoraPhoneStateListener(this, null);
            ((TelephonyManager) context.getSystemService("phone")).listen(this.mPhoneStateLinstner, 288);
        } catch (Throwable e) {
            Logging.e(TAG, "Unable to create PhoneStateListener, ", e);
        }
    }

    public void doDestroy() {
        setExternalVideoSource(false, false, true);
        this.mAudioRoutingController.uninitialize();
        this.mAudioRoutingController = null;
        monitorConnectionEvent(false);
        this.mOrientationListener = null;
        this.mPhoneStateLinstner = null;
        nativeDestroy(this.mNativeHandle);
        this.mNativeHandle = 0;
    }

    public void reinitialize(Context context, String str, IRtcEngineEventHandler iRtcEngineEventHandler) {
        this.mHandler = iRtcEngineEventHandler;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void SetDeviceOrientation(int r13) {
        /*
        r12 = this;
        r11 = 40;
        r10 = 20;
        r1 = 2;
        r2 = 1;
        r4 = java.lang.System.currentTimeMillis();
        r6 = r12.lastOrientationTs;
        r6 = r4 - r6;
        r8 = 100;
        r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r0 >= 0) goto L_0x0015;
    L_0x0014:
        return;
    L_0x0015:
        r6 = (double) r13;
        r8 = 4636033603912859648; // 0x4056800000000000 float:0.0 double:90.0;
        r6 = r6 / r8;
        r6 = java.lang.Math.round(r6);
        r8 = 90;
        r6 = r6 * r8;
        r0 = (int) r6;
        r0 = r0 % 360;
        r3 = 0;
        r6 = r0 - r13;
        r6 = java.lang.Math.abs(r6);
        if (r6 >= r10) goto L_0x0063;
    L_0x002f:
        r3 = r2;
    L_0x0030:
        if (r0 != 0) goto L_0x0071;
    L_0x0032:
        r6 = 180; // 0xb4 float:2.52E-43 double:8.9E-322;
        if (r13 <= r6) goto L_0x0071;
    L_0x0036:
        r6 = 360 - r13;
        if (r6 >= r10) goto L_0x006d;
    L_0x003a:
        r1 = r2;
    L_0x003b:
        if (r1 <= 0) goto L_0x0060;
    L_0x003d:
        r3 = new android.hardware.Camera$CameraInfo;	 Catch:{ Exception -> 0x007c }
        r3.<init>();	 Catch:{ Exception -> 0x007c }
        r6 = r12.mCameraIndex;	 Catch:{ Exception -> 0x007c }
        android.hardware.Camera.getCameraInfo(r6, r3);	 Catch:{ Exception -> 0x007c }
        r3 = r3.orientation;	 Catch:{ Exception -> 0x007c }
        if (r1 != r2) goto L_0x0073;
    L_0x004b:
        r1 = r0;
    L_0x004c:
        r0 = r12.mUsingFrontCamera;	 Catch:{ Exception -> 0x007c }
        if (r0 == 0) goto L_0x0077;
    L_0x0050:
        r0 = 360 - r1;
        r0 = r0 % 360;
        r0 = r0 + r3;
        r0 = r0 % 360;
    L_0x0057:
        r2 = r12.mTotalRotation;	 Catch:{ Exception -> 0x007c }
        if (r0 == r2) goto L_0x005e;
    L_0x005b:
        r12.setVideoRotateCapturedFrames(r0, r1);	 Catch:{ Exception -> 0x007c }
    L_0x005e:
        r12.mTotalRotation = r0;	 Catch:{ Exception -> 0x007c }
    L_0x0060:
        r12.lastOrientationTs = r4;
        goto L_0x0014;
    L_0x0063:
        r6 = r0 - r13;
        r6 = java.lang.Math.abs(r6);
        if (r6 >= r11) goto L_0x0030;
    L_0x006b:
        r3 = r1;
        goto L_0x0030;
    L_0x006d:
        r6 = 360 - r13;
        if (r6 < r11) goto L_0x003b;
    L_0x0071:
        r1 = r3;
        goto L_0x003b;
    L_0x0073:
        r0 = r0 + 5;
        r1 = r0;
        goto L_0x004c;
    L_0x0077:
        r0 = r1 + r3;
        r0 = r0 % 360;
        goto L_0x0057;
    L_0x007c:
        r0 = move-exception;
        r1 = "RtcEngine";
        r2 = "Unable to get camera info, ";
        io.agora.rtc.internal.Logging.e(r1, r2, r0);
        goto L_0x0060;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.agora.rtc.internal.RtcEngineImpl.SetDeviceOrientation(int):void");
    }

    private int setVideoRotateCapturedFrames(int i, int i2) {
        return setParameterObject("che.video.local.rotate_video", formatString("{\"degree\":%d,\"rotation\":%d}", Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public int setupRemoteVideo(VideoCanvas videoCanvas) {
        if (this.mVideoEnabled && videoCanvas != null) {
            return nativeSetupVideoRemote(this.mNativeHandle, videoCanvas.view, videoCanvas.renderMode, videoCanvas.uid);
        }
        return -1;
    }

    public int setupLocalVideo(VideoCanvas videoCanvas) {
        if (!this.mVideoEnabled || this.mUseExternalVideoSource) {
            return -1;
        }
        if (videoCanvas != null) {
            nativeSetupVideoLocal(this.mNativeHandle, videoCanvas.view, videoCanvas.renderMode);
        } else {
            nativeSetupVideoLocal(this.mNativeHandle, null, 1);
        }
        return 0;
    }

    public int setLocalRenderMode(int i) {
        return setRemoteRenderMode(0, i);
    }

    public int setRemoteRenderMode(int i, int i2) {
        return setParameterObject("che.video.render_mode", formatString("{\"uid\":%d,\"mode\":%d}", Long.valueOf(((long) i) & 4294967295L), Integer.valueOf(i2)));
    }

    public int enableDualStreamMode(boolean z) {
        return setParameters(String.format("{\"rtc.dual_stream_mode\":%b,\"che.video.enableLowBitRateStream\":%b}", new Object[]{Boolean.valueOf(z), Boolean.valueOf(z)}));
    }

    private int selectFrontCamera() {
        return Camera.getNumberOfCameras() > 1 ? 1 : 0;
    }

    @Deprecated
    public void monitorHeadsetEvent(boolean z) {
        Logging.i(TAG, "enter monitorHeadsetEvent:" + z);
    }

    @TargetApi(11)
    @Deprecated
    public void monitorBluetoothHeadsetEvent(boolean z) {
        Logging.i(TAG, "enter monitorBluetoothHeadsetEvent:" + z);
    }

    public void monitorConnectionEvent(boolean z) {
        if (!z) {
            this.mConnectionBroadcastReceiver = null;
        } else if (this.mConnectionBroadcastReceiver == null) {
            try {
                this.mConnectionBroadcastReceiver = new ConnectionChangeBroadcastReceiver(this);
            } catch (Throwable e) {
                Logging.e(TAG, "Unable to create ConnectionChangeBroadcastReceiver, ", e);
            }
        }
    }

    public boolean enableHighPerfWifiMode(boolean z) {
        Context context = (Context) this.mContext.get();
        if (context == null) {
            return false;
        }
        if (!z) {
            this.mWifiLock = null;
        } else if (context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") != 0) {
            this.mWifiLock = null;
            return false;
        } else if (this.mWifiLock == null) {
            this.mWifiLock = ((WifiManager) context.getSystemService("wifi")).createWifiLock(3, "agora.voip.lock");
        }
        return true;
    }

    public IRtcEngineEventHandler$RtcStats getRtcStats() {
        if (this.mRtcStats == null) {
            this.mRtcStats = new IRtcEngineEventHandler$RtcStats();
        }
        return this.mRtcStats;
    }

    public synchronized void udpateRtcStats(PMediaResRtcStats pMediaResRtcStats) {
        IRtcEngineEventHandler$RtcStats rtcStats = getRtcStats();
        if (rtcStats != null) {
            rtcStats.totalDuration = pMediaResRtcStats.totalDuration;
            rtcStats.txBytes = pMediaResRtcStats.totalTxBytes;
            rtcStats.rxBytes = pMediaResRtcStats.totalRxBytes;
            rtcStats.txKBitRate = pMediaResRtcStats.txKBitRate;
            rtcStats.rxKBitRate = pMediaResRtcStats.rxKBitRate;
            rtcStats.txAudioKBitRate = pMediaResRtcStats.txAudioKBitRate;
            rtcStats.rxAudioKBitRate = pMediaResRtcStats.rxAudioKBitRate;
            rtcStats.txVideoKBitRate = pMediaResRtcStats.txVideoKBitRate;
            rtcStats.rxVideoKBitRate = pMediaResRtcStats.rxVideoKBitRate;
            rtcStats.users = pMediaResRtcStats.users;
            rtcStats.cpuTotalUsage = ((double) pMediaResRtcStats.cpuTotalUsage) / 100.0d;
            rtcStats.cpuAppUsage = ((double) pMediaResRtcStats.cpuAppUsage) / 100.0d;
        }
    }

    private MediaAppContext createAppContext(Context context) {
        MediaAppContext mediaAppContext = new MediaAppContext();
        mediaAppContext.networkInfo = doGetNetworkInfo(context);
        return mediaAppContext;
    }

    private MediaNetworkInfo doGetNetworkInfo(Context context) {
        if (!checkAccessNetworkState(context)) {
            return null;
        }
        MediaNetworkInfo mediaNetworkInfo = new MediaNetworkInfo();
        String localHost = getLocalHost();
        if (localHost != null) {
            mediaNetworkInfo.localIp4 = localHost;
        }
        NetworkInfo networkInfo = Connectivity.getNetworkInfo(context);
        mediaNetworkInfo.networkType = Connectivity.getNetworkType(networkInfo);
        if (networkInfo != null) {
            mediaNetworkInfo.networkSubtype = networkInfo.getSubtype();
        }
        mediaNetworkInfo.dnsList = Connectivity.getDnsList();
        if (mediaNetworkInfo.networkType == 2) {
            if (checkAccessWifiState(context)) {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
                if (dhcpInfo != null) {
                    InetAddress intToInetAddress = intToInetAddress(dhcpInfo.gateway);
                    if (intToInetAddress != null) {
                        mediaNetworkInfo.gatewayIp4 = intToInetAddress.getHostAddress();
                    }
                }
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    localHost = connectionInfo.getBSSID();
                    mediaNetworkInfo.ssid = connectionInfo.getSSID().replace("\"", "");
                    mediaNetworkInfo.bssid = localHost == null ? "" : localHost.replace("\"", "");
                    mediaNetworkInfo.rssi = connectionInfo.getRssi();
                    mediaNetworkInfo.signalLevel = WifiManager.calculateSignalLevel(mediaNetworkInfo.rssi, 5);
                    if (VERSION.SDK_INT >= 21) {
                        int frequency = connectionInfo.getFrequency();
                        if (frequency >= 5000) {
                            mediaNetworkInfo.networkSubtype = 101;
                        } else if (frequency >= 2400) {
                            mediaNetworkInfo.networkSubtype = 100;
                        }
                    }
                }
            } else {
                mediaNetworkInfo.ssid = "";
                mediaNetworkInfo.bssid = "";
                mediaNetworkInfo.rssi = 0;
                mediaNetworkInfo.signalLevel = 0;
                return mediaNetworkInfo;
            }
        } else if (this.mPhoneStateLinstner != null) {
            mediaNetworkInfo.rssi = this.mPhoneStateLinstner.getRssi();
            mediaNetworkInfo.signalLevel = this.mPhoneStateLinstner.getLevel();
            mediaNetworkInfo.asu = this.mPhoneStateLinstner.getAsuLevel();
        } else if (context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) {
            getSignalStrength(context, mediaNetworkInfo);
        }
        return mediaNetworkInfo;
    }

    @TargetApi(17)
    private boolean getSignalStrength(Context context, MediaNetworkInfo mediaNetworkInfo) {
        if (context == null || VERSION.SDK_INT < 17) {
            this.mMobileType = -1;
            return false;
        }
        List allCellInfo = ((TelephonyManager) context.getSystemService("phone")).getAllCellInfo();
        if (allCellInfo == null || allCellInfo.isEmpty()) {
            return false;
        }
        CellInfo cellInfo = (CellInfo) allCellInfo.get(0);
        if (cellInfo == null) {
            return false;
        }
        try {
            if (this.mMobileType == -1 || this.mMobileType == 0) {
                CellSignalStrengthGsm cellSignalStrength = ((CellInfoGsm) cellInfo).getCellSignalStrength();
                if (cellSignalStrength != null) {
                    this.mMobileType = 0;
                    mediaNetworkInfo.rssi = cellSignalStrength.getDbm();
                    mediaNetworkInfo.signalLevel = cellSignalStrength.getLevel();
                    mediaNetworkInfo.asu = cellSignalStrength.getAsuLevel();
                    return true;
                }
            }
        } catch (Exception e) {
            this.mMobileType = -1;
        }
        try {
            if (this.mMobileType == -1 || this.mMobileType == 1) {
                CellSignalStrengthCdma cellSignalStrength2 = ((CellInfoCdma) cellInfo).getCellSignalStrength();
                if (cellSignalStrength2 != null) {
                    this.mMobileType = 1;
                    mediaNetworkInfo.rssi = cellSignalStrength2.getDbm();
                    mediaNetworkInfo.signalLevel = cellSignalStrength2.getLevel();
                    mediaNetworkInfo.asu = cellSignalStrength2.getAsuLevel();
                    return true;
                }
            }
        } catch (Exception e2) {
            this.mMobileType = -1;
        }
        try {
            if (this.mMobileType == -1 || this.mMobileType == 2) {
                if (VERSION.SDK_INT < 18) {
                    return false;
                }
                CellSignalStrengthWcdma cellSignalStrength3 = ((CellInfoWcdma) cellInfo).getCellSignalStrength();
                if (cellSignalStrength3 != null) {
                    this.mMobileType = 2;
                    mediaNetworkInfo.rssi = cellSignalStrength3.getDbm();
                    mediaNetworkInfo.signalLevel = cellSignalStrength3.getLevel();
                    mediaNetworkInfo.asu = cellSignalStrength3.getAsuLevel();
                    return true;
                }
            }
        } catch (Exception e3) {
            this.mMobileType = -1;
        }
        try {
            if (this.mMobileType == -1 || this.mMobileType == 3) {
                CellSignalStrengthLte cellSignalStrength4 = ((CellInfoLte) cellInfo).getCellSignalStrength();
                if (cellSignalStrength4 != null) {
                    this.mMobileType = 3;
                    mediaNetworkInfo.rssi = cellSignalStrength4.getDbm();
                    mediaNetworkInfo.signalLevel = cellSignalStrength4.getLevel();
                    mediaNetworkInfo.asu = cellSignalStrength4.getAsuLevel();
                    return true;
                }
            }
        } catch (Exception e4) {
            this.mMobileType = -1;
        }
        return false;
    }

    private String getAppStorageDir(Context context) {
        if (context != null) {
            if (context.checkCallingOrSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) {
                return "/sdcard/" + context.getApplicationInfo().packageName;
            }
            Logging.w(TAG, "read external storage is not granted");
        }
        return null;
    }

    protected static String getRandomUUID() {
        return UUID.randomUUID().toString().replace(Constants.ACCEPT_TIME_SEPARATOR_SERVER, "");
    }

    private void doMonitorSystemEvent(Context context, MediaAppContext mediaAppContext) {
        if (context != null) {
            if (this.mConnectionBroadcastReceiver != null) {
                context.registerReceiver(this.mConnectionBroadcastReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            }
            if (mediaAppContext.networkInfo.networkType == 2 && context.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") == 0 && this.mWifiLock != null) {
                this.mWifiLock.acquire();
                Logging.i(TAG, "hp connection mode detected");
            }
        }
    }

    private int doCheckPermission(Context context) {
        int i = 1;
        if (this.mChannelProfile == 1) {
            i = this.mClientRole;
        }
        if (checkVoipPermissions(context, i) == 0) {
            return 0;
        }
        Logging.e(TAG, "can't join channel because no permission");
        return -9;
    }

    public int joinChannel(String str, String str2, String str3, int i) {
        Context context = (Context) this.mContext.get();
        if (context == null) {
            return -7;
        }
        String str4;
        MediaAppContext createAppContext = createAppContext(context);
        doMonitorSystemEvent(context, createAppContext);
        doCheckPermission(context);
        if (this.mVideoEnabled) {
            try {
                if (this.mOrientationListener == null) {
                    this.mOrientationListener = new RtcEngineImpl$1(this, context, 2);
                }
                this.mOrientationListener.enable();
            } catch (Throwable e) {
                Logging.e(TAG, "Unable to create OrientationEventListener, ", e);
            }
            setVideoCamera(this.mCameraIndex);
        }
        if (this.mConfiguration == null || !this.mConfiguration.validate()) {
            str4 = str3;
        } else {
            if (str3 != null) {
                Logging.w(TAG, "override optionalInfo by publisherConfiguration");
            }
            str4 = this.mConfiguration.toJsonString();
        }
        return nativeJoinChannel(this.mNativeHandle, createAppContext.marshall(), str, str2, str4, i);
    }

    private void doStopMonitorSystemEvent(Context context) {
        if (context != null) {
            try {
                if (this.mConnectionBroadcastReceiver != null) {
                    context.unregisterReceiver(this.mConnectionBroadcastReceiver);
                }
            } catch (IllegalArgumentException e) {
            }
        }
        if (this.mOrientationListener != null) {
            this.mOrientationListener.disable();
            this.mOrientationListener = null;
        }
        if (this.mWifiLock != null && this.mWifiLock.isHeld()) {
            this.mWifiLock.release();
            Logging.i(TAG, "hp connection mode ended");
        }
    }

    public int leaveChannel() {
        doStopMonitorSystemEvent((Context) this.mContext.get());
        this.mAudioRoutingController.stopMonitoring();
        return nativeLeaveChannel(this.mNativeHandle);
    }

    public int startEchoTest() {
        Context context = (Context) this.mContext.get();
        if (context == null) {
            return -7;
        }
        MediaAppContext createAppContext = createAppContext(context);
        doMonitorSystemEvent(context, createAppContext);
        return nativeStartEchoTest(this.mNativeHandle, createAppContext.marshall());
    }

    public int stopEchoTest() {
        return nativeStopEchoTest(this.mNativeHandle);
    }

    public int enableLastmileTest() {
        return setParameter("rtc.lastmile_test", true);
    }

    public int disableLastmileTest() {
        return setParameter("rtc.lastmile_test", false);
    }

    public int enableVideo() {
        if (this.mUseExternalVideoSource) {
            this.mVideoEnabled = true;
        } else {
            this.mVideoEnabled = CameraHelper.checkPermission();
        }
        if (this.mAudioRoutingController != null) {
            this.mAudioRoutingController.sendEvent(14, 0);
        }
        if (this.mVideoEnabled) {
            return setParameter("rtc.video.enabled", true);
        }
        return -9;
    }

    public int disableVideo() {
        this.mVideoEnabled = false;
        if (this.mAudioRoutingController != null) {
            this.mAudioRoutingController.sendEvent(14, 1);
        }
        return setParameter("rtc.video.enabled", false);
    }

    public int enableLocalVideo(boolean z) {
        if (!this.mVideoEnabled) {
            return -7;
        }
        return setParameters(String.format("{\"rtc.video.capture\":%b,\"che.video.local.capture\":%b,\"che.video.local.render\":%b,\"che.video.local.send\":%b}", new Object[]{Boolean.valueOf(z), Boolean.valueOf(z), Boolean.valueOf(z), Boolean.valueOf(z)}));
    }

    public int startPreview() {
        if (!this.mVideoEnabled || this.mUseExternalVideoSource) {
            return -4;
        }
        if (setParameter("che.video.local.camera_index", this.mCameraIndex) == 0) {
            return setParameter("rtc.video.preview", true);
        }
        return -7;
    }

    public int stopPreview() {
        return setParameter("rtc.video.preview", false);
    }

    public int enableAudio() {
        return setParameter("che.disable.audio", false);
    }

    public int disableAudio() {
        return setParameter("che.disable.audio", true);
    }

    public int muteLocalAudioStream(boolean z) {
        return setParameters(formatString("{\"rtc.audio.mute_me\":%b, \"che.audio.mute_me\":%b}", Boolean.valueOf(z), Boolean.valueOf(z)));
    }

    public int muteLocalVideoStream(boolean z) {
        boolean z2 = false;
        if (!this.mVideoEnabled) {
            return -7;
        }
        if (this.mAudioRoutingController != null) {
            int i;
            AudioRoutingController audioRoutingController = this.mAudioRoutingController;
            if (z) {
                i = 1;
            } else {
                i = 0;
            }
            audioRoutingController.sendEvent(12, i);
        }
        String str = "{\"rtc.video.mute_me\":%b, \"che.video.local.send\":%b}";
        Object[] objArr = new Object[2];
        objArr[0] = Boolean.valueOf(z);
        if (!z) {
            z2 = true;
        }
        objArr[1] = Boolean.valueOf(z2);
        return setParameters(formatString(str, objArr));
    }

    public int muteAllRemoteAudioStreams(boolean z) {
        return setParameter("rtc.audio.mute_peers", z);
    }

    public int muteAllRemoteVideoStreams(boolean z) {
        if (this.mAudioRoutingController != null) {
            this.mAudioRoutingController.sendEvent(13, z ? 1 : 0);
        }
        return setParameter("rtc.video.mute_peers", z);
    }

    public int muteRemoteAudioStream(int i, boolean z) {
        return setParameters(formatString("{\"rtc.audio.mute_peer\":{\"uid\":%d,\"mute\":%b}}", Long.valueOf(((long) i) & 4294967295L), Boolean.valueOf(z)));
    }

    public int muteRemoteVideoStream(int i, boolean z) {
        return setParameters(formatString("{\"rtc.video.mute_peer\":{\"uid\":%d,\"mute\":%b}}", Long.valueOf(((long) i) & 4294967295L), Boolean.valueOf(z)));
    }

    public int renewChannelKey(String str) {
        if (str == null) {
            return -2;
        }
        return setParameter("rtc.renew_channel_key", str);
    }

    public int setDefaultAudioRoutetoSpeakerphone(boolean z) {
        int i = 1;
        Logging.i(String.format("API call to setDefaultAudioRoutetoSpeakerphone :%b", new Object[]{Boolean.valueOf(z)}));
        if (this.mAudioRoutingController == null) {
            return -7;
        }
        AudioRoutingController audioRoutingController = this.mAudioRoutingController;
        if (z) {
            i = 3;
        }
        audioRoutingController.sendEvent(10, i);
        return 0;
    }

    public int setEnableSpeakerphone(boolean z) {
        int i = 1;
        Logging.i(String.format("API call to setEnableSpeakerphone to %b", new Object[]{Boolean.valueOf(z)}));
        if (this.mAudioRoutingController == null) {
            return -7;
        }
        AudioRoutingController audioRoutingController = this.mAudioRoutingController;
        if (!z) {
            i = 0;
        }
        audioRoutingController.sendEvent(11, i);
        return 0;
    }

    public boolean isSpeakerphoneEnabled() {
        Context context = (Context) this.mContext.get();
        if (context == null) {
            return false;
        }
        return getAudioManager(context).isSpeakerphoneOn();
    }

    public int setSpeakerphoneVolume(int i) {
        return setParameter("che.audio.output.volume", i);
    }

    public int startRecordingService(String str) {
        if (str == null) {
            return -2;
        }
        return setParameter("rtc.api.start_recording_service", str);
    }

    public int stopRecordingService(String str) {
        if (str == null) {
            return -2;
        }
        return setParameter("rtc.api.stop_recording_service", str);
    }

    public int refreshRecordingServiceStatus() {
        return setParameter("rtc.api.query_recording_service_status", true);
    }

    public int adjustRecordingSignalVolume(int i) {
        if (i < 0) {
            i = 0;
        } else if (i > 400) {
            i = 400;
        }
        return setParameter("che.audio.record.signal.volume", i);
    }

    public int adjustPlaybackSignalVolume(int i) {
        if (i < 0) {
            i = 0;
        } else if (i > 400) {
            i = 400;
        }
        return setParameter("che.audio.playout.signal.volume", i);
    }

    public int setRecordingAudioFrameParameters(int i, int i2, int i3, int i4) {
        return setParameterObject("che.audio.set_capture_raw_audio_format", formatString("{\"sampleRate\":%d,\"channelCnt\":%d,\"mode\":%d,\"samplesPerCall\":%d}", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)));
    }

    public int setPlaybackAudioFrameParameters(int i, int i2, int i3, int i4) {
        return setParameterObject("che.audio.set_render_raw_audio_format", formatString("{\"sampleRate\":%d,\"channelCnt\":%d,\"mode\":%d,\"samplesPerCall\":%d}", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)));
    }

    public int setMixedAudioFrameParameters(int i, int i2) {
        return setParameterObject("che.audio.set_mixed_raw_audio_format", formatString("{\"sampleRate\":%d,\"samplesPerCall\":%d}", Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public int setHighQualityAudioParameters(boolean z, boolean z2, boolean z3) {
        return setParameterObject("che.audio.codec.hq", formatString("{\"fullband\":%b,\"stereo\":%b,\"fullBitrate\":%b}", Boolean.valueOf(z), Boolean.valueOf(z2), Boolean.valueOf(z3)));
    }

    public int enableInEarMonitoring(boolean z) {
        return setParameter("che.audio.headset.monitoring", z);
    }

    public int enableWebSdkInteroperability(boolean z) {
        return setParameters(String.format("{\"rtc.video.web_h264_interop_enable\":%b,\"che.video.web_h264_interop_enable\":%b}", new Object[]{Boolean.valueOf(z), Boolean.valueOf(z)}));
    }

    public int setVideoQualityParameters(boolean z) {
        return setParameter("rtc.video.prefer_frame_rate", z);
    }

    @Deprecated
    public void setPreferHeadset(boolean z) {
    }

    private int monitorAudioRouting() {
        if (this.mMonitorAudioRouting) {
            this.mAudioRoutingController.startMonitoring();
        }
        return 0;
    }

    public int enableAudioVolumeIndication(int i, int i2) {
        if (i < 0) {
            i = 0;
        }
        return setParameterObject("che.audio.volume_indication", formatString("{\"interval\":%d,\"smooth\":%d}", Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public int enableAudioQualityIndication(boolean z) {
        return setParameter("rtc.audio_quality_indication", z);
    }

    public int enableTransportQualityIndication(boolean z) {
        return setParameter("rtc.transport_quality_indication", z);
    }

    public int playRecap() {
        return setParameter("che.audio.recap.start_play", true);
    }

    public int enableRecap(int i) {
        if (i < 0) {
            i = 0;
        }
        return setParameter("che.audio.recap.interval", i);
    }

    public int setVideoProfile(int i, boolean z) {
        if (i < 0) {
            return -2;
        }
        return setParameters(formatString("{\"rtc.video.profile\":[%d,%b]}", Integer.valueOf(i), Boolean.valueOf(z)));
    }

    public int setVideoProfileEx(int i, int i2, int i3, int i4) {
        return nativeSetVideoProfileEx(this.mNativeHandle, i, i2, i3, i4);
    }

    public int monitorAudioRouteChange(boolean z) {
        Logging.i("API call monitorAudioRouteChange:" + z);
        this.mMonitorAudioRouting = z;
        return 0;
    }

    public int switchCamera() {
        if (!this.mVideoEnabled || this.mUseExternalVideoSource || Camera.getNumberOfCameras() <= 1) {
            return -1;
        }
        if (this.mCameraIndex == 0) {
            setVideoCamera(1);
        } else {
            setVideoCamera(0);
        }
        return 0;
    }

    public void switchView(int i, int i2) {
        setParameterObject("che.video.switch_view_by_uid", formatString("{\"uid1\":%d, \"uid2\":%d}", Long.valueOf(((long) i) & 4294967295L), Long.valueOf(((long) i2) & 4294967295L)));
    }

    public int setVideoCamera(int i) {
        boolean z = true;
        if (!this.mVideoEnabled || this.mUseExternalVideoSource) {
            return -1;
        }
        this.mCameraIndex = i;
        if (this.mCameraIndex != 1) {
            z = false;
        }
        this.mUsingFrontCamera = z;
        return setParameter("che.video.local.camera_index", i);
    }

    public int enableLocalVideoCapture(boolean z) {
        if (!this.mVideoEnabled || this.mUseExternalVideoSource) {
            return -1;
        }
        return setParameter("che.video.local.capture", z);
    }

    public int enableLocalVideoRender(boolean z) {
        if (!this.mVideoEnabled || this.mUseExternalVideoSource) {
            return -1;
        }
        return setParameter("che.video.local.render", z);
    }

    public int enableLocalVideoSend(boolean z) {
        if (!this.mVideoEnabled) {
            return -1;
        }
        return muteLocalVideoStream(!z);
    }

    public int enableRemoteVideo(boolean z, int i) {
        if (!this.mVideoEnabled) {
            return -1;
        }
        return setParameterObject("che.video.peer.receive", formatString("{\"enable\":%b, \"uid\":%d}", Boolean.valueOf(z), Long.valueOf(((long) i) & 4294967295L)));
    }

    public int stopRemoteVideo(int i) {
        if (this.mVideoEnabled) {
            return setParameter("che.video.peer.stop_video", ((long) i) & 4294967295L);
        }
        return -1;
    }

    private static String formatString(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    public int stopAllRemoteVideo() {
        if (this.mVideoEnabled) {
            return setParameter("che.video.peer.stop_all_renders", true);
        }
        return -1;
    }

    public int startAudioRecording(String str) {
        return setParameter("che.audio.start_recording", str);
    }

    public int stopAudioRecording() {
        return setParameter("che.audio.stop_recording", true);
    }

    public int startPlayingStream(String str) {
        return setParameter("rtc.api.video.start_play_stream", str);
    }

    public int stopPlayingStream() {
        return setParameter("rtc.api.video.stop_play_stream", true);
    }

    public int startAudioMixing(String str, boolean z, boolean z2, int i) {
        return setParameterObject("che.audio.start_file_as_playout", formatString("{\"filePath\":\"%s\", \"loopback\":%b, \"replace\":%b, \"cycle\":%d}", str, Boolean.valueOf(z), Boolean.valueOf(z2), Integer.valueOf(i)));
    }

    public int stopAudioMixing() {
        return setParameter("che.audio.stop_file_as_playout", true);
    }

    public int pauseAudioMixing() {
        return setParameter("che.audio.pause_file_as_playout", true);
    }

    public int resumeAudioMixing() {
        return setParameter("che.audio.pause_file_as_playout", false);
    }

    public int adjustAudioMixingVolume(int i) {
        return setParameter("che.audio.set_file_as_playout_volume", i);
    }

    public int getAudioMixingDuration() {
        return nativeGetIntParameter(this.mNativeHandle, "che.audio.get_mixing_file_length_ms", null);
    }

    public int getAudioMixingCurrentPosition() {
        return nativeGetIntParameter(this.mNativeHandle, "che.audio.get_mixing_file_played_ms", null);
    }

    public int setAudioMixingPosition(int i) {
        return setParameter("che.audio.mixing.file.position", i);
    }

    public int useExternalAudioDevice() {
        return setParameters("{\"che.audio.audioSampleRate\":32000, \"che.audio.external_device\":true}");
    }

    public int registerAudioFrameObserver(IAudioFrameObserver iAudioFrameObserver) {
        return nativeRegisterAudioFrameObserver(this.mNativeHandle, iAudioFrameObserver);
    }

    public int setLogFile(String str) {
        return setParameter("rtc.log_file", str);
    }

    public int setLogFilter(int i) {
        return setParameter("rtc.log_filter", i & io.agora.rtc.Constants.LOG_FILTER_DEBUG);
    }

    public int setProfile(String str, boolean z) {
        return nativeSetProfile(this.mNativeHandle, str, z);
    }

    public String getProfile() {
        return nativeGetProfile(this.mNativeHandle);
    }

    public int setParameters(String str) {
        return nativeSetParameters(this.mNativeHandle, str);
    }

    public String getParameters(String str) {
        return nativeGetParameters(this.mNativeHandle, str);
    }

    public String getParameter(String str, String str2) {
        return nativeGetParameter(this.mNativeHandle, str, str2);
    }

    public String makeQualityReportUrl(String str, int i, int i2, int i3) {
        return nativeMakeQualityReportUrl(this.mNativeHandle, str, i, i2, i3);
    }

    public String getCallId() {
        return nativeGetCallId(this.mNativeHandle);
    }

    public int rate(String str, int i, String str2) {
        return nativeRate(this.mNativeHandle, str, i, str2);
    }

    public int complain(String str, String str2) {
        return nativeComplain(this.mNativeHandle, str, str2);
    }

    public int setChannelProfile(int i) {
        this.mChannelProfile = i;
        this.mAudioRoutingController.sendEvent(20, i);
        return setParameter("rtc.channel_profile", i);
    }

    public int setClientRole(int i, String str) {
        Context context = (Context) this.mContext.get();
        if (context == null) {
            return -7;
        }
        if (checkVoipPermissions(context, i) != 0) {
            return -9;
        }
        switch (i) {
            case 1:
            case 2:
                this.mClientRole = i;
                if (str == null) {
                    str = "";
                }
                return setParameterObject("rtc.client_role", formatString("[%d,\"%s\"]", Integer.valueOf(i), str));
            default:
                return -2;
        }
    }

    public int setRemoteVideoStreamType(int i, int i2) {
        return setParameters(formatString("{\"rtc.video.set_remote_video_stream\":{\"uid\":%d,\"stream\":%d}}", Long.valueOf(((long) i) & 4294967295L), Integer.valueOf(i2)));
    }

    public int setEncryptionMode(String str) {
        return setParameter("rtc.encryption.mode", str);
    }

    public int setEncryptionSecret(String str) {
        return nativeSetEncryptionSecret(this.mNativeHandle, str);
    }

    private void onLogEvent(int i, String str) {
    }

    protected void onEvent(int i, byte[] bArr) {
        try {
            handleEvent(i, bArr);
        } catch (Exception e) {
        }
    }

    protected void handleEvent(int i, byte[] bArr) {
        if (this.mHandler != null) {
            PError pError;
            PMediaResRtcStats pMediaResRtcStats;
            PMediaResUserState pMediaResUserState;
            switch (i) {
                case 100:
                    sendLogEvent(bArr);
                    return;
                case 101:
                    pError = new PError();
                    pError.unmarshall(bArr);
                    this.mHandler.onError(pError.err);
                    return;
                case 102:
                    pError = new PError();
                    pError.unmarshall(bArr);
                    this.mHandler.onWarning(pError.err);
                    return;
                case 1002:
                    this.mHandler.onMediaEngineLoadSuccess();
                    return;
                case 1005:
                    this.mHandler.onCameraReady();
                    return;
                case 1006:
                    monitorAudioRouting();
                    this.mHandler.onMediaEngineStartCallSuccess();
                    return;
                case 1007:
                    this.mHandler.onVideoStopped();
                    return;
                case 1101:
                    PMediaResTransportQuality pMediaResTransportQuality = new PMediaResTransportQuality();
                    pMediaResTransportQuality.unmarshall(bArr);
                    if (pMediaResTransportQuality.isAudio) {
                        getExHandler().onAudioTransportQuality(pMediaResTransportQuality.peer_uid, pMediaResTransportQuality.bitrate, pMediaResTransportQuality.delay, pMediaResTransportQuality.lost);
                        return;
                    } else {
                        getExHandler().onVideoTransportQuality(pMediaResTransportQuality.peer_uid, pMediaResTransportQuality.bitrate, pMediaResTransportQuality.delay, pMediaResTransportQuality.lost);
                        return;
                    }
                case EvtType.EVT_AUDIO_QUALITY /*1102*/:
                    PMediaResAudioQuality pMediaResAudioQuality = new PMediaResAudioQuality();
                    pMediaResAudioQuality.unmarshall(bArr);
                    this.mHandler.onAudioQuality(pMediaResAudioQuality.peer_uid, pMediaResAudioQuality.quality, pMediaResAudioQuality.delay, pMediaResAudioQuality.lost);
                    return;
                case EvtType.EVT_MEDIA_ENGINE_EVENT /*1104*/:
                    PMediaEngineEvent pMediaEngineEvent = new PMediaEngineEvent();
                    pMediaEngineEvent.unmarshall(bArr);
                    switch (pMediaEngineEvent.code) {
                        case 10:
                            this.mHandler.onAudioMixingFinished();
                            return;
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                            this.mAudioRoutingController.sendEvent(21, pMediaEngineEvent.code);
                            return;
                        default:
                            return;
                    }
                case EvtType.EVT_API_CALL_EXECUTED /*1106*/:
                    onApiCallExecuted(bArr);
                    return;
                case EvtType.EVT_REQUEST_CHANNEL_KEY /*1108*/:
                    this.mHandler.onRequestChannelKey();
                    return;
                case 10001:
                    new MediaResSetupTime().unmarshall(bArr);
                    return;
                case EvtType.EVT_OPEN_CHANNEL_SUCCESS /*13001*/:
                    PMediaResJoinMedia pMediaResJoinMedia = new PMediaResJoinMedia();
                    pMediaResJoinMedia.unmarshall(bArr);
                    if (pMediaResJoinMedia.firstSuccess) {
                        this.mHandler.onJoinChannelSuccess(pMediaResJoinMedia.channel, pMediaResJoinMedia.uid, pMediaResJoinMedia.elapsed);
                        return;
                    } else {
                        this.mHandler.onRejoinChannelSuccess(pMediaResJoinMedia.channel, pMediaResJoinMedia.uid, pMediaResJoinMedia.elapsed);
                        return;
                    }
                case EvtType.EVT_LEAVE_CHANNEL /*13006*/:
                    Context context = (Context) this.mContext.get();
                    if (context != null) {
                        getAudioManager(context).setMode(0);
                    }
                    pMediaResRtcStats = new PMediaResRtcStats();
                    pMediaResRtcStats.unmarshall(bArr);
                    udpateRtcStats(pMediaResRtcStats);
                    this.mHandler.onLeaveChannel(getRtcStats());
                    return;
                case EvtType.EVT_NETWORK_QUALITY /*13007*/:
                    PMediaResNetworkQuality pMediaResNetworkQuality = new PMediaResNetworkQuality();
                    pMediaResNetworkQuality.unmarshall(bArr);
                    this.mHandler.onNetworkQuality(pMediaResNetworkQuality.uid, pMediaResNetworkQuality.txQuality, pMediaResNetworkQuality.rxQuality);
                    return;
                case EvtType.EVT_USER_OFFLINE /*13008*/:
                    PMediaResUserOfflineEvent pMediaResUserOfflineEvent = new PMediaResUserOfflineEvent();
                    pMediaResUserOfflineEvent.unmarshall(bArr);
                    this.mHandler.onUserOffline(pMediaResUserOfflineEvent.uid, pMediaResUserOfflineEvent.reason);
                    return;
                case EvtType.EVT_RTC_STATS /*13010*/:
                    pMediaResRtcStats = new PMediaResRtcStats();
                    pMediaResRtcStats.unmarshall(bArr);
                    udpateRtcStats(pMediaResRtcStats);
                    this.mHandler.onRtcStats(getRtcStats());
                    return;
                case EvtType.EVT_USER_JOINED /*13013*/:
                    PMediaResUserJoinedEvent pMediaResUserJoinedEvent = new PMediaResUserJoinedEvent();
                    pMediaResUserJoinedEvent.unmarshall(bArr);
                    this.mHandler.onUserJoined(pMediaResUserJoinedEvent.uid, pMediaResUserJoinedEvent.elapsed);
                    return;
                case EvtType.EVT_USER_MUTE_AUDIO /*13014*/:
                    pMediaResUserState = new PMediaResUserState();
                    pMediaResUserState.unmarshall(bArr);
                    this.mHandler.onUserMuteAudio(pMediaResUserState.uid, pMediaResUserState.state);
                    return;
                case EvtType.EVT_USER_MUTE_VIDEO /*13015*/:
                    pMediaResUserState = new PMediaResUserState();
                    pMediaResUserState.unmarshall(bArr);
                    this.mHandler.onUserMuteVideo(pMediaResUserState.uid, pMediaResUserState.state);
                    return;
                case EvtType.EVT_USER_ENABLE_VIDEO /*13016*/:
                    pMediaResUserState = new PMediaResUserState();
                    pMediaResUserState.unmarshall(bArr);
                    this.mHandler.onUserEnableVideo(pMediaResUserState.uid, pMediaResUserState.state);
                    return;
                case EvtType.EVT_LASTMILE_QUALITY /*13017*/:
                    PMediaResLastmileQuality pMediaResLastmileQuality = new PMediaResLastmileQuality();
                    pMediaResLastmileQuality.unmarshall(bArr);
                    this.mHandler.onLastmileQuality(pMediaResLastmileQuality.quality);
                    return;
                case EvtType.EVT_RECAP_INDICATION /*14000*/:
                    getExHandler().onRecap(bArr);
                    return;
                case EvtType.EVT_AUDIO_VOLUME_INDICATION /*14001*/:
                    onSpeakersReport(bArr);
                    return;
                case EvtType.EVT_FIRST_REMOTE_VIDEO_FRAME /*14002*/:
                    onFirstRemoteVideoFrame(bArr);
                    return;
                case EvtType.EVT_LOCAL_VIDEO_STAT /*14003*/:
                    onLocalVideoStat(bArr);
                    return;
                case EvtType.EVT_REMOTE_VIDEO_STAT /*14004*/:
                    onRemoteVideoStat(bArr);
                    return;
                case EvtType.EVT_FIRST_LOCAL_VIDEO_FRAME /*14005*/:
                    onFirstLocalVideoFrame(bArr);
                    return;
                case EvtType.EVT_FIRST_REMOTE_VIDEO_DECODED /*14007*/:
                    onFirstRemoteVideoDecoded(bArr);
                    return;
                case EvtType.EVT_CONNECTION_LOST /*14008*/:
                    this.mHandler.onConnectionLost();
                    return;
                case EvtType.EVT_STREAM_MESSAGE /*14009*/:
                    onStreamMessage(bArr);
                    return;
                case EvtType.EVT_CONNECTION_INTERRUPTED /*14010*/:
                    this.mHandler.onConnectionInterrupted();
                    return;
                case EvtType.QUERY_RECORDING_SERVICE_STATUS /*14011*/:
                    PRecordingServiceStatus pRecordingServiceStatus = new PRecordingServiceStatus();
                    pRecordingServiceStatus.unmarshall(bArr);
                    this.mHandler.onRefreshRecordingServiceStatus(pRecordingServiceStatus.status);
                    return;
                case EvtType.EVT_STREAM_MESSAGE_ERROR /*14012*/:
                    onStreamMessageError(bArr);
                    return;
                case EvtType.EVT_VIDEO_SIZE_CHANGED /*14013*/:
                    onVideoSizeChanged(bArr);
                    return;
                case EvtType.FIRST_LOCAL_AUDIO_FRAME /*14014*/:
                    onFirstLocalAudioFrame(bArr);
                    return;
                case EvtType.FIRST_REMOTE_AUDIO_FRAME /*14015*/:
                    onFirstRemoteAudioFrame(bArr);
                    return;
                default:
                    return;
            }
        }
    }

    private void onApiCallExecuted(byte[] bArr) {
        PApiCallExecuted pApiCallExecuted = new PApiCallExecuted();
        pApiCallExecuted.unmarshall(bArr);
        this.mHandler.onApiCallExecuted(pApiCallExecuted.api, pApiCallExecuted.error);
    }

    private void onFirstRemoteVideoDecoded(byte[] bArr) {
        PFirstRemoteVideoDecoded pFirstRemoteVideoDecoded = new PFirstRemoteVideoDecoded();
        pFirstRemoteVideoDecoded.unmarshall(bArr);
        this.mHandler.onFirstRemoteVideoDecoded(pFirstRemoteVideoDecoded.uid, pFirstRemoteVideoDecoded.width, pFirstRemoteVideoDecoded.height, pFirstRemoteVideoDecoded.elapsed);
    }

    private void onVideoSizeChanged(byte[] bArr) {
        PVideoSizeChanged pVideoSizeChanged = new PVideoSizeChanged();
        pVideoSizeChanged.unmarshall(bArr);
        this.mHandler.onVideoSizeChanged(pVideoSizeChanged.uid, pVideoSizeChanged.width, pVideoSizeChanged.height, pVideoSizeChanged.rotation);
    }

    private void onStreamMessage(byte[] bArr) {
        PStreamMessage pStreamMessage = new PStreamMessage();
        pStreamMessage.unmarshall(bArr);
        this.mHandler.onStreamMessage(pStreamMessage.uid, pStreamMessage.streamId, pStreamMessage.payload);
    }

    private void onStreamMessageError(byte[] bArr) {
        PStreamMessageError pStreamMessageError = new PStreamMessageError();
        pStreamMessageError.unmarshall(bArr);
        this.mHandler.onStreamMessageError(pStreamMessageError.uid, pStreamMessageError.streamId, pStreamMessageError.error, pStreamMessageError.missed, pStreamMessageError.cached);
    }

    private void onFirstLocalVideoFrame(byte[] bArr) {
        PFirstLocalVideoFrame pFirstLocalVideoFrame = new PFirstLocalVideoFrame();
        pFirstLocalVideoFrame.unmarshall(bArr);
        this.mHandler.onFirstLocalVideoFrame(pFirstLocalVideoFrame.width, pFirstLocalVideoFrame.height, pFirstLocalVideoFrame.elapsed);
    }

    private void onRemoteVideoStat(byte[] bArr) {
        PRemoteVideoStat pRemoteVideoStat = new PRemoteVideoStat();
        pRemoteVideoStat.unmarshall(bArr);
        if (pRemoteVideoStat.stats.uid != 0) {
            this.mHandler.onRemoteVideoStats(pRemoteVideoStat.stats);
        }
    }

    private void onLocalVideoStat(byte[] bArr) {
        PLocalVideoStat pLocalVideoStat = new PLocalVideoStat();
        pLocalVideoStat.unmarshall(bArr);
        this.mHandler.onLocalVideoStats(pLocalVideoStat.stats);
    }

    private void onFirstRemoteVideoFrame(byte[] bArr) {
        PFirstRemoteVideoFrame pFirstRemoteVideoFrame = new PFirstRemoteVideoFrame();
        pFirstRemoteVideoFrame.unmarshall(bArr);
        this.mHandler.onFirstRemoteVideoFrame(pFirstRemoteVideoFrame.uid, pFirstRemoteVideoFrame.width, pFirstRemoteVideoFrame.height, pFirstRemoteVideoFrame.elapsed);
    }

    private void onFirstLocalAudioFrame(byte[] bArr) {
        PFirstLocalAudioFrame pFirstLocalAudioFrame = new PFirstLocalAudioFrame();
        pFirstLocalAudioFrame.unmarshall(bArr);
        this.mHandler.onFirstLocalAudioFrame(pFirstLocalAudioFrame.elapsed);
    }

    private void onFirstRemoteAudioFrame(byte[] bArr) {
        PFirstRemoteAudioFrame pFirstRemoteAudioFrame = new PFirstRemoteAudioFrame();
        pFirstRemoteAudioFrame.unmarshall(bArr);
        this.mHandler.onFirstRemoteAudioFrame(pFirstRemoteAudioFrame.uid, pFirstRemoteAudioFrame.elapsed);
    }

    private void onSpeakersReport(byte[] bArr) {
        if (bArr != null) {
            PMediaResSpeakersReport pMediaResSpeakersReport = new PMediaResSpeakersReport();
            pMediaResSpeakersReport.unmarshall(bArr);
            if (pMediaResSpeakersReport.speakers == null || pMediaResSpeakersReport.speakers.length < 0) {
                this.mHandler.onAudioVolumeIndication(null, pMediaResSpeakersReport.mixVolume);
                return;
            }
            IRtcEngineEventHandler$AudioVolumeInfo[] iRtcEngineEventHandler$AudioVolumeInfoArr = new IRtcEngineEventHandler$AudioVolumeInfo[pMediaResSpeakersReport.speakers.length];
            for (int i = 0; i < pMediaResSpeakersReport.speakers.length; i++) {
                iRtcEngineEventHandler$AudioVolumeInfoArr[i] = new IRtcEngineEventHandler$AudioVolumeInfo();
                iRtcEngineEventHandler$AudioVolumeInfoArr[i].uid = pMediaResSpeakersReport.speakers[i].uid;
                iRtcEngineEventHandler$AudioVolumeInfoArr[i].volume = pMediaResSpeakersReport.speakers[i].volume;
            }
            this.mHandler.onAudioVolumeIndication(iRtcEngineEventHandler$AudioVolumeInfoArr, pMediaResSpeakersReport.mixVolume);
        }
    }

    private void sendLogEvent(byte[] bArr) {
        try {
            onLogEvent(0, new String(bArr, "ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
        }
    }

    protected AudioManager getAudioManager(Context context) {
        if (context == null) {
            return null;
        }
        return (AudioManager) context.getSystemService("audio");
    }

    protected ActivityManager getActivityManager(Context context) {
        if (context == null) {
            return null;
        }
        return (ActivityManager) context.getSystemService("activity");
    }

    private boolean checkAccessNetworkState(Context context) {
        if (context != null && context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0) {
            return true;
        }
        return false;
    }

    private boolean checkAccessWifiState(Context context) {
        if (context != null && context.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") == 0) {
            return true;
        }
        return false;
    }

    protected int getNetworkType() {
        Context context = (Context) this.mContext.get();
        if (checkAccessNetworkState(context)) {
            return Connectivity.getNetworkType(context);
        }
        return -1;
    }

    protected byte[] getNetworkInfo() {
        MediaNetworkInfo doGetNetworkInfo = doGetNetworkInfo((Context) this.mContext.get());
        if (doGetNetworkInfo != null) {
            return doGetNetworkInfo.marshall();
        }
        return null;
    }

    private PVideoNetOptions getOptionsByVideoProfile(int i) {
        try {
            byte[] nativeGetOptionsByVideoProfile = nativeGetOptionsByVideoProfile(i);
            if (nativeGetOptionsByVideoProfile != null) {
                PVideoNetOptions pVideoNetOptions = new PVideoNetOptions();
                pVideoNetOptions.unmarshall(nativeGetOptionsByVideoProfile);
                return pVideoNetOptions;
            }
        } catch (Exception e) {
        }
        return null;
    }

    private IRtcEngineEventHandlerEx getExHandler() {
        return (IRtcEngineEventHandlerEx) this.mHandler;
    }

    public void notifyNetworkChange() {
        nativeNotifyNetworkChange(this.mNativeHandle, getNetworkInfo());
    }

    public int createDataStream(boolean z, boolean z2) {
        return nativeCreateDataStream(this.mNativeHandle, z, z2);
    }

    public int sendStreamMessage(int i, byte[] bArr) {
        return nativeSendStreamMessage(this.mNativeHandle, i, bArr);
    }

    public int updateSharedContext(EGLContext eGLContext) {
        if (this.mVideoEnabled) {
            return nativeSetEGL10Context(this.mNativeHandle, eGLContext);
        }
        return -1;
    }

    public int updateSharedContext(android.opengl.EGLContext eGLContext) {
        if (this.mVideoEnabled) {
            return nativeSetEGL14Context(this.mNativeHandle, eGLContext);
        }
        return -1;
    }

    public int setTextureId(int i, EGLContext eGLContext, int i2, int i3, long j) {
        if (!this.mVideoEnabled) {
            return -1;
        }
        return nativeSetEGL10TextureId(this.mNativeHandle, i, eGLContext, 10, i2, i3, j, sMatrix);
    }

    public int setTextureId(int i, android.opengl.EGLContext eGLContext, int i2, int i3, long j) {
        if (!this.mVideoEnabled) {
            return -1;
        }
        return nativeSetEGL14TextureId(this.mNativeHandle, i, eGLContext, 11, i2, i3, j, sMatrix);
    }

    public int setTextureIdWithMatrix(int i, EGLContext eGLContext, int i2, int i3, int i4, long j, float[] fArr) {
        if (!this.mVideoEnabled) {
            return -1;
        }
        if (fArr == null) {
            return nativeSetEGL10TextureId(this.mNativeHandle, i, eGLContext, i2, i3, i4, j, sMatrix);
        } else if (fArr.length < 16) {
            return -2;
        } else {
            return nativeSetEGL10TextureId(this.mNativeHandle, i, eGLContext, i2, i3, i4, j, fArr);
        }
    }

    public int setTextureIdWithMatrix(int i, android.opengl.EGLContext eGLContext, int i2, int i3, int i4, long j, float[] fArr) {
        if (!this.mVideoEnabled) {
            return -1;
        }
        if (fArr == null) {
            return nativeSetEGL14TextureId(this.mNativeHandle, i, eGLContext, i2, i3, i4, j, sMatrix);
        } else if (fArr.length < 16) {
            return -2;
        } else {
            return nativeSetEGL14TextureId(this.mNativeHandle, i, eGLContext, i2, i3, i4, j, fArr);
        }
    }

    public boolean isTextureEncodeSupported() {
        return DeviceUtils.getRecommendedEncoderType() == 0;
    }

    public void setExternalVideoSource(boolean z, boolean z2, boolean z3) {
        int i = 1;
        this.mUseExternalVideoSource = z;
        if (!z2) {
            long j = this.mNativeHandle;
            int i2 = z ? 1 : 0;
            if (!z3) {
                i = 0;
            }
            setExtVideoSource(j, i2, i);
        } else if (z) {
            setParameter("che.video.enable_external_texture_input", true);
        } else {
            setParameter("che.video.enable_external_texture_input", false);
            Logging.w("setVideoSource: on Android, texture mode cannot be disabled once enabled.");
        }
    }

    public boolean pushExternalVideoFrame(AgoraVideoFrame agoraVideoFrame) {
        if (agoraVideoFrame == null || agoraVideoFrame.format == 12) {
            return false;
        }
        if (this.mUseExternalVideoSource && (agoraVideoFrame.format == 10 || agoraVideoFrame.format == 11)) {
            if (agoraVideoFrame.eglContext14 != null) {
                if (updateSharedContext(agoraVideoFrame.eglContext14) == 0) {
                    return setTextureIdWithMatrix(agoraVideoFrame.textureID, agoraVideoFrame.eglContext14, agoraVideoFrame.format, agoraVideoFrame.stride, agoraVideoFrame.height, agoraVideoFrame.timeStamp, agoraVideoFrame.transform) == 0;
                }
            } else if (agoraVideoFrame.eglContext11 != null) {
                if (updateSharedContext(agoraVideoFrame.eglContext11) == 0) {
                    return setTextureIdWithMatrix(agoraVideoFrame.textureID, agoraVideoFrame.eglContext11, agoraVideoFrame.format, agoraVideoFrame.stride, agoraVideoFrame.height, agoraVideoFrame.timeStamp, agoraVideoFrame.transform) == 0;
                }
            }
        } else if (agoraVideoFrame.format > 0 && agoraVideoFrame.format <= 8) {
            return deliverFrame(this.mNativeHandle, agoraVideoFrame.buf, agoraVideoFrame.stride, agoraVideoFrame.height, agoraVideoFrame.cropLeft, agoraVideoFrame.cropTop, agoraVideoFrame.cropRight, agoraVideoFrame.cropBottom, agoraVideoFrame.rotation, agoraVideoFrame.timeStamp, agoraVideoFrame.format) == 0;
        }
        return false;
    }

    public long getNativeHandle() {
        return nativeGetHandle(this.mNativeHandle);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String inetAddressToIpAddress(java.net.InetAddress r1) {
        /*
        r0 = r1.isLoopbackAddress();
        if (r0 != 0) goto L_0x0015;
    L_0x0006:
        r0 = r1 instanceof java.net.Inet4Address;
        if (r0 == 0) goto L_0x0011;
    L_0x000a:
        r1 = (java.net.Inet4Address) r1;
        r0 = r1.getHostAddress();
    L_0x0010:
        return r0;
    L_0x0011:
        r0 = r1 instanceof java.net.Inet6Address;
        if (r0 == 0) goto L_0x0015;
    L_0x0015:
        r0 = 0;
        goto L_0x0010;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.agora.rtc.internal.RtcEngineImpl.inetAddressToIpAddress(java.net.InetAddress):java.lang.String");
    }

    public int configPublisher(PublisherConfiguration publisherConfiguration) {
        if (!publisherConfiguration.validate()) {
            return -2;
        }
        this.mConfiguration = publisherConfiguration;
        return 0;
    }

    public int setVideoCompositingLayout(VideoCompositingLayout videoCompositingLayout) {
        if (videoCompositingLayout == null || videoCompositingLayout.regions == null) {
            return -2;
        }
        int i = 0;
        while (i < videoCompositingLayout.regions.length) {
            if (videoCompositingLayout.regions[i].uid == 0 || videoCompositingLayout.regions[i].width <= 0.0d || videoCompositingLayout.regions[i].height <= 0.0d) {
                return -2;
            }
            i++;
        }
        return nativeSetVideoCompositingLayout(this.mNativeHandle, new PVideoCompositingLayout().marshall(videoCompositingLayout));
    }

    public int clearVideoCompositingLayout() {
        return setParameter("rtc.api.clear_video_compositing_layout", true);
    }

    protected static String getLocalHost() {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (!networkInterface.getName().startsWith("usb")) {
                    for (InetAddress inetAddressToIpAddress : Collections.list(networkInterface.getInetAddresses())) {
                        String inetAddressToIpAddress2 = inetAddressToIpAddress(inetAddressToIpAddress);
                        if (inetAddressToIpAddress2 != null && !inetAddressToIpAddress2.isEmpty()) {
                            return inetAddressToIpAddress2;
                        }
                    }
                    continue;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    protected static String[] getLocalHostList() {
        try {
            String inetAddressToIpAddress;
            List<NetworkInterface> list = Collections.list(NetworkInterface.getNetworkInterfaces());
            List<String> arrayList = new ArrayList();
            for (NetworkInterface networkInterface : list) {
                if (!networkInterface.getName().startsWith("usb")) {
                    for (InetAddress inetAddressToIpAddress2 : Collections.list(networkInterface.getInetAddresses())) {
                        inetAddressToIpAddress = inetAddressToIpAddress(inetAddressToIpAddress2);
                        if (inetAddressToIpAddress != null) {
                            arrayList.add(inetAddressToIpAddress);
                        }
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                String[] strArr = new String[arrayList.size()];
                int i = 0;
                for (String inetAddressToIpAddress3 : arrayList) {
                    strArr[i] = inetAddressToIpAddress3;
                    i++;
                }
                return strArr;
            }
        } catch (Exception e) {
        }
        return null;
    }

    private static InetAddress intToInetAddress(int i) {
        try {
            return InetAddress.getByAddress(new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)});
        } catch (UnknownHostException e) {
            return null;
        }
    }

    public IAudioEffectManager getAudioEffectManager() {
        return this;
    }

    public double getEffectsVolume() {
        return (double) nativeGetIntParameter(this.mNativeHandle, "che.audio.game_get_effects_volume", null);
    }

    public int setEffectsVolume(double d) {
        return setParameter("che.audio.game_set_effects_volume", d);
    }

    public int playEffect(int i, String str, boolean z, double d, double d2, double d3) {
        return setParameterObject("che.audio.game_play_effect", formatString("{\"soundId\":%d,\"filePath\":\"%s\",\"loop\":%b,\"pitch\":%f,\"pan\":%f,\"gain\":%f}", Integer.valueOf(i), str, Boolean.valueOf(z), Double.valueOf(d), Double.valueOf(d2), Double.valueOf(d3)));
    }

    public int stopEffect(int i) {
        return setParameter("che.audio.game_stop_effect", i);
    }

    public int stopAllEffects() {
        return setParameter("che.audio.game_stop_all_effects", true);
    }

    public int preloadEffect(int i, String str) {
        return setParameterObject("che.audio.game_preload_effect", formatString("{\"soundId\":%d,\"filePath\":\"%s\"}", Integer.valueOf(i), str));
    }

    public int unloadEffect(int i) {
        return setParameter("che.audio.game_unload_effect", i);
    }

    public int pauseEffect(int i) {
        return setParameter("che.audio.game_pause_effect", i);
    }

    public int pauseAllEffects() {
        return setParameter("che.audio.game_pause_all_effects", true);
    }

    public int resumeEffect(int i) {
        return setParameter("che.audio.game_resume_effect", i);
    }

    public int resumeAllEffects() {
        return setParameter("che.audio.game_resume_all_effects", true);
    }

    public void finalize() {
        if (this.mNativeHandle != 0) {
            nativeDestroy(this.mNativeHandle);
        }
    }

    private int setParameter(String str, boolean z) {
        return setParameters(formatString("{\"%s\":%b}", str, Boolean.valueOf(z)));
    }

    private int setParameter(String str, int i) {
        return setParameters(formatString("{\"%s\":%d}", str, Integer.valueOf(i)));
    }

    private int setParameter(String str, long j) {
        return setParameters(formatString("{\"%s\":%d}", str, Long.valueOf(j)));
    }

    private int setParameter(String str, double d) {
        return setParameters(formatString("{\"%s\":%f}", str, Double.valueOf(d)));
    }

    private int setParameter(String str, String str2) {
        return setParameters(formatString("{\"%s\":\"%s\"}", str, str2));
    }

    private int setParameterObject(String str, String str2) {
        return setParameters(formatString("{\"%s\":%s}", str, str2));
    }
}
