package io.agora.rtc.video;

import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.Area;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Face;
import android.hardware.Camera.FaceDetectionListener;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import com.alipay.sdk.util.h;
import com.xiaomi.mipush.sdk.Constants;
import io.agora.rtc.internal.DeviceUtils;
import io.agora.rtc.internal.Logging;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class VideoCaptureCamera extends VideoCapture implements PreviewCallback, Callback {
    private static final boolean DEBUG = false;
    private static final String TAG = "CAMERA1";
    private boolean isCaptureRunning = false;
    private boolean isCaptureStarted = false;
    private boolean isFaceDetectionStarted = false;
    private boolean isSurfaceReady = false;
    protected Camera mCamera;
    private int mCaptureFormat = 17;
    private int mCaptureFps = -1;
    private int mCaptureHeight = -1;
    private ReentrantLock mCaptureLock = new ReentrantLock();
    private int mCaptureWidth = -1;
    private SurfaceTexture mDummySurfaceTexture = null;
    private int mExpectedFrameSize = 0;
    private SurfaceHolder mLocalPreview = null;
    private final int mNumCaptureBuffers = 3;
    private boolean mOwnsBuffers = false;
    protected ReentrantLock mPreviewBufferLock = new ReentrantLock();

    public void onPreviewFrame(byte[] r5, android.hardware.Camera r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0015 in list [B:7:0x0012]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r4 = this;
        r0 = r4.mPreviewBufferLock;	 Catch:{ all -> 0x002d }
        r0.lock();	 Catch:{ all -> 0x002d }
        if (r5 == 0) goto L_0x000b;	 Catch:{ all -> 0x002d }
    L_0x0007:
        r0 = r4.isCaptureRunning;	 Catch:{ all -> 0x002d }
        if (r0 != 0) goto L_0x0016;
    L_0x000b:
        r0 = r4.mPreviewBufferLock;
        r0.unlock();
        if (r6 == 0) goto L_0x0015;
    L_0x0012:
        r6.addCallbackBuffer(r5);
    L_0x0015:
        return;
    L_0x0016:
        r0 = r5.length;	 Catch:{ all -> 0x002d }
        r1 = r4.mExpectedFrameSize;	 Catch:{ all -> 0x002d }
        if (r0 != r1) goto L_0x0022;	 Catch:{ all -> 0x002d }
    L_0x001b:
        r0 = r4.mExpectedFrameSize;	 Catch:{ all -> 0x002d }
        r2 = r4.mNativeVideoCaptureDeviceAndroid;	 Catch:{ all -> 0x002d }
        r4.ProvideCameraFrame(r5, r0, r2);	 Catch:{ all -> 0x002d }
    L_0x0022:
        r0 = r4.mPreviewBufferLock;
        r0.unlock();
        if (r6 == 0) goto L_0x0015;
    L_0x0029:
        r6.addCallbackBuffer(r5);
        goto L_0x0015;
    L_0x002d:
        r0 = move-exception;
        r1 = r4.mPreviewBufferLock;
        r1.unlock();
        if (r6 == 0) goto L_0x0038;
    L_0x0035:
        r6.addCallbackBuffer(r5);
    L_0x0038:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.agora.rtc.video.VideoCaptureCamera.onPreviewFrame(byte[], android.hardware.Camera):void");
    }

    VideoCaptureCamera(Context context, int i, long j) {
        super(context, i, j);
    }

    protected static CameraInfo getCameraInfo(int i) {
        CameraInfo cameraInfo = new CameraInfo();
        try {
            Camera.getCameraInfo(i, cameraInfo);
            return cameraInfo;
        } catch (Throwable e) {
            Logging.e(TAG, "getCameraInfo: Camera.getCameraInfo: ", e);
            return null;
        }
    }

    static int getNumberOfCameras() {
        return Camera.getNumberOfCameras();
    }

    static String getName(int i) {
        CameraInfo cameraInfo = getCameraInfo(i);
        if (cameraInfo == null) {
            return null;
        }
        return "camera " + i + ", facing " + (cameraInfo.facing == 1 ? "front" : "back");
    }

    static int getSensorOrientation(int i) {
        CameraInfo cameraInfo = getCameraInfo(i);
        if (cameraInfo == null) {
            return -1;
        }
        return cameraInfo.orientation;
    }

    public int allocate() {
        try {
            this.mCamera = Camera.open(this.mId);
            CameraInfo cameraInfo = getCameraInfo(this.mId);
            if (cameraInfo == null) {
                this.mCamera.release();
                this.mCamera = null;
                return -2;
            }
            if (VideoCapture.fetchCapability(this.mId, this.mContext) == null) {
                createCapabilities();
            }
            this.mCameraNativeOrientation = cameraInfo.orientation;
            return 0;
        } catch (Throwable e) {
            Logging.e(TAG, "allocate: Camera.open: ", e);
            return -1;
        }
    }

    public int setCaptureFormat(int i) {
        Logging.d(TAG, "setCaptureFormat: " + i);
        this.mCaptureFormat = VideoCapture.translateToAndroidFormat(i);
        if (this.mCaptureFormat != 0) {
            return 0;
        }
        Logging.e(TAG, "setCaptureFormat failed, unkonwn format: " + i);
        return -1;
    }

    private int tryStartCapture(int i, int i2, int i3) {
        if (this.mCamera == null) {
            Logging.e(TAG, "Camera not initialized %d" + this.mId);
            return -1;
        }
        Logging.i(TAG, "tryStartCapture: " + i + "*" + i2 + ", frameRate: " + i3 + ", isCaptureRunning: " + this.isCaptureRunning + ", isSurfaceReady: " + this.isSurfaceReady + ", isCaptureStarted: " + this.isCaptureStarted);
        if (this.isCaptureRunning || !this.isCaptureStarted) {
            Logging.w(TAG, "tryStartCapture return");
            return 0;
        }
        Parameters parameters = this.mCamera.getParameters();
        parameters.setPreviewSize(i, i2);
        parameters.setPreviewFormat(this.mCaptureFormat);
        parameters.setPreviewFrameRate(i3);
        setAdvancedCameraParameters(parameters);
        setDeviceSpecificParameters(parameters);
        try {
            this.mCamera.setParameters(parameters);
            int bitsPerPixel = (((i * i2) * ImageFormat.getBitsPerPixel(this.mCaptureFormat)) / 8) + 4096;
            for (int i4 = 0; i4 < 3; i4++) {
                this.mCamera.addCallbackBuffer(new byte[bitsPerPixel]);
            }
            this.mCamera.setPreviewCallbackWithBuffer(this);
            this.mOwnsBuffers = true;
            this.mCamera.startPreview();
            if (parameters.getMaxNumDetectedFaces() > 0) {
                this.mCamera.setFaceDetectionListener(new FaceDetectionListener() {
                    private long mLastFocusedTs;

                    public void onFaceDetection(Face[] faceArr, Camera camera) {
                        if (faceArr != null && faceArr.length != 0 && System.currentTimeMillis() - this.mLastFocusedTs >= 3000 && faceArr[0].score > 50) {
                            try {
                                List arrayList = new ArrayList();
                                arrayList.add(new Area(faceArr[0].rect, 1000));
                                if (VideoCaptureCamera.this.mCamera.getParameters().getMaxNumFocusAreas() > 0) {
                                    VideoCaptureCamera.this.mCamera.getParameters().setFocusAreas(arrayList);
                                }
                                if (VideoCaptureCamera.this.mCamera.getParameters().getMaxNumMeteringAreas() > 0) {
                                    VideoCaptureCamera.this.mCamera.getParameters().setMeteringAreas(arrayList);
                                }
                                VideoCaptureCamera.this.mCamera.autoFocus(new AutoFocusCallback() {
                                    public void onAutoFocus(boolean z, Camera camera) {
                                        if (camera != null) {
                                            camera.cancelAutoFocus();
                                        }
                                    }
                                });
                                this.mLastFocusedTs = System.currentTimeMillis();
                            } catch (RuntimeException e) {
                                Logging.w(VideoCaptureCamera.TAG, "Exception in facedetection callback:" + e.toString());
                            }
                        }
                    }
                });
                Logging.i(TAG, "enable face detection");
                this.mCamera.startFaceDetection();
                this.isFaceDetectionStarted = true;
            }
            this.mPreviewBufferLock.lock();
            this.mExpectedFrameSize = bitsPerPixel;
            this.isCaptureRunning = true;
            this.mPreviewBufferLock.unlock();
            Logging.e(TAG, "Params:" + this.mCamera.getParameters().flatten());
            return 0;
        } catch (RuntimeException e) {
            Logging.e(TAG, "setParameters failed " + e);
            return -1;
        }
    }

    public int startCapture(int i, int i2, int i3) {
        int i4 = -1;
        if (this.mCamera == null) {
            Logging.e(TAG, "startCapture: camera is null!!");
        } else {
            this.mLocalPreview = ViERenderer.GetLocalRenderer();
            if (this.mLocalPreview != null) {
                if (this.mLocalPreview.getSurface() != null && this.mLocalPreview.getSurface().isValid()) {
                    surfaceCreated(this.mLocalPreview);
                }
                this.mLocalPreview.addCallback(this);
            } else {
                this.mCaptureLock.lock();
                try {
                    this.mDummySurfaceTexture = new SurfaceTexture(42);
                    this.mCamera.setPreviewTexture(this.mDummySurfaceTexture);
                } catch (Exception e) {
                    Logging.e(TAG, "failed to startPreview, invalid surfaceTexture!");
                    this.mDummySurfaceTexture = null;
                } finally {
                    this.mCaptureLock.unlock();
                }
            }
            this.mCaptureLock.lock();
            this.isCaptureStarted = true;
            this.mCaptureWidth = i;
            this.mCaptureHeight = i2;
            this.mCaptureFps = i3;
            try {
                i4 = tryStartCapture(i, i2, i3);
            } catch (Throwable th) {
                Logging.e(TAG, "try start capture failed" + th);
            } finally {
                this.mCaptureLock.unlock();
            }
        }
        return i4;
    }

    public int stopCapture() {
        if (this.isCaptureStarted) {
            try {
                if (this.isFaceDetectionStarted) {
                    this.mCamera.stopFaceDetection();
                    this.mCamera.setFaceDetectionListener(null);
                    this.isFaceDetectionStarted = false;
                }
                this.mCamera.stopPreview();
                this.mPreviewBufferLock.lock();
                this.isCaptureRunning = false;
                this.mPreviewBufferLock.unlock();
                this.mCamera.setPreviewCallbackWithBuffer(null);
                this.isCaptureStarted = false;
                return 0;
            } catch (Throwable e) {
                Logging.e(TAG, "Failed to stop camera", e);
                return -1;
            }
        }
        Logging.w(TAG, "already stop capture");
        return 0;
    }

    public void deallocate() {
        if (this.mCamera != null) {
            stopCapture();
            this.mCaptureLock.lock();
            this.mCamera.release();
            this.mCamera = null;
            this.mCaptureLock.unlock();
            this.mNativeVideoCaptureDeviceAndroid = 0;
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.mCaptureLock.lock();
        try {
            if (this.mCamera != null) {
                this.mCamera.stopPreview();
                this.mCamera.setPreviewDisplay(surfaceHolder);
            }
        } catch (Throwable e) {
            Logging.e(TAG, "Failed to set preview surface!", e);
        }
        this.mCaptureLock.unlock();
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mCaptureLock.lock();
        try {
            if (this.mCamera != null) {
                this.mCamera.setPreviewDisplay(null);
            }
        } catch (Throwable e) {
            Logging.e(TAG, "Failed to clear preview surface!", e);
        }
        this.mCaptureLock.unlock();
    }

    private static boolean isSupported(String str, List<String> list) {
        return list != null && list.indexOf(str) >= 0;
    }

    private void setAdvancedCameraParameters(Parameters parameters) {
        String str = "off";
        if (isSupported(str, parameters.getSupportedFlashModes())) {
            Logging.i(TAG, "AgoraVideo set flash mode = FLASH_MODE_OFF");
            parameters.setFlashMode(str);
        }
        str = "auto";
        if (isSupported(str, parameters.getSupportedWhiteBalance())) {
            Logging.i(TAG, "AgoraVideo set white blance = WHITE_BALANCE_AUTO");
            parameters.setWhiteBalance(str);
        }
        str = "continuous-video";
        if (isSupported(str, parameters.getSupportedFocusModes())) {
            Logging.i(TAG, "AgoraVideo set Focus mode = FOCUS_MODE_CONTINUOUS_VIDEO");
            parameters.setFocusMode(str);
        }
        str = "auto";
        if (isSupported(str, parameters.getSupportedAntibanding())) {
            Logging.i(TAG, "AgoraVideo set anti-banding = ANTIBANDING_AUTO");
            parameters.setAntibanding(str);
        }
        str = "auto";
        if (isSupported(str, parameters.getSupportedSceneModes())) {
            Logging.i(TAG, "AgoraVideo set sence mode = " + str);
            if (parameters.getSceneMode() != str) {
                parameters.setSceneMode(str);
            }
        }
    }

    private void setDeviceSpecificParameters(Parameters parameters) {
        String deviceId = DeviceUtils.getDeviceId();
        String cpuName = DeviceUtils.getCpuName();
        String cpuABI = DeviceUtils.getCpuABI();
        int numberOfCPUCores = DeviceUtils.getNumberOfCPUCores();
        int cPUMaxFreqKHz = DeviceUtils.getCPUMaxFreqKHz();
        Logging.i(TAG, "Current Device: " + deviceId);
        Logging.i(TAG, "CPU name: " + cpuName + ", with " + numberOfCPUCores + " cores, " + "arch: " + cpuABI + ", max Freq: " + cPUMaxFreqKHz);
        if (deviceId.contains("xiaomi/mi note")) {
            Logging.i(TAG, "set MiNote config");
            parameters.set("scene-detect", "on");
            parameters.set("xiaomi-still-beautify-values", "i:3");
            parameters.set("skinToneEnhancement", "enable");
            parameters.set("auto-exposure", "center-weighted");
        }
        if (deviceId.contains("oppo/r7c/r7c")) {
            Logging.i(TAG, "set oppo r7c config");
            parameters.set("skinToneEnhancement", 1);
            parameters.set("face-beautify", 100);
            parameters.set("auto-exposure", "center-weighted");
        }
    }

    public Parameters getCameraParameters() {
        try {
            return this.mCamera.getParameters();
        } catch (Throwable e) {
            Logging.e(TAG, "getCameraParameters: Camera.getParameters: ", e);
            if (this.mCamera != null) {
                this.mCamera.release();
            }
            return null;
        }
    }

    public int createCapabilities() {
        String str = null;
        Parameters cameraParameters = getCameraParameters();
        if (cameraParameters != null) {
            int translateToEngineFormat;
            String str2 = "\"id\":" + this.mId + Constants.ACCEPT_TIME_SEPARATOR_SP;
            String str3 = "\"resolution\":";
            List supportedPreviewSizes = cameraParameters.getSupportedPreviewSizes();
            int i = 0;
            String str4 = "";
            while (i < supportedPreviewSizes.size()) {
                str = "{\"w\":" + ((Size) supportedPreviewSizes.get(i)).width + ",\"h\":" + ((Size) supportedPreviewSizes.get(i)).height + h.d;
                if (i != supportedPreviewSizes.size() - 1) {
                    str = str4 + str + Constants.ACCEPT_TIME_SEPARATOR_SP;
                } else {
                    str = str4 + str;
                }
                i++;
                str4 = str;
            }
            String str5 = "\"format\":";
            List supportedPreviewFormats = cameraParameters.getSupportedPreviewFormats();
            i = 0;
            String str6 = "";
            while (i < supportedPreviewFormats.size()) {
                translateToEngineFormat = VideoCapture.translateToEngineFormat(((Integer) supportedPreviewFormats.get(i)).intValue());
                if (i != supportedPreviewFormats.size() - 1) {
                    str = str6 + translateToEngineFormat + Constants.ACCEPT_TIME_SEPARATOR_SP;
                } else {
                    str = str6 + translateToEngineFormat;
                }
                i++;
                str6 = str;
            }
            String str7 = "\"fps\":";
            List supportedPreviewFrameRates = cameraParameters.getSupportedPreviewFrameRates();
            i = 0;
            String str8 = "";
            while (i < supportedPreviewFrameRates.size()) {
                translateToEngineFormat = ((Integer) supportedPreviewFrameRates.get(i)).intValue();
                if (i != supportedPreviewFrameRates.size() - 1) {
                    str = str8 + translateToEngineFormat + Constants.ACCEPT_TIME_SEPARATOR_SP;
                } else {
                    str = str8 + translateToEngineFormat;
                }
                i++;
                str8 = str;
            }
            str = "{" + str2 + str3 + "[" + str4 + "]," + str5 + "[" + str6 + "]," + str7 + "[" + str8 + "]}";
        }
        VideoCapture.cacheCapability(this.mId, this.mContext, str);
        return 0;
    }
}
