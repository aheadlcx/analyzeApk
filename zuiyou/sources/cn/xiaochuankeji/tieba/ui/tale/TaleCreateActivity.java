package cn.xiaochuankeji.tieba.ui.tale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.dreamtobe.kpswitch.b.c;
import cn.dreamtobe.kpswitch.b.c.b;
import cn.dreamtobe.kpswitch.widget.KPSwitchPanelFrameLayout;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.tale.TaleService;
import cn.xiaochuankeji.tieba.background.tale.TaleTheme;
import cn.xiaochuankeji.tieba.background.upload.f;
import cn.xiaochuankeji.tieba.background.upload.j;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.tale.FollowPostCreateJson;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.publish.e;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import cn.xiaochuankeji.tieba.widget.rich.RichTextEditor;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sina.weibo.sdk.api.CmdObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TaleCreateActivity extends a implements TextWatcher, e.a {
    private e a;
    @BindView
    View album;
    @BindView
    RichTextEditor article_editor;
    private int b;
    private Handler c = new Handler();
    private Runnable d;
    private String e;
    private j f;
    @BindView
    View hide_keyboard;
    @BindView
    KPSwitchPanelFrameLayout panel_root;
    @BindView
    AppCompatTextView theme_counter;
    @BindView
    EditText theme_editor;

    public static void a(Activity activity, String str, TaleTheme taleTheme, int i) {
        Intent intent = new Intent(activity, TaleCreateActivity.class);
        intent.putExtra("_page_src", str);
        intent.putExtra("_theme", taleTheme);
        activity.startActivityForResult(intent, i);
    }

    protected int a() {
        return R.layout.activity_tale_create;
    }

    protected void i_() {
        ButterKnife.a((Activity) this);
        final TaleTheme taleTheme = (TaleTheme) getIntent().getParcelableExtra("_theme");
        this.e = getIntent().getStringExtra("_page_src");
        this.theme_editor.setOnFocusChangeListener(new OnFocusChangeListener(this) {
            final /* synthetic */ TaleCreateActivity a;

            {
                this.a = r1;
            }

            public void onFocusChange(View view, boolean z) {
                this.a.album.setVisibility(z ? 4 : 0);
            }
        });
        this.hide_keyboard.setVisibility(4);
        c.a(this, this.panel_root, new b(this) {
            final /* synthetic */ TaleCreateActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                this.a.hide_keyboard.setVisibility(z ? 0 : 4);
            }
        });
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ TaleCreateActivity b;

            public void run() {
                if (taleTheme == null || taleTheme.id <= 0) {
                    rx.a.b.a.a().a().a(new rx.b.a(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void call() {
                            cn.htjyb.c.a.a(this.a.b.theme_editor);
                        }
                    }, 500, TimeUnit.MILLISECONDS);
                    this.b.album.setVisibility(4);
                    return;
                }
                this.b.theme_counter.setVisibility(4);
                this.b.theme_editor.setEnabled(false);
                this.b.theme_editor.setText(taleTheme.title);
            }
        });
        this.a = new e(this, this);
    }

    public boolean h() {
        return false;
    }

    protected void onResume() {
        super.onResume();
        this.theme_editor.addTextChangedListener(this);
    }

    protected void onPause() {
        super.onPause();
        this.theme_editor.removeTextChangedListener(this);
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
    }

    @OnClick
    public void album() {
        cn.htjyb.c.a.a(this.theme_editor);
        this.article_editor.a();
        cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.f(this, 32);
    }

    @OnClick
    public void hideKeyboard() {
        cn.htjyb.c.a.a(this.theme_editor, this.article_editor);
    }

    @OnClick
    public void onClick(View view) {
        onBackPressed();
    }

    @OnClick
    public void send() {
        String trim = this.theme_editor.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            g.a("主题不能为空");
        } else if (trim.length() < 5) {
            g.a("主题不能少于5个字");
        } else if (trim.length() > 40) {
            g.a("主题不能多于40个字");
        } else {
            ArrayList tale = this.article_editor.getTale();
            if (tale.isEmpty()) {
                g.a("跟帖不能为空");
            } else if (!this.a.c()) {
                a(trim, tale);
            }
        }
    }

    private void a(final String str, final ArrayList<RichTextEditor.a> arrayList) {
        final List arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            RichTextEditor.a aVar = (RichTextEditor.a) it.next();
            if ("img".equals(aVar.a)) {
                LocalMedia localMedia = new LocalMedia();
                localMedia.path = aVar.g;
                localMedia.width = aVar.d;
                localMedia.height = aVar.e;
                localMedia.mimeType = aVar.f;
                arrayList2.add(localMedia);
            }
        }
        if (arrayList2.isEmpty()) {
            this.a.a("正在发帖", 30, this.b);
            this.a.a();
            JSONArray jSONArray = new JSONArray();
            it = arrayList.iterator();
            while (it.hasNext()) {
                aVar = (RichTextEditor.a) it.next();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("type", aVar.a);
                jSONObject.put("txt", aVar.b);
                jSONArray.add(jSONObject);
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("content", jSONArray.toJSONString());
            a(str, jSONObject2);
            return;
        }
        this.a.a("正在上传", arrayList2.size(), 0);
        this.a.a();
        this.f = new j();
        this.f.a(arrayList2, "tale", new cn.xiaochuankeji.tieba.background.upload.b(this) {
            final /* synthetic */ TaleCreateActivity b;

            public void a(long j, long j2, int i) {
                if (arrayList2.size() > 0 && i < arrayList2.size() && i >= 0) {
                    int i2 = ((LocalMedia) arrayList2.get(i)).type;
                    StringBuilder stringBuilder = new StringBuilder("正在上传");
                    if (1 == i2) {
                        stringBuilder.append("视频");
                    } else {
                        stringBuilder.append("图片");
                    }
                    stringBuilder.append((i + 1) + "/" + arrayList2.size());
                    this.b.a.a(stringBuilder.toString(), (int) j, (int) j2);
                }
            }
        }, new f(this) {
            final /* synthetic */ TaleCreateActivity c;

            public void a(List<Long> list, List<Long> list2, HashMap<String, LocalMedia> hashMap) {
                JSONArray jSONArray = new JSONArray();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    RichTextEditor.a aVar = (RichTextEditor.a) it.next();
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("type", aVar.a);
                    if ("txt".equals(aVar.a)) {
                        jSONObject.put("txt", aVar.b);
                    } else {
                        LocalMedia localMedia = (LocalMedia) hashMap.get(aVar.g);
                        jSONObject.put("id", Long.valueOf(localMedia.id));
                        jSONObject.put("w", Integer.valueOf(localMedia.width));
                        jSONObject.put("h", Integer.valueOf(localMedia.height));
                        jSONObject.put("fmt", localMedia.fmt);
                    }
                    jSONArray.add(jSONObject);
                }
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("content", jSONArray.toJSONString());
                this.c.a(str, jSONObject2);
            }

            public void a(String str) {
                if (this.c.a != null && this.c.a.c()) {
                    this.c.a.b();
                }
                g.a("上传图片失败");
            }
        });
    }

    private void a(String str, JSONObject jSONObject) {
        jSONObject.put("theme_title", str);
        jSONObject.put("localid", Integer.valueOf(jSONObject.hashCode()));
        TaleTheme taleTheme = (TaleTheme) getIntent().getParcelableExtra("_theme");
        jSONObject.put("from", this.e);
        if (taleTheme != null && taleTheme.id > 0) {
            jSONObject.put("theme_id", Long.valueOf(taleTheme.id));
        }
        ((TaleService) cn.xiaochuankeji.tieba.network.c.b(TaleService.class)).create(jSONObject).a(rx.a.b.a.a()).a(new rx.e<FollowPostCreateJson>(this) {
            final /* synthetic */ TaleCreateActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((FollowPostCreateJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                if (this.a.a != null && this.a.a.c()) {
                    this.a.a.b();
                }
                g.a(th.getMessage());
            }

            public void a(final FollowPostCreateJson followPostCreateJson) {
                this.a.d = new Runnable(this) {
                    final /* synthetic */ AnonymousClass6 b;

                    public void run() {
                        this.b.a.b = this.b.a.b + 1;
                        if (this.b.a.b <= 30) {
                            this.b.a.a.a("正在发帖", 30, this.b.a.b);
                            this.b.a.c.post(this.b.a.d);
                            return;
                        }
                        if (this.b.a.a != null && this.b.a.a.c()) {
                            this.b.a.a.b();
                        }
                        g.a("发布成功");
                        Intent intent = new Intent();
                        if (!TextUtils.isEmpty(this.b.a.e)) {
                            if (this.b.a.e.equals(CmdObject.CMD_HOME) || this.b.a.e.equals("index")) {
                                intent.putExtra("create_data", followPostCreateJson.theme);
                            } else if (this.b.a.e.equals("theme")) {
                                intent.putExtra("create_data", followPostCreateJson.article);
                            }
                            this.b.a.setResult(-1, intent);
                        }
                        if (this.b.a.article_editor != null) {
                            this.b.a.article_editor.a();
                        }
                        cn.htjyb.c.a.a(this.b.a.theme_editor);
                        this.b.a.finish();
                    }
                };
                this.a.c.post(this.a.d);
            }
        });
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 32) {
            for (LocalMedia localMedia : cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.b(intent)) {
                RichTextEditor.a aVar = new RichTextEditor.a();
                aVar.a = "img";
                aVar.d = localMedia.width;
                aVar.e = localMedia.height;
                aVar.f = localMedia.mimeType;
                aVar.g = localMedia.path;
                this.article_editor.a(aVar);
            }
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        int length = editable.toString().length();
        if (length > 40) {
            this.theme_counter.setText(getString(R.string.follow_create_tip1, new Object[]{String.valueOf(editable.toString().length() - 40)}));
            c.a.b.a(this.theme_counter, (int) R.color.CH_1);
        } else if (length > 30) {
            this.theme_counter.setText(getString(R.string.follow_create_tip2, new Object[]{String.valueOf(40 - length)}));
            c.a.b.a(this.theme_counter, (int) R.color.CT_6);
        } else {
            this.theme_counter.setText("");
        }
    }

    public void onBackPressed() {
        if ((this.article_editor == null || this.article_editor.getTale().isEmpty()) && (this.theme_editor == null || TextUtils.isEmpty(this.theme_editor.getText().toString()))) {
            cn.htjyb.c.a.a(this.theme_editor);
            super.onBackPressed();
            return;
        }
        this.article_editor.a();
        cn.xiaochuankeji.tieba.ui.widget.f.a("提示", "确定放弃发表？", this, new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
            final /* synthetic */ TaleCreateActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                if (z) {
                    this.a.overridePendingTransition(0, R.anim.bottom_out);
                    this.a.finish();
                }
            }
        });
    }

    public void l_() {
        if (this.a.c()) {
            this.a.b();
        }
        if (this.f != null) {
            this.f.a();
        }
    }
}
