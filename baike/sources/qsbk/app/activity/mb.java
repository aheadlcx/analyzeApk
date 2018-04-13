package qsbk.app.activity;

import android.net.Uri;
import com.qiniu.rs.CallBack;
import com.qiniu.rs.CallRet;
import com.qiniu.rs.UploadCallRet;
import qsbk.app.utils.DebugUtil;

class mb extends CallBack {
    final /* synthetic */ Uri a;
    final /* synthetic */ GroupInfoActivity b;

    mb(GroupInfoActivity groupInfoActivity, Uri uri) {
        this.b = groupInfoActivity;
        this.a = uri;
    }

    public void onProcess(long j, long j2) {
        DebugUtil.debug(GroupInfoActivity.a, "onProcess  current:" + j + "  total:" + j2);
    }

    public void onSuccess(UploadCallRet uploadCallRet) {
        this.b.a(this.a, uploadCallRet.getKey());
    }

    public void onFailure(CallRet callRet) {
        this.b.j();
        DebugUtil.debug(GroupInfoActivity.a, "onFailure  " + callRet.getResponse());
        this.b.b("图片上传失败，请重试");
        if (callRet.getException() != null) {
            callRet.getException().printStackTrace();
        }
    }
}
