package cn.xiaochuankeji.tieba.ui.videomaker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.d.i;
import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.background.post.PostUgcVideo;
import cn.xiaochuankeji.tieba.background.post.PostUgcVideo.b;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.background.topic.TopicHistoryRecordManager;
import cn.xiaochuankeji.tieba.background.upload.j;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.topic.QueryFobiddenJson;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.mediabrowse.MediaBrowseWhenSelectActivity;
import cn.xiaochuankeji.tieba.ui.publish.e;
import cn.xiaochuankeji.tieba.ui.publish.e.a;
import cn.xiaochuankeji.tieba.ui.publish.selecttopic.SelectTopicActivity;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import cn.xiaochuankeji.tieba.ui.widget.f;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.greenrobot.eventbus.c;

public class MainVideoPublishActivity extends h implements b, a {
    private h d;
    private String[] e;
    private Topic f;
    private EditText g;
    private TextView h;
    private WebImageView i;
    private e j;
    private boolean k;
    private j l;
    private Handler m = new Handler();
    private Runnable n;
    private int o;

    public static void a(Activity activity, h hVar, String[] strArr, int i) {
        Intent intent = new Intent(activity, MainVideoPublishActivity.class);
        intent.putExtra("key_video_info", hVar);
        intent.putExtra("key_video_texts", strArr);
        activity.startActivityForResult(intent, i);
    }

    protected int a() {
        return R.layout.activity_main_video_publish;
    }

    protected void i_() {
        this.g = (EditText) findViewById(R.id.edit_content);
        if (this.e != null && this.e.length > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < this.e.length; i++) {
                if (i > 0) {
                    stringBuilder.append(" ");
                }
                stringBuilder.append(this.e[i].replaceAll("\n", ""));
            }
            this.g.setText(stringBuilder.toString());
            this.g.setSelection(this.g.getText().length());
        }
        this.h = (TextView) findViewById(R.id.btn_select_topic);
        this.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MainVideoPublishActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                SelectTopicActivity.a(this.a, 100, 0);
            }
        });
        v();
        this.i = (WebImageView) findViewById(R.id.ugcvideo_cover);
        this.i.setImagePath(this.d.d);
        this.i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MainVideoPublishActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                PictureImpl a = cn.xiaochuankeji.tieba.background.a.f().a(this.a.d.a, Type.kVideo, 0);
                ArrayList arrayList = new ArrayList();
                arrayList.add(a);
                MediaBrowseWhenSelectActivity.a(this.a, arrayList, null, 0);
            }
        });
        this.j = new e(this, this);
    }

    protected boolean a(Bundle bundle) {
        Intent intent = getIntent();
        this.d = (h) intent.getParcelableExtra("key_video_info");
        this.e = intent.getStringArrayExtra("key_video_texts");
        this.f = this.d.i;
        return true;
    }

    public boolean h() {
        return false;
    }

    public void onBackPressed() {
        if (!l() && !this.j.c()) {
            if (TextUtils.isEmpty(this.g.getText().toString().trim())) {
                finish();
            } else {
                f.a("提示", "确定放弃发表？", this, new f.a(this) {
                    final /* synthetic */ MainVideoPublishActivity a;

                    {
                        this.a = r1;
                    }

                    public void a(boolean z) {
                        if (z) {
                            this.a.finish();
                        }
                    }
                });
            }
        }
    }

    public void s() {
        j();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.m.removeCallbacksAndMessages(null);
    }

    private void j() {
        this.g.postDelayed(new Runnable(this) {
            final /* synthetic */ MainVideoPublishActivity a;

            {
                this.a = r1;
            }

            public void run() {
                cn.htjyb.c.a.a(this.a);
            }
        }, 500);
        this.j.a("正在上传视频", 10, 0);
        this.j.a();
        this.k = false;
        if (this.f != null) {
            TopicHistoryRecordManager.getInstance(TopicHistoryRecordManager.Type.kSelect).insert(this.f);
            new cn.xiaochuankeji.tieba.api.topic.b().b(this.f._topicID).a(rx.a.b.a.a()).b(new rx.j<QueryFobiddenJson>(this) {
                final /* synthetic */ MainVideoPublishActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((QueryFobiddenJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    this.a.j.b();
                }

                public void a(QueryFobiddenJson queryFobiddenJson) {
                    if (!this.a.k) {
                        if (queryFobiddenJson == null || !queryFobiddenJson.isFobidden) {
                            this.a.k();
                            return;
                        }
                        g.a("您在该话题内被禁止发帖，请尝试其他话题");
                        this.a.j.b();
                    }
                }
            });
            return;
        }
        k();
    }

    private void k() {
        String trim = this.g.getText().toString().trim();
        this.l = new j();
        new PostUgcVideo(this.f == null ? 0 : this.f._topicID, trim, this.d.a, this.d.d, this.e, this.d.h, this.d.d(), this.l).a((b) this, new cn.xiaochuankeji.tieba.background.upload.b(this) {
            final /* synthetic */ MainVideoPublishActivity a;

            {
                this.a = r1;
            }

            public void a(long j, long j2, int i) {
                this.a.j.a("正在上传视频", (int) j, (int) j2);
            }
        }, new cn.xiaochuankeji.tieba.background.upload.f(this) {
            final /* synthetic */ MainVideoPublishActivity a;

            {
                this.a = r1;
            }

            public void a(List<Long> list, List<Long> list2, HashMap<String, LocalMedia> hashMap) {
            }

            public void a(String str) {
                g.a("上传失败");
            }
        });
    }

    private void v() {
        if (this.f != null) {
            this.h.setText(this.f._topicName);
            this.h.setSelected(true);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100 && i2 == -1) {
            this.f = new Topic(intent);
            v();
        }
    }

    public void a(boolean z, final String str, final Moment moment) {
        if (z) {
            this.o = 0;
            this.n = new Runnable(this) {
                final /* synthetic */ MainVideoPublishActivity b;

                public void run() {
                    this.b.o = this.b.o + 1;
                    if (this.b.o <= 30) {
                        this.b.j.a("正在发帖", 30, this.b.o);
                        this.b.m.post(this.b.n);
                        return;
                    }
                    j.d();
                    j.a(this.b.d);
                    this.b.j.b();
                    Intent intent = new Intent();
                    intent.putExtra("key_published_main_video", moment);
                    this.b.setResult(-1, intent);
                    if (this.b.d.f > 0) {
                        c.a().d(new i());
                    }
                    c.a().d(new cn.xiaochuankeji.tieba.ui.topic.data.a());
                    this.b.finish();
                }
            };
            this.m.post(this.n);
            return;
        }
        this.m.post(new Runnable(this) {
            final /* synthetic */ MainVideoPublishActivity b;

            public void run() {
                this.b.j.b();
                g.a(str);
            }
        });
    }

    public void l_() {
        this.k = true;
        if (this.j.c()) {
            this.j.b();
        }
        if (this.l != null) {
            this.l.a();
            this.l = null;
        }
    }
}
