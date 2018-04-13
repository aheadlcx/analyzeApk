package qsbk.app.video;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.common.util.UriUtil;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import qsbk.app.R;
import qsbk.app.core.AsyncTask;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.model.ImageInfo;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.TimeDelta;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.ToastUtil;
import qsbk.app.widget.VideoRecordProgressBar;
import qsbk.app.ye.videotools.camera.CameraHelper;
import qsbk.app.ye.videotools.camera.CameraLoader;
import qsbk.app.ye.videotools.camera.CameraRender;
import qsbk.app.ye.videotools.camera.CameraRender.SurfaceListener;
import qsbk.app.ye.videotools.recorder.MediaRecorder;
import qsbk.app.ye.videotools.utils.VideoEditer;

public class VideoRecordActivity extends Activity implements OnClickListener, SurfaceListener {
    public static final int MAX_DURATION = 300000;
    private static final String a = VideoRecordActivity.class.getSimpleName();
    private RelativeLayout b;
    private ImageView c;
    private ImageView d;
    private VideoRecordProgressBar e;
    private ImageView f;
    private TextView g;
    private ImageView h;
    private ImageView i;
    private ProgressDialog j;
    private MediaRecorder k;
    private volatile boolean l = false;
    private int m;
    private CameraRender n;
    private CameraHelper o;
    private CameraLoader p;
    private int q = 1;
    private TimeDelta r;
    private ArrayList<VideoSnippet> s = new ArrayList();
    private Handler t = new Handler();
    private Runnable u = new bj(this);

    public class VideoSnippet {
        final /* synthetic */ VideoRecordActivity a;
        public int camera;
        public int endTime;

        public VideoSnippet(VideoRecordActivity videoRecordActivity) {
            this.a = videoRecordActivity;
        }

