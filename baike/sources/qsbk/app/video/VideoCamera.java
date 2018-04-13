package qsbk.app.video;

import android.annotation.TargetApi;
import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.media.CamcorderProfile;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import qsbk.app.utils.DebugUtil;

@TargetApi(9)
public class VideoCamera {
    private static final String a = VideoCamera.class.getSimpleName();
    private int b = -1;
    private int c = 0;
    private boolean d = false;
    private int e = 0;
    private int f = 0;
    public Camera mCamera;

    public synchronized boolean openCamera() {
        boolean z = false;
        synchronized (this) {
            if (this.mCamera == null) {
                try {
                    DebugUtil.debug(a, "openCamera mCameraFacing:" + this.c);
                    int numberOfCameras = Camera.getNumberOfCameras();
                    CameraInfo cameraInfo = new CameraInfo();
                    for (int i = 0; i < numberOfCameras; i++) {
                        Camera.getCameraInfo(i, cameraInfo);
                        if (cameraInfo.facing == this.c) {
                            this.b = i;
                            break;
                        }
                    }
                    if (this.b >= 0) {
                        this.mCamera = Camera.open(this.b);
                    } else {
                        this.mCamera = Camera.open();
                    }
                    z = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return z;
    }

    public synchronized void closeCamera() {
        if (this.mCamera != null) {
            DebugUtil.debug(a, "closeCamera mIsPreviewOn:" + this.d);
            stopPreview();
            this.mCamera.release();
            this.mCamera = null;
        }
    }

    public void startPreview() {
        if (this.mCamera != null) {
            DebugUtil.debug(a, "startPreview");
            if (this.d) {
                stopPreview();
            }
            this.d = true;
            this.mCamera.startPreview();
        }
    }

    public void stopPreview() {
        if (this.mCamera != null && this.d) {
            DebugUtil.debug(a, "stopPreview");
            this.mCamera.stopPreview();
        }
        this.d = false;
    }

    public void setPreviewDisplay(SurfaceHolder surfaceHolder) {
        if (this.mCamera != null) {
            try {
                this.mCamera.setPreviewDisplay(surfaceHolder);
            } catch (IOException e) {
                e.printStackTrace();
                closeCamera();
            }
        }
    }

    public void unlock() {
        if (this.mCamera != null) {
            this.mCamera.unlock();
        }
    }

    public void lock() {
        if (this.mCamera != null) {
            this.mCamera.lock();
        }
    }

    private Parameters a() {
        if (this.mCamera != null) {
            return this.mCamera.getParameters();
        }
        return null;
    }

    public void setParameters() {
        Parameters a = a();
        if (a != null) {
            Object c = c();
            if (!TextUtils.isEmpty(c)) {
                a.setFocusMode(c);
            }
            CamcorderProfile camcorderProfile = CamcorderProfile.get(4);
            Size a2 = a(camcorderProfile.videoFrameWidth, camcorderProfile.videoFrameHeight);
            if (a2 != null) {
                a.setPreviewSize(a2.width, a2.height);
            }
            a.setRotation(90);
            this.mCamera.setParameters(a);
        }
    }

    private List<Size> b() {
        if (this.mCamera != null) {
            return this.mCamera.getParameters().getSupportedPreviewSizes();
        }
        return null;
    }

    private String c() {
        String str = "";
        Parameters a = a();
        if (a == null) {
            return str;
        }
        String str2;
        List supportedFocusModes = a.getSupportedFocusModes();
        int i = 0;
        while (i < supportedFocusModes.size()) {
            if ("auto".equalsIgnoreCase((String) supportedFocusModes.get(i))) {
                str2 = "auto";
                break;
            } else if ("continuous-video".equalsIgnoreCase((String) supportedFocusModes.get(i))) {
                str2 = "continuous-video";
                break;
            } else if (i == supportedFocusModes.size() - 1) {
                str2 = (String) supportedFocusModes.get(0);
                break;
            } else {
                i++;
            }
        }
        str2 = str;
        return str2;
    }

    private Size a(int i, int i2) {
        Size size;
        Object obj = null;
        List b = b();
        if (b == null || b.size() <= 0) {
            size = null;
        } else {
            Collections.sort(b, new p(this));
            for (int i3 = 0; i3 < b.size(); i3++) {
                size = (Size) b.get(i3);
                if (size != null && size.width == i && size.height == i2) {
                    obj = 1;
                    break;
                }
            }
            size = null;
            if (obj == null) {
                size = (Size) b.get(b.size() / 2);
            }
        }
        if (size != null) {
            this.e = size.width;
            this.f = size.height;
        }
        DebugUtil.debug(a, "mPreviewWidth:" + this.e + "  mPreviewHeight:" + this.f);
        return size;
    }

    public int getPreviewWidth() {
        return this.e;
    }

    public int getPreviewHeight() {
        return this.f;
    }

    public void setFlashState(boolean z) {
        if (this.mCamera != null) {
            Parameters a = a();
            if (a != null) {
                this.mCamera.cancelAutoFocus();
                if (z) {
                    a.setFlashMode("torch");
                } else {
                    a.setFlashMode("off");
                }
                this.mCamera.setParameters(a);
                this.mCamera.autoFocus(null);
            }
        }
    }

    public void overturnCameraFacing() {
        this.c = this.c == 0 ? 1 : 0;
    }

    public int getCameraFacing() {
        return this.c;
    }

    public void setDisplayOrientation(Activity activity) {
        if (this.mCamera != null) {
            this.mCamera.setDisplayOrientation(determineDisplayOrientation(activity));
        }
    }

    public int determineDisplayOrientation(Activity activity) {
        int i = 0;
        if (VERSION.SDK_INT > 8) {
            CameraInfo cameraInfo = new CameraInfo();
            Camera.getCameraInfo(this.b, cameraInfo);
            int a = a(activity);
            if (cameraInfo.facing == 1) {
                i = (360 - ((cameraInfo.orientation + a) % 360)) % 360;
            } else {
                i = ((cameraInfo.orientation - a) + 360) % 360;
            }
            DebugUtil.debug(a, "displayOrientation:" + i);
        }
        return i;
    }

    private int a(Activity activity) {
        switch (activity.getWindowManager().getDefaultDisplay().getRotation()) {
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return 270;
            default:
                return 0;
        }
    }
}
