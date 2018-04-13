package com.qiniu.resumableio;

import android.content.Context;
import android.net.Uri;
import com.qiniu.auth.Authorizer;
import com.qiniu.resumableio.SliceUploadTask.Block;
import com.qiniu.rs.CallBack;
import com.qiniu.rs.CallRet;
import com.qiniu.rs.PutExtra;
import com.qiniu.rs.UploadTaskExecutor;
import com.qiniu.utils.InputStreamAt;
import com.qiniu.utils.UploadTask;
import java.io.File;
import java.util.List;

public class ResumableIO {
    public static UploadTaskExecutor putFile(Context context, Authorizer authorizer, String str, Uri uri, PutExtra putExtra, CallBack callBack) {
        return putFile(context, authorizer, str, uri, putExtra, null, callBack);
    }

    public static UploadTaskExecutor putFile(Context context, Authorizer authorizer, String str, Uri uri, PutExtra putExtra, List<Block> list, CallBack callBack) {
        try {
            return put(authorizer, str, InputStreamAt.fromUri(context, uri), putExtra, list, callBack);
        } catch (Exception e) {
            callBack.onFailure(new CallRet(0, "", e));
            return null;
        }
    }

    public static UploadTaskExecutor putFile(Authorizer authorizer, String str, File file, PutExtra putExtra, CallBack callBack) {
        return putFile(authorizer, str, file, putExtra, null, callBack);
    }

    public static UploadTaskExecutor putFile(Authorizer authorizer, String str, File file, PutExtra putExtra, List<Block> list, CallBack callBack) {
        try {
            return put(authorizer, str, InputStreamAt.fromFile(file), putExtra, list, callBack);
        } catch (Exception e) {
            callBack.onFailure(new CallRet(0, "", e));
            return null;
        }
    }

    public static UploadTaskExecutor put(Authorizer authorizer, String str, InputStreamAt inputStreamAt, PutExtra putExtra, CallBack callBack) {
        return put(authorizer, str, inputStreamAt, putExtra, null, callBack);
    }

    public static UploadTaskExecutor put(Authorizer authorizer, String str, InputStreamAt inputStreamAt, PutExtra putExtra, List<Block> list, CallBack callBack) {
        try {
            UploadTask sliceUploadTask = new SliceUploadTask(authorizer, inputStreamAt, str, putExtra, callBack);
            sliceUploadTask.setLastUploadBlocks(list);
            sliceUploadTask.execute(new Object[0]);
            return new UploadTaskExecutor(sliceUploadTask);
        } catch (Exception e) {
            callBack.onFailure(new CallRet(0, "", e));
            return null;
        }
    }
}
