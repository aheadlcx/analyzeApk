package cn.xiaochuankeji.tieba.ui.selectlocalmedia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.aop.permission.PermissionItem;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.mediabrowse.MediaBrowseWhenSelectActivity;
import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public class SelectPicturesActivity extends h implements OnClickListener, OnItemClickListener, cn.xiaochuankeji.tieba.ui.selectlocalmedia.a.b {
    private static final org.aspectj.lang.a.a w = null;
    private boolean d;
    private d e;
    private GridView f;
    private TextView g;
    private a h;
    private ArrayList<LocalMedia> i = new ArrayList();
    private ArrayList<cn.xiaochuankeji.tieba.ui.selectlocalmedia.a.a> j = new ArrayList();
    private int k = -1;
    private ArrayList<LocalMedia> l = new ArrayList();
    private HashMap<Long, SoftReference<Bitmap>> m = new HashMap();
    private SelectEntranceType n = SelectEntranceType.kDefault;
    private a o;
    private cn.htjyb.c.b.a p;
    private f q;
    private b r;
    private String s;
    private ArrayList<LocalMedia> t = new ArrayList();
    private cn.xiaochuankeji.tieba.background.h.e u;
    private int v = 9;

    public enum SelectEntranceType {
        kDefault,
        kChatImage,
        kTopicEditTop,
        kEditMemberCover,
        kSticker
    }

    private class a extends ImageView {
        final /* synthetic */ SelectPicturesActivity a;

        public a(SelectPicturesActivity selectPicturesActivity, Context context) {
            this.a = selectPicturesActivity;
            super(context);
            setBackgroundResource(R.color.bg_section_divider);
            setImageResource(R.drawable.bg_take_photo);
            setScaleType(ScaleType.CENTER);
        }
    }

    private class b extends BaseAdapter {
        final /* synthetic */ SelectPicturesActivity a;

        private b(SelectPicturesActivity selectPicturesActivity) {
            this.a = selectPicturesActivity;
        }

        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            this.a.e.a(getCount());
        }

        public int getCount() {
            return this.a.j.size();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            view = view != null ? (c) view : new c(this.a, this.a);
            view.a((cn.xiaochuankeji.tieba.ui.selectlocalmedia.a.a) this.a.j.get(i));
            return view;
        }
    }

    private class c extends LinearLayout implements cn.htjyb.c.b.a.a {
        ImageView a = ((ImageView) findViewById(R.id.imgThumbnail));
        TextView b = ((TextView) findViewById(R.id.textCatalogName));
        TextView c = ((TextView) findViewById(R.id.textPictureCount));
        final /* synthetic */ SelectPicturesActivity d;
        private cn.xiaochuankeji.tieba.ui.selectlocalmedia.a.a e;

        public c(SelectPicturesActivity selectPicturesActivity, Context context) {
            this.d = selectPicturesActivity;
            super(context);
            LayoutInflater.from(context).inflate(R.layout.view_picture_catalog_item, this, true);
        }

        public void a(cn.xiaochuankeji.tieba.ui.selectlocalmedia.a.a aVar) {
            this.e = aVar;
            this.a.setBackgroundResource(R.color.bg_section_divider);
            this.a.setImageResource(R.drawable.picture_album_placeholder);
            this.a.setScaleType(ScaleType.CENTER);
            if (1 == this.e.f) {
                this.d.p.a(this.e, false, aVar.d, aVar.e, (cn.htjyb.c.b.a.a) this);
            } else {
                this.d.p.a(this.e, true, aVar.d, aVar.e, (cn.htjyb.c.b.a.a) this);
            }
            this.b.setText(this.e.c);
            this.c.setText("(" + this.e.b + ")");
            if (this.d.k == this.e.a) {
                setBackgroundResource(R.color.bg_picture_catalog_pressed);
            } else {
                setBackgroundResource(R.drawable.picture_catalog_selector);
            }
        }

        public void a(Object obj, Bitmap bitmap) {
            if (bitmap != null && this.e == obj) {
                this.a.setScaleType(ScaleType.CENTER_CROP);
                this.a.setImageBitmap(cn.htjyb.c.b.b.a(bitmap, cn.htjyb.c.a.a(3.0f, this.d), true));
            }
        }
    }

    public class d extends FrameLayout {
        final /* synthetic */ SelectPicturesActivity a;
        private ListView b = ((ListView) findViewById(R.id.listCatalog));

        public d(SelectPicturesActivity selectPicturesActivity, Context context) {
            this.a = selectPicturesActivity;
            super(context);
            LayoutInflater.from(context).inflate(R.layout.view_picture_catalogs, this, true);
        }

        public void a(int i) {
            int min = Math.min(i, 4) * getContext().getResources().getDimensionPixelSize(R.dimen.picture_catalogs_list_item_height);
            LayoutParams layoutParams = this.b.getLayoutParams();
            layoutParams.height = min;
            this.b.setLayoutParams(layoutParams);
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                Rect rect = new Rect();
                int rawX = (int) motionEvent.getRawX();
                int rawY = (int) motionEvent.getRawY();
                this.b.getGlobalVisibleRect(rect);
                if (!rect.contains(rawX, rawY)) {
                    this.a.w();
                    return true;
                }
            }
            return false;
        }
    }

    private class e extends FrameLayout implements cn.htjyb.c.b.a.a {
        final /* synthetic */ SelectPicturesActivity a;
        private LocalMedia b;
        private ImageView c;
        private ImageView d;
        private ImageView e;
        private ImageView f;
        private View g;
        private int h;

        public e(final SelectPicturesActivity selectPicturesActivity, Context context) {
            this.a = selectPicturesActivity;
            super(context);
            this.c = new ImageView(context);
            this.c.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            this.c.setScaleType(ScaleType.CENTER_CROP);
            addView(this.c);
            this.g = new View(context);
            LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            this.g.setBackgroundResource(R.drawable.icon_selected_img_mask);
            this.g.setVisibility(4);
            addView(this.g, layoutParams);
            this.d = new ImageView(context);
            int a = cn.xiaochuankeji.tieba.ui.utils.e.a(12.0f);
            LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
            layoutParams2.gravity = 53;
            this.d.setLayoutParams(layoutParams2);
            this.d.setPadding(a, a, a, a);
            this.d.setImageResource(R.drawable.selector_picture_flag_selector);
            addView(this.d);
            this.e = new ImageView(context);
            a = cn.htjyb.c.a.a(25.0f, context);
            layoutParams2 = new FrameLayout.LayoutParams(a, a);
            layoutParams2.gravity = 83;
            this.e.setLayoutParams(layoutParams2);
            this.e.setImageResource(R.drawable.icon_tucao_little);
            addView(this.e);
            this.e.setVisibility(8);
            this.f = new ImageView(context);
            layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 85;
            layoutParams.rightMargin = cn.xiaochuankeji.tieba.ui.utils.e.a(6.0f);
            layoutParams.bottomMargin = cn.xiaochuankeji.tieba.ui.utils.e.a(6.0f);
            this.f.setLayoutParams(layoutParams);
            addView(this.f);
            this.f.setVisibility(8);
            this.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ e b;

                public void onClick(View view) {
                    boolean z = true;
                    int i = 0;
                    ImageView b;
                    View c;
                    if (this.b.a.t.contains(this.b.b)) {
                        this.b.a.t.remove(this.b.b);
                        b = this.b.d;
                        if (this.b.d.isSelected()) {
                            z = false;
                        }
                        b.setSelected(z);
                        c = this.b.g;
                        if (!this.b.d.isSelected()) {
                            i = 4;
                        }
                        c.setVisibility(i);
                        this.b.a.j();
                    } else if (this.b.a.z() && !this.b.a()) {
                        this.b.a.t.add(this.b.b);
                        b = this.b.d;
                        if (this.b.d.isSelected()) {
                            z = false;
                        }
                        b.setSelected(z);
                        c = this.b.g;
                        if (!this.b.d.isSelected()) {
                            i = 4;
                        }
                        c.setVisibility(i);
                        this.b.a.j();
                    }
                }
            });
            setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ e b;

                public void onClick(View view) {
                    cn.xiaochuankeji.tieba.background.picture.a f = cn.xiaochuankeji.tieba.background.a.f();
                    ArrayList arrayList = new ArrayList();
                    Iterator it = this.b.a.l.iterator();
                    while (it.hasNext()) {
                        cn.htjyb.b.a a;
                        LocalMedia localMedia = (LocalMedia) it.next();
                        if (localMedia.type == 1) {
                            a = f.a(localMedia.path, Type.kVideo, 0);
                        } else {
                            String substring = localMedia.path.substring(localMedia.path.length() - 3, localMedia.path.length());
                            if (substring.equals("gif") || substring.equals("GIF")) {
                                a = f.a(localMedia.path, Type.kGif, 0);
                            } else {
                                a = f.a(localMedia.path, Type.kPostPicLarge, 0);
                            }
                        }
                        a.setSelect(this.b.a.t.contains(localMedia));
                        arrayList.add(a);
                    }
                    MediaBrowseWhenSelectActivity.a(this.b.a, arrayList, (ArrayList) this.b.a.l.clone(), this.b.h, this.b.a.v);
                }
            });
        }

        private boolean a() {
            long j = this.b.size;
            if ((((float) j) / 1024.0f) / 1024.0f >= 100.0f) {
                g.b("暂不支持发100M以上的视频哦~");
                return true;
            } else if (this.b.duration > TimeUnit.MINUTES.toMillis(15)) {
                g.a("上传视频不能超过15分钟");
                return true;
            } else {
                boolean z;
                if ((this.b.mimeType != null && this.b.mimeType.toLowerCase().contains("gif")) || this.b.path.endsWith("gif") || this.b.path.endsWith("GIF")) {
                    z = true;
                } else {
                    z = false;
                }
                if (this.a.n != SelectEntranceType.kSticker || !r0 || (((float) j) / 1024.0f) / 1024.0f < 10.0f) {
                    return false;
                }
                g.b("GIF大小不能超过10M");
                return true;
            }
        }

        public void a(LocalMedia localMedia, int i) {
            int i2 = 0;
            this.h = i;
            this.a.p.a(this.b);
            this.b = localMedia;
            if (localMedia == null) {
                this.c.setImageResource(R.drawable.selector_take_photo_bg);
                this.g.setVisibility(4);
                this.d.setVisibility(4);
                return;
            }
            int i3;
            Bitmap a = this.a.a((long) localMedia.mediaID);
            this.c.setImageBitmap(a);
            if (a == null) {
                if (1 == localMedia.type) {
                    this.a.p.a((Object) localMedia, false, localMedia.mediaID, localMedia.path, (cn.htjyb.c.b.a.a) this);
                } else {
                    this.a.p.a((Object) localMedia, true, localMedia.mediaID, localMedia.path, (cn.htjyb.c.b.a.a) this);
                }
            }
            this.d.setVisibility(0);
            boolean contains = this.a.t.contains(localMedia);
            this.d.setSelected(contains);
            View view = this.g;
            if (contains) {
                i3 = 0;
            } else {
                i3 = 8;
            }
            view.setVisibility(i3);
            if (1 == localMedia.type) {
                this.f.setImageResource(R.drawable.flag_video);
                this.f.setVisibility(0);
            } else {
                i3 = localMedia.path.length() - 3;
                if (localMedia.path.substring(i3).equals("gif") || localMedia.path.substring(i3).equals("GIF")) {
                    this.f.setImageResource(R.drawable.icon_gif_flag);
                    this.f.setVisibility(0);
                } else {
                    this.f.setVisibility(8);
                }
            }
            contains = this.a.u.a(localMedia.path);
            ImageView imageView = this.e;
            if (!contains) {
                i2 = 8;
            }
            imageView.setVisibility(i2);
        }

        public void a(Object obj, Bitmap bitmap) {
            if (bitmap != null) {
                this.a.a((long) ((LocalMedia) obj).mediaID, bitmap);
                if (this.b == obj) {
                    this.c.setImageBitmap(bitmap);
                }
            }
        }
    }

    private class f extends BaseAdapter {
        final /* synthetic */ SelectPicturesActivity a;
        private int b;
        private int c;
        private int d;

        public f(SelectPicturesActivity selectPicturesActivity, int i, int i2, int i3) {
            this.a = selectPicturesActivity;
            this.b = i;
            this.c = i2;
            this.d = i3;
        }

        public int getCount() {
            return this.a.l.size() + 1;
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View eVar;
            if (getItemViewType(i) == 0) {
                eVar = view != null ? view : new e(this.a, this.a);
                ((e) eVar).a((LocalMedia) this.a.l.get(i - 1), i - 1);
            } else {
                eVar = this.a.h;
            }
            int i2 = (i + 1) % this.b == 0 ? this.d : this.c;
            int i3 = this.c;
            LayoutParams layoutParams = eVar.getLayoutParams();
            if (!(layoutParams != null && i2 == layoutParams.width && i3 == layoutParams.height)) {
                eVar.setLayoutParams(new AbsListView.LayoutParams(i2, i3));
            }
            return eVar;
        }

        public int getViewTypeCount() {
            return 2;
        }

        public int getItemViewType(int i) {
            return i > 0 ? 0 : 1;
        }
    }

    static {
        C();
    }

    private static void C() {
        org.aspectj.a.b.b bVar = new org.aspectj.a.b.b("SelectPicturesActivity.java", SelectPicturesActivity.class);
        w = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.selectlocalmedia.SelectPicturesActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 124);
    }

    public static void a(Activity activity, ArrayList<String> arrayList, SelectEntranceType selectEntranceType, int i) {
        Intent intent = new Intent(activity, SelectPicturesActivity.class);
        intent.putStringArrayListExtra("key_selected_paths", arrayList);
        intent.putExtra("kKeyPictureSelectType", selectEntranceType);
        activity.startActivityForResult(intent, i);
    }

    static final void a(SelectPicturesActivity selectPicturesActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        selectPicturesActivity.d = selectPicturesActivity.getIntent().getBooleanExtra("kKeyEnableSwipeBack", true);
        super.onCreate(bundle);
        PermissionItem permissionItem = new PermissionItem("android.permission.READ_EXTERNAL_STORAGE");
        permissionItem.runIgnorePermission = false;
        permissionItem.settingText = "设置";
        permissionItem.deniedMessage = "开启以下权限才能正常浏览图片和视频";
        permissionItem.needGotoSetting = true;
        cn.xiaochuankeji.aop.permission.a.a((Context) selectPicturesActivity).a(permissionItem, new cn.xiaochuankeji.aop.permission.e(selectPicturesActivity) {
            final /* synthetic */ SelectPicturesActivity a;

            {
                this.a = r1;
            }

            public void permissionGranted() {
                if (this.a.n == SelectEntranceType.kChatImage || this.a.n == SelectEntranceType.kSticker || this.a.n == SelectEntranceType.kTopicEditTop || this.a.n == SelectEntranceType.kEditMemberCover) {
                    Log.i(cn.xiaochuankeji.tieba.background.utils.b.e.a, "只显示图片");
                    this.a.c(true);
                    return;
                }
                Log.i(cn.xiaochuankeji.tieba.background.utils.b.e.a, "显示图片和视频");
                this.a.c(false);
            }

            public void permissionDenied() {
                g.a("开启以下权限才能正常浏览图片和视频");
                this.a.finish();
            }
        });
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = org.aspectj.a.b.b.a(w, this, this, bundle);
        cn.xiaochuankeji.aop.permission.c.c().a(new c(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected int a() {
        return R.layout.activity_select_pictures;
    }

    protected boolean a(Bundle bundle) {
        this.u = cn.xiaochuankeji.tieba.background.h.e.a();
        this.n = (SelectEntranceType) getIntent().getSerializableExtra("kKeyPictureSelectType");
        if (this.n == SelectEntranceType.kTopicEditTop || this.n == SelectEntranceType.kEditMemberCover) {
            this.v = 1;
        }
        return true;
    }

    public boolean h() {
        return this.d;
    }

    protected void c() {
        this.a.setTitle("全部照片");
        this.a.setTitleRightDrawable(R.drawable.arrow_down);
        this.f = (GridView) findViewById(R.id.viewPictures);
        this.g = (TextView) findViewById(R.id.textConform);
        this.e = new d(this, this);
        this.e.setVisibility(8);
        this.e.setLayoutParams(new LayoutParams(-1, -1));
        ((ViewGroup) findViewById(R.id.rootView)).addView(this.e);
        this.h = new a(this, this);
        this.a.setTitleClickListener(new cn.xiaochuankeji.tieba.ui.widget.NavigationBar.b(this) {
            final /* synthetic */ SelectPicturesActivity a;

            {
                this.a = r1;
            }

            public void a() {
                if (!this.a.j.isEmpty()) {
                    this.a.w();
                }
            }
        });
    }

    protected void i_() {
        j();
        k();
        this.r = new b();
        this.e.b.setAdapter(this.r);
    }

    private void j() {
        this.g.setText("确定(" + this.t.size() + "/" + this.v + ")");
    }

    private void k() {
        int a = cn.xiaochuankeji.tieba.ui.utils.e.a(1.0f);
        int b = (cn.xiaochuankeji.tieba.ui.utils.e.b() - (a * 2)) / 3;
        int b2 = cn.xiaochuankeji.tieba.ui.utils.e.b() - ((a + b) * 2);
        this.f.setNumColumns(3);
        this.f.setVerticalSpacing(a);
        this.f.setHorizontalSpacing(a);
        this.q = new f(this, 3, b, b2);
        this.f.setAdapter(this.q);
    }

    protected void j_() {
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.e.b.setOnItemClickListener(this);
    }

    public void onBackPressed() {
        if (!d(false)) {
            super.onBackPressed();
        }
    }

    private void c(boolean z) {
        cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this, true);
        this.o = new a(getContentResolver(), this, z);
        if (VERSION.SDK_INT >= 11) {
            this.o.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            this.o.execute(new Void[0]);
        }
        this.p = new cn.htjyb.c.b.a(getContentResolver());
        this.p.start();
    }

    protected void onDestroy() {
        super.onDestroy();
        org.greenrobot.eventbus.c.a().c(this);
        if (this.o != null) {
            this.o.cancel(true);
        }
        if (this.p != null) {
            this.p.a();
        }
        if (this.m != null) {
            for (SoftReference softReference : this.m.values()) {
                Bitmap bitmap = (Bitmap) softReference.get();
                if (bitmap != null) {
                    bitmap.recycle();
                }
            }
            this.m.clear();
        }
    }

    public void a(ArrayList<LocalMedia> arrayList, ArrayList<cn.xiaochuankeji.tieba.ui.selectlocalmedia.a.a> arrayList2) {
        cn.xiaochuankeji.tieba.ui.widget.g.c(this);
        this.i = arrayList;
        this.j = arrayList2;
        if (!this.i.isEmpty()) {
            LocalMedia localMedia = (LocalMedia) this.i.get(0);
            cn.xiaochuankeji.tieba.ui.selectlocalmedia.a.a aVar = new cn.xiaochuankeji.tieba.ui.selectlocalmedia.a.a();
            aVar.a = -1;
            aVar.c = "全部照片";
            aVar.b = this.i.size();
            aVar.d = localMedia.mediaID;
            aVar.e = localMedia.path;
            aVar.f = localMedia.type;
            this.j.add(0, aVar);
        }
        if (this.j.isEmpty()) {
            this.a.setTitleRightDrawable(0);
        }
        this.l = new ArrayList(this.i);
        if (!(this.n == SelectEntranceType.kChatImage || this.n == SelectEntranceType.kSticker || this.n == SelectEntranceType.kTopicEditTop || this.n == SelectEntranceType.kEditMemberCover)) {
            v();
        }
        this.q.notifyDataSetChanged();
        this.r.notifyDataSetChanged();
    }

    private void v() {
        ArrayList stringArrayListExtra = getIntent().getStringArrayListExtra("key_selected_paths");
        if (stringArrayListExtra != null && stringArrayListExtra.size() > 0) {
            Iterator it = stringArrayListExtra.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                Iterator it2 = this.l.iterator();
                while (it2.hasNext()) {
                    LocalMedia localMedia = (LocalMedia) it2.next();
                    if (localMedia.path.equals(str)) {
                        this.t.add(localMedia);
                        break;
                    }
                }
            }
        }
        j();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textConform:
                x();
                break;
        }
        if (this.h == view && z()) {
            B();
        }
    }

    private void w() {
        boolean z;
        boolean z2 = true;
        if (this.e.getVisibility() == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            z2 = false;
        }
        d(z2);
    }

    private boolean d(boolean z) {
        if (z) {
            if (this.e.getVisibility() != 0) {
                this.e.setVisibility(0);
                this.a.setTitleRightDrawable(R.drawable.arrow_up);
                return true;
            }
        } else if (this.e.getVisibility() == 0) {
            this.e.setVisibility(4);
            this.a.setTitleRightDrawable(R.drawable.arrow_down);
            return true;
        }
        return false;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            switch (i) {
                case 1:
                    String[] strArr = new String[]{A()};
                    MediaScannerConnection.scanFile(this, strArr, null, null);
                    com.izuiyou.a.a.b.e("takePhoto path:" + strArr[0]);
                    LocalMedia localMedia = new LocalMedia();
                    localMedia.path = strArr[0];
                    localMedia.type = 2;
                    this.t.add(localMedia);
                    x();
                    break;
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    private void x() {
        if (this.t.size() == 0) {
            g.a("请至少选择一张图片");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("key_selected_local_media", this.t);
        setResult(-1, intent);
        finish();
    }

    @l(a = ThreadMode.MAIN)
    public void message(MessageEvent messageEvent) {
        if (messageEvent.getEventType() == MessageEventType.MESSAGE_BIGPIC_CONFIRM) {
            a((ArrayList) messageEvent.getData());
            x();
        } else if (messageEvent.getEventType() == MessageEventType.MESSAGE_BIG_PIC_BACK_PRESSED) {
            a((ArrayList) messageEvent.getData());
        } else if (messageEvent.getEventType() == MessageEventType.MESSAGE_EDIR_FINISH_IN_BIGPIC) {
            a((ArrayList) messageEvent.getData());
            LocalMedia a = a((cn.htjyb.b.a) messageEvent.getExtraData());
            this.i.add(0, a);
            this.l.add(0, a);
            if (this.t.size() < this.v) {
                this.t.add(a);
            }
            this.q.notifyDataSetChanged();
            j();
        }
    }

    private LocalMedia a(cn.htjyb.b.a aVar) {
        LocalMedia localMedia = new LocalMedia();
        localMedia.path = aVar.cachePath();
        localMedia.dateAdded = System.currentTimeMillis();
        localMedia.mediaID = 0 - y();
        localMedia.type = 2;
        return localMedia;
    }

    private int y() {
        return 1 + (new Random().nextInt(100000) % 100000);
    }

    private void a(ArrayList<Integer> arrayList) {
        Collection arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(this.l.get(((Integer) it.next()).intValue()));
        }
        this.t.clear();
        this.t.addAll(arrayList2);
        j();
        this.q.notifyDataSetChanged();
    }

    private boolean z() {
        com.izuiyou.a.a.b.e("mUserSelectMedias.size():" + this.t.size() + " _maxSelectCount:" + this.v);
        if (this.t.size() < this.v) {
            return true;
        }
        g.a("最多选择" + this.v + "张图片");
        return false;
    }

    private String A() {
        if (this.s == null) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (externalStorageDirectory != null) {
                File file = new File(externalStorageDirectory.getPath() + "/DCIM/Camera");
                file.mkdirs();
                this.s = file.getPath() + "/" + System.currentTimeMillis() + ".jpg";
            } else {
                this.s = BaseApplication.getAppContext().getExternalCacheDir().getPath() + "/" + System.currentTimeMillis() + ".jpg";
            }
        }
        return this.s;
    }

    private void B() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", Uri.fromFile(new File(A())));
        startActivityForResult(intent, 1);
    }

    private Bitmap a(long j) {
        SoftReference softReference = (SoftReference) this.m.get(Long.valueOf(j));
        return softReference != null ? (Bitmap) softReference.get() : null;
    }

    private void a(long j, Bitmap bitmap) {
        this.m.put(Long.valueOf(j), new SoftReference(bitmap));
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        w();
        cn.xiaochuankeji.tieba.ui.selectlocalmedia.a.a aVar = (cn.xiaochuankeji.tieba.ui.selectlocalmedia.a.a) this.j.get(i);
        if (this.k != aVar.a) {
            this.a.setTitle(aVar.c);
            this.k = aVar.a;
            if (-1 == this.k) {
                this.l = new ArrayList(this.i);
            } else {
                this.l.clear();
                Iterator it = this.i.iterator();
                while (it.hasNext()) {
                    LocalMedia localMedia = (LocalMedia) it.next();
                    if (localMedia.bucketID == this.k) {
                        this.l.add(localMedia);
                    }
                }
            }
            this.r.notifyDataSetChanged();
            this.q.notifyDataSetChanged();
        }
    }
}
