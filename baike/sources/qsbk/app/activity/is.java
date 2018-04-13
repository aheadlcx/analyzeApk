package qsbk.app.activity;

import android.net.Uri;
import com.qiniu.rs.CallBack;
import com.qiniu.rs.CallRet;
import com.qiniu.rs.UploadCallRet;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class is extends CallBack {
    final /* synthetic */ Uri a;
    final /* synthetic */ CircleTopicActivity b;

    is(CircleTopicActivity circleTopicActivity, Uri uri) {
        this.b = circleTopicActivity;
        this.a = uri;
    }

    public void onProcess(long j, long j2) {
    }

    public void onSuccess(UploadCallRet uploadCallRet) {
        this.b.a(this.a, uploadCallRet.getKey());
    }

    public void onFailure(CallRet callRet) {
        this.b.r();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "图片上传失败", Integer.valueOf(0)).show();
        if (callRet.getException() != null) {
            callRet.getException().printStackTrace();
        }
    }
}
