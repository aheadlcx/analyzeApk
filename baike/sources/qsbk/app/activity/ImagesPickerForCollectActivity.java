package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.adapter.DefaultAdapter;
import qsbk.app.imagepicker.ImageFolderInfo;
import qsbk.app.imagepicker.ImagePickerManager;
import qsbk.app.model.ImageInfo;
import qsbk.app.widget.CheckedImageView;

public class ImagesPickerForCollectActivity extends BaseActionBarActivity {
    public static final int KEY_COLLECT_FOR_CAMARE = 100;
    public static final int KEY_COLLECT_FOR_LOCAL = 99;
    public static final int KEY_OPEN_CAMERE = 1000;
    private ArrayList<ImageFolderInfo> a;
    private ArrayList<Object> b;
    private b c;
    private c d;
    private ListView e;
    private View f;
    private GridView g;
    private TextView h;
    private Animation i;
    private Animation j;
    private Animation k;
    private Animation l;
    private boolean m = false;
    private int n = 0;
    private ResizeOptions o;
    private ImagePickerManager p;
    private Uri q;

    private class a {
        SimpleDraweeView a;
        TextView b;
        TextView c;
        final /* synthetic */ ImagesPickerForCollectActivity d;

        private a(ImagesPickerForCollectActivity imagesPickerForCollectActivity) {
            this.d = imagesPickerForCollectActivity;
        }
    }

    private class b extends DefaultAdapter<ImageFolderInfo> {
        final /* synthetic */ ImagesPickerForCollectActivity a;

        public b(ImagesPickerForCollectActivity imagesPickerForCollectActivity, Activity activity) {
            this.a = imagesPickerForCollectActivity;
            super(imagesPickerForCollectActivity.a, activity);
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
                if (!(layoutParams != null && layoutParams.height == this.a.n && layoutParams.width == this.a.n)) {
                    aVar.a.setLayoutParams(new RelativeLayout.LayoutParams(this.a.n, this.a.n));
                }
                this.a.a(aVar.a, ((ImageInfo) imageFolderInfo.list().get(0)).url);
            }
            return view;
        }
    }

    private class c extends BaseImageAdapter {
        final /* synthetic */ ImagesPickerForCollectActivity a;

        private class a {
            int a;
            ImageInfo b;
            CheckedImageView c;
            final /* synthetic */ c d;

            private a(c cVar) {
                this.d = cVar;
            }
        }

        public c(ImagesPickerForCollectActivity imagesPickerForCollectActivity, Activity activity) {
            this.a = imagesPickerForCollectActivity;
            super(imagesPickerForCollectActivity.b, activity);
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
                    aVar.c.setOnClickListener(new pt(this));
                } else {
                    aVar.c.setChecked(false);
                    aVar.c.setCheckable(false);
                }
                LayoutParams layoutParams = aVar.c.getLayoutParams();
                if (!(layoutParams != null && layoutParams.height == this.a.n && layoutParams.width == this.a.n)) {
                    aVar.c.setLayoutParams(new AbsListView.LayoutParams(this.a.n, this.a.n));
                }
            } else {
                aVar = (a) view.getTag();
            }
            aVar.a = i;
            if (getItemViewType(i) == 0) {
                ImageInfo imageInfo = (ImageInfo) getItem(i);
                if (aVar.b != imageInfo) {
                    this.a.a(aVar.c, imageInfo.url);
                    aVar.b = imageInfo;
                }
                aVar.c.setOnClickListener(new pu(this, imageInfo));
            }
            return view;
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return e();
    }

    public static Intent prepareIntent(Activity activity) {
        return new Intent(activity, ImagesPickerForCollectActivity.class);
    }

    protected String e() {
        return "图片";
    }

    protected int a() {
        return R.layout.image_picker_for_collect;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.p = ImagePickerManager.newInstance();
        f();
        g();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.n = (int) ((((float) displayMetrics.widthPixels) - (displayMetrics.density * 20.0f)) / 4.0f);
        this.o = new ResizeOptions(this.n, this.n);
        setResult(0);
    }

    protected void onResume() {
        super.onResume();
        k();
    }

    private void f() {
        this.g = (GridView) findViewById(R.id.image_picker_grid);
        this.g.setOnItemClickListener(new pm(this));
        this.e = (ListView) findViewById(R.id.image_folder_list);
        this.f = findViewById(R.id.image_folder_list_mask);
        this.e.setOnItemClickListener(new pn(this));
        this.h = (TextView) findViewById(R.id.picker_name);
        this.h.setOnClickListener(new po(this));
    }

    private void g() {
        this.i = new TranslateAnimation(0, 0.0f, 0, 0.0f, 2, 1.0f, 1, 0.0f);
        this.i.setDuration(300);
        this.j = new TranslateAnimation(0, 0.0f, 0, 0.0f, 1, 0.0f, 2, 1.0f);
        this.j.setAnimationListener(new pp(this));
        this.j.setDuration(300);
        this.k = new AlphaAnimation(0.0f, 1.0f);
        this.k.setDuration(300);
        this.l = new AlphaAnimation(1.0f, 0.0f);
        this.l.setDuration(300);
    }

    private void i() {
        this.m = true;
        this.e.setVisibility(0);
        this.f.setVisibility(0);
        this.e.startAnimation(this.i);
        this.f.startAnimation(this.k);
        this.f.setOnTouchListener(new pq(this));
    }

    private void j() {
        this.m = false;
        this.e.startAnimation(this.j);
        this.f.startAnimation(this.l);
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
        this.p.reset();
        this.p.init(this, new pr(this));
    }

    private void a(int i) {
        ImageFolderInfo imageFolderInfo = (ImageFolderInfo) this.a.get(i);
        this.h.setText(imageFolderInfo.getName());
        Collection list = imageFolderInfo.list();
        this.b = new ArrayList();
        this.b.add(new Object());
        this.b.addAll(list);
        this.d = new c(this, this);
        this.g.setAdapter(this.d);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        String stringExtra;
        Intent intent2;
        if (i == 99 && i2 == -1) {
            stringExtra = intent.getStringExtra("uploadUri");
            intent2 = new Intent();
            intent2.putExtra("uploadUri", stringExtra);
            intent2.putExtra("type", 0);
            setResult(-1, intent2);
            finish();
        } else if (i == 1000 && i2 == -1) {
            if (this.q != null) {
                Intent intent3 = new Intent();
                intent3.setClass(this, ShowCollectImageActivity.class);
                intent3.putExtra("uri", this.q.toString());
                startActivityForResult(intent3, 100);
            }
        } else if (i == 100 && i2 == -1) {
            stringExtra = intent.getStringExtra("uploadUri");
            intent2 = new Intent();
            intent2.putExtra("uploadUri", stringExtra);
            intent2.putExtra("type", 1);
            setResult(-1, intent2);
            finish();
        }
        super.onActivityResult(i, i2, intent);
    }

    public void startCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        this.q = Uri.fromFile(a((Context) this));
        intent.putExtra("output", this.q);
        startActivityForResult(intent, 1000);
    }

    private File a(Context context) {
        File file = new File(Environment.getExternalStorageDirectory(), context.getPackageName());
        if (!file.exists()) {
            file.mkdir();
        }
        return new File(file, ((long) new Date().getSeconds()) + ".jpg");
    }

    private void a(SimpleDraweeView simpleDraweeView, String str) {
        simpleDraweeView.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setOldController(simpleDraweeView.getController())).setImageRequest(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).setResizeOptions(this.o).build())).build());
    }
}
