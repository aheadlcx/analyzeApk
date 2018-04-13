package qsbk.app.video;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.common.util.UriUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import qsbk.app.R;
import qsbk.app.core.AsyncTask;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.model.ImageInfo;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.Md5;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.TimeDelta;
import qsbk.app.utils.UIHelper;
import qsbk.app.ye.videotools.utils.VideoEditer;

@TargetApi(11)
public class VideoEditActivity extends AppCompatActivity implements OnClickListener {
    public static final int MAX_CROP_TIME = 300000;
    public static final int MAX_VIDEO_HEIGHT = 854;
    public static final int MAX_VIDEO_WIDTH = 480;
    public static final int MIN_CROP_TIME = 3000;
    public static final int REQUEST_EDIT = 1;
    private static final String e = VideoEditActivity.class.getSimpleName();
    int a = MAX_VIDEO_WIDTH;
    int b = MAX_VIDEO_WIDTH;
    TimeDelta c;
    ImageInfo d;
    private ImageView f;
    private TextView g;
    private CheckBox h;
    private RelativeLayout i;
    private VideoEditPlayView j;
    private ImageView k;
    private VideoCropView l;
    private int m = 0;
    private int n = 0;
    private int o = 0;
    private int p;
    private int q = 0;
    private int r = 0;
    private boolean s;
    private PopupWindow t;
    private String u = "90";
    private ProgressDialog v;

    public static void launchForResult(Activity activity, ImageInfo imageInfo) {
        Intent intent = new Intent(activity, VideoEditActivity.class);
        intent.putExtra("video", imageInfo);
        activity.startActivityForResult(intent, 1);
    }

