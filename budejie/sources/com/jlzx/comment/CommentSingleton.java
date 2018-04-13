package com.jlzx.comment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.umeng.onlineconfig.OnlineConfigAgent;

public class CommentSingleton {
    private static CommentSingleton singleton = null;
    private Context appcontext;
    private String leftBtnTxt;
    private String message;
    private String packageName;
    private PopStrategy popStrategy;
    private String rightBtnTxt;
    private int thresholdNum;
    private String title;

    private CommentSingleton() {
        this.title = null;
        this.message = null;
        this.rightBtnTxt = null;
        this.leftBtnTxt = null;
        this.thresholdNum = 7;
        this.appcontext = null;
        this.packageName = null;
        this.popStrategy = null;
        this.title = "通知";
        this.message = "恳请给姐一个5星好评，姐会给你推荐更多好玩的内容哦~";
        this.rightBtnTxt = "马上去评分";
        this.leftBtnTxt = "残忍拒绝";
    }

    public static synchronized CommentSingleton getInstance() {
        CommentSingleton commentSingleton;
        synchronized (CommentSingleton.class) {
            if (singleton == null) {
                singleton = new CommentSingleton();
            }
            commentSingleton = singleton;
        }
        return commentSingleton;
    }

    public void SetDefault(String str, String str2, String str3, String str4) {
        this.title = str;
        this.message = str2;
        this.rightBtnTxt = str4;
        this.leftBtnTxt = str3;
    }

    public boolean IsOpen() {
        return this.popStrategy == null ? false : this.popStrategy.IsOpen();
    }

    public void Start(Context context, int i) {
        if (context != null) {
            try {
                if (this.popStrategy == null) {
                    this.popStrategy = new PopStrategyImp(context, this.thresholdNum);
                }
                if (IsOpen()) {
                    this.appcontext = context.getApplicationContext();
                    this.packageName = context.getPackageName();
                    GetConfigParams(context);
                    if (this.popStrategy.CheckCondition()) {
                        new CommentSingleton$PopAsyncTask(this).execute(new Integer[]{Integer.valueOf(i)});
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    private void GetConfigParams(Context context) {
        try {
            this.message = OnlineConfigAgent.getInstance().getConfigParams(context, "market_comment_message");
            this.rightBtnTxt = OnlineConfigAgent.getInstance().getConfigParams(context, "market_comment_posbuttontxt");
            this.leftBtnTxt = OnlineConfigAgent.getInstance().getConfigParams(context, "market_comment_negbuttontxt");
        } catch (Exception e) {
        }
    }

    private void ShowMarketDialog() {
        Builder builder = new Builder(this.appcontext);
        builder.setTitle(this.title);
        builder.setMessage(this.message);
        builder.setCancelable(true);
        builder.setNegativeButton(this.rightBtnTxt, new CommentSingleton$1(this));
        builder.setPositiveButton(this.leftBtnTxt, new CommentSingleton$2(this));
        builder.setOnCancelListener(new CommentSingleton$3(this));
        AlertDialog create = builder.create();
        create.getWindow().setType(2003);
        create.show();
    }

    public void Reject() {
        try {
            this.popStrategy.Reject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Comment() {
        try {
            this.popStrategy.Comment();
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(268435456);
            intent.setData(Uri.parse("market://details?id=" + this.packageName));
            this.appcontext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
