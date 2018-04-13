package cn.xiaochuankeji.tieba.d;

import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class c {

    private static abstract class a<T> implements Comparator<T> {
        abstract int a(T t);

        private a() {
        }

        public int compare(T t, T t2) {
            return a(t) - a(t2);
        }
    }

    public static int[] a(Parameters parameters, final int i) {
        Collection supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
        if (!supportedPreviewFpsRange.isEmpty()) {
            return (int[]) Collections.min(supportedPreviewFpsRange, new a<int[]>() {
                int a(int[] iArr) {
                    return iArr[0] + (Math.abs(i - iArr[1]) * 10);
                }
            });
        }
        Log.w("CameraUtils", "No supported preview fps range");
        return new int[]{0, 0};
    }

    public static Size a(List<Size> list, final int i, final int i2) {
        Collection arrayList = new ArrayList();
        for (Size size : list) {
            if (size.width > size.height) {
            }
            if ((((float) Math.min(size.width, size.height)) * 1.0f) / 9.0f == (((float) Math.max(size.width, size.height)) * 1.0f) / 16.0f) {
                arrayList.add(size);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return (Size) Collections.min(arrayList, new a<Size>() {
            int a(Size size) {
                return Math.abs(Math.min(i, i2) - Math.min(size.width, size.height)) + Math.abs(Math.max(i2, i) - Math.max(size.width, size.height));
            }
        });
    }
}
