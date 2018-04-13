package qsbk.app.ye.videotools.camera;

import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera;
import android.hardware.Camera.Area;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.activity.publish.Publish_Image;
import qsbk.app.video.VideoEditActivity;
import qsbk.app.ye.videotools.utils.SystemUtils;

public class CameraLoader {
    private static final String a = CameraLoader.class.getSimpleName();
    private final a b = new a();
    private Handler c;
    private int d = 0;
    private Camera e = null;
    private CameraHelper f = null;
    private CameraRender g = null;
    private boolean h = false;
    private int i = VideoEditActivity.MAX_VIDEO_HEIGHT;
    private int j = VideoEditActivity.MAX_VIDEO_WIDTH;
    private int k = 15;
    private int[] l = null;
    private boolean m = false;
    private List<Area> n;
    private List<Area> o;
    private Matrix p = new Matrix();
    private int q = 0;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;
    private boolean u = false;
    private boolean v = false;
    private boolean w = false;
    private boolean x = false;
    private boolean y = true;
    private boolean z = false;

    private final class a implements AutoFocusCallback {
        final /* synthetic */ CameraLoader a;

        private a(CameraLoader cameraLoader) {
            this.a = cameraLoader;
        }

        public void onAutoFocus(boolean z, Camera camera) {
            if (this.a.q == 1) {
                if (z) {
                    this.a.q = 2;
                } else {
                    this.a.q = 3;
                }
                this.a.c.sendEmptyMessageDelayed(0, 3000);
            } else if (this.a.q != 0) {
            }
        }
    }

    private class b extends Handler {
        final /* synthetic */ CameraLoader a;

