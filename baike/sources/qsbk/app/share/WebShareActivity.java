package qsbk.app.share;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import com.baidu.mobstat.StatService;
import com.qiushibaike.statsdk.StatSDK;
import com.tencent.connect.common.Constants;
import qsbk.app.R;
import qsbk.app.model.ShareMsgItem;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.ShareItem;

public class WebShareActivity extends Activity implements OnItemClickListener {
    private static final String b = WebShareActivity.class.getSimpleName();
    private static int c;
    ShareMsgItem a;
    private a[] d;
    private GridView e;

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

    private class b extends BaseAdapter {
        final /* synthetic */ WebShareActivity a;

        private b(WebShareActivity webShareActivity) {
            this.a = webShareActivity;
        }

        public int getCount() {
            return this.a.d.length;
        }

        public Object getItem(int i) {
            return this.a.d[i];
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View shareItem;
            if (view == null) {
                shareItem = new ShareItem(this.a);
                shareItem.measure(WebShareActivity.c, WebShareActivity.c);
            } else {
                shareItem = view;
            }
            a aVar = this.a.d[i];
            ShareItem shareItem2 = (ShareItem) shareItem;
            shareItem2.setText(aVar.b);
            shareItem2.setImageResource(aVar.a);
            shareItem.setOnClickListener(new ai(this, i));
            return shareItem;
        }
    }

    public static int getShareItemWXPYQ() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_wx_pyq_dark : R.drawable.share_item_wx_pyq;
    }

    public static int getShareItemWXPY() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_wx_py_dark : R.drawable.share_item_wx_py;
    }

    public static int getShareItemQQ() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_qq_dark : R.drawable.share_item_qq;
    }

    public static int getShareItemQZone() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_qzone_dark : R.drawable.share_item_qzone;
    }

    public static int getShareItemReport() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_report_dark : R.drawable.share_item_report;
    }

    public static int getShareItemSina() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_sina_dark : R.drawable.share_item_sina;
    }

    public static int getShareItemChat() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_chat_dark : R.drawable.share_item_chat;
    }

    public static int getShareItemQyq() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_qyq_dark : R.drawable.share_item_qyq;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null) {
            this.a = (ShareMsgItem) intent.getSerializableExtra("share_msg_item");
        }
        if (this.a == null) {
            finish();
        }
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.AppTheme.share.new.Dark);
        }
        setContentView(R.layout.activity_new_share_web);
        if (this.a.shareFor == 5 || this.a.shareType == 2) {
            this.d = new a[]{new a(getShareItemWXPYQ(), "微信朋友圈", 8), new a(getShareItemWXPY(), "微信好友", 4), new a(getShareItemQQ(), "QQ好友", 3), new a(getShareItemQZone(), "QQ空间", 10), new a(getShareItemSina(), "新浪微博", 1)};
        } else if (this.a.shareType == 1) {
            this.d = new a[]{new a(getShareItemChat(), "糗友/群", 9), new a(getShareItemQyq(), "糗友圈", 15)};
        } else {
            this.d = new a[]{new a(getShareItemWXPYQ(), "微信朋友圈", 8), new a(getShareItemWXPY(), "微信好友", 4), new a(getShareItemQQ(), "QQ好友", 3), new a(getShareItemQZone(), "QQ空间", 10), new a(getShareItemSina(), "新浪微博", 1), new a(getShareItemChat(), "糗友/群", 9), new a(getShareItemQyq(), "糗友圈", 15)};
        }
        this.e = (GridView) findViewById(R.id.grid);
        c = getResources().getDimensionPixelSize(R.dimen.new_share_item_size);
        this.e.setAdapter(new b());
        this.e.setOnItemClickListener(this);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        DebugUtil.debug("QiushiShare", "NewShareActivity, onActivityResult, requestCode=" + i + ",requestCode=" + i + ",data=" + intent);
        super.onActivityResult(i, i2, intent);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        a(this.d[i]);
    }

    private void a(a aVar) {
        Intent intent = new Intent();
        int i = aVar.c;
        a(i);
        setResult(i, intent);
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
            case 8:
                str = "pyq";
                break;
            case 9:
                str = "chat";
                break;
            case 10:
                str = Constants.SOURCE_QZONE;
                break;
            case 15:
                str = "qiuyouquan";
                break;
        }
        StatService.onEvent(this, "share", str);
        StatSDK.onEvent(this, "share", str);
    }
}
