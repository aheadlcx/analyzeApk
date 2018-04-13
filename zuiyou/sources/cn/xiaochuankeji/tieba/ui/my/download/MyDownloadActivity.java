package cn.xiaochuankeji.tieba.ui.my.download;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.d.h;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.my.download.c.c;
import cn.xiaochuankeji.tieba.ui.widget.f;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.ui.widget.MediaGrid.OnMediaLongClickListener;
import com.zhihu.matisse.thumbnail.ThumbnailManager;
import java.io.File;
import java.io.FilenameFilter;
import java.lang.annotation.Annotation;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.aspectj.a.b.b;
import rx.d;
import rx.d$a;
import rx.j;

public class MyDownloadActivity extends a implements OnClickListener, c, f.a, g.a, OnMediaLongClickListener {
    private static final org.aspectj.lang.a.a k = null;
    private static Annotation l;
    BroadcastReceiver a = new BroadcastReceiver(this) {
        final /* synthetic */ MyDownloadActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            g.a("SD卡已拔出");
            this.a.finish();
        }
    };
    private c b;
    @Nullable
    @BindView
    View back;
    private ThumbnailManager c = new ThumbnailManager();
    private final g d = new g();
    private List<a> e = new ArrayList();
    private DateFormat f = new SimpleDateFormat("yyyy年MM月dd号");
    private int g;
    private String h;
    private i i;
    private boolean j = false;
    @Nullable
    @BindView
    RecyclerView mRecyclerView;
    @Nullable
    @BindView
    View tv_setting;

    static {
        q();
    }

    private static void q() {
        b bVar = new b("MyDownloadActivity.java", MyDownloadActivity.class);
        k = bVar.a("method-execution", bVar.a("2", "k", "cn.xiaochuankeji.tieba.ui.my.download.MyDownloadActivity", "", "", "", "void"), 132);
    }

    public static void a(Context context) {
        context.startActivity(new Intent(context, MyDownloadActivity.class));
    }

    protected int a() {
        return R.layout.activity_download;
    }

    protected void i_() {
        ButterKnife.a((Activity) this);
        this.back.setOnClickListener(this);
        this.tv_setting.setOnClickListener(this);
        this.c.onCreate(this);
        this.c.loadThumbnails();
        this.b = new c(this, this.c, this.mRecyclerView);
        this.mRecyclerView.setAdapter(this.b);
        LayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        gridLayoutManager.setSpanSizeLookup(new SpanSizeLookup(this) {
            final /* synthetic */ MyDownloadActivity a;

            {
                this.a = r1;
            }

            public int getSpanSize(int i) {
                if (this.a.b.getItemViewType(i) == 1) {
                    return 4;
                }
                return 1;
            }
        });
        this.mRecyclerView.setLayoutManager(gridLayoutManager);
        this.mRecyclerView.addItemDecoration(new b(getResources().getDimensionPixelSize(R.dimen.media_grid_spacing)));
        this.b.a((c) this);
        this.b.a((OnMediaLongClickListener) this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter.addDataScheme("file");
        registerReceiver(this.a, intentFilter);
    }

    protected void onResume() {
        super.onResume();
        k();
    }

    static final void a(MyDownloadActivity myDownloadActivity, org.aspectj.lang.a aVar) {
        if (ContextCompat.checkSelfPermission(myDownloadActivity, "android.permission.WRITE_EXTERNAL_STORAGE") == 0 && !myDownloadActivity.j) {
            myDownloadActivity.j = true;
            myDownloadActivity.d.a((FragmentActivity) myDownloadActivity, (g.a) myDownloadActivity);
            myDownloadActivity.d.b();
            myDownloadActivity.p();
        }
    }

    @cn.xiaochuankeji.aop.permission.b(a = {"android.permission.WRITE_EXTERNAL_STORAGE"}, b = "无法使用扩展存储", d = "确认", f = "拒绝该权限后无法读取存储内容", h = "拒绝", j = "设置权限", l = true, m = true)
    private void k() {
        org.aspectj.lang.a a = b.a(k, (Object) this, (Object) this);
        cn.xiaochuankeji.aop.permission.c c = cn.xiaochuankeji.aop.permission.c.c();
        org.aspectj.lang.b linkClosureAndJoinPoint = new d(new Object[]{this, a}).linkClosureAndJoinPoint(69648);
        Annotation annotation = l;
        if (annotation == null) {
            annotation = MyDownloadActivity.class.getDeclaredMethod("k", new Class[0]).getAnnotation(cn.xiaochuankeji.aop.permission.b.class);
            l = annotation;
        }
        c.a(linkClosureAndJoinPoint, (cn.xiaochuankeji.aop.permission.b) annotation);
    }

    @RequiresPermission("android.permission.WRITE_EXTERNAL_STORAGE")
    private void p() {
        final String string = cn.xiaochuankeji.tieba.background.a.a().getString("key_download_path", "");
        if (!TextUtils.isEmpty(string) && !string.contains("DCIM")) {
            this.i = new i(this);
            d.b(new d$a<Integer>(this) {
                final /* synthetic */ MyDownloadActivity b;

                public /* synthetic */ void call(Object obj) {
                    a((j) obj);
                }

                public void a(j<? super Integer> jVar) {
                    int i = 0;
                    File file = new File(string);
                    if (file.exists()) {
                        File[] listFiles = file.listFiles(new FilenameFilter(this) {
                            final /* synthetic */ AnonymousClass3 a;

                            {
                                this.a = r1;
                            }

                            public boolean accept(File file, String str) {
                                if (str.endsWith(".mp4") || str.endsWith(".jpg") || str.endsWith(".jpeg") || str.endsWith(".gif") || str.endsWith(".png") || str.endsWith(".webp")) {
                                    return true;
                                }
                                return false;
                            }
                        });
                        this.b.h = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/zuiyou/";
                        File file2 = new File(this.b.h);
                        if (file2.exists() || file2.mkdirs()) {
                            this.b.g = listFiles.length;
                            jVar.onNext(Integer.valueOf(0));
                            while (i < listFiles.length) {
                                try {
                                    file2 = new File(this.b.h + System.currentTimeMillis() + listFiles[i].getPath().substring(listFiles[i].getPath().lastIndexOf(".")));
                                    File parentFile = file2.getParentFile();
                                    if (!parentFile.exists()) {
                                        parentFile.mkdirs();
                                    }
                                    if (cn.htjyb.c.a.b.a(listFiles[i], file2)) {
                                        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                                        intent.setData(Uri.fromFile(file2));
                                        this.b.sendBroadcast(intent);
                                        jVar.onNext(Integer.valueOf(i + 1));
                                    } else {
                                        jVar.onError(new Exception("转移文件失败"));
                                    }
                                    i++;
                                } catch (Exception e) {
                                }
                            }
                        } else {
                            return;
                        }
                    }
                    Editor edit = cn.xiaochuankeji.tieba.background.a.a().edit();
                    edit.putString("key_download_path", this.b.h);
                    edit.apply();
                }
            }).b(rx.f.a.c()).a(rx.a.b.a.a()).b(new j<Integer>(this) {
                final /* synthetic */ MyDownloadActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((Integer) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    if (this.a.i != null) {
                        this.a.i.d();
                    }
                }

                public void a(Integer num) {
                    if (num.intValue() == 0) {
                        this.a.i.c();
                    }
                    if (this.a.i != null) {
                        this.a.i.a(num.intValue(), this.a.g);
                    }
                    if (num.intValue() == this.a.g) {
                        this.a.mRecyclerView.postDelayed(new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.i.d();
                                this.a.a.e.clear();
                                this.a.a.d.c();
                            }
                        }, 1000);
                    }
                }
            });
        }
    }

    private void b(Cursor cursor) {
        if (cursor == null || !cursor.moveToFirst()) {
            this.b.a(this.e);
        }
        do {
            Item valueOf = Item.valueOf(cursor);
            if (new File(valueOf.path).exists()) {
                a aVar;
                if (this.e.isEmpty() || !h.a(((a) this.e.get(this.e.size() - 1)).c.time, valueOf.time)) {
                    aVar = new a();
                    aVar.b = 1;
                    aVar.a = this.f.format(new Date(valueOf.time));
                    this.e.add(aVar);
                }
                aVar = new a();
                aVar.b = 2;
                aVar.c = valueOf;
                this.e.add(aVar);
            }
        } while (cursor.moveToNext());
        this.b.a(this.e);
    }

    public void a(Cursor cursor) {
        b(cursor);
    }

    public void e() {
    }

    protected void onDestroy() {
        super.onDestroy();
        this.c.onDestroy();
        this.d.a();
        unregisterReceiver(this.a);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                return;
            case R.id.tv_setting:
                List arrayList = new ArrayList();
                e eVar = new e();
                eVar.a = "内部存储";
                eVar.b = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/zuiyou/";
                arrayList.add(eVar);
                if (VERSION.SDK_INT >= 21) {
                    File[] externalMediaDirs = getExternalMediaDirs();
                    int i = 0;
                    while (i < externalMediaDirs.length) {
                        if (!(externalMediaDirs[i] == null || externalMediaDirs[i].getPath().contains(Environment.getExternalStorageDirectory().getPath()))) {
                            e eVar2 = new e();
                            eVar2.a = "存储卡" + i;
                            File file = new File(externalMediaDirs[i].getPath());
                            if (file.exists() || file.mkdirs()) {
                                eVar2.b = externalMediaDirs[i].getPath() + "/DCIM/zuiyou/";
                                arrayList.add(eVar2);
                            }
                        }
                        i++;
                    }
                }
                String string = cn.xiaochuankeji.tieba.background.a.a().getString("key_download_path", "");
                if (TextUtils.isEmpty(string)) {
                    string = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/zuiyou/";
                }
                f.a(this, arrayList, string, this);
                return;
            default:
                return;
        }
    }

    public void a(Item item) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(item.uri, item.mimeType);
        startActivity(intent);
    }

    public void a(e eVar) {
        Editor edit = cn.xiaochuankeji.tieba.background.a.a().edit();
        edit.putString("key_download_path", eVar.b);
        edit.apply();
        cn.xiaochuankeji.tieba.background.a.e().E();
        g.a("下载目录已改为" + eVar.a);
    }

    public void j() {
        g.a("修改下载目录失败");
    }

    public void onBackPressed() {
        if ((this.i == null || !this.i.a()) && !f.a((Activity) this)) {
            super.onBackPressed();
        }
    }

    public void onLongClick(final Item item) {
        f.a("提示", "是否要删除?", this, new f.a(this) {
            final /* synthetic */ MyDownloadActivity b;

            public void a(boolean z) {
                if (z) {
                    File file = new File(item.path);
                    if (file.exists()) {
                        file.delete();
                        this.b.e.clear();
                        this.b.d.c();
                    }
                }
            }
        });
    }
}