        public VideoSnippet(VideoRecordActivity videoRecordActivity, int i, int i2) {
            this.a = videoRecordActivity;
            this.camera = i;
            this.endTime = i2;
        }
    }

    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.activity_video_record);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.m = displayMetrics.widthPixels;
        b();
    }

    private void b() {
        this.b = (RelativeLayout) findViewById(R.id.camera_operation);
        this.c = (ImageView) findViewById(R.id.close);
        this.c.setOnClickListener(this);
        this.d = (ImageView) findViewById(R.id.switch_camera);
        this.d.setOnClickListener(this);
        this.e = (VideoRecordProgressBar) findViewById(R.id.progress);
        this.e.setVisibility(4);
        this.e.setVideoSnippets(this.s);
        this.g = (TextView) findViewById(R.id.record_time_toast);
        this.f = (ImageView) findViewById(R.id.record_btn);
        this.f.setVisibility(4);
        this.f.setOnTouchListener(new bk(this));
        this.h = (ImageView) findViewById(R.id.left_btn);
        this.h.setImageResource(R.drawable.video_delete_normal);
        this.h.setAlpha(0.3f);
        this.h.setEnabled(false);
        this.h.setOnClickListener(this);
        this.i = (ImageView) findViewById(R.id.right_btn);
        this.i.setImageResource(R.drawable.video_finish_select);
        this.i.setAlpha(0.3f);
        this.i.setEnabled(false);
        this.i.setOnClickListener(this);
        f();
    }

    public void onClick(View view) {
        int i = 0;
        switch (view.getId()) {
            case R.id.close:
                File[] c = c();
                int length = c.length;
                while (i < length) {
                    c[i].delete();
                    i++;
                }
                finish();
                return;
            case R.id.switch_camera:
                try {
                    if (this.p != null) {
                        this.p.switchCamera();
                        if (this.q == 0) {
                            i = 1;
                        }
                        this.q = i;
                        return;
                    }
                    return;
                } catch (Exception e) {
                    return;
                }
            case R.id.left_btn:
                e();
                return;
            case R.id.right_btn:
                if (this.s.size() > 0) {
                    a(false);
                    new bm(this).execute(new Void[0]);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void a(File file) {
        Intent intent = new Intent();
        Serializable imageInfo = new ImageInfo(UriUtil.getUriForFile(file).toString(), MediaFormat.VIDEO);
        imageInfo.width = VideoEditActivity.MAX_VIDEO_WIDTH;
        imageInfo.height = VideoEditActivity.MAX_VIDEO_WIDTH;
        intent.putExtra("video", imageInfo);
        intent.putExtra("video_facing", this.q == 0);
        setResult(-1, intent);
        finish();
    }

    private void b(File file) {
        this.j = ProgressDialog.show(this, null, "正在处理视频，请稍候。", true, false);
        String path = file.getPath();
        String str = d() + "crop_" + path.substring(path.lastIndexOf(47) + 1);
        this.r = new TimeDelta();
        new bn(this, path, str, file).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void a(String str, Bitmap bitmap) {
        String str2 = d() + "edit_" + str.substring(str.lastIndexOf(47) + 1);
        VideoEditer create = VideoEditer.create();
        create.addDataSource(str);
        create.setTargetPath(str2);
        if (bitmap != null) {
            create.addVideoEndingMark(bitmap, 5);
        }
        create.setOnCompletionListener(new bq(this, str2, str));
        create.setOnErrorListener(new br(this, str));
        create.prepare();
        create.start();
    }

    private File[] c() {
        File[] fileArr = new File[this.s.size()];
        for (int i = 0; i < fileArr.length; i++) {
            fileArr[i] = new File(d() + "out" + i + ".ts");
        }
        return fileArr;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean c(java.io.File r14) {
        /*
        r13 = this;
        r7 = 0;
        r0 = 0;
        r1 = r13.s;
        r1 = r1.size();
        if (r1 != 0) goto L_0x000b;
    L_0x000a:
        return r0;
    L_0x000b:
        r1 = r14.exists();	 Catch:{ IOException -> 0x0065 }
        if (r1 == 0) goto L_0x0014;
    L_0x0011:
        r14.delete();	 Catch:{ IOException -> 0x0065 }
    L_0x0014:
        r1 = r14.getParentFile();	 Catch:{ IOException -> 0x0065 }
        r1 = r1.exists();	 Catch:{ IOException -> 0x0065 }
        if (r1 != 0) goto L_0x0025;
    L_0x001e:
        r1 = r14.getParentFile();	 Catch:{ IOException -> 0x0065 }
        r1.mkdirs();	 Catch:{ IOException -> 0x0065 }
    L_0x0025:
        r14.createNewFile();	 Catch:{ IOException -> 0x0065 }
    L_0x0028:
        r1 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x0083, IOException -> 0x009f, all -> 0x00bc }
        r1.<init>(r14);	 Catch:{ FileNotFoundException -> 0x0083, IOException -> 0x009f, all -> 0x00bc }
        r6 = r1.getChannel();	 Catch:{ FileNotFoundException -> 0x0083, IOException -> 0x009f, all -> 0x00bc }
        r9 = r13.c();	 Catch:{ FileNotFoundException -> 0x00e9, IOException -> 0x00df, all -> 0x00d4 }
        r10 = r9.length;	 Catch:{ FileNotFoundException -> 0x00e9, IOException -> 0x00df, all -> 0x00d4 }
        r8 = r0;
        r2 = r7;
    L_0x0038:
        if (r8 >= r10) goto L_0x006d;
    L_0x003a:
        r11 = r9[r8];	 Catch:{ FileNotFoundException -> 0x00ef, IOException -> 0x00e7 }
        r1 = r11.exists();	 Catch:{ FileNotFoundException -> 0x00ef, IOException -> 0x00e7 }
        if (r1 == 0) goto L_0x005e;
    L_0x0042:
        r1 = r11.canRead();	 Catch:{ FileNotFoundException -> 0x00ef, IOException -> 0x00e7 }
        if (r1 == 0) goto L_0x005e;
    L_0x0048:
        r1 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x00ef, IOException -> 0x00e7 }
        r1.<init>(r11);	 Catch:{ FileNotFoundException -> 0x00ef, IOException -> 0x00e7 }
        r1 = r1.getChannel();	 Catch:{ FileNotFoundException -> 0x00ef, IOException -> 0x00e7 }
        r2 = 0;
        r4 = r1.size();	 Catch:{ FileNotFoundException -> 0x00eb, IOException -> 0x00e2, all -> 0x00d7 }
        r1.transferTo(r2, r4, r6);	 Catch:{ FileNotFoundException -> 0x00eb, IOException -> 0x00e2, all -> 0x00d7 }
        r1.close();	 Catch:{ IOException -> 0x006a, FileNotFoundException -> 0x00eb, all -> 0x00d7 }
        r2 = r7;
    L_0x005e:
        r11.delete();	 Catch:{ FileNotFoundException -> 0x00ef, IOException -> 0x00e7 }
        r1 = r8 + 1;
        r8 = r1;
        goto L_0x0038;
    L_0x0065:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0028;
    L_0x006a:
        r2 = move-exception;
        r2 = r1;
        goto L_0x005e;
    L_0x006d:
        r0 = 1;
        if (r2 == 0) goto L_0x0073;
    L_0x0070:
        r2.close();	 Catch:{ IOException -> 0x007e }
    L_0x0073:
        if (r6 == 0) goto L_0x000a;
    L_0x0075:
        r6.close();	 Catch:{ IOException -> 0x0079 }
        goto L_0x000a;
    L_0x0079:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x000a;
    L_0x007e:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0073;
    L_0x0083:
        r1 = move-exception;
        r6 = r7;
    L_0x0085:
        r1.printStackTrace();	 Catch:{ all -> 0x00dc }
        if (r7 == 0) goto L_0x008d;
    L_0x008a:
        r7.close();	 Catch:{ IOException -> 0x009a }
    L_0x008d:
        if (r6 == 0) goto L_0x000a;
    L_0x008f:
        r6.close();	 Catch:{ IOException -> 0x0094 }
        goto L_0x000a;
    L_0x0094:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x000a;
    L_0x009a:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x008d;
    L_0x009f:
        r1 = move-exception;
        r6 = r7;
        r2 = r7;
    L_0x00a2:
        r1.printStackTrace();	 Catch:{ all -> 0x00da }
        if (r2 == 0) goto L_0x00aa;
    L_0x00a7:
        r2.close();	 Catch:{ IOException -> 0x00b7 }
    L_0x00aa:
        if (r6 == 0) goto L_0x000a;
    L_0x00ac:
        r6.close();	 Catch:{ IOException -> 0x00b1 }
        goto L_0x000a;
    L_0x00b1:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x000a;
    L_0x00b7:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00aa;
    L_0x00bc:
        r0 = move-exception;
        r6 = r7;
        r2 = r7;
    L_0x00bf:
        if (r2 == 0) goto L_0x00c4;
    L_0x00c1:
        r2.close();	 Catch:{ IOException -> 0x00ca }
    L_0x00c4:
        if (r6 == 0) goto L_0x00c9;
    L_0x00c6:
        r6.close();	 Catch:{ IOException -> 0x00cf }
    L_0x00c9:
        throw r0;
    L_0x00ca:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00c4;
    L_0x00cf:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00c9;
    L_0x00d4:
        r0 = move-exception;
        r2 = r7;
        goto L_0x00bf;
    L_0x00d7:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00bf;
    L_0x00da:
        r0 = move-exception;
        goto L_0x00bf;
    L_0x00dc:
        r0 = move-exception;
        r2 = r7;
        goto L_0x00bf;
    L_0x00df:
        r1 = move-exception;
        r2 = r7;
        goto L_0x00a2;
    L_0x00e2:
        r2 = move-exception;
        r12 = r2;
        r2 = r1;
        r1 = r12;
        goto L_0x00a2;
    L_0x00e7:
        r1 = move-exception;
        goto L_0x00a2;
    L_0x00e9:
        r1 = move-exception;
        goto L_0x0085;
    L_0x00eb:
        r2 = move-exception;
        r7 = r1;
        r1 = r2;
        goto L_0x0085;
    L_0x00ef:
        r1 = move-exception;
        r7 = r2;
        goto L_0x0085;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.video.VideoRecordActivity.c(java.io.File):boolean");
    }

    private String d() {
        String str = Environment.getExternalStorageDirectory() + File.separator + LogUtils.DEFAULT_TAG + File.separator + "video" + File.separator;
        try {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
        }
        return str;
    }

    private void e() {
        this.h.setAlpha(0.3f);
        this.h.setEnabled(false);
        this.f.setEnabled(false);
        boolean z = this.k != null && this.s.size() > 0;
        if (z) {
            if (this.e.isSelectedLast()) {
                if (this.k.removeLastSegment()) {
                    this.s.remove(this.s.size() - 1);
                    this.h.setImageResource(R.drawable.video_delete_normal);
                    if (this.s.size() > 0) {
                        float f;
                        this.h.setEnabled(true);
                        this.h.setAlpha(1.0f);
                        int i = ((VideoSnippet) this.s.get(this.s.size() - 1)).endTime;
                        this.i.setEnabled(i >= 3000);
                        ImageView imageView = this.i;
                        if (i >= 3000) {
                            f = 1.0f;
                        } else {
                            f = 0.3f;
                        }
                        imageView.setAlpha(f);
                    } else {
                        this.h.setEnabled(false);
                        this.h.setAlpha(0.3f);
                        this.i.setEnabled(false);
                        this.i.setAlpha(0.3f);
                    }
                    this.e.setCurrentSnippetTime(0);
                }
                this.e.setSelectedLast(false);
            } else {
                this.e.setSelectedLast(true);
                this.h.setImageResource(R.drawable.video_delete_selected);
                this.h.setEnabled(true);
                this.h.setAlpha(1.0f);
            }
        }
        this.f.setEnabled(true);
    }

    private void f() {
        g();
        h();
        this.o = new CameraHelper(this);
        int hasBackCamera = this.o.hasBackCamera();
        if (hasBackCamera == -1) {
            hasBackCamera = 0;
        }
        this.p = new CameraLoader(hasBackCamera, this.o, this.n, getMainLooper());
    }

    private void g() {
        this.n = new CameraRender(this);
        TextureView textureView = (TextureView) findViewById(R.id.textureView);
        LayoutParams layoutParams = (RelativeLayout.LayoutParams) textureView.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(0, 0);
        }
        layoutParams.width = this.m;
        layoutParams.height = this.m;
        textureView.setLayoutParams(layoutParams);
        textureView.setSurfaceTextureListener(this.n);
        this.n.setTextureView(textureView);
    }

    private void h() {
        this.f.setVisibility(0);
        this.e.setVisibility(0);
    }

    private void i() {
        if (this.k == null) {
            l();
        } else {
            n();
        }
    }

    private void j() {
        if (k() && !this.k.isPause()) {
            n();
            this.s.add(new VideoSnippet(this, this.q, (int) m()));
            this.h.setEnabled(true);
            this.h.setAlpha(1.0f);
            this.e.update();
        }
    }

    private boolean k() {
        return this.k != null && this.k.isStart();
    }

    private void l() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            if (this.k == null) {
                this.k = MediaRecorder.create();
                if (this.k == null) {
                    ToastUtil.Short("暂不支持该机型视频录制...");
                    return;
                }
                this.k.setOutputFilePrefix(d() + "out");
                this.k.maxRecordTime(300000);
                this.k.setRate(1, 1);
                this.k.setFramesPerSecond(15);
                this.k.setDimension(VideoEditActivity.MAX_VIDEO_WIDTH, VideoEditActivity.MAX_VIDEO_WIDTH);
                this.k.prepare(false);
                this.k.start();
                this.n.setSink(this.k);
                this.k.setOnCompletionListener(new bs(this));
            }
            this.t.postDelayed(this.u, 50);
            return;
        }
        ToastAndDialog.makeNegativeToast(this, "SD卡异常...", Integer.valueOf(0)).show();
        DebugUtil.debug(a, "SD卡异常...");
    }

    private long m() {
        return this.k != null ? this.k.getCurrentPosition() : 0;
    }

    private void n() {
        if (this.k == null) {
            return;
        }
        if (!this.k.isStart()) {
            this.k.start();
            this.t.postDelayed(this.u, 50);
        } else if (this.k.isPause()) {
            this.k.resume();
            this.t.postDelayed(this.u, 50);
        } else {
            this.k.pause();
            this.t.removeCallbacks(this.u);
        }
    }

    private void a(boolean z) {
        if (this.k != null) {
            this.n.setSink(null);
            this.k.stop(z);
            this.k = null;
        }
        this.t.removeCallbacks(this.u);
    }

    protected void onResume() {
        super.onResume();
        this.l = false;
    }

    protected void onPause() {
        super.onPause();
        this.l = true;
        j();
    }

    protected void onStop() {
        super.onStop();
        o();
    }

    private void o() {
        AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService("audio");
        audioManager.setStreamMute(1, false);
        audioManager.setStreamMute(4, false);
        audioManager.setStreamMute(3, false);
        audioManager.setStreamMute(2, false);
        audioManager.setStreamMute(8, false);
        audioManager.setStreamMute(0, false);
    }

    private boolean p() {
        return this.p != null && this.p.isCameraEnable();
    }

    private boolean q() {
        return this.l || isFinishing();
    }

    private void r() {
        new Builder(this).setMessage("请打开手机\"设置\"，找到\"糗事百科\"，开启视频拍摄相关权限").setPositiveButton("设置", new bt(this)).setNegativeButton("取消", null).create().show();
    }

    public void onSurfaceTextureAvailable() {
        if (this.p != null) {
            try {
                this.p.setUpCamera();
            } catch (OutOfMemoryError e) {
                System.gc();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (!p() && !q()) {
                r();
            }
        }
    }

    public void onSurfaceTextureDestroyed() {
        try {
            if (this.p != null) {
                this.p.releaseCamera();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        a(false);
    }
}
