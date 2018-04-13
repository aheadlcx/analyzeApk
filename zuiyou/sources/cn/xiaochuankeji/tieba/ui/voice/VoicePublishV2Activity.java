package cn.xiaochuankeji.tieba.ui.voice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.voice.VoiceService;
import cn.xiaochuankeji.tieba.background.utils.f;
import cn.xiaochuankeji.tieba.json.TopicJson;
import cn.xiaochuankeji.tieba.json.voice.AudioJson;
import cn.xiaochuankeji.tieba.json.voice.VoicePublishJson;
import cn.xiaochuankeji.tieba.network.c;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.b;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import cn.xiaochuankeji.tieba.ui.topic.holder.HolderCreator.PostFromType;
import cn.xiaochuankeji.tieba.ui.voice.widget.VoicePostItemView;
import cn.xiaochuankeji.tieba.ui.widget.g;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import rx.d;
import rx.d$a;
import rx.j;
import tv.danmaku.ijk.media.player.FFmpegMainCaller;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class VoicePublishV2Activity extends a {
    private static long[] k = cn.xiaochuankeji.tieba.background.utils.c.a.c().h();
    private static int l = 0;
    private TopicJson a;
    private String b;
    private String c;
    private long d;
    private long e;
    private long f;
    private long g;
    private LocalMedia h;
    private Uri i;
    private PostDataBean j;
    @BindView
    TextView tv_select_topic;
    @BindView
    VoicePostItemView voicePostItemView;

    public static void a(Context context, String str, String str2, long j, long j2, String str3) {
        Intent intent = new Intent(context, VoicePublishV2Activity.class);
        intent.putExtra("text", str);
        intent.putExtra("audio", str2);
        intent.putExtra("second", j);
        intent.putExtra("topicId", j2);
        intent.putExtra("topicName", str3);
        context.startActivity(intent);
    }

    public static void a(Context context, String str, String str2, long j, long j2, String str3, long j3) {
        Intent intent = new Intent(context, VoicePublishV2Activity.class);
        intent.putExtra("text", str);
        intent.putExtra("audio", str2);
        intent.putExtra("second", j);
        intent.putExtra("topicId", j2);
        intent.putExtra("topicName", str3);
        intent.putExtra("tempCoverId", j3);
        context.startActivity(intent);
    }

    protected int a() {
        return R.layout.activity_voice_publishv2;
    }

    protected void i_() {
        ButterKnife.a((Activity) this);
        this.b = getIntent().getStringExtra("audio");
        this.c = getIntent().getStringExtra("text");
        this.d = getIntent().getLongExtra("second", 0);
        this.g = getIntent().getLongExtra("tempCoverId", 0);
        this.j = new PostDataBean();
        AudioJson audioJson = new AudioJson();
        audioJson.url = this.b;
        audioJson.dur = (int) this.d;
        this.j.audio = audioJson;
        this.j.content = this.c;
        this.j.postId = (long) (Math.random() * 100.0d);
        k();
        this.voicePostItemView.setOnVoicePostItemViewListener(new VoicePostItemView.a(this) {
            final /* synthetic */ VoicePublishV2Activity a;

            {
                this.a = r1;
            }

            public void a() {
                b.f(this.a, IMediaPlayer.MEDIA_INFO_SUBTITLE_TIMED_OUT);
            }
        });
        CharSequence stringExtra = getIntent().getStringExtra("topicName");
        this.e = getIntent().getLongExtra("topicId", 0);
        if (!(TextUtils.isEmpty(stringExtra) || this.e == 0)) {
            this.tv_select_topic.setText(stringExtra);
            this.tv_select_topic.setSelected(true);
            this.tv_select_topic.setOnClickListener(null);
        }
        this.f = k[l];
        l++;
        if (l >= k.length) {
            l = 0;
        }
        if (this.g != 0) {
            this.f = this.g;
        }
        this.voicePostItemView.setLocalImage(cn.xiaochuankeji.tieba.background.f.b.a(this.f));
    }

    @OnClick
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                return;
            case R.id.tv_publish:
                e();
                return;
            case R.id.tv_select_topic:
                VoiceSelectTopicActivity.a(this, 1);
                return;
            default:
                return;
        }
    }

    private void e() {
        if (!TextUtils.isEmpty(this.b)) {
            if (this.a == null && this.e == 0) {
                VoiceSelectTopicActivity.a(this, 0);
                return;
            }
            g.a((Activity) this);
            d.b(new d$a<String>(this) {
                final /* synthetic */ VoicePublishV2Activity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void call(Object obj) {
                    a((j) obj);
                }

                public void a(j<? super String> jVar) {
                    String str = cn.xiaochuankeji.tieba.background.a.e().w() + "final.aac";
                    FFmpegMainCaller.wavToAac(this.a.b, str);
                    jVar.onNext(str);
                }
            }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.b.b<String>(this) {
                final /* synthetic */ VoicePublishV2Activity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void call(Object obj) {
                    a((String) obj);
                }

                public void a(String str) {
                    this.a.a(str);
                }
            });
        }
    }

    private void a(String str) {
        new f(str, "aac", new f.a(this) {
            final /* synthetic */ VoicePublishV2Activity a;

            {
                this.a = r1;
            }

            public void a(boolean z, String str, String str2) {
                if (z) {
                    long j;
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("localid", Long.valueOf(System.currentTimeMillis()));
                    String str3 = "tid";
                    if (this.a.e == 0) {
                        j = this.a.a.id;
                    } else {
                        j = this.a.e;
                    }
                    jSONObject.put(str3, Long.valueOf(j));
                    jSONObject.put("content", this.a.c);
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.add(Long.valueOf(this.a.f));
                    jSONObject.put("imgs", jSONArray);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("uri", str);
                    jSONObject2.put("dur", Long.valueOf(this.a.d));
                    jSONObject.put("audio", jSONObject2);
                    jSONObject.put("c_type", Integer.valueOf(2));
                    ((VoiceService) c.b(VoiceService.class)).publishVoice(jSONObject).a(rx.a.b.a.a()).b(new j<VoicePublishJson>(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ void onNext(Object obj) {
                            a((VoicePublishJson) obj);
                        }

                        public void onCompleted() {
                        }

                        public void onError(Throwable th) {
                            g.c(this.a.a);
                            cn.xiaochuankeji.tieba.background.utils.g.a("发布失败，请重试");
                        }

                        public void a(VoicePublishJson voicePublishJson) {
                            g.c(this.a.a);
                            org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.voice.a.c(voicePublishJson.post));
                            org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.topic.data.a());
                            cn.xiaochuankeji.tieba.background.utils.g.a("发帖成功");
                            this.a.a.finish();
                        }
                    });
                    return;
                }
                g.c(this.a);
                cn.xiaochuankeji.tieba.background.utils.g.a("发布失败，请重试");
            }
        }).a();
    }

    protected void onPause() {
        super.onPause();
        this.voicePostItemView.a();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            return;
        }
        if (i == IMediaPlayer.MEDIA_INFO_SUBTITLE_TIMED_OUT) {
            Iterator it = ((ArrayList) b.b(intent)).iterator();
            while (it.hasNext()) {
                LocalMedia localMedia = (LocalMedia) it.next();
                if (localMedia.type != 1 && cn.htjyb.c.a.b.c(localMedia.path)) {
                    this.h = localMedia;
                    String str = localMedia.path;
                    try {
                        Uri parse = Uri.parse("file://" + str);
                        Uri fromFile = Uri.fromFile(new File(cn.xiaochuankeji.tieba.background.a.e().B(), System.currentTimeMillis() + new File(parse.getPath()).getName()));
                        int i3 = getResources().getDisplayMetrics().widthPixels;
                        if (parse.isAbsolute()) {
                            cn.xiaochuan.image.a.b.a(this, parse, fromFile, i3, "剪裁封面", 300, 300);
                            return;
                        }
                        return;
                    } catch (Exception e) {
                        this.i = Uri.parse("file://" + str);
                        j();
                        return;
                    }
                }
            }
        } else if (i == 69) {
            this.i = cn.xiaochuan.image.a.b.a(intent);
            String path = this.i.getPath();
            if (this.h != null) {
                this.h.path = path;
                j();
            }
        } else if (i == 1) {
            this.a = (TopicJson) JSON.parseObject(intent.getStringExtra("topic"), TopicJson.class);
            if (this.a != null) {
                this.tv_select_topic.setText(this.a.topic);
                this.tv_select_topic.setSelected(true);
            }
        } else if (i == 0) {
            this.a = (TopicJson) JSON.parseObject(intent.getStringExtra("topic"), TopicJson.class);
            if (this.a != null) {
                this.tv_select_topic.setText(this.a.topic);
                this.tv_select_topic.setSelected(true);
                e();
            }
        }
    }

    private void j() {
        if (this.h != null) {
            g.a((Activity) this);
            cn.xiaochuankeji.tieba.background.upload.j jVar = new cn.xiaochuankeji.tieba.background.upload.j();
            List arrayList = new ArrayList();
            arrayList.add(this.h);
            jVar.a(arrayList, "", null, new cn.xiaochuankeji.tieba.background.upload.f(this) {
                final /* synthetic */ VoicePublishV2Activity a;

                {
                    this.a = r1;
                }

                public void a(List<Long> list, List<Long> list2, HashMap<String, LocalMedia> hashMap) {
                    if (!list2.isEmpty()) {
                        this.a.f = ((Long) list2.get(0)).longValue();
                    }
                    if (this.a.i != null) {
                        this.a.voicePostItemView.setLocalImage(this.a.i.toString());
                        org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.voice.a.d(this.a.i.toString(), this.a.f));
                    }
                    this.a.h = null;
                    g.c(this.a);
                }

                public void a(String str) {
                    cn.xiaochuankeji.tieba.background.utils.g.a(str);
                    g.c(this.a);
                }
            });
        }
    }

    private void k() {
        this.voicePostItemView.a(this.j, PostFromType.FROM_VOICEPUBLISH, false);
    }
}
