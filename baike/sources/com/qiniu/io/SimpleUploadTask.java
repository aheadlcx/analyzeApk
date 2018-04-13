package com.qiniu.io;

import com.facebook.common.util.UriUtil;
import com.qiniu.auth.Authorizer;
import com.qiniu.conf.Conf;
import com.qiniu.rs.CallBack;
import com.qiniu.rs.CallRet;
import com.qiniu.rs.PutExtra;
import com.qiniu.utils.InputStreamAt;
import com.qiniu.utils.MultipartEntity;
import com.qiniu.utils.UploadTask;
import com.qiniu.utils.Util;
import java.io.IOException;
import java.util.Map.Entry;
import qsbk.app.activity.pay.PayPWDUniversalActivity;

public class SimpleUploadTask extends UploadTask {
    public SimpleUploadTask(Authorizer authorizer, InputStreamAt inputStreamAt, String str, PutExtra putExtra, CallBack callBack) throws IOException {
        super(authorizer, inputStreamAt, str, putExtra, callBack);
    }

    protected CallRet a(Object... objArr) {
        CallRet a = a(Conf.UP_HOST);
        if (Util.needChangeUpAdress(a)) {
            try {
                this.f.reset();
                a = a(Conf.UP_HOST2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return a;
    }

    private CallRet a(String str) {
        CallRet handleResult;
        try {
            this.d = Util.newPost(str);
            this.d.setEntity(c());
            handleResult = Util.handleResult(b().execute(this.d));
        } catch (Exception e) {
            handleResult = new CallRet(0, "", e);
        } finally {
            this.d = null;
        }
        return handleResult;
    }

    private MultipartEntity c() throws IOException {
        MultipartEntity multipartEntity = new MultipartEntity();
        if (this.c != null) {
            multipartEntity.addField(PayPWDUniversalActivity.KEY, this.c);
        }
        if (this.g.checkCrc == 1) {
            this.g.crc32 = this.f.crc32();
        }
        if (this.g.checkCrc != 0) {
            multipartEntity.addField("crc32", this.g.crc32 + "");
        }
        if (this.g.params != null) {
            for (Entry entry : this.g.params.entrySet()) {
                if (((String) entry.getKey()).startsWith("x:") && entry.getValue() != null) {
                    multipartEntity.addField((String) entry.getKey(), (String) entry.getValue());
                }
            }
        }
        multipartEntity.addField("token", this.e.getUploadToken());
        multipartEntity.addFile(UriUtil.LOCAL_FILE_SCHEME, this.g.mimeType, this.f.getFilename(), this.f);
        multipartEntity.setProcessNotify(new a(this));
        return multipartEntity;
    }
}
