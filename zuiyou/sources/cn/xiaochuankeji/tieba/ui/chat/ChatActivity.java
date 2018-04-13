package cn.xiaochuankeji.tieba.ui.chat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.dreamtobe.kpswitch.widget.KPSwitchPanelLinearLayout;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.aop.permission.PermissionItem;
import cn.xiaochuankeji.aop.permission.e;
import cn.xiaochuankeji.aop.permission.f;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.i;
import cn.xiaochuankeji.tieba.push.api.ChatSyncService;
import cn.xiaochuankeji.tieba.push.data.XMessage;
import cn.xiaochuankeji.tieba.push.data.XSession;
import cn.xiaochuankeji.tieba.ui.base.MainActivity;
import cn.xiaochuankeji.tieba.ui.base.d;
import cn.xiaochuankeji.tieba.ui.chat.a.c;
import cn.xiaochuankeji.tieba.ui.hollow.detail.HollowDetailActivity;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import cn.xiaochuankeji.tieba.widget.record.ChatRecordLayout;
import cn.xiaochuankeji.tieba.widget.ripple.RippleBackground;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.sdk.android.mns.common.MNSConstants;
import com.iflytek.aiui.AIUIConstant;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.a.h;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import tv.danmaku.ijk.media.player.FFmpegMediaMetadataRetriever;

public class ChatActivity extends d {
    private static final a d = null;
    private cn.xiaochuankeji.tieba.ui.chat.adapter.a a = new cn.xiaochuankeji.tieba.ui.chat.adapter.a();
    private c b = new c();
    private boolean c;
    @BindView
    ChatRecordLayout chatRecordLayout;
    @BindView
    EditText input;
    @BindView
    View more;
    @BindView
    KPSwitchPanelLinearLayout panelLayout;
    @BindView
    RecyclerView recycler;
    @BindView
    SmartRefreshLayout refreshLayout;
    @BindView
    RippleBackground rippleBackground;
    @BindView
    View start_voice;
    @BindView
    AppCompatTextView title;
    @BindView
    AppCompatTextView title_extra;
    @BindView
    AppCompatTextView voiceNotifyMsg;
    @BindView
    TextView voiceTouchNotify;
    @BindView
    AppCompatImageView voice_recorder;

    static {
        b();
    }