    public static String hashFirst8096Byte(File file) {
        InputStream fileInputStream;
        Exception e;
        Throwable th;
        String str = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[8096];
                int read = fileInputStream.read(bArr, 0, 8096);
                LogUtil.d("readed:" + read);
                if (read > 0) {
                    str = Md5.MD5(bArr, 0, read);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e2) {
                        }
                    }
                } else if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e3) {
                    }
                }
            } catch (Exception e4) {
                e = e4;
                try {
                    e.printStackTrace();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e5) {
                        }
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e6) {
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e7) {
            e = e7;
            fileInputStream = null;
            e.printStackTrace();
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return str;
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
        return str;
    }

    protected void onCreate(Bundle bundle) {
        supportRequestWindowFeature(1);
        super.onCreate(bundle);
        this.d = (ImageInfo) getIntent().getSerializableExtra("video");
        if (this.d != null) {
            setContentView(R.layout.activity_video_edit);
            b();
            c();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        this.j.stop();
    }

    private void b() {
        this.f = (ImageView) findViewById(R.id.backbutton);
        this.f.setOnClickListener(this);
        this.g = (TextView) findViewById(R.id.nextstep);
        this.g.setOnClickListener(this);
        this.h = (CheckBox) findViewById(R.id.fixed_ratio);
        this.h.setOnCheckedChangeListener(new w(this));
        this.h.getViewTreeObserver().addOnPreDrawListener(new ab(this));
        this.i = (RelativeLayout) findViewById(R.id.video_preview);
        this.j = (VideoEditPlayView) findViewById(R.id.video_play);
        this.k = (ImageView) findViewById(R.id.play_button);
        this.k.setOnClickListener(this);
        this.l = (VideoCropView) findViewById(R.id.video_crop);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.m = displayMetrics.widthPixels;
        LayoutParams layoutParams = (LayoutParams) this.i.getLayoutParams();
        layoutParams.width = this.m;
        layoutParams.height = this.m;
        this.i.setLayoutParams(layoutParams);
        layoutParams = (LayoutParams) this.l.getLayoutParams();
        layoutParams.topMargin = this.m;
        this.l.setLayoutParams(layoutParams);
        this.k.post(new ad(this));
    }

    private void c() {
        this.n = 0;
        this.p = getIntent().getIntExtra("video_time", 300000);
        this.o = this.p;
        if (this.o > 300000) {
            this.o = 300000;
        }
        if (VERSION.SDK_INT >= 17) {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(this.d.getFilePath(this));
            this.u = mediaMetadataRetriever.extractMetadata(24);
            Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime();
            if (frameAtTime != null) {
                this.d.width = frameAtTime.getWidth();
                this.d.height = frameAtTime.getHeight();
                this.u = "0";
            }
            DebugUtil.debug(e, "mOritation:" + this.u);
        }
        if (this.u.endsWith("90") || this.u.endsWith("270")) {
            this.j.setView(this.d.getFilePath(this), this.d.height, this.d.width, this.m, this.s);
        } else {
            this.j.setView(this.d.getFilePath(this), this.d.width, this.d.height, this.m, this.s);
        }
        this.j.setOnPauseListener(new ae(this));
        this.j.setOnPrePareListener(new ag(this));
        this.j.setOnCompletionListener(new ah(this));
        this.j.setOnScrollStopListener(new aj(this));
        this.l.addListener(new ak(this));
        this.l.initView(this.d.getFilePath(this));
        f();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backbutton:
                this.j.stop();
                finish();
                return;
            case R.id.nextstep:
                if (this.n == 0 && this.o == this.p && this.d.width == this.d.height && MAX_VIDEO_WIDTH == this.d.height) {
                    a(this.d.getFilePath(this), hashFirst8096Byte(new File(this.d.getFilePath(this))), true);
                    return;
                }
                this.j.stop();
                f();
                d();
                return;
            case R.id.play_button:
                DebugUtil.debug(e, "start_position:" + this.n);
                this.j.play(this.n, this.o);
                this.k.setVisibility(8);
                return;
            default:
                return;
        }
    }

    private void d() {
        new al(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void a(String str, Bitmap bitmap) {
        String str2 = e() + "edit_" + str.substring(str.lastIndexOf(47) + 1);
        VideoEditer create = VideoEditer.create();
        create.addDataSource(str);
        create.setTargetPath(str2);
        if (bitmap != null) {
            create.addVideoEndingMark(bitmap, 5);
        }
        create.setOnCompletionListener(new x(this, str2, str));
        create.setOnErrorListener(new y(this, str2));
        create.prepare();
        create.start();
    }

    private String e() {
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

    private void a(String str, String str2, boolean z) {
        Intent intent = new Intent();
        Serializable imageInfo = new ImageInfo(UriUtil.getUriForFile(new File(str)).toString(), MediaFormat.VIDEO);
        imageInfo.width = this.a;
        imageInfo.height = this.b;
        intent.putExtra("video_width", this.a);
        intent.putExtra("video_height", this.b);
        intent.putExtra("video_path", str);
        intent.putExtra("video_origin_hash", str2);
        intent.putExtra("success", z);
        intent.putExtra("video", imageInfo);
        setResult(-1, intent);
        finish();
    }

    private void f() {
        this.v = ProgressDialog.show(this, null, "正在处理视频，请稍候。", true, false);
    }

    private void g() {
        if (this.v != null) {
            this.v.cancel();
            this.v = null;
        }
    }

    private void h() {
        if (!SharePreferenceUtils.getSharePreferencesBoolValue("showed_video_edit_tip")) {
            int[] iArr = new int[2];
            this.h.getLocationOnScreen(iArr);
            Drawable drawable = getResources().getDrawable(R.drawable.ic_video_edit_tip);
            View imageView = new ImageView(this);
            imageView.setImageDrawable(drawable);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            this.t = new PopupWindow(imageView, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            imageView.setOnClickListener(new z(this));
            this.t.setOutsideTouchable(true);
            this.t.setBackgroundDrawable(new BitmapDrawable());
            int height = (iArr[1] + this.h.getHeight()) + UIHelper.dip2px(this, 4.0f);
            this.t.showAtLocation(getWindow().getDecorView(), 51, (iArr[0] + (this.h.getWidth() / 2)) - (drawable.getIntrinsicWidth() / 2), height);
            this.t.setOnDismissListener(new aa(this));
        }
    }
}
