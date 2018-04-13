package qsbk.app.activity;

import android.app.Activity;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.baidu.mobstat.StatService;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.UserInfo;
import qsbk.app.utils.DebugUtil;
import qsbk.app.widget.ButtonBar;
import qsbk.app.widget.PersonalInfoView;
import qsbk.app.widget.clip.ClipImageView;

public class ImageClipActivity extends Activity {
    private static final String a = ImageClipActivity.class.getSimpleName();
    private ButtonBar b;
    private ClipImageView c;
    private PersonalInfoView d;
    private Handler e = new Handler();

    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.activity_image_clip);
        this.b = (ButtonBar) findViewById(R.id.id_top_bar);
        this.b.setOnClickListener(new om(this), new on(this));
        b();
        this.c = (ClipImageView) findViewById(R.id.src_pic);
        String stringExtra = getIntent().getStringExtra("uri");
        if (TextUtils.isEmpty(Uri.parse(stringExtra).getScheme())) {
            stringExtra = "file://" + stringExtra;
        }
        DebugUtil.debug("ImageClipActivity", stringExtra);
        FrescoImageloader.displayImage(this.c, stringExtra);
        this.d = (PersonalInfoView) findViewById(R.id.info);
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int i = rect.top;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int i2 = displayMetrics.widthPixels;
        i = displayMetrics.heightPixels - i;
        if (QsbkApp.getInstance().isMeizuVersion()) {
            i -= 100;
        }
        i = ((i - getResources().getDimensionPixelSize(R.dimen.topbar_height)) / 8) * 5;
        if (i > i2) {
            i = i2;
        }
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, -1);
        layoutParams.addRule(14);
        this.d.setLayoutParams(layoutParams);
        this.d.setView((UserInfo) getIntent().getSerializableExtra("userinfo"), 0);
    }

    protected void onResume() {
        super.onResume();
        StatService.onPageStart(this, a);
    }

    protected void onPause() {
        super.onPause();
        StatService.onPageEnd(this, a);
    }

    private Uri a() {
        File file = new File(MyInfoActivity.PHOTO_DIR_1);
        if (!file.exists()) {
            file.mkdir();
        }
        Date date = new java.sql.Date(System.currentTimeMillis());
        return Uri.fromFile(new File(MyInfoActivity.PHOTO_DIR_1, new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss").format(date) + ".jpg"));
    }

    private void b() {
    }
}
