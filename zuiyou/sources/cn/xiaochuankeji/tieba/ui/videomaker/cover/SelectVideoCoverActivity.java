package cn.xiaochuankeji.tieba.ui.videomaker.cover;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.common.medialib.g;
import cn.xiaochuankeji.tieba.common.medialib.h;
import cn.xiaochuankeji.tieba.common.medialib.j;
import cn.xiaochuankeji.tieba.common.medialib.j.b;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.videomaker.i;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.c;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.d;
import cn.xiaochuankeji.tieba.ui.widget.AspectRatioFrameLayout;
import org.json.JSONException;
import org.json.JSONObject;

public class SelectVideoCoverActivity extends a {
    private AspectRatioFrameLayout a;
    private ImageView b;
    private VideoFrameSeekBar c;
    private String d;
    private String e;
    private int f;
    private j g;
    private d h;

    public static void a(Activity activity, String str, String str2, int i, int i2) {
        Intent intent = new Intent(activity, SelectVideoCoverActivity.class);
        intent.putExtra("key_video_path", str);
        intent.putExtra("key_stickers_json", str2);
        intent.putExtra("key_initial_time", i);
        activity.startActivityForResult(intent, i2);
    }

    protected int a() {
        return R.layout.activity_video_cover_select;
    }

    protected boolean a(Bundle bundle) {
        Intent intent = getIntent();
        this.d = intent.getStringExtra("key_video_path");
        this.e = intent.getStringExtra("key_stickers_json");
        this.f = intent.getIntExtra("key_initial_time", 0);
        try {
            this.h = d.a(this, new JSONObject(this.e));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.g = new j(this.d);
        this.g.a(new h(this) {
            final /* synthetic */ SelectVideoCoverActivity a;

            {
                this.a = r1;
            }

            public void a(int i, g gVar) {
                c a = this.a.h.a(i);
                gVar.a = a.a();
                gVar.b = a.b();
            }
        });
        this.g.a(new b(this) {
            final /* synthetic */ SelectVideoCoverActivity a;

            {
                this.a = r1;
            }

            public void a(j jVar) {
                if (jVar.a() > 0) {
                    this.a.a(this.a.f);
                } else {
                    cn.xiaochuankeji.tieba.background.utils.g.a("视频获取失败");
                }
            }
        });
        return true;
    }

    protected void i_() {
        findViewById(R.id.btn_back).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SelectVideoCoverActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.onBackPressed();
            }
        });
        findViewById(R.id.btn_finish).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SelectVideoCoverActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int progress = this.a.c.getProgress();
                if (progress == -1) {
                    cn.xiaochuankeji.tieba.background.utils.g.a("正在截取封面，请稍候...");
                } else if (progress < 0) {
                    cn.xiaochuankeji.tieba.background.utils.g.a("截取封面失败，请重试...");
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("key_selected_time", this.a.c.getProgress());
                    this.a.setResult(-1, intent);
                    this.a.finish();
                }
            }
        });
        this.a = (AspectRatioFrameLayout) findViewById(R.id.video_container);
        this.a.setAspectRatio(i.a());
        this.b = (ImageView) findViewById(R.id.video_frame);
        this.c = (VideoFrameSeekBar) findViewById(R.id.video_frame_seekbar);
        this.c.a(this, this.d, this.e, this.f);
        this.c.setListener(new VideoFrameSeekBar.a(this) {
            final /* synthetic */ SelectVideoCoverActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                this.a.a(i);
            }
        });
    }

    public boolean h() {
        return false;
    }

    protected void onDestroy() {
        super.onDestroy();
        this.g.b();
        this.c.a();
    }

    private void a(int i) {
        this.g.a(i, -1, true, new j.a(this) {
            final /* synthetic */ SelectVideoCoverActivity a;

            {
                this.a = r1;
            }

            public void a(int i, Bitmap bitmap) {
                this.a.b.setImageBitmap(bitmap);
                this.a.c.a(i, bitmap);
                if (this.a.c.getProgress() == -2) {
                    cn.xiaochuankeji.tieba.background.utils.g.a("截取封面为空，请重新选择...");
                }
            }
        });
    }
}