    private static void b() {
        b bVar = new b("ChatActivity.java", ChatActivity.class);
        d = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.chat.ChatActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 116);
    }

    static final void a(ChatActivity chatActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        int i;
        boolean z = false;
        chatActivity.getWindow().setBackgroundDrawable(null);
        super.onCreate(bundle);
        if (bundle != null) {
            bundle.setClassLoader(XSession.class.getClassLoader());
        }
        chatActivity.setContentView((int) R.layout.activity_new_chat);
        ButterKnife.a((Activity) chatActivity);
        chatActivity.recycler.setOnTouchListener(new OnTouchListener(chatActivity) {
            final /* synthetic */ ChatActivity a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    cn.dreamtobe.kpswitch.b.a.b(this.a.panelLayout);
                }
                return false;
            }
        });
        chatActivity.refreshLayout.b(true);
        chatActivity.refreshLayout.a(false);
        chatActivity.refreshLayout.a(new com.scwang.smartrefresh.layout.e.c(chatActivity) {
            final /* synthetic */ ChatActivity a;

            {
                this.a = r1;
            }

            public void a_(h hVar) {
                this.a.a();
            }
        });
        chatActivity.voice_recorder.setVisibility(0);
        chatActivity.a.a(chatActivity.b);
        chatActivity.recycler.setHasFixedSize(false);
        i.a(chatActivity.recycler);
        chatActivity.recycler.setItemAnimator(new cn.xiaochuankeji.tieba.ui.widget.a.a());
        chatActivity.recycler.addItemDecoration(new cn.xiaochuankeji.tieba.widget.a());
        final LayoutManager linearLayoutManager = new LinearLayoutManager(chatActivity);
        linearLayoutManager.setOrientation(1);
        linearLayoutManager.setInitialPrefetchItemCount(8);
        chatActivity.recycler.setLayoutManager(linearLayoutManager);
        chatActivity.recycler.setAdapter(chatActivity.a);
        cn.dreamtobe.kpswitch.b.c.a(chatActivity, chatActivity.panelLayout, new cn.dreamtobe.kpswitch.b.c.b(chatActivity) {
            final /* synthetic */ ChatActivity b;

            public void a(boolean z) {
                if (z) {
                    linearLayoutManager.scrollToPosition(this.b.a.getItemCount() - 1);
                }
            }
        });
        chatActivity.panelLayout.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(chatActivity) {
            final /* synthetic */ ChatActivity a;

            {
                this.a = r1;
            }

            public void onGlobalLayout() {
                this.a.voice_recorder.setImageResource(this.a.panelLayout.isShown() ? R.drawable.ic_keyboard : R.drawable.ic_record_voice);
            }
        });
        cn.dreamtobe.kpswitch.b.a.a(chatActivity.panelLayout, chatActivity.voice_recorder, chatActivity.input, new cn.dreamtobe.kpswitch.b.a.a(chatActivity) {
            final /* synthetic */ ChatActivity b;

            public void a(boolean z) {
                if (z) {
                    linearLayoutManager.scrollToPosition(this.b.a.getItemCount() - 1);
                    if (!f.a()) {
                        PermissionItem permissionItem = new PermissionItem("android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE");
                        permissionItem.runIgnorePermission = false;
                        permissionItem.settingText = "设置";
                        permissionItem.deniedMessage = "开启以下权限才能正常发布语音内容";
                        permissionItem.needGotoSetting = true;
                        cn.xiaochuankeji.aop.permission.a.a(this.b).a(permissionItem, new e(this) {
                            final /* synthetic */ AnonymousClass15 a;

                            {
                                this.a = r1;
                            }

                            public void permissionGranted() {
                            }

                            public void permissionDenied() {
                                g.a("开启以下权限才能正常发布语音内容");
                            }
                        });
                    }
                }
            }
        });
        final XSession xSession = (XSession) chatActivity.getIntent().getParcelableExtra("session");
        Bundle extras = chatActivity.getIntent().getExtras();
        if (extras != null) {
            chatActivity.c = extras.getBoolean("OPEN_FROM_NOTIFICATION");
        }
        if (chatActivity.c) {
            cn.xiaochuankeji.tieba.background.h.d.a().a(4);
            cn.xiaochuankeji.tieba.background.h.d.a().d();
        }
        if (xSession.isAnonymous()) {
            chatActivity.title.setVisibility(0);
            chatActivity.title_extra.setVisibility(0);
            chatActivity.title.setText(String.valueOf(xSession.x_other.name));
            chatActivity.title_extra.setText(String.format("来自树洞: %s >", new Object[]{xSession.x_room.room_name}));
            chatActivity.title_extra.setEllipsize(TruncateAt.MIDDLE);
            c.a.b.a(chatActivity.title, 0, 0, xSession.x_other.gender == 1 ? R.drawable.sexual_male : R.drawable.sexual_female, 0);
        } else {
            chatActivity.title.setVisibility(0);
            chatActivity.title_extra.setVisibility(8);
            chatActivity.title.setText(String.valueOf(xSession.x_other.name));
        }
        View view = chatActivity.more;
        if (xSession.isOfficial()) {
            i = 8;
        } else {
            i = 0;
        }
        view.setVisibility(i);
        chatActivity.a.a(xSession);
        chatActivity.input.setHint("打个招呼吧...");
        chatActivity.input.setOnEditorActionListener(new OnEditorActionListener(chatActivity) {
            final /* synthetic */ ChatActivity a;

            {
                this.a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (keyEvent != null && keyEvent.getKeyCode() == 66) {
                    return true;
                }
                return false;
            }
        });
        if (extras != null) {
            z = extras.getBoolean("_show_keyboard", false);
        }
        if (z) {
            rx.a.b.a.a().a().a(new rx.b.a(chatActivity) {
                final /* synthetic */ ChatActivity a;

                {
                    this.a = r1;
                }

                public void call() {
                    cn.dreamtobe.kpswitch.b.a.a(this.a.panelLayout, this.a.input);
                }
            }, 200, TimeUnit.MILLISECONDS);
        }
        cn.xiaochuankeji.tieba.push.d.a().c(xSession);
        rx.d.a(Boolean.valueOf(true)).d(new rx.b.g<Boolean, List<cn.xiaochuankeji.tieba.push.data.a>>(chatActivity) {
            final /* synthetic */ ChatActivity b;

            public /* synthetic */ Object call(Object obj) {
                return a((Boolean) obj);
            }

            public List<cn.xiaochuankeji.tieba.push.data.a> a(Boolean bool) {
                return cn.xiaochuankeji.tieba.push.b.e.c(xSession);
            }
        }).b(rx.f.a.c()).c(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.e<List<cn.xiaochuankeji.tieba.push.data.a>>(chatActivity) {
            final /* synthetic */ ChatActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((List) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(List<cn.xiaochuankeji.tieba.push.data.a> list) {
                if (this.a.a != null && !this.a.isFinishing()) {
                    this.a.a.a((List) list);
                    this.a.recycler.scrollToPosition(Math.max(0, this.a.a.getItemCount() - 1));
                    cn.xiaochuankeji.tieba.push.d.a().a((XSession) this.a.getIntent().getParcelableExtra("session"));
                }
            }
        });
        chatActivity.chatRecordLayout.a(chatActivity.start_voice);
        chatActivity.chatRecordLayout.a(chatActivity.b);
        chatActivity.chatRecordLayout.setOnOnRecordListener(new cn.xiaochuankeji.tieba.widget.record.a(chatActivity) {
            final /* synthetic */ ChatActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.voiceNotifyMsg.setVisibility(0);
                this.a.voiceTouchNotify.setVisibility(4);
                this.a.rippleBackground.a();
                if (this.a.g()) {
                    com.c.a.c.b(this.a).b(false);
                }
            }

            public void a(String str) {
                this.a.rippleBackground.b();
                this.a.voiceNotifyMsg.setVisibility(4);
                this.a.voiceTouchNotify.setVisibility(0);
                if (!TextUtils.isEmpty(str)) {
                    this.a.a(new File(str));
                }
                if (this.a.g()) {
                    com.c.a.c.b(this.a).b(true);
                }
            }

            public void b() {
                this.a.rippleBackground.b();
                this.a.voiceTouchNotify.setVisibility(0);
                this.a.voiceNotifyMsg.setVisibility(4);
                if (this.a.g()) {
                    com.c.a.c.b(this.a).b(true);
                }
            }
        });
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(d, this, this, bundle);
        cn.xiaochuankeji.aop.permission.c.c().a(new a(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        com.izuiyou.a.a.b.a();
    }

    protected void onResume() {
        super.onResume();
        if (getIntent().getBooleanExtra("_need_refresh", false)) {
            getIntent().putExtra("_need_refresh", false);
            this.a.notifyDataSetChanged();
        }
        cn.xiaochuankeji.tieba.push.d.c.a((XSession) getIntent().getParcelableExtra("session"));
    }

    protected void onPause() {
        super.onPause();
        cn.xiaochuankeji.tieba.push.d.c.b((XSession) getIntent().getParcelableExtra("session"));
        cn.htjyb.c.a.a((Activity) this);
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            bundle.putParcelable("session", (XSession) getIntent().getParcelableExtra("session"));
        }
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (bundle != null) {
            getIntent().putExtra("session", (XSession) bundle.getParcelable("session"));
        }
    }

    protected void onStop() {
        super.onStop();
        if (this.b != null) {
            this.b.b();
        }
    }

    protected void onDestroy() {
        if (this.a != null) {
            this.a.b();
        }
        this.b.a();
        super.onDestroy();
    }

    @OnClick
    public void navClickEvent(View view) {
        final XSession xSession = (XSession) getIntent().getParcelableExtra("session");
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                return;
            case R.id.title_container:
                if (xSession.session_type == 2) {
                    HollowDetailActivity.a((Context) this, xSession.x_room.room_id, "chat");
                    return;
                }
                return;
            case R.id.more:
                new cn.xiaochuankeji.tieba.ui.chat.a.a(this, new cn.xiaochuankeji.tieba.ui.chat.a.a.a(this) {
                    final /* synthetic */ ChatActivity b;

                    public void a() {
                        cn.xiaochuankeji.tieba.ui.widget.g.a(this.b);
                    }

                    public void b() {
                        cn.xiaochuankeji.tieba.ui.widget.g.c(this.b);
                    }

                    public void c() {
                        cn.xiaochuankeji.tieba.push.b.e.h(xSession);
                        cn.xiaochuankeji.tieba.background.h.d.a().d();
                        cn.dreamtobe.kpswitch.b.a.b(this.b.panelLayout);
                        this.b.finish();
                    }

                    public void d() {
                    }
                }).a(xSession, this.more, true, false);
                return;
            default:
                return;
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || keyEvent.getKeyCode() != 4 || this.panelLayout.getVisibility() != 0) {
            return super.dispatchKeyEvent(keyEvent);
        }
        cn.dreamtobe.kpswitch.b.a.b(this.panelLayout);
        return true;
    }

    public void onBackPressed() {
        cn.dreamtobe.kpswitch.b.a.b(this.panelLayout);
        if (this.c) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("DEFAULT_TAB_INDEX", 3);
            intent.putExtra("DEFAULT_SEGMENT_IDX", 1);
            intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
            intent.setFlags(67108864);
            startActivity(intent);
        }
        super.onBackPressed();
    }

    private void a() {
        final XSession xSession = (XSession) getIntent().getParcelableExtra("session");
        long j = xSession.x_sid;
        final long d = cn.xiaochuankeji.tieba.push.b.e.d(xSession);
        ((ChatSyncService) cn.xiaochuankeji.tieba.network.c.b(ChatSyncService.class)).message(cn.xiaochuankeji.tieba.push.d.a(j, 0, d, xSession.session_id, xSession.session_type, xSession.session_type == 2 ? xSession.x_mask.id : -1)).d(new rx.b.g<JSONObject, Boolean>(this) {
            final /* synthetic */ ChatActivity c;

            public /* synthetic */ Object call(Object obj) {
                return a((JSONObject) obj);
            }

            public Boolean a(JSONObject jSONObject) {
                JSONArray jSONArray = jSONObject.getJSONArray(MNSConstants.LOCATION_MESSAGES);
                boolean z = (jSONArray == null || jSONArray.isEmpty() || !cn.xiaochuankeji.tieba.push.b.e.a(xSession, 0, d, jSONArray, "CONTINUE".equalsIgnoreCase(jSONObject.getString("state")))) ? false : true;
                return Boolean.valueOf(z);
            }
        }).d(new rx.b.g<Boolean, List<cn.xiaochuankeji.tieba.push.data.a>>(this) {
            final /* synthetic */ ChatActivity b;

            public /* synthetic */ Object call(Object obj) {
                return a((Boolean) obj);
            }

            public List<cn.xiaochuankeji.tieba.push.data.a> a(Boolean bool) {
                long j = Long.MAX_VALUE;
                if (!(this.b.a == null || this.b.a.a().isEmpty())) {
                    cn.xiaochuankeji.tieba.push.data.a c = this.b.a.c();
                    if (c != null) {
                        j = c.j;
                    }
                }
                return cn.xiaochuankeji.tieba.push.b.e.a(xSession, 0, j);
            }
        }).a(rx.a.b.a.a()).a(new rx.e<List<cn.xiaochuankeji.tieba.push.data.a>>(this) {
            final /* synthetic */ ChatActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((List) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                th.printStackTrace();
                g.a("没有更早的消息了");
                if (this.a.refreshLayout != null) {
                    this.a.refreshLayout.m();
                }
            }

            public void a(List<cn.xiaochuankeji.tieba.push.data.a> list) {
                if (this.a.a != null) {
                    if (list.isEmpty()) {
                        g.a("没有更早的消息了");
                    } else {
                        this.a.a.b((List) list);
                        int size = list.size() > 0 ? list.size() - 1 : 0;
                        if (size > 0) {
                            this.a.recycler.scrollToPosition(size);
                        }
                    }
                    if (this.a.refreshLayout != null) {
                        this.a.refreshLayout.m();
                    }
                }
            }
        });
    }

    @l(a = ThreadMode.MAIN)
    public void chatUpdate(cn.xiaochuankeji.tieba.push.c.b bVar) {
        XSession xSession = (XSession) getIntent().getParcelableExtra("session");
        if (xSession.x_sid == bVar.b) {
            List b = cn.xiaochuankeji.tieba.push.d.a().b(xSession);
            if (isFinishing()) {
                this.a.a().clear();
                this.a.a().addAll(b);
                getIntent().putExtra("_need_refresh", true);
                return;
            }
            if (this.a != null) {
                this.a.a(b);
                this.recycler.scrollToPosition(this.a.getItemCount() - 1);
            }
            getIntent().putExtra("_need_refresh", false);
        }
    }

    @l(a = ThreadMode.MAIN)
    public void chatInsert(cn.xiaochuankeji.tieba.push.c.a aVar) {
        if (isFinishing() || aVar.a == null) {
            getIntent().putExtra("_need_refresh", true);
            return;
        }
        XSession xSession = (XSession) getIntent().getParcelableExtra("session");
        if (xSession.x_sid == aVar.a.a && !this.a.a(aVar.a.j)) {
            this.a.a(aVar.a);
            this.recycler.scrollToPosition(this.a.getItemCount() - 1);
            cn.xiaochuankeji.tieba.push.d.a().c(xSession);
        }
    }

    @OnClick
    public void sendText() {
        if (cn.xiaochuankeji.tieba.ui.auth.a.a(this, "chat_login", 6)) {
            String obj = this.input.getText().toString();
            if (TextUtils.isEmpty(obj.trim())) {
                g.a("不能发送空消息");
            } else if (!TextUtils.isEmpty(obj)) {
                this.input.setText("");
                a(obj, 1);
            }
        }
    }

    @OnClick
    public void sendImage() {
        if (cn.xiaochuankeji.tieba.ui.auth.a.a(this, "chat_login", 6)) {
            PermissionItem permissionItem = new PermissionItem("android.permission.READ_EXTERNAL_STORAGE");
            permissionItem.runIgnorePermission = false;
            permissionItem.settingText = "设置";
            permissionItem.deniedMessage = "开启以下权限才能正常浏览图片和视频";
            permissionItem.needGotoSetting = true;
            cn.xiaochuankeji.aop.permission.a.a((Context) this).a(permissionItem, new e(this) {
                final /* synthetic */ ChatActivity a;

                {
                    this.a = r1;
                }

                public void permissionGranted() {
                    cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.c(this.a, 32);
                }

                public void permissionDenied() {
                    g.a("开启以下权限才能正常浏览图片和视频");
                }
            });
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 32) {
            for (LocalMedia a : cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.b(intent)) {
                a(a);
            }
        }
    }

    private void a(LocalMedia localMedia) {
        if (localMedia != null && !TextUtils.isEmpty(localMedia.path)) {
            if (new File(localMedia.path).exists()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("w", Integer.valueOf(localMedia.width));
                jSONObject.put("h", Integer.valueOf(localMedia.height));
                if (TextUtils.isEmpty(localMedia.mimeType) || !localMedia.mimeType.toLowerCase().contains("gif")) {
                    jSONObject.put("fmt", "jpeg");
                } else {
                    jSONObject.put("fmt", "gif");
                }
                jSONObject.put(AIUIConstant.RES_TYPE_PATH, localMedia.path);
                a(jSONObject.toJSONString(), 2);
                return;
            }
            g.a("图片文件不存在");
        }
    }

    private void a(String str, int i) {
        XSession xSession = (XSession) getIntent().getParcelableExtra("session");
        if ("//我要重新获取私信".equals(str)) {
            cn.xiaochuankeji.tieba.push.b.e.f();
            finish();
            return;
        }
        long b = cn.xiaochuankeji.tieba.push.b.e.b();
        XMessage xMessage = new XMessage();
        xMessage.msg_id = b;
        xMessage.content = str;
        xMessage.msg_type = i;
        xMessage.msg_uid = xSession.x_mask.id;
        xMessage.time = System.currentTimeMillis() / 1000;
        xSession.session_local_id = b;
        xSession.time = xMessage.time;
        xSession.x_last_msg_id = xMessage.msg_id;
        cn.xiaochuankeji.tieba.push.b.e.g(xSession);
        cn.xiaochuankeji.tieba.push.data.a a = a(xSession, xMessage);
        a.c = xSession.x_mask.avatar;
        a.e = xSession.x_mask.name;
        cn.xiaochuankeji.tieba.push.b.e.a(xSession, a, b);
        this.a.a(a);
        this.recycler.scrollToPosition(this.a.getItemCount() - 1);
        cn.xiaochuankeji.tieba.push.d.a().a(xSession, a, new cn.xiaochuankeji.tieba.push.a.c(this) {
            final /* synthetic */ ChatActivity a;

            {
                this.a = r1;
            }

            public void a(long j, cn.xiaochuankeji.tieba.push.data.a aVar) {
                if (this.a.a != null) {
                    this.a.a.a(j, aVar);
                }
            }

            public void b(long j, cn.xiaochuankeji.tieba.push.data.a aVar) {
                if (this.a.a != null) {
                    this.a.a.a(j, aVar);
                }
            }
        });
    }

    private cn.xiaochuankeji.tieba.push.data.a a(XSession xSession, XMessage xMessage) {
        cn.xiaochuankeji.tieba.push.data.a aVar = new cn.xiaochuankeji.tieba.push.data.a();
        aVar.j = xSession.session_local_id;
        aVar.a = xSession.x_mask.id;
        aVar.b = xSession.x_sid;
        aVar.k = xSession.time;
        aVar.g = xMessage.msg_type;
        aVar.f = xMessage.content;
        if (xMessage.msg_type == 2) {
            aVar.i = R.layout.view_item_chat_self_image;
        } else if (xMessage.msg_type == 3) {
            aVar.i = R.layout.view_item_chat_self_voice;
        } else {
            aVar.i = R.layout.view_item_chat_self_txt;
        }
        aVar.h = 1;
        return aVar;
    }

    private void a(File file) {
        rx.d.a((Object) file).d(new rx.b.g<File, JSONObject>(this) {
            final /* synthetic */ ChatActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((File) obj);
            }

            public JSONObject a(File file) {
                long j = 60000;
                if (file == null || !file.exists()) {
                    this.a.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass11 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            g.a("音频文件不存在");
                        }
                    });
                    return null;
                }
                FFmpegMediaMetadataRetriever fFmpegMediaMetadataRetriever = new FFmpegMediaMetadataRetriever();
                fFmpegMediaMetadataRetriever.setDataSource(BaseApplication.getAppContext(), Uri.fromFile(file));
                long longValue = Long.valueOf(fFmpegMediaMetadataRetriever.extractMetadata("duration")).longValue();
                if (longValue < 1000) {
                    file.delete();
                    this.a.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass11 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            g.a("说话时间太短啦");
                        }
                    });
                    return null;
                }
                if (longValue <= 60000) {
                    j = longValue;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(AIUIConstant.RES_TYPE_PATH, file.getAbsolutePath());
                jSONObject.put("duration", Long.valueOf(j));
                jSONObject.put("fmt", "wav");
                jSONObject.put("name", file.getName());
                return jSONObject;
            }
        }).a(new rx.b.g<JSONObject, Boolean>(this) {
            final /* synthetic */ ChatActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((JSONObject) obj);
            }

            public Boolean a(JSONObject jSONObject) {
                return Boolean.valueOf(jSONObject != null);
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.e<JSONObject>(this) {
            final /* synthetic */ ChatActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((JSONObject) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                g.a(th.getMessage());
            }

            public void a(JSONObject jSONObject) {
                this.a.a(jSONObject.toJSONString(), 3);
            }
        });
    }
}
