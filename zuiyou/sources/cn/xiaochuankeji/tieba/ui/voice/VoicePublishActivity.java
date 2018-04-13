package cn.xiaochuankeji.tieba.ui.voice;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.voice.VoiceService;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.background.screen.Observer;
import cn.xiaochuankeji.tieba.background.screen.Observer.ScreenStatus;
import cn.xiaochuankeji.tieba.background.utils.f;
import cn.xiaochuankeji.tieba.json.TopicJson;
import cn.xiaochuankeji.tieba.json.voice.VoicePublishJson;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.hollow.util.c;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import cn.xiaochuankeji.tieba.ui.widget.g;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
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
import tv.danmaku.ijk.media.player.IMediaPlayer$OnCompletionListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnPreparedListener;

public class VoicePublishActivity extends a implements Observer, IMediaPlayer$OnCompletionListener, IMediaPlayer$OnPreparedListener {
    private AnimationDrawable a;
    private TopicJson b;
    private String c;
    private String d;
    private long e;
    private boolean f;
    private cn.xiaochuankeji.tieba.common.d.a g;
    private long h;
    private long i;
    @BindView
    ImageView iv_anim;
    @BindView
    WebImageView iv_cover;
    @BindView
    ImageView iv_play;
    private LocalMedia j;
    private Uri k;
    private long l = 0;
    private long m = 0;
    @SuppressLint({"HandlerLeak"})
    private Handler n = new Handler(this) {
        final /* synthetic */ VoicePublishActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            this.a.tv_time.setText(c.a((int) (((System.currentTimeMillis() - this.a.l) + this.a.m) / 1000)));
            this.a.pb_play.setMax(this.a.g.getDuration());
            this.a.pb_play.setProgress(this.a.g.getCurrentPosition());
            this.a.n.sendMessageDelayed(this.a.n.obtainMessage(0), 1000);
        }
    };
    @BindView
    ProgressBar pb_play;
    @BindView
    TextView tv_select_topic;
    @BindView
    TextView tv_text;
    @BindView
    TextView tv_time;
    @BindView
    TextView tv_time_total;

    protected int a() {
        return R.layout.activity_voice_publish;
    }

    @SuppressLint({"SetTextI18n"})
    protected void i_() {
        ButterKnife.a((Activity) this);
        this.a = (AnimationDrawable) getResources().getDrawable(R.drawable.anim_voice_play);
        this.iv_anim.setImageDrawable(this.a);
        this.c = getIntent().getStringExtra("audio");
        this.d = getIntent().getStringExtra("text");
        this.e = getIntent().getLongExtra("second", 0);
        this.tv_time_total.setText(" / " + c.a((int) Math.floor((double) (((float) this.e) / 1000.0f))));
        this.tv_text.setText(this.d);
        this.tv_text.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.g = new cn.xiaochuankeji.tieba.common.d.a(this);
        this.g.a((IMediaPlayer$OnPreparedListener) this);
        this.g.a((IMediaPlayer$OnCompletionListener) this);
        CharSequence stringExtra = getIntent().getStringExtra("topicName");
        this.h = getIntent().getLongExtra("topicId", 0);
        if (!(TextUtils.isEmpty(stringExtra) || this.h == 0)) {
            this.tv_select_topic.setText(stringExtra);
            this.tv_select_topic.setSelected(true);
            this.tv_select_topic.setOnClickListener(null);
        }
        String[] g = cn.xiaochuankeji.tieba.background.utils.c.a.c().g();
        if (g != null && g.length > 0) {
            this.i = Long.valueOf(g[0]).longValue();
            this.iv_cover.setWebImage(b.a(this.i));
        }
        cn.xiaochuankeji.tieba.background.screen.a.a().a((Observer) this, (Context) this);
    }

    public void onPrepared(IMediaPlayer iMediaPlayer) {
        this.g.start();
    }

    public void onCompletion(IMediaPlayer iMediaPlayer) {
        this.tv_time.setText(c.a(0));
        this.n.removeMessages(0);
        this.iv_play.setImageResource(R.drawable.voice_play);
        this.a.stop();
        this.pb_play.setProgress(0);
        this.f = false;
        this.l = 0;
        this.m = 0;
    }

    @OnClick
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                return;
            case R.id.tv_publish:
                if (this.g.isPlaying() && this.g.canPause()) {
                    b(true);
                }
                e();
                return;
            case R.id.tv_change_cover:
                if (this.g.isPlaying() && this.g.canPause()) {
                    b(true);
                }
                cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.f(this, IMediaPlayer.MEDIA_INFO_SUBTITLE_TIMED_OUT);
                return;
            case R.id.tv_select_topic:
                if (this.g.isPlaying() && this.g.canPause()) {
                    b(true);
                }
                VoiceSelectTopicActivity.a(this, 1);
                return;
            case R.id.iv_play:
                if (!TextUtils.isEmpty(this.c)) {
                    b(this.f);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void b(boolean z) {
        if (z) {
            this.f = false;
            this.g.pause();
            this.m = (System.currentTimeMillis() - this.l) + this.m;
            this.iv_play.setImageResource(R.drawable.voice_play);
            this.n.removeMessages(0);
            this.a.stop();
            return;
        }
        this.f = true;
        this.g.a(this.c);
        this.g.start();
        this.l = System.currentTimeMillis();
        this.iv_play.setImageResource(R.drawable.voice_play_pause);
        this.n.sendMessageDelayed(this.n.obtainMessage(0), 1000 - (this.m % 1000));
        this.a.start();
    }

    private void e() {
        if (!TextUtils.isEmpty(this.c)) {
            if (this.b == null && this.h == 0) {
                VoiceSelectTopicActivity.a(this, 0);
                return;
            }
            if (this.g.isPlaying() && this.g.canPause()) {
                b(true);
            }
            g.a((Activity) this);
            d.b(new d$a<String>(this) {
                final /* synthetic */ VoicePublishActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void call(Object obj) {
                    a((j) obj);
                }

                public void a(j<? super String> jVar) {
                    String str = cn.xiaochuankeji.tieba.background.a.e().w() + "final.aac";
                    FFmpegMainCaller.wavToAac(this.a.c, str);
                    jVar.onNext(str);
                }
            }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.b.b<String>(this) {
                final /* synthetic */ VoicePublishActivity a;

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
            final /* synthetic */ VoicePublishActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z, String str, String str2) {
                if (z) {
                    long j;
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("localid", Long.valueOf(System.currentTimeMillis()));
                    String str3 = "tid";
                    if (this.a.h == 0) {
                        j = this.a.b.id;
                    } else {
                        j = this.a.h;
                    }
                    jSONObject.put(str3, Long.valueOf(j));
                    jSONObject.put("content", this.a.d);
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.add(Long.valueOf(this.a.i));
                    jSONObject.put("imgs", jSONArray);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("uri", str);
                    jSONObject2.put("dur", Long.valueOf(this.a.e));
                    jSONObject.put("audio", jSONObject2);
                    jSONObject.put("c_type", Integer.valueOf(2));
                    ((VoiceService) cn.xiaochuankeji.tieba.network.c.b(VoiceService.class)).publishVoice(jSONObject).a(rx.a.b.a.a()).b(new j<VoicePublishJson>(this) {
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

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            return;
        }
        if (i == IMediaPlayer.MEDIA_INFO_SUBTITLE_TIMED_OUT) {
            Iterator it = ((ArrayList) cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.b(intent)).iterator();
            while (it.hasNext()) {
                LocalMedia localMedia = (LocalMedia) it.next();
                if (localMedia.type != 1 && cn.htjyb.c.a.b.c(localMedia.path)) {
                    this.j = localMedia;
                    String str = localMedia.path;
                    try {
                        Uri parse = Uri.parse("file://" + str);
                        Uri fromFile = Uri.fromFile(new File(cn.xiaochuankeji.tieba.background.a.e().B(), System.currentTimeMillis() + new File(parse.getPath()).getName()));
                        int i3 = getResources().getDisplayMetrics().widthPixels;
                        if (parse.isAbsolute()) {
                            cn.xiaochuan.image.a.b.a(this, parse, fromFile, i3, "剪裁封面", 300, 170);
                            return;
                        }
                        return;
                    } catch (Exception e) {
                        this.k = Uri.parse("file://" + str);
                        j();
                        return;
                    }
                }
            }
        } else if (i == 69) {
            this.k = cn.xiaochuan.image.a.b.a(intent);
            String path = this.k.getPath();
            if (this.j != null) {
                this.j.path = path;
                j();
            }
        } else if (i == 1) {
            this.b = (TopicJson) JSON.parseObject(intent.getStringExtra("topic"), TopicJson.class);
            if (this.b != null) {
                this.tv_select_topic.setText(this.b.topic);
                this.tv_select_topic.setSelected(true);
            }
        } else if (i == 0) {
            this.b = (TopicJson) JSON.parseObject(intent.getStringExtra("topic"), TopicJson.class);
            if (this.b != null) {
                this.tv_select_topic.setText(this.b.topic);
                this.tv_select_topic.setSelected(true);
                e();
            }
        }
    }

    private void j() {
        if (this.j != null) {
            g.a((Activity) this);
            cn.xiaochuankeji.tieba.background.upload.j jVar = new cn.xiaochuankeji.tieba.background.upload.j();
            List arrayList = new ArrayList();
            arrayList.add(this.j);
            jVar.a(arrayList, "", null, new cn.xiaochuankeji.tieba.background.upload.f(this) {
                final /* synthetic */ VoicePublishActivity a;

                {
                    this.a = r1;
                }

                public void a(List<Long> list, List<Long> list2, HashMap<String, LocalMedia> hashMap) {
                    if (!list2.isEmpty()) {
                        this.a.i = ((Long) list2.get(0)).longValue();
                    }
                    if (this.a.k != null) {
                        this.a.iv_cover.setImageURI(this.a.k.toString());
                    }
                    this.a.j = null;
                    g.c(this.a);
                }

                public void a(String str) {
                    cn.xiaochuankeji.tieba.background.utils.g.a(str);
                    g.c(this.a);
                }
            });
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.g.isPlaying() && this.g.canPause()) {
            b(true);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 26 && this.g.isPlaying() && this.g.canPause()) {
            b(true);
        }
        return super.onKeyDown(i, keyEvent);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.g.g();
        this.n.removeMessages(0);
        this.a.stop();
        cn.xiaochuankeji.tieba.background.screen.a.a().b(this, this);
    }

    public void a(ScreenStatus screenStatus) {
        switch (screenStatus) {
            case SCREEN_OFF:
                if (this.g.isPlaying() && this.g.canPause()) {
                    b(true);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