        public b(CameraLoader cameraLoader, Looper looper) {
            this.a = cameraLoader;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.a.b();
                    return;
                default:
                    return;
            }
        }
    }

    public CameraLoader(int i, CameraHelper cameraHelper, CameraRender cameraRender, Looper looper) {
        this.d = i;
        this.f = cameraHelper;
        this.g = cameraRender;
        if (SystemUtils.getProcessors() >= 8 && SystemUtils.getFrequency() >= 1500000) {
            this.h = true;
            Log.d(a, "++++++++++support 720p++++++++++");
        }
        this.c = new b(this, looper);
    }

    public void setLiveFlag() {
        this.x = true;
    }

    public void setPreviewSize(int i, int i2) {
        this.i = i;
        this.j = i2;
    }

    public void switchCamera() {
        releaseCamera();
        int numberOfCameras = this.f.getNumberOfCameras();
        if (numberOfCameras > 0) {
            this.d = (this.d + 1) % numberOfCameras;
        }
        setUpCamera();
    }

    public void releaseCamera() {
        this.q = 0;
        resetTouchFocus();
        this.c.removeMessages(0);
        if (this.e != null) {
            this.e.addCallbackBuffer(null);
            this.e.setPreviewCallbackWithBuffer(null);
            this.e.stopPreview();
            this.e.release();
            this.e = null;
        }
    }

    public boolean isCameraEnable() {
        return this.e != null;
    }

    public void setUpCamera() {
        this.e = a(this.d);
        if (this.e != null) {
            try {
                int i;
                boolean z;
                a(true);
                PreviewCallback previewCallback = this.g.getPreviewCallback();
                if (previewCallback != null) {
                    i = (((((this.i + 15) / 16) * 16) * 3) * (((this.j + 15) / 16) * 16)) / 2;
                    for (int i2 = 0; i2 < 10; i2++) {
                        this.e.addCallbackBuffer(new byte[i]);
                    }
                }
                i = this.f.setCameraDisplayOrientation(this.d, this.e);
                CameraInfo cameraInfo = new CameraInfo();
                this.f.getCameraInfo(this.d, cameraInfo);
                a(cameraInfo.facing == 1, i);
                if (cameraInfo.facing == 1) {
                    i = (360 - i) % 360;
                    z = true;
                } else {
                    z = false;
                }
                this.g.setFpsRange(this.l[0], this.l[1]);
                this.g.setRotation(i, false, z);
                this.g.setImageSize(this.i, this.j);
                try {
                    this.e.setPreviewTexture(this.g.getSurfaceTexture());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (previewCallback != null) {
                    this.e.setPreviewCallbackWithBuffer(previewCallback);
                }
                this.e.startPreview();
                this.q = 0;
            } catch (RuntimeException e2) {
                Log.e("CameraLoader", "setParameters failed!");
                e2.printStackTrace();
                this.e.release();
                this.e = null;
            }
        }
    }

    public int getCameraFacing() {
        return this.d;
    }

    public void focus(int i, int i2) {
        if (this.e != null) {
            if (this.t || this.u) {
                if (this.n != null && (this.q == 1 || this.q == 2 || this.q == 3)) {
                    b();
                }
                if (this.t) {
                    a(i, i2, this.g.getSurfaceTextureWidth(), this.g.getSurfaceTextureHeight());
                }
                if (this.u) {
                    b(i, i2, this.g.getSurfaceTextureWidth(), this.g.getSurfaceTextureHeight());
                }
                try {
                    a(false);
                    if (this.t) {
                        a();
                        return;
                    }
                    this.c.removeMessages(0);
                    this.c.sendEmptyMessageDelayed(0, 3000);
                } catch (RuntimeException e) {
                    Log.e("CameraLoader", "autofocus setParameters failed!");
                    e.printStackTrace();
                }
            }
        }
    }

    public void continueFocus(boolean z) {
        if (this.e != null) {
            this.y = z;
            if (this.v || this.w) {
                try {
                    String str;
                    a(false);
                    String str2 = "CameraLoader";
                    StringBuilder append = new StringBuilder().append("set ");
                    if (z) {
                        str = "continue";
                    } else {
                        str = "static";
                    }
                    Log.w(str2, append.append(str).append(" focus").toString());
                } catch (RuntimeException e) {
                    Log.e("CameraLoader", "continueFocus setParameters failed!");
                    e.printStackTrace();
                }
            }
        }
    }

    public void setGlobalFocusAndMetering(boolean z) {
        this.z = z;
    }

    public boolean getGlobalFocusAndMetering() {
        return this.z;
    }

    public void setFlashState(boolean z) {
        if (this.e != null) {
            this.e.cancelAutoFocus();
            this.q = 0;
            this.c.removeMessages(0);
            this.m = z;
            a(false);
        }
    }

    public boolean getFlashState() {
        return this.m;
    }

    private void a() {
        this.e.autoFocus(this.b);
        this.q = 1;
        this.c.removeMessages(0);
    }

    private void b() {
        resetTouchFocus();
        this.e.cancelAutoFocus();
        this.q = 0;
        this.c.removeMessages(0);
    }

    public void resetTouchFocus() {
        this.n = null;
        this.o = null;
    }

    private void a(int i, int i2, int i3, int i4) {
        if (this.n == null && !this.z) {
            this.n = new ArrayList();
            this.n.add(new Area(new Rect(), 1));
        }
        if (this.z) {
            this.n = null;
            return;
        }
        a((int) (((float) Math.min(this.i, this.j)) * 0.4f), i, i2, i3, i4, ((Area) this.n.get(0)).rect);
    }

    private void b(int i, int i2, int i3, int i4) {
        if (this.o == null && !this.z) {
            this.o = new ArrayList();
            this.o.add(new Area(new Rect(), 1));
        }
        if (this.z) {
            this.o = null;
            return;
        }
        a((int) (((float) Math.min(this.i, this.j)) * 0.6f), i, i2, i3, i4, ((Area) this.o.get(0)).rect);
    }

    private void a(int i, int i2, int i3, int i4, int i5, Rect rect) {
        int clamp = clamp(i2 - (i / 2), 0, i4 - i);
        int clamp2 = clamp(i3 - (i / 2), 0, i5 - i);
        RectF rectF = new RectF((float) clamp, (float) clamp2, (float) (clamp + i), (float) (clamp2 + i));
        this.p.mapRect(rectF);
        rectFToRect(rectF, rect);
    }

    private void a(boolean z, int i) {
        int surfaceTextureWidth = this.g.getSurfaceTextureWidth();
        int surfaceTextureHeight = this.g.getSurfaceTextureHeight();
        if (surfaceTextureWidth != 0 && surfaceTextureHeight != 0) {
            Matrix matrix = new Matrix();
            prepareMatrix(matrix, z, i, surfaceTextureWidth, surfaceTextureHeight);
            matrix.invert(this.p);
        }
    }

    public static void prepareMatrix(Matrix matrix, boolean z, int i, int i2, int i3) {
        matrix.setScale(z ? -1.0f : 1.0f, 1.0f);
        matrix.postRotate((float) i);
        RectF rectF = new RectF(0.0f, 0.0f, (float) i2, (float) i3);
        Matrix matrix2 = new Matrix();
        matrix2.setRectToRect(new RectF(-1000.0f, -1000.0f, 1000.0f, 1000.0f), rectF, ScaleToFit.FILL);
        matrix.setConcat(matrix2, matrix);
    }

    public static int clamp(int i, int i2, int i3) {
        if (i > i3) {
            return i3;
        }
        if (i < i2) {
            return i2;
        }
        return i;
    }

    public static void rectFToRect(RectF rectF, Rect rect) {
        rect.left = Math.round(rectF.left);
        rect.top = Math.round(rectF.top);
        rect.right = Math.round(rectF.right);
        rect.bottom = Math.round(rectF.bottom);
    }

    private void a(boolean z) {
        Parameters parameters = this.e.getParameters();
        if (z) {
            if (parameters.getSupportedFocusModes().contains("auto")) {
                this.r = true;
            } else {
                this.r = false;
            }
            if (parameters.getSupportedFocusModes().contains("macro")) {
                this.s = true;
            } else {
                this.s = false;
            }
            if (parameters.getSupportedFocusModes().contains("continuous-picture")) {
                this.v = true;
            } else {
                this.v = false;
            }
            if (parameters.getSupportedFocusModes().contains("continuous-video")) {
                this.w = true;
            } else {
                this.w = false;
            }
            if (parameters.getMaxNumFocusAreas() <= 0 || !this.r) {
                this.t = false;
            } else {
                this.t = true;
            }
            if (parameters.getMaxNumMeteringAreas() > 0) {
                this.u = true;
            } else {
                this.u = false;
            }
            a(parameters);
            this.k = b(15, parameters);
            this.l = a(this.k, parameters);
        }
        if (this.y) {
            if (this.v && !this.x) {
                parameters.setFocusMode("continuous-picture");
            } else if (this.w) {
                parameters.setFocusMode("continuous-video");
            } else if (this.v && this.x) {
                parameters.setFocusMode("continuous-picture");
            } else if (this.r) {
                parameters.setFocusMode("auto");
            }
        } else if (this.s) {
            parameters.setFocusMode("macro");
        } else if (this.r) {
            parameters.setFocusMode("auto");
        }
        parameters.setPreviewSize(this.i, this.j);
        parameters.setPreviewFrameRate(this.k);
        if (this.l != null) {
            parameters.setPreviewFpsRange(this.l[0], this.l[1]);
        }
        if (this.m) {
            if (parameters.getSupportedFlashModes() != null) {
                if (parameters.getSupportedFlashModes().contains("torch")) {
                    parameters.setFlashMode("torch");
                } else if (parameters.getSupportedFlashModes().contains("auto")) {
                    parameters.setFlashMode("auto");
                }
            }
        } else if (parameters.getSupportedFlashModes() != null && parameters.getSupportedFlashModes().contains("off")) {
            parameters.setFlashMode("off");
        }
        if (this.t) {
            parameters.setFocusAreas(this.n);
        }
        if (this.u) {
            parameters.setMeteringAreas(this.o);
        }
        parameters.setPreviewFormat(17);
        this.e.setParameters(parameters);
    }

    private Camera a(int i) {
        Camera camera = null;
        try {
            camera = this.f.openCamera(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return camera;
    }

    private void a(Parameters parameters) {
        Object obj;
        Object obj2;
        Object obj3;
        int i;
        int i2;
        int i3;
        Object obj4;
        Object obj5;
        int i4;
        float f;
        int i5;
        float abs;
        float f2;
        Object obj6;
        int i6;
        int i7;
        Object obj7;
        float abs2;
        List<Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        int i8 = ((Size) supportedPreviewSizes.get(0)).width;
        int i9 = ((Size) supportedPreviewSizes.get(0)).height;
        if (this.h) {
            obj = null;
            obj2 = null;
            Object obj8 = null;
            for (Size size : supportedPreviewSizes) {
                if (size.width == 1280 && size.height == 720) {
                    obj3 = obj8;
                    obj8 = obj2;
                    obj2 = 1;
                } else if (size.width == 960 && size.height == 544) {
                    obj3 = obj8;
                    i = 1;
                    obj2 = obj;
                } else if (size.width == 960 && size.height == Publish_Image.MIN_IMG_WIDTH) {
                    i2 = 1;
                    obj8 = obj2;
                    obj2 = obj;
                } else {
                    obj3 = obj8;
                    obj8 = obj2;
                    obj2 = obj;
                }
                obj = obj2;
                obj2 = obj8;
                obj8 = obj3;
            }
            if (obj8 != null) {
                i = Publish_Image.MIN_IMG_HEIGHT;
                i2 = Publish_Image.MIN_IMG_WIDTH;
                obj2 = 1;
            } else if (obj2 != null) {
                i = Publish_Image.MIN_IMG_HEIGHT;
                i2 = 544;
                obj2 = 1;
            } else if (obj != null) {
                i = 1280;
                i2 = 720;
                obj2 = 1;
            }
            if (obj2 != null) {
                i3 = i8;
                obj4 = null;
                obj5 = obj2;
                i4 = i9;
                i9 = i;
                f = 2.0f;
                i5 = i2;
                for (Size size2 : supportedPreviewSizes) {
                    if (size2.width >= 720 && size2.width < Publish_Image.MIN_IMG_HEIGHT && size2.height >= 450 && size2.height < Publish_Image.MIN_IMG_WIDTH) {
                        abs = Math.abs(((((float) size2.width) * 1.0f) / ((float) size2.height)) - 1.7777778f);
                        if (abs < f) {
                            i9 = size2.width;
                            i5 = size2.height;
                            obj5 = 1;
                            f = abs;
                        }
                    }
                    if (size2.width >= i3 && size2.height >= i4 && size2.width < 720 && size2.height < 1280) {
                        i3 = size2.width;
                        i4 = size2.height;
                    }
                    if (size2.width == 864 || size2.height != VideoEditActivity.MAX_VIDEO_WIDTH) {
                        obj3 = obj4;
                    } else {
                        obj3 = 1;
                    }
                    obj4 = obj3;
                }
                f2 = f;
                obj6 = obj4;
                i = i5;
                i8 = i4;
                i4 = i9;
                i6 = i3;
                obj = obj5;
                i7 = i6;
            } else {
                obj6 = null;
                obj = obj2;
                i7 = i8;
                i4 = i;
                i = i2;
                i8 = i9;
                f2 = 2.0f;
            }
            if (obj != null) {
                obj7 = obj;
                i3 = i4;
                i4 = i;
                f = f2;
                for (Size size22 : supportedPreviewSizes) {
                    if (size22.width >= VideoEditActivity.MAX_VIDEO_WIDTH && size22.width < 1280 && size22.height >= 320 && size22.height < 720) {
                        abs2 = Math.abs(((((float) size22.width) * 1.0f) / ((float) size22.height)) - 1.7777778f);
                        if (abs2 < f) {
                            i3 = size22.width;
                            i4 = size22.height;
                            f2 = abs2;
                            i = i4;
                            i4 = i3;
                            obj = 1;
                            obj7 = obj;
                            i3 = i4;
                            i4 = i;
                            f = f2;
                        }
                    }
                    f2 = f;
                    i = i4;
                    i4 = i3;
                    obj = obj7;
                    obj7 = obj;
                    i3 = i4;
                    i4 = i;
                    f = f2;
                }
                i2 = i4;
                i = i3;
            } else {
                i2 = i;
                obj7 = obj;
                i = i4;
            }
            if (obj7 != null) {
                i = i8;
                i2 = i7;
            } else {
                i6 = i2;
                i2 = i;
                i = i6;
            }
            if (obj6 != null && r1 == VideoEditActivity.MAX_VIDEO_HEIGHT && i == VideoEditActivity.MAX_VIDEO_WIDTH) {
                i2 = 864;
            }
            this.i = i2;
            this.j = i;
        }
        i2 = 720;
        i = 1280;
        obj2 = null;
        if (obj2 != null) {
            obj6 = null;
            obj = obj2;
            i7 = i8;
            i4 = i;
            i = i2;
            i8 = i9;
            f2 = 2.0f;
        } else {
            i3 = i8;
            obj4 = null;
            obj5 = obj2;
            i4 = i9;
            i9 = i;
            f = 2.0f;
            i5 = i2;
            for (Size size222 : supportedPreviewSizes) {
                abs = Math.abs(((((float) size222.width) * 1.0f) / ((float) size222.height)) - 1.7777778f);
                if (abs < f) {
                    i9 = size222.width;
                    i5 = size222.height;
                    obj5 = 1;
                    f = abs;
                }
                i3 = size222.width;
                i4 = size222.height;
                if (size222.width == 864) {
                }
                obj3 = obj4;
                obj4 = obj3;
            }
            f2 = f;
            obj6 = obj4;
            i = i5;
            i8 = i4;
            i4 = i9;
            i6 = i3;
            obj = obj5;
            i7 = i6;
        }
        if (obj != null) {
            i2 = i;
            obj7 = obj;
            i = i4;
        } else {
            obj7 = obj;
            i3 = i4;
            i4 = i;
            f = f2;
            for (Size size2222 : supportedPreviewSizes) {
                abs2 = Math.abs(((((float) size2222.width) * 1.0f) / ((float) size2222.height)) - 1.7777778f);
                if (abs2 < f) {
                    i3 = size2222.width;
                    i4 = size2222.height;
                    f2 = abs2;
                    i = i4;
                    i4 = i3;
                    obj = 1;
                    obj7 = obj;
                    i3 = i4;
                    i4 = i;
                    f = f2;
                }
                f2 = f;
                i = i4;
                i4 = i3;
                obj = obj7;
                obj7 = obj;
                i3 = i4;
                i4 = i;
                f = f2;
            }
            i2 = i4;
            i = i3;
        }
        if (obj7 != null) {
            i6 = i2;
            i2 = i;
            i = i6;
        } else {
            i = i8;
            i2 = i7;
        }
        i2 = 864;
        this.i = i2;
        this.j = i;
    }

    private int[] a(int i, Parameters parameters) {
        List<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
        int[] iArr = (int[]) supportedPreviewFpsRange.get(0);
        int i2 = i * 1000;
        int i3 = 1000000;
        int[] iArr2 = iArr;
        for (int[] iArr3 : supportedPreviewFpsRange) {
            int[] iArr4;
            int i4;
            int i5 = iArr3[0];
            int i6 = iArr3[1];
            if ((i5 < i2 && i6 >= i2) || (i5 <= i2 && i6 > i2)) {
                i5 = (i2 - i5) + (i6 - i2);
                if (i5 < i3) {
                    iArr4 = iArr3;
                    i4 = i5;
                    iArr2 = iArr4;
                    i3 = i4;
                }
            }
            i4 = i3;
            iArr4 = iArr2;
            iArr2 = iArr4;
            i3 = i4;
        }
        return iArr2;
    }

    private int b(int i, Parameters parameters) {
        List<Integer> supportedPreviewFrameRates = parameters.getSupportedPreviewFrameRates();
        int intValue = ((Integer) supportedPreviewFrameRates.get(0)).intValue();
        int i2 = intValue;
        int i3 = -1;
        for (Integer intValue2 : supportedPreviewFrameRates) {
            intValue = intValue2.intValue();
            if (intValue > i3 && intValue <= i) {
                i3 = intValue;
            }
            if (intValue >= i2) {
                intValue = i2;
            }
            i2 = intValue;
        }
        return i3 == -1 ? i2 : i3;
    }
}
