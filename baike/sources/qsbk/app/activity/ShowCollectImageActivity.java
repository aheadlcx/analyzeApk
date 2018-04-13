package qsbk.app.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.ColorMatrixColorFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import com.facebook.drawee.view.SimpleDraweeView;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.utils.UIHelper;

public class ShowCollectImageActivity extends BaseActionBarActivity {
    private String a = null;
    private SimpleDraweeView b;
    private Uri c;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected String f() {
        return "添加的表情";
    }

    protected int a() {
        return R.layout.activity_show_collect_image;
    }

    @SuppressLint({"NewApi"})
    private void g() {
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItemCompat.setShowAsAction(menu.add(0, 1, 1, "使用"), 2);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 1:
                Intent intent = new Intent();
                intent.putExtra("uploadUri", this.a.toString());
                setResult(-1, intent);
                finish();
                return true;
            case 16908332:
                finish();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void c_() {
        setTheme(R.style.Day.ImagePreview);
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        setResult(0);
        handleIntent();
        initWidget();
    }

    public void handleIntent() {
        this.a = getIntent().getStringExtra("uri");
        this.c = Uri.parse(this.a);
    }

    public void initWidget() {
        if (TextUtils.isEmpty(this.c.getPath())) {
            finish();
            return;
        }
        g();
        this.b = (SimpleDraweeView) findViewById(R.id.image);
        if (UIHelper.isNightTheme()) {
            this.b.setColorFilter(new ColorMatrixColorFilter(ImageViewer.BT_SELECTED));
        }
        this.b.setOnClickListener(null);
        this.b.setImageURI(this.c);
    }
}
