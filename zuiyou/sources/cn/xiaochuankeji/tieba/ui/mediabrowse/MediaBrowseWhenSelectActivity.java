package cn.xiaochuankeji.tieba.ui.mediabrowse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.mediabrowse.component.d;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import cn.xiaochuankeji.tieba.ui.widget.text.BadgeTextView;
import com.android.a.a.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import org.aspectj.a.b.b;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public class MediaBrowseWhenSelectActivity extends a implements OnClickListener {
    private static ArrayList<cn.htjyb.b.a> a;
    private static ArrayList<LocalMedia> c;
    private static final org.aspectj.lang.a.a n = null;
    private ArrayList<cn.htjyb.b.a> b = new ArrayList();
    private ArrayList<LocalMedia> d = new ArrayList();
    private int e;
    private ViewPager f;
    private boolean g;
    private ImageView h;
    private TextView i;
    private BadgeTextView j;
    private ImageView k;
    private d l;
    private int m;

    static {
        r();
    }

    private static void r() {
        b bVar = new b("MediaBrowseWhenSelectActivity.java", MediaBrowseWhenSelectActivity.class);
        n = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.mediabrowse.MediaBrowseWhenSelectActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 74);
    }

    public static void a(Context context, ArrayList<cn.htjyb.b.a> arrayList, ArrayList<LocalMedia> arrayList2, int i, int i2) {
        a = arrayList;
        c = arrayList2;
        Intent intent = new Intent(context, MediaBrowseWhenSelectActivity.class);
        intent.putExtra("key_current_index", i);
        intent.putExtra("key_max_limit", i2);
        context.startActivity(intent);
    }

    public static void a(Context context, ArrayList<cn.htjyb.b.a> arrayList, ArrayList<LocalMedia> arrayList2, int i) {
        a = arrayList;
        c = arrayList2;
        Intent intent = new Intent(context, MediaBrowseWhenSelectActivity.class);
        intent.putExtra("key_current_index", i);
        intent.putExtra("key_just_for_browse", true);
        context.startActivity(intent);
    }

    static final void a(MediaBrowseWhenSelectActivity mediaBrowseWhenSelectActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        c.a(mediaBrowseWhenSelectActivity.getWindow(), true);
        c.a.b.a((Activity) mediaBrowseWhenSelectActivity, 17170444);
        super.onCreate(bundle);
        mediaBrowseWhenSelectActivity.overridePendingTransition(R.anim.scale_in, 0);
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(n, this, this, bundle);
        cn.xiaochuankeji.aop.permission.c.c().a(new b(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected int a() {
        return R.layout.activity_big_pic_select;
    }

    protected boolean a(Bundle bundle) {
        this.b = a;
        a = null;
        if (this.b == null) {
            return false;
        }
        this.d = c;
        c = null;
        Bundle extras = getIntent().getExtras();
        this.m = extras.getInt("key_max_limit");
        this.e = extras.getInt("key_current_index");
        this.g = extras.getBoolean("key_just_for_browse", false);
        return true;
    }

    protected void c() {
        this.f = (ViewPager) findViewById(R.id.viewPager);
        this.h = (ImageView) findViewById(R.id.ivSelect);
        this.i = (TextView) findViewById(R.id.tvFinish);
        this.j = (BadgeTextView) findViewById(R.id.tvPicCount);
        this.k = (ImageView) findViewById(R.id.ivLeftArrow);
        if (this.g) {
            this.h.setVisibility(8);
            this.j.setVisibility(8);
            findViewById(R.id.controller).setVisibility(8);
        }
    }

    protected void i_() {
        this.l = new d(getSupportFragmentManager(), this, 0, this.b, null);
        this.f.setAdapter(this.l);
        this.f.setCurrentItem(this.e);
        this.f.setEnabled(false);
        e();
        k();
    }

    private void e() {
        this.j.setBadgeCount(j());
    }

    private int j() {
        Iterator it = this.b.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2;
            if (((cn.htjyb.b.a) it.next()).isSelect()) {
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
        return i;
    }

    private void k() {
        if (((cn.htjyb.b.a) this.b.get(this.e)).isSelect()) {
            this.h.setSelected(true);
        } else {
            this.h.setSelected(false);
        }
    }

    protected void j_() {
        this.f.setOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ MediaBrowseWhenSelectActivity a;

            {
                this.a = r1;
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                this.a.e = i;
                if (this.a.l != null && this.a.l.a() != null) {
                    Iterator it = this.a.l.a().iterator();
                    while (it.hasNext()) {
                        cn.xiaochuankeji.tieba.ui.mediabrowse.component.c cVar = (cn.xiaochuankeji.tieba.ui.mediabrowse.component.c) it.next();
                        if (cVar.c() == this.a.e) {
                            cVar.b();
                        } else {
                            cVar.a();
                        }
                    }
                    this.a.e();
                    this.a.k();
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.k.setOnClickListener(this);
    }

    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, R.anim.scale_out);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.l != null) {
            this.l.b();
        }
    }

    public void onClick(View view) {
        int i = 0;
        switch (view.getId()) {
            case R.id.tvFinish:
                ArrayList arrayList = new ArrayList();
                while (i < this.b.size()) {
                    if (((cn.htjyb.b.a) this.b.get(i)).isSelect()) {
                        arrayList.add(Integer.valueOf(i));
                    }
                    i++;
                }
                MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_BIGPIC_CONFIRM);
                messageEvent.setData(arrayList);
                org.greenrobot.eventbus.c.a().d(messageEvent);
                finish();
                return;
            case R.id.ivLeftArrow:
                q();
                finish();
                return;
            case R.id.ivSelect:
                if (((cn.htjyb.b.a) this.b.get(this.e)).isSelect()) {
                    ((cn.htjyb.b.a) this.b.get(this.e)).setSelect(false);
                } else if (j() >= this.m) {
                    g.a("最多选择" + this.m + "张图片");
                } else if (!p()) {
                    ((cn.htjyb.b.a) this.b.get(this.e)).setSelect(true);
                } else {
                    return;
                }
                k();
                e();
                return;
            default:
                return;
        }
    }

    private boolean p() {
        if (this.d != null && this.e >= 0 && this.e < this.d.size()) {
            LocalMedia localMedia = (LocalMedia) this.d.get(this.e);
            if ((((float) localMedia.size) / 1024.0f) / 1024.0f >= 100.0f) {
                g.b("暂不支持发100M以上的视频哦~");
                return true;
            } else if (localMedia.duration > TimeUnit.MINUTES.toMillis(15)) {
                g.a("上传视频不能超过15分钟");
                return true;
            }
        }
        return false;
    }

    @l(a = ThreadMode.MAIN)
    public void message(MessageEvent messageEvent) {
        if (messageEvent.getEventType() == MessageEventType.MESSAGE_EDIT_FINISH_WHEN_SELECTED_PIC) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < this.b.size(); i++) {
                if (((cn.htjyb.b.a) this.b.get(i)).isSelect()) {
                    arrayList.add(Integer.valueOf(i));
                }
            }
            cn.htjyb.b.a aVar = (cn.htjyb.b.a) messageEvent.getData();
            MessageEvent messageEvent2 = new MessageEvent(MessageEventType.MESSAGE_EDIR_FINISH_IN_BIGPIC);
            messageEvent2.setData(arrayList);
            messageEvent2.setExtraData(aVar);
            org.greenrobot.eventbus.c.a().d(messageEvent2);
            finish();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        this.l.b();
        q();
    }

    private void q() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.b.size(); i++) {
            if (((cn.htjyb.b.a) this.b.get(i)).isSelect()) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_BIG_PIC_BACK_PRESSED);
        messageEvent.setData(arrayList);
        org.greenrobot.eventbus.c.a().d(messageEvent);
    }
}
