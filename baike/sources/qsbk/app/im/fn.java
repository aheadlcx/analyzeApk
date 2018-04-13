package qsbk.app.im;

import android.graphics.Bitmap;
import android.text.TextUtils;
import qsbk.app.QsbkApp;
import qsbk.app.http.GetBitmapFromUrlHttpTask;
import qsbk.app.im.CollectEmotion.CollectImageDomain;
import qsbk.app.utils.FileUtils;
import qsbk.app.utils.Md5;
import qsbk.app.utils.ToastAndDialog;

class fn extends GetBitmapFromUrlHttpTask {
    final /* synthetic */ String a;
    final /* synthetic */ IMChatBaseActivityEx b;

    fn(IMChatBaseActivityEx iMChatBaseActivityEx, String str) {
        this.b = iMChatBaseActivityEx;
        this.a = str;
    }

    protected Bitmap a(String... strArr) {
        try {
            IMChatBaseActivityEx.downloadUrls.add(this.a);
            return FileUtils.getImage(this.a);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void a(Bitmap bitmap) {
        IMChatBaseActivityEx.downloadUrls.remove(this.a);
        if (bitmap == null) {
            return;
        }
        if (TextUtils.isEmpty(FileUtils.saveDrawable(bitmap, Md5.MD5(this.a) + ".jpg", "qsbk/qiushibaike/myCollect"))) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "保存失败，请重试", Integer.valueOf(0)).show();
            return;
        }
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "图片已保存到收藏！", Integer.valueOf(0)).show();
        this.b.onSavePhotoSucc(new CollectImageDomain(Md5.MD5(this.a) + ".jpg", Md5.MD5(this.a), this.a));
    }
}
