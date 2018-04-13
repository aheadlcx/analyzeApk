package android.support.v4.view;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.util.Pools.SynchronizedPool;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.concurrent.ArrayBlockingQueue;

public final class AsyncLayoutInflater {
    LayoutInflater a;
    Handler b;
    c c;
    private Callback d = new d(this);

    public interface OnInflateFinishedListener {
        void onInflateFinished(@NonNull View view, @LayoutRes int i, @Nullable ViewGroup viewGroup);
    }

    private static class a extends LayoutInflater {
        private static final String[] a = new String[]{"android.widget.", "android.webkit.", "android.app."};

        a(Context context) {
            super(context);
        }

        public LayoutInflater cloneInContext(Context context) {
            return new a(context);
        }

        protected View onCreateView(String str, AttributeSet attributeSet) throws ClassNotFoundException {
            String[] strArr = a;
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                try {
                    View createView = createView(str, strArr[i], attributeSet);
                    if (createView != null) {
                        return createView;
                    }
                    i++;
                } catch (ClassNotFoundException e) {
                }
            }
            return super.onCreateView(str, attributeSet);
        }
    }

    private static class b {
        AsyncLayoutInflater a;
        ViewGroup b;
        int c;
        View d;
        OnInflateFinishedListener e;

        b() {
        }
    }

    private static class c extends Thread {
        private static final c a = new c();
        private ArrayBlockingQueue<b> b = new ArrayBlockingQueue(10);
        private SynchronizedPool<b> c = new SynchronizedPool(10);

        private c() {
        }

        static {
            a.start();
        }

        public static c getInstance() {
            return a;
        }

        public void runInner() {
            try {
                b bVar = (b) this.b.take();
                try {
                    bVar.d = bVar.a.a.inflate(bVar.c, bVar.b, false);
                } catch (Throwable e) {
                    Log.w("AsyncLayoutInflater", "Failed to inflate resource in the background! Retrying on the UI thread", e);
                }
                Message.obtain(bVar.a.b, 0, bVar).sendToTarget();
            } catch (Throwable e2) {
                Log.w("AsyncLayoutInflater", e2);
            }
        }

        public void run() {
            while (true) {
                runInner();
            }
        }

        public b obtainRequest() {
            b bVar = (b) this.c.acquire();
            if (bVar == null) {
                return new b();
            }
            return bVar;
        }

        public void releaseRequest(b bVar) {
            bVar.e = null;
            bVar.a = null;
            bVar.b = null;
            bVar.c = 0;
            bVar.d = null;
            this.c.release(bVar);
        }

        public void enqueue(b bVar) {
            try {
                this.b.put(bVar);
            } catch (Throwable e) {
                throw new RuntimeException("Failed to enqueue async inflate request", e);
            }
        }
    }

    public AsyncLayoutInflater(@NonNull Context context) {
        this.a = new a(context);
        this.b = new Handler(this.d);
        this.c = c.getInstance();
    }

    @UiThread
    public void inflate(@LayoutRes int i, @Nullable ViewGroup viewGroup, @NonNull OnInflateFinishedListener onInflateFinishedListener) {
        if (onInflateFinishedListener == null) {
            throw new NullPointerException("callback argument may not be null!");
        }
        b obtainRequest = this.c.obtainRequest();
        obtainRequest.a = this;
        obtainRequest.c = i;
        obtainRequest.b = viewGroup;
        obtainRequest.e = onInflateFinishedListener;
        this.c.enqueue(obtainRequest);
    }
}
