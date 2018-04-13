package cn.xiaochuankeji.tieba.ui.topic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.background.topic.TopicCreateTaskManager;
import cn.xiaochuankeji.tieba.background.topic.TopicCreateTaskManager.OnTopicCreateListener;
import cn.xiaochuankeji.tieba.background.topic.TopicCreateTaskManager.onTopicModifyListener;
import cn.xiaochuankeji.tieba.background.topic.TopicHistoryRecordManager;
import cn.xiaochuankeji.tieba.background.topic.TopicHistoryRecordManager.Type;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.publish.PublishPostActivity;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import cn.xiaochuankeji.tieba.ui.widget.f;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.io.File;
import org.aspectj.a.b.b;

public class TopicCreateActivity extends a implements TextWatcher, OnClickListener, OnTopicCreateListener, onTopicModifyListener {
    private static Topic f = null;
    private static final org.aspectj.lang.a.a s = null;
    private final int a = 1;
    private final int b = 2;
    private final int c = 4;
    private String d;
    private String e;
    private Topic g = null;
    private String h = null;
    private File i;
    private File j;
    private WebImageView k;
    private EditText l;
    private EditText m;
    private EditText n;
    private TextView o;
    private TextView p;
    private View q;
    private View r;

    private static void j() {
        b bVar = new b("TopicCreateActivity.java", TopicCreateActivity.class);
        s = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.topic.TopicCreateActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 95);
    }

    static {
        j();
    }

    public static void a(Context context, Topic topic, String str, String str2) {
        Intent intent = new Intent(context, TopicCreateActivity.class);
        intent.setFlags(1073741824);
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        intent.putExtra("kFrom", str);
        intent.putExtra("kBrief", str2);
        f = topic;
        context.startActivity(intent);
    }

    public static void a(Context context, String str, String str2) {
        Intent intent = new Intent(context, TopicCreateActivity.class);
        intent.setFlags(1073741824);
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        intent.putExtra("kTopicName", str);
        intent.putExtra("kFrom", str2);
        context.startActivity(intent);
    }

    static final void a(TopicCreateActivity topicCreateActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(s, this, this, bundle);
        c.c().a(new g(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected int a() {
        return R.layout.activity_topic_create;
    }

    protected boolean a(Bundle bundle) {
        this.g = f;
        f = null;
        Bundle extras = getIntent().getExtras();
        this.d = extras.getString("kFrom");
        if (this.d.equals("kEditTopic")) {
            this.h = extras.getString("kBrief");
            if (this.g == null) {
                return false;
            }
        }
        this.e = extras.getString("kTopicName");
        return true;
    }

    protected void c() {
        this.m = (EditText) findViewById(R.id.etBrief);
        this.l = (EditText) findViewById(R.id.etTopicName);
        this.n = (EditText) findViewById(R.id.etFocusName);
        this.k = (WebImageView) findViewById(R.id.pvTopicCover);
        this.o = (TextView) findViewById(R.id.tvTitle);
        this.p = (TextView) findViewById(R.id.tvOptionText);
        this.r = findViewById(R.id.cover_crumb);
        this.r.setVisibility(8);
        this.q = findViewById(R.id.ivBack);
        this.q.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ TopicCreateActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                cn.htjyb.c.a.a(this.a);
                this.a.onBackPressed();
            }
        });
        this.p.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ TopicCreateActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                String trim = this.a.l.getText().toString().trim();
                String trim2 = this.a.n.getText().toString().trim();
                if (trim.length() == 0) {
                    g.a("为话题取个名字吧");
                } else if (trim2.length() == 0) {
                    g.a("为关注话题的同学取个名字吧");
                } else {
                    cn.xiaochuankeji.tieba.ui.widget.g.a(this.a);
                    if (this.a.d.equals("kEditTopic")) {
                        TopicCreateTaskManager instance = TopicCreateTaskManager.getInstance();
                        instance.setOnTopicModifyListener(this.a);
                        instance.modifyTopic(this.a.g._topicID, this.a.m.getText().toString().trim(), this.a.i != null ? this.a.i.getAbsolutePath() : "", trim2);
                        return;
                    }
                    TopicCreateTaskManager instance2 = TopicCreateTaskManager.getInstance();
                    instance2.setOnTopicCreateListener(this.a);
                    instance2.createTopic(trim, this.a.m.getText().toString().trim(), this.a.i != null ? this.a.i.getAbsolutePath() : "", trim2);
                }
            }
        });
    }

    protected void i_() {
        this.k.setOnClickListener(this);
        if (this.d.equals("kEditTopic")) {
            this.l.setText(this.g._topicName);
            this.l.setFilters(new InputFilter[]{new InputFilter(this) {
                final /* synthetic */ TopicCreateActivity a;

                {
                    this.a = r1;
                }

                public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                    return charSequence.length() < 1 ? spanned.subSequence(i3, i4) : "";
                }
            }});
            this.l.setFocusable(false);
            if (this.h != null) {
                this.m.setText(this.h);
                this.m.setSelection(this.m.getText().length());
            }
            if (this.g.topicCover() != null) {
                this.r.setVisibility(0);
                this.k.setWebImage(cn.xiaochuankeji.tieba.background.f.b.c(this.g._topicCoverID, false));
            }
            CharSequence charSequence = this.g._attsTitle;
            if (charSequence != null) {
                this.n.setText(charSequence);
            }
            this.o.setText("话题编辑");
            this.p.setText("完成");
        }
    }

    protected void onResume() {
        super.onResume();
        this.m.addTextChangedListener(this);
        this.l.addTextChangedListener(this);
        this.n.addTextChangedListener(this);
    }

    protected void onPause() {
        super.onPause();
        this.m.removeTextChangedListener(this);
        this.l.removeTextChangedListener(this);
        this.n.removeTextChangedListener(this);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.i = null;
    }

    public void onBackPressed() {
        Object obj = 1;
        if (this.d.equals("kEditTopic")) {
            f.a("提示", "你要放弃编辑吗？", this, new f.a(this) {
                final /* synthetic */ TopicCreateActivity a;

                {
                    this.a = r1;
                }

                public void a(boolean z) {
                    if (z) {
                        super.onBackPressed();
                    }
                }
            });
            return;
        }
        Object obj2;
        if (this.m.getText().toString().trim().length() > 0) {
            obj2 = 1;
        } else {
            obj2 = null;
        }
        if (!(this.i != null && this.i.isFile() && this.i.exists())) {
            obj = null;
        }
        if (obj2 == null && !e() && r1 == null) {
            super.onBackPressed();
        } else {
            f.a("提示", "确定放弃创建？", this, new f.a(this) {
                final /* synthetic */ TopicCreateActivity a;

                {
                    this.a = r1;
                }

                public void a(boolean z) {
                    if (z) {
                        super.onBackPressed();
                    }
                }
            });
        }
    }

    private boolean e() {
        return this.l.getText().toString().trim().length() > 0;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (1 == i) {
            if (-1 == i2) {
                for (LocalMedia localMedia : cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.b(intent)) {
                    if (localMedia.type != 1 && cn.htjyb.c.a.b.c(localMedia.path)) {
                        this.i = new File(localMedia.path);
                        a(this.i);
                        return;
                    }
                }
            }
        } else if (2 == i) {
            if (-1 == i2) {
                a(intent);
            }
        } else if ((i == 69 || i == 70) && intent != null && cn.xiaochuan.image.a.b.a(intent) != null) {
            com.facebook.drawee.a.a.c.c().a(Uri.fromFile(this.i));
            this.r.setVisibility(0);
            this.k.setImageURI(Uri.fromFile(this.i));
        }
    }

    private void a(Intent intent) {
        if (a(this.i, this.i)) {
            a(this.i);
        }
    }

    private void a(File file) {
        if (this.j != null) {
            this.j.delete();
        }
        this.j = new File(file.getPath() + "." + System.currentTimeMillis());
        cn.htjyb.c.a.b.a(file, this.j);
        Uri fromFile = Uri.fromFile(this.j);
        Uri fromFile2 = Uri.fromFile(this.i);
        if (fromFile != null) {
            try {
                if (fromFile.isAbsolute()) {
                    cn.xiaochuan.image.a.b.a((Activity) this, fromFile, fromFile2, "剪裁封面");
                }
            } catch (Exception e) {
                if (fromFile != null) {
                    try {
                        if (fromFile.isAbsolute()) {
                            cn.xiaochuan.image.a.b.a((Activity) this, fromFile, fromFile2, 70);
                        }
                    } catch (Exception e2) {
                        this.i = this.j;
                        this.r.setVisibility(0);
                        this.k.setImageURI(Uri.fromFile(this.i));
                    }
                }
            }
        }
    }

    private boolean a(File file, File file2) {
        if (cn.htjyb.c.b.b.a(file, file2, 80, 800)) {
            return true;
        }
        cn.htjyb.ui.widget.a.a((Context) this, (CharSequence) "保存照片失败", 0).show();
        return false;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pvTopicCover:
                cn.htjyb.c.a.a((Activity) this);
                cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.f(this, 1);
                return;
            default:
                return;
        }
    }

    public void onTopicCreateSuccess(Topic topic, boolean z) {
        cn.xiaochuankeji.tieba.ui.widget.g.c(this);
        if (this.d.equals("kTopicTab")) {
            TopicDetailActivity.a((Context) this, topic, !z, "topic_create", 0);
            finish();
            return;
        }
        TopicHistoryRecordManager.getInstance(Type.kSelect).insert(topic);
        PublishPostActivity.d = topic;
        finish();
    }

    public void onTopicCreateFailure(String str) {
        cn.xiaochuankeji.tieba.ui.widget.g.c(this);
        g.b(str);
    }

    public void onTopicModifySuccess() {
        cn.xiaochuankeji.tieba.ui.widget.g.c(this);
        g.a("待审核,暂时不会变更");
        finish();
    }

    public void onTopidModifyFailure(String str) {
        cn.xiaochuankeji.tieba.ui.widget.g.c(this);
        g.b(str);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
    }
}
