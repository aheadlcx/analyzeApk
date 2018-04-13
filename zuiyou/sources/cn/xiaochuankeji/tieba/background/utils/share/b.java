package cn.xiaochuankeji.tieba.background.utils.share;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import cn.htjyb.c.d;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.newshare.ShareDataModel;
import com.izuiyou.auth.sina.c;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Resp;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

public class b implements IUiListener {
    public static b a;
    private a b;

    public interface a {
        void a(boolean z);
    }

    private b() {
    }

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public void a(int i, Activity activity, WebPageShareStruct webPageShareStruct) {
        String thumbPath = webPageShareStruct.getThumbPath();
        String titleBy = webPageShareStruct.getTitleBy(i);
        String descBy = webPageShareStruct.getDescBy(i);
        String targetUrl = webPageShareStruct.getTargetUrl();
        switch (i) {
            case 1:
                com.izuiyou.auth.a.a.a().a(activity, false, titleBy, descBy, targetUrl, thumbPath, this);
                break;
            case 2:
                c.a().a(false, titleBy, descBy, targetUrl, thumbPath);
                break;
            case 3:
                c.a().a(activity, a(titleBy, descBy, targetUrl, webPageShareStruct.suffix), thumbPath);
                break;
            case 4:
                if (TextUtils.isEmpty(descBy)) {
                    descBy = titleBy;
                } else {
                    descBy = titleBy + " - " + descBy;
                }
                c.a().a(true, null, descBy, targetUrl, thumbPath);
                break;
            case 5:
                com.izuiyou.auth.a.a.a().a(activity, true, titleBy, descBy, targetUrl, thumbPath, this);
                break;
        }
        if (this.b != null) {
            this.b.a(true);
        }
    }

    public void a(Activity activity, ShareDataModel shareDataModel) {
        String thumbPath = shareDataModel.getThumbPath();
        String titleBy = shareDataModel.getTitleBy();
        String descriptionBy = shareDataModel.getDescriptionBy();
        String targetUrl = shareDataModel.getTargetUrl();
        switch (shareDataModel.getSharePlatformFlag()) {
            case 1:
                com.izuiyou.auth.a.a.a().a(activity, false, titleBy, descriptionBy, targetUrl, thumbPath, this);
                return;
            case 2:
                c.a().a(false, titleBy, descriptionBy, targetUrl, thumbPath);
                return;
            case 3:
                c.a().a(activity, a(titleBy, descriptionBy, targetUrl, null), thumbPath);
                return;
            case 4:
                if (TextUtils.isEmpty(descriptionBy)) {
                    descriptionBy = titleBy;
                } else {
                    descriptionBy = titleBy + " - " + descriptionBy;
                }
                c.a().a(true, null, descriptionBy, targetUrl, thumbPath);
                return;
            case 5:
                com.izuiyou.auth.a.a.a().a(activity, true, titleBy, descriptionBy, targetUrl, thumbPath, this);
                return;
            default:
                return;
        }
    }

    private String a(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str4)) {
            str4 = "(分享自@最右APP)看详情戳链接→_→";
        }
        double f = 130.0d - d.f(str4);
        if (!TextUtils.isEmpty(str2)) {
            str = str + " - " + str2;
        }
        if (d.f(str) > f) {
            str = d.a(str, f - 1.5d) + "...";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(str4);
        stringBuilder.append(str3);
        return stringBuilder.toString();
    }

    public void a(int i, Activity activity, Bitmap bitmap, String str) {
        switch (i) {
            case 1:
                com.izuiyou.auth.a.a.a().a(activity, false, str, this);
                break;
            case 2:
                c.a().a(false, bitmap, str);
                break;
            case 3:
                c.a().a(activity, "来自最右的搞笑图片(分享自@最右APP)", str);
                break;
            case 4:
                c.a().a(true, bitmap, str);
                break;
        }
        if (this.b != null) {
            this.b.a(true);
        }
    }

    public void b(int i, Activity activity, Bitmap bitmap, String str) {
        switch (i) {
            case 1:
                com.izuiyou.auth.a.a.a().a(activity, false, str, this);
                break;
            case 2:
                c.a().a(bitmap, str);
                break;
        }
        if (this.b != null) {
            this.b.a(true);
        }
    }

    public void a(Resp resp) {
        if (resp.errCode == 0) {
            g.a("分享成功");
        } else if (-2 != resp.errCode) {
            g.a("分享失败");
        }
    }

    public void a(int i, int i2, Intent intent) {
        com.izuiyou.auth.a.a.a().a(i, i2, intent);
    }

    public void onComplete(Object obj) {
        g.b("分享成功");
    }

    public void onError(UiError uiError) {
        g.a("分享失败");
    }

    public void onCancel() {
    }
}
