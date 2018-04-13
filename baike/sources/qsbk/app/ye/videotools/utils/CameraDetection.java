package qsbk.app.ye.videotools.utils;

import android.hardware.Camera;
import qsbk.app.ye.videotools.camera.CameraHelper;

public class CameraDetection {
    public static boolean detect() {
        boolean z = false;
        Camera camera = null;
        CameraHelper cameraHelper = new CameraHelper(null);
        int hasFrontCamera = cameraHelper.hasFrontCamera();
        if (hasFrontCamera == -1) {
            hasFrontCamera = 0;
        }
        try {
            camera = cameraHelper.openCamera(hasFrontCamera);
            camera.setParameters(camera.getParameters());
            camera.setPreviewCallback(null);
            camera.stopPreview();
            z = true;
            if (camera != null) {
                try {
                    camera.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (camera != null) {
                try {
                    camera.release();
                } catch (Exception e22) {
                    e22.printStackTrace();
                }
            }
        } catch (Throwable th) {
            if (camera != null) {
                try {
                    camera.release();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
        return z;
    }
}
