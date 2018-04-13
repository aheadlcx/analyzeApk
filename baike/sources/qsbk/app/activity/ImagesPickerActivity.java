package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ImageDecodeOptionsBuilder;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.adapter.DefaultAdapter;
import qsbk.app.imagepicker.ImageFolderInfo;
import qsbk.app.imagepicker.ImagePickerManager;
import qsbk.app.model.ImageInfo;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.utils.FileUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ResultActivityListener;
import qsbk.app.widget.CheckedImageView;

public class ImagesPickerActivity extends BaseActionBarActivity {
    public static final String CHECKED_ARRAY_KEY = "checkedArray";
    public static final String COUNT_KEY = "count";
    public static final int KEY_DIRECTOR_RETURN = 999;
    public static final int KEY_PRIVIEW = 99;
    public static final String PATH_KEY = "paths";
    public static int maxCount = 6;
    private ArrayList<ImageFolderInfo> a = new ArrayList();
    private ArrayList<Object> b = new ArrayList();
    private b c;
    private c d;
    private ArrayList<ImageInfo> e = new ArrayList();
    private ListView f;
    private View g;
    private GridView h;
    private TextView i;
    private TextView j;
    private TextView k;
    private RelativeLayout l;
    private TextView m;
    private Animation n;
    private Animation o;
    private Animation p;
    private Animation q;
    private boolean r = false;
    private int s = 0;
    private ImagePickerManager t;
    private int u;
    private Uri v;
    private ResultActivityListener w = new ow(this);

    private class a {
        SimpleDraweeView a;
        TextView b;
        TextView c;
        final /* synthetic */ ImagesPickerActivity d;

        private a(ImagesPickerActivity imagesPickerActivity) {
            this.d = imagesPickerActivity;
        }
    }

    private class b extends DefaultAdapter<ImageFolderInfo> {
        final /* synthetic */ ImagesPickerActivity a;

        public b(ImagesPickerActivity imagesPickerActivity, Activity activity, ArrayList<ImageFolderInfo> arrayList) {
            this.a = imagesPickerActivity;
            super(arrayList, activity);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                view = this.a.getLayoutInflater().inflate(R.layout.item_image_folder, viewGroup, false);
                aVar = new a();
                aVar.a = (SimpleDraweeView) view.findViewById(R.id.image_folder_image);
                aVar.b = (TextView) view.findViewById(R.id.image_folder_name);
                aVar.c = (TextView) view.findViewById(R.id.image_folder_count);
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            ImageFolderInfo imageFolderInfo = (ImageFolderInfo) getItem(i);
            aVar.b.setText(imageFolderInfo.getName());
            aVar.c.setText(imageFolderInfo.list().size() + "张");
            if (imageFolderInfo.list().size() > 0) {
                LayoutParams layoutParams = aVar.a.getLayoutParams();
                if (!(layoutParams != null && layoutParams.height == this.a.s && layoutParams.width == this.a.s)) {
                    aVar.a.setLayoutParams(new RelativeLayout.LayoutParams(this.a.s, this.a.s));
                }
                this.a.a(aVar.a, ((ImageInfo) imageFolderInfo.list().get(0)).url);
            }
            return view;
        }
    }

    private class c extends BaseImageAdapter {
        final /* synthetic */ ImagesPickerActivity a;

        private class a {
            int a;
            ImageInfo b;
            CheckedImageView c;
            final /* synthetic */ c d;

            private a(c cVar) {
                this.d = cVar;
            }
        }

        public c(ImagesPickerActivity imagesPickerActivity, Activity activity, ArrayList<Object> arrayList) {
            this.a = imagesPickerActivity;
            super(arrayList, activity);
        }

        public int getViewTypeCount() {
            return 2;
        }

        public int getItemViewType(int i) {
            if (getItem(i) instanceof ImageInfo) {
                return 0;
            }
            return 1;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                aVar = new a();
                view = this.a.getLayoutInflater().inflate(R.layout.item_image_picker, null);
                aVar.c = (CheckedImageView) view.findViewById(R.id.image_folder_image);
                view.setTag(aVar);
                if (getItemViewType(i) == 1) {
                    aVar.c.setChecked(false);
                    aVar.c.setCheckable(false);
                    aVar.c.setImageResource(R.drawable.image_picker_camera);
                    aVar.c.setBackgroundColor(-12303292);
                    aVar.c.setOnClickListener(new pi(this));
                } else {
                    aVar.c.setOnClickListener(new pj(this, aVar));
                    aVar.c.setOnCheckedChangeListener(new pk(this, aVar));
                }
                LayoutParams layoutParams = aVar.c.getLayoutParams();
                if (!(layoutParams != null && layoutParams.height == this.a.s && layoutParams.width == this.a.s)) {
                    aVar.c.setLayoutParams(new AbsListView.LayoutParams(this.a.s, this.a.s));
                }
            } else {
                aVar = (a) view.getTag();
            }
            aVar.a = i;
            if (getItemViewType(i) == 0) {
                ImageInfo imageInfo = (ImageInfo) getItem(i);
                aVar.c.setTypeImageResouce(MediaFormat.getFormatTagImage(imageInfo.mediaFormat));
                if (this.a.e.contains(imageInfo)) {
                    aVar.c.setChecked(true);
                } else {
                    aVar.c.setChecked(false);
                }
                if (aVar.b != imageInfo) {
                    if (aVar.b != null) {
                        this.a.a(aVar.c, imageInfo.url);
                        aVar.b = imageInfo;
                    } else {
                        this.a.a(aVar.c, imageInfo.url);
                        aVar.b = imageInfo;
                    }
                }
            } else {
                aVar.c.setTypeImageResouce(0);
            }
            return view;
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return e();
    }

