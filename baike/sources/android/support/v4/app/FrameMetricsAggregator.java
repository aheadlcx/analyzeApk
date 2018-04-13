package android.support.v4.app;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.util.SparseIntArray;
import android.view.Window.OnFrameMetricsAvailableListener;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class FrameMetricsAggregator {
    public static final int ANIMATION_DURATION = 256;
    public static final int ANIMATION_INDEX = 8;
    public static final int COMMAND_DURATION = 32;
    public static final int COMMAND_INDEX = 5;
    public static final int DELAY_DURATION = 128;
    public static final int DELAY_INDEX = 7;
    public static final int DRAW_DURATION = 8;
    public static final int DRAW_INDEX = 3;
    public static final int EVERY_DURATION = 511;
    public static final int INPUT_DURATION = 2;
    public static final int INPUT_INDEX = 1;
    public static final int LAYOUT_MEASURE_DURATION = 4;
    public static final int LAYOUT_MEASURE_INDEX = 2;
    public static final int SWAP_DURATION = 64;
    public static final int SWAP_INDEX = 6;
    public static final int SYNC_DURATION = 16;
    public static final int SYNC_INDEX = 4;
    public static final int TOTAL_DURATION = 1;
    public static final int TOTAL_INDEX = 0;
    private b a;

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MetricType {
    }

    private static class b {
        private b() {
        }

        public void add(Activity activity) {
        }

        public SparseIntArray[] remove(Activity activity) {
            return null;
        }

        public SparseIntArray[] stop() {
            return null;
        }

        public SparseIntArray[] getMetrics() {
            return null;
        }

        public SparseIntArray[] reset() {
            return null;
        }
    }

    @RequiresApi(24)
    private static class a extends b {
        private static HandlerThread e = null;
        private static Handler f = null;
        OnFrameMetricsAvailableListener a = new ai(this);
        private int b;
        private SparseIntArray[] c = new SparseIntArray[9];
        private ArrayList<WeakReference<Activity>> d = new ArrayList();

        a(int i) {
            super();
            this.b = i;
        }

        void a(SparseIntArray sparseIntArray, long j) {
            if (sparseIntArray != null) {
                int i = (int) ((500000 + j) / 1000000);
                if (j >= 0) {
                    sparseIntArray.put(i, sparseIntArray.get(i) + 1);
                }
            }
        }

        public void add(Activity activity) {
            if (e == null) {
                e = new HandlerThread("FrameMetricsAggregator");
                e.start();
                f = new Handler(e.getLooper());
            }
            int i = 0;
            while (i <= 8) {
                if (this.c[i] == null && (this.b & (1 << i)) != 0) {
                    this.c[i] = new SparseIntArray();
                }
                i++;
            }
            activity.getWindow().addOnFrameMetricsAvailableListener(this.a, f);
            this.d.add(new WeakReference(activity));
        }

        public SparseIntArray[] remove(Activity activity) {
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                if (weakReference.get() == activity) {
                    this.d.remove(weakReference);
                    break;
                }
            }
            activity.getWindow().removeOnFrameMetricsAvailableListener(this.a);
            return this.c;
        }

        public SparseIntArray[] stop() {
            for (int size = this.d.size() - 1; size >= 0; size--) {
                WeakReference weakReference = (WeakReference) this.d.get(size);
                Activity activity = (Activity) weakReference.get();
                if (weakReference.get() != null) {
                    activity.getWindow().removeOnFrameMetricsAvailableListener(this.a);
                    this.d.remove(size);
                }
            }
            return this.c;
        }

        public SparseIntArray[] getMetrics() {
            return this.c;
        }

        public SparseIntArray[] reset() {
            SparseIntArray[] sparseIntArrayArr = this.c;
            this.c = new SparseIntArray[9];
            return sparseIntArrayArr;
        }
    }

    public FrameMetricsAggregator() {
        this(1);
    }

    public FrameMetricsAggregator(int i) {
        if (VERSION.SDK_INT >= 24) {
            this.a = new a(i);
        } else {
            this.a = new b();
        }
    }

    public void add(@NonNull Activity activity) {
        this.a.add(activity);
    }

    @Nullable
    public SparseIntArray[] remove(@NonNull Activity activity) {
        return this.a.remove(activity);
    }

    @Nullable
    public SparseIntArray[] stop() {
        return this.a.stop();
    }

    @Nullable
    public SparseIntArray[] reset() {
        return this.a.reset();
    }

    @Nullable
    public SparseIntArray[] getMetrics() {
        return this.a.getMetrics();
    }
}
