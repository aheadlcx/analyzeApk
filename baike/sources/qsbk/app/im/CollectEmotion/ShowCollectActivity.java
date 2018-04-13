package qsbk.app.im.CollectEmotion;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.io.File;
import java.util.ArrayList;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.publish.QiniuUploaderForCollect;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.LatestUsedCollectionData;
import qsbk.app.im.datastore.LatestUsedCollectionStore;
import qsbk.app.im.image.ImageUploader;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.ReadLine;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.widget.CheckedImageView;

public class ShowCollectActivity extends BaseActionBarActivity {
    public static final String COLLECT_DATA = "collectData";
    private a a;
    private ArrayList<Object> b = new ArrayList();
    private GridView c;
    private int d = 0;
    private ProgressDialog e = null;
    private SimpleHttpTask f;
    private LatestUsedCollectionStore g = null;
    private CollectionManager h;

    private class a extends BaseImageAdapter {
        final /* synthetic */ ShowCollectActivity a;

        private class a {
            int a;
            LatestUsedCollectionData b;
            CheckedImageView c;
            final /* synthetic */ a d;

            private a(a aVar) {
                this.d = aVar;
            }
        }

        public a(ShowCollectActivity showCollectActivity, Activity activity) {
            this.a = showCollectActivity;
            super(showCollectActivity.b, activity);
        }

        public int getViewTypeCount() {
            return 2;
        }

        public int getItemViewType(int i) {
            if (getItem(i) instanceof LatestUsedCollectionData) {
                return 0;
            }
            return 1;
        }

