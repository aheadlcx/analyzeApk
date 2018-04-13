package qsbk.app.im.CollectEmotion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import java.util.Iterator;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.im.LatestUsedCollectionData;
import qsbk.app.im.datastore.LatestUsedCollectionStore;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.widget.CheckedImageView;

public class ManageCollectActivity extends BaseActionBarActivity {
    public static final String COLLECT_DATA = "collectData";
    public static final String COUNT_KEY = "count";
    public static final String PATH_KEY = "paths";
    LatestUsedCollectionStore a;
    private ArrayList<Object> b;
    private a c;
    private ArrayList<LatestUsedCollectionData> d = new ArrayList();
    private GridView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private RelativeLayout i;
    private int j = 0;

    private class a extends BaseImageAdapter {
        final /* synthetic */ ManageCollectActivity a;

        private class a {
            int a;
            LatestUsedCollectionData b;
            CheckedImageView c;
            final /* synthetic */ a d;

            private a(a aVar) {
                this.d = aVar;
            }
        }

        public a(ManageCollectActivity manageCollectActivity, Activity activity) {
            this.a = manageCollectActivity;
            super(manageCollectActivity.b, activity);
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
                view.setTag(aVar);
                if (getItemViewType(i) == 1) {
                    aVar.c.setChecked(false);
                    aVar.c.setCheckable(false);
                    aVar.c.setScaleType(ScaleType.CENTER);
                    aVar.c.setImageResource(R.drawable.image_picker_camera);
                    aVar.c.setBackgroundColor(-12303292);
                    aVar.c.setOnClickListener(new i(this));
                } else {
                    aVar.c.setOnCheckedChangeListener(new j(this, aVar));
                }
            } else {
                aVar = (a) view.getTag();
            }
            aVar.a = i;
            if (getItemViewType(i) == 0) {
                LatestUsedCollectionData latestUsedCollectionData = (LatestUsedCollectionData) getItem(i);
                aVar.c.setOnClickListener(new k(this, aVar, latestUsedCollectionData));
                if (this.a.d.contains(latestUsedCollectionData)) {
                    aVar.c.setChecked(true);
                } else {
                    aVar.c.setChecked(false);
                }
                if (aVar.b != latestUsedCollectionData) {
                    int i2;
                    LayoutParams layoutParams;
                    if (aVar.b != null) {
                        i2 = latestUsedCollectionData.type;
                        layoutParams = aVar.c.getLayoutParams();
                    } else {
                        i2 = latestUsedCollectionData.type;
                        layoutParams = aVar.c.getLayoutParams();
                    }
                    if (layoutParams == null) {
                        layoutParams = new AbsListView.LayoutParams(this.a.j, this.a.j);
                    } else {
                        layoutParams.width = this.a.j;
                        layoutParams.height = this.a.j;
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
            }
            return view;
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Activity activity, int i) {
        activity.startActivityForResult(new Intent(activity, ManageCollectActivity.class), i);
    }

    public static void launch(Activity activity, int i, ArrayList<LatestUsedCollectionData> arrayList) {
        Intent intent = new Intent(activity, ManageCollectActivity.class);
        intent.putExtra("collectData", arrayList);
        activity.startActivityForResult(intent, i);
    }

    protected String f() {
        return "消息";
    }

    protected int a() {
        return R.layout.activity_manage_collect;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.b = (ArrayList) getIntent().getSerializableExtra("collectData");
        this.a = LatestUsedCollectionStore.getCollectionStore(QsbkApp.currentUser.userId);
        g();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.j = (int) ((((float) displayMetrics.widthPixels) - (displayMetrics.density * 20.0f)) / 4.0f);
        setResult(0);
    }

    protected void onResume() {
        super.onResume();
    }

    private void g() {
        this.e = (GridView) findViewById(R.id.image_picker_grid);
        this.f = (TextView) findViewById(R.id.select_delete);
        this.h = (TextView) findViewById(R.id.select_all);
        this.i = (RelativeLayout) findViewById(R.id.bottom_layout);
        this.i.setClickable(false);
        this.i.setOnClickListener(new e(this));
        this.h.setOnClickListener(new h(this));
        this.g = (TextView) findViewById(R.id.select_num);
        this.g.setText(this.d.size() + "");
        this.c = new a(this, this);
        this.e.setAdapter(this.c);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItemCompat.setShowAsAction(menu.add(0, 1, 1, "取消"), 2);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intent intent;
        switch (menuItem.getItemId()) {
            case 1:
                intent = new Intent();
                intent.putExtra("imageInfo", this.b);
                setResult(-1, intent);
                finish();
                return true;
            case 16908332:
                intent = new Intent();
                intent.putExtra("imageInfo", this.b);
                setResult(-1, intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void deleteDatas(ArrayList<Integer> arrayList, ArrayList<LatestUsedCollectionData> arrayList2) {
        this.a.delete((ArrayList) arrayList);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            LatestUsedCollectionData latestUsedCollectionData = (LatestUsedCollectionData) it.next();
            if (latestUsedCollectionData.type == 1) {
                File file = new File(DeviceUtils.getCollectSDPath() + File.separator + latestUsedCollectionData.collectImageDomain.url);
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }
}
