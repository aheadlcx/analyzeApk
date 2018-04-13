package qsbk.app.video;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Environment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import java.io.File;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.utils.FileUtils;
import qsbk.app.utils.FileUtils.CallBack;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UpdateHelper;
import qsbk.app.video.SimpleVideoPlayer.OnVideoCacheListener;
import qsbk.app.video.SimpleVideoPlayer.OnVideoEventListener;
import qsbk.app.video.SimpleVideoPlayer.OnVideoStateListener;

public class VideoPlayerView extends RelativeLayout implements OnClickListener, OnVideoCacheListener, OnVideoEventListener, OnVideoStateListener {
    public static final int HEIGHT_FIT_HEIGHT = 2;
    public static final int HEIGHT_FIT_WIDTH = 1;
    public static final int HEIGHT_LIMIT_NONE = 0;
    public static final int MODE_GIF = 1;
    public static final int MODE_VIDEO = 0;
    public static final int VideoSizeMode_Auto = 0;
    public static final int VideoSizeMode_Exactly = 1;
    private static final File l = new File(Utils.getCacheDirPath());
    private static long m = 0;
    protected TextureView a;
    protected ProgressBar b;
    protected View c;
    protected View d;
    protected SimpleVideoPlayer e;
    protected float f = 0.0f;
    protected int g;
    protected boolean h = false;
    protected OnVideoEventListener i;
    protected OnVideoStateListener j;
    protected int k = 0;
    private int n;
    private boolean o = false;
    private boolean p;
    private int q;
    private int r;
    private Surface s;
    private int t;
    private int u;
    private boolean v = false;
    private int w = 0;
    private int x = 1;

    public VideoPlayerView(Context context) {
        super(context);
        a(null, 0);
    }

