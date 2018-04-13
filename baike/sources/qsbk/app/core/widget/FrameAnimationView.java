package qsbk.app.core.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Handler;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.core.utils.FileUtils;

public class FrameAnimationView extends AppCompatImageView {
    private static final String a = FrameAnimationView.class.getSimpleName();
    private ArrayList<String> b;
    private int c = 0;
    private long d = 40;
    private boolean e = false;
    private int f = 0;
    private a g = a.Assets;
    private AnimationListener h;
    private long i = 0;
    private Bitmap j;
    private volatile boolean k = false;
    private ExecutorService l = Executors.newFixedThreadPool(5);
    private boolean m = false;
    private Handler n = new Handler();
    private int o = 0;

    public interface AnimationListener {
        void onEnd();

        void onStart();
    }

    private enum a {
        Assets,
        SdCard
    }

    public FrameAnimationView(Context context) {
        super(context);
    }

    public FrameAnimationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FrameAnimationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void loop(boolean z) {
        this.e = z;
    }

    public void loopDelay(int i) {
        loop(true);
        this.f = i;
    }

    public void setFramesInAssets(String str) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.clear();
        this.g = a.Assets;
        try {
            String[] list = getResources().getAssets().list(str);
            if (list != null && list.length > 0) {
                for (String str2 : list) {
                    this.b.add(str + MqttTopic.TOPIC_LEVEL_SEPARATOR + str2);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFreq(int i) {
        this.d = (long) i;
    }

    public void play() {
        Log.d(a, "play()");
        this.c = 0;
        if (isPlaying()) {
            stop();
        }
        setImageBitmap(null);
        a(this.j);
        if (this.n != null) {
            this.n.removeCallbacksAndMessages(null);
        }
        this.k = true;
        this.i = System.currentTimeMillis();
        this.o = 0;
        if (this.h != null) {
            this.h.onStart();
        }
        startAnimateFrames();
    }

    public void startAnimateFrames() {
        try {
            if (!this.l.isShutdown()) {
                this.l.execute(new k(this));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (this.h != null) {
                this.h.onEnd();
            }
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            System.gc();
            if (this.h != null) {
                this.h.onEnd();
            }
        }
    }

    private void b() {
        a(new l(this));
    }

    private void a(Runnable runnable) {
        try {
            if (!this.l.isShutdown()) {
                this.l.execute(runnable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            System.gc();
        }
    }

    private Bitmap a(String str, Options options) throws Exception {
        byte[] fileToBytes = FileUtils.fileToBytes(str);
        for (int i = 0; i < 100; i++) {
            fileToBytes[i] = (byte) (fileToBytes[i] ^ i);
        }
        return BitmapFactory.decodeByteArray(fileToBytes, 0, fileToBytes.length, options);
    }

    private void c() {
        while (this.k) {
            if (this.b == null || this.b.size() <= this.c) {
                long currentTimeMillis;
                if (this.e) {
                    this.c = 0;
                    currentTimeMillis = System.currentTimeMillis();
                    Log.d(a, "animateTime: " + (currentTimeMillis - this.i));
                    this.i = currentTimeMillis;
                    if (this.b == null || this.b.size() == 0) {
                        this.n.post(new m(this));
                        this.k = false;
                        return;
                    } else if (this.f > 0) {
                        try {
                            Thread.sleep((long) this.f);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    currentTimeMillis = System.currentTimeMillis();
                    Log.d(a, "animateTime: " + (currentTimeMillis - this.i));
                    this.i = currentTimeMillis;
                    if (this.b != null && this.b.size() > 0) {
                        Log.d(a, "average decode time: " + (this.o / this.b.size()));
                    }
                    this.n.post(new n(this));
                    this.k = false;
                    return;
                }
            }
            if (this.j == null) {
                d();
            }
            this.n.post(new o(this));
            if (this.k) {
                this.c++;
                b();
                try {
                    Thread.sleep(this.d);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            } else if (!this.m) {
                this.n.post(new p(this));
            }
        }
    }

    private void d() {
        if (this.c < this.b.size() && isPlaying()) {
            try {
                Options options = new Options();
                options.inMutable = true;
                if (!(this.j == null || this.j.isRecycled())) {
                    options.inSampleSize = 1;
                    options.inBitmap = this.j;
                }
                if (this.g == a.SdCard) {
                    if (((String) this.b.get(this.c)).endsWith(".ecp")) {
                        this.j = a((String) this.b.get(this.c), options);
                    } else {
                        this.j = BitmapFactory.decodeFile((String) this.b.get(this.c), options);
                    }
                } else if (this.g == a.Assets) {
                    this.j = BitmapFactory.decodeStream(getResources().getAssets().open((String) this.b.get(this.c)), null, options);
                }
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                System.gc();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void setFramesInSdCard(String str) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.clear();
        this.g = a.SdCard;
        File file = new File(str);
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File absolutePath : listFiles) {
                    this.b.add(absolutePath.getAbsolutePath());
                }
                Collections.sort(this.b);
            }
        }
    }

    public void setAnimationListener(AnimationListener animationListener) {
        this.h = animationListener;
    }

    private void a(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    public boolean isPlaying() {
        return this.k;
    }

    public void stop() {
        Log.d(a, "stop()");
        this.k = false;
    }

    protected void onDetachedFromWindow() {
        release();
        super.onDetachedFromWindow();
    }

    public void release() {
        Log.d(a, "release()");
        if (isPlaying()) {
            stop();
        }
        setImageBitmap(null);
        a(this.j);
        if (!(this.l == null || this.l.isShutdown())) {
            this.l.shutdown();
        }
        if (this.n != null) {
            this.n.removeCallbacksAndMessages(null);
        }
    }

    public void setFillAfter(boolean z) {
        this.m = z;
    }
}
