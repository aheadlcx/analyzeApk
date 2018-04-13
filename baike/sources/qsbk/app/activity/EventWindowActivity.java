package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.baidu.mobstat.StatService;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.qiushibaike.statsdk.StatSDK;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.HttpTask;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.EventWindow;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;
import qsbk.app.utils.UIHelper;

public class EventWindowActivity extends BaseActivity {
    public static final int ACTION_FOR_JUMP = 0;
    public static final int ACTION_FOR_RESULT = 1;
    public static final int REQ_FOR_RESULT = 273;
    EventWindow a;
    Button b;
    ImageView c;
    TextView d;
    View e;
    View f;
    int g;
    DataSource<CloseableReference<CloseableImage>> h;

    public static void launch(Context context, EventWindow eventWindow) {
        Intent intent = new Intent(context, EventWindowActivity.class);
        intent.putExtra("event", eventWindow);
        context.startActivity(intent);
    }

    public static void launchForResult(Activity activity, EventWindow eventWindow) {
        Intent intent = new Intent(activity, EventWindowActivity.class);
        intent.putExtra("event", eventWindow);
        intent.putExtra("action", 1);
        activity.startActivityForResult(intent, 273);
    }

    protected int a() {
        return R.layout.activity_event_window;
    }

    protected void a(Bundle bundle) {
        this.b = (Button) findViewById(R.id.btn);
        this.c = (ImageView) findViewById(R.id.img);
        this.d = (TextView) findViewById(R.id.bottom);
        this.e = findViewById(R.id.close);
        this.f = findViewById(R.id.progress);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.a = (EventWindow) intent.getSerializableExtra("event");
        this.g = intent.getIntExtra("action", 0);
        if (this.a == null) {
            finish();
            return;
        }
        this.e.setOnClickListener(new kl(this));
        this.b.setText(this.a.btnTitle);
        this.d.setText(this.a.desc);
        this.b.setOnClickListener(new km(this));
        if (this.a.imgWidth == 0 || this.a.imgHeight == 0) {
            this.h = Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(this.a.img), null);
            this.h.subscribe(new kn(this), UiThreadImmediateExecutorService.getInstance());
        } else {
            a(this.a.imgWidth, this.a.imgHeight);
        }
        StatService.onEvent(QsbkApp.mContext, "popup_event", "show_" + this.a.id);
        StatSDK.onEvent(QsbkApp.mContext, "popup_event", "show_" + this.a.id);
    }

    private void a(int i, int i2) {
        float f = (((float) i) * 1.0f) / ((float) i2);
        LayoutParams layoutParams = this.c.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(0, 0);
        }
        int dip2px = UIHelper.dip2px(this, 280.0f);
        layoutParams.width = dip2px;
        layoutParams.height = (int) (((float) dip2px) / f);
        this.c.setLayoutParams(layoutParams);
        FrescoImageloader.displayImage(this.c, this.a.img, TileBackground.getBackgroud(this, BgImageType.ARTICLE));
    }

    private void d() {
        new HttpTask(null, Constants.ACTIVITY_WINDOW_NEW, new ko(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        this.f.setVisibility(0);
    }

    private void e() {
        if (this.g == 0) {
            this.a.jumpTo(this);
            StatService.onEvent(QsbkApp.mContext, "popup_event", "click_" + this.a.id);
            StatSDK.onEvent(QsbkApp.mContext, "popup_event", "click_" + this.a.id);
        } else {
            setResult(-1);
        }
        finish();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.h != null) {
            this.h.close();
        }
    }
}
