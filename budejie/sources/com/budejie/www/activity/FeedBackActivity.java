package com.budejie.www.activity;

import android.content.Intent;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.base.BaseTitleActivity;
import com.budejie.www.util.aa;
import com.budejie.www.util.h;
import java.io.File;

public class FeedBackActivity extends BaseTitleActivity implements MediaScannerConnectionClient, OnClickListener {
    private final String a = "FeedBackActivity";
    private String b = (Environment.getExternalStorageDirectory().toString() + "/budejie/");
    private String c = "feed_back_QR_Code.jpg";
    private ImageView d;
    private TextView e;
    private Toast i = null;
    private MediaScannerConnection j;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        e(R.layout.activity_feed_back);
        a();
    }

    private void a() {
        a((int) R.string.yijian);
        this.d = (ImageView) findViewById(R.id.feed_back_image);
        this.e = (TextView) findViewById(R.id.feed_back_save);
        this.e.setOnClickListener(this);
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.feed_back_save:
                if (h.a(((BitmapDrawable) getResources().getDrawable(R.drawable.feed_back_code)).getBitmap(), new File(this.b + this.c), CompressFormat.JPEG, 100)) {
                    b();
                    d(getResources().getString(R.string.save_successed));
                    return;
                }
                d(getResources().getString(R.string.save_failed));
                return;
            default:
                return;
        }
    }

    private void d(String str) {
        if (this.i == null) {
            this.i = Toast.makeText(this, str, 1);
        } else {
            this.i.setText(str);
        }
        this.i.show();
    }

    private void b() {
        try {
            Uri fromFile = Uri.fromFile(new File(this.b + this.c));
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(fromFile);
            sendBroadcast(intent);
        } catch (Exception e) {
            if (this.j != null) {
                this.j.disconnect();
            }
            this.j = new MediaScannerConnection(this, this);
            this.j.connect();
        }
    }

    public void onMediaScannerConnected() {
        aa.a("FeedBackActivity", "onMediaScannerConnected:" + this.b + this.c);
        try {
            this.j.scanFile(this.b + this.c, "image/*");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public void onScanCompleted(String str, Uri uri) {
        aa.a("FeedBackActivity", "onScanCompleted");
        try {
            this.j.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
