package qsbk.app.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.fragments.BrowseBaseFragment.MediaClickListener;
import qsbk.app.fragments.BrowseImgFragment;
import qsbk.app.fragments.BrowseLongImgFragment;
import qsbk.app.fragments.BrowseUltraFragment;
import qsbk.app.model.ImageInfo;
import qsbk.app.utils.ToastAndDialog;

public class ImagePreviewActivity extends BaseActionBarActivity implements OnPageChangeListener, MediaClickListener {
    public static final String KEY_CHECKED_ARRAY = "_checked_array_";
    public static final String KEY_PIC_ALL = "_pic_all_";
    public static final String KEY_POSITION = "_pic_position_";
    private ViewPager a;
    private ArrayList<ImageInfo> b = new ArrayList();
    private ArrayList<ImageInfo> c = new ArrayList();
    private PagerAdapter d;
    private int e = 0;
    private ImageInfo f = null;
    private RelativeLayout g;
    private TextView h;
    private TextView i;
    private int j;

    public class ImagePreviewAdapter extends FragmentStatePagerAdapter {
        final LayoutInflater a;
        final /* synthetic */ ImagePreviewActivity b;

        ImagePreviewAdapter(ImagePreviewActivity imagePreviewActivity, Context context, FragmentManager fragmentManager) {
            this.b = imagePreviewActivity;
            super(fragmentManager);
            this.a = LayoutInflater.from(context);
        }

        public int getCount() {
            return this.b.c.size();
        }

        public Fragment getItem(int i) {
            ImageInfo imageInfo = (ImageInfo) this.b.c.get(i);
            switch (op.a[imageInfo.mediaFormat.ordinal()]) {
                case 1:
                case 2:
                    return BrowseUltraFragment.newInstance(imageInfo);
                case 3:
                    return BrowseLongImgFragment.newInstance(imageInfo);
                default:
                    return BrowseImgFragment.newInstance(imageInfo);
            }
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return e();
    }

    public static void launchForResult(Activity activity, ArrayList<ImageInfo> arrayList, ArrayList<ImageInfo> arrayList2, int i, int i2) {
        Intent intent = new Intent();
        intent.setClass(activity, ImagePreviewActivity.class);
        intent.putExtra(KEY_CHECKED_ARRAY, arrayList);
        intent.putExtra(KEY_PIC_ALL, arrayList2);
        intent.putExtra(KEY_POSITION, i);
        activity.startActivityForResult(intent, i2);
    }

    protected String e() {
        return (this.e + 1) + MqttTopic.TOPIC_LEVEL_SEPARATOR + (this.c == null ? "..." : this.c.size() + "");
    }

    protected int a() {
        return R.layout.activity_image_preview;
    }

    @SuppressLint({"NewApi"})
    private void f() {
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.image_preview_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem findItem = menu.findItem(R.id.select_icon);
        if (this.f == null || !this.b.contains(this.f)) {
            findItem.setIcon(R.drawable.image_picker_unchecked);
        } else {
            findItem.setIcon(R.drawable.image_picker_checked);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                Intent intent = new Intent();
                intent.putExtra(ImagesPickerActivity.CHECKED_ARRAY_KEY, this.b);
                setResult(99, intent);
                finish();
                break;
            case R.id.select_icon:
                if (!this.b.contains(this.f)) {
                    if (this.b.size() >= ImagesPickerActivity.maxCount) {
                        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, String.format("最多只能选取%s张图片哦", new Object[]{Integer.valueOf(ImagesPickerActivity.maxCount)}), Integer.valueOf(0)).show();
                        break;
                    }
                    if (this.f.isOverSize()) {
                        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, String.format("图片不能大于%s", new Object[]{this.f.getMaxSize()})).show();
                    } else {
                        this.b.add(this.f);
                    }
                    this.h.setText(this.b.size() + "");
                    ActivityCompat.invalidateOptionsMenu(this);
                    break;
                }
                this.b.remove(this.f);
                this.h.setText(this.b.size() + "");
                ActivityCompat.invalidateOptionsMenu(this);
                break;
            default:
                super.onOptionsItemSelected(menuItem);
                break;
        }
        return false;
    }

    protected void c_() {
        setTheme(R.style.Day.ImagePreview);
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        if (handleIntent(getIntent())) {
            initWidget();
            initListener();
            return;
        }
        finish();
    }

    public boolean handleIntent(Intent intent) {
        if (intent.getExtras() != null && intent.getExtras().getClassLoader().getClass().getSimpleName().contains("BootClassLoader")) {
            intent.setExtrasClassLoader(ImagePreviewActivity.class.getClassLoader());
        }
        this.c = (ArrayList) intent.getSerializableExtra(KEY_PIC_ALL);
        if (this.c == null || this.c.size() == 0) {
            return false;
        }
        this.b = (ArrayList) intent.getSerializableExtra(KEY_CHECKED_ARRAY);
        this.e = intent.getIntExtra(KEY_POSITION, 0);
        if (this.e < 0) {
            this.e = 0;
        }
        if (this.e >= this.c.size()) {
            this.e = this.c.size() - 1;
        }
        this.f = (ImageInfo) this.c.get(this.e);
        this.j = intent.getIntExtra("KEY_PICK_IAMGE", 0);
        return true;
    }

    public void initWidget() {
        f();
        this.a = (ViewPager) findViewById(R.id.priview_viewpager);
        this.g = (RelativeLayout) findViewById(R.id.bottom_layout);
        this.h = (TextView) findViewById(R.id.select_num);
        this.h.setText(this.b.size() + "");
        this.i = (TextView) findViewById(R.id.sent_textview);
        this.i.setText("确定");
    }

    public void initListener() {
        this.d = new ImagePreviewAdapter(this, this, getSupportFragmentManager());
        this.a.setAdapter(this.d);
        this.a.setCurrentItem(this.e);
        this.a.setOnPageChangeListener(this);
        this.g.setOnClickListener(new oo(this));
        onPageSelected(this.e);
    }

    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(ImagesPickerActivity.CHECKED_ARRAY_KEY, this.b);
        setResult(99, intent);
        super.onBackPressed();
    }

    public void onMediaClick() {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        this.e = i;
        this.f = (ImageInfo) this.c.get(i);
        ActivityCompat.invalidateOptionsMenu(this);
        setTitle(e());
    }

    public void onPageScrollStateChanged(int i) {
    }
}
