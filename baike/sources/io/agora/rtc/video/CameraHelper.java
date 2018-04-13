package io.agora.rtc.video;

import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import io.agora.rtc.internal.Logging;
import java.util.ArrayList;
import java.util.List;

public class CameraHelper {
    private static final String TAG = "CameraHelper";

    public static class Capability {
        public static final int CAMERA_FACING_BACK = 0;
        public static final int CAMERA_FACING_FRONT = 1;
        public int facing;
        public int height;
        public int id;
        public int maxFps;
        public int width;

        public Capability(int i, int i2, int i3, int i4, int i5) {
            this.id = i;
            this.facing = i2;
            this.width = i3;
            this.height = i4;
            this.maxFps = i5;
        }
    }

    public static boolean checkPermission() {
        return true;
    }

    public static Capability createCapability(int i, int i2, Parameters parameters) {
        List<Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        List supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
        if (supportedPreviewSizes.isEmpty() || supportedPreviewFpsRange.isEmpty()) {
            Logging.e(TAG, "failed get preview size/fps, parameters = " + parameters.flatten());
            throw new IllegalArgumentException(parameters.flatten());
        }
        Size size = (Size) supportedPreviewSizes.get(0);
        Size size2 = size;
        for (Size size3 : supportedPreviewSizes) {
            if (size3.width * size3.height <= size2.width * size2.height) {
                size3 = size2;
            }
            size2 = size3;
        }
        int i3 = ((int[]) supportedPreviewFpsRange.get(0))[1] / 1000;
        Logging.d(TAG, "creaet capability for camera " + i + " : width: " + size2.width + " , height: " + size2.height + " max fps: " + i3);
        return new Capability(i, i2, size2.width, size2.height, i3);
    }

    public static synchronized List<Capability> getCameraCapability() {
        List<Capability> arrayList;
        synchronized (CameraHelper.class) {
            arrayList = new ArrayList();
            int numberOfCameras = Camera.getNumberOfCameras();
            if (numberOfCameras < 1) {
                throw new RuntimeException("no camera device");
            }
            int i = 0;
            while (i < numberOfCameras) {
                CameraInfo cameraInfo = new CameraInfo();
                Camera.getCameraInfo(i, cameraInfo);
                try {
                    Camera open = Camera.open(i);
                    arrayList.add(createCapability(i, cameraInfo.facing, open.getParameters()));
                    open.release();
                    i++;
                } catch (RuntimeException e) {
                    throw e;
                }
            }
        }
        return arrayList;
    }
}