        private void a(ImageView imageView, String str, ResizeOptions resizeOptions) {
            if (resizeOptions == null) {
                a(imageView, str);
                return;
            }
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) imageView;
            DraweeController build = ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setOldController(simpleDraweeView.getController())).setImageRequest(ImageRequestBuilder.newBuilderWithSource(FrescoImageloader.get(str)).setResizeOptions(resizeOptions).build())).build();
            DraweeHierarchy draweeHierarchy = (GenericDraweeHierarchy) simpleDraweeView.getHierarchy();
            if (draweeHierarchy == null) {
                draweeHierarchy = new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).build();
            }
            draweeHierarchy.setPlaceholderImage(d());
            draweeHierarchy.setFailureImage(null);
            build.setHierarchy(draweeHierarchy);
            simpleDraweeView.setController(build);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                aVar = new a();
                view = this.a.getLayoutInflater().inflate(R.layout.item_image_picker, null);
                aVar.c = (CheckedImageView) view.findViewById(R.id.image_folder_image);
                aVar.c.setCheckable(false);
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            aVar.a = i;
            if (getItemViewType(i) == 0) {
                LatestUsedCollectionData latestUsedCollectionData = (LatestUsedCollectionData) getItem(i);
                if (aVar.b != latestUsedCollectionData) {
                    int i2 = latestUsedCollectionData.type;
                    LayoutParams layoutParams = aVar.c.getLayoutParams();
                    if (layoutParams == null) {
                        layoutParams = new AbsListView.LayoutParams(this.a.d, this.a.d);
                    } else {
                        layoutParams.width = this.a.d;
                        layoutParams.height = this.a.d;
                    }
                    aVar.c.setLayoutParams(layoutParams);
                    ResizeOptions resizeOptions = new ResizeOptions(layoutParams.width, layoutParams.height);
                    switch (i2) {
                        case 1:
                            if (!new File(DeviceUtils.getCollectSDPath() + File.separator + latestUsedCollectionData.collectImageDomain.url).exists()) {
                                a(aVar.c, latestUsedCollectionData.collectImageDomain.netUrl, resizeOptions);
                                break;
                            }
                            a(aVar.c, "file://" + DeviceUtils.getCollectSDPath() + File.separator + latestUsedCollectionData.collectImageDomain.url, resizeOptions);
                            break;
                        case 2:
                            aVar.c.setImageResource(latestUsedCollectionData.chatMsgEmotionData.localResource);
                            break;
                        case 3:
                            if (!latestUsedCollectionData.collectImageLocal.localUrl.startsWith("file://")) {
                                if (!new File(latestUsedCollectionData.collectImageLocal.localUrl).exists()) {
                                    a(aVar.c, latestUsedCollectionData.collectImageLocal.netUrl, resizeOptions);
                                    break;
                                }
                                a(aVar.c, "file://" + latestUsedCollectionData.collectImageLocal.localUrl, resizeOptions);
                                break;
                            } else if (!new File(latestUsedCollectionData.collectImageLocal.localUrl.substring(8)).exists()) {
                                a(aVar.c, latestUsedCollectionData.collectImageLocal.netUrl, resizeOptions);
                                break;
                            } else {
                                a(aVar.c, latestUsedCollectionData.collectImageLocal.localUrl, resizeOptions);
                                break;
                            }
                    }
                    aVar.b = latestUsedCollectionData;
                }
            } else if (getItemViewType(i) == 1) {
                aVar.c.setChecked(false);
                aVar.c.setCheckable(false);
                aVar.c.setScaleType(ScaleType.FIT_XY);
                aVar.c.setImageResource(R.drawable.image_add);
                aVar.c.setBackgroundColor(ViewCompat.MEASURED_SIZE_MASK);
                aVar.c.setOnClickListener(new o(this));
            }
            return view;
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Activity activity, int i) {
        activity.startActivityForResult(new Intent(activity, ShowCollectActivity.class), i);
    }

    public static void launch(Activity activity, int i, ArrayList<LatestUsedCollectionData> arrayList) {
        Intent intent = new Intent(activity, ShowCollectActivity.class);
        intent.putExtra("collectData", arrayList);
        activity.startActivityForResult(intent, i);
    }

    private void g() {
        if (this.e != null) {
            this.e.dismiss();
        }
        this.e = ProgressDialog.show(this, null, "上传中，请稍候..", true, true);
        this.e.setCanceledOnTouchOutside(false);
        this.e.setOnCancelListener(new l(this));
    }

    private void i() {
        if (this.e != null) {
            this.e.dismiss();
            this.e = null;
        }
    }

    public void getToken(Uri uri) {
        g();
        new SimpleHttpTask(ImageUploader.im_get_token + QsbkApp.currentUser.userId, new m(this, uri)).execute();
    }

    private void a(String str, Uri uri) {
        new QiniuUploaderForCollect(str, uri, new n(this)).startUpload();
    }

    private boolean j() {
        return this.e == null || !this.e.isShowing();
    }

    protected String f() {
        if (this.b.size() > 1) {
            return "我添加的表情(" + (this.b.size() - 1) + ")";
        }
        return "我添加的表情";
    }

    protected int a() {
        return R.layout.activity_show_collect;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.h = CollectionManager.getInstance(QsbkApp.currentUser.userId);
        this.g = LatestUsedCollectionStore.getCollectionStore(QsbkApp.currentUser.userId);
        k();
        l();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.d = (int) ((((float) displayMetrics.widthPixels) - (displayMetrics.density * 20.0f)) / 4.0f);
        setResult(0);
        init();
    }

    private void k() {
        File file = new File(DeviceUtils.getCollectSDPath() + "/.nomedia");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void init() {
        this.b.clear();
        this.b.addAll(this.h.feachAllData());
        this.b.add(new ReadLine());
    }

    protected void onResume() {
        super.onResume();
    }

    private void l() {
        this.c = (GridView) findViewById(R.id.image_picker_grid);
        this.a = new a(this, this);
        this.c.setAdapter(this.a);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItemCompat.setShowAsAction(menu.add(0, 1, 1, "整理"), 2);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 1:
                ManageCollectActivity.launch(this, 101, this.h.feachAllData());
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 101) {
            this.h.getAll();
            init();
            if (this.a != null) {
                this.a.notifyDataSetChanged();
            }
            setTitle(f());
        } else if (i == 102 && i2 == -1) {
            getToken(Uri.parse(intent.getStringExtra("uploadUri")));
        }
    }
}
