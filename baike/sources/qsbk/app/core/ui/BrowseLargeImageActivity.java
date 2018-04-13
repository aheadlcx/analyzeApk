package qsbk.app.core.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.io.File;
import qsbk.app.core.R;
import qsbk.app.core.ui.base.BaseActivity;

public class BrowseLargeImageActivity extends BaseActivity {
    protected RelativeLayout a;
    protected SimpleDraweeView b;
    private ProgressBar c;
    private ImageView d;
    private Bitmap e;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    protected int getLayoutId() {
        return R.layout.activity_browse_large_image;
    }

    protected void initView() {
        this.a = (RelativeLayout) findViewById(R.id.wrapper);
        this.b = (SimpleDraweeView) findViewById(R.id.iv_float_image);
        this.c = (ProgressBar) findViewById(R.id.loading);
        this.d = (ImageView) findViewById(R.id.iv_download);
        this.d.setOnClickListener(new a(this));
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            int i = getResources().getDisplayMetrics().widthPixels;
            int i2 = getResources().getDisplayMetrics().heightPixels;
            LayoutParams layoutParams = (LayoutParams) this.b.getLayoutParams();
            layoutParams.width = i;
            layoutParams.height = i2;
            this.b.setLayoutParams(layoutParams);
        }
    }

    protected void initData() {
        String str = null;
        Intent intent = getIntent();
        if (intent != null) {
            str = intent.getStringExtra("url");
        }
        if (TextUtils.isEmpty(str)) {
            this.c.setVisibility(8);
        } else {
            this.c.setVisibility(0);
            ControllerListener bVar = new b(this);
            int indexOf = str.indexOf("?");
            if (indexOf != -1) {
                str = str.substring(0, indexOf);
            }
            Uri parse = Uri.parse(str);
            ImageRequest build = ImageRequestBuilder.newBuilderWithSource(parse).setLocalThumbnailPreviewsEnabled(true).build();
            this.b.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setControllerListener(bVar)).setImageRequest(build)).setOldController(this.b.getController())).setUri(parse).build());
            Fresco.getImagePipeline().fetchDecodedImage(build, this).subscribe(new c(this), CallerThreadExecutor.getInstance());
        }
        this.a.setOnClickListener(new d(this));
    }

    protected void a() {
        new Thread(new e(this)).start();
    }

    private static void b(Context context, File file, String str) {
        if (context != null) {
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(Uri.fromFile(file));
            context.sendBroadcast(intent);
        }
    }
}
