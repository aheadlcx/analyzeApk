package qsbk.app.activity;

import com.qiniu.rs.CallBack;
import com.qiniu.rs.CallRet;
import com.qiniu.rs.UploadCallRet;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.http.HttpTask;
import qsbk.app.utils.DebugUtil;

class acn extends CallBack {
    final /* synthetic */ SettingPersonalBigCoverBaseActivity a;

    acn(SettingPersonalBigCoverBaseActivity settingPersonalBigCoverBaseActivity) {
        this.a = settingPersonalBigCoverBaseActivity;
    }

    public void onSuccess(UploadCallRet uploadCallRet) {
        DebugUtil.debug(SettingPersonalBigCoverBaseActivity.b, "onSuccess  " + uploadCallRet.getKey());
        Map hashMap = new HashMap();
        hashMap.put("name", uploadCallRet.getKey());
        String str = Constants.URL_QINIU_KEY;
        String str2 = Constants.URL_QINIU_KEY;
        Object[] objArr = new Object[1];
        QsbkApp.getInstance();
        objArr[0] = QsbkApp.currentUser.userId;
        HttpTask httpTask = new HttpTask(str, String.format(str2, objArr), new aco(this));
        httpTask.setMapParams(hashMap);
        httpTask.execute(new Void[0]);
    }

    public void onProcess(long j, long j2) {
        DebugUtil.debug(SettingPersonalBigCoverBaseActivity.b, "onProcess  current:" + j + "  total:" + j2);
    }

    public void onFailure(CallRet callRet) {
        this.a.b();
        DebugUtil.debug(SettingPersonalBigCoverBaseActivity.b, "onFailure  " + callRet.getResponse());
        this.a.a(this.a.getApplication().getString(R.string.upload_big_cover_fail));
        if (callRet.getException() != null) {
            callRet.getException().printStackTrace();
        }
    }
}
