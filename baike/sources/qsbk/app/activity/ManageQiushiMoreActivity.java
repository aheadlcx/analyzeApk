package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.baidu.mobstat.StatService;
import com.qiushibaike.statsdk.StatSDK;
import com.tencent.connect.common.Constants;
import qsbk.app.R;
import qsbk.app.model.Article;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.ShareItem;

public class ManageQiushiMoreActivity extends Activity {
    public static final int CHANGE_DATA_SOURCE = 10001;
    public static final String NEW_PUBLISH_ARTICLE = "is_new_publish_aritilce";
    public static final int NEW_PUBLISH_ARTICLE_DELETE = 10000;
    public static final int NEW_PUBLISH_ARTICLE_RESEND = 9999;
    private Article a;
    private ShareItem b;
    private ShareItem c;
    private ShareItem d;
    private a e;
    private a f;
    private a g;
    private boolean h;

    private static class a {
        final int a;
        final String b;
        final int c;

        a(int i, String str, int i2) {
            this.a = i;
            this.b = str;
            this.c = i2;
        }
    }

    public static void launch(Context context, String str, Article article) {
        Intent intent = new Intent(context, ManageQiushiMoreActivity.class);
        intent.putExtra("userId", str);
        intent.putExtra("article", article);
        if (context instanceof Context) {
            ((Activity) context).startActivityForResult(intent, 1);
        } else {
            context.startActivity(intent);
        }
    }

    public static void launch(Context context, String str, Article article, boolean z) {
        Intent intent = new Intent(context, ManageQiushiMoreActivity.class);
        intent.putExtra("userId", str);
        intent.putExtra("article", article);
        intent.putExtra(NEW_PUBLISH_ARTICLE, z);
        if (context instanceof Context) {
            ((Activity) context).startActivityForResult(intent, 1);
        } else {
            context.startActivity(intent);
        }
    }

    public static int getResendItem() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_resend_dark : R.drawable.share_item_resend;
    }

    public static int getSendToQiuyouCircleItem() {
        return UIHelper.isNightTheme() ? R.drawable.sent_to_qiuyou_circle_dark : R.drawable.sent_to_qiuyou_circle;
    }

    public static int getDeleteItem() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_delete_dark : R.drawable.share_item_delete;
    }

    private static final void a(a aVar, ShareItem shareItem) {
        if (shareItem != null && aVar != null) {
            shareItem.setText(aVar.b);
            shareItem.setImageResource(aVar.a);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.AppTheme.share.new.Dark);
        }
        setContentView(R.layout.activity_manageqiushi_new_delete);
        this.h = getIntent().getBooleanExtra(NEW_PUBLISH_ARTICLE, false);
        this.b = (ShareItem) findViewById(R.id.resend);
        this.c = (ShareItem) findViewById(R.id.send_to_qiuyou_circle);
        this.d = (ShareItem) findViewById(R.id.share_delete);
        this.a = (Article) getIntent().getSerializableExtra("article");
        this.e = new a(getResendItem(), "重新投稿", 100);
        this.f = new a(getSendToQiuyouCircleItem(), "发糗友圈", 101);
        this.g = new a(getDeleteItem(), "删除", 102);
        a(this.e, this.b);
        a(this.f, this.c);
        a(this.g, this.d);
        this.b.setOnClickListener(new tc(this));
        this.c.setOnClickListener(new td(this));
        this.d.setOnClickListener(new te(this));
        if (this.h) {
            this.c.setVisibility(8);
        } else {
            this.c.setVisibility(0);
        }
    }

    private void a(a aVar) {
        Intent intent = new Intent();
        if (this.a != null) {
            intent.putExtra("article", this.a);
        }
        int i = aVar.c;
        if (!this.h) {
            a(i);
        } else if (i == 100) {
            i = 9999;
        } else if (i == 102) {
            i = 10000;
        }
        setResult(i, intent);
        this.h = false;
        finish();
    }

    private void a(int i) {
        String str = "";
        switch (i) {
            case 1:
                str = "sina";
                break;
            case 3:
                str = ThirdPartyConstants.THIRDPARTY_TYLE_QQ;
                break;
            case 4:
                str = "py";
                break;
            case 5:
                str = "copy";
                break;
            case 8:
                str = "pyq";
                break;
            case 9:
                str = "chat";
                break;
            case 10:
                str = Constants.SOURCE_QZONE;
                break;
            case 11:
                str = "delete";
                break;
            case 13:
                str = "anonymous";
                break;
            case 15:
                str = "qiuyouquan";
                break;
            case 100:
                str = "resend";
                break;
            case 101:
                str = "sendToQiuyouCircle";
                break;
        }
        StatService.onEvent(this, "share", str);
        StatSDK.onEvent(this, "share", str);
    }
}
