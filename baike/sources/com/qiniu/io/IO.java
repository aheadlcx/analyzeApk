package com.qiniu.io;

import android.content.Context;
import android.net.Uri;
import com.qiniu.auth.Authorizer;
import com.qiniu.rs.CallBack;
import com.qiniu.rs.CallRet;
import com.qiniu.rs.PutExtra;
import com.qiniu.rs.UploadTaskExecutor;
import com.qiniu.utils.InputStreamAt;
import com.qiniu.utils.UploadTask;
import java.io.File;

public class IO {
    public static String UNDEFINED_KEY = null;

    public static UploadTaskExecutor putFile(Context context, Authorizer authorizer, String str, Uri uri, PutExtra putExtra, CallBack callBack) {
        try {
            return put(authorizer, str, InputStreamAt.fromUri(context, uri), putExtra, callBack);
        } catch (Exception e) {
            callBack.onFailure(new CallRet(0, "", e));
            return null;
        }
    }

    public static UploadTaskExecutor putFile(Authorizer authorizer, String str, File file, PutExtra putExtra, CallBack callBack) {
        try {
            return put(authorizer, str, InputStreamAt.fromFile(file), putExtra, callBack);
        } catch (Exception e) {
            callBack.onFailure(new CallRet(0, "", e));
            return null;
        }
    }

    public static UploadTaskExecutor put(Authorizer authorizer, String str, InputStreamAt inputStreamAt, PutExtra putExtra, CallBack callBack) {
        try {
            UploadTask simpleUploadTask = new SimpleUploadTask(authorizer, inputStreamAt, str, putExtra, callBack);
            simpleUploadTask.execute(new Object[0]);
            return new UploadTaskExecutor(simpleUploadTask);
        } catch (Exception e) {
            callBack.onFailure(new CallRet(0, "", e));
            return null;
        }
    }
}
