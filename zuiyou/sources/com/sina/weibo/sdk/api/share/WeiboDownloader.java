package com.sina.weibo.sdk.api.share;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.utils.Utility;
import com.tencent.wcdb.database.SQLiteDatabase;

public class WeiboDownloader {
    private static final String CANCEL_CHINESS = "以后再说";
    private static final String CANCEL_ENGLISH = "Download Later";
    private static final String OK_CHINESS = "现在下载";
    private static final String OK_ENGLISH = "Download Now";
    private static final String PROMPT_CHINESS = "未安装微博客户端，是否现在去下载？";
    private static final String PROMPT_ENGLISH = "Sina Weibo client is not installed, download now?";
    private static final String TITLE_CHINESS = "提示";
    private static final String TITLE_ENGLISH = "Notice";

    /* renamed from: com.sina.weibo.sdk.api.share.WeiboDownloader$1 */
    class AnonymousClass1 implements OnClickListener {
        private final /* synthetic */ Context val$context;

        AnonymousClass1(Context context) {
            this.val$context = context;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            WeiboDownloader.downloadWeibo(this.val$context);
        }
    }

    /* renamed from: com.sina.weibo.sdk.api.share.WeiboDownloader$2 */
    class AnonymousClass2 implements OnClickListener {
        private final /* synthetic */ IWeiboDownloadListener val$listener;

        AnonymousClass2(IWeiboDownloadListener iWeiboDownloadListener) {
            this.val$listener = iWeiboDownloadListener;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            if (this.val$listener != null) {
                this.val$listener.onCancel();
            }
        }
    }

    public static Dialog createDownloadConfirmDialog(Context context, IWeiboDownloadListener iWeiboDownloadListener) {
        CharSequence charSequence = TITLE_CHINESS;
        CharSequence charSequence2 = PROMPT_CHINESS;
        CharSequence charSequence3 = OK_CHINESS;
        CharSequence charSequence4 = CANCEL_CHINESS;
        if (!Utility.isChineseLocale(context.getApplicationContext())) {
            charSequence = TITLE_ENGLISH;
            charSequence2 = PROMPT_ENGLISH;
            charSequence3 = OK_ENGLISH;
            charSequence4 = CANCEL_ENGLISH;
        }
        return new Builder(context).setMessage(charSequence2).setTitle(charSequence).setPositiveButton(charSequence3, new AnonymousClass1(context)).setNegativeButton(charSequence4, new AnonymousClass2(iWeiboDownloadListener)).create();
    }

    private static void downloadWeibo(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        intent.setData(Uri.parse(WBConstants.WEIBO_DOWNLOAD_URL));
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