    public VideoPlayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, 0);
    }

    public VideoPlayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet, i);
    }

    private static void d() {
        long currentTimeMillis = System.currentTimeMillis();
        if (m + 36000000 < currentTimeMillis) {
            m = currentTimeMillis;
            FileUtils.trimDirAsync(l, 209715200, null);
        }
    }

    private static File getSaveFile() {
        return new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/qsbk/video/video_" + new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date(System.currentTimeMillis())) + ".mp4");
    }

    private static void b(File file) {
        a(file, null, null);
    }

    private static void a(File file, File file2, CallBack callBack) {
        WeakReference weakReference = new WeakReference(callBack);
        if (file2 == null) {
            file2 = getSaveFile();
        }
        FileUtils.copyFileAsync(file, file2, new be(file2, weakReference));
    }

    private void a(AttributeSet attributeSet, int i) {
        int i2;
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.VideoPlayerView, i, 0);
        this.n = obtainStyledAttributes.getResourceId(0, 0);
        this.w = obtainStyledAttributes.getInt(1, this.w);
        this.o = obtainStyledAttributes.getBoolean(4, this.o);
        this.x = obtainStyledAttributes.getInt(2, 1);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        if (this.x == 1) {
            i2 = displayMetrics.widthPixels;
        } else if (this.x == 2) {
            i2 = displayMetrics.heightPixels;
        } else {
            i2 = Integer.MAX_VALUE;
        }
        this.g = obtainStyledAttributes.getDimensionPixelSize(3, i2);
        obtainStyledAttributes.recycle();
        a(this.n);
        this.e = new SimpleVideoPlayer();
        this.e.setLoop(true);
        this.e.setOnVideoEventListener(this);
        this.e.setOnVideoStateListener(this);
        this.e.setOnVideoCacheListener(this);
        c();
        setOnClickListener(this);
        reset();
    }

    public void setLoop(boolean z) {
        this.e.setLoop(z);
    }

    public int getDisplayMode() {
        return this.k;
    }

    public void setDisplayMode(int i) {
        this.k = i;
    }

    public void setLandscape(boolean z) {
        this.v = z;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (this.n != 0) {
            if (this.b == null) {
                this.b = (ProgressBar) findViewById(R.id.video_progress);
            }
            if (this.c == null) {
                this.c = findViewById(R.id.video_play);
            }
            if (this.d == null) {
                this.d = findViewById(R.id.video_preview);
            }
        }
    }

    protected void a(int i) {
        if (i != 0) {
            removeAllViews();
            inflate(getContext(), i, this);
            this.b = (ProgressBar) findViewById(R.id.video_progress);
            this.c = findViewById(R.id.video_play);
            this.d = findViewById(R.id.video_preview);
        }
    }

    public View getProgressBar() {
        return this.b;
    }

    public View getPlayBtn() {
        return this.c;
    }

    protected void onMeasure(int i, int i2) {
        int size;
        int i3 = 0;
        if (this.f != 0.0f) {
            i3 = MeasureSpec.getSize(i);
            size = MeasureSpec.getSize(i2);
            if (size == 0) {
                size = Integer.MAX_VALUE;
            }
            if (size > this.g) {
                size = this.g;
                i2 = MeasureSpec.makeMeasureSpec(size, 1073741824);
            }
            int i4 = (int) (((double) (((float) i3) / this.f)) + 0.5d);
            if (i4 <= size + 1 || this.v) {
                super.onMeasure(i, MeasureSpec.makeMeasureSpec(i4, 1073741824));
            } else {
                if (this.w != 1) {
                    i3 = (int) ((((float) size) * this.f) + 0.5f);
                }
                if (size == this.g) {
                    super.onMeasure(i, i2);
                } else {
                    super.onMeasure(i, MeasureSpec.makeMeasureSpec(i4, 1073741824));
                }
            }
        } else {
            if (MeasureSpec.getSize(i2) > this.g) {
                i2 = (-1073741824 & i2) | this.g;
            }
            super.onMeasure(i, i2);
        }
        size = getMeasuredHeight();
        if (Math.abs(this.q - i3) > 1 || Math.abs(this.r - size) > 1) {
            LayoutParams layoutParams = this.a.getLayoutParams();
            layoutParams.width = i3;
            this.q = i3;
            layoutParams.height = size;
            this.r = size;
            this.a.setLayoutParams(layoutParams);
        }
        if (this.o && this.d != null) {
            layoutParams = this.d.getLayoutParams();
            if (Math.abs(layoutParams.width - i3) > 1 || Math.abs(layoutParams.height - size) > 1) {
                layoutParams.width = i3;
                layoutParams.height = size;
                this.d.setLayoutParams(layoutParams);
            }
        }
    }

    public void setAspectRatio(int i, int i2) {
        if (i2 == 0) {
            setAspectRatio(0.0f);
        } else {
            setAspectRatio(((float) i) / ((float) i2));
        }
        this.t = i;
        this.u = i2;
    }

    private void setAspectRatio(float f) {
        this.f = f;
        requestLayout();
    }

    public void setWidget(ProgressBar progressBar, View view, View view2) {
        this.b = progressBar;
        this.c = view;
        this.d = view2;
        this.h = true;
    }

    public void setStartMs(long j) {
        this.e.setStartMs(j);
    }

    public String getVideo() {
        return this.e.getVideoUri();
    }

    public void setVideo(String str) {
        if (TextUtils.isEmpty(str)) {
            reset();
        } else if (!str.equals(this.e.getVideoUri())) {
            resetWidget();
            this.e.setVideo(str);
            if (!str.startsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                this.e.setCachePath(Utils.getCacheFilePath(str));
            }
            autoSetVolume();
            if (this.p) {
                ToastAndDialog.makeNegativeToast(getContext(), "保存视频失败，请稍候重试").show();
            }
            this.p = false;
        } else if (this.d == null) {
        } else {
            if (this.e.a() >= 2) {
                this.d.setVisibility(4);
            } else {
                this.d.setVisibility(0);
            }
        }
    }

    public void play() {
        autoSetVolume();
        this.e.start();
        VideoPlayersManager.onVideoPlayerPlay(this);
    }

    private void a(boolean z) {
        Context context = getContext();
        if (context instanceof Activity) {
            Window window = ((Activity) context).getWindow();
            if (z) {
                window.addFlags(128);
            } else {
                window.clearFlags(128);
            }
        }
    }

    public boolean isPlaying() {
        int playState = this.e.getPlayState();
        if (playState == 1 || playState == 3 || playState == 4) {
            return true;
        }
        return false;
    }

    public void startOrPause() {
        if (isPlaying()) {
            pause();
        } else {
            play();
        }
    }

    public void playWithOutEnd() {
        play();
    }

    public void pause() {
        this.e.pause();
        VideoPlayersManager.onVideoPlayerPause(this, false);
    }

    public void stop() {
        this.e.stop();
        VideoPlayersManager.onVideoPlayerPause(this, false);
    }

    public void resetWidget() {
        if (this.c != null) {
            this.c.setVisibility(0);
        }
        if (this.b != null) {
            this.b.setVisibility(4);
        }
        if (this.d != null) {
            this.d.setVisibility(0);
        }
    }

    public void reset() {
        this.e.reset();
        if (!this.h || getVisibility() == 0) {
            resetWidget();
        }
        VideoPlayersManager.onVideoPlayerPause(this, true);
    }

    public View getPreviewView() {
        return this.d;
    }

    @TargetApi(14)
    protected void c() {
        this.a = new TextureView(getContext());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        addView(this.a, 0, layoutParams);
        this.a.setSurfaceTextureListener(new bf(this));
    }

    public void seekTo(long j) {
        this.e.seekTo(j);
    }

    public long getCurrentTime() {
        if (this.e.getPlayer() == null) {
            return 0;
        }
        return this.e.getPlayer().getCurrentPosition();
    }

    public void setOnVideoEventListener(OnVideoEventListener onVideoEventListener) {
        this.i = onVideoEventListener;
    }

    public void setOnVideoStateListener(OnVideoStateListener onVideoStateListener) {
        this.j = onVideoStateListener;
    }

    public void onVideoCompletion(SimpleVideoPlayer simpleVideoPlayer) {
        if (this.i != null) {
            this.i.onVideoCompletion(simpleVideoPlayer);
        }
    }

    public void onVideoPrepared(SimpleVideoPlayer simpleVideoPlayer) {
        if (this.i != null) {
            this.i.onVideoPrepared(simpleVideoPlayer);
        }
    }

    public void onVideoError(SimpleVideoPlayer simpleVideoPlayer, int i, int i2) {
        if (this.i != null) {
            this.i.onVideoError(simpleVideoPlayer, i, i2);
        }
    }

    public void onVideoBuffering(SimpleVideoPlayer simpleVideoPlayer, int i) {
        if (this.b != null) {
            this.b.setVisibility(i < 100 ? 0 : 4);
        }
        if (this.i != null) {
            this.i.onVideoBuffering(simpleVideoPlayer, i);
        }
    }

    public void onVideoState(SimpleVideoPlayer simpleVideoPlayer, int i) {
        if (i == 4 && this.d != null && this.d.getVisibility() == 0 && this.k == 1) {
            this.d.setVisibility(4);
        }
        if (i == 1 || i == 3 || i == 4) {
            a();
        } else {
            b();
        }
        if (this.j != null) {
            this.j.onVideoState(simpleVideoPlayer, i);
        }
    }

    protected void a() {
        a(true);
        if (this.c != null) {
            this.c.setVisibility(4);
        }
        if (this.b != null) {
            this.b.setVisibility(4);
        }
    }

    protected void b() {
        a(false);
        if (!this.h || getVisibility() == 0) {
            if (this.c != null) {
                this.c.setVisibility(0);
            }
            if (this.b != null) {
                this.b.setVisibility(4);
            }
        }
    }

    public void onClick(View view) {
        startOrPause();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.e.release();
    }

    public void autoSetVolume() {
        this.e.setVolume(100);
    }

    public void onVideoCachePercent(SimpleVideoPlayer simpleVideoPlayer, int i) {
        if (i >= 0) {
            d();
        }
        if (i >= 100) {
            if (this.p) {
                download();
            }
            this.p = false;
        }
    }

    public void download() {
        download(null);
    }

    public void downloadTo(File file, CallBack callBack) {
        if (this.e.isCached()) {
            a(new File(this.e.getCachePath()), file, callBack);
            return;
        }
        this.p = true;
        ToastAndDialog.makeNeutralToast(QsbkApp.mContext, "正在下载，请稍候。").show();
    }

    public void download(CallBack callBack) {
        downloadTo(null, callBack);
    }

    public void downloadWithoutPlay() {
        File file = new File(this.e.getCachePath());
        if (file.exists()) {
            b(file);
            return;
        }
        ToastAndDialog.makeNeutralToast(QsbkApp.mContext, "正在下载，请稍候。").show();
        UpdateHelper.getInstance().download(this.e.getVideoUri(), file, new bg(this, file));
    }
}
