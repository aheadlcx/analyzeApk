package qsbk.app.ye.videotools.camera;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import com.xiaomi.mipush.sdk.Constants;
import java.util.List;
import qsbk.app.ye.videotools.utils.SystemUtils;

public class CameraHelper {
    private Activity a = null;

    public CameraHelper(Activity activity) {
        this.a = activity;
    }

    public int getNumberOfCameras() {
        return Camera.getNumberOfCameras();
    }

    public Camera openCamera(int i) {
        return Camera.open(i);
    }

    public Camera openDefaultCamera() {
        return Camera.open();
    }

    public Camera openFrontCamera() {
        return Camera.open(a(1));
    }

    public Camera openBackCamera() {
        return Camera.open(a(0));
    }

    public int hasFrontCamera() {
        return a(1);
    }

    public int hasBackCamera() {
        return a(0);
    }

    public void getCameraInfo(int i, CameraInfo cameraInfo) {
        Camera.getCameraInfo(i, cameraInfo);
    }

    public int setCameraDisplayOrientation(int i, Camera camera) {
        int cameraDisplayOrientation = getCameraDisplayOrientation(i);
        camera.setDisplayOrientation(cameraDisplayOrientation);
        return cameraDisplayOrientation;
    }

    public int getCameraDisplayOrientation(int i) {
        int i2 = 0;
        switch (this.a.getWindowManager().getDefaultDisplay().getRotation()) {
            case 1:
                i2 = 90;
                break;
            case 2:
                i2 = 180;
                break;
            case 3:
                i2 = 270;
                break;
        }
        CameraInfo cameraInfo = new CameraInfo();
        getCameraInfo(i, cameraInfo);
        if (cameraInfo.facing == 1) {
            return (360 - ((i2 + cameraInfo.orientation) % 360)) % 360;
        }
        return ((cameraInfo.orientation - i2) + 360) % 360;
    }

    public static String getCameraInfo() {
        StringBuilder stringBuilder = new StringBuilder(2048);
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera camera = null;
            try {
                camera = Camera.open(i);
                Parameters parameters = camera.getParameters();
                List<Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
                List<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
                List<Integer> supportedPreviewFrameRates = parameters.getSupportedPreviewFrameRates();
                stringBuilder.append(i);
                stringBuilder.append(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
                for (Size size : supportedPreviewSizes) {
                    stringBuilder.append(Integer.toHexString(size.width));
                    stringBuilder.append(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
                    stringBuilder.append(Integer.toHexString(size.height));
                    stringBuilder.append(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
                }
                stringBuilder.append(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
                for (int[] iArr : supportedPreviewFpsRange) {
                    stringBuilder.append(Integer.toHexString(iArr[0]));
                    stringBuilder.append(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
                    stringBuilder.append(Integer.toHexString(iArr[1]));
                    stringBuilder.append(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
                }
                stringBuilder.append(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
                for (Integer intValue : supportedPreviewFrameRates) {
                    stringBuilder.append(Integer.toHexString(intValue.intValue()));
                    stringBuilder.append(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
                }
                stringBuilder.append("--");
                if (camera != null) {
                    camera.release();
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
                if (camera != null) {
                    camera.release();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (camera != null) {
                    camera.release();
                }
            } catch (Throwable th) {
                if (camera != null) {
                    camera.release();
                }
            }
        }
        stringBuilder.append(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
        stringBuilder.append(SystemUtils.getProcessors());
        stringBuilder.append(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
        stringBuilder.append(Integer.toHexString(SystemUtils.getFrequency()));
        return stringBuilder.toString();
    }

    private int a(int i) {
        int numberOfCameras = Camera.getNumberOfCameras();
        CameraInfo cameraInfo = new CameraInfo();
        for (int i2 = 0; i2 < numberOfCameras; i2++) {
            Camera.getCameraInfo(i2, cameraInfo);
            if (cameraInfo.facing == i) {
                return i2;
            }
        }
        return -1;
    }
}
