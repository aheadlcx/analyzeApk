package io.agora.rtc.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCaptureSession.CaptureCallback;
import android.hardware.camera2.CameraCaptureSession.StateCallback;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraManager.AvailabilityCallback;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureRequest.Builder;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.Face;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.Image.Plane;
import android.media.ImageReader;
import android.media.ImageReader.OnImageAvailableListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Size;
import com.alipay.sdk.util.h;
import com.xiaomi.mipush.sdk.Constants;
import io.agora.rtc.internal.Logging;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@TargetApi(21)
public class VideoCaptureCamera2 extends VideoCapture {
    private static final boolean DEBUG = false;
    private static final String TAG = "CAMERA2";
    private static final MeteringRectangle[] ZERO_WEIGHT_3A_REGION = new MeteringRectangle[]{new MeteringRectangle(0, 0, 0, 0, 0)};
    private static final double kNanoSecondsToFps = 1.0E-9d;
    private MeteringRectangle[] mAFAERegions = ZERO_WEIGHT_3A_REGION;
    public AvailabilityCallback mAvailabilityCallback = new AvailabilityCallback() {
        public synchronized void onCameraAvailable(String str) {
            super.onCameraAvailable(str);
            if (VideoCaptureCamera2.this.mCameraState == CameraState.EVICTED && VideoCaptureCamera2.this.tryOpenCamera() != 0) {
                Logging.e(VideoCaptureCamera2.TAG, "start capture failed");
            }
        }

        public synchronized void onCameraUnavailable(String str) {
            super.onCameraUnavailable(str);
            Logging.e(VideoCaptureCamera2.TAG, "Camera " + str + " unavailable");
        }
    };
    private CameraDevice mCameraDevice = null;
    private CameraState mCameraState = CameraState.STOPPED;
    private final Object mCameraStateLock = new Object();
    private final CaptureCallback mCaptureCallback = new CaptureCallback() {
        private long mLastFocusedTs;

        private void process(CaptureResult captureResult) {
            Face[] faceArr = (Face[]) captureResult.get(CaptureResult.STATISTICS_FACES);
            if (faceArr == null || faceArr.length <= 0) {
                VideoCaptureCamera2.this.mAFAERegions = VideoCaptureCamera2.ZERO_WEIGHT_3A_REGION;
            } else if (System.currentTimeMillis() - this.mLastFocusedTs >= 3000 && faceArr[0].getScore() > 50) {
                VideoCaptureCamera2.this.mAFAERegions = new MeteringRectangle[]{new MeteringRectangle(faceArr[0].getBounds(), 1000)};
                VideoCaptureCamera2.this.addRegionsToCaptureRequestBuilder(VideoCaptureCamera2.this.mPreviewBuilder);
                if (VideoCaptureCamera2.this.mCameraState == CameraState.STARTED) {
                    try {
                        VideoCaptureCamera2.this.mCaptureSession.capture(VideoCaptureCamera2.this.mPreviewBuilder.build(), VideoCaptureCamera2.this.mCaptureCallback, null);
                        VideoCaptureCamera2.this.createCaptureRequest();
                        this.mLastFocusedTs = System.currentTimeMillis();
                    } catch (Exception e) {
                        Logging.e(VideoCaptureCamera2.TAG, "capture: " + e);
                    }
                }
            }
        }

        public void onCaptureProgressed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureResult captureResult) {
            process(captureResult);
        }

        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            process(totalCaptureResult);
        }
    };
    private byte[] mCaptureData;
    private int mCaptureFormat = 35;
    private int mCaptureFps = -1;
    private int mCaptureHeight = -1;
    private CameraCaptureSession mCaptureSession = null;
    private int mCaptureWidth = -1;
    private int mExpectedFrameSize = 0;
    private int mFaceDetectMode;
    private boolean mFaceDetectSupported;
    private ImageReader mImageReader = null;
    private Handler mMainHandler = new Handler(this.mContext.getMainLooper());
    private CameraManager mManager = null;
    private Builder mPreviewBuilder = null;
    private HandlerThread mPreviewThread = null;

    private enum CameraState {
        OPENING,
        STARTED,
        EVICTED,
        STOPPED
    }

    private class CaptureSessionListener extends StateCallback {
        private CaptureSessionListener() {
        }

        public void onConfigured(CameraCaptureSession cameraCaptureSession) {
            VideoCaptureCamera2.this.mCaptureSession = cameraCaptureSession;
            if (VideoCaptureCamera2.this.createCaptureRequest() != 0) {
                VideoCaptureCamera2.this.changeCameraStateAndNotify(CameraState.STOPPED);
                VideoCaptureCamera2.this.onCameraError(VideoCaptureCamera2.this.mNativeVideoCaptureDeviceAndroid, "Fail to setup capture session");
                return;
            }
            VideoCaptureCamera2.this.changeCameraStateAndNotify(CameraState.STARTED);
        }

        public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
            Logging.e(VideoCaptureCamera2.TAG, "onConfigureFailed");
            if (VideoCaptureCamera2.this.mCameraState != CameraState.EVICTED) {
                VideoCaptureCamera2.this.changeCameraStateAndNotify(CameraState.STOPPED);
            }
            VideoCaptureCamera2.this.onCameraError(VideoCaptureCamera2.this.mNativeVideoCaptureDeviceAndroid, "Camera session configuration error");
        }
    }

    private class CrStateListener extends CameraDevice.StateCallback {
        private CrStateListener() {
        }

        public void onOpened(CameraDevice cameraDevice) {
            VideoCaptureCamera2.this.mCameraDevice = cameraDevice;
            if (VideoCaptureCamera2.this.doStartCapture() < 0) {
                if (VideoCaptureCamera2.this.mCameraState != CameraState.EVICTED) {
                    VideoCaptureCamera2.this.changeCameraStateAndNotify(CameraState.STOPPED);
                }
                Logging.e(VideoCaptureCamera2.TAG, "Camera startCapture failed!!");
                VideoCaptureCamera2.this.onCameraError(VideoCaptureCamera2.this.mNativeVideoCaptureDeviceAndroid, "Error configuring camera");
            }
        }

        public void onDisconnected(CameraDevice cameraDevice) {
            if (VideoCaptureCamera2.this.mCameraState != CameraState.STOPPED) {
                Logging.w(VideoCaptureCamera2.TAG, "camera client is evicted by other application");
                VideoCaptureCamera2.this.onCameraError(VideoCaptureCamera2.this.mNativeVideoCaptureDeviceAndroid, "Camera device evicted by other application");
                Logging.i(VideoCaptureCamera2.TAG, "Camera device enter state: EVICTED");
                if (VideoCaptureCamera2.this.mCameraDevice != null) {
                    VideoCaptureCamera2.this.mCameraDevice.close();
                    VideoCaptureCamera2.this.mCameraDevice = null;
                }
                VideoCaptureCamera2.this.changeCameraStateAndNotify(CameraState.EVICTED);
            }
        }

        public void onError(CameraDevice cameraDevice, int i) {
            if (VideoCaptureCamera2.this.mCameraState != CameraState.EVICTED) {
                if (VideoCaptureCamera2.this.mCameraDevice != null) {
                    VideoCaptureCamera2.this.mCameraDevice.close();
                    VideoCaptureCamera2.this.mCameraDevice = null;
                }
                VideoCaptureCamera2.this.changeCameraStateAndNotify(CameraState.STOPPED);
                Logging.e(VideoCaptureCamera2.TAG, "CameraDevice Error :" + Integer.toString(i));
                VideoCaptureCamera2.this.onCameraError(VideoCaptureCamera2.this.mNativeVideoCaptureDeviceAndroid, "Camera device error" + Integer.toString(i));
            }
        }
    }

    private class ImageReaderListener implements OnImageAvailableListener {
        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onImageAvailable(android.media.ImageReader r7) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x000c in list [B:6:0x0009]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r6 = this;
            r1 = 0;
            r1 = r7.acquireLatestImage();	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            if (r1 != 0) goto L_0x000d;
        L_0x0007:
            if (r1 == 0) goto L_0x000c;
        L_0x0009:
            r1.close();
        L_0x000c:
            return;
        L_0x000d:
            r0 = r1.getFormat();	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2 = 35;	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            if (r0 != r2) goto L_0x001d;	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
        L_0x0015:
            r0 = r1.getPlanes();	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r0 = r0.length;	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2 = 3;	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            if (r0 == r2) goto L_0x004e;	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
        L_0x001d:
            r0 = "CAMERA2";	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2 = new java.lang.StringBuilder;	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2.<init>();	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r3 = "Unexpected image format: ";	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2 = r2.append(r3);	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r3 = r1.getFormat();	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2 = r2.append(r3);	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r3 = "or #planes:";	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2 = r2.append(r3);	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r3 = r1.getPlanes();	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r3 = r3.length;	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2 = r2.append(r3);	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2 = r2.toString();	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            io.agora.rtc.internal.Logging.e(r0, r2);	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            if (r1 == 0) goto L_0x000c;
        L_0x004a:
            r1.close();
            goto L_0x000c;
        L_0x004e:
            r0 = r7.getWidth();	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2 = r1.getWidth();	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            if (r0 != r2) goto L_0x0062;	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
        L_0x0058:
            r0 = r7.getHeight();	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2 = r1.getHeight();	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            if (r0 == r2) goto L_0x00b8;	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
        L_0x0062:
            r0 = new java.lang.IllegalStateException;	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2 = new java.lang.StringBuilder;	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2.<init>();	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r3 = "ImageReader size ";	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2 = r2.append(r3);	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r3 = r7.getWidth();	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2 = r2.append(r3);	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r3 = "x";	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2 = r2.append(r3);	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r3 = r7.getHeight();	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2 = r2.append(r3);	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r3 = " did not match Image size: ";	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2 = r2.append(r3);	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r3 = r1.getWidth();	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2 = r2.append(r3);	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r3 = "x";	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2 = r2.append(r3);	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r3 = r1.getHeight();	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2 = r2.append(r3);	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2 = r2.toString();	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r0.<init>(r2);	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            throw r0;	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
        L_0x00a9:
            r0 = move-exception;
            r2 = "CAMERA2";	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r3 = "acquireLastest Image():";	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            io.agora.rtc.internal.Logging.e(r2, r3, r0);	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            if (r1 == 0) goto L_0x000c;
        L_0x00b3:
            r1.close();
            goto L_0x000c;
        L_0x00b8:
            r0 = io.agora.rtc.video.VideoCaptureCamera2.this;	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r0 = r0.mCaptureData;	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            io.agora.rtc.video.VideoCaptureCamera2.readImageIntoBuffer(r1, r0);	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r0 = io.agora.rtc.video.VideoCaptureCamera2.this;	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2 = io.agora.rtc.video.VideoCaptureCamera2.this;	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r2 = r2.mCaptureData;	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r3 = io.agora.rtc.video.VideoCaptureCamera2.this;	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r3 = r3.mExpectedFrameSize;	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r4 = io.agora.rtc.video.VideoCaptureCamera2.this;	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r4 = r4.mNativeVideoCaptureDeviceAndroid;	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            r0.ProvideCameraFrame(r2, r3, r4);	 Catch:{ IllegalStateException -> 0x00a9, all -> 0x00dd }
            if (r1 == 0) goto L_0x000c;
        L_0x00d8:
            r1.close();
            goto L_0x000c;
        L_0x00dd:
            r0 = move-exception;
            if (r1 == 0) goto L_0x00e3;
        L_0x00e0:
            r1.close();
        L_0x00e3:
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.agora.rtc.video.VideoCaptureCamera2.ImageReaderListener.onImageAvailable(android.media.ImageReader):void");
        }

        private ImageReaderListener() {
        }
    }

    private static CameraCharacteristics getCameraCharacteristics(Context context, int i) {
        try {
            return ((CameraManager) context.getSystemService("camera")).getCameraCharacteristics(Integer.toString(i));
        } catch (CameraAccessException e) {
            Logging.i(TAG, "getNumberOfCameras: getCameraIdList(): " + e);
            return null;
        }
    }

    static boolean isLegacyDevice(Context context, int i) {
        try {
            CameraCharacteristics cameraCharacteristics = getCameraCharacteristics(context, i);
            return cameraCharacteristics != null && ((Integer) cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue() == 2;
        } catch (Exception e) {
            Logging.w(TAG, "this is a legacy camera device");
            return true;
        }
    }

    static int getNumberOfCameras(Context context) {
        try {
            return ((CameraManager) context.getSystemService("camera")).getCameraIdList().length;
        } catch (Throwable e) {
            Logging.e(TAG, "getNumberOfCameras: getCameraIdList(): ", e);
            return 0;
        }
    }

    static String getName(int i, Context context) {
        CameraCharacteristics cameraCharacteristics = getCameraCharacteristics(context, i);
        if (cameraCharacteristics == null) {
            return null;
        }
        return "camera2 " + i + ", facing " + (((Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING)).intValue() == 0 ? "front" : "back");
    }

    static int getSensorOrientation(int i, Context context) {
        CameraCharacteristics cameraCharacteristics = getCameraCharacteristics(context, i);
        if (cameraCharacteristics == null) {
            return -1;
        }
        return ((Integer) cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
    }

    VideoCaptureCamera2(Context context, int i, long j) {
        super(context, i, j);
    }

    private void changeCameraStateAndNotify(CameraState cameraState) {
        synchronized (this.mCameraStateLock) {
            this.mCameraState = cameraState;
            this.mCameraStateLock.notifyAll();
        }
    }

    private int createCaptureRequest() {
        try {
            this.mCaptureSession.setRepeatingRequest(this.mPreviewBuilder.build(), this.mCaptureCallback, null);
            return 0;
        } catch (Throwable e) {
            Logging.e(TAG, "setRepeatingRequest: ", e);
            return -1;
        } catch (Throwable e2) {
            Logging.e(TAG, "setRepeatingRequest: ", e2);
            return -2;
        } catch (Throwable e22) {
            Logging.e(TAG, "setRepeatingRequest: ", e22);
            return -3;
        } catch (IllegalStateException e3) {
            Logging.e(TAG, "capture:" + e3);
            return -4;
        }
    }

    private static void readImageIntoBuffer(Image image, byte[] bArr) {
        int width = image.getWidth();
        int height = image.getHeight();
        Plane[] planes = image.getPlanes();
        int i = 0;
        int i2 = 0;
        while (i2 < planes.length) {
            ByteBuffer buffer = planes[i2].getBuffer();
            int rowStride = planes[i2].getRowStride();
            int pixelStride = planes[i2].getPixelStride();
            int i3 = i2 == 0 ? width : width / 2;
            int i4 = i2 == 0 ? height : height / 2;
            if (pixelStride == 1 && rowStride == i3) {
                buffer.get(bArr, i, i3 * i4);
                i += i3 * i4;
            } else {
                int i5;
                byte[] bArr2 = new byte[rowStride];
                int i6 = i;
                for (int i7 = 0; i7 < i4 - 1; i7++) {
                    buffer.get(bArr2, 0, rowStride);
                    i = 0;
                    while (i < i3) {
                        i5 = i6 + 1;
                        bArr[i6] = bArr2[i * pixelStride];
                        i++;
                        i6 = i5;
                    }
                }
                buffer.get(bArr2, 0, Math.min(rowStride, buffer.remaining()));
                i = i6;
                i6 = 0;
                while (i6 < i3) {
                    i5 = i + 1;
                    bArr[i] = bArr2[i6 * pixelStride];
                    i6++;
                    i = i5;
                }
            }
            i2++;
        }
    }

    private int tryOpenCamera() {
        try {
            this.mManager.openCamera(Integer.toString(this.mId), new CrStateListener(), this.mMainHandler);
            return 0;
        } catch (Throwable e) {
            Logging.e(TAG, "allocate: manager.openCamera: ", e);
            return -1;
        } catch (Throwable e2) {
            Logging.e(TAG, "allocate: manager.openCamera: ", e2);
            return -2;
        } catch (Throwable e22) {
            Logging.e(TAG, "allocate: manager.openCamera: ", e22);
            return -3;
        } catch (Throwable e222) {
            Logging.e(TAG, "unknown error", e222);
            return -4;
        }
    }

    private void addRegionsToCaptureRequestBuilder(Builder builder) {
        builder.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(2));
        builder.set(CaptureRequest.CONTROL_AE_REGIONS, this.mAFAERegions);
        builder.set(CaptureRequest.CONTROL_AF_REGIONS, this.mAFAERegions);
        builder.set(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(1));
        builder.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(0));
        builder.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(1));
    }

    private int doStartCapture() {
        this.mExpectedFrameSize = ((this.mCaptureWidth * this.mCaptureHeight) * ImageFormat.getBitsPerPixel(this.mCaptureFormat)) / 8;
        this.mCaptureData = new byte[this.mExpectedFrameSize];
        this.mImageReader = ImageReader.newInstance(this.mCaptureWidth, this.mCaptureHeight, this.mCaptureFormat, 2);
        if (this.mPreviewThread == null) {
            this.mPreviewThread = new HandlerThread("CameraPreview");
            this.mPreviewThread.start();
        }
        Handler handler = new Handler(this.mPreviewThread.getLooper());
        this.mImageReader.setOnImageAvailableListener(new ImageReaderListener(), handler);
        try {
            this.mPreviewBuilder = this.mCameraDevice.createCaptureRequest(1);
            if (this.mPreviewBuilder == null) {
                Logging.e(TAG, "mPreviewBuilder error");
                return -4;
            }
            this.mPreviewBuilder.addTarget(this.mImageReader.getSurface());
            this.mPreviewBuilder.set(CaptureRequest.CONTROL_MODE, Integer.valueOf(1));
            setFaceDetect(this.mPreviewBuilder, this.mFaceDetectMode);
            List arrayList = new ArrayList(1);
            arrayList.add(this.mImageReader.getSurface());
            try {
                this.mCameraDevice.createCaptureSession(arrayList, new CaptureSessionListener(), null);
                return 0;
            } catch (Throwable e) {
                Logging.e(TAG, "createCaptureSession :", e);
                return -1;
            } catch (Throwable e2) {
                Logging.e(TAG, "createCaptureSession :", e2);
                return -2;
            } catch (Throwable e22) {
                Logging.e(TAG, "createCaptureSession :", e22);
                return -3;
            }
        } catch (Throwable e3) {
            Logging.e(TAG, "createCaptureRequest: ", e3);
            return -1;
        } catch (Throwable e222) {
            Logging.e(TAG, "createCaptureRequest: ", e222);
            return -2;
        } catch (Throwable e2222) {
            Logging.e(TAG, "createCaptureRequest ", e2222);
            return -3;
        }
    }

    private int doStopCapture() {
        if (this.mPreviewThread != null) {
            this.mPreviewThread.quitSafely();
            this.mPreviewThread = null;
        }
        try {
            this.mCaptureSession.abortCaptures();
            if (this.mCameraDevice != null) {
                this.mCameraDevice.close();
                this.mCameraDevice = null;
            }
            return 0;
        } catch (Throwable e) {
            Logging.e(TAG, "abortCaptures: ", e);
            return -1;
        } catch (Throwable e2) {
            Logging.e(TAG, "abortCaptures: ", e2);
            return -1;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int allocate() {
        /*
        r7 = this;
        r2 = 0;
        r1 = r7.mCameraStateLock;
        monitor-enter(r1);
        r0 = r7.mCameraState;	 Catch:{ all -> 0x0074 }
        r3 = io.agora.rtc.video.VideoCaptureCamera2.CameraState.OPENING;	 Catch:{ all -> 0x0074 }
        if (r0 != r3) goto L_0x0014;
    L_0x000a:
        r0 = "CAMERA2";
        r2 = "allocate() invoked while Camera is busy opening/configuring";
        io.agora.rtc.internal.Logging.e(r0, r2);	 Catch:{ all -> 0x0074 }
        r0 = -1;
        monitor-exit(r1);	 Catch:{ all -> 0x0074 }
    L_0x0013:
        return r0;
    L_0x0014:
        monitor-exit(r1);	 Catch:{ all -> 0x0074 }
        r0 = r7.mContext;
        r1 = r7.mId;
        r1 = getCameraCharacteristics(r0, r1);
        r0 = r7.mId;
        r3 = r7.mContext;
        r0 = io.agora.rtc.video.VideoCapture.fetchCapability(r0, r3);
        if (r0 != 0) goto L_0x002e;
    L_0x0027:
        r0 = r7.mId;
        r3 = r7.mContext;
        createCapabilities(r0, r3);
    L_0x002e:
        r0 = android.hardware.camera2.CameraCharacteristics.SENSOR_ORIENTATION;
        r0 = r1.get(r0);
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r7.mCameraNativeOrientation = r0;
        r0 = r7.mContext;
        r3 = "camera";
        r0 = r0.getSystemService(r3);
        r0 = (android.hardware.camera2.CameraManager) r0;
        r7.mManager = r0;
        r0 = android.hardware.camera2.CameraCharacteristics.STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES;
        r0 = r1.get(r0);
        r0 = (int[]) r0;
        r3 = android.hardware.camera2.CameraCharacteristics.STATISTICS_INFO_MAX_FACE_COUNT;
        r1 = r1.get(r3);
        r1 = (java.lang.Integer) r1;
        r3 = r1.intValue();
        r1 = r0.length;
        if (r1 <= 0) goto L_0x0088;
    L_0x005f:
        r4 = new java.util.ArrayList;
        r4.<init>();
        r5 = r0.length;
        r1 = r2;
    L_0x0066:
        if (r1 >= r5) goto L_0x0077;
    L_0x0068:
        r6 = r0[r1];
        r6 = java.lang.Integer.valueOf(r6);
        r4.add(r6);
        r1 = r1 + 1;
        goto L_0x0066;
    L_0x0074:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0074 }
        throw r0;
    L_0x0077:
        if (r3 <= 0) goto L_0x0088;
    L_0x0079:
        r0 = 1;
        r7.mFaceDetectSupported = r0;
        r0 = java.util.Collections.max(r4);
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r7.mFaceDetectMode = r0;
    L_0x0088:
        r0 = "CAMERA2";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r4 = "allocate() face detection: ";
        r1 = r1.append(r4);
        r4 = r7.mFaceDetectMode;
        r1 = r1.append(r4);
        r4 = " ";
        r1 = r1.append(r4);
        r1 = r1.append(r3);
        r3 = " ";
        r1 = r1.append(r3);
        r3 = r7.mFaceDetectSupported;
        r1 = r1.append(r3);
        r1 = r1.toString();
        io.agora.rtc.internal.Logging.i(r0, r1);
        r0 = r7.mManager;
        r1 = r7.mAvailabilityCallback;
        r3 = r7.mMainHandler;
        r0.registerAvailabilityCallback(r1, r3);
        r0 = r2;
        goto L_0x0013;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.agora.rtc.video.VideoCaptureCamera2.allocate():int");
    }

    public int setCaptureFormat(int i) {
        if (VideoCapture.translateToAndroidFormat(i) == this.mCaptureFormat) {
            return 0;
        }
        Logging.e(TAG, "For camera2 api, only YUV_420_888 format are supported");
        return -1;
    }

    public int startCapture(int i, int i2, int i3) {
        Logging.d(TAG, "startCapture, w=" + i + ", h=" + i2 + ", fps=" + i3);
        this.mCaptureWidth = i;
        this.mCaptureHeight = i2;
        this.mCaptureFps = i3;
        synchronized (this.mCameraStateLock) {
            while (this.mCameraState != CameraState.STARTED && this.mCameraState != CameraState.EVICTED && this.mCameraState != CameraState.STOPPED) {
                try {
                    this.mCameraStateLock.wait();
                } catch (Throwable e) {
                    Logging.e(TAG, "CaptureStartedEvent: ", e);
                }
            }
            if (this.mCameraState == CameraState.STARTED) {
                return 0;
            }
            changeCameraStateAndNotify(CameraState.OPENING);
            return tryOpenCamera();
        }
    }

    public int stopCapture() {
        synchronized (this.mCameraStateLock) {
            while (this.mCameraState != CameraState.STARTED && this.mCameraState != CameraState.EVICTED && this.mCameraState != CameraState.STOPPED) {
                try {
                    this.mCameraStateLock.wait();
                } catch (Throwable e) {
                    Logging.e(TAG, "CaptureStartedEvent: ", e);
                }
            }
            if (this.mCameraState == CameraState.EVICTED) {
                this.mCameraState = CameraState.STOPPED;
            }
            if (this.mCameraState == CameraState.STOPPED) {
            } else {
                doStopCapture();
                changeCameraStateAndNotify(CameraState.STOPPED);
            }
        }
        return 0;
    }

    public void deallocate() {
        this.mManager.unregisterAvailabilityCallback(this.mAvailabilityCallback);
    }

    private void setFaceDetect(Builder builder, int i) {
        if (this.mFaceDetectSupported) {
            builder.set(CaptureRequest.STATISTICS_FACE_DETECT_MODE, Integer.valueOf(i));
        }
    }

    public static int createCapabilities(int i, Context context) {
        StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) getCameraCharacteristics(context, i).get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap == null) {
            Logging.e(TAG, "Failed to create capabilities");
            return -1;
        }
        Logging.e(TAG, "dump configuration map:" + streamConfigurationMap.toString());
        List arrayList = new ArrayList(Arrays.asList(streamConfigurationMap.getOutputSizes(35)));
        String str = "\"id\":" + i + Constants.ACCEPT_TIME_SEPARATOR_SP;
        String str2 = "\"resolution\":";
        int i2 = 0;
        String str3 = "";
        while (i2 < arrayList.size()) {
            String str4 = "{\"w\":" + ((Size) arrayList.get(i2)).getWidth() + ",\"h\":" + ((Size) arrayList.get(i2)).getHeight() + h.d;
            if (i2 != arrayList.size() - 1) {
                str4 = str3 + str4 + Constants.ACCEPT_TIME_SEPARATOR_SP;
            } else {
                str4 = str3 + str4;
            }
            i2++;
            str3 = str4;
        }
        VideoCapture.cacheCapability(i, context, "{" + str + str2 + "[" + str3 + "]," + "\"format\":" + "[" + ("" + VideoCapture.translateToEngineFormat(35)) + "]," + "\"fps\":" + "[" + "30" + "]}");
        return 0;
    }
}