    public static Intent prepareIntent(Activity activity, int i) {
        Intent intent = new Intent(activity, ImagesPickerActivity.class);
        intent.putExtra("count", i);
        return intent;
    }

    public static Intent prepareIntent(Activity activity, int i, ArrayList<ImageInfo> arrayList) {
        Intent intent = new Intent(activity, ImagesPickerActivity.class);
        intent.putExtra("count", i);
        intent.putExtra(CHECKED_ARRAY_KEY, arrayList);
        return intent;
    }

    protected String e() {
        return "图片选择";
    }

    protected int a() {
        return R.layout.fragment_image_picker;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.t = ImagePickerManager.newInstance();
        maxCount = getIntent().getIntExtra("count", 0);
        this.u = getIntent().getIntExtra("KEY_PICK_IAMGE", 0);
        ArrayList arrayList = (ArrayList) getIntent().getSerializableExtra(CHECKED_ARRAY_KEY);
        if (arrayList != null && arrayList.size() > 0) {
            this.e.addAll(arrayList);
        }
        if (maxCount <= 0) {
            finish();
        }
        f();
        g();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.s = (int) ((((float) displayMetrics.widthPixels) - (displayMetrics.density * 20.0f)) / 4.0f);
        setResult(0);
        k();
    }

    protected void onResume() {
        super.onResume();
    }

    private void f() {
        this.c = new b(this, this, this.a);
        this.h = (GridView) findViewById(R.id.image_picker_grid);
        this.h.setOnItemClickListener(new oy(this));
        this.d = new c(this, this, this.b);
        this.h.setAdapter(this.d);
        this.f = (ListView) findViewById(R.id.image_folder_list);
        this.g = findViewById(R.id.image_folder_list_mask);
        this.f.setAdapter(this.c);
        this.f.setOnItemClickListener(new pa(this));
        this.i = (TextView) findViewById(R.id.picker_name);
        this.j = (TextView) findViewById(R.id.picker_count);
        if (this.u != 0) {
            this.j.setText("发送");
        }
        this.k = (TextView) findViewById(R.id.select_num);
        this.k.setText(this.e.size() + "");
        this.l = (RelativeLayout) findViewById(R.id.bottom_layout);
        this.l.setOnClickListener(new pb(this));
        this.m = (TextView) findViewById(R.id.picker_preview);
        if (this.e.size() > 0) {
            this.m.setClickable(true);
            this.m.setTextColor(-1);
            this.m.setOnClickListener(new pc(this));
        } else {
            this.m.setClickable(false);
            this.m.setTextColor(-1184275);
        }
        this.i.setOnClickListener(new pd(this));
    }

    private void g() {
        this.n = new TranslateAnimation(0, 0.0f, 0, 0.0f, 2, 1.0f, 1, 0.0f);
        this.n.setDuration(300);
        this.o = new TranslateAnimation(0, 0.0f, 0, 0.0f, 1, 0.0f, 2, 1.0f);
        this.o.setAnimationListener(new pe(this));
        this.o.setDuration(300);
        this.p = new AlphaAnimation(0.0f, 1.0f);
        this.p.setDuration(300);
        this.q = new AlphaAnimation(1.0f, 0.0f);
        this.q.setDuration(300);
    }

    private void i() {
        this.r = true;
        this.f.setVisibility(0);
        this.g.setVisibility(0);
        this.f.startAnimation(this.n);
        this.g.startAnimation(this.p);
        this.g.setOnTouchListener(new pf(this));
    }

    private void j() {
        this.r = false;
        this.f.startAnimation(this.o);
        this.g.startAnimation(this.q);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItemCompat.setShowAsAction(menu.add(0, 1, 1, "取消"), 2);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 1:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void k() {
        this.t.reset();
        this.a.clear();
        this.c.notifyDataSetChanged();
        this.t.init(this, new pg(this));
    }

    private void a(int i) {
        ImageFolderInfo imageFolderInfo = (ImageFolderInfo) this.a.get(i);
        this.i.setText(imageFolderInfo.getName());
        Collection list = imageFolderInfo.list();
        this.b.clear();
        this.b.add(new Object());
        this.b.addAll(list);
        this.d.notifyDataSetChanged();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == 999) {
            LogUtil.e("set result ok");
            ArrayList arrayList = (ArrayList) intent.getSerializableExtra("paths");
            Intent intent2 = new Intent();
            intent2.putExtra("paths", arrayList);
            setResult(-1, intent2);
            finish();
        } else {
            if (i == 99 && i2 == 99) {
                this.e = (ArrayList) intent.getSerializableExtra(CHECKED_ARRAY_KEY);
            }
            if (this.d != null) {
                this.d.notifyDataSetChanged();
            }
            this.k.setText(this.e.size() + "");
            if (this.e.size() > 0) {
                this.m.setClickable(true);
                this.m.setTextColor(-1);
                this.m.setOnClickListener(new ox(this));
            } else {
                this.m.setClickable(false);
                this.m.setTextColor(-1184275);
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    private void l() {
        if (!isFinishing()) {
            this.k.setText(this.e.size() + "");
        }
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public void startCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        this.v = Uri.fromFile(a((Context) this));
        intent.putExtra("output", this.v);
        a(intent, this.w);
    }

    private File a(Context context) {
        return new File(FileUtils.getExternalDCIMDir(context), "QSBK" + System.currentTimeMillis() + ".jpg");
    }

    private void a(SimpleDraweeView simpleDraweeView, String str) {
        simpleDraweeView.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setOldController(simpleDraweeView.getController())).setImageRequest(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).setResizeOptions(new ResizeOptions(this.s, this.s)).setImageDecodeOptions(new ImageDecodeOptionsBuilder().setForceStaticImage(true).build()).build())).build());
    }
}
